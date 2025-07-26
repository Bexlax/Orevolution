package net.bexla.orevolution.content.data;

import net.bexla.orevolution.content.data.base.OrevolutionArmorMaterial;
import net.bexla.orevolution.content.data.powers.armors.ArmorCauseEffectsOnAttacked;
import net.bexla.orevolution.content.data.powers.armors.ArmorMultiPower;
import net.bexla.orevolution.content.data.utility.OrevolutionArmorTiers;
import net.bexla.orevolution.content.data.utility.OrevolutionLists;

public class OrevolutionArmorMats {
    public static final OrevolutionArmorMaterial PLATINUM = new OrevolutionArmorMaterial(
            OrevolutionLists.PLATINUM_ARMOR_SET,
            OrevolutionArmorTiers.PLATINUM,
            new ArmorCauseEffectsOnAttacked("platinum_on_atacked", 100, 1, OrevolutionLists.PLATINUM_ARMOR_EFFECTS, null)
    );

    public static final OrevolutionArmorMaterial AETHERSTEEL = new OrevolutionArmorMaterial(
            OrevolutionLists.AETHERSTEEL_ARMOR_SET,
            OrevolutionArmorTiers.AETHERSTEEL,
            new ArmorMultiPower(OrevolutionLists.AETHERSTEEL_ARMOR_POWERS)
    );
}
