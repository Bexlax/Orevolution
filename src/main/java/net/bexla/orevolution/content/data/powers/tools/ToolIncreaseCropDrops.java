package net.bexla.orevolution.content.data.powers.tools;

import net.bexla.orevolution.content.data.base.OrevolutionToolPower;
import net.bexla.orevolution.content.data.interfaces.OrevolutionConditional;
import net.minecraft.core.BlockPos;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;

public class ToolIncreaseCropDrops extends OrevolutionToolPower {
    private final int dropIncrement;

    public ToolIncreaseCropDrops(String tooltip_id, OrevolutionConditional conditional, int dropIncrement) {
        super(tooltip_id, conditional);
        this.dropIncrement = dropIncrement;
    }

    @Override
    public void onMineBlock(ItemStack stack, Level level, BlockPos pos, Player player, BlockState state) {
        if(!state.is(BlockTags.CROPS)) return;

        if(getCondition(stack, level, player, null)) {
            for(int i = 0; i < this.dropIncrement; i++) {
                Block.dropResources(state, level, pos);
            }
        }
    }
}
