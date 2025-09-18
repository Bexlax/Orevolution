package net.bexla.orevolution.content.data;

import net.bexla.orevolution.content.data.interfaces.OrevolutionConditional;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.MobType;
import net.minecraft.world.level.material.Fluid;

public class Conditionals {
    // perchance
    public static OrevolutionConditional byChance(double chance) {
        return (stack, level, player, possibleTarget) -> level.getRandom().nextDouble() < chance;
    }

    public static OrevolutionConditional isTargetMobType(MobType type) {
        return (stack, level, player, possibleTarget) -> possibleTarget != null && possibleTarget.getMobType() == type;
    }

    public static OrevolutionConditional isSubmergedInLiquid(TagKey<Fluid> fluidTag) {
        return (stack, level, player, target) -> player.isEyeInFluid(fluidTag);
    }

    public static OrevolutionConditional isCurrentHPPercentage(float percentage) {
        return (stack, level, player, possibleTarget) -> (player.getHealth() / player.getMaxHealth()) <= percentage;
    }

    public static OrevolutionConditional isCurrentHPAmount(int amount) {
        return (stack, level, player, possibleTarget) -> player.getHealth() <= amount;
    }

    public static OrevolutionConditional isReceivingDayLight() {
        return (stack, level, player, possibleTarget) -> level.canSeeSky(player.blockPosition()) && level.isDay();
    }

    public static OrevolutionConditional and(OrevolutionConditional a, OrevolutionConditional b) {
        return (stack, level, player, possibleTarget) -> a.shouldActivate(stack, level, player, possibleTarget) && b.shouldActivate(stack, level, player, possibleTarget);
    }

    public static OrevolutionConditional or(OrevolutionConditional a, OrevolutionConditional b) {
        return (stack, level, player, possibleTarget) -> a.shouldActivate(stack, level, player, possibleTarget) || b.shouldActivate(stack, level, player, possibleTarget);
    }

    public static OrevolutionConditional not(OrevolutionConditional a) {
        return (stack, level, player, possibleTarget) -> !a.shouldActivate(stack, level, player, possibleTarget);
    }

    public static OrevolutionConditional always() {
        return (stack, level, player, possibleTarget) -> true;
    }
}
