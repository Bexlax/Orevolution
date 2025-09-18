package net.bexla.orevolution.content.data;

import net.bexla.orevolution.content.data.base.OrevolutionArmorMaterial;
import net.bexla.orevolution.content.data.powers.armors.*;
import net.bexla.orevolution.content.data.utility.OrevolutionArmorTiers;
import net.bexla.orevolution.content.data.utility.OrevolutionLists;
import net.minecraft.world.item.ArmorMaterials;

public class OrevolutionArmorMats {
    public static final OrevolutionArmorMaterial IRON = new OrevolutionArmorMaterial(
            ArmorMaterials.IRON,
            new ArmorGrantEffects("iron_grants", Conditionals.isCurrentHPPercentage(0.5f), 20, 0, OrevolutionLists.IRON_ARMOR_EFFECTS)
    );

    public static final OrevolutionArmorMaterial GOLD = new OrevolutionArmorMaterial(
            ArmorMaterials.GOLD,
            new ArmorGrantEffects("gold_grants", Conditionals.always(), 20, 1, OrevolutionLists.GOLD_ARMOR_EFFECTS)
    );

    public static final OrevolutionArmorMaterial PLATINUM = new OrevolutionArmorMaterial(
            OrevolutionArmorTiers.PLATINUM,
            new ArmorCauseEffectsOnAttacked("platinum_on_atacked", Conditionals.always(), 100, 1, OrevolutionLists.PLATINUM_ARMOR_EFFECTS, null)
    );

    public static final OrevolutionArmorMaterial DIAMOND = new OrevolutionArmorMaterial(
            ArmorMaterials.DIAMOND,
            new ArmorGrantEffects("diamond_grants", Conditionals.always(), 20, 1, OrevolutionLists.DIAMOND_ARMOR_EFFECTS)
    );

    public static final OrevolutionArmorMaterial NETHERITE = new OrevolutionArmorMaterial(
            ArmorMaterials.NETHERITE,
            new ArmorCauseEffectsOnHit("netherite_on_hit", Conditionals.isCurrentHPPercentage(0.5f), 20, 0, OrevolutionLists.NETHERITE_ARMOR_ATTACKED_EFFECTS, null)
    );

    public static final OrevolutionArmorMaterial REINFORCED_NETHERITE = new OrevolutionArmorMaterial(
            OrevolutionArmorTiers.REINFORCED_NETHERITE,
            new ArmorMultiPower(OrevolutionLists.REINFORCED_ARMOR_POWERS)
    );

    public static final OrevolutionArmorMaterial AETHERSTEEL = new OrevolutionArmorMaterial(
            OrevolutionArmorTiers.AETHERSTEEL,
            new ArmorMultiPower(OrevolutionLists.AETHERSTEEL_ARMOR_POWERS)
    );

    public static final OrevolutionArmorMaterial LIVINGSTONE = new OrevolutionArmorMaterial(
            OrevolutionArmorTiers.LIVINGSTONE,
            new ArmorGrantImmunityEffects("livingstone_grants", Conditionals.isReceivingDayLight(), OrevolutionLists.LIVINGSTONE_ARMOR_GRANTS_IMMUNITY)
    );

    public static final OrevolutionArmorMaterial VERDITE = new OrevolutionArmorMaterial(
            OrevolutionArmorTiers.VERDITE,
            new ArmorMultiPower(OrevolutionLists.VERDITE_ARMOR_POWERS)
    );
}
