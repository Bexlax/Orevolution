package net.bexla.orevolution.providers;

import net.bexla.orevolution.Orevolution;
import net.bexla.orevolution.content.init.RegBlocks;
import net.bexla.orevolution.content.init.RegItems;
import net.bexla.orevolution.content.types.ItemModelProvider;
import net.minecraft.data.PackOutput;
import net.minecraftforge.common.data.ExistingFileHelper;

public class ItemModels extends ItemModelProvider {
    public ItemModels(PackOutput output, ExistingFileHelper helper) {
        super(output, helper);
    }

    @Override
    public String getName() {
        return Orevolution.MODID + " Item Models";
    }

    @Override
    protected void registerModels() {
        normalItem(RegItems.RAW_TIN);
        normalItem(RegItems.RAW_PLATINUM);
        normalItem(RegItems.RAW_TUNGSTEN);
        normalItem(RegItems.AETHERSTEEL_CHUNK);
        normalItem(RegItems.TIN_INGOT);
        normalItem(RegItems.PLATINUM_INGOT);
        normalItem(RegItems.TUNGSTEN_INGOT);
        normalItem(RegItems.AETHERSTEEL_INGOT);
        normalItem(RegItems.BRONZE_ALLOY);
        normalItem(RegItems.STEEL_ALLOY);
        normalItem(RegItems.TUMBAGA_ALLOY);

        toolItem(RegItems.TIN_SWORD);
        toolItem(RegItems.TIN_PICKAXE);
        toolItem(RegItems.TIN_AXE);
        toolItem(RegItems.TIN_SHOVEL);
        toolItem(RegItems.TIN_HOE);

        normalItem(RegItems.PLATINUM_HELMET);
        normalItem(RegItems.PLATINUM_CHESTPLATE);
        normalItem(RegItems.PLATINUM_LEGGINGS);
        normalItem(RegItems.PLATINUM_BOOTS);

        toolItem(RegItems.PLATINUM_SWORD);
        toolItem(RegItems.PLATINUM_PICKAXE);
        toolItem(RegItems.PLATINUM_AXE);
        toolItem(RegItems.PLATINUM_SHOVEL);
        toolItem(RegItems.PLATINUM_HOE);

        normalItem(RegItems.AETHERSTEEL_HELMET);
        normalItem(RegItems.AETHERSTEEL_CHESTPLATE);
        normalItem(RegItems.AETHERSTEEL_LEGGINGS);
        normalItem(RegItems.AETHERSTEEL_BOOTS);

        toolItem(RegItems.AETHERSTEEL_SWORD);
        toolItem(RegItems.AETHERSTEEL_PICKAXE);
        toolItem(RegItems.AETHERSTEEL_AXE);
        toolItem(RegItems.AETHERSTEEL_SHOVEL);
        toolItem(RegItems.AETHERSTEEL_HOE);

        block(RegBlocks.TIN_ORE);
        block(RegBlocks.DEEPSLATE_TIN_ORE);
        block(RegBlocks.PLATINUM_ORE);
        block(RegBlocks.DEEPSLATE_PLATINUM_ORE);
        block(RegBlocks.NETHER_TUNGSTEN_ORE);
        // shieldItem(RegItems.TungstenShield);
    }
}
