package net.bexla.orevolution;

import com.mojang.logging.LogUtils;
import com.mojang.serialization.Codec;
import com.teamabnormals.blueprint.core.util.registry.RegistryHelper;
import net.bexla.orevolution.compatibility.ModCompat;
import net.bexla.orevolution.compatibility.farmersdelight.RegItemsFD;
import net.bexla.orevolution.compatibility.spelunkery.RegBlocksSK;
import net.bexla.orevolution.compatibility.spelunkery.RegItemsSK;
import net.bexla.orevolution.content.data.AddLootModifier;
import net.bexla.orevolution.content.data.DataRegistries;
import net.bexla.orevolution.datagens.GENLootDrops;
import net.bexla.orevolution.datagens.GENRecipes;
import net.bexla.orevolution.datagens.OrevolutionSpriteSourceProvider;
import net.bexla.orevolution.datagens.langs.GENLangENUS;
import net.bexla.orevolution.datagens.langs.GENLangESAR;
import net.bexla.orevolution.datagens.models.GENBlockStateModels;
import net.bexla.orevolution.datagens.models.GENItemModels;
import net.bexla.orevolution.datagens.tags.GENBlockTags;
import net.bexla.orevolution.datagens.tags.GENItemTags;
import net.bexla.orevolution.init.*;
import net.minecraft.DetectedVersion;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraft.data.metadata.PackMetadataGenerator;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.PackType;
import net.minecraft.server.packs.metadata.pack.PackMetadataSection;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.data.DatapackBuiltinEntriesProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.common.loot.IGlobalLootModifier;
import net.minecraftforge.common.util.MutableHashedLinkedMap;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import org.slf4j.Logger;
import vectorwing.farmersdelight.common.registry.ModItems;

import java.util.Arrays;
import java.util.concurrent.CompletableFuture;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collectors;

@Mod(Orevolution.MODID)
public class Orevolution
{

    public static final String MODID = "orevolution";
    private static final Logger LOGGER = LogUtils.getLogger();

    public static final RegistryHelper REGISTRY_HELPER = new RegistryHelper(MODID);

    private static final DeferredRegister<Codec<? extends IGlobalLootModifier>> LOOT_MODIFIERS = DeferredRegister.create(ForgeRegistries.Keys.GLOBAL_LOOT_MODIFIER_SERIALIZERS, Orevolution.MODID);

    public Orevolution(FMLJavaModLoadingContext context)
    {
        IEventBus modEventBus = context.getModEventBus();

        OrevolutionConfig.register();

        RegMobEffects.EFFECTS.register(modEventBus);

        MinecraftForge.EVENT_BUS.register(this);

        LOOT_MODIFIERS.register("loot_item", () -> AddLootModifier.CODEC);

        REGISTRY_HELPER.register(modEventBus);

        RegFeatures.FEATURES.register(modEventBus);

        RegMisc.RegisterAll();

        LOOT_MODIFIERS.register(modEventBus);

        if(ModCompat.isModLoaded(ModCompat.FARMERSDELIGHT)) RegItemsFD.register(modEventBus);

        if(ModCompat.isModLoaded(ModCompat.SPELUNKERY)) RegItemsSK.register(modEventBus);RegBlocksSK.register(modEventBus);

        modEventBus.addListener(this::gatherData);
        modEventBus.addListener(this::addCreative);
    }

    public void gatherData(GatherDataEvent event) {
        DataGenerator generator = event.getGenerator();
        PackOutput output = generator.getPackOutput();
        CompletableFuture<HolderLookup.Provider> future = event.getLookupProvider();
        ExistingFileHelper helper = event.getExistingFileHelper();
        boolean client = event.includeClient();
        boolean server = event.includeServer();

        var lang = new GENLangENUS(output);

        generator.addProvider(client, new GENBlockStateModels(output, helper));
        generator.addProvider(client, new GENItemModels(output, helper));
        GENBlockTags blockTags = new GENBlockTags(output, future, helper);
        generator.addProvider(server, blockTags);
        generator.addProvider(client, new GENItemTags(output, future, blockTags.contentsGetter(), helper));
        generator.addProvider(client, new OrevolutionSpriteSourceProvider(output, helper));
        generator.addProvider(client, new GENLootDrops(output));
        generator.addProvider(client, new GENRecipes(output));

        DatapackBuiltinEntriesProvider datapackProvider = new DataRegistries(output, future);
        CompletableFuture<HolderLookup.Provider> lookupProvider = datapackProvider.getRegistryProvider();
        generator.addProvider(server, datapackProvider);

        generator.addProvider(client, lang);
        generator.addProvider(client, new GENLangESAR(output));

        generator.addProvider(server, new PackMetadataGenerator(output).add(PackMetadataSection.TYPE, new PackMetadataSection(
                Component.literal("Orevolution resources"),
                DetectedVersion.BUILT_IN.getPackVersion(PackType.CLIENT_RESOURCES),
                Arrays.stream(PackType.values()).collect(Collectors.toMap(Function.identity(), DetectedVersion.BUILT_IN::getPackVersion))
        )));
    }

