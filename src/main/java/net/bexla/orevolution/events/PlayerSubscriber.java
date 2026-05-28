package net.bexla.orevolution.events;

import net.bexla.orevolution.OrevolutionConfig;
import net.bexla.orevolution.content.data.OrevolutionArmorTiers;
import net.bexla.orevolution.content.data.OrevolutionToolTiers;
import net.bexla.orevolution.content.types.TierProgressRegistry;
import net.bexla.orevolution.content.types.ToolPowerRegistry;
import net.bexla.orevolution.content.types.interfaces.IToolPower;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.Tag;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.Tags;
import net.minecraftforge.event.entity.living.LivingDropsEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.Iterator;

@Mod.EventBusSubscriber
public class PlayerSubscriber {
    private static final String SOULBOUND_ITEMS = "SoulboundItems";

    @SubscribeEvent
    public static void onDrops(LivingDropsEvent event) {
        if (!(event.getEntity() instanceof ServerPlayer player)) return;

        ListTag stored = new ListTag();

        Iterator<ItemEntity> iterator = event.getDrops().iterator();

        while (iterator.hasNext()) {
            ItemEntity itemEntity = iterator.next();
            ItemStack stack = itemEntity.getItem();

            if (isSoulbound(stack)) {

                CompoundTag itemTag = new CompoundTag();
                stack.save(itemTag);

                stored.add(itemTag);

                iterator.remove();
            }
        }

        if (!stored.isEmpty()) {
            player.getPersistentData().put(SOULBOUND_ITEMS, stored);
        }
    }

    @SubscribeEvent
    public static void onClone(PlayerEvent.Clone event) {
        if (!event.isWasDeath()) return;

        CompoundTag oldData = event.getOriginal().getPersistentData();

        if (oldData.contains(SOULBOUND_ITEMS)) {
            event.getEntity().getPersistentData().put(
                    SOULBOUND_ITEMS,
                    oldData.getList(SOULBOUND_ITEMS, Tag.TAG_COMPOUND)
            );
        }
    }

    @SubscribeEvent
    public static void onRespawn(PlayerEvent.PlayerRespawnEvent event) {
        ServerPlayer player = (ServerPlayer) event.getEntity();

        CompoundTag data = player.getPersistentData();

        if (!data.contains(SOULBOUND_ITEMS)) return;

        ListTag list = data.getList(SOULBOUND_ITEMS, Tag.TAG_COMPOUND);

        for (Tag tag : list) {
            ItemStack stack = ItemStack.of((CompoundTag) tag);

            if (!player.addItem(stack)) {
                player.drop(stack, false);
            }
        }

        data.remove(SOULBOUND_ITEMS);
    }

    private static boolean isSoulbound(ItemStack stack) {
        return (stack.getItem() instanceof TieredItem tool && tool.getTier() == OrevolutionToolTiers.AETHERSTEEL)
                || (stack.getItem() instanceof ArmorItem armor && armor.getMaterial() == OrevolutionArmorTiers.AETHERSTEEL);
    }

    private static final Component CANT_HARVEST_ORE = Component.translatable("actionbar.orevolution.cant_harvest_ore");

    @SubscribeEvent
    public static void onHarvestCheck(PlayerEvent.HarvestCheck event) {
        Player player = event.getEntity();
        if (player.isCreative() || player.isSpectator()) return;

        if (!(player.getMainHandItem().getItem() instanceof TieredItem tiered)) return;

        BlockState state = event.getTargetBlock();
        Tier tier = tiered.getTier();
        boolean isCorrectTier = TierProgressRegistry.isCorrectTierForDrops(tier, state);

        showCantHarvestWarning(player, isCorrectTier, state, tiered);

        if (OrevolutionConfig.COMMON.modProgression.get()) {
            event.setCanHarvest(isCorrectTier);
        }
    }

    @SubscribeEvent
    public static void onBreakSpeed(PlayerEvent.BreakSpeed event) {
        Player player = event.getEntity();
        if (player.isCreative() || player.isSpectator()) {
            return;
        }

        ItemStack stack = player.getMainHandItem();
        if (!(stack.getItem() instanceof TieredItem tiered)) {
            return;
        }

        BlockState state = event.getState();
        Tier tier = tiered.getTier();
        boolean isCorrectTier = TierProgressRegistry.isCorrectTierForDrops(tier, state);

        showCantHarvestWarning(player, isCorrectTier, state, tiered);

        final float originalSpeed = event.getOriginalSpeed();
        float newSpeed = originalSpeed;

        if (tiered instanceof SwordItem && OrevolutionConfig.COMMON.weaponsPowers.get()) {
            IToolPower power = ToolPowerRegistry.getWeaponPower(tier);
            newSpeed = power.setDestroySpeed(stack, state, originalSpeed);
        } else if (tiered instanceof DiggerItem && OrevolutionConfig.COMMON.toolsPowers.get()) {
            IToolPower power = ToolPowerRegistry.getToolPower(tier);
            newSpeed = power.setDestroySpeed(stack, state, originalSpeed);
        }

        if (OrevolutionConfig.COMMON.safeOreBreaking.get() && !TierProgressRegistry.isCorrectTierForDrops(tier, state) && state.is(Tags.Blocks.ORES)) {
            newSpeed = 0.0F;
        }

        if (newSpeed != originalSpeed) {
            event.setNewSpeed(newSpeed);
        }
    }

    private static void showCantHarvestWarning(Player player, boolean isCorrectTier, BlockState state, Item item) {
        if (OrevolutionConfig.CLIENT.warnBreak.get() && !isCorrectTier) {
            if (isToolCorrectForBlock(item, state)) {
                player.displayClientMessage(CANT_HARVEST_ORE, true);
            }
        }
    }

    private static boolean isToolCorrectForBlock(Item item, BlockState state) {
        return (state.is(BlockTags.MINEABLE_WITH_PICKAXE) && item instanceof PickaxeItem)
                || (state.is(BlockTags.MINEABLE_WITH_SHOVEL) && item instanceof ShovelItem)
                || (state.is(BlockTags.MINEABLE_WITH_AXE) && item instanceof AxeItem)
                || (state.is(BlockTags.MINEABLE_WITH_HOE) && item instanceof HoeItem)
                || (state.is(BlockTags.SWORD_EFFICIENT) && item instanceof SwordItem);
    }
}
