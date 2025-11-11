package net.bexla.orevolution.content.types.interfaces;

import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;

import java.util.List;


public interface ToolPower {
    ToolPower EMPTY = new ToolPower() {};

    /** Called when the entity attack an entity with the (tiered) item.
     * @param stack The (tiered) item stack.
     * @param target The target entity that's being attacked.
     * @param attacker The attacking entity that's attacking the target, usually the player.
     */
    default void onHitEntity(ItemStack stack, LivingEntity target, LivingEntity attacker) {}

    /** Called when the entity mines a block with the (tiered) item.
     * @param stack The (tiered) item stack.
     * @param level The level.
     * @param pos The block position.
     * @param entity The entity mining the block.
     * @param state The block state.
     */
    default void onMineBlock(ItemStack stack, Level level, BlockPos pos, LivingEntity entity, BlockState state) {}

    /** Called when the entity has the (tiered) item in inventory (including when held).
     * @param stack The (tiered) item stack.
     * @param level The level.
     * @param entity The entity.
     * @param slot The slot index.
     * @param selected Whether the (tiered) item is selected.
     */
    default void onInventoryTick(ItemStack stack, Level level, Entity entity, int slot, boolean selected) {}

    /** Called when the player hovers the (tiered) item.
     * adds tooltip lines to the (tiered) item.
     * @param stack The (tiered) item stack.
     * @param level The level.
     * @param lines The tooltip lines.
     */
    default List<Component> appendTooltip(ItemStack stack, Level level, List<Component> lines) {
        return List.of();
    }

    /** Called when the entity uses the (tiered) item (mining or attacking).
     * return true to override the default behavior (like mining speed or damage).
     * @param stack The (tiered) item stack.
     * @param level The level.
     * @param player The player using the (tiered) item.
     * @return true to override the default behavior.
     */
    default boolean onUseOverride(ItemStack stack, Level level, LivingEntity player) {return false;}

    /**
     * Sets the destroy speed of the (tiered) item.
     * @param stack The (tiered) item stack.
     * @param state The block state being destroyed.
     * @param defaultSpeed The default destroy speed of the (tiered) item.
     * @return The modified destroy speed.
     */
    default float setDestroySpeed(ItemStack stack, BlockState state, float defaultSpeed) {return defaultSpeed;}

    /**
     * Sets the max durability (uses) of the (tiered) item.
     * @param stack The (tiered) item stack.
     * @param defaultUses The default max durability (uses) of the (tiered) item.
     * @return The modified durability (uses).
     */
    default int setMaxUses(ItemStack stack, int defaultUses) {return defaultUses;}

    /**
     * Sets the attack damage of the item.
     * @param stack The item stack.
     * @param defaultDamage The default attack damage of the item.
     * @return The modified attack damage.
     */
    default float setAttackDamage(ItemStack stack, float defaultDamage) {return defaultDamage;}
}