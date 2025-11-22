package net.bexla.orevolution.content.types;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;
import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import com.google.common.graph.ElementOrder;
import com.google.common.graph.GraphBuilder;
import com.google.common.graph.MutableGraph;
import com.google.gson.*;
import it.unimi.dsi.fastutil.booleans.BooleanConsumer;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.packs.resources.PreparableReloadListener;
import net.minecraft.server.packs.resources.Resource;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.server.packs.resources.SimplePreparableReloadListener;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.util.GsonHelper;
import net.minecraft.util.profiling.ProfilerFiller;
import net.minecraft.world.item.DiggerItem;
import net.minecraft.world.item.Tier;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.ClientPlayerNetworkEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.fml.loading.FMLEnvironment;
import net.minecraftforge.fml.loading.toposort.TopologicalSort;
import net.minecraftforge.network.NetworkDirection;
import net.minecraftforge.network.NetworkEvent;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.PacketDistributor;
import net.minecraftforge.network.simple.SimpleChannel;
import net.minecraftforge.server.ServerLifecycleHooks;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.IOException;
import java.io.Reader;
import java.util.*;
import java.util.function.Supplier;
import java.util.stream.Collectors;

// Credits to Forge/Mojang for the majority of this code, as it's heavily based on their tier sorting system (TierSortingRegistry).
public class TierProgressRegistry {
    private static final Logger LOGGER = LogManager.getLogger();
    private static final ResourceLocation ITEM_TIER_ORDERING_JSON = new ResourceLocation("forge", "item_tier_ordering.json");
    private static final Map<Tier, TagKey<Block>> tierTagMap = new HashMap<>();
    private static final Map<Tier, Tier> secondaryTiers = new HashMap<>();

    /**
     * Registers a tier into the tier sorting registry.
     * @param tier The tier to register
     * @param name The name to use internally for dependency resolution
     * @param after List of tiers to place this tier after (the tiers in the list will be considered lesser tiers)
     * @param before List of tiers to place this tier before (the tiers in the list will be considered better tiers)
     * @param tag The tag to associate with this tier, for isCorrectTierForDrops checks
     */
    public static synchronized Tier registerTier(Tier tier, ResourceLocation name, List<Object> after, List<Object> before, TagKey<Block> tag)
    {
        if (tiers.containsKey(name))
            throw new IllegalStateException("Duplicate tier name " + name);

        processTier(tier, name, after, before);
        registerTierTag(tier, tag);

        hasCustomTiers = true;
        return tier;
    }

    /**
     * Registers a tier into the tier sorting registry.
     * @param tier The tier to register
     * @param name The name to use internally for dependency resolution
     * @param after List of tiers to place this tier after (the tiers in the list will be considered lesser tiers)
     * @param before List of tiers to place this tier before (the tiers in the list will be considered better tiers)
     */
    public static synchronized Tier registerTier(Tier tier, ResourceLocation name, List<Object> after, List<Object> before)
    {
        if (tiers.containsKey(name))
            throw new IllegalStateException("Duplicate tier name " + name);

        processTier(tier, name, after, before);

        hasCustomTiers = true;
        return tier;
    }

    public static synchronized void registerTierTag(Tier tier, TagKey<Block> tag)
    {
        tierTagMap.put(tier, tag);
    }


    /**
     * registers a secondary progression tier, mostly for tiers that aren't necessary for the progression, and are still sorted.
     * <br>
     * The tier associated MUST be part of the registered tiers in TierProgressRegistry.
     * <br>
     * eg: Verdite-tiered items will show up the same way as iron-tiered items, but won't gate any progression.
     * <br><br>
     * This helps in things like Mining tier tooltip, where you want to show the next progression tier, but don't want this tier to show itself as part of that progression.
     */
    public static synchronized Tier registerSecondaryTier(Tier tier, Tier associated)
    {
        if (secondaryTiers.containsKey(tier))
            throw new IllegalStateException("Duplicate tier name: " + tier);

        if(!tiers.containsKey(getTierName(associated)))
            throw new IllegalStateException("Tier not found: " + associated);

        secondaryTiers.put(tier, associated);

        return tier;
    }

    public static synchronized void removeSecondaryTier(Tier tier, Tier associated)
    {
        secondaryTiers.remove(tier, associated);
    }

    public static Tier getAssociatedTierFromSecondary(Tier tier) {
        return secondaryTiers.get(tier);
    }


