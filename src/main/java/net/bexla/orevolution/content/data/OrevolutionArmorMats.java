package net.bexla.orevolution.content.data;

import net.bexla.orevolution.content.data.base.OrevolutionArmorMaterial;
import net.bexla.orevolution.content.data.powers.armors.ArmorCauseEffectsOnAttacked;
import net.bexla.orevolution.content.data.powers.armors.ArmorGrantEffects;
import net.bexla.orevolution.content.data.powers.armors.ArmorMultiPower;
import net.bexla.orevolution.content.data.utility.OrevolutionArmorTiers;
import net.bexla.orevolution.content.data.utility.OrevolutionLists;
import net.minecraft.world.item.ArmorMaterials;

public class OrevolutionArmorMats {
    public static final OrevolutionArmorMaterial IRON = new OrevolutionArmorMaterial(
            ArmorMaterials.IRON,
            new ArmorGrantEffects("iron_grants", 20, 0, OrevolutionLists.IRON_ARMOR_EFFECTS)
    );

    public static final OrevolutionArmorMaterial PLATINUM = new OrevolutionArmorMaterial(
            OrevolutionArmorTiers.PLATINUM,
            new ArmorCauseEffectsOnAttacked("platinum_on_atacked", 100, 1, OrevolutionLists.PLATINUM_ARMOR_EFFECTS, null)
    );

    public static final OrevolutionArmorMaterial AETHERSTEEL = new OrevolutionArmorMaterial(
            OrevolutionArmorTiers.AETHERSTEEL,
            new ArmorMultiPower(OrevolutionLists.AETHERSTEEL_ARMOR_POWERS)
    );
}
