package net.bexla.orevolution.content.types.base.interfaces;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;

@FunctionalInterface
public interface Conditional {
    boolean shouldActivate(ItemStack stack, BlockState state, Level level, LivingEntity player, LivingEntity possibleTarget);
}
