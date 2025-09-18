package net.bexla.orevolution.content.data.utility;

import net.bexla.orevolution.Orevolution;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.Tier;
import net.minecraftforge.common.TierSortingRegistry;
import net.minecraftforge.fml.ModList;

public class OrevolutionUtils {
    public static boolean isWearingFullSet(LivingEntity entity, ArmorMaterial material) {
        return entity.getItemBySlot(EquipmentSlot.HEAD).getItem() instanceof ArmorItem head &&
                entity.getItemBySlot(EquipmentSlot.CHEST).getItem() instanceof ArmorItem chest &&
                entity.getItemBySlot(EquipmentSlot.LEGS).getItem() instanceof ArmorItem legs &&
                entity.getItemBySlot(EquipmentSlot.FEET).getItem() instanceof ArmorItem feet &&
                head.getMaterial() == material &&
                chest.getMaterial() == material &&
                legs.getMaterial() == material &&
                feet.getMaterial() == material;
    }

    public static Tier getTierAfter(Tier tier) {
        return TierSortingRegistry.getSortedTiers().get(getTierIndex(tier) + 1);
    }

    public static Tier getTierBefore(Tier tier) {
        return TierSortingRegistry.getSortedTiers().get(getTierIndex(tier) - 1);
    }

    public static int getTierIndex(Tier tier) {
        return TierSortingRegistry.getSortedTiers().indexOf(tier);
    }

    public static ResourceLocation modLocat(String id) {
        return new ResourceLocation(Orevolution.MODID, id);
    }

    /// Mod Compat
    public static final String FARMERSDELIGHT = "farmersdelight";
    public static final String OREGANIZED = "oreganized";
    public static final String CAC = "caverns_and_chasms";
    public static final String SPELUNKERY = "spelunkery";
    public static final String SHIELDEXPANSION = "shieldex";

    public static boolean isModLoaded(String id) {
        return ModList.get().isLoaded(id);
    }
}
