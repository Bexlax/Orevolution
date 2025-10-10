package net.bexla.orevolution.content.data.utility;

import net.bexla.orevolution.init.RegItems;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.block.Block;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.function.Supplier;

// import net.minecraft.world.item.Tiers;
public enum OrevolutionToolTiers implements Tier {
    TIN(1, 256, 5F, 1F, 7, () -> Ingredient.of(RegItems.TIN_INGOT.get()), OrevolutionTags.Blocks.TinTiered),
    PLATINUM(2,768,7F,2F,11, () -> Ingredient.of(RegItems.PLATINUM_INGOT.get()), OrevolutionTags.Blocks.PlatTiered),
    AETHERSTEEL(5,3520,10F,5F,22, () -> Ingredient.of(RegItems.AETHERSTEEL_INGOT.get()), OrevolutionTags.Blocks.AethersteelTiered),
    TUMBAGA(2, 256, 9F, 3F, 17, () -> Ingredient.of(RegItems.TUMBAGA_ALLOY.get()), OrevolutionTags.Blocks.TinTiered),
    STEEL(2, 1152, 3F, 4F, 12, () -> Ingredient.of(RegItems.STEEL_ALLOY.get()), OrevolutionTags.Blocks.PlatTiered),
    LIVINGSTONE(1, 192, 4F, 1F, 8, () -> Ingredient.of(RegItems.LIVINGSTONE_SHARD.get()), OrevolutionTags.Blocks.TinTiered),
    VERDITE(2, 448, 6F, 2F, 16, () -> Ingredient.of(RegItems.VERDITE_INGOT.get()), BlockTags.NEEDS_IRON_TOOL);

    private final int level;
    private final int durability;
    private final float speed;
    private final float damage;
    private final int enchantmentValue;
    private final Supplier<Ingredient> repairIngredient;
    private final TagKey<Block> blockTag;

    OrevolutionToolTiers(int level, int durability, float speed, float damage, int enchantmentValue, Supplier<Ingredient> repairIngredient, TagKey<Block> blockTag) {
        this.level = level;
        this.durability = durability;
        this.speed = speed;
        this.damage = damage;
        this.enchantmentValue = enchantmentValue;
        this.repairIngredient = repairIngredient;
        this.blockTag = blockTag;
    }

    public int getUses() {
        return this.durability;
    }

    public float getSpeed() {
        return this.speed;
    }

    public float getAttackDamageBonus() {
        return this.damage;
    }

    public int getLevel() {
        return this.level;
    }

    public int getEnchantmentValue() {
        return this.enchantmentValue;
    }

    public @NotNull Ingredient getRepairIngredient() {
        return this.repairIngredient.get();
    }

    public @Nullable TagKey<Block> getTag() {
        return blockTag;
    }
}
