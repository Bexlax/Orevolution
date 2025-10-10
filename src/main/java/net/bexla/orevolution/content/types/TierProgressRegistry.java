package net.bexla.orevolution.content.types;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Tier;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.TierSortingRegistry;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TierProgressRegistry {
    private static final Map<Tier, TagKey<Block>> tierTagMap = new HashMap<>();

    /**
     * Registers a tier with an associated block tag and automatically adds it to TierSortingRegistry.
     * @param tier The custom tier to register.
     * @param tag The block tag associated with this tier.
     * @param before Tier before this one.
     * @param after Tier after this one.
     */
    public static void registerTier(Tier tier, ResourceLocation tierName, Tier before, Tier after, TagKey<Block> tag) {
        registerTierNoSort(tier, tag);
        TierSortingRegistry.registerTier(tier, tierName, before == null? List.of() : List.of(before), after == null? List.of() : List.of(after));
    }

    /** Removes a registered tier from the registry. You should only use this if you know what you're doing.
     * <br>
     * Note: This does not unregister the tier from the TierSortingRegistry, as TierSortingRegistry does not allow that.
     * @param tier The custom tier to remove.
     * @param tag The block tag associated with this tier.
     */
    public static void removeRegisteredTier(Tier tier, TagKey<Block> tag) {
        tierTagMap.remove(tier, tag);
        // Personally, i'd use mixins to remove the tiers from TierSortingRegistry, but i'd rather keep it as vanilla as possible.
    }

    /**
     * Registers a tier with an associated block tag without adding it to TierSortingRegistry.
     * This is useful if you want to handle the tier ordering manually.
     * <br>
     * In my case, it was just to register vanilla tiers, which are already registered in TierSortingRegistry.
     * @param tier The custom tier to register.
     * @param tag The block tag associated with this tier.
     */
    public static void registerTierNoSort(Tier tier, TagKey<Block> tag) {
        tierTagMap.put(tier, tag);
    }

    public static TagKey<Block> getTagForTier(Tier tier) {
        return tierTagMap.get(tier);
    }
}
