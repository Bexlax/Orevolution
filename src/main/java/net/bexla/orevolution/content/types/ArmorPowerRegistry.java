package net.bexla.orevolution.content.types;

import com.mojang.logging.LogUtils;
import net.bexla.orevolution.content.types.base.interfaces.ArmorPower;
import net.minecraft.world.item.ArmorMaterial;
import org.slf4j.Logger;

import java.util.HashMap;
import java.util.Map;

public class ArmorPowerRegistry {
    private static final Logger LOGGER = LogUtils.getLogger();
    private static final Map<ArmorMaterial, ArmorPower> POWER_MAP = new HashMap<>();

    public static void register(ArmorMaterial material, ArmorPower power) {
        if (POWER_MAP.containsKey(material)) {
            LOGGER.error("Found duplicate registration for armor material: {}, stopping duplicate registration", material);
            return;
        }

        POWER_MAP.put(material, power);
        LOGGER.debug("Registered power for: ${}", material);
    }

    public static ArmorPower getPower(ArmorMaterial material) {
        ArmorPower power = POWER_MAP.get(material);
        return power != null? POWER_MAP.get(material) : ArmorPower.EMPTY;
    }
}