package net.bexla.orevolution.content.data;

import net.bexla.orevolution.content.types.interfaces.Conditional;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.MobType;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.material.Fluid;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

/** A utility class providing various predefined conditionals for use in the Orevolution mod.
 * These conditionals can be used to determine whether certain effects or powers should activate
 * based on specific criteria such as chance, entity type, player health, and environmental conditions.
 */
public class Conditionals {
    /** Conditional that activates based on a given chance (0.0 to 1.0).
     *
     * @param chance The chance (between 0.0 and 1.0) for the conditional to activate.
     * @return A Conditional that activates based on the specified chance.
     */
    @Contract(pure = true)
    public static @NotNull Conditional byChance(double chance) {
        return (stack, state, level, player, possibleTarget) -> level.getRandom().nextDouble() < chance;
    }

    /** Conditional that activates if the target entity's current health percentage is less than or equal to the specified percentage.
     *
     * @param percentage The health percentage threshold (between 0.0 and 1.0).
     * @return A Conditional that activates if the target entity's health percentage is below or equal to the specified value.
     */
    @Contract(pure = true)
    public static @NotNull Conditional isTargetHPPercentage(float percentage) {
        return (stack, state, level, player, possibleTarget) -> possibleTarget != null && (possibleTarget.getHealth() / possibleTarget.getMaxHealth()) <= percentage;
    }

    /** Returns a conditional that activates if the target entity's current health amount is less than or equal to the specified amount.
     *
     * @param amount The health amount threshold.
     * @return A Conditional that activates if the target entity's health amount is below or equal to the specified value.
     */
    @Contract(pure = true)
    public static @NotNull Conditional isTargetHPAmount(float amount) {
        return (stack, state, level, player, possibleTarget) -> possibleTarget != null && possibleTarget.getHealth() <= amount;
    }

    /** Conditional that activates if the target entity is of the specified MobType.
     *
     * @param type The MobType to check against the target entity.
     * @return A Conditional that activates if the target entity matches the specified MobType.
     */
    @Contract(pure = true)
    public static @NotNull Conditional isTargetMobType(MobType type) {
        return (stack, state, level, player, possibleTarget) -> possibleTarget != null && possibleTarget.getMobType() == type;
    }

    /** Conditional that activates if the player is submerged in a fluid matching the specified fluid tag.
     *
     * @param fluidTag The TagKey of the fluid to check for submersion.
     * @return A Conditional that activates if the player is submerged in the specified fluid.
     */
    @Contract(pure = true)
    public static @NotNull Conditional isSubmergedInLiquid(TagKey<Fluid> fluidTag) {
        return (stack, state, level, player, target) -> player.isEyeInFluid(fluidTag);
    }

    /** Conditional that activates if the player's current health percentage is less than or equal to the specified percentage.
     *
     * @param percentage The health percentage threshold (between 0.0 and 1.0).
     * @return A Conditional that activates if the player's health percentage is below or equal to the specified value.
     */
    @Contract(pure = true)
    public static @NotNull Conditional isCurrentHPPercentage(float percentage) {
        return (stack, state, level, player, possibleTarget) -> (player.getHealth() / player.getMaxHealth()) <= percentage;
    }

    /** Conditional that activates if the item has the specified enchantment
     *
     * @param enchantment The enchantment required
     * @return A Conditional that activates if the item has the specified enchantment
     */
    @Contract(pure = true)
    public static @NotNull Conditional toolHasEnchantment(Enchantment enchantment) {
        return (stack, state, level, player, possibleTarget) -> !stack.getAllEnchantments().isEmpty() && stack.getAllEnchantments().containsKey(enchantment);
    }

    /** Conditional that activates if the item has the specified enchantment
     */
    @Contract(pure = true)
    public static @NotNull Conditional toolHasAnyEnchantment() {
        return (stack, state, level, player, possibleTarget) -> !stack.getAllEnchantments().isEmpty();
    }

    /** Returns a conditional that activates if the player's current health amount is less than or equal to the specified amount.
     *
     * @param amount The health amount threshold.
     * @return A Conditional that activates if the player's health amount is below or equal to the specified value.
     */
    @Contract(pure = true)
    public static @NotNull Conditional isCurrentHPAmount(int amount) {
        return (stack, state, level, player, possibleTarget) -> player.getHealth() <= amount;
    }

    /** Returns a conditional that activates if the player is currently receiving daylight
     *
     * @return A Conditional that activates if the player can see the sky and is currently daytime.
     */
    @Contract(pure = true)
    public static @NotNull Conditional isReceivingDayLight() {
        return (stack, state, level, player, possibleTarget) -> level.canSeeSky(player.blockPosition()) && level.isDay();
    }

    /** Returns a conditional that activates if the blockstate matches the specified block tag.
     *
     * @param blockTag The TagKey of the block to check for submersion.
     * @return A Conditional that activates if the player is submerged in the specified fluid.
     */
    @Contract(pure = true)
    public static @NotNull Conditional isBlockstateTaggedAs(TagKey<Block> blockTag) {
        return (stack, state, level, player, possibleTarget) -> state != null && state.is(blockTag);
    }

    /** Combines two OrevolutionConditionals with a logical AND operation.
     *
     * @param a The first Conditional.
     * @param b The second Conditional.
     * @return A Conditional that activates only if both input conditionals activate.
     */
    @Contract(pure = true)
    public static @NotNull Conditional and(Conditional a, Conditional b) {
        return (stack, state, level, player, possibleTarget) -> a.shouldActivate(stack, state, level, player, possibleTarget) && b.shouldActivate(stack, state, level, player, possibleTarget);
    }

    /** Combines two OrevolutionConditionals with a logical OR operation.
     *
     * @param a The first Conditional.
     * @param b The second Conditional.
     * @return A Conditional that activates if either input conditional activates.
     */
    @Contract(pure = true)
    public static @NotNull Conditional or(Conditional a, Conditional b) {
        return (stack, state, level, player, possibleTarget) -> a.shouldActivate(stack, state, level, player, possibleTarget) || b.shouldActivate(stack, state, level, player, possibleTarget);
    }

    @Contract(pure = true)
    public static @NotNull Conditional listConditionals(Conditional... conditionals) {
        for(Conditional conditional : conditionals) {
            return conditional;
        }
        return (stack, state, level, player, possibleTarget) -> false;
    }

    /** Negates a Conditional with a logical NOT operation.
     *
     * @param a The Conditional to negate.
     * @return A Conditional that activates if the input conditional does not activate.
     */
    @Contract(pure = true)
    public static @NotNull Conditional not(Conditional a) {
        return (stack, state, level, player, possibleTarget) -> !a.shouldActivate(stack, state, level, player, possibleTarget);
    }

    /** Conditional that always activates.
     */
    @Contract(pure = true)
    public static @NotNull Conditional always() {
        return (stack, state, level, player, possibleTarget) -> true;
    }
}