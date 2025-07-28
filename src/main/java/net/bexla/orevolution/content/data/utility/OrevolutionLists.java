package net.bexla.orevolution.content.data.utility;

import net.bexla.orevolution.content.data.Conditionals;
import net.bexla.orevolution.content.data.base.OrevolutionArmorPower;
import net.bexla.orevolution.content.data.base.OrevolutionToolPower;
import net.bexla.orevolution.content.data.powers.armors.ArmorCauseEffectsOnAttacked;
import net.bexla.orevolution.content.data.powers.armors.ArmorGrantEffects;
import net.bexla.orevolution.content.data.powers.tools.ToolAvoidDamageOnUse;
import net.bexla.orevolution.content.data.powers.tools.ToolIncreaseDrops;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffects;

import java.util.List;

public class OrevolutionLists {
    public static final List<MobEffect> PLATINUM_TOOL_EFFECTS = List.of(
            MobEffects.POISON,
            MobEffects.WEAKNESS
    );

    public static final List<MobEffect> AETHERSTEEL_TOOL_EFFECTS = List.of(
            MobEffects.POISON,
            MobEffects.WEAKNESS
    );

    public static final List<MobEffect> AETHERSTEEL_ARMOR_GRANTS_EFFECTS = List.of(
            MobEffects.FIRE_RESISTANCE,
            MobEffects.HEALTH_BOOST
    );

    public static final List<MobEffect> AETHERSTEEL_ARMOR_ATTACKED_EFFECTS_WEARER = List.of(
            MobEffects.DAMAGE_BOOST,
            MobEffects.REGENERATION
    );

    public static final List<MobEffect> AETHERSTEEL_ARMOR_ATTACKED_EFFECTS_ATTACKER = List.of(
            MobEffects.MOVEMENT_SLOWDOWN
    );

    public static final List<MobEffect> PLATINUM_ARMOR_EFFECTS = List.of(
            MobEffects.DAMAGE_BOOST
    );

    public static final List<MobEffect> IRON_ARMOR_EFFECTS = List.of(
            MobEffects.DAMAGE_RESISTANCE
    );

    public static final List<OrevolutionToolPower> AETHERSTEEL_TOOL_POWERS = List.of(
            new ToolIncreaseDrops("aethersteel_duplication", Conditionals.always(), 1, 0.5),
            new ToolAvoidDamageOnUse("aethersteel_avoid_damage", Conditionals.byChance(0.2))
    );

    public static final List<OrevolutionArmorPower> AETHERSTEEL_ARMOR_POWERS = List.of(
            new ArmorGrantEffects("aethersteel_grants", 20, 1, AETHERSTEEL_ARMOR_GRANTS_EFFECTS),
            new ArmorCauseEffectsOnAttacked("aethersteel_on_attacked", 200, 0, AETHERSTEEL_ARMOR_ATTACKED_EFFECTS_WEARER, AETHERSTEEL_ARMOR_ATTACKED_EFFECTS_ATTACKER)
    );
}
