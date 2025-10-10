package net.bexla.orevolution.content.data.powers.tools;

import net.bexla.orevolution.content.types.base.interfaces.Conditional;
import net.minecraft.world.effect.MobEffect;

import java.util.List;

public class ToolCauseEffectOnHit extends ToolCauseMultipleEffectsOnHit {
    public ToolCauseEffectOnHit(String tooltip_id, Conditional conditional, int duration, int amplifier, MobEffect effectTarget, MobEffect effectAttacker) {
        super(tooltip_id, conditional, duration, amplifier, effectTarget != null? List.of(effectTarget) : null, effectAttacker != null?  List.of(effectAttacker) : null);
    }
}
