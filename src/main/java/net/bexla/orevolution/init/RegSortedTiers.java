package net.bexla.orevolution.init;

import net.bexla.orevolution.content.data.utility.OrevolutionToolTiers;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.Tiers;
import net.minecraftforge.common.TierSortingRegistry;

import java.util.List;

import static net.bexla.orevolution.content.data.utility.OrevolutionUtils.modLocat;

public class RegSortedTiers {
    private static void sortTier(Tier tier, String id, Tier before, Tier after) {
        TierSortingRegistry.registerTier(tier, modLocat(id), List.of(before), List.of(after));
    }

    private static void postNetherite(Tier tier, String id) {
        TierSortingRegistry.registerTier(tier, modLocat(id), List.of(Tiers.NETHERITE), List.of());
    }

    public static void RegisterSortedTiers() {
        sortTier(OrevolutionToolTiers.TIN, "tin", Tiers.STONE, Tiers.IRON);
        sortTier(OrevolutionToolTiers.PLATINUM, "platinum", Tiers.IRON, Tiers.DIAMOND);
        postNetherite(OrevolutionToolTiers.AETHERSTEEL, "aethersteel");
    }
}
