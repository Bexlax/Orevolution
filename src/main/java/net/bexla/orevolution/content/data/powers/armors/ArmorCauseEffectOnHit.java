package net.bexla.orevolution.content.data.powers.armors;

import net.bexla.orevolution.content.types.base.interfaces.Conditional;
import net.minecraft.world.effect.MobEffect;

import java.util.List;

public class ArmorCauseEffectOnHit extends ArmorCauseMultipleEffectsOnHit {

    public ArmorCauseEffectOnHit(String tooltipId, Conditional conditional, int duration, int amplifier, MobEffect effectsTarget, MobEffect effectsWearer) {
        super(tooltipId, conditional, duration, amplifier, effectsTarget != null? List.of(effectsTarget) : null, effectsWearer != null? List.of(effectsWearer) : null);
    }
}
