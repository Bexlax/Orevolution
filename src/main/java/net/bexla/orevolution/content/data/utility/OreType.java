package net.bexla.orevolution.content.data.utility;

import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.levelgen.structure.templatesystem.TagMatchTest;
import net.minecraftforge.common.Tags;

import java.util.List;

public enum OreType {
    OVERWORLD(List.of(
            new TagMatchTest(BlockTags.STONE_ORE_REPLACEABLES),
            new TagMatchTest(BlockTags.DEEPSLATE_ORE_REPLACEABLES)
    )),
    NETHER(List.of(
            new TagMatchTest(Tags.Blocks.NETHERRACK)
    )),
    END(List.of(
            new TagMatchTest(Tags.Blocks.END_STONES)
    )),
    OVERWORLD_SPELUNKERY(List.of(
            new TagMatchTest(OrevolutionTags.Blocks.tuffs),
            new TagMatchTest(OrevolutionTags.Blocks.andesites),
            new TagMatchTest(OrevolutionTags.Blocks.diorites),
            new TagMatchTest(OrevolutionTags.Blocks.granites)
    )),
    NETHER_SPELUNKERY(List.of(
            new TagMatchTest(OrevolutionTags.Blocks.blackstones)
    ));

    private final List<TagMatchTest> targets;

    OreType(List<TagMatchTest> targets) {
        this.targets = targets;
    }

    public List<TagMatchTest> getTargets() {
        return targets;
    }
}