package net.bexla.orevolution.content.data.powers.tools;

import net.bexla.orevolution.content.data.base.OrevolutionToolPower;
import net.bexla.orevolution.content.data.interfaces.OrevolutionConditional;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

import java.util.List;

public class ToolCauseEffectOnHit extends OrevolutionToolPower {
    private final MobEffect effectTarget;
    private final MobEffect effectAttacker;
    private final int duration;
    private final int amplifier;

    public ToolCauseEffectOnHit(String tooltip_id, OrevolutionConditional conditional, int duration, int amplifier, MobEffect effectTarget, MobEffect effectAttacker) {
        super(tooltip_id, conditional);
        this.effectTarget = effectTarget;
        this.effectAttacker = effectAttacker;
        this.duration = duration;
        this.amplifier = amplifier;
    }

    @Override
    public void appendTooltip(ItemStack stack, Level level, List<Component> lines) {
        if(this.effectTarget != null) {
            lines.add(Component.translatable("tooltips.orevolution." + getTooltipID()).withStyle(ChatFormatting.GREEN));
            lines.add(Component.literal(this.effectTarget.getDisplayName().getString()).withStyle(ChatFormatting.GREEN));
        }
        if(this.effectAttacker != null) {
            lines.add(Component.translatable("tooltips.orevolution.attacker_" + getTooltipID()).withStyle(ChatFormatting.GREEN));
            lines.add(Component.literal(this.effectAttacker.getDisplayName().getString()).withStyle(ChatFormatting.GREEN));
        }
    }

    @Override
    public void onHitEntity(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        if(!getCondition(stack, attacker.level(), attacker, target)) return; // Get the condition assigned to the power. If condition didn't return a true value, then don't do anything else.

        if(this.effectTarget != null) {
            if(target.hasEffect(this.effectTarget)) {
                target.getEffect(this.effectTarget).update(new MobEffectInstance(this.effectTarget, this.duration, this.amplifier));
            }
            else {
                target.addEffect(new MobEffectInstance(this.effectTarget, this.duration, this.amplifier));
            }
        }
        if(this.effectAttacker != null) {
            if(attacker.hasEffect(this.effectAttacker)) {
                target.getEffect(this.effectTarget).update(new MobEffectInstance(this.effectTarget, duration, amplifier));
            }
            else {
                target.addEffect(new MobEffectInstance(this.effectTarget, this.duration, this.amplifier));
            }
        }
    }
}
