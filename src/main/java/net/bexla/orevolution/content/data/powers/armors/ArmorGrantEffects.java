package net.bexla.orevolution.content.data.powers.armors;

import net.bexla.orevolution.content.data.base.OrevolutionArmorPower;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

import java.util.List;

public class ArmorGrantEffects extends OrevolutionArmorPower {
    private final List<MobEffect> effects;
    private final int duration;
    private final int amplifier;

    public ArmorGrantEffects(String tooltipId, int duration, int amplifier, List<MobEffect> effect) {
        super(tooltipId);
        this.effects = effect;
        this.duration = duration;
        this.amplifier = amplifier;
    }

    @Override
    public void appendTooltip(ItemStack stack, Level level, List<Component> lines) {
        lines.add(Component.translatable("tooltips.orevolution." + getTooltipID()).withStyle(ChatFormatting.GREEN));
        for (MobEffect p : this.effects) { // check every effect in the list
            lines.add(Component.literal(p.getDisplayName().getString()).withStyle(ChatFormatting.GREEN)); // add a tooltip for each one of them
        }
    }

    @Override
    public void onTickWhileWorn(ItemStack stack, LivingEntity wearer, EquipmentSlot slot) {
        for(MobEffect p : this.effects) {
            if(wearer.hasEffect(p)) {
                wearer.getEffect(p).update(new MobEffectInstance(p, this.duration, this.amplifier));
            }
            else {
                wearer.addEffect(new MobEffectInstance(p, this.duration, this.amplifier));
            }
        }
    }
}
