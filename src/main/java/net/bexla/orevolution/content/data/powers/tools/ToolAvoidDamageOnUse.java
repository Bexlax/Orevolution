package net.bexla.orevolution.content.data.powers.tools;

import net.bexla.orevolution.content.data.base.OrevolutionToolPower;

import static net.bexla.orevolution.content.data.utility.OrevolutionHelperMethods.getChance;

public class ToolAvoidDamageOnUse extends OrevolutionToolPower {
    private final double chance;
    public ToolAvoidDamageOnUse(String tooltip_id, double chance) {
        super(tooltip_id);
        this.chance = chance;
    }

    @Override
    public boolean onUseOverride() {
        return getChance(chance);
    }
}