    public static synchronized void removeTier(ResourceLocation name) {
        Tier tier = tiers.get(name);
        if (tier == null) {
            LOGGER.warn("Tried to remove unregistered tier: {}", name);
            return;
        }

        tiers.remove(name);

        // Remove all edges related to this tier
        edges.removeAll(name);
        edges.entries().removeIf(e -> e.getValue().equals(name));

        // Remove tag if present
        tierTagMap.remove(tier);

        // Recalculate order after removal
        recalculateItemTiers();

        // Update state flag
        hasCustomTiers = !tiers.isEmpty();
    }

    /**
     * Removes all non-vanilla (custom) tiers from the registry.
     */
    public static synchronized void removeAllCustomTiers() {
        List<ResourceLocation> vanilla = List.of(
                new ResourceLocation("wood"),
                new ResourceLocation("stone"),
                new ResourceLocation("iron"),
                new ResourceLocation("diamond"),
                new ResourceLocation("netherite")
        );

        tiers.keySet().removeIf(name -> !vanilla.contains(name));
        edges.keySet().removeIf(name -> !vanilla.contains(name));
        edges.entries().removeIf(e -> !vanilla.contains(e.getKey()) || !vanilla.contains(e.getValue()));

        tierTagMap.entrySet().removeIf(entry ->
                tiers.inverse().get(entry.getKey()) == null
        );

        recalculateItemTiers();
        hasCustomTiers = false;
    }

    /**
     * Returns the list of tiers in the order defined by the dependencies.
     * This list will remain valid
     * @return An unmodifiable list of tiers ordered lesser to greater
     */
    public static List<Tier> getSortedTiers()
    {
        return sortedTiersUnmodifiable;
    }

    /**
     * Returns the tag associated with a tier, if any.
     * @param tier The tier to look up
     * @return The tag, or null if none registered
     */
    public static TagKey<Block> getExceptionsFromTier(Tier tier)
    {
        return tierTagMap.get(tier);
    }

    /**
     * Returns the tier associated with a name, if registered into the sorting system.
     * @param name The name to look up
     * @return The tier, or null if not registered
     */
    @Nullable
    public static Tier byName(ResourceLocation name)
    {
        return tiers.get(name);
    }

    /**
     * Returns the name associated with a tier, if the tier is registered into the sorting system.
     * @param tier The tier to look up
     * @return The name for the tier, or null if not registered
     */
    @Nullable
    public static ResourceLocation getName(Tier tier)
    {
        return tiers.inverse().get(tier);
    }

    /**
     * Queries if a tier should be evaluated using the sorting system, by calling isCorrectTierForDrops
     * @param tier The tier to query
     * @return True if isCorrectTierForDrops should be called for the tier
     */
    public static boolean isTierSorted(Tier tier)
    {
        return getName(tier) != null;
    }

    /**
     * Queries if a tier is high enough to be able to get drops for the given blockstate.
     * @param tier The tier to look up
     * @param state The state to test against
     * @return True if the tier is good enough
     */
    public static boolean isCorrectTierForDrops(Tier tier, BlockState state) {
        // Resolve secondary tier -> primary tier
        Tier effectiveTier = tier;
        if (!isTierSorted(effectiveTier)) {
            Tier associated = getAssociatedTierFromSecondary(effectiveTier);
            if (associated != null) {
                effectiveTier = associated;
            } else {
                return isCorrectTierVanilla(tier, state);
            }
        }

        // Exceptions -> hard fail
        TagKey<Block> exceptionTag = getExceptionsFromTier(effectiveTier);
        if (exceptionTag != null && state.is(exceptionTag)) {
            return false;
        }

        // Fetch tiers
        List<Tier> sorted = getSortedTiers();

        int tierIndex = sorted.indexOf(effectiveTier);
        if (tierIndex == -1) {
            return isCorrectTierVanilla(tier, state);
        }

        // Look at all tier above the current one
        for (int i = tierIndex + 1; i < sorted.size(); i++) {
            Tier stronger = sorted.get(i);
            TagKey<Block> tag = stronger.getTag();

            if (tag != null && state.is(tag)) {
                return false;
            }
        }

        return true;
    }


    /**
     * Helper to query all tiers that are lower than the given tier
     * @param tier The tier
     * @return All the lower tiers
     */
    public static List<Tier> getTiersLowerThan(Tier tier)
    {
        if (!isTierSorted(tier)) return List.of();
        return sortedTiers.stream().takeWhile(t -> t != tier).toList();
    }

