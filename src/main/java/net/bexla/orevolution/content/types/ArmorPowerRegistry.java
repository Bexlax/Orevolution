package net.bexla.orevolution.content.types;

import com.mojang.logging.LogUtils;
import net.bexla.orevolution.content.data.base.OrevolutionArmorMaterial;
import net.bexla.orevolution.content.data.interfaces.ArmorPower;
import net.minecraft.world.item.ArmorMaterial;
import org.slf4j.Logger;

import java.util.HashMap;
import java.util.Map;

public class ArmorPowerRegistry {
    private static final Logger LOGGER = LogUtils.getLogger();
    private static final Map<ArmorMaterial, ArmorPower> POWER_MAP = new HashMap<>();

    public static void register(OrevolutionArmorMaterial material) {
        if (POWER_MAP.containsKey(material.getTier())) {
            LOGGER.error("Found duplicate registration for armor material: {}, stopping duplicate registration", material.getTier());
            return;
        }

        POWER_MAP.put(material.getTier(), material.getArmorPowers());
        LOGGER.debug("Registered power for: ${}", material);
    }

    public static ArmorPower getPower(ArmorMaterial material) {
        return POWER_MAP.get(material);
    }
}