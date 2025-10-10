package net.bexla.orevolution.content.data.powers.tools;

import net.bexla.orevolution.content.types.base.OrevolutionToolPower;
import net.bexla.orevolution.content.types.base.interfaces.Conditional;
import net.minecraft.world.item.ItemStack;

public class ToolIncreaseDurability extends OrevolutionToolPower {
    private final int durabilityIncrease;

    public ToolIncreaseDurability(String tooltipId, Conditional conditional, int durabilityIncrease) {
        super(tooltipId, conditional);
        this.durabilityIncrease = durabilityIncrease;
    }

    @Override
    public Object addTooltipValue() {
        return durabilityIncrease;
    }

    @Override
    public int setMaxUses(ItemStack stack, int defaultUses) {
        return getCondition(stack, null, null, null, null)? defaultUses + durabilityIncrease : defaultUses;
    }
}
