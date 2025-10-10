package net.bexla.orevolution.content.data.powers.tools;

import net.bexla.orevolution.content.types.base.OrevolutionToolPower;
import net.bexla.orevolution.content.types.base.interfaces.Conditional;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.state.BlockState;

public class ToolMineBlockFaster extends OrevolutionToolPower {
    private final float speedIncrease;

    public ToolMineBlockFaster(String tooltipId, Conditional conditional, float speedIncrease) {
        super(tooltipId, conditional);
        this.speedIncrease = speedIncrease;
    }

    @Override
    public float setDestroySpeed(ItemStack stack, BlockState state, float defaultSpeed) {
        return getCondition(stack, state, null, null, null)? defaultSpeed + speedIncrease : defaultSpeed;
    }
}
