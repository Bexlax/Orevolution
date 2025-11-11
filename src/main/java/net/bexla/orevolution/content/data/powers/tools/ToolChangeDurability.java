package net.bexla.orevolution.content.data.powers.tools;

import net.bexla.orevolution.content.types.OrevolutionToolPower;
import net.bexla.orevolution.content.types.interfaces.Conditional;
import net.minecraft.world.item.ItemStack;

public class ToolChangeDurability extends OrevolutionToolPower {
    private final int durability;
    private final boolean replaceOriginalValue;

    public ToolChangeDurability(String tooltipId, Conditional conditional, int durability, boolean replaceOriginalValue) {
        super(tooltipId, conditional);
        this.durability = durability;
        this.replaceOriginalValue = replaceOriginalValue;
    }

    public ToolChangeDurability(String tooltipId, Conditional conditional, int durability) {
        super(tooltipId, conditional);
        this.durability = durability;
        this.replaceOriginalValue = false;
    }

    @Override
    public Object addTooltipValue() {
        return durability;
    }

    @Override
    public int setMaxUses(ItemStack stack, int defaultUses) {
        if(!getCondition(stack, null, null, null, null)) return defaultUses;

        stack.setDamageValue(stack.getDamageValue() + durability);
        return replaceOriginalValue? durability : defaultUses + durability;
    }
}
