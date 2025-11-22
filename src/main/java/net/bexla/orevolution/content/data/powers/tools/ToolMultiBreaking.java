package net.bexla.orevolution.content.data.powers.tools;

import net.bexla.orevolution.content.types.interfaces.Conditional;
import net.bexla.orevolution.content.types.power.tool.OrevolutionToolPower;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;

public class ToolMultiBreaking extends OrevolutionToolPower {

    public ToolMultiBreaking(String tooltip_id, Conditional conditional) {
        super(tooltip_id, conditional);
    }

    @Override
    public void onMineBlock(ItemStack stack, Level level, BlockPos pos, LivingEntity player, BlockState state) {
        if (!getCBoolean(stack, state, level, player, null)) return;

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

                if (!targetState.isAir() && stack.isCorrectToolForDrops(targetState)) { // Check if the tool can break this block
                    if (!offsetPos.equals(pos)) {
                        int dam = stack.getDamageValue();
                        level.destroyBlock(offsetPos, true, player);
                        stack.setDamageValue(dam - 1);
                    }
                }
            }
        }
    }
}
