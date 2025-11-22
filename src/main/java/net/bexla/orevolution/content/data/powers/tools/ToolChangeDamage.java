package net.bexla.orevolution.content.data.powers.tools;

import net.bexla.orevolution.content.types.interfaces.Conditional;
import net.bexla.orevolution.content.types.power.tool.OrevolutionToolPower;
import net.minecraft.world.item.ItemStack;

public class ToolChangeDamage extends OrevolutionToolPower {
    private final float damage;
    private final boolean replaceOriginalValue;

    public ToolChangeDamage(String tooltipId, Conditional conditional, float damage, boolean replaceOriginalValue) {
        super(tooltipId, conditional);
        this.damage = damage;
        this.replaceOriginalValue = replaceOriginalValue;
    }

    public ToolChangeDamage(String tooltipId, Conditional conditional, float damage) {
        super(tooltipId, conditional);
        this.damage = damage;
        this.replaceOriginalValue = false;
    }

    @Override
    public Object addTooltipValue() {
        return damage;
    }

    @Override
    public float setAttackDamage(ItemStack stack, float defaultDamage) {
        if(!getCBoolean(stack, null, null, null, null)) return defaultDamage;

        return replaceOriginalValue? damage : defaultDamage + damage;
    }
}
