package net.bexla.orevolution.content.data.utility;

import com.mojang.logging.LogUtils;
import net.bexla.orevolution.Orevolution;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.Tier;
import net.minecraftforge.common.TierSortingRegistry;
import net.minecraftforge.fml.ModList;
import org.slf4j.Logger;

import java.util.List;

public class OrevolutionUtils {
    protected static final Logger LOGGER = LogUtils.getLogger();

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

    public static Tier getTierBefore(Tier tier) {
        int index = getTierIndex(tier);
        List<Tier> tiers = TierSortingRegistry.getSortedTiers();
        if (index <= 0 || index >= tiers.size()) return tier; // or null
        return tiers.get(index - 1);
    }

    public static Tier getTierAfter(Tier tier) {
        int index = getTierIndex(tier);
        List<Tier> tiers = TierSortingRegistry.getSortedTiers();
        if (index < 0 || index + 1 >= tiers.size()) return tier; // or null
        return tiers.get(index + 1);
    }

    public static int getTierIndex(Tier tier) {
        if(!TierSortingRegistry.isTierSorted(tier)) {
            LOGGER.error("Tier not found in TierSortingRegistry: {}", tier);
            return 0;
        }

        List<Tier> tiers = TierSortingRegistry.getSortedTiers();
        int index = tiers.indexOf(tier);

        if (index == -1) {
            LOGGER.error("Tier not found in TierSortingRegistry: {}", tier);
            return 0;
        }

        return index;
    }

    public static List<Tier> getTiersLowerOrEqualThan(Tier tier) {
        List<Tier> sortedTiers = TierSortingRegistry.getSortedTiers();
        int index = sortedTiers.indexOf(tier);
        if (index == -1) return List.of();
        return sortedTiers.subList(0, index + 1);
    }

    public static List<Tier> getTiersHigherOrEqualThan(Tier tier) {
        List<Tier> sortedTiers = TierSortingRegistry.getSortedTiers();
        int index = sortedTiers.indexOf(tier);
        if (index == -1 || index == sortedTiers.size() - 1) return List.of();
        return sortedTiers.subList(index, sortedTiers.size());
    }

    public static ResourceLocation modLocat(String id) {
        return new ResourceLocation(Orevolution.MODID, id);
    }

    // Mod Compat
    public enum ModCompat {
        FARMERSDELIGHT("farmersdelight"),
        OREGANIZED("oreganized"),
        CAC("caverns_and_chasms"),
        SPELUNKERY("spelunkery"),
        SHIELDEXPANSION("shieldexp");

        final String id;
        ModCompat(String id) {this.id = id;}

        public String getId() {
            return id;
        }

        public static boolean isModLoaded(ModCompat val) {
            return ModList.get().isLoaded(val.getId());
        }
    }
}
