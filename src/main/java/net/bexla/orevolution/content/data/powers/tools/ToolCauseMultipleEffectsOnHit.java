package net.bexla.orevolution.content.data.powers.tools;

import net.bexla.orevolution.content.types.interfaces.Conditional;
import net.bexla.orevolution.content.types.power.tool.ToolPowerMobEffects;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;

import java.util.List;
import java.util.function.Supplier;

public class ToolCauseMultipleEffectsOnHit extends ToolPowerMobEffects {
    public ToolCauseMultipleEffectsOnHit(String tooltip_target_id, String tooltip_attacker_id, Conditional conditional, int duration, int amplifier, List<Supplier<MobEffect>> effectTarget, List<Supplier<MobEffect>> effectAttacker) {
        super(tooltip_target_id, tooltip_attacker_id, conditional, duration, amplifier, effectTarget, effectAttacker);
    }

    @Override
    public void onHitEntity(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        if(!getCBoolean(stack, null, attacker.level(), attacker, target)) return;

        if(this.effectTarget != null) {
            for(Supplier<MobEffect> p : this.effectTarget) {
                if(target.hasEffect(p.get())) {
                    target.getEffect(p.get()).update(new MobEffectInstance(p.get(), this.duration, this.amplifier));
                }
                else {
                    target.addEffect(new MobEffectInstance(p.get(), this.duration, this.amplifier));
                }
            }
        }

        if(this.effectAttacker != null) {
            for(Supplier<MobEffect> p : this.effectAttacker) {
                MobEffectInstance attackerInstance = attacker.getEffect(p.get());

                if(attacker.hasEffect(p.get()) && attackerInstance != null) {
                    attackerInstance.update(new MobEffectInstance(p.get(), this.duration, this.amplifier));
                }
                else {
                    attacker.addEffect(new MobEffectInstance(p.get(), this.duration, this.amplifier));
                }
            }
        }
    }
}
