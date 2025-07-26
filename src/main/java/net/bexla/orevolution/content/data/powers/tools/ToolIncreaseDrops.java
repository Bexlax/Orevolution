package net.bexla.orevolution.content.data.powers.tools;

import net.bexla.orevolution.content.data.base.OrevolutionToolPower;
import net.bexla.orevolution.content.data.tags.OrevolutionBlockTags;
import net.minecraft.core.BlockPos;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;

public class ToolIncreaseDrops extends OrevolutionToolPower {
    private final int dropMultiplier;
    private final double chanceMultiplier;

    public ToolIncreaseDrops(String tooltip_id, int dropMultiplier, double chanceMultiplier) {
        super(tooltip_id);
        this.dropMultiplier = dropMultiplier;
        this.chanceMultiplier = chanceMultiplier;
    }

    @Override
    public void onMineBlock(ItemStack stack, Level level, BlockPos pos, Player player, BlockState state) {
        double chance = 0.30;
        Item item = stack.getItem();
        if (!level.isClientSide && item.isCorrectToolForDrops(state)) {
            if(state.is(BlockTags.BEACON_BASE_BLOCKS))
                chance = 0.01;
            if(state.is(BlockTags.NEEDS_STONE_TOOL))
                chance = 0.15;
            if(state.is(OrevolutionBlockTags.Ores))
                chance = 0.07;

            chance = chance * chanceMultiplier;

            if(Math.random() < chance) {
                for(int i = 0; i < dropMultiplier; i++) {
                    Block.dropResources(state, level, pos);
                }
            }
        }
    }
}
