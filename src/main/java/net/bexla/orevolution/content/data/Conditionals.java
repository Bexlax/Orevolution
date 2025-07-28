package net.bexla.orevolution.content.data;

import net.bexla.orevolution.content.data.interfaces.OrevolutionConditional;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.MobType;

public class Conditionals {
    public static OrevolutionConditional byChance(double chance) {
        return (stack, level, player, possibleTarget) -> level.getRandom().nextDouble() < chance;
    }

    public static OrevolutionConditional isMobType(MobType type) {
        return (stack, level, player, possibleTarget) -> {
            if (possibleTarget instanceof LivingEntity living) {
                return living.getMobType() == type;
            }
            return false;
        };
    }

    public static OrevolutionConditional isDay() {
        return (stack, level, player, possibleTarget) -> level.isDay();
    }

    public static OrevolutionConditional and(OrevolutionConditional a, OrevolutionConditional b) {
        return (stack, level, player, possibleTarget) -> a.shouldActivate(stack, level, player, possibleTarget) && b.shouldActivate(stack, level, player, possibleTarget);
    }

    public static OrevolutionConditional or(OrevolutionConditional a, OrevolutionConditional b) {
        return (stack, level, player, possibleTarget) -> a.shouldActivate(stack, level, player, possibleTarget) || b.shouldActivate(stack, level, player, possibleTarget);
    }

    public static OrevolutionConditional always() {
        return (stack, level, player, possibleTarget) -> true;
    }

    public static OrevolutionConditional never() {
        return (stack, level, player, possibleTarget) -> false;
    }
}
