package net.bexla.orevolution.content.data.powers.tools;

import net.bexla.orevolution.content.types.base.OrevolutionToolPower;
import net.bexla.orevolution.content.types.base.interfaces.Conditional;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class ToolAvoidDamageOnUse extends OrevolutionToolPower {
    public ToolAvoidDamageOnUse(String tooltipId, Conditional conditional) {
        super(tooltipId, conditional);
    }

    @Override
    public boolean onUseOverride(ItemStack stack, Level level, LivingEntity player) {
        return getCondition(stack, null, level, player, null);
    }
}
