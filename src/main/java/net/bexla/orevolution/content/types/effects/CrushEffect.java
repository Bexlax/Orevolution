package net.bexla.orevolution.content.types.effects;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class CrushEffect extends MobEffect {
    public CrushEffect() {
        super(MobEffectCategory.HARMFUL, 0xC84E4E);
        MinecraftForge.EVENT_BUS.register(this);
    }

    @SubscribeEvent
    public void onLivingHurt(LivingHurtEvent event) {
        LivingEntity entity = event.getEntity();
        if (entity == null) return;

        if (entity.hasEffect(this)) {
            int amplifier = entity.getEffect(this).getAmplifier();

            float multiplier = 1.15f + (amplifier * 0.15f);

            event.setAmount(event.getAmount() * multiplier);
        }
    }

    @Override
    public boolean isDurationEffectTick(int duration, int amplifier) {
        return false;
    }
}
