package net.bexla.orevolution.content.data.powers.armors;

import net.bexla.orevolution.content.types.OrevolutionArmorPower;
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

public class ArmorCauseEffectsOnHit extends OrevolutionArmorPower {
    private final List<Supplier<MobEffect>> effectsTarget;
    private final List<Supplier<MobEffect>> effectsWearer;
    private final int duration;
    private final int amplifier;
    private final String tooltip_wearer_id;

    public ArmorCauseEffectsOnHit(String tooltip_target_id, String tooltip_wearer_id, Conditional conditional, int duration, int amplifier, List<Supplier<MobEffect>> effectsTarget, List<Supplier<MobEffect>> effectsWearer) {
        super(tooltip_target_id, conditional);
        this.effectsTarget = effectsTarget;
        this.effectsWearer = effectsWearer;
        this.duration = duration;
        this.amplifier = amplifier;
        this.tooltip_wearer_id = tooltip_wearer_id;
    }

    public ArmorCauseEffectsOnHit(String tooltip_target_id, String tooltip_wearer_id, Conditional conditional, int duration, int amplifier, Supplier<MobEffect> effectTarget, Supplier<MobEffect> effectWearer) {
        super(tooltip_target_id, conditional);
        this.effectsTarget = effectTarget != null? List.of(effectTarget) : null;
        this.effectsWearer = effectWearer != null? List.of(effectWearer) : null;
        this.duration = duration;
        this.amplifier = amplifier;
        this.tooltip_wearer_id = tooltip_wearer_id;
    }

    @Override
    public List<Component> appendTooltip(ItemStack stack, Level level, List<Component> lines) {
        List<Component> tips = new ArrayList<>();
        if(this.effectsTarget != null) {
            tips.add(Component.translatable("tooltip.orevolution." + getTooltipID()).withStyle(ChatFormatting.GREEN));
            for (Supplier<MobEffect> p : this.effectsTarget) {
                tips.add(Component.literal(" - " + p.get().getDisplayName().getString() + (this.amplifier > 0 ? " " + Component.translatable("potion.potency." + this.amplifier).getString() : "")).withStyle(ChatFormatting.AQUA));
            }
        }
        if(this.effectsWearer != null) {
            tips.add(Component.translatable("tooltip.orevolution." + tooltip_wearer_id).withStyle(ChatFormatting.GREEN));
            for (Supplier<MobEffect> p : this.effectsWearer) {
                tips.add(Component.literal(" - " + p.get().getDisplayName().getString() + (this.amplifier > 0 ? " " + Component.translatable("potion.potency." + this.amplifier).getString() : "")).withStyle(ChatFormatting.AQUA));
            }
        }
        return tips;
    }

    @Override
    public void onAttackTarget(LivingEntity wearer, LivingEntity target) {
        if(!getCondition(null, wearer.level(), wearer, target)) return;

        if(this.effectsTarget != null) {
            for(Supplier<MobEffect> p : this.effectsTarget) {
                if(target.hasEffect(p.get())) {
                    target.getEffect(p.get()).update(new MobEffectInstance(p.get(), this.duration, this.amplifier));
                }
                else {
                    target.addEffect(new MobEffectInstance(p.get(), this.duration, this.amplifier));
                }
            }
        }

        if(this.effectsWearer != null) {
            for(Supplier<MobEffect> p : this.effectsWearer) {
                if(wearer.hasEffect(p.get())) {
                    wearer.getEffect(p.get()).update(new MobEffectInstance(p.get(), this.duration, this.amplifier));
                }
                else {
                    wearer.addEffect(new MobEffectInstance(p.get(), this.duration, this.amplifier));
                }
            }
        }
    }
}
