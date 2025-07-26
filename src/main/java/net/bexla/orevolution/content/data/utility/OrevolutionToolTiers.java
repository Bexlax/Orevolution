package net.bexla.orevolution.content.data.utility;

import com.teamabnormals.blueprint.core.api.BlueprintItemTier;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.crafting.Ingredient;
import net.bexla.orevolution.content.init.RegItems;

public class OrevolutionToolTiers  {
    public static final Tier LIVINGSTONE = new BlueprintItemTier(1, 142, 4F, 1F, 8, () -> Ingredient.of(RegItems.LIVINGSTONE_SHARD.get()));
    public static final Tier VERDITE = new BlueprintItemTier(2, 286, 6F, 2F, 16, () -> Ingredient.of(RegItems.TIN_INGOT.get()));
    public static final Tier TIN = new BlueprintItemTier(1, 174, 5F, 1F, 7, () -> Ingredient.of(RegItems.TIN_INGOT.get()));
    public static final Tier PLATINUM = new BlueprintItemTier(2,429,7F,2F,12, () -> Ingredient.of(RegItems.PLATINUM_INGOT.get()));
    public static final Tier AETHERSTEEL = new BlueprintItemTier(5,3341,10F,5F,22, () -> Ingredient.of(RegItems.AETHERSTEEL_INGOT.get()));
}
