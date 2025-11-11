package net.bexla.orevolution.content.data.powers.armors;

import net.bexla.orevolution.content.types.OrevolutionArmorPower;
import net.bexla.orevolution.content.types.interfaces.Conditional;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

public class ArmorCauseEffectsOnAttacked extends OrevolutionArmorPower {
    private final List<Supplier<MobEffect>> effectsWearer;
    private final List<Supplier<MobEffect>> effectsAttacker;
    private final int duration;
    private final int amplifier;
    private final String tooltip_wearer_id;

    public ArmorCauseEffectsOnAttacked(String tooltip_target_id, String tooltip_wearer_id, Conditional conditional, int duration, int amplifier, List<Supplier<MobEffect>> effectsWearer, List<Supplier<MobEffect>> effectsAttacker) {
        super(tooltip_target_id, conditional);
        this.effectsWearer = effectsWearer;
        this.effectsAttacker = effectsAttacker;
        this.duration = duration;
        this.amplifier = amplifier;
        this.tooltip_wearer_id = tooltip_wearer_id;
    }

    public ArmorCauseEffectsOnAttacked(String tooltip_target_id, String tooltip_wearer_id, Conditional conditional, int duration, int amplifier, Supplier<MobEffect> effectWearer, Supplier<MobEffect> effectAttacker) {
        super(tooltip_target_id, conditional);
        this.effectsWearer = effectWearer != null? List.of(effectWearer) : null;
        this.effectsAttacker = effectAttacker != null? List.of(effectAttacker) : null;
        this.duration = duration;
        this.amplifier = amplifier;
        this.tooltip_wearer_id = tooltip_wearer_id;
    }

    @Override
    public List<Component> appendTooltip(ItemStack stack, Level level, List<Component> lines) {
        List<Component> tips = new ArrayList<>();
        if(this.effectsAttacker != null) {
            tips.add(Component.translatable("tooltip.orevolution." + getTooltipID()).withStyle(ChatFormatting.GREEN));
            for (Supplier<MobEffect> p : this.effectsAttacker) {
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
    public void onAttacked(LivingEntity wearer, DamageSource source, float amount) {
        LivingEntity attacker = (LivingEntity)source.getEntity();

        if(!getCondition(null, wearer.level(), wearer, attacker)) return;

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

        if(this.effectsAttacker != null) {
            for(Supplier<MobEffect> p : this.effectsAttacker) {
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
