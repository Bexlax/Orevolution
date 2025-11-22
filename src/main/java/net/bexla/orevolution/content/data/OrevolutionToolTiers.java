package net.bexla.orevolution.content.data;

import net.bexla.orevolution.content.data.utility.OrevolutionTags;
import net.bexla.orevolution.init.RegItems;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.block.Block;
import org.jetbrains.annotations.NotNull;

import java.util.function.Supplier;

// import net.minecraft.world.item.Tiers;
public enum OrevolutionToolTiers implements Tier {
    TIN(1, 256, 5F, 1F, 7, () -> Ingredient.of(OrevolutionTags.Items.tinIngots), OrevolutionTags.Blocks.needsTinTool),
    PLATINUM(2,768,7F,2F,11, () -> Ingredient.of(OrevolutionTags.Items.platIngots), OrevolutionTags.Blocks.needsPlatinumTool),
    AETHERSTEEL(5,3520,10F,5F,22, () -> Ingredient.of(RegItems.AETHERSTEEL_INGOT.get()), OrevolutionTags.Blocks.needsAethersteelTool),
    STEEL(2, 1152, 3F, 4F, 12, () -> Ingredient.of(RegItems.STEEL_ALLOY.get()), OrevolutionTags.Blocks.needsPlatinumTool),
    LIVINGSTONE(1, 192, 4F, 1F, 8, () -> Ingredient.of(OrevolutionTags.Items.livingstoneFragments), OrevolutionTags.Blocks.needsTinTool),
    VERDITE(2, 448, 6F, 2F, 16, () -> Ingredient.of(OrevolutionTags.Items.verditeIngots), OrevolutionTags.Blocks.needsPlatinumTool);

    private final int level;
    private final int durability;
    private final float speed;
    private final float damage;
    private final int enchantmentValue;
    private final Supplier<Ingredient> repairIngredient;
    private final TagKey<Block> tag;

    OrevolutionToolTiers(int level, int durability, float speed, float damage, int enchantmentValue, Supplier<Ingredient> repairIngredient, TagKey<Block> tag) {
        this.level = level;
        this.durability = durability;
        this.speed = speed;
        this.damage = damage;
        this.enchantmentValue = enchantmentValue;
        this.repairIngredient = repairIngredient;
        this.tag = tag;
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

    public TagKey<Block> getTag() {
        return this.tag;
    }
}
