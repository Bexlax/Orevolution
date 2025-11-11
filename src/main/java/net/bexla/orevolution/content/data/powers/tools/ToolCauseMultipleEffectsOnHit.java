package net.bexla.orevolution.content.data.powers.tools;

import net.bexla.orevolution.content.types.OrevolutionToolPower;
import net.bexla.orevolution.content.types.interfaces.Conditional;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

public class ToolCauseMultipleEffectsOnHit extends OrevolutionToolPower {
    protected final List<Supplier<MobEffect>> effectTarget;
    protected final List<Supplier<MobEffect>> effectAttacker;
    private final int duration;
    private final int amplifier;
    private final String tooltip_attacker_id;

    public ToolCauseMultipleEffectsOnHit(String tooltip_target_id, String tooltip_attacker_id, Conditional conditional, int duration, int amplifier, List<Supplier<MobEffect>> effectTarget, List<Supplier<MobEffect>> effectAttacker) {
        super(tooltip_target_id, conditional);
        this.tooltip_attacker_id = tooltip_attacker_id;
        this.effectTarget = effectTarget;
        this.effectAttacker = effectAttacker;
        this.duration = duration;
        this.amplifier = amplifier;
    }

    @Override
    public List<Component> appendTooltip(ItemStack stack, Level level, List<Component> lines) {
        List<Component> tips = new ArrayList<>();
        if(this.effectTarget != null) {
            tips.add(Component.translatable("tooltip.orevolution." + getTooltipID()).withStyle(ChatFormatting.GREEN));
            for (Supplier<MobEffect> p : this.effectTarget) {
                tips.add(Component.literal(" - " + p.get().getDisplayName().getString() + (this.amplifier > 0 ? Component.translatable("potion.potency." + this.amplifier).toString() : "")).withStyle(ChatFormatting.AQUA));
            }
        }
        if(this.effectAttacker != null) {
            tips.add(Component.translatable("tooltip.orevolution." + tooltip_attacker_id).withStyle(ChatFormatting.GREEN));
            for (Supplier<MobEffect> p : this.effectAttacker) {
                tips.add(Component.literal(" - " + p.get().getDisplayName().getString() + (this.amplifier > 0 ? Component.translatable("potion.potency." + this.amplifier).toString() : "")).withStyle(ChatFormatting.AQUA));
            }
        }
        return tips;
    }

    @Override
    public void onHitEntity(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        if(!getCondition(stack, null, attacker.level(), attacker, target)) return;

        if(this.effectTarget != null) {
            for(Supplier<MobEffect> p : this.effectTarget) {
                if(target.hasEffect(p.get())) {
                    target.getEffect(p.get()).update(new MobEffectInstance(p.get(), this.duration, this.amplifier));
                }
                else {
                    target.addEffect(new MobEffectInstance(p.get(), this.duration, this.amplifier));
                }
            } // (only reason i do it like this is because of health boost, idk of other issues of not using update)
        }
        if(this.effectAttacker != null) {
            for(Supplier<MobEffect> p : this.effectAttacker) {
                if(attacker.hasEffect(p.get())) {
                    attacker.getEffect(p.get()).update(new MobEffectInstance(p.get(), this.duration, this.amplifier));
                }
                else {
                    attacker.addEffect(new MobEffectInstance(p.get(), this.duration, this.amplifier));
                }
            }
        }
    }
}
