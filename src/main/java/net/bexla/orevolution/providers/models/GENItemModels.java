package net.bexla.orevolution.providers.models;

import net.bexla.orevolution.Orevolution;
import net.bexla.orevolution.content.types.base.providers.ItemModelProvider;
import net.bexla.orevolution.init.RegBlocks;
import net.bexla.orevolution.init.RegItems;
import net.minecraft.data.PackOutput;
import net.minecraft.world.item.Item;
import net.minecraftforge.client.model.generators.ItemModelBuilder;
import net.minecraftforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.NotNull;

import java.util.function.Supplier;

public class GENItemModels extends ItemModelProvider {
    public GENItemModels(PackOutput output, ExistingFileHelper helper) {
        super(output, helper);
    }

    @Override
    public @NotNull String getName() {
        return Orevolution.MODID + " Item Models";
    }

    private ItemModelBuilder ingredient(Supplier<? extends Item> item) {
        return normalItem(item, "ingredient");
    }

    private ItemModelBuilder consumable(Supplier<? extends Item> item) {
        return normalItem(item, "consumable");
    }

    private ItemModelBuilder armor(Supplier<? extends Item> item) {
        return normalItem(item, "armor");
    }

    @Override
    protected void registerModels() {
        ingredient(RegItems.RAW_TIN);
        ingredient(RegItems.RAW_PLATINUM);
        ingredient(RegItems.RAW_TUNGSTEN);
        ingredient(RegItems.AETHERSTEEL_CHUNK);

        ingredient(RegItems.TIN_INGOT);
        ingredient(RegItems.PLATINUM_INGOT);
        ingredient(RegItems.TUNGSTEN_INGOT);
        ingredient(RegItems.AETHERSTEEL_INGOT);

        ingredient(RegItems.BRONZE_ALLOY);
        ingredient(RegItems.STEEL_ALLOY);
        ingredient(RegItems.TUMBAGA_ALLOY);

        ingredient(RegItems.TIN_NUGGET);
        ingredient(RegItems.PLATINUM_NUGGET);
        ingredient(RegItems.TUNGSTEN_NUGGET);
        ingredient(RegItems.VERDITE_NUGGET);
        ingredient(RegItems.LIVINGSTONE_SHARD);

        ingredient(RegItems.AETHERSTEEL_TEMPLATE);
        ingredient(RegItems.REINFORCED_TEMPLATE);
        ingredient(RegItems.BASIC_TEMPLATE);

        consumable(RegItems.VERDITE_APPLE);

        toolItem(RegItems.TIN_SWORD);
        toolItem(RegItems.TIN_PICKAXE);
        toolItem(RegItems.TIN_AXE);
        toolItem(RegItems.TIN_SHOVEL);
        toolItem(RegItems.TIN_HOE);

        armor(RegItems.PLATINUM_HELMET);
        armor(RegItems.PLATINUM_CHESTPLATE);
        armor(RegItems.PLATINUM_LEGGINGS);
        armor(RegItems.PLATINUM_BOOTS);
        toolItem(RegItems.PLATINUM_SWORD);
        toolItem(RegItems.PLATINUM_PICKAXE);
        toolItem(RegItems.PLATINUM_AXE);
        toolItem(RegItems.PLATINUM_SHOVEL);
        toolItem(RegItems.PLATINUM_HOE);

        armor(RegItems.REINFORCED_NETHERITE_HELMET);
        armor(RegItems.REINFORCED_NETHERITE_CHESTPLATE);
        armor(RegItems.REINFORCED_NETHERITE_LEGGINGS);
        armor(RegItems.REINFORCED_NETHERITE_BOOTS);

        armor(RegItems.AETHERSTEEL_HELMET);
        armor(RegItems.AETHERSTEEL_CHESTPLATE);
        armor(RegItems.AETHERSTEEL_LEGGINGS);
        armor(RegItems.AETHERSTEEL_BOOTS);
        toolItem(RegItems.AETHERSTEEL_SWORD);
        toolItem(RegItems.AETHERSTEEL_PICKAXE);
        toolItem(RegItems.AETHERSTEEL_AXE);
        toolItem(RegItems.AETHERSTEEL_SHOVEL);
        toolItem(RegItems.AETHERSTEEL_HOE);

        armor(RegItems.LIVINGSTONE_HELMET);
        armor(RegItems.LIVINGSTONE_CHESTPLATE);
        armor(RegItems.LIVINGSTONE_LEGGINGS);
        armor(RegItems.LIVINGSTONE_BOOTS);

        toolItem(RegItems.LIVINGSTONE_SWORD);
        toolItem(RegItems.LIVINGSTONE_PICKAXE);
        toolItem(RegItems.LIVINGSTONE_AXE);
        toolItem(RegItems.LIVINGSTONE_SHOVEL);
        toolItem(RegItems.LIVINGSTONE_HOE);

        armor(RegItems.VERDITE_HELMET);
        armor(RegItems.VERDITE_CHESTPLATE);
        armor(RegItems.VERDITE_LEGGINGS);
        armor(RegItems.VERDITE_BOOTS);

        toolItem(RegItems.VERDITE_SWORD);
        toolItem(RegItems.VERDITE_PICKAXE);
        toolItem(RegItems.VERDITE_AXE);
        toolItem(RegItems.VERDITE_SHOVEL);
        toolItem(RegItems.VERDITE_HOE);

        armor(RegItems.BRONZE_CROWN);
        armor(RegItems.BRONZE_CROWN_EMERALD);
        armor(RegItems.BRONZE_CROWN_DIAMOND);
        armor(RegItems.BRONZE_CROWN_LAPIS);
        armor(RegItems.BRONZE_CROWN_REDSTONE);

        toolItem(RegItems.TUMBAGA_SWORD);
        toolItem(RegItems.TUMBAGA_PICKAXE);
        toolItem(RegItems.TUMBAGA_AXE);
        toolItem(RegItems.TUMBAGA_SHOVEL);
        toolItem(RegItems.TUMBAGA_HOE);

        // shieldItem(RegItems.TungstenShield);

        block(RegBlocks.TIN_ORE);
        block(RegBlocks.DEEPSLATE_TIN_ORE);
        block(RegBlocks.PLATINUM_ORE);
        block(RegBlocks.DEEPSLATE_PLATINUM_ORE);
        block(RegBlocks.NETHER_TUNGSTEN_ORE);
        block(RegBlocks.NETHER_XP_ORE);
        block(RegBlocks.END_XP_ORE);
        block(RegBlocks.PRIMITIVE_AETHERROCK);
        block(RegBlocks.AETHERROCK);

        block(RegBlocks.POLISHED_AETHERROCK);
        block(RegBlocks.POLISHED_TUNGSTEN);
        block(RegBlocks.AETHERROCK_BRICKS);
        block(RegBlocks.AETHERROCK_TILES);
        block(RegBlocks.BRONZE_TILES);
        block(RegBlocks.CUT_TUNGSTEN_BLOCK);

        block(RegBlocks.REINFORCED_NETHERITE_BLOCK);
        block(RegBlocks.AETHERSTEEL_BLOCK);
        block(RegBlocks.TIN_BLOCK);
        block(RegBlocks.PLATINUM_BLOCK);
        block(RegBlocks.TUNGSTEN_BLOCK);
        block(RegBlocks.BRONZE_BLOCK);
        block(RegBlocks.STEEL_BLOCK);
        block(RegBlocks.TUMBAGA_BLOCK);
        block(RegBlocks.LIVINGSTONE_BLOCK);

        block(RegBlocks.RAW_TIN_BLOCK);
        block(RegBlocks.RAW_PLATINUM_BLOCK);
        block(RegBlocks.RAW_TUNGSTEN_BLOCK);
    }
}
