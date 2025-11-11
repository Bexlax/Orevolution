package net.bexla.orevolution.content.types.effects;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;

import java.util.UUID;

public class PetrifiedEffect extends MobEffect {
    private static final UUID MOVEMENT_SPEED_UUID = UUID.fromString("5a8f8f3a-1b7a-4b6b-9e2c-123456789abc");

    public PetrifiedEffect() {
        super(MobEffectCategory.HARMFUL, 0x4B8B3B);

        this.addAttributeModifier(Attributes.MOVEMENT_SPEED, MOVEMENT_SPEED_UUID.toString(),
                -0.15D, AttributeModifier.Operation.MULTIPLY_TOTAL);
    }

    @Override
    public void applyEffectTick(LivingEntity entity, int amplifier) {
        if (entity.level().isClientSide) return;

        float damage = 1.0F * (amplifier + 1);

        if (entity.getHealth() > 1.0F)
            entity.hurt(entity.damageSources().magic(), damage);
    }

    /**
     * Control the tick frequency of applyEffectTick.
     * duration is remaining duration (in ticks), amplifier is the effect level.
     *
     * We want damage every 40 ticks (2 seconds). Higher amplifiers can increase tick rate if desired.
     */
    @Override
    public boolean isDurationEffectTick(int duration, int amplifier) {
        int interval = 40 >> amplifier;
        if (interval == 0) interval = 1;

        return duration % interval == 0;
    }
}