    @SubscribeEvent
    public void addCreative(BuildCreativeModeTabContentsEvent event) {
        ResourceKey<CreativeModeTab> tab = event.getTabKey();
        MutableHashedLinkedMap<ItemStack, CreativeModeTab.TabVisibility> entries = event.getEntries();

        if(tab == CreativeModeTabs.COMBAT) {
            putAfter(entries, Items.STONE_SWORD, RegItems.TIN_SWORD);
            putAfter(entries, RegItems.TIN_SWORD.get(), RegItems.LIVINGSTONE_SWORD);
            putAfter(entries, RegItems.LIVINGSTONE_SWORD.get(), RegItems.VERDITE_SWORD);
            putAfter(entries, Items.IRON_SWORD, RegItems.STEEL_SCYTHE);
            putBefore(entries, Items.DIAMOND_SWORD, RegItems.PLATINUM_SWORD);
            putAfter(entries, Items.NETHERITE_SWORD, RegItems.AETHERSTEEL_SWORD);

            putAfter(entries, Items.STONE_AXE, RegItems.TIN_AXE);
            putAfter(entries, RegItems.TIN_AXE.get(), RegItems.LIVINGSTONE_AXE);
            putAfter(entries, RegItems.LIVINGSTONE_AXE.get(), RegItems.VERDITE_AXE);
            putBefore(entries, Items.DIAMOND_AXE, RegItems.PLATINUM_AXE);
            putAfter(entries, Items.NETHERITE_AXE, RegItems.AETHERSTEEL_AXE);

            putAfter(entries, Items.CHAINMAIL_BOOTS, RegItems.BRONZE_CROWN);
            putAfter(entries, RegItems.BRONZE_CROWN.get(), RegItems.BRONZE_CROWN_EMERALD);
            putAfter(entries, RegItems.BRONZE_CROWN_EMERALD.get(), RegItems.BRONZE_CROWN_LAPIS);
            putAfter(entries, RegItems.BRONZE_CROWN_LAPIS.get(), RegItems.BRONZE_CROWN_REDSTONE);
            putAfter(entries, RegItems.BRONZE_CROWN_REDSTONE.get(), RegItems.BRONZE_CROWN_DIAMOND);

            putAfter(entries, Items.IRON_BOOTS, RegItems.PLATINUM_HELMET);
            putAfter(entries, RegItems.PLATINUM_HELMET.get(), RegItems.PLATINUM_CHESTPLATE);
            putAfter(entries, RegItems.PLATINUM_CHESTPLATE.get(), RegItems.PLATINUM_LEGGINGS);
            putAfter(entries, RegItems.PLATINUM_LEGGINGS.get(), RegItems.PLATINUM_BOOTS);

            putAfter(entries, Items.NETHERITE_BOOTS, RegItems.REINFORCED_NETHERITE_HELMET);
            putAfter(entries, RegItems.REINFORCED_NETHERITE_HELMET.get(), RegItems.REINFORCED_NETHERITE_CHESTPLATE);
            putAfter(entries, RegItems.REINFORCED_NETHERITE_CHESTPLATE.get(), RegItems.REINFORCED_NETHERITE_LEGGINGS);
            putAfter(entries, RegItems.REINFORCED_NETHERITE_LEGGINGS.get(), RegItems.REINFORCED_NETHERITE_BOOTS);

            putAfter(entries, RegItems.REINFORCED_NETHERITE_BOOTS.get(), RegItems.AETHERSTEEL_HELMET);
            putAfter(entries, RegItems.AETHERSTEEL_HELMET.get(), RegItems.AETHERSTEEL_CHESTPLATE);
            putAfter(entries, RegItems.AETHERSTEEL_CHESTPLATE.get(), RegItems.AETHERSTEEL_LEGGINGS);
            putAfter(entries, RegItems.AETHERSTEEL_LEGGINGS.get(), RegItems.AETHERSTEEL_BOOTS);
        }
        else if(tab == CreativeModeTabs.TOOLS_AND_UTILITIES) {
            putBefore(entries, Items.WOODEN_SHOVEL, RegItems.LIVINGSTONE_SHOVEL);
            putAfter(entries, RegItems.LIVINGSTONE_SHOVEL.get(), RegItems.LIVINGSTONE_PICKAXE);
            putAfter(entries, RegItems.LIVINGSTONE_PICKAXE.get(), RegItems.LIVINGSTONE_AXE);
            putAfter(entries, RegItems.LIVINGSTONE_AXE.get(), RegItems.LIVINGSTONE_HOE);

            putAfter(entries, RegItems.LIVINGSTONE_HOE.get(), RegItems.VERDITE_SHOVEL);
            putAfter(entries, RegItems.VERDITE_SHOVEL.get(), RegItems.VERDITE_PICKAXE);
            putAfter(entries, RegItems.VERDITE_PICKAXE.get(), RegItems.VERDITE_AXE);
            putAfter(entries, RegItems.VERDITE_AXE.get(), RegItems.VERDITE_HOE);

            putAfter(entries, Items.STONE_HOE, RegItems.TIN_SHOVEL);
            putAfter(entries, RegItems.TIN_SHOVEL.get(), RegItems.TIN_PICKAXE);
            putAfter(entries, RegItems.TIN_PICKAXE.get(), RegItems.TIN_AXE);
            putAfter(entries, RegItems.TIN_AXE.get(), RegItems.TIN_HOE);

            putAfter(entries, Items.IRON_HOE, RegItems.STEEL_DIGGER);
            putAfter(entries, RegItems.STEEL_DIGGER.get(), RegItems.STEEL_HAMMER);
            putAfter(entries, RegItems.STEEL_HAMMER.get(), RegItems.STEEL_SCYTHE);

            putBefore(entries, Items.DIAMOND_SHOVEL, RegItems.PLATINUM_SHOVEL);
            putAfter(entries, RegItems.PLATINUM_SHOVEL.get(), RegItems.PLATINUM_PICKAXE);
            putAfter(entries, RegItems.PLATINUM_PICKAXE.get(), RegItems.PLATINUM_AXE);
            putAfter(entries, RegItems.PLATINUM_AXE.get(), RegItems.PLATINUM_HOE);

            putAfter(entries, Items.NETHERITE_HOE, RegItems.AETHERSTEEL_SHOVEL);
            putAfter(entries, RegItems.AETHERSTEEL_SHOVEL.get(), RegItems.AETHERSTEEL_PICKAXE);
            putAfter(entries, RegItems.AETHERSTEEL_PICKAXE.get(), RegItems.AETHERSTEEL_AXE);
            putAfter(entries, RegItems.AETHERSTEEL_AXE.get(), RegItems.AETHERSTEEL_HOE);
        }
        else if(tab == CreativeModeTabs.INGREDIENTS) {
            putBefore(entries, RegItems.VERDITE_INGOT.get(), RegItems.LIVINGSTONE_SHARD);
            putBefore(entries, RegItems.TIN_INGOT.get(), RegItems.VERDITE_INGOT);
            putBefore(entries, Items.IRON_INGOT, RegItems.TIN_INGOT);
            putAfter(entries, Items.IRON_INGOT, RegItems.PLATINUM_INGOT);
            putAfter(entries, RegItems.PLATINUM_INGOT.get(), RegItems.TUNGSTEN_INGOT);
            putAfter(entries, RegItems.TUNGSTEN_INGOT.get(), RegItems.BRONZE_ALLOY);
            putAfter(entries, Items.NETHERITE_INGOT, RegItems.AETHERSTEEL_INGOT);
            putAfter(entries, RegItems.BRONZE_ALLOY.get(), RegItems.STEEL_ALLOY);

            putBefore(entries, Items.IRON_NUGGET, RegItems.TIN_NUGGET);
            putAfter(entries, Items.IRON_NUGGET, RegItems.PLATINUM_NUGGET);
            putAfter(entries, Items.GOLD_NUGGET, RegItems.TUNGSTEN_NUGGET);
            putBefore(entries, Items.GOLD_NUGGET, RegItems.VERDITE_NUGGET);

            putBefore(entries, Items.RAW_IRON, RegItems.RAW_TIN);
            putAfter(entries, Items.RAW_IRON, RegItems.RAW_PLATINUM);
            putAfter(entries, RegItems.RAW_PLATINUM.get(), RegItems.RAW_TUNGSTEN);
        }
        else if (tab == CreativeModeTabs.BUILDING_BLOCKS) {
            putBefore(entries, RegBlocks.VERDITE_BLOCK.get(), RegBlocks.LIVINGSTONE_BLOCK);
            putBefore(entries, RegBlocks.TIN_BLOCK.get(), RegBlocks.VERDITE_BLOCK);
            putBefore(entries, Items.IRON_BLOCK, RegBlocks.TIN_BLOCK);
            putAfter(entries, Items.IRON_BLOCK, RegBlocks.PLATINUM_BLOCK);
            putAfter(entries, RegBlocks.PLATINUM_BLOCK.get(), RegBlocks.TUNGSTEN_BLOCK);
            putAfter(entries, Items.NETHERITE_BLOCK, RegBlocks.AETHERSTEEL_BLOCK);
            putAfter(entries, RegBlocks.AETHERSTEEL_BLOCK.get(), RegBlocks.BRONZE_BLOCK);
            putAfter(entries, RegBlocks.BRONZE_BLOCK.get(), RegBlocks.BRONZE_TILES);
            putAfter(entries, RegBlocks.BRONZE_BLOCK.get(), RegBlocks.STEEL_BLOCK);

            putAfter(entries, Blocks.RED_NETHER_BRICK_WALL, RegBlocks.AETHERROCK);
            putAfter(entries, RegBlocks.AETHERROCK.get(), RegBlocks.POLISHED_AETHERROCK);
            putAfter(entries, RegBlocks.POLISHED_AETHERROCK.get(), RegBlocks.POLISHED_AETHERROCK_STAIR);
            putAfter(entries, RegBlocks.POLISHED_AETHERROCK_STAIR.get(), RegBlocks.POLISHED_AETHERROCK_SLAB);
            putAfter(entries, RegBlocks.POLISHED_AETHERROCK_SLAB.get(), RegBlocks.POLISHED_AETHERROCK_WALL);
            putAfter(entries, RegBlocks.POLISHED_AETHERROCK_WALL.get(), RegBlocks.AETHERROCK_BRICKS);
            putAfter(entries, RegBlocks.AETHERROCK_BRICKS.get(), RegBlocks.CRACKED_AETHERROCK_BRICKS);
            putAfter(entries, RegBlocks.CRACKED_AETHERROCK_BRICKS.get(), RegBlocks.AETHERROCK_TILES);
            putAfter(entries, RegBlocks.TUNGSTEN_BLOCK.get(), RegBlocks.POLISHED_TUNGSTEN);
            putAfter(entries, RegBlocks.POLISHED_TUNGSTEN.get(), RegBlocks.CUT_TUNGSTEN_BLOCK);
            putAfter(entries, RegBlocks.CUT_TUNGSTEN_BLOCK.get(), RegBlocks.CHISELED_TUNGSTEN_BLOCK);
            putAfter(entries, RegBlocks.CHISELED_TUNGSTEN_BLOCK.get(), RegBlocks.CHISELED_TUNGSTEN_BRICKS);
            putAfter(entries, RegBlocks.STEEL_BLOCK.get(), RegBlocks.CUT_STEEL_BLOCK);
            putAfter(entries, RegBlocks.CUT_STEEL_BLOCK.get(), RegBlocks.STEEL_PILLAR);
            putAfter(entries, RegBlocks.STEEL_PILLAR.get(), RegBlocks.STEEL_DOOR);
        }
        else if(tab == CreativeModeTabs.NATURAL_BLOCKS) {
            putBefore(entries, Items.IRON_ORE, RegBlocks.DEEPSLATE_TIN_ORE);
            putBefore(entries, RegBlocks.DEEPSLATE_TIN_ORE.get(), RegBlocks.TIN_ORE);
            putAfter(entries, Items.DEEPSLATE_IRON_ORE, RegBlocks.PLATINUM_ORE);
            putAfter(entries, RegBlocks.PLATINUM_ORE.get(), RegBlocks.DEEPSLATE_PLATINUM_ORE);
            putBefore(entries, Items.COAL_ORE, RegBlocks.NETHER_XP_ORE);
            putBefore(entries, RegBlocks.END_XP_ORE.get(), RegBlocks.NETHER_XP_ORE);
            putAfter(entries, Items.NETHER_QUARTZ_ORE, RegBlocks.NETHER_TUNGSTEN_ORE);
            putAfter(entries, Blocks.ANCIENT_DEBRIS, RegBlocks.PRIMITIVE_AETHERROCK);
            
            if(ModCompat.isModLoaded(ModCompat.SPELUNKERY)) {
                putAfter(entries, RegBlocks.DEEPSLATE_PLATINUM_ORE.get(), RegBlocksSK.PLATINUM_ORE_ANDESITE);
                putAfter(entries, RegBlocksSK.PLATINUM_ORE_ANDESITE.get(), RegBlocksSK.PLATINUM_ORE_GRANITE);
                putAfter(entries, RegBlocksSK.PLATINUM_ORE_GRANITE.get(), RegBlocksSK.PLATINUM_ORE_DIORITE);
                putAfter(entries, RegBlocksSK.PLATINUM_ORE_DIORITE.get(), RegBlocksSK.PLATINUM_ORE_TUFF);
                putAfter(entries, RegBlocks.DEEPSLATE_TIN_ORE.get(), RegBlocksSK.TIN_ORE_ANDESITE);
                putAfter(entries, RegBlocksSK.TIN_ORE_ANDESITE.get(), RegBlocksSK.TIN_ORE_GRANITE);
                putAfter(entries, RegBlocksSK.TIN_ORE_GRANITE.get(), RegBlocksSK.TIN_ORE_DIORITE);
                putAfter(entries, RegBlocksSK.TIN_ORE_DIORITE.get(), RegBlocksSK.TIN_ORE_TUFF);
            }

            putBefore(entries, Items.RAW_IRON_BLOCK, RegBlocks.RAW_TIN_BLOCK);
            putAfter(entries, Items.RAW_IRON_BLOCK, RegBlocks.RAW_PLATINUM_BLOCK);
            putAfter(entries, RegBlocks.RAW_PLATINUM_BLOCK.get(), RegBlocks.RAW_TUNGSTEN_BLOCK);
        }

        putBefore(entries, Items.NETHERITE_UPGRADE_SMITHING_TEMPLATE, RegItems.BASIC_TEMPLATE);
        putAfter(entries, Items.NETHERITE_UPGRADE_SMITHING_TEMPLATE, RegItems.REINFORCED_TEMPLATE);
        putAfter(entries, RegItems.REINFORCED_TEMPLATE.get(), RegItems.AETHERSTEEL_TEMPLATE);

        ResourceLocation FD_TAB = new ResourceLocation(ModCompat.FARMERSDELIGHT.getId(), ModCompat.FARMERSDELIGHT.getId());

        if (ModCompat.isModLoaded(ModCompat.FARMERSDELIGHT) && tab.location().equals(FD_TAB)) {
            putAfter(entries, ModItems.FLINT_KNIFE.get(), RegItemsFD.TIN_KNIFE);
            putAfter(entries, RegItemsFD.TIN_KNIFE.get(), RegItemsFD.LIVINGSTONE_KNIFE);
            putAfter(entries, RegItemsFD.LIVINGSTONE_KNIFE.get(), RegItemsFD.VERDITE_KNIFE);
            putAfter(entries, ModItems.IRON_KNIFE.get(), RegItemsFD.PLATINUM_KNIFE);
            putAfter(entries, ModItems.NETHERITE_KNIFE.get(), RegItemsFD.AETHERSTEEL_KNIFE);
        }
    }

    private static void putAfter(MutableHashedLinkedMap<ItemStack, CreativeModeTab.TabVisibility> entries, ItemLike after, Supplier<? extends ItemLike> supplier) {
        ItemLike key = supplier.get();
        if (!entries.contains(new ItemStack(after))) return;
        entries.putAfter(new ItemStack(after), new ItemStack(key), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
    }

    private static void putBefore(MutableHashedLinkedMap<ItemStack, CreativeModeTab.TabVisibility> entries, ItemLike before, Supplier<? extends ItemLike> supplier) {
        ItemLike key = supplier.get();
        if (!entries.contains(new ItemStack(before))) return;
        entries.putBefore(new ItemStack(before), new ItemStack(key), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
    }
}
