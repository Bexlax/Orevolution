package net.bexla.orevolution.content.data.tags;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;

public class OrevolutionBlockTags {
    public static final TagKey<Block> Ores = BlockTags.create(ResourceLocation.parse("forge:ores"));
}
