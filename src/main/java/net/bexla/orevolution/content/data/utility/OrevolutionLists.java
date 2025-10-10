package net.bexla.orevolution.content.data.utility;

import net.bexla.orevolution.content.data.Conditionals;
import net.bexla.orevolution.content.data.powers.armors.ArmorCauseEffectOnHit;
import net.bexla.orevolution.content.data.powers.armors.ArmorCauseMultipleEffectsOnAttacked;
import net.bexla.orevolution.content.data.powers.armors.ArmorGrantImmunityEffects;
import net.bexla.orevolution.content.data.powers.armors.ArmorGrantMultipleEffects;
import net.bexla.orevolution.content.types.base.OrevolutionArmorPower;
import net.minecraft.tags.FluidTags;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffects;

import java.util.List;

public class OrevolutionLists {
    public static final List<MobEffect> PLATINUM_TOOL_EFFECTS = List.of(
            MobEffects.POISON,
            MobEffects.WEAKNESS
    );

    public static final List<MobEffect> LIVINGSTONE_ARMOR_GRANTS_IMMUNITY = List.of(
            MobEffects.HUNGER
    );

    public static final List<MobEffect> VERDITE_ARMOR_GRANTS_IMMUNITY = List.of(
            MobEffects.HUNGER,
            MobEffects.POISON
    );

    public static final List<MobEffect> AETHERSTEEL_ARMOR_GRANTS_EFFECTS = List.of(
            MobEffects.FIRE_RESISTANCE,
            MobEffects.HEALTH_BOOST
    );

    public static final List<MobEffect> AETHERSTEEL_ARMOR_ATTACKED_EFFECTS_WEARER = List.of(
            MobEffects.DAMAGE_BOOST,
            MobEffects.REGENERATION
    );

    public static final List<MobEffect> VERDITE_ARMOR_ATTACKED_EFFECTS_WEARER = List.of(
            MobEffects.REGENERATION
    );

    public static final List<MobEffect> AETHERSTEEL_ARMOR_ATTACKED_EFFECTS_ATTACKER = List.of(
            MobEffects.MOVEMENT_SLOWDOWN
    );

    public static final List<MobEffect> REINFORCED_ARMOR_GRANTS_EFFECTS = List.of(
            MobEffects.FIRE_RESISTANCE,
            MobEffects.HEALTH_BOOST
    );

    public static final List<MobEffect> GOLD_ARMOR_EFFECTS = List.of(
            MobEffects.MOVEMENT_SPEED,
            MobEffects.DIG_SPEED,
            MobEffects.LUCK
    );

    public static final List<OrevolutionArmorPower> REINFORCED_ARMOR_POWERS = List.of(
            new ArmorGrantMultipleEffects("reinforced_netherite_armor", Conditionals.not(Conditionals.isSubmergedInLiquid(FluidTags.LAVA)), 20, 1, REINFORCED_ARMOR_GRANTS_EFFECTS),
            new ArmorCauseEffectOnHit("netherite_armor", Conditionals.isCurrentHPPercentage(0.5f), 20, 0, MobEffects.WITHER, null)
    );

    public static final List<OrevolutionArmorPower> AETHERSTEEL_ARMOR_POWERS = List.of(
            new ArmorGrantMultipleEffects("armor_wearer_grants", Conditionals.always(), 20, 1, AETHERSTEEL_ARMOR_GRANTS_EFFECTS),
            new ArmorCauseMultipleEffectsOnAttacked("aethersteel_armor", Conditionals.always(), 200, 0, AETHERSTEEL_ARMOR_ATTACKED_EFFECTS_WEARER, AETHERSTEEL_ARMOR_ATTACKED_EFFECTS_ATTACKER)
    );

    public static final List<OrevolutionArmorPower> VERDITE_ARMOR_POWERS = List.of(
            new ArmorGrantImmunityEffects("verdite_grants", Conditionals.isReceivingDayLight(), VERDITE_ARMOR_GRANTS_IMMUNITY),
            new ArmorCauseEffectOnHit("verdite_on_hit", Conditionals.isReceivingDayLight(), 200, 0, MobEffects.REGENERATION, null)
    );
}
