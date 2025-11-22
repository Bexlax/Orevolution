package net.bexla.orevolution.content.data.utility;

import net.bexla.orevolution.content.data.Conditionals;
import net.bexla.orevolution.content.data.powers.armors.ArmorCauseEffectsOnAttacked;
import net.bexla.orevolution.content.data.powers.armors.ArmorCauseEffectsOnHit;
import net.bexla.orevolution.content.data.powers.armors.ArmorGrantEffects;
import net.bexla.orevolution.content.data.powers.armors.ArmorGrantImmunityEffects;
import net.bexla.orevolution.content.data.powers.tools.*;
import net.bexla.orevolution.content.types.power.armor.OrevolutionArmorPower;
import net.bexla.orevolution.content.types.power.tool.OrevolutionToolPower;
import net.minecraft.tags.FluidTags;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffects;

import java.util.List;
import java.util.function.Supplier;

public class OrevolutionLists {
    public static final List<Supplier<MobEffect>> BRONZE_REDSTONE_EFFECTS = List.of(
            () -> MobEffects.DIG_SPEED,
            () -> MobEffects.MOVEMENT_SPEED,
            () -> MobEffects.DOLPHINS_GRACE
    );

    public static final List<Supplier<MobEffect>> PLATINUM_TOOL_EFFECTS = List.of(
            () -> MobEffects.POISON,
            () -> MobEffects.WEAKNESS
    );

    public static final List<Supplier<MobEffect>> VERDITE_ARMOR_GRANTS_IMMUNITY = List.of(
            () -> MobEffects.HUNGER,
            () -> MobEffects.POISON
    );

    public static final List<Supplier<MobEffect>> AETHERSTEEL_ARMOR_GRANTS_EFFECTS = List.of(
            () -> MobEffects.FIRE_RESISTANCE,
            () -> MobEffects.HEALTH_BOOST
    );

    public static final List<Supplier<MobEffect>> AETHERSTEEL_ARMOR_ATTACKED_EFFECTS_WEARER = List.of(
            () -> MobEffects.DAMAGE_BOOST,
            () -> MobEffects.REGENERATION
    );

    public static final List<Supplier<MobEffect>> AETHERSTEEL_ARMOR_ATTACKED_EFFECTS_ATTACKER = List.of(
            () -> MobEffects.MOVEMENT_SLOWDOWN
    );

    public static final List<Supplier<MobEffect>> REINFORCED_ARMOR_GRANTS_EFFECTS = List.of(
            () -> MobEffects.FIRE_RESISTANCE,
            () -> MobEffects.HEALTH_BOOST
    );

    public static final List<Supplier<MobEffect>> GOLD_ARMOR_EFFECTS = List.of(
            () -> MobEffects.MOVEMENT_SPEED,
            () -> MobEffects.DIG_SPEED,
            () -> MobEffects.LUCK
    );

    public static final List<OrevolutionArmorPower> REINFORCED_ARMOR_POWERS = List.of(
            new ArmorGrantEffects("reinforced_netherite_armor", Conditionals.not(Conditionals.isSubmergedInLiquid(FluidTags.LAVA)), 20, 1, REINFORCED_ARMOR_GRANTS_EFFECTS),
            new ArmorCauseEffectsOnHit("netherite_armor", "", Conditionals.isCurrentHPPercentage(0.5f), 20, 0, () -> MobEffects.WITHER, null)
    );

    public static final List<OrevolutionArmorPower> AETHERSTEEL_ARMOR_POWERS = List.of(
            new ArmorGrantEffects("armor_wearer_grants", Conditionals.always(), 20, 1, AETHERSTEEL_ARMOR_GRANTS_EFFECTS),
            new ArmorCauseEffectsOnAttacked("armor_wearer_on_attacked_target", "armor_wearer_on_attacked_wearer", Conditionals.always(), 200, 0, AETHERSTEEL_ARMOR_ATTACKED_EFFECTS_WEARER, AETHERSTEEL_ARMOR_ATTACKED_EFFECTS_ATTACKER)
    );

    public static final List<OrevolutionArmorPower> VERDITE_ARMOR_POWERS = List.of(
            new ArmorGrantImmunityEffects("armor_immunity_daylight", Conditionals.isReceivingDayLight(), VERDITE_ARMOR_GRANTS_IMMUNITY),
            new ArmorCauseEffectsOnHit("", "verdite_armor", Conditionals.isReceivingDayLight(), 200, 0, () -> MobEffects.REGENERATION, null)
    );

    public static final List<OrevolutionToolPower> AETHERSTEEL_TOOL_POWERS = List.of(
            new ToolAvoidDamageOnUse("avoid_damage", Conditionals.byChance(0.03)),
            new ToolIncreaseDrops("triplication", Conditionals.always(), 2, 0.15)
    );
    public static final List<OrevolutionToolPower> AETHERSTEEL_SWORD_POWERS = List.of(
            new ToolCauseEffectOnHit("attacker_on_hit_effect", "", Conditionals.byChance(0.7), 80, 0, () -> MobEffects.REGENERATION, null),
            new ToolIncreaseMobLoot("triplicate_loot", Conditionals.always(), 2F, 0.1)
    );


    public static final List<OrevolutionToolPower> ELECTRUM_POWERS = List.of(
            new ToolAddEffectPerBlockAmount("grant_on_mine", Conditionals.always(), () -> MobEffects.DIG_SPEED, 6, 120, 3),
            new OrevolutionToolPower("electrum", Conditionals.always())
    );
}