    /**
     * Fallback for when a tier isn't in the registry, copy of the logic in {@link DiggerItem#isCorrectToolForDrops}
     */
    private static boolean isCorrectTierVanilla(Tier tier, BlockState state)
    {
        int i = tier.getLevel();
        if (i < 3 && state.is(BlockTags.NEEDS_DIAMOND_TOOL))
        {
            return false;
        }
        else if (i < 2 && state.is(BlockTags.NEEDS_IRON_TOOL))
        {
            return false;
        }
        else if (i < 1 && state.is(BlockTags.NEEDS_STONE_TOOL))
        {
            return false;
        }
        return true;
    }

    private static void processTier(Tier tier, ResourceLocation name, List<Object> afters, List<Object> befores)
    {
        tiers.put(name, tier);
        for(Object after : afters)
        {
            ResourceLocation other = getTierName(after);
            edges.put(other, name);
        }
        for(Object before : befores)
        {
            ResourceLocation other = getTierName(before);
            edges.put(name, other);
        }
    }


    private static ResourceLocation getTierName(Object entry)
    {
        if (entry instanceof String s)
            return new ResourceLocation(s);
        if (entry instanceof ResourceLocation rl)
            return rl;
        if (entry instanceof Tier t) {
            ResourceLocation name = getName(t);
            if (name == null) { throw new IllegalStateException("Tier " + t + " not yet registered; cannot use as dependency."); }
            return name;
        }
        throw new IllegalStateException("Invalid object type passed into the tier dependencies " + entry.getClass());
    }

    private static boolean hasCustomTiers = false;
    private static final BiMap<ResourceLocation, Tier> tiers = HashBiMap.create();
    private static final Multimap<ResourceLocation, ResourceLocation> edges = HashMultimap.create();

    private static final List<Tier> sortedTiers = new ArrayList<>();
    private static final List<Tier> sortedTiersUnmodifiable = Collections.unmodifiableList(sortedTiers);

    private static final ResourceLocation CHANNEL_NAME = new ResourceLocation("orevolution:tier_sorting");
    private static final String PROTOCOL_VERSION = "1.0";
    private static final SimpleChannel SYNC_CHANNEL = NetworkRegistry.newSimpleChannel(
            CHANNEL_NAME, () -> "1.0",
            versionFromServer -> PROTOCOL_VERSION.equals(versionFromServer) || (allowVanilla() && NetworkRegistry.ACCEPTVANILLA.equals(versionFromServer)),
            versionFromClient -> PROTOCOL_VERSION.equals(versionFromClient) || (allowVanilla() && NetworkRegistry.ACCEPTVANILLA.equals(versionFromClient))
    );

    static boolean allowVanilla() {
        return !hasCustomTiers || tiers.size() <= 6;
    }

    /*package private*/
    static void init()
    {
        SYNC_CHANNEL.registerMessage(0, TierProgressRegistry.SyncPacket.class, TierProgressRegistry.SyncPacket::encode, TierProgressRegistry::receive, TierProgressRegistry::handle, Optional.of(NetworkDirection.PLAY_TO_CLIENT));
        MinecraftForge.EVENT_BUS.addListener(TierProgressRegistry::playerLoggedIn);
        if (FMLEnvironment.dist == Dist.CLIENT) TierProgressRegistry.ClientEvents.init();
    }

    /*package private*/
    static PreparableReloadListener getReloadListener()
    {
        return new SimplePreparableReloadListener<JsonObject>()
        {
            final Gson gson = (new GsonBuilder()).create();

            @NotNull
            @Override
            protected JsonObject prepare(@NotNull ResourceManager resourceManager, ProfilerFiller p)
            {
                Optional<Resource> res = resourceManager.getResource(ITEM_TIER_ORDERING_JSON);
                if (res.isEmpty())
                    return new JsonObject();

                try (Reader reader = res.get().openAsReader())
                {
                    return gson.fromJson(reader, JsonObject.class);
                }
                catch (IOException e)
                {
                    LOGGER.error("Could not read Tier sorting file " + ITEM_TIER_ORDERING_JSON, e);
                    return new JsonObject();
                }
            }

            @Override
            protected void apply(@NotNull JsonObject data, @NotNull ResourceManager resourceManager, ProfilerFiller p)
            {
                try
                {
                    if (data.size() > 0)
                    {
                        JsonArray order = GsonHelper.getAsJsonArray(data, "order");
                        List<Tier> customOrder = new ArrayList<>();
                        for (JsonElement entry : order)
                        {
                            ResourceLocation id = new ResourceLocation(entry.getAsString());
                            Tier tier = byName(id);
                            if (tier == null) throw new IllegalStateException("Tier not found with name " + id);
                            customOrder.add(tier);
                        }

                        List<Tier> missingTiers = tiers.values().stream().filter(tier -> !customOrder.contains(tier)).toList();
                        if (missingTiers.size() > 0)
                            throw new IllegalStateException("Tiers missing from the ordered list: " + missingTiers.stream().map(tier -> Objects.toString(TierProgressRegistry.getName(tier))).collect(Collectors.joining(", ")));

                        setTierOrder(customOrder);
                        return;
                    }
                }
                catch(Exception e)
                {
                    LOGGER.error("Error parsing Tier sorting file " + ITEM_TIER_ORDERING_JSON, e);
                }

                recalculateItemTiers();
            }
        };
    }

