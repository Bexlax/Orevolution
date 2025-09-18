package net.bexla.orevolution.content.data;

import net.bexla.orevolution.content.data.base.OrevolutionToolMaterial;
import net.bexla.orevolution.content.data.powers.tools.*;
import net.bexla.orevolution.content.data.powers.tools.specifics.ToolIronPower;
import net.bexla.orevolution.content.data.utility.OrevolutionLists;
import net.bexla.orevolution.content.data.utility.OrevolutionToolTiers;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.MobType;
import net.minecraft.world.item.Tiers;

public class OrevolutionToolMats {
    public static final OrevolutionToolMaterial TIN = new OrevolutionToolMaterial(
            OrevolutionToolTiers.TIN,
            new ToolIncreaseDrops("tin_duplication", Conditionals.always(), 1, 1),
            new ToolCauseEffectOnHit("tin_on_hit", Conditionals.byChance(0.3), 120, 0, MobEffects.POISON, null)
    );

    public static final OrevolutionToolMaterial IRON = new OrevolutionToolMaterial(
            Tiers.IRON,
            new ToolIronPower("iron_mining", Conditionals.always()),
            new ToolCauseEffectOnHit("iron_on_hit", Conditionals.byChance(0.5), 60, 0, null, MobEffects.DAMAGE_BOOST)
    );

    public static final OrevolutionToolMaterial GOLD = new OrevolutionToolMaterial(
            Tiers.GOLD,
            new ToolIronPower("iron_mining", Conditionals.always()),
            new ToolCauseEffectOnHit("iron_on_hit", Conditionals.byChance(0.5), 60, 0, null, MobEffects.DAMAGE_BOOST)
    );

    public static final OrevolutionToolMaterial DIAMOND = new OrevolutionToolMaterial(
            Tiers.DIAMOND,
            new ToolIronPower("iron_mining", Conditionals.always()),
            new ToolCauseEffectOnHit("iron_on_hit", Conditionals.byChance(0.5), 60, 0, null, MobEffects.DAMAGE_BOOST)
    );

    public static final OrevolutionToolMaterial NETHERITE = new OrevolutionToolMaterial(
            Tiers.NETHERITE,
            new ToolIronPower("iron_mining", Conditionals.always()),
            new ToolCauseEffectOnHit("iron_on_hit", Conditionals.byChance(0.5), 60, 0, null, MobEffects.DAMAGE_BOOST)
    );

    public static final OrevolutionToolMaterial STEEL = new OrevolutionToolMaterial(
            OrevolutionToolTiers.STEEL,
            new ToolAvoidDamageOnUse("platinum_avoid_damage", Conditionals.byChance(0.25)),
            new ToolCauseMultipleEffectsOnHit("platinum_on_hit", Conditionals.isTargetMobType(MobType.UNDEAD), 160, 0, OrevolutionLists.PLATINUM_TOOL_EFFECTS, null)
    );

    public static final OrevolutionToolMaterial PLATINUM = new OrevolutionToolMaterial(
            OrevolutionToolTiers.PLATINUM,
            new ToolAvoidDamageOnUse("platinum_avoid_damage", Conditionals.byChance(0.25)),
            new ToolCauseMultipleEffectsOnHit("platinum_on_hit", Conditionals.isTargetMobType(MobType.UNDEAD), 160, 0, OrevolutionLists.PLATINUM_TOOL_EFFECTS, null)
    );

    public static final OrevolutionToolMaterial AETHERSTEEL = new OrevolutionToolMaterial(
            OrevolutionToolTiers.AETHERSTEEL,
            new ToolMultiPower(OrevolutionLists.AETHERSTEEL_TOOL_POWERS),
            new ToolCauseMultipleEffectsOnHit("aethersteel_on_hit", Conditionals.always(), 220, 0, OrevolutionLists.AETHERSTEEL_TOOL_EFFECTS, null)
    );

    public static final OrevolutionToolMaterial LIVINGSTONE = new OrevolutionToolMaterial(
            OrevolutionToolTiers.LIVINGSTONE,
            new ToolIncreaseCropDrops("livingstone_crops", Conditionals.byChance(0.75), 1),
            new ToolCauseEffectOnHit("livingstone_on_hit", Conditionals.byChance(0.35), 120, 0, MobEffects.POISON, null)
    );

    public static final OrevolutionToolMaterial VERDITE = new OrevolutionToolMaterial(
            OrevolutionToolTiers.VERDITE,
            new ToolIncreaseCropDrops("verdite_crops", Conditionals.byChance(0.8), 2),
            new ToolCauseEffectOnHit("verdite_on_hit", Conditionals.byChance(0.35), 120, 0, MobEffects.POISON, null)
    );
}