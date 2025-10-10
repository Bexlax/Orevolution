package net.bexla.orevolution.content.types.effects;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeMap;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;

public class PetrifiedEffect extends MobEffect {
    public PetrifiedEffect() {
        super(MobEffectCategory.HARMFUL, -11645352);
        this.addAttributeModifier(Attributes.MOVEMENT_SPEED, "8bba3dd8-69dc-322d-b9f0-edf337cb2159", -0.15, AttributeModifier.Operation.MULTIPLY_TOTAL);
    }

    @Override
    public void removeAttributeModifiers(LivingEntity entity, AttributeMap attributeMap, int amplifier) {
        super.removeAttributeModifiers(entity, attributeMap, amplifier);

        if (!entity.level().isClientSide())
            entity.addEffect(new MobEffectInstance(MobEffects.HARM, 5, amplifier));
    }

    @Override
    public boolean isDurationEffectTick(int duration, int amplifier) {
        return true;
    }
}
