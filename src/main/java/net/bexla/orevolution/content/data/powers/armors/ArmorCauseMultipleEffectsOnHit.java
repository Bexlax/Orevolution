package net.bexla.orevolution.content.data.powers.armors;

import net.bexla.orevolution.content.data.base.OrevolutionArmorPower;
import net.bexla.orevolution.content.data.base.interfaces.Conditional;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

import java.util.List;

public class ArmorCauseMultipleEffectsOnHit extends OrevolutionArmorPower {
    private final List<MobEffect> effectsTarget;
    private final List<MobEffect> effectsWearer;
    private final int duration;
    private final int amplifier;

    public ArmorCauseMultipleEffectsOnHit(String tooltipId, Conditional conditional, int duration, int amplifier, List<MobEffect> effectsTarget, List<MobEffect> effectsWearer) {
        super(tooltipId, conditional);
        this.effectsTarget = effectsTarget;
        this.effectsWearer = effectsWearer;
        this.duration = duration;
        this.amplifier = amplifier;
    }

    @Override
    public void appendTooltip(ItemStack stack, Level level, List<Component> lines) {
        if(this.effectsTarget != null) {
            lines.add(Component.translatable("tooltips.orevolution." + getTooltipID()).withStyle(ChatFormatting.GREEN));
            for (MobEffect p : this.effectsTarget) {
                lines.add(Component.literal(" - " + p.getDisplayName().getString()).withStyle(ChatFormatting.AQUA));
            }
        }
        if(this.effectsWearer != null) {
            lines.add(Component.translatable("tooltips.orevolution.wearer_" + getTooltipID()).withStyle(ChatFormatting.GREEN));
            for (MobEffect p : this.effectsWearer) {
                lines.add(Component.literal(" - " + p.getDisplayName().getString()).withStyle(ChatFormatting.AQUA));
            }
        }
    }

    @Override
    public void onAttackTarget(LivingEntity wearer, LivingEntity target) {
        if(!getCondition(null, wearer.level(), wearer, target)) return;

        if(this.effectsTarget != null) {
            for(MobEffect p : this.effectsTarget) {
                if(target.hasEffect(p)) {
                    target.getEffect(p).update(new MobEffectInstance(p, this.duration, this.amplifier));
                }
                else {
                    target.addEffect(new MobEffectInstance(p, this.duration, this.amplifier));
                }
            }
        }

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
    }
}
