package net.bexla.orevolution.content.data;

import net.bexla.orevolution.content.data.base.OrevolutionToolMaterial;
import net.bexla.orevolution.content.data.powers.tools.*;
import net.bexla.orevolution.content.data.utility.OrevolutionLists;
import net.bexla.orevolution.content.data.utility.OrevolutionToolTiers;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.MobType;

public class OrevolutionToolMats {
    public static final OrevolutionToolMaterial LIVINGSTONE = new OrevolutionToolMaterial(
            OrevolutionToolTiers.LIVINGSTONE,
            new ToolIncreaseCropDrops("livingstone_crops", 1, 0.5),
            new ToolCauseEffectOnHit("livingstone_on_hit", 0.3, 120, 0, MobEffects.POISON, null)
    );

    public static final OrevolutionToolMaterial TIN = new OrevolutionToolMaterial(
            OrevolutionToolTiers.TIN,
            new ToolIncreaseDrops("tin_duplication", 1, 1),
            new ToolCauseEffectOnHit("tin_on_hit", 0.3, 120, 0, MobEffects.POISON, null)
    );

    public static final OrevolutionToolMaterial PLATINUM = new OrevolutionToolMaterial(
            OrevolutionToolTiers.PLATINUM,
            new ToolAvoidDamageOnUse("platinum_avoid_damage", 0.3),
            new ToolCauseMultipleEffectsOnHit("platinum_on_hit", MobType.UNDEAD, 160, 0, OrevolutionLists.PLATINUM_TOOL_EFFECTS, null)
    );

    public static final OrevolutionToolMaterial AETHERSTEEL = new OrevolutionToolMaterial(
            OrevolutionToolTiers.AETHERSTEEL,
            new ToolMultiPower(OrevolutionLists.AETHERSTEEL_TOOL_POWERS),
            new ToolCauseMultipleEffectsOnHit("aethersteel_on_hit", 220, 0, OrevolutionLists.AETHERSTEEL_TOOL_EFFECTS, null)
    );
}
