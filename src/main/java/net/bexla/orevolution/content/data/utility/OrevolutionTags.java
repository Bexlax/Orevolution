package net.bexla.orevolution.content.data.utility;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

public class OrevolutionTags {
    public static final TagKey<Block> Ores = BlockTags.create(ResourceLocation.parse("forge:ores"));

    public static final TagKey<Item> TinProgFollow = ItemTags.create(ResourceLocation.parse("orevolution:tools/tin_progress_followers"));
    public static final TagKey<Item> PlatProgFollow = ItemTags.create(ResourceLocation.parse("orevolution:tools/platinum_progress_followers"));
    public static final TagKey<Item> TinProgExcept = ItemTags.create(ResourceLocation.parse("orevolution:tools/tin_progress_exceptions"));
    public static final TagKey<Item> PlatProgExcept = ItemTags.create(ResourceLocation.parse("orevolution:tools/platinum_progress_exceptions"));
}
