package net.bexla.orevolution.content.init;

import net.bexla.orevolution.content.data.OrevolutionArmorMats;
import net.bexla.orevolution.content.data.OrevolutionToolMats;
import net.bexla.orevolution.content.types.ArmorPowerRegistry;
import net.bexla.orevolution.content.types.ExternalToolsPowerRegistry;

import static net.bexla.orevolution.OrevolutionConfig.armorsPowers;
import static net.bexla.orevolution.OrevolutionConfig.toolsPowers;

public class RegPowers {
    public static void RegisterArmorPowers() {
        if(armorsPowers) {
            ArmorPowerRegistry.register(OrevolutionArmorMats.IRON);
            ArmorPowerRegistry.register(OrevolutionArmorMats.PLATINUM);
            ArmorPowerRegistry.register(OrevolutionArmorMats.AETHERSTEEL);
        }
    }

    public static void RegisterToolsPowers() {
        if(toolsPowers) {
            ExternalToolsPowerRegistry.register(OrevolutionToolMats.IRON);
        }
    }
}