    @SuppressWarnings("UnstableApiUsage")
    private static void recalculateItemTiers()
    {
        final MutableGraph<Tier> graph = GraphBuilder.directed().nodeOrder(ElementOrder.<Tier>insertion()).build();

        for(Tier tier : tiers.values())
        {
            graph.addNode(tier);
        }
        edges.forEach((key, value) -> {
            if (tiers.containsKey(key) && tiers.containsKey(value))
                graph.putEdge(tiers.get(key), tiers.get(value));
        });
        List<Tier> tierList = TopologicalSort.topologicalSort(graph, null);

        setTierOrder(tierList);
    }

    private static void setTierOrder(List<Tier> tierList)
    {
        runInServerThreadIfPossible(hasServer -> {
            sortedTiers.clear();
            sortedTiers.addAll(tierList);
            if(hasServer) syncToAll();
        });
    }

    private static void runInServerThreadIfPossible(BooleanConsumer runnable)
    {
        MinecraftServer server = ServerLifecycleHooks.getCurrentServer();
        if (server != null) server.execute(() -> runnable.accept(true));
        else runnable.accept(false);
    }

    private static void syncToAll()
    {
        for(ServerPlayer serverPlayer : ServerLifecycleHooks.getCurrentServer().getPlayerList().getPlayers())
        {
            syncToPlayer(serverPlayer);
        }
    }

    private static void playerLoggedIn(PlayerEvent.PlayerLoggedInEvent event)
    {
        if (event.getEntity() instanceof ServerPlayer serverPlayer)
        {
            syncToPlayer(serverPlayer);
        }
    }

    private static void syncToPlayer(ServerPlayer serverPlayer)
    {
        if (SYNC_CHANNEL.isRemotePresent(serverPlayer.connection.connection) && !serverPlayer.connection.connection.isMemoryConnection())
        {
            SYNC_CHANNEL.send(PacketDistributor.PLAYER.with(() -> serverPlayer), new TierProgressRegistry.SyncPacket(sortedTiers.stream().map(TierProgressRegistry::getName).toList()));
        }
    }

    private static TierProgressRegistry.SyncPacket receive(FriendlyByteBuf buffer)
    {
        int count = buffer.readVarInt();
        List<ResourceLocation> list = new ArrayList<>();
        for(int i=0;i<count;i++)
            list.add(buffer.readResourceLocation());
        return new TierProgressRegistry.SyncPacket(list);
    }

    private static void handle(TierProgressRegistry.SyncPacket packet, Supplier<NetworkEvent.Context> context)
    {
        setTierOrder(packet.tiers.stream().map(TierProgressRegistry::byName).toList());
        context.get().setPacketHandled(true);
    }

    private record SyncPacket(List<ResourceLocation> tiers)
    {
        private void encode(FriendlyByteBuf buffer)
        {
            buffer.writeVarInt(tiers.size());
            for(ResourceLocation loc : tiers)
                buffer.writeResourceLocation(loc);
        }
    }

    private static class ClientEvents
    {
        public static void init()
        {
            MinecraftForge.EVENT_BUS.addListener(TierProgressRegistry.ClientEvents::clientLogInToServer);
        }

        private static void clientLogInToServer(ClientPlayerNetworkEvent.LoggingIn event)
        {
            if (event.getConnection() == null || !event.getConnection().isMemoryConnection())
                recalculateItemTiers();
        }
    }
}
