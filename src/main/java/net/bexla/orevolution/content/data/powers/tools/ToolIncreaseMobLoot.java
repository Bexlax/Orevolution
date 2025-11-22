package net.bexla.orevolution.content.data.powers.tools;

import net.bexla.orevolution.content.types.interfaces.Conditional;
import net.bexla.orevolution.content.types.power.tool.OrevolutionToolPower;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.ItemStack;

public class ToolIncreaseMobLoot extends OrevolutionToolPower {
    private final float multiplier;
    private final double chance;

    public ToolIncreaseMobLoot(String tooltipId, Conditional conditional, float multiplier, double chance) {
        super(tooltipId, conditional);
        this.multiplier = multiplier;
        this.chance = chance;
    }

    @Override
    public Object addTooltipValue() {
        return (int)(chance * 100) + "%";
    }

    @Override
    public void onHitEntity(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        if (!getCBoolean(stack, null, null, attacker, target)) return;
        if (attacker.level().isClientSide) return;

        if(Math.random() > chance) return;

        float damage = (float) attacker.getAttributeValue(Attributes.ATTACK_DAMAGE);
        if (target.getHealth() - damage > 0f) return; // not fatal

        CompoundTag data = target.getPersistentData();
        data.putFloat("orev_loot_multiplier", multiplier);
        data.putUUID("orev_loot_attacker", attacker.getUUID());
        long expireTime = attacker.level().getGameTime() + 40;
        data.putLong("orev_loot_expire", expireTime);
    }
}
