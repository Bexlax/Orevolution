package net.bexla.orevolution.content.types;

import com.mojang.logging.LogUtils;
import net.bexla.orevolution.content.types.interfaces.ArmorPower;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.Item;
import org.slf4j.Logger;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

public class ArmorPowerRegistry {
    private static final Logger LOGGER = LogUtils.getLogger();
    private static final Map<ArmorMaterial, ArmorPower> POWER_MAP = new HashMap<>();

    private static final Map<Supplier<Item>, ArmorPower> SINGULAR_POWER_MAP = new HashMap<>();

    public static void register(ArmorMaterial material, ArmorPower power) {
        if (POWER_MAP.containsKey(material)) {
            throw new IllegalStateException("Found duplicate registration for armor material: " + material);
        }

        POWER_MAP.put(material, power);
    }

    public static void registerItem(Supplier<Item> item, ArmorPower power) {
        if (SINGULAR_POWER_MAP.containsKey(item)) {
            throw new IllegalStateException("Found duplicate registration for item: " + item);
        }

        SINGULAR_POWER_MAP.put(item, power);
    }

    public static ArmorPower getPower(ArmorMaterial material) {
        ArmorPower power = POWER_MAP.get(material);
        return power != null? POWER_MAP.get(material) : ArmorPower.EMPTY;
    }

    public static ArmorPower getItemPower(Supplier<Item> item) {
        ArmorPower power = SINGULAR_POWER_MAP.get(item);
        return power != null? SINGULAR_POWER_MAP.get(item) : ArmorPower.EMPTY;
    }
}