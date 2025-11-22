package net.bexla.orevolution.datagens.models;

import net.bexla.orevolution.Orevolution;
import net.bexla.orevolution.compatibility.ModCompat;
import net.bexla.orevolution.compatibility.spelunkery.RegBlocksSK;
import net.bexla.orevolution.content.types.block.OreCropBlock;
import net.bexla.orevolution.content.types.providers.BlockStateModelProvider;
import net.bexla.orevolution.init.RegBlocks;
import net.minecraft.data.PackOutput;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.RegistryObject;
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

    private void compat(String modid, Supplier<? extends Block> block) {
        simpleBlock(block, "compat/" + modid);
    }

    public void makeCrop(RegistryObject<Block> block, String modelName, String textureName) {
        makeCrop((OreCropBlock) block.get(), modelName, textureName);
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
        storage(RegBlocks.AETHERSTEEL_BLOCK);
        storage(RegBlocks.BRONZE_BLOCK);
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
        decorative(RegBlocks.CUT_STEEL_BLOCK);
        decorative(RegBlocks.CUT_TUNGSTEN_BLOCK);
        decorative(RegBlocks.CRACKED_AETHERROCK_BRICKS);
        cubeBottomTop(RegBlocks.STEEL_PILLAR, "decorative");
        decorative(RegBlocks.PLATINUM_TILES);
        decorative(RegBlocks.CHISELED_TUNGSTEN_BLOCK);
        decorative(RegBlocks.CHISELED_TUNGSTEN_BRICKS);
        decorative(RegBlocks.TIN_TILES);
        decorative(RegBlocks.TIN_BRICKS);

        stairsBlock(RegBlocks.POLISHED_AETHERROCK_STAIR, RegBlocks.POLISHED_AETHERROCK);
        slabBlock(RegBlocks.POLISHED_AETHERROCK_SLAB, RegBlocks.POLISHED_AETHERROCK);
        wallBlock(RegBlocks.POLISHED_AETHERROCK_WALL, RegBlocks.POLISHED_AETHERROCK);

        doorBlock(RegBlocks.STEEL_DOOR);
        trapdoorBlock(RegBlocks.STEEL_TRAPDOOR);

        makeCrop(RegBlocks.VERDITE_CROP, "verdite_crop_stage", "verdite_crop_stage");
        makeCrop(RegBlocks.LIVINGSTONE_CROP, "livingstone_crop_stage", "livingstone_crop_stage");

        compat(ModCompat.spelunkery(), RegBlocksSK.TIN_ORE_ANDESITE);
        compat(ModCompat.spelunkery(), RegBlocksSK.TIN_ORE_GRANITE);
        compat(ModCompat.spelunkery(), RegBlocksSK.TIN_ORE_DIORITE);
        compat(ModCompat.spelunkery(), RegBlocksSK.TIN_ORE_TUFF);
        compat(ModCompat.spelunkery(), RegBlocksSK.PLATINUM_ORE_ANDESITE);
        compat(ModCompat.spelunkery(), RegBlocksSK.PLATINUM_ORE_GRANITE);
        compat(ModCompat.spelunkery(), RegBlocksSK.PLATINUM_ORE_DIORITE);
        compat(ModCompat.spelunkery(), RegBlocksSK.PLATINUM_ORE_TUFF);

        compat(ModCompat.spelunkery(), RegBlocksSK.NETHER_TUNGSTEN_ORE_BLACKSTONE);
    }
}
