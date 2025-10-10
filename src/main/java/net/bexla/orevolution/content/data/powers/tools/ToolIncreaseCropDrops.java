package net.bexla.orevolution.content.data.powers.tools;

import net.bexla.orevolution.content.types.base.OrevolutionToolPower;
import net.bexla.orevolution.content.types.base.interfaces.Conditional;
import net.minecraft.core.BlockPos;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;

public class ToolIncreaseCropDrops extends OrevolutionToolPower {
    private final int dropIncrement;

    public ToolIncreaseCropDrops(String tooltip_id, Conditional conditional, int dropIncrement) {
        super(tooltip_id, conditional);
        this.dropIncrement = dropIncrement;
    }

    @Override
    public @NotNull Object addTooltipValue() {
        return dropIncrement;
    }

    @Override
    public void onMineBlock(ItemStack stack, Level level, BlockPos pos, LivingEntity player, BlockState state) {
        if(!state.is(BlockTags.CROPS)) return;

        if(getCondition(stack, state, level, player, null)) {
            for(int i = 1; i < this.dropIncrement; i++) {
                Block.dropResources(state, level, pos);
            }
        }
    }
}
