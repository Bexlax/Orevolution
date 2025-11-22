package net.bexla.orevolution.compatibility;

import net.minecraftforge.fml.ModList;

public class ModCompat {
    public static boolean isModLoaded(String mod) {
        return ModList.get().isLoaded(mod);
    }

    public static String farmersdelight() {
        return "farmersdelight";
    }

    public static String oreganized() {
        return "oreganized";
    }

    public static String spelunkery() {
        return "spelunkery";
    }

    public static String cac() {
        return "caverns_and_chasms";
    }

    public static String shieldexp() {
        return "shieldexp";
    }
}
