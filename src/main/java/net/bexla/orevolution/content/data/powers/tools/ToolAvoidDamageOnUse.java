package net.bexla.orevolution.content.data.powers.tools;

import net.bexla.orevolution.content.data.base.OrevolutionToolPower;
import net.bexla.orevolution.content.data.interfaces.OrevolutionConditional;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

/**
 * This does NOT work on non-Orevolution-class Tools (OrevolutionAxe, OrevolutionPickaxe, etc).
 */
public class ToolAvoidDamageOnUse extends OrevolutionToolPower {
    public ToolAvoidDamageOnUse(String tooltipId, OrevolutionConditional conditional) {
        super(tooltipId, conditional);
    }

    @Override
    public boolean onUseOverride(ItemStack stack, Level level, LivingEntity player) {
        return getCondition(stack, level, player, null);
    }
}
