package net.bexla.orevolution.content.data.powers.armors.hardcoded;

import net.bexla.orevolution.content.data.Conditionals;
import net.bexla.orevolution.content.types.power.armor.OrevolutionArmorPower;
import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

import java.util.ArrayList;
import java.util.List;

public class ArmorElectrum extends OrevolutionArmorPower {
    public ArmorElectrum(String tooltip_id) {
        super(tooltip_id, Conditionals.always());
    }

    @Override
    public List<Component> appendTooltip(ItemStack stack, Level level, List<Component> lines) {
        List<Component> tips = new ArrayList<>();
        tips.add(Component.translatable("tooltip.orevolution." + getTooltipID()).withStyle(ChatFormatting.GREEN));
        tips.add(Component.literal(" - " + MobEffects.MOVEMENT_SPEED.getDisplayName().getString()).withStyle(ChatFormatting.AQUA));

        if(Screen.hasControlDown()) {
            tips.add(Component.translatable("tooltip.orevolution." + getTooltipID() + "_explanation", MobEffects.MOVEMENT_SPEED.getDisplayName().getString()).withStyle(ChatFormatting.DARK_GRAY));
        }
        else {
            tips.add(Component.translatable("tooltip.orevolution.press_key", Component.translatable("key.keyboard.left.control").getString()).withStyle(ChatFormatting.DARK_GRAY));
        }

        tips.add(Component.literal(""));

        return tips;
    }



    private double distanceTraveled = 0;

    @Override
    public void onTickWhileWorn(ItemStack stack, LivingEntity wearer, EquipmentSlot slot) {
        if(!getCBoolean(stack, wearer.level(), wearer, null)) return;

        if(!(wearer instanceof Player player)) return;

        double val = player.walkDist - player.walkDistO;

        distanceTraveled += Math.sqrt(val * val);

        if (distanceTraveled >= 160) {
            distanceTraveled = 160;
            player.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 100, 2, true, false));
        } else if (distanceTraveled >= 90) {
            player.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 160, 1, true, false));
        } else if (distanceTraveled >= 50) {
            player.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 200, 0, true, false));
        }
    }

    @Override
    public void onAttacked(LivingEntity wearer, DamageSource source, float amount) {
        distanceTraveled = 0;
        wearer.removeEffect(MobEffects.MOVEMENT_SPEED);
    }
}
