package net.bexla.orevolution.init;

import com.mojang.logging.LogUtils;
import net.bexla.orevolution.content.data.OrevolutionArmorMats;
import net.bexla.orevolution.content.data.OrevolutionToolMats;
import net.bexla.orevolution.content.types.ArmorPowerRegistry;
import net.bexla.orevolution.content.types.ExternalToolsPowerRegistry;
import org.slf4j.Logger;

public class RegPowers {
    private static final Logger LOGGER = LogUtils.getLogger();

    public static void RegisterArmorPowers() {
        // Needed both for vanilla and modded.
        ArmorPowerRegistry.register(OrevolutionArmorMats.IRON);
        ArmorPowerRegistry.register(OrevolutionArmorMats.GOLD);
        ArmorPowerRegistry.register(OrevolutionArmorMats.PLATINUM);
        ArmorPowerRegistry.register(OrevolutionArmorMats.DIAMOND);
        ArmorPowerRegistry.register(OrevolutionArmorMats.NETHERITE);
        ArmorPowerRegistry.register(OrevolutionArmorMats.REINFORCED_NETHERITE);
        ArmorPowerRegistry.register(OrevolutionArmorMats.AETHERSTEEL);

        ArmorPowerRegistry.register(OrevolutionArmorMats.LIVINGSTONE);
        ArmorPowerRegistry.register(OrevolutionArmorMats.VERDITE);

        LOGGER.debug("Registered all armor powers");
    }

    // todo: finish register of tools powers
    public static void RegisterToolsPowers() {
        // Mostly used for vanilla, can also be used to register powers for external mods without needing full dependency
        ExternalToolsPowerRegistry.register(OrevolutionToolMats.IRON);

        LOGGER.debug("Registered all tool powers");
    }
}
