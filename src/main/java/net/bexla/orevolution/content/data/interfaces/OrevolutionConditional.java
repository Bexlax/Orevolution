package net.bexla.orevolution.content.data.interfaces;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

@FunctionalInterface
public interface OrevolutionConditional {
    boolean shouldActivate(ItemStack stack, Level level, LivingEntity player, LivingEntity possibleTarget);
}
