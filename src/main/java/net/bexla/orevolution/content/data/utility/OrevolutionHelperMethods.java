package net.bexla.orevolution.content.data.utility;

import net.bexla.orevolution.Orevolution;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.fml.ModList;

public class OrevolutionHelperMethods {
    public static boolean getChance(double chance) {
        return Math.random() < chance;
    }

    public static ResourceLocation modLocat(String id) {
        return new ResourceLocation(Orevolution.MODID, id);
    }

    /// Mod Compat
    public static final String FARMERSDELIGHT = "farmersdelight";
    public static final String SHIELDEXPANSION = "shieldex";

    public static boolean farmersDelightLoaded() {
        return ModList.get().isLoaded(FARMERSDELIGHT);
    }

    public static boolean shieldExpansionLoaded() {
        return ModList.get().isLoaded(SHIELDEXPANSION);
    }
}
