package net.bexla.orevolution.datagens.models;

import net.bexla.orevolution.Orevolution;
import net.bexla.orevolution.compatibility.ModCompat;
import net.bexla.orevolution.compatibility.farmersdelight.RegItemsFD;
import net.bexla.orevolution.compatibility.spelunkery.RegBlocksSK;
import net.bexla.orevolution.content.types.providers.ItemModelProvider;
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

    private ItemModelBuilder blockitem(Supplier<? extends Item> item) {
        return normalItem(item, "blockitem");
    }

    private ItemModelBuilder compat(String modid, Supplier<? extends Item> item) {
        return normalItem(item, "compat/" + modid);
    }
    
    private ItemModelBuilder compatBlock(String modid, Supplier<? extends Item> item) {
        return normalItem(item, "compat/" + modid);
    }

    @Override
    protected void registerModels() {
        blockitem(RegItems.DEAD_SEED);
        blockitem(RegItems.PETRIFIED_SEED);

        ingredient(RegItems.RAW_TIN);
        ingredient(RegItems.RAW_PLATINUM);
        ingredient(RegItems.RAW_TUNGSTEN);
        ingredient(RegItems.AETHERSTEEL_CHUNK);

        ingredient(RegItems.TIN_INGOT);
        ingredient(RegItems.PLATINUM_INGOT);
        ingredient(RegItems.TUNGSTEN_INGOT);
        ingredient(RegItems.AETHERSTEEL_INGOT);
        ingredient(RegItems.VERDITE_INGOT);

        ingredient(RegItems.BRONZE_ALLOY);
        ingredient(RegItems.STEEL_ALLOY);

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
        compat(ModCompat.farmersdelight(), RegItemsFD.TIN_KNIFE);

        trimmableArmorItem(RegItems.PLATINUM_HELMET);
        trimmableArmorItem(RegItems.PLATINUM_CHESTPLATE);
        trimmableArmorItem(RegItems.PLATINUM_LEGGINGS);
        trimmableArmorItem(RegItems.PLATINUM_BOOTS);
        toolItem(RegItems.PLATINUM_SWORD);
        toolItem(RegItems.PLATINUM_PICKAXE);
        toolItem(RegItems.PLATINUM_AXE);
        toolItem(RegItems.PLATINUM_SHOVEL);
        toolItem(RegItems.PLATINUM_HOE);
        compat(ModCompat.farmersdelight(), RegItemsFD.PLATINUM_KNIFE);

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
        compat(ModCompat.farmersdelight(), RegItemsFD.AETHERSTEEL_KNIFE);

        armor(RegItems.LIVINGSTONE_HELMET);
        armor(RegItems.LIVINGSTONE_CHESTPLATE);
        armor(RegItems.LIVINGSTONE_LEGGINGS);
        armor(RegItems.LIVINGSTONE_BOOTS);
        toolItem(RegItems.LIVINGSTONE_SWORD);
        toolItem(RegItems.LIVINGSTONE_PICKAXE);
        toolItem(RegItems.LIVINGSTONE_AXE);
        toolItem(RegItems.LIVINGSTONE_SHOVEL);
        toolItem(RegItems.LIVINGSTONE_HOE);
        compat(ModCompat.farmersdelight(), RegItemsFD.LIVINGSTONE_KNIFE);

        armor(RegItems.VERDITE_HELMET);
        armor(RegItems.VERDITE_CHESTPLATE);
        armor(RegItems.VERDITE_LEGGINGS);
        armor(RegItems.VERDITE_BOOTS);
        toolItem(RegItems.VERDITE_SWORD);
        toolItem(RegItems.VERDITE_PICKAXE);
        toolItem(RegItems.VERDITE_AXE);
        toolItem(RegItems.VERDITE_SHOVEL);
        toolItem(RegItems.VERDITE_HOE);
        compat(ModCompat.farmersdelight(), RegItemsFD.VERDITE_KNIFE);

        toolItem(RegItems.STEEL_DIGGER);
        toolItem(RegItems.STEEL_HAMMER);
        toolItem(RegItems.STEEL_SCYTHE);

        armor(RegItems.BRONZE_CROWN);
        armor(RegItems.BRONZE_CROWN_EMERALD);
        armor(RegItems.BRONZE_CROWN_DIAMOND);
        armor(RegItems.BRONZE_CROWN_LAPIS);
        armor(RegItems.BRONZE_CROWN_REDSTONE);

        shieldItem(RegItems.TIN_SHIELD, "small");
        shieldItem(RegItems.PLATINUM_SHIELD, "medium");
        shieldItem(RegItems.AETHERSTEEL_SHIELD, "big");
        shieldItem(RegItems.LIVINGSTONE_SHIELD, "small");
        shieldItem(RegItems.VERDITE_SHIELD, "medium");

        block(RegBlocks.TIN_ORE);
        block(RegBlocks.DEEPSLATE_TIN_ORE);
        block(RegBlocks.PLATINUM_ORE);
        block(RegBlocks.DEEPSLATE_PLATINUM_ORE);
        block(RegBlocks.NETHER_TUNGSTEN_ORE);
        block(RegBlocks.END_XP_ORE);
        block(RegBlocks.NETHER_XP_ORE);
        block(RegBlocks.PRIMITIVE_AETHERROCK);

        block(RegBlocks.TIN_BLOCK);
        block(RegBlocks.PLATINUM_BLOCK);
        block(RegBlocks.TUNGSTEN_BLOCK);
        block(RegBlocks.AETHERSTEEL_BLOCK);
        block(RegBlocks.BRONZE_BLOCK);
        block(RegBlocks.STEEL_BLOCK);
        block(RegBlocks.LIVINGSTONE_BLOCK);
        block(RegBlocks.VERDITE_BLOCK);
        block(RegBlocks.RAW_TIN_BLOCK);
        block(RegBlocks.RAW_PLATINUM_BLOCK);
        block(RegBlocks.RAW_TUNGSTEN_BLOCK);

        block(RegBlocks.AETHERROCK);
        block(RegBlocks.POLISHED_AETHERROCK);
        block(RegBlocks.AETHERROCK_BRICKS);
        block(RegBlocks.AETHERROCK_TILES);
        block(RegBlocks.BRONZE_TILES);
        block(RegBlocks.POLISHED_TUNGSTEN);
        block(RegBlocks.CUT_TUNGSTEN_BLOCK);
        block(RegBlocks.CUT_STEEL_BLOCK);
        block(RegBlocks.CRACKED_AETHERROCK_BRICKS);
        block(RegBlocks.STEEL_PILLAR);
        block(RegBlocks.GOLD_PILLAR);
        block(RegBlocks.PLATINUM_PILLAR);
        block(RegBlocks.CHISELED_TUNGSTEN_BRICKS);
        block(RegBlocks.CHISELED_TUNGSTEN_BLOCK);
        block(RegBlocks.TIN_TILES);
        block(RegBlocks.TIN_BRICKS);
        block(RegBlocks.TUNGSTEN_BRICKS);

        wall(RegBlocks.POLISHED_AETHERROCK_WALL, RegBlocks.POLISHED_AETHERROCK);
        block(RegBlocks.POLISHED_AETHERROCK_SLAB);
        block(RegBlocks.POLISHED_AETHERROCK_STAIR);

        block(RegBlocksSK.TIN_ORE_ANDESITE);
        block(RegBlocksSK.TIN_ORE_GRANITE);
        block(RegBlocksSK.TIN_ORE_DIORITE);
        block(RegBlocksSK.TIN_ORE_TUFF);
        block(RegBlocksSK.PLATINUM_ORE_ANDESITE);
        block(RegBlocksSK.PLATINUM_ORE_GRANITE);
        block(RegBlocksSK.PLATINUM_ORE_DIORITE);
        block(RegBlocksSK.PLATINUM_ORE_TUFF);
        block(RegBlocksSK.NETHER_TUNGSTEN_ORE_BLACKSTONE);
    }
}
