package net.bexla.orevolution.content.data.interfaces;

import net.minecraft.network.chat.Component;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

import java.util.List;

public interface ArmorPower {
    default void onTickWhileWorn(ItemStack stack, LivingEntity wearer, EquipmentSlot slot) {}
    default void onAttacked(LivingEntity wearer, DamageSource source, float amount) {}
    default void onAttackTarget(LivingEntity wearer, LivingEntity target) {}
    default void onKnockback(LivingEntity wearer, float strength, double ratioX, double ratioZ) {}
    default void onFall(LivingEntity wearer, float distance, float damageMultiplier) {}
    default void onDeath(LivingEntity wearer, LivingEntity killer) {}
    default void appendTooltip(ItemStack stack, Level level, List<Component> lines) {}
}