package net.bexla.orevolution.datagens.tags;

import net.bexla.orevolution.Orevolution;
import net.bexla.orevolution.content.data.utility.OrevolutionTags;
import net.bexla.orevolution.init.RegBlocks;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.IntrinsicHolderTagsProvider;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

import static net.bexla.orevolution.content.data.utility.OrevolutionUtils.modLocat;

public class GENBlockTags extends IntrinsicHolderTagsProvider<Block> {

    public GENBlockTags(PackOutput output, CompletableFuture<HolderLookup.Provider> future, @Nullable ExistingFileHelper helper) {
        super(output, Registries.BLOCK, future, block -> block.builtInRegistryHolder().key(), Orevolution.MODID, helper);
    }

    @Override
    public @NotNull String getName() {
        return "Orevolution Block Tags";
    }

    @Override
    protected void addTags(HolderLookup.@NotNull Provider provider) {
        tag(BlockTags.CROPS).add(RegBlocks.LIVINGSTONE_CROP.get()).add(RegBlocks.VERDITE_CROP.get());

        tag(OrevolutionTags.Blocks.tinStorages).add(RegBlocks.TIN_BLOCK.get());
        tag(OrevolutionTags.Blocks.platStorages).add(RegBlocks.PLATINUM_BLOCK.get());
        tag(OrevolutionTags.Blocks.tungsStorages).add(RegBlocks.TUNGSTEN_BLOCK.get());
        tag(OrevolutionTags.Blocks.enderiteStorages).add(RegBlocks.AETHERSTEEL_BLOCK.get());
        tag(OrevolutionTags.Blocks.livingstoneStorages).add(RegBlocks.LIVINGSTONE_BLOCK.get());
        tag(OrevolutionTags.Blocks.verditeStorages).add(RegBlocks.VERDITE_BLOCK.get());

        tag(OrevolutionTags.Blocks.rawTinStorages).add(RegBlocks.RAW_TIN_BLOCK.get());
        tag(OrevolutionTags.Blocks.rawPlatStorages).add(RegBlocks.RAW_PLATINUM_BLOCK.get());
        tag(OrevolutionTags.Blocks.rawTungsStorages).add(RegBlocks.RAW_TUNGSTEN_BLOCK.get());

        tag(OrevolutionTags.Blocks.tuffs).add(Blocks.TUFF);
        tag(OrevolutionTags.Blocks.andesites).add(Blocks.ANDESITE);
        tag(OrevolutionTags.Blocks.diorites).add(Blocks.DIORITE);
        tag(OrevolutionTags.Blocks.granites).add(Blocks.GRANITE);

        tag(BlockTags.NEEDS_STONE_TOOL).add(RegBlocks.TIN_ORE.get()).add(RegBlocks.DEEPSLATE_TIN_ORE.get()).add(RegBlocks.LIVINGSTONE_CROP.get())
                .add(RegBlocks.AETHERROCK.get()).add(RegBlocks.POLISHED_AETHERROCK.get()).add(RegBlocks.AETHERROCK_BRICKS.get())
                .add(RegBlocks.CRACKED_AETHERROCK_BRICKS.get()).add(RegBlocks.BRONZE_BLOCK.get()).add(RegBlocks.BRONZE_TILES.get())
                .add(RegBlocks.TIN_TILES.get()).add(RegBlocks.TIN_BRICKS.get()).add(RegBlocks.RAW_TIN_BLOCK.get()).add(RegBlocks.TIN_BLOCK.get());

        tag(BlockTags.NEEDS_IRON_TOOL).add(RegBlocks.PLATINUM_ORE.get()).add(RegBlocks.DEEPSLATE_PLATINUM_ORE.get()).add(RegBlocks.VERDITE_CROP.get())
                .add(RegBlocks.STEEL_TRAPDOOR.get()).add(RegBlocks.STEEL_BLOCK.get()).add(RegBlocks.STEEL_DOOR.get()).add(RegBlocks.STEEL_PILLAR.get())
                .add(RegBlocks.PLATINUM_TILES.get()).add(RegBlocks.RAW_PLATINUM_BLOCK.get()).add(RegBlocks.PLATINUM_BLOCK.get());

        tag(BlockTags.NEEDS_DIAMOND_TOOL).add(RegBlocks.NETHER_TUNGSTEN_ORE.get()).add(RegBlocks.TUNGSTEN_BLOCK.get()).add(RegBlocks.RAW_TUNGSTEN_BLOCK.get())
                .add(RegBlocks.POLISHED_TUNGSTEN.get()).add(RegBlocks.CHISELED_TUNGSTEN_BLOCK.get()).add(RegBlocks.CUT_TUNGSTEN_BLOCK.get());

        tag(OrevolutionTags.Blocks.spelunkeryOres).addOptional(modLocat("nether_tungsten_ore_blackstone")).addOptional(modLocat("tin_ore_andesite")).addOptional(modLocat("tin_ore_diorite"))
                .addOptional(modLocat("tin_ore_granite")).addOptional(modLocat("tin_ore_tuff")).addOptional(modLocat("platinum_ore_andesite"))
                .addOptional(modLocat("platinum_ore_diorite")).addOptional(modLocat("platinum_ore_granite")).addOptional(modLocat("platinum_ore_tuff"));

        tag(OrevolutionTags.Blocks.tinOres).add(RegBlocks.TIN_ORE.get()).add(RegBlocks.DEEPSLATE_TIN_ORE.get())
                .addOptional(modLocat("tin_ore_andesite")).addOptional(modLocat("tin_ore_diorite")).addOptional(modLocat("tin_ore_granite")).addOptional(modLocat("tin_ore_tuff"));
        tag(OrevolutionTags.Blocks.tungsOres).add(RegBlocks.NETHER_TUNGSTEN_ORE.get()).addOptional(modLocat("nether_tungsten_ore_blackstone"));
        tag(OrevolutionTags.Blocks.platOres).add(RegBlocks.PLATINUM_ORE.get()).add(RegBlocks.DEEPSLATE_PLATINUM_ORE.get())
                .addOptional(modLocat("platinum_ore_andesite")).addOptional(modLocat("platinum_ore_diorite")).addOptional(modLocat("platinum_ore_granite")).addOptional(modLocat("platinum_ore_tuff"));
        tag(OrevolutionTags.Blocks.XPOres).add(RegBlocks.END_XP_ORE.get()).add(RegBlocks.NETHER_XP_ORE.get());

        tag(BlockTags.MINEABLE_WITH_PICKAXE).add(RegBlocks.TIN_ORE.get()).add(RegBlocks.PLATINUM_ORE.get()).add(RegBlocks.NETHER_TUNGSTEN_ORE.get())
                .add(RegBlocks.NETHER_XP_ORE.get()).add(RegBlocks.END_XP_ORE.get()).add(RegBlocks.DEEPSLATE_TIN_ORE.get()).add(RegBlocks.DEEPSLATE_PLATINUM_ORE.get())
                .add(RegBlocks.PRIMITIVE_AETHERROCK.get()).add(RegBlocks.TIN_BLOCK.get()).add(RegBlocks.PLATINUM_BLOCK.get()).add(RegBlocks.TUNGSTEN_BLOCK.get())
                .add(RegBlocks.AETHERSTEEL_BLOCK.get()).add(RegBlocks.BRONZE_BLOCK.get()).add(RegBlocks.STEEL_BLOCK.get()).add(RegBlocks.STEEL_DOOR.get())
                .add(RegBlocks.LIVINGSTONE_BLOCK.get()).add(RegBlocks.VERDITE_BLOCK.get()).add(RegBlocks.RAW_TIN_BLOCK.get()).add(RegBlocks.RAW_PLATINUM_BLOCK.get())
                .add(RegBlocks.RAW_TUNGSTEN_BLOCK.get()).add(RegBlocks.AETHERROCK.get()).add(RegBlocks.POLISHED_AETHERROCK.get()).add(RegBlocks.POLISHED_TUNGSTEN.get())
                .add(RegBlocks.AETHERROCK_BRICKS.get()).add(RegBlocks.CRACKED_AETHERROCK_BRICKS.get()).add(RegBlocks.AETHERROCK_TILES.get()).add(RegBlocks.BRONZE_TILES.get())
                .add(RegBlocks.CUT_TUNGSTEN_BLOCK.get()).add(RegBlocks.CUT_STEEL_BLOCK.get()).add(RegBlocks.STEEL_PILLAR.get()).add(RegBlocks.POLISHED_AETHERROCK_STAIR.get())
                .add(RegBlocks.POLISHED_AETHERROCK_WALL.get()).add(RegBlocks.POLISHED_AETHERROCK_SLAB.get()).add(RegBlocks.VERDITE_CROP.get()).add(RegBlocks.LIVINGSTONE_CROP.get())
                .addOptionalTag(modLocat("spelunkery/ores"));

        tag(OrevolutionTags.Blocks.uncommonDuplicateChance).addTags(
                BlockTags.NEEDS_STONE_TOOL,
                BlockTags.NEEDS_IRON_TOOL,
                BlockTags.ANVIL,
                Tags.Blocks.GRAVEL);
        tag(OrevolutionTags.Blocks.rareDuplicateChance)
                .addTag(Tags.Blocks.STORAGE_BLOCKS);
        tag(OrevolutionTags.Blocks.alwaysDuplicateChance).addTags(
                Tags.Blocks.OBSIDIAN,
                OrevolutionTags.Blocks.tuffs,
                OrevolutionTags.Blocks.andesites,
                OrevolutionTags.Blocks.diorites,
                OrevolutionTags.Blocks.granites,
                BlockTags.LEAVES
        );

        tag(OrevolutionTags.Blocks.woodExceptions).addTag(OrevolutionTags.Blocks.stoneExceptions);

        tag(OrevolutionTags.Blocks.stoneExceptions).add(Blocks.IRON_BLOCK).add(Blocks.IRON_ORE).add(Blocks.DEEPSLATE_IRON_ORE).add(Blocks.RAW_IRON_BLOCK)
                .add(Blocks.IRON_BARS).add(Blocks.IRON_DOOR).add(Blocks.IRON_TRAPDOOR).add(Blocks.HOPPER)
                .addTag(BlockTags.ANVIL).addTag(OrevolutionTags.Blocks.tinExceptions);

        tag(OrevolutionTags.Blocks.tinExceptions).add(RegBlocks.VERDITE_BLOCK.get()).add(RegBlocks.PLATINUM_ORE.get()).add(RegBlocks.DEEPSLATE_PLATINUM_ORE.get()).
                add(RegBlocks.RAW_PLATINUM_BLOCK.get()).add(RegBlocks.NETHER_XP_ORE.get()).add(RegBlocks.STEEL_BLOCK.get()).add(RegBlocks.PLATINUM_BLOCK.get()).
                addTag(OrevolutionTags.Blocks.ironExceptions);

        tag(OrevolutionTags.Blocks.ironExceptions).add(Blocks.DIAMOND_BLOCK).add(Blocks.DIAMOND_ORE).add(Blocks.DEEPSLATE_DIAMOND_ORE)
                .add(Blocks.REDSTONE_BLOCK).add(Blocks.REDSTONE_ORE).add(Blocks.DEEPSLATE_REDSTONE_ORE).addTag(OrevolutionTags.Blocks.platExceptions);

        tag(OrevolutionTags.Blocks.platExceptions).add(RegBlocks.NETHER_TUNGSTEN_ORE.get()).add(RegBlocks.RAW_TUNGSTEN_BLOCK.get()).add(RegBlocks.END_XP_ORE.get())
                .add(RegBlocks.TUNGSTEN_BLOCK.get()).addTag(OrevolutionTags.Blocks.diamondExceptions);

        tag(OrevolutionTags.Blocks.diamondExceptions).add(RegBlocks.PRIMITIVE_AETHERROCK.get()).add(RegBlocks.AETHERSTEEL_BLOCK.get());

        tag(Tags.Blocks.STORAGE_BLOCKS).addTags(
                OrevolutionTags.Blocks.tinStorages,
                OrevolutionTags.Blocks.platStorages,
                OrevolutionTags.Blocks.tungsStorages,
                OrevolutionTags.Blocks.verditeStorages,
                OrevolutionTags.Blocks.enderiteStorages,
                OrevolutionTags.Blocks.livingstoneStorages,
                OrevolutionTags.Blocks.rawPlatStorages,
                OrevolutionTags.Blocks.rawTinStorages,
                OrevolutionTags.Blocks.rawTungsStorages
        );

        tag(Tags.Blocks.ORES).addTags(
                OrevolutionTags.Blocks.tinOres,
                OrevolutionTags.Blocks.platOres,
                OrevolutionTags.Blocks.tungsOres,
                OrevolutionTags.Blocks.XPOres
        ).add(RegBlocks.NETHER_TUNGSTEN_ORE.get()).add(RegBlocks.PRIMITIVE_AETHERROCK.get())
                .addOptionalTag(modLocat("spelunkery/ores"));
    }
}
