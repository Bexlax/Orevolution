package net.bexla.orevolution.content.data.powers.tools;

import net.bexla.orevolution.content.data.base.OrevolutionToolPower;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.MobType;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

import java.util.List;

public class ToolCauseMultipleEffectsOnHit extends OrevolutionToolPower {
    protected final List<MobEffect> effectTarget;
    protected final List<MobEffect> effectAttacker;
    private final double chance;
    private final MobType entityType;
    private final int duration;
    private final int amplifier;

    public ToolCauseMultipleEffectsOnHit(String tooltip_id, int duration, int amplifier, List<MobEffect> effectTarget, List<MobEffect> effectAttacker) {
        super(tooltip_id);
        this.effectTarget = effectTarget;
        this.effectAttacker = effectAttacker;
        this.duration = duration;
        this.amplifier = amplifier;
        this.chance = 1;
        this.entityType = null;
    }

    public ToolCauseMultipleEffectsOnHit(String tooltip_id, double chance, int duration, int amplifier, List<MobEffect> effectTarget, List<MobEffect> effectAttacker) {
        super(tooltip_id);
        this.effectTarget = effectTarget;
        this.effectAttacker = effectAttacker;
        this.chance = chance;
        this.duration = duration;
        this.amplifier = amplifier;
        this.entityType = null;
    }

    public ToolCauseMultipleEffectsOnHit(String tooltip_id, MobType entityType, int duration, int amplifier, List<MobEffect> effectTarget, List<MobEffect> effectAttacker) {
        super(tooltip_id);
        this.effectTarget = effectTarget;
        this.effectAttacker = effectAttacker;
        this.duration = duration;
        this.amplifier = amplifier;
        this.chance = 1;
        this.entityType = entityType;
    }

    @Override
    public void appendTooltip(ItemStack stack, Level level, List<Component> lines) {
        if(this.effectTarget != null) {
            lines.add(Component.translatable("tooltips.orevolution." + getTooltipID()).withStyle(ChatFormatting.GREEN));
            for (MobEffect p : this.effectTarget) { // check every effect in the list
                lines.add(Component.literal(p.getDisplayName().getString()).withStyle(ChatFormatting.GREEN)); // add a tooltip for each one of them
            }
        }
        if(this.effectAttacker != null) {
            lines.add(Component.translatable("tooltips.orevolution.attacker_" + getTooltipID()).withStyle(ChatFormatting.GREEN));
            for (MobEffect p : this.effectAttacker) { // check every effect in the list
                lines.add(Component.literal(p.getDisplayName().getString()).withStyle(ChatFormatting.GREEN)); // add a tooltip for each one of them
            }
        }
    }

    @Override
    public void onHitEntity(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        if(Math.random() > chance) return; // chance to apply effect, if math.random() returns a number bigger than chance, then return

        if(this.entityType != null) { // verify that entity type isn't null to avoid issues
            if (target.getMobType() != this.entityType) return;
        }

        if(this.effectTarget != null) { // verify effects
            for(MobEffect p : this.effectTarget) { // check every effect in the list
                if(target.hasEffect(p)) { // check if the effect already exists in this entity
                    target.getEffect(p).update(new MobEffectInstance(p, this.duration, this.amplifier)); // if it does, then update the already existing effect
                }
                else {
                    target.addEffect(new MobEffectInstance(p, this.duration, this.amplifier)); // if it doesn't then add it
                }
            }
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

    public int getDuration() {
        return duration;
    }
}
