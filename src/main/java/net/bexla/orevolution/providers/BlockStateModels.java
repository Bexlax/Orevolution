package net.bexla.orevolution.providers;

import net.bexla.orevolution.Orevolution;
import net.bexla.orevolution.content.init.RegBlocks;
import net.bexla.orevolution.content.init.RegItems;
import net.bexla.orevolution.content.types.BlockStateModelProvider;
import net.bexla.orevolution.content.types.ItemModelProvider;
import net.minecraft.data.PackOutput;
import net.minecraftforge.common.data.ExistingFileHelper;

public class BlockStateModels extends BlockStateModelProvider {
    public BlockStateModels(PackOutput output, ExistingFileHelper helper) {
        super(output, helper);
    }

    @Override
    public String getName() {
        return Orevolution.MODID + " Block States";
    }

    @Override
    protected void registerStatesAndModels() {
        simpleBlock(RegBlocks.TIN_ORE);
        simpleBlock(RegBlocks.DEEPSLATE_TIN_ORE);
        simpleBlock(RegBlocks.PLATINUM_ORE);
        simpleBlock(RegBlocks.DEEPSLATE_PLATINUM_ORE);
        simpleBlock(RegBlocks.NETHER_TUNGSTEN_ORE);
    }
}
