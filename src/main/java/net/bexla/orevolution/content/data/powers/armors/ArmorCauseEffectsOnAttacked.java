package net.bexla.orevolution.content.data.powers.armors;

import net.bexla.orevolution.content.data.base.OrevolutionArmorPower;
import net.bexla.orevolution.content.data.interfaces.OrevolutionConditional;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

import java.util.List;

public class ArmorCauseEffectsOnAttacked extends OrevolutionArmorPower {
    private final List<MobEffect> effectsWearer;
    private final List<MobEffect> effectsAttacker;
    private final int duration;
    private final int amplifier;

    public ArmorCauseEffectsOnAttacked(String tooltipId, OrevolutionConditional conditional, int duration, int amplifier, List<MobEffect> effectsWearer, List<MobEffect> effectsAttacker) {
        super(tooltipId, conditional);
        this.effectsWearer = effectsWearer;
        this.effectsAttacker = effectsAttacker;
        this.duration = duration;
        this.amplifier = amplifier;
    }

    @Override
    public void appendTooltip(ItemStack stack, Level level, List<Component> lines) {
        if(this.effectsAttacker != null) {
            lines.add(Component.translatable("tooltips.orevolution." + getTooltipID()).withStyle(ChatFormatting.GREEN));
            for (MobEffect p : this.effectsAttacker) {
                lines.add(Component.literal(p.getDisplayName().getString()).withStyle(ChatFormatting.GREEN));
            }
        }
        if(this.effectsWearer != null) {
            lines.add(Component.translatable("tooltips.orevolution.wearer_" + getTooltipID()).withStyle(ChatFormatting.GREEN));
            for (MobEffect p : this.effectsWearer) {
                lines.add(Component.literal(p.getDisplayName().getString()).withStyle(ChatFormatting.GREEN));
            }
        }
    }

    @Override
    public void onAttacked(LivingEntity wearer, DamageSource source, float amount) {
        LivingEntity attacker = (LivingEntity)source.getEntity();

        if(!getCondition(null, wearer.level(), wearer, attacker)) return;

        if(this.effectsWearer != null) {
            for(MobEffect p : this.effectsWearer) {
                if(wearer.hasEffect(p)) {
                    wearer.getEffect(p).update(new MobEffectInstance(p, this.duration, this.amplifier));
                }
                else {
                    wearer.addEffect(new MobEffectInstance(p, this.duration, this.amplifier));
                }
            }
        }

        if(this.effectsAttacker != null) {
            for(MobEffect p : this.effectsAttacker) {
                if(attacker.hasEffect(p)) {
                    attacker.getEffect(p).update(new MobEffectInstance(p, this.duration, this.amplifier));
                }
                else {
                    attacker.addEffect(new MobEffectInstance(p, this.duration, this.amplifier));
                }
            }
        }
    }
}
