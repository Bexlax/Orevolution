package net.bexla.orevolution.content.types;

import net.bexla.orevolution.content.types.interfaces.IArmorPower;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraftforge.common.ForgeConfigSpec;

import java.util.HashMap;
import java.util.Map;

public class ArmorPowerRegistry {
    private static final Map<ArmorMaterial, Entry> POWER_MAP = new HashMap<>();

    public static void register(ForgeConfigSpec.ConfigValue<Boolean> enabled, ArmorMaterial material, IArmorPower power) {
        if (POWER_MAP.containsKey(material)) {
            throw new IllegalStateException("Duplicate registration for armor material: " + material);
        }

        POWER_MAP.put(material, new Entry(enabled, power));
    }

    public static IArmorPower getPower(ArmorMaterial material) {
        Entry entry = POWER_MAP.get(material);

        if (entry == null) {
            return IArmorPower.EMPTY;
        }

        return entry.enabled.get() ? entry.power : IArmorPower.EMPTY;
    }

    private record Entry(ForgeConfigSpec.ConfigValue<Boolean> enabled, IArmorPower power) {}
}