package net.bexla.orevolution.content.data.powers.tools;

import net.bexla.orevolution.content.data.base.OrevolutionToolPower;
import net.bexla.orevolution.content.data.base.interfaces.Conditional;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

import java.util.List;

public class ToolCauseMultipleEffectsOnHit extends OrevolutionToolPower {
    protected final List<MobEffect> effectTarget;
    protected final List<MobEffect> effectAttacker;
    private final int duration;
    private final int amplifier;

    public ToolCauseMultipleEffectsOnHit(String tooltip_id, Conditional conditional, int duration, int amplifier, List<MobEffect> effectTarget, List<MobEffect> effectAttacker) {
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
            for (MobEffect p : this.effectTarget) {
                lines.add(Component.literal(" - " + p.getDisplayName().getString()).withStyle(ChatFormatting.AQUA));
            }
        }
        if(this.effectAttacker != null) {
            lines.add(Component.translatable("tooltips.orevolution.attacker_" + getTooltipID()).withStyle(ChatFormatting.GREEN));
            for (MobEffect p : this.effectAttacker) {
                lines.add(Component.literal(" - " + p.getDisplayName().getString()).withStyle(ChatFormatting.AQUA));
            }
        }
    }

    @Override
    public void onHitEntity(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        if(!getCondition(stack, null, attacker.level(), attacker, target)) return;

        if(this.effectTarget != null) {
            for(MobEffect p : this.effectTarget) {
                if(target.hasEffect(p)) {
                    target.getEffect(p).update(new MobEffectInstance(p, this.duration, this.amplifier));
                }
                else {
                    target.addEffect(new MobEffectInstance(p, this.duration, this.amplifier));
                }
            } // (only reason i do it like this is because of health boost, idk of other issues of not using update)
        }
        if(this.effectAttacker != null) {
            for(MobEffect p : this.effectAttacker) {
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
