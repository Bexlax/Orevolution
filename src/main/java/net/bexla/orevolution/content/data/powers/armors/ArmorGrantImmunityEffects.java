package net.bexla.orevolution.content.data.powers.armors;

import net.bexla.orevolution.content.types.base.OrevolutionArmorPower;
import net.bexla.orevolution.content.types.base.interfaces.Conditional;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

import java.util.List;

public class ArmorGrantImmunityEffects extends OrevolutionArmorPower {
    private final List<MobEffect> effects;

    public ArmorGrantImmunityEffects(String tooltipId, Conditional conditional, List<MobEffect> effects) {
        super(tooltipId, conditional);
        this.effects = effects;
    }

    @Override
    public void appendTooltip(ItemStack stack, Level level, List<Component> lines) {
        lines.add(Component.translatable("tooltips.orevolution." + getTooltipID()).withStyle(ChatFormatting.GREEN));
        for (MobEffect p : this.effects) {
            lines.add(Component.literal(" - " + p.getDisplayName().getString()).withStyle(ChatFormatting.AQUA));
        }
    }

    @Override
    public void onTickWhileWorn(ItemStack stack, LivingEntity wearer, EquipmentSlot slot) {
        if(!getCondition(stack, wearer.level(), wearer, null)) return;

        for(MobEffect p : this.effects) {
            wearer.removeEffect(p);
        }
    }
}
