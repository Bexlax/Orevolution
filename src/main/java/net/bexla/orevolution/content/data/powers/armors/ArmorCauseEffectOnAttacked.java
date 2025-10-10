package net.bexla.orevolution.content.data.powers.armors;

import net.bexla.orevolution.content.types.base.interfaces.Conditional;
import net.minecraft.world.effect.MobEffect;

import java.util.List;

public class ArmorCauseEffectOnAttacked extends ArmorCauseMultipleEffectsOnAttacked {
    public ArmorCauseEffectOnAttacked(String tooltipId, Conditional conditional, int duration, int amplifier, MobEffect effectsWearer, MobEffect effectsAttacker) {
        super(tooltipId, conditional, duration, amplifier, effectsWearer != null? List.of(effectsWearer) : null, effectsAttacker != null? List.of(effectsAttacker) : null);
    }
}
