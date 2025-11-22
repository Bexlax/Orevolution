package net.bexla.orevolution.content.data.powers.armors;

import net.bexla.orevolution.content.types.interfaces.Conditional;
import net.bexla.orevolution.content.types.power.armor.ArmorPowerMobEffects;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;

import java.util.List;
import java.util.function.Supplier;

public class ArmorCauseEffectsOnAttacked extends ArmorPowerMobEffects {
    public ArmorCauseEffectsOnAttacked(String tooltip_target_id, String tooltip_wearer_id, Conditional conditional, int duration, int amplifier, List<Supplier<MobEffect>> effectsWearer, List<Supplier<MobEffect>> effectsAttacker) {
        super(tooltip_target_id, tooltip_wearer_id, conditional, duration, amplifier, effectsAttacker, effectsWearer);
    }

    public ArmorCauseEffectsOnAttacked(String tooltip_target_id, String tooltip_wearer_id, Conditional conditional, int duration, int amplifier, Supplier<MobEffect> effectWearer, Supplier<MobEffect> effectAttacker) {
        super(tooltip_target_id, tooltip_wearer_id, conditional, duration, amplifier, effectAttacker != null? List.of(effectAttacker) : null, effectWearer != null? List.of(effectWearer) : null);
    }

    @Override
    public void onAttacked(LivingEntity wearer, DamageSource source, float amount) {
        LivingEntity attacker = (LivingEntity)source.getEntity();

        if(!getCBoolean(null, wearer.level(), wearer, attacker)) return;

        if(this.effectsPlayer != null) {
            for(Supplier<MobEffect> p : this.effectsPlayer) {
                MobEffectInstance instance = wearer.getEffect(p.get());

                if(wearer.hasEffect(p.get()) && instance != null) {
                    instance.update(new MobEffectInstance(p.get(), this.duration, this.amplifier));
                }
                else {
                    wearer.addEffect(new MobEffectInstance(p.get(), this.duration, this.amplifier));
                }
            }
        }

        if(attacker == null) return;

        if(this.effectsMob != null) {
            for(Supplier<MobEffect> p : this.effectsMob) {
                MobEffectInstance instance = attacker.getEffect(p.get());
                if(attacker.hasEffect(p.get()) && instance != null) {
                    instance.update(new MobEffectInstance(p.get(), this.duration, this.amplifier));
                }
                else {
                    attacker.addEffect(new MobEffectInstance(p.get(), this.duration, this.amplifier));
                }
            }
        }
    }
}
