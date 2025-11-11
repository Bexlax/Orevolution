package net.bexla.orevolution.content.data.powers.armors;

import net.bexla.orevolution.content.types.OrevolutionArmorPower;
import net.bexla.orevolution.content.types.interfaces.Conditional;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

public class ArmorGrantEffects extends OrevolutionArmorPower {
    private final List<Supplier<MobEffect>> effects;
    private final int duration;
    private final int amplifier;

    public ArmorGrantEffects(String tooltipId, Conditional conditional, int duration, int amplifier, List<Supplier<MobEffect>> effect) {
        super(tooltipId, conditional);
        this.effects = effect;
        this.duration = duration;
        this.amplifier = amplifier;
    }

    public ArmorGrantEffects(String tooltipId, Conditional conditional, int duration, int amplifier, Supplier<MobEffect> effect) {
        super(tooltipId, conditional);
        this.effects = effect != null? List.of(effect) : null;
        this.duration = duration;
        this.amplifier = amplifier;
    }

    @Override
    public List<Component> appendTooltip(ItemStack stack, Level level, List<Component> lines) {
        List<Component> tips = new ArrayList<>();
        tips.add(Component.translatable("tooltip.orevolution." + getTooltipID()).withStyle(ChatFormatting.GREEN));
        for (Supplier<MobEffect> p : this.effects) {
            tips.add(Component.literal(" - " + p.get().getDisplayName().getString() + (this.amplifier > 0 ? " " + Component.translatable("potion.potency." + this.amplifier).getString() : "")).withStyle(ChatFormatting.AQUA));
        }
        return tips;
    }

    @Override
    public void onTickWhileWorn(ItemStack stack, LivingEntity wearer, EquipmentSlot slot) {
        if(!getCondition(stack, wearer.level(), wearer, null)) return;

        for(Supplier<MobEffect> p : this.effects) {
            if(wearer.hasEffect(p.get())) {
                wearer.getEffect(p.get()).update(new MobEffectInstance(p.get(), this.duration, this.amplifier));
            }
            else {
                wearer.addEffect(new MobEffectInstance(p.get(), this.duration, this.amplifier));
            }
        }
    }
}
