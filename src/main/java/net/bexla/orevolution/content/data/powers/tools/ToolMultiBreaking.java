package net.bexla.orevolution.content.data.powers.tools;

import net.bexla.orevolution.content.types.base.OrevolutionToolPower;
import net.bexla.orevolution.content.types.base.interfaces.Conditional;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;

public class ToolMultiBreaking extends OrevolutionToolPower {

    public ToolMultiBreaking(String tooltip_id, Conditional conditional) {
        super(tooltip_id, conditional);
    }

    @Override
    public void onMineBlock(ItemStack stack, Level level, BlockPos pos, LivingEntity player, BlockState state) {
        if (!getCondition(stack, state, level, player, null)) return;

        if (level.isClientSide) return;

        Direction facing = player.getDirection();
        boolean vertical = Math.abs(player.getXRot()) > 36;

        BlockPos.MutableBlockPos offsetPos = new BlockPos.MutableBlockPos();

        for (int dx = -1; dx <= 1; dx++) {
            for (int dy = -1; dy <= 1; dy++) {
                if (dx == 0 && dy == 0) continue;

                int ox = 0, oy = 0, oz = 0;

                if (vertical) {
                    ox = dx;
                    oz = dy;
                    oy = 0;
                }
                else if (facing.getAxis() == Direction.Axis.X) {
                    oy = dy;
                    oz = dx;
                    ox = 0;
                }
                else if (facing.getAxis() == Direction.Axis.Z) {
                    oy = dy;
                    ox = dx;
                    oz = 0;
                }

                offsetPos.set(pos.getX() + ox, pos.getY() + oy, pos.getZ() + oz);
                BlockState targetState = level.getBlockState(offsetPos);

                if (!targetState.isAir() && stack.isCorrectToolForDrops(targetState)) {
                    Block block = targetState.getBlock();

                    if (!level.isEmptyBlock(offsetPos) && !offsetPos.equals(pos)) {
                        level.destroyBlock(offsetPos, true, player);
                    }
                }
            }
        }
    }
}
