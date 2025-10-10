package net.bexla.orevolution.content.data.powers.armors;

import net.bexla.orevolution.content.data.base.interfaces.Conditional;
import net.minecraft.world.effect.MobEffect;

import java.util.List;

public class ArmorGrantEffect extends ArmorGrantMultipleEffects {
    public ArmorGrantEffect(String tooltipId, Conditional conditional, int duration, int amplifier, MobEffect effect) {
        super(tooltipId, conditional, duration, amplifier, effect != null ? List.of(effect) : null);
    }
}
