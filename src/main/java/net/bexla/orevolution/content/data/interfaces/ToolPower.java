package net.bexla.orevolution.content.data.interfaces;

import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;

import java.util.List;


public interface ToolPower {
    default void onHitEntity(ItemStack stack, LivingEntity target, LivingEntity attacker) {}
    default void onMineBlock(ItemStack stack, Level level, BlockPos pos, Player player, BlockState state) {}
    default boolean onUseOverride(ItemStack stack, Level level, LivingEntity player) {return false;}
    default void onInventoryTick(ItemStack stack, Level level, Entity entity, int slot, boolean selected) {}
    default void appendTooltip(ItemStack stack, Level level, List<Component> lines) {}

    String getTooltipID();
}