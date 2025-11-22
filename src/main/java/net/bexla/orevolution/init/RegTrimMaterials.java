package net.bexla.orevolution.init;

import net.bexla.orevolution.content.data.utility.OrevolutionUtils;
import net.minecraft.Util;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.Style;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ArmorMaterials;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.armortrim.TrimMaterial;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.Map;

public class RegTrimMaterials {
    public static final ResourceKey<TrimMaterial> TIN = createKey("tin");
    public static final ResourceKey<TrimMaterial> PLATINUM = createKey("platinum");
    public static final ResourceKey<TrimMaterial> TUNGSTEN = createKey("tungsten");

    public static void bootstrap(BootstapContext<TrimMaterial> context) {
        register(context, TIN, RegItems.TIN_INGOT.get(), Style.EMPTY.withColor(1159283), Map.of());
        register(context, PLATINUM, RegItems.PLATINUM_INGOT.get(), Style.EMPTY.withColor(163195214), Map.of());
        register(context, TUNGSTEN, RegItems.TUNGSTEN_INGOT.get(), Style.EMPTY.withColor(162219105), Map.of());
    }

    private static ResourceKey<TrimMaterial> createKey(String name) {
        return ResourceKey.create(Registries.TRIM_MATERIAL, OrevolutionUtils.modLocat(name));
    }

    private static void register(BootstapContext<TrimMaterial> context, ResourceKey<TrimMaterial> key, Item item, Style style, Map<ArmorMaterials, String> overrides) {
        ResourceLocation location = key.location();
        context.register(key, new TrimMaterial(location.getNamespace() + "_" + location.getPath(), ForgeRegistries.ITEMS.getHolder(item).get(), -1.0F, overrides, Component.translatable(Util.makeDescriptionId("trim_material", location)).withStyle(style)));
    }
}