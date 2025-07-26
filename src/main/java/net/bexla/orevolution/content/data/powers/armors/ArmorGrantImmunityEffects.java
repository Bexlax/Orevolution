package net.bexla.orevolution.content.data.powers.armors;

import net.bexla.orevolution.content.data.base.OrevolutionArmorPower;
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

    public ArmorGrantImmunityEffects(String tooltipId, List<MobEffect> effects) {
        super(tooltipId);
        this.effects = effects;
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
            wearer.removeEffect(p);
        }
    }
}
