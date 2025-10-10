package net.bexla.orevolution.providers.models;

import net.bexla.orevolution.Orevolution;
import net.bexla.orevolution.content.types.base.providers.BlockStateModelProvider;
import net.bexla.orevolution.init.RegBlocks;
import net.minecraft.data.PackOutput;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.NotNull;

import java.util.function.Supplier;

public class GENBlockStateModels extends BlockStateModelProvider {
    public GENBlockStateModels(PackOutput output, ExistingFileHelper helper) {
        super(output, helper);
    }

    @Override
    public @NotNull String getName() {
        return Orevolution.MODID + " Block States";
    }

    private void ore(Supplier<? extends Block> block) {
        simpleBlock(block, "ore");
    }

    private void storage(Supplier<? extends Block> block) {
        simpleBlock(block, "storage");
    }

    private void decorative(Supplier<? extends Block> block) {
        simpleBlock(block, "decorative");
    }

    @Override
    protected void registerStatesAndModels() {
        ore(RegBlocks.TIN_ORE);
        ore(RegBlocks.DEEPSLATE_TIN_ORE);
        ore(RegBlocks.PLATINUM_ORE);
        ore(RegBlocks.DEEPSLATE_PLATINUM_ORE);
        ore(RegBlocks.NETHER_TUNGSTEN_ORE);
        ore(RegBlocks.END_XP_ORE);
        ore(RegBlocks.NETHER_XP_ORE);
        ore(RegBlocks.PRIMITIVE_AETHERROCK);

        storage(RegBlocks.TIN_BLOCK);
        storage(RegBlocks.PLATINUM_BLOCK);
        storage(RegBlocks.TUNGSTEN_BLOCK);
        storage(RegBlocks.REINFORCED_NETHERITE_BLOCK);
        storage(RegBlocks.AETHERSTEEL_BLOCK);
        storage(RegBlocks.BRONZE_BLOCK);
        storage(RegBlocks.TUMBAGA_BLOCK);
        storage(RegBlocks.STEEL_BLOCK);
        storage(RegBlocks.LIVINGSTONE_BLOCK);
        storage(RegBlocks.VERDITE_BLOCK);
        storage(RegBlocks.RAW_TIN_BLOCK);
        storage(RegBlocks.RAW_PLATINUM_BLOCK);
        storage(RegBlocks.RAW_TUNGSTEN_BLOCK);

        decorative(RegBlocks.AETHERROCK);
        decorative(RegBlocks.POLISHED_AETHERROCK);
        decorative(RegBlocks.AETHERROCK_BRICKS);
        decorative(RegBlocks.AETHERROCK_TILES);
        decorative(RegBlocks.BRONZE_TILES);
        decorative(RegBlocks.POLISHED_TUNGSTEN);
        decorative(RegBlocks.CUT_TUNGSTEN_BLOCK);
        decorative(RegBlocks.CRACKED_AETHERROCK_BRICKS);
    }
}
