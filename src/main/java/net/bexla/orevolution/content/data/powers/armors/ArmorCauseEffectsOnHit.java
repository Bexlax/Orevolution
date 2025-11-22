package net.bexla.orevolution.content.data.powers.armors;

import net.bexla.orevolution.content.types.interfaces.Conditional;
import net.bexla.orevolution.content.types.power.armor.ArmorPowerMobEffects;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;

import java.util.List;
import java.util.function.Supplier;

public class ArmorCauseEffectsOnHit extends ArmorPowerMobEffects {
    public ArmorCauseEffectsOnHit(String tooltip_target_id, String tooltip_wearer_id, Conditional conditional, int duration, int amplifier, List<Supplier<MobEffect>> effectsTarget, List<Supplier<MobEffect>> effectsWearer) {
        super(tooltip_target_id, tooltip_wearer_id, conditional, duration, amplifier, effectsTarget, effectsWearer);
    }

    public ArmorCauseEffectsOnHit(String tooltip_target_id, String tooltip_wearer_id, Conditional conditional, int duration, int amplifier, Supplier<MobEffect> effectTarget, Supplier<MobEffect> effectWearer) {
        super(tooltip_target_id, tooltip_wearer_id, conditional, duration, amplifier, effectTarget != null ? List.of(effectTarget) : List.of(), effectWearer != null ? List.of(effectWearer) : List.of());
    }

    @Override
    public void onAttackTarget(LivingEntity wearer, LivingEntity target) {
        if(!getCBoolean(null, wearer.level(), wearer, target)) return;

        if(!this.effectsMob.isEmpty()) {
            for(Supplier<MobEffect> p : this.effectsMob) {
                if(target.hasEffect(p.get())) {
                    target.getEffect(p.get()).update(new MobEffectInstance(p.get(), this.duration, this.amplifier));
                }
                else {
                    target.addEffect(new MobEffectInstance(p.get(), this.duration, this.amplifier));
                }
            }
        }

        if(!this.effectsPlayer.isEmpty()) {
            for(Supplier<MobEffect> p : this.effectsPlayer) {
                if(wearer.hasEffect(p.get())) {
                    wearer.getEffect(p.get()).update(new MobEffectInstance(p.get(), this.duration, this.amplifier));
                }
                else {
                    wearer.addEffect(new MobEffectInstance(p.get(), this.duration, this.amplifier));
                }
            }
        }
    }
}
