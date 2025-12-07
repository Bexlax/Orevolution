package net.bexla.orevolution.content.types.providers;

import com.possible_triangle.multikulti.datagen.conditions.Conditional;
import com.possible_triangle.multikulti.datagen.conditions.Inverted;
import com.possible_triangle.multikulti.datagen.conditions.ModLoaded;
import net.bexla.orevolution.compatibility.ModCompat;
import net.bexla.orevolution.content.data.utility.OrevolutionTags;
import net.bexla.orevolution.content.data.utility.OrevolutionUtils;
import net.bexla.orevolution.init.RegItems;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.crafting.ConditionalRecipe;
import net.minecraftforge.common.crafting.conditions.ModLoadedCondition;
import vectorwing.farmersdelight.data.builder.CuttingBoardRecipeBuilder;

import java.util.List;
import java.util.function.Consumer;
import java.util.function.Supplier;

// Credits to Oreganized (Team Galena)
public abstract class RecipesProvider extends RecipeProvider {
    public RecipesProvider(PackOutput output) {
        super(output);
    }

    public ShapedRecipeBuilder makeSlab(Supplier<? extends Block> slabOut, Supplier<? extends Block> blockIn) {
        return ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, slabOut.get(), 6)
                .pattern("AAA")
                .define('A', blockIn.get())
                .unlockedBy(getHasName(blockIn.get()), has(blockIn.get()));
    }

    public ShapedRecipeBuilder makeStairs(Supplier<? extends Block> stairsOut, Supplier<? extends Block> blockIn) {
        return ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, stairsOut.get(), 4)
                .pattern("A  ")
                .pattern("AA ")
                .pattern("AAA")
                .define('A', blockIn.get())
                .unlockedBy(getHasName(blockIn.get()), has(blockIn.get()));
    }

    public ShapedRecipeBuilder makeWall(Supplier<? extends Block> wallOut, Supplier<? extends Block> blockIn) {
        return ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, wallOut.get(), 6)
                .pattern("AAA")
                .pattern("AAA")
                .define('A', blockIn.get())
                .unlockedBy(getHasName(blockIn.get()), has(blockIn.get()));
    }

    public ShapedRecipeBuilder makeBarsItem(Supplier<? extends Block> barsOut, Supplier<? extends Item> itemIn) {
        return ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, barsOut.get(), 16)
                .pattern("AAA")
                .pattern("AAA")
                .define('A', itemIn.get())
                .unlockedBy(getHasName(itemIn.get()), has(itemIn.get()));
    }

    public ShapedRecipeBuilder makeBars(Supplier<? extends Block> barsOut, Supplier<? extends Block> blockIn) {
        return ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, barsOut.get(), 16)
                .pattern("AAA")
                .pattern("AAA")
                .define('A', blockIn.get())
                .unlockedBy(getHasName(blockIn.get()), has(blockIn.get()));
    }

    public ShapedRecipeBuilder quadTransform(Supplier<? extends Block> blockOut, Supplier<? extends Block> blockIn) {
        return quadTransform(blockOut, blockIn, 4);
    }

    public ShapedRecipeBuilder quadTransform(Supplier<? extends Block> blockOut, Supplier<? extends Block> blockIn, int amount) {
        return ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, blockOut.get(), amount)
                .pattern("AA")
                .pattern("AA")
                .define('A', blockIn.get())
                .unlockedBy(getHasName(blockIn.get()), has(blockIn.get()));
    }

    public ShapedRecipeBuilder quadTransformItem(Supplier<? extends Block> blockOut, Supplier<? extends Item> blockIn, int amount) {
        return ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, blockOut.get(), amount)
                .pattern("AA")
                .pattern("AA")
                .define('A', blockIn.get())
                .unlockedBy(getHasName(blockIn.get()), has(blockIn.get()));
    }

    public ShapedRecipeBuilder makeChiseled(Supplier<? extends Block> blockOut, Supplier<? extends Block> slabIn) {
        return ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, blockOut.get())
                .pattern("A")
                .pattern("A")
                .define('A', slabIn.get())
                .unlockedBy(getHasName(slabIn.get()), has(slabIn.get()));
    }

    public ShapedRecipeBuilder makePillar(Supplier<? extends Block> blockOut, Supplier<? extends Block> blockIn) {
        return ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, blockOut.get(), 2)
                .pattern("A")
                .pattern("A")
                .define('A', blockIn.get())
                .unlockedBy(getHasName(blockIn.get()), has(blockIn.get()));
    }

    public ShapedRecipeBuilder makePillarItem(Supplier<? extends Block> blockOut, Supplier<? extends Item> matIn) {
        return ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, blockOut.get(), 2)
                .pattern("A")
                .pattern("A")
                .define('A', matIn.get())
                .unlockedBy(getHasName(matIn.get()), has(matIn.get()));
    }

    public ShapedRecipeBuilder bronzeCrown(Supplier<? extends Item> crownResult, Supplier<? extends Item> gem) {
        return ShapedRecipeBuilder.shaped(RecipeCategory.MISC, crownResult.get())
                .pattern("AB")
                .define('A', RegItems.BRONZE_CROWN.get())
                .define('B', gem.get())
                .unlockedBy(getHasName(gem.get()), has(gem.get()));
    }

    public void armorSet(String id, Item helmet, Item chestplate, Item leggings, Item boots, TagKey<Item> ingredient, Consumer<FinishedRecipe> consumer) {
        boots(id, boots, ingredient).save(consumer);
        leggings(id, leggings, ingredient).save(consumer);
        chestplate(id, chestplate, ingredient).save(consumer);
        helmet(id, helmet, ingredient).save(consumer);
    }

    public ShapedRecipeBuilder helmet(String id, Item itemOut, TagKey<Item> itemIn) {
        return ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, itemOut)
                .pattern("AAA")
                .pattern("A A")
                .define('A', itemIn)
                .unlockedBy("has_" + id, has(itemIn));
    }

    public ShapedRecipeBuilder chestplate(String id, Item itemOut, TagKey<Item> itemIn) {
        return ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, itemOut)
                .pattern("A A")
                .pattern("AAA")
                .pattern("AAA")
                .define('A', itemIn)
                .unlockedBy("has_" + id, has(itemIn));
    }

    public ShapedRecipeBuilder leggings(String id, Item itemOut, TagKey<Item> itemIn) {
        return ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, itemOut)
                .pattern("AAA")
                .pattern("A A")
                .pattern("A A")
                .define('A', itemIn)
                .unlockedBy("has_" + id, has(itemIn));
    }

    public ShapedRecipeBuilder boots(String id, Item itemOut, TagKey<Item> itemIn) {
        return ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, itemOut)
                .pattern("A A")
                .pattern("A A")
                .define('A', itemIn)
                .unlockedBy("has_" + id, has(itemIn));
    }

    public SmithingTransformRecipeBuilder smithingRecipe(Supplier<? extends Item> input, Supplier<? extends Item> upgradeItem, Supplier<? extends Item> templateItem, Supplier<? extends Item> result) {
        return SmithingTransformRecipeBuilder.smithing(Ingredient.of(templateItem.get()), Ingredient.of(input.get()), Ingredient.of(upgradeItem.get()), RecipeCategory.MISC, result.get())
                .unlocks(getHasName(upgradeItem.get()), has(upgradeItem.get()));
    }

    public SmithingTransformRecipeBuilder smithingRecipe(Supplier<? extends Item> input, TagKey<Item> upgradeItem, Supplier<? extends Item> templateItem, Supplier<? extends Item> result) {
        return SmithingTransformRecipeBuilder.smithing(Ingredient.of(templateItem.get()), Ingredient.of(input.get()), Ingredient.of(upgradeItem), RecipeCategory.MISC, result.get())
                .unlocks("has_" + upgradeItem.location().getPath(), has(upgradeItem));
    }

    public SmithingTransformRecipeBuilder smithingAether(Supplier<? extends Item> input, Supplier<? extends Item> result) {
        return smithingRecipe(input, RegItems.AETHERSTEEL_INGOT, RegItems.AETHERSTEEL_TEMPLATE, result);
    }


    public SmithingTransformRecipeBuilder smithingReinforced(Supplier<? extends Item> input, Supplier<? extends Item> result) {
        return smithingRecipe(input, RegItems.TUNGSTEN_INGOT, RegItems.REINFORCED_TEMPLATE, result);
    }

    public SmithingTransformRecipeBuilder smithingBasic(Supplier<? extends Item> input, Supplier<? extends Item> result) {
        return smithingRecipe(input, () -> Items.IRON_INGOT, RegItems.BASIC_TEMPLATE, result);
    }

    public void toolSet(String id, Item sword, Item pickaxe, Item axe, Item shovel, Item hoe, Item knife, Item shield, TagKey<Item> ingredient, Consumer<FinishedRecipe> consumer) {
        hoe(id, hoe, ingredient).save(consumer);
        shovel(id, shovel, ingredient).save(consumer);
        axe(id, axe, ingredient).save(consumer);
        pickaxe(id, pickaxe, ingredient).save(consumer);
        sword(id, sword, ingredient).save(consumer);
        whenLoaded(knife(id, knife, ingredient), ModCompat.farmersdelight()).save(consumer);
        whenLoaded(shield(id, shield, ingredient), ModCompat.shieldexp()).save(consumer);
    }

    public ShapedRecipeBuilder shield(String id, Item itemOut, TagKey<Item> itemIn) {
        return ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, itemOut)
                .pattern("AAA")
                .pattern("ASA")
                .pattern("AAA")
                .define('A', itemIn)
                .define('S', Items.STICK)
                .unlockedBy("has_" + id, has(itemIn));
    }

    public ShapedRecipeBuilder knife(String id, Item itemOut, TagKey<Item> itemIn) {
        return ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, itemOut)
                .pattern("A")
                .pattern("S")
                .define('A', itemIn)
                .define('S', Items.STICK)
                .unlockedBy("has_" + id, has(itemIn));
    }

    public ShapedRecipeBuilder hoe(String id, Item itemOut, TagKey<Item> itemIn) {
        return ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, itemOut)
                .pattern("AA")
                .pattern(" S")
                .pattern(" S")
                .define('A', itemIn)
                .define('S', Items.STICK)
                .unlockedBy("has_" + id, has(itemIn));
    }

    public ShapedRecipeBuilder shovel(String id, Item itemOut, TagKey<Item> itemIn) {
        return ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, itemOut)
                .pattern("A")
                .pattern("S")
                .pattern("S")
                .define('A', itemIn)
                .define('S', Items.STICK)
                .unlockedBy("has_" + id, has(itemIn));
    }

    public ShapedRecipeBuilder pickaxe(String id, Item itemOut, TagKey<Item> itemIn) {
        return ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, itemOut)
                .pattern("AAA")
                .pattern(" S ")
                .pattern(" S ")
                .define('A', itemIn)
                .define('S', Items.STICK)
                .unlockedBy("has_" + id, has(itemIn));
    }

    public ShapedRecipeBuilder axe(String id, Item itemOut, TagKey<Item> itemIn) {
        return ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, itemOut)
                .pattern("AA")
                .pattern("AS")
                .pattern(" S")
                .define('A', itemIn)
                .define('S', Items.STICK)
                .unlockedBy("has_" + id, has(itemIn));
    }

    public ShapedRecipeBuilder sword(String id, Item itemOut, TagKey<Item> itemIn) {
        return ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, itemOut)
                .pattern("A")
                .pattern("A")
                .pattern("S")
                .define('A', itemIn)
                .define('S', Items.STICK)
                .unlockedBy("has_" + id, has(itemIn));
    }

    public ShapedRecipeBuilder compact(Item itemOut, Item itemIn) {
        return ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, itemOut)
                .pattern("AAA")
                .pattern("AAA")
                .pattern("AAA")
                .define('A', itemIn)
                .unlockedBy(getHasName(itemIn), has(itemIn));
    }

    public ShapelessRecipeBuilder alloyLow(String has, Item itemOut, TagKey<Item> firstItem, TagKey<Item> secondItem) {
        return ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, itemOut, 1)
                .requires(firstItem)
                .requires(firstItem)
                .requires(secondItem)
                .requires(secondItem)
                .unlockedBy("has_" + has, has(firstItem));
    }

    public ShapelessRecipeBuilder alloyLow(String has, Item itemOut, TagKey<Item> firstItem, TagKey<Item> secondItem, Item requirement) {
        return ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, itemOut, 1)
                .requires(firstItem)
                .requires(requirement)
                .requires(secondItem)
                .unlockedBy("has_" + has, has(firstItem));
    }

    public ShapelessRecipeBuilder alloyHigh(String has, Item itemOut, Item firstItem, TagKey<Item> secondItem) {
        return ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, itemOut, 1)
                .requires(firstItem)
                .requires(firstItem)
                .requires(firstItem)
                .requires(firstItem)
                .requires(secondItem)
                .requires(secondItem)
                .requires(secondItem)
                .requires(secondItem)
                .unlockedBy("has_" + has, has(firstItem));
    }

    public ShapelessRecipeBuilder alloyHigh(String has, Item itemOut, TagKey<Item> firstItem, Item secondItem, Item requirement) {
        return ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, itemOut, 1)
                .requires(firstItem)
                .requires(firstItem)
                .requires(firstItem)
                .requires(requirement)
                .requires(secondItem)
                .requires(secondItem)
                .requires(secondItem)
                .unlockedBy("has_" + has, has(firstItem));
    }

    public void autoCompact(Item itemOut, Item itemIn, Consumer<FinishedRecipe> consumer) {
        compact(itemOut, itemIn).save(consumer, OrevolutionUtils.modLocat(getItemName(itemOut) + "_from_" + getItemName(itemIn)));
        unCompact(itemIn, itemOut).save(consumer, OrevolutionUtils.modLocat(getItemName(itemIn) + "_from_" + getItemName(itemOut)));
    }

    public ShapelessRecipeBuilder unCompact(Item itemOut, Item itemIn) {
        return ShapelessRecipeBuilder.shapeless(RecipeCategory.BUILDING_BLOCKS, itemOut, 9)
                .requires(itemIn)
                .unlockedBy(getHasName(itemIn), has(itemIn));
    }

    public void ore(ItemLike result, List<ItemLike> ingredients, int time, float xp, String group, Consumer<FinishedRecipe> consumer) {
        oreSmeltingRecipe(result, ingredients, time, xp, group, consumer);
        oreBlastingRecipe(result, ingredients, time / 2, xp, group, consumer);
    }

    public void oreSK(ItemLike result, List<ItemLike> ingredients, float xp, String group, Consumer<FinishedRecipe> consumer) {
        for (ItemLike ingredient : ingredients) {
            smeltingRecipeX(result, ingredient, xp, 1).group(group).save(consumer, OrevolutionUtils.modLocat(getItemName(result) + "_from_smelting_" + getItemName(ingredient)));
        }
        for (ItemLike ingredient : ingredients) {
            blastingRecipeX(result, ingredient, xp, 1).group(group).save(consumer, OrevolutionUtils.modLocat(getItemName(result) + "_from_blasting_" + getItemName(ingredient)));
        }
    }

    public SimpleCookingRecipeBuilder smeltingRecipeX(ItemLike result, ItemLike ingredient, float exp, int count) {
        return whenLoaded(SimpleCookingRecipeBuilder.smelting(Ingredient.of(new ItemStack(ingredient, count)), RecipeCategory.MISC, result, exp, 200)
                .unlockedBy(getHasName(ingredient), has(ingredient)), ModCompat.spelunkery());
    }

    public SimpleCookingRecipeBuilder blastingRecipeX(ItemLike result, ItemLike ingredient, float exp, int count) {
        return whenLoaded(SimpleCookingRecipeBuilder.blasting(Ingredient.of(new ItemStack(ingredient, count)), RecipeCategory.MISC, result, exp, 200)
                .unlockedBy(getHasName(ingredient), has(ingredient)), ModCompat.spelunkery());
    }

    public SimpleCookingRecipeBuilder smeltingRecipe(ItemLike result, ItemLike ingredient, int time, float exp) {
        return smeltingRecipe(result, ingredient, time, exp, 1);
    }

    private void oreSmeltingRecipe(ItemLike result, List<ItemLike> ingredients, int time, float xp, String group, Consumer<FinishedRecipe> consumer) {
        for (ItemLike ingredient : ingredients) {
            smeltingRecipe(result, ingredient, time, xp, 1).group(group).save(consumer, OrevolutionUtils.modLocat(getItemName(result) + "_from_smelting_" + getItemName(ingredient)));
        }
    }

    public SimpleCookingRecipeBuilder smeltingRecipe(ItemLike result, ItemLike ingredient, int time, float exp, int count) {
        return SimpleCookingRecipeBuilder.smelting(Ingredient.of(new ItemStack(ingredient, count)), RecipeCategory.MISC, result, exp, time)
                .unlockedBy(getHasName(ingredient), has(ingredient));
    }

    public SimpleCookingRecipeBuilder smeltingRecipeTag(ItemLike result, TagKey<Item> ingredient, float exp) {
        return smeltingRecipeTag(result, ingredient, exp, 1);
    }

    public SimpleCookingRecipeBuilder smeltingRecipeTag(ItemLike result, TagKey<Item> ingredient, float exp, int count) {
        return SimpleCookingRecipeBuilder.smelting(Ingredient.of(ingredient), RecipeCategory.MISC, result, exp, 200)
                .unlockedBy("has_" + ingredient, has(ingredient));
    }

    public SimpleCookingRecipeBuilder blastingRecipe(ItemLike result, ItemLike ingredient, int time, float exp) {
        return blastingRecipe(result, ingredient, time, exp, 1);
    }

    private void oreBlastingRecipe(ItemLike result, List<ItemLike> ingredients, int time, float xp, String group, Consumer<FinishedRecipe> consumer) {
        for (ItemLike ingredient : ingredients) {
            blastingRecipe(result, ingredient, time, xp, 1).group(group).save(consumer, OrevolutionUtils.modLocat(getItemName(result) + "_from_blasting_" + getItemName(ingredient)));
        }
    }

    public SimpleCookingRecipeBuilder blastingRecipe(ItemLike result, ItemLike ingredient, int time, float exp, int count) {
        return SimpleCookingRecipeBuilder.blasting(Ingredient.of(new ItemStack(ingredient, count)), RecipeCategory.MISC, result, exp, time)
                .unlockedBy(getHasName(ingredient.asItem()), has(ingredient));
    }

    public SimpleCookingRecipeBuilder blastingRecipeTag(ItemLike result, TagKey<Item> ingredient, float exp) {
        return blastingRecipeTag(result, ingredient, exp, 1);
    }

    public SimpleCookingRecipeBuilder blastingRecipeTag(ItemLike result, TagKey<Item> ingredient, float exp, int count) {
        return SimpleCookingRecipeBuilder.blasting(Ingredient.of(ingredient), RecipeCategory.MISC, result, exp, 100)
                .unlockedBy("has_" + ingredient, has(ingredient));
    }

    public SingleItemRecipeBuilder stonecutting(Supplier<? extends Block> input, ItemLike result) {
        return SingleItemRecipeBuilder.stonecutting(Ingredient.of(input.get()), RecipeCategory.BUILDING_BLOCKS, result)
                .unlockedBy(getHasName(input.get()), has(input.get()));
    }

    public SingleItemRecipeBuilder stonecutting(Supplier<? extends Block> input, ItemLike result, int resultAmount) {
        return SingleItemRecipeBuilder.stonecutting(Ingredient.of(input.get()), RecipeCategory.BUILDING_BLOCKS, result, resultAmount)
                .unlockedBy(getHasName(input.get()), has(input.get()));
    }

    public ShapelessRecipeBuilder makeWaxed(Supplier<? extends Block> blockOut, Block blockIn) {
        return ShapelessRecipeBuilder.shapeless(RecipeCategory.DECORATIONS, blockOut.get())
                .requires(blockIn)
                .requires(Items.HONEYCOMB)
                .unlockedBy(getHasName(blockIn), has(blockIn))
                .unlockedBy("has_honeycomb", has(Items.HONEYCOMB));
    }

    public ShapelessRecipeBuilder makeWaxed(Supplier<? extends Block> blockOut, Supplier<? extends Block> blockIn) {
        return makeWaxed(blockOut, blockIn.get());
    }

    public void makeSlabStonecutting(Supplier<? extends Block> blockOut, Supplier<? extends Block> blockIn, Consumer<FinishedRecipe> consumer) {
        makeSlab(blockOut, blockIn).save(consumer);
        stonecutting(blockIn, blockOut.get(), 2).save(consumer, OrevolutionUtils.modLocat("stonecutting/" + getItemName(blockOut.get())));
    }

    public void makeStairsStonecutting(Supplier<? extends Block> blockOut, Supplier<? extends Block> blockIn, Consumer<FinishedRecipe> consumer) {
        makeStairs(blockOut, blockIn).save(consumer);
        stonecutting(blockIn, blockOut.get()).save(consumer, OrevolutionUtils.modLocat("stonecutting/" + getItemName(blockOut.get())));
    }

    public void makeWallStonecutting(Supplier<? extends Block> blockOut, Supplier<? extends Block> blockIn, Consumer<FinishedRecipe> consumer) {
        makeWall(blockOut, blockIn).save(consumer);
        stonecutting(blockIn, blockOut.get()).save(consumer, OrevolutionUtils.modLocat("stonecutting/" + getItemName(blockOut.get())));
    }

    public void makeChiseledStonecutting(Supplier<? extends Block> blockOut, Supplier<? extends Block> blockIn, Consumer<FinishedRecipe> consumer) {
        makeChiseled(blockOut, blockIn).save(consumer);
        stonecutting(blockIn, blockOut.get()).save(consumer, OrevolutionUtils.modLocat("stonecutting/" + getItemName(blockOut.get())));
    }

    public void flowerDye(Supplier<? extends ItemLike> flower, ItemLike primary, Consumer<FinishedRecipe> consumer) {
        var name = getItemName(flower.get());

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, primary)
                .requires(flower.get())
                .unlockedBy(getHasName(flower.get()), has(flower.get()))
                .save(consumer, OrevolutionUtils.modLocat("dye_from_" + name));

        ConditionalRecipe.builder()
                .addCondition(new ModLoadedCondition(ModCompat.farmersdelight()))
                .addRecipe(CuttingBoardRecipeBuilder.cuttingRecipe(Ingredient.of(flower.get()), Ingredient.of(OrevolutionTags.Items.toolsKnives), primary, 2)::build)
                .build(consumer, OrevolutionUtils.modLocat("cutting/" + getItemName(flower.get())));
    }

    public <T> T unlessLoaded(T value, String... modIds) {
        return Conditional.with(value, new Inverted(new ModLoaded(modIds, true)));
    }

    public <T> T whenLoaded(T value, String... modIds) {
        return Conditional.with(value, new ModLoaded(modIds));
    }
}
