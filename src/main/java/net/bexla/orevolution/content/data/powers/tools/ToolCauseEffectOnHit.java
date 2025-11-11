package net.bexla.orevolution.content.data.powers.tools;

import net.bexla.orevolution.content.types.interfaces.Conditional;
import net.minecraft.world.effect.MobEffect;

import java.util.List;
import java.util.function.Supplier;

public class ToolCauseEffectOnHit extends ToolCauseMultipleEffectsOnHit {
    public ToolCauseEffectOnHit(String tooltip_target_id, String tooltip_attacker_id, Conditional conditional, int duration, int amplifier, Supplier<MobEffect> effectTarget, Supplier<MobEffect> effectAttacker) {
        super(tooltip_target_id, tooltip_attacker_id, conditional, duration, amplifier, effectTarget != null? List.of(effectTarget) : null, effectAttacker != null?  List.of(effectAttacker) : null);
    }
}
