package net.bexla.orevolution;

import com.mojang.logging.LogUtils;
import net.bexla.orevolution.content.init.RegItems;
import net.bexla.orevolution.providers.BlockStateModels;
import net.bexla.orevolution.providers.ItemModels;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.ItemLike;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.common.util.MutableHashedLinkedMap;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;

import com.teamabnormals.blueprint.core.util.registry.RegistryHelper;

import java.util.concurrent.CompletableFuture;
import java.util.function.Supplier;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(Orevolution.MODID)
public class Orevolution
{

    public static final String MODID = "orevolution";
    private static final Logger LOGGER = LogUtils.getLogger();

    public static final RegistryHelper REGISTRY_HELPER = new RegistryHelper(MODID);

    public Orevolution(FMLJavaModLoadingContext context)
    {
        IEventBus modEventBus = context.getModEventBus();

        REGISTRY_HELPER.register(modEventBus);

        // Register ourselves for server and other game events we are interested in
        MinecraftForge.EVENT_BUS.register(this);

        // Register the item to a creative tab
        modEventBus.addListener(this::addCreative);
        modEventBus.addListener(this::gatherData);

        // Register our mod's ForgeConfigSpec so that Forge can create and load the config file for us
        context.registerConfig(ModConfig.Type.COMMON, Config.SPEC);
    }

    public void gatherData(GatherDataEvent event) {
        DataGenerator generator = event.getGenerator();
        PackOutput output = generator.getPackOutput();
        CompletableFuture<HolderLookup.Provider> future = event.getLookupProvider();
        ExistingFileHelper helper = event.getExistingFileHelper();
        boolean client = event.includeClient();
        boolean server = event.includeServer();

        generator.addProvider(client, new BlockStateModels(output, helper));
        generator.addProvider(client, new ItemModels(output, helper));
    }

    @SubscribeEvent
    public void addCreative(BuildCreativeModeTabContentsEvent event) {
        ResourceKey<CreativeModeTab> tab = event.getTabKey();
        MutableHashedLinkedMap<ItemStack, CreativeModeTab.TabVisibility> entries = event.getEntries();

        putAfter(entries, Items.STONE_SWORD, RegItems.TIN_SWORD);
        putBefore(entries, Items.DIAMOND_SWORD, RegItems.PLATINUM_SWORD);
        putAfter(entries, Items.NETHERITE_SWORD, RegItems.AETHERSTEEL_SWORD);

        putAfter(entries, Items.STONE_HOE, RegItems.TIN_SHOVEL);
        putAfter(entries, RegItems.TIN_SHOVEL.get(), RegItems.TIN_PICKAXE);
        putAfter(entries, RegItems.TIN_PICKAXE.get(), RegItems.TIN_AXE);
        putAfter(entries, RegItems.TIN_AXE.get(), RegItems.TIN_HOE);

        putBefore(entries, Items.DIAMOND_SHOVEL, RegItems.PLATINUM_SHOVEL);
        putAfter(entries, RegItems.PLATINUM_SHOVEL.get(), RegItems.PLATINUM_PICKAXE);
        putAfter(entries, RegItems.PLATINUM_PICKAXE.get(), RegItems.PLATINUM_AXE);
        putAfter(entries, RegItems.PLATINUM_AXE.get(), RegItems.PLATINUM_HOE);

        putAfter(entries, Items.NETHERITE_HOE, RegItems.AETHERSTEEL_SHOVEL);
        putAfter(entries, RegItems.AETHERSTEEL_SHOVEL.get(), RegItems.AETHERSTEEL_PICKAXE);
        putAfter(entries, RegItems.AETHERSTEEL_PICKAXE.get(), RegItems.AETHERSTEEL_AXE);
        putAfter(entries, RegItems.AETHERSTEEL_AXE.get(), RegItems.AETHERSTEEL_HOE);

        putBefore(entries, Items.IRON_INGOT, RegItems.TIN_INGOT);
        putAfter(entries, Items.IRON_INGOT, RegItems.PLATINUM_INGOT);
        putAfter(entries, RegItems.PLATINUM_INGOT.get(), RegItems.TUNGSTEN_INGOT);
        putAfter(entries, RegItems.TUNGSTEN_INGOT.get(), RegItems.BRONZE_ALLOY);
        putAfter(entries, RegItems.BRONZE_ALLOY.get(), RegItems.TUMBAGA_ALLOY);
        putAfter(entries, RegItems.TUMBAGA_ALLOY.get(), RegItems.STEEL_ALLOY);

        putBefore(entries, Items.RAW_IRON, RegItems.RAW_TIN);
        putAfter(entries, Items.RAW_IRON, RegItems.RAW_PLATINUM);
        putAfter(entries, Items.DIAMOND, RegItems.RAW_TUNGSTEN);

        /*
        if (farmersDelightLoaded()) {
            putAfter(entries, ModItems.NETHERITE_KNIFE.get(), OItems.ELECTRUM_KNIFE);
        }
        if (shieldExpansionLoaded()) {
            putAfter(entries, ItemsInit.NETHERITE_SHIELD.get(), OItems.ELECTRUM_SHIELD);
        }
        */
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

    // You can use EventBusSubscriber to automatically register all static methods in the class annotated with @SubscribeEvent
    @Mod.EventBusSubscriber(modid = MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents
    {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event)
        {
        }
    }
}
