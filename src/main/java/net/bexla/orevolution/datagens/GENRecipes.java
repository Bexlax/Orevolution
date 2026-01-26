package net.bexla.orevolution.datagens;

import net.bexla.orevolution.compatibility.ModCompat;
import net.bexla.orevolution.compatibility.farmersdelight.RegItemsFD;
import net.bexla.orevolution.compatibility.spelunkery.RegBlocksSK;
import net.bexla.orevolution.content.data.utility.OrevolutionTags;
import net.bexla.orevolution.content.types.providers.RecipesProvider;
import net.bexla.orevolution.init.RegBlocks;
import net.bexla.orevolution.init.RegItems;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.data.recipes.SimpleCookingRecipeBuilder;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;
import net.minecraftforge.common.Tags;
import org.infernalstudios.shieldexp.init.ItemsInit;
import vectorwing.farmersdelight.common.registry.ModItems;

import java.util.List;
import java.util.function.Consumer;

import static net.bexla.orevolution.content.data.utility.OrevolutionUtils.modLocat;

public class GENRecipes extends RecipesProvider {
    protected static final Ingredient TIN_TOOLS_SMELTABLES =
            Ingredient.of(
                    RegItems.TIN_SWORD.get(),
                    RegItems.TIN_PICKAXE.get(),
                    RegItems.TIN_AXE.get(),
                    RegItems.TIN_SHOVEL.get(),
                    RegItems.TIN_HOE.get()
            );
    protected static final Ingredient PLATINUM_TOOLS_SMELTABLES =
            Ingredient.of(
                    RegItems.PLATINUM_SWORD.get(),
                    RegItems.PLATINUM_PICKAXE.get(),
                    RegItems.PLATINUM_AXE.get(),
                    RegItems.PLATINUM_SHOVEL.get(),
                    RegItems.PLATINUM_HOE.get(),
                    RegItems.PLATINUM_HELMET.get(),
                    RegItems.PLATINUM_CHESTPLATE.get(),
                    RegItems.PLATINUM_LEGGINGS.get(),
                    RegItems.PLATINUM_BOOTS.get()
            );
    public static final List<ItemLike> TIN_ORES = List.of(RegBlocks.TIN_ORE.get(), RegBlocks.DEEPSLATE_TIN_ORE.get(), RegItems.RAW_TIN.get());
    public static final List<ItemLike> PLATINUM_ORE = List.of(RegBlocks.PLATINUM_ORE.get(), RegBlocks.DEEPSLATE_PLATINUM_ORE.get(), RegItems.RAW_PLATINUM.get());

    public static final List<ItemLike> TIN_ORES_SK = List.of(RegBlocksSK.TIN_ORE_ANDESITE.get(), RegBlocksSK.TIN_ORE_GRANITE.get(), RegBlocksSK.TIN_ORE_DIORITE.get(), RegBlocksSK.TIN_ORE_TUFF.get());
    public static final List<ItemLike> PLATINUM_ORE_SK = List.of(RegBlocksSK.PLATINUM_ORE_ANDESITE.get(), RegBlocksSK.PLATINUM_ORE_GRANITE.get(), RegBlocksSK.PLATINUM_ORE_DIORITE.get(), RegBlocksSK.PLATINUM_ORE_TUFF.get());

    public GENRecipes(PackOutput output) {
        super(output);
    }

    @Override
    protected void buildRecipes(Consumer<FinishedRecipe> consumer) {
        ore(RegItems.TIN_INGOT.get(), TIN_ORES, 150, 0.4F, "orevolution:tin_ingot", consumer);
        ore(RegItems.PLATINUM_INGOT.get(), PLATINUM_ORE, 200, 0.4F, "orevolution:platinum_ingot", consumer);
        ore(RegItems.TUNGSTEN_INGOT.get(), List.of(RegBlocks.NETHER_TUNGSTEN_ORE.get(), RegItems.RAW_TUNGSTEN.get()), 300, 0.7F, "orevolution:tungsten_ingot", consumer);
        ore(RegItems.AETHERSTEEL_CHUNK.get(), List.of(RegBlocks.PRIMITIVE_AETHERROCK.get(), RegItems.AETHERSTEEL_INGOT.get()), 600, 3.0F, "orevolution:aethersteel_chunk", consumer);

        SimpleCookingRecipeBuilder.smelting(TIN_TOOLS_SMELTABLES, RecipeCategory.MISC, RegItems.TIN_NUGGET.get(), 0.1F, 150)
                .unlockedBy("has_tin_pickaxe", has(RegItems.TIN_PICKAXE.get()))
                .unlockedBy("has_tin_shovel", has(RegItems.TIN_SHOVEL.get()))
                .unlockedBy("has_tin_axe", has(RegItems.TIN_AXE.get()))
                .unlockedBy("has_tin_hoe", has(RegItems.TIN_HOE.get()))
                .unlockedBy("has_tin_sword", has(RegItems.TIN_SWORD.get()))
                .save(consumer, modLocat(getSmeltingRecipeName(RegItems.TIN_NUGGET.get())));

        SimpleCookingRecipeBuilder.blasting(TIN_TOOLS_SMELTABLES, RecipeCategory.MISC, RegItems.TIN_NUGGET.get(), 0.1F, 150)
                .unlockedBy("has_tin_pickaxe", has(RegItems.TIN_PICKAXE.get()))
                .unlockedBy("has_tin_shovel", has(RegItems.TIN_SHOVEL.get()))
                .unlockedBy("has_tin_axe", has(RegItems.TIN_AXE.get()))
                .unlockedBy("has_tin_hoe", has(RegItems.TIN_HOE.get()))
                .unlockedBy("has_tin_sword", has(RegItems.TIN_SWORD.get()))
                .save(consumer, modLocat(getBlastingRecipeName(RegItems.TIN_NUGGET.get())));

        SimpleCookingRecipeBuilder.smelting(PLATINUM_TOOLS_SMELTABLES, RecipeCategory.MISC, RegItems.PLATINUM_NUGGET.get(), 0.1F, 200)
                .unlockedBy("has_platinum_pickaxe", has(RegItems.PLATINUM_PICKAXE.get()))
                .unlockedBy("has_platinum_shovel", has(RegItems.PLATINUM_SHOVEL.get()))
                .unlockedBy("has_platinum_axe", has(RegItems.PLATINUM_AXE.get()))
                .unlockedBy("has_platinum_hoe", has(RegItems.PLATINUM_HOE.get()))
                .unlockedBy("has_platinum_sword", has(RegItems.PLATINUM_SWORD.get()))
                .unlockedBy("has_platinum_helmet", has(RegItems.PLATINUM_HELMET.get()))
                .unlockedBy("has_platinum_chestplate", has(RegItems.PLATINUM_CHESTPLATE.get()))
                .unlockedBy("has_platinum_leggings", has(RegItems.PLATINUM_LEGGINGS.get()))
                .unlockedBy("has_platinum_boots", has(RegItems.PLATINUM_BOOTS.get()))
                .save(consumer, modLocat(getSmeltingRecipeName(RegItems.PLATINUM_NUGGET.get())));

        SimpleCookingRecipeBuilder.blasting(PLATINUM_TOOLS_SMELTABLES, RecipeCategory.MISC, RegItems.PLATINUM_NUGGET.get(), 0.1F, 200)
                .unlockedBy("has_platinum_pickaxe", has(RegItems.PLATINUM_PICKAXE.get()))
                .unlockedBy("has_platinum_shovel", has(RegItems.PLATINUM_SHOVEL.get()))
                .unlockedBy("has_platinum_axe", has(RegItems.PLATINUM_AXE.get()))
                .unlockedBy("has_platinum_hoe", has(RegItems.PLATINUM_HOE.get()))
                .unlockedBy("has_platinum_sword", has(RegItems.PLATINUM_SWORD.get()))
                .unlockedBy("has_platinum_helmet", has(RegItems.PLATINUM_HELMET.get()))
                .unlockedBy("has_platinum_chestplate", has(RegItems.PLATINUM_CHESTPLATE.get()))
                .unlockedBy("has_platinum_leggings", has(RegItems.PLATINUM_LEGGINGS.get()))
                .unlockedBy("has_platinum_boots", has(RegItems.PLATINUM_BOOTS.get()))
                .save(consumer, modLocat(getBlastingRecipeName(RegItems.PLATINUM_NUGGET.get())));

        ore(RegItems.TIN_INGOT.get(), TIN_ORES_SK, 150, 0.4F, "orevolution:tin_ingot", consumer);
        ore(RegItems.PLATINUM_INGOT.get(), PLATINUM_ORE_SK, 200, 0.4F, "orevolution:platinum_ingot", consumer);

        //nugget(RegItems.TIN_NUGGET.get(), TIN_TOOLS_SMELTABLES, 0.1F, "orevolution:tin_nugget", consumer);
        //nugget(RegItems.PLATINUM_NUGGET.get(), PLATINUM_TOOLS_SMELTABLES, 0.2F, "orevolution:platinum_nugget", consumer);

        autoCompact(RegBlocks.TIN_BLOCK.get().asItem(), RegItems.TIN_INGOT.get(), consumer);
        autoCompact(RegBlocks.PLATINUM_BLOCK.get().asItem(), RegItems.PLATINUM_INGOT.get(), consumer);
        autoCompact(RegBlocks.TUNGSTEN_BLOCK.get().asItem(), RegItems.TUNGSTEN_INGOT.get(), consumer);
        autoCompact(RegBlocks.AETHERSTEEL_BLOCK.get().asItem(), RegItems.AETHERSTEEL_INGOT.get(), consumer);
        autoCompact(RegBlocks.BRONZE_BLOCK.get().asItem(), RegItems.BRONZE_ALLOY.get(), consumer);
        autoCompact(RegBlocks.STEEL_BLOCK.get().asItem(), RegItems.STEEL_ALLOY.get(), consumer);
        autoCompact(RegBlocks.VERDITE_BLOCK.get().asItem(), RegItems.VERDITE_INGOT.get(), consumer);

        autoCompact(RegBlocks.RAW_TIN_BLOCK.get().asItem(), RegItems.RAW_TIN.get(), consumer);
        autoCompact(RegBlocks.RAW_PLATINUM_BLOCK.get().asItem(), RegItems.RAW_PLATINUM.get(), consumer);
        autoCompact(RegBlocks.RAW_TUNGSTEN_BLOCK.get().asItem(), RegItems.RAW_TUNGSTEN.get(), consumer);

        autoCompact(RegItems.TIN_INGOT.get(), RegItems.TIN_NUGGET.get(), consumer);
        autoCompact(RegItems.PLATINUM_INGOT.get(), RegItems.PLATINUM_NUGGET.get(), consumer);
        autoCompact(RegItems.TUNGSTEN_INGOT.get(), RegItems.TUNGSTEN_NUGGET.get(), consumer);
        autoCompact(RegBlocks.LIVINGSTONE_BLOCK.get().asItem(), RegItems.LIVINGSTONE_SHARD.get(), consumer);
        autoCompact(RegItems.VERDITE_INGOT.get(), RegItems.VERDITE_NUGGET.get(), consumer);

        makeStairsStonecutting(RegBlocks.POLISHED_AETHERROCK_STAIR, RegBlocks.POLISHED_AETHERROCK, consumer);
        makeSlabStonecutting(RegBlocks.POLISHED_AETHERROCK_SLAB, RegBlocks.POLISHED_AETHERROCK, consumer);
        makeWallStonecutting(RegBlocks.POLISHED_AETHERROCK_WALL, RegBlocks.POLISHED_AETHERROCK, consumer);

        quadTransform(RegBlocks.POLISHED_AETHERROCK, RegBlocks.AETHERROCK).save(consumer);

        makePillar(RegBlocks.AETHERROCK_BRICKS, RegBlocks.AETHERROCK).save(consumer);

        quadTransform(RegBlocks.AETHERROCK_TILES, RegBlocks.POLISHED_AETHERROCK).save(consumer);

        quadTransformItem(RegBlocks.POLISHED_TUNGSTEN, RegItems.TUNGSTEN_INGOT, 1).save(consumer);
        quadTransform(RegBlocks.TUNGSTEN_BRICKS, RegBlocks.POLISHED_TUNGSTEN).save(consumer);

        makePillarItem(RegBlocks.STEEL_PILLAR, RegItems.STEEL_ALLOY).save(consumer);

        makePillarItem(RegBlocks.PLATINUM_PILLAR, RegItems.PLATINUM_INGOT).save(consumer);
        makePillarItem(RegBlocks.GOLD_PILLAR, () -> Items.GOLD_INGOT).save(consumer);

        makeBarsItem(RegBlocks.PLATINUM_BARS, RegItems.PLATINUM_INGOT);
        makeBarsItem(RegBlocks.TUNGSTEN_BARS, RegItems.TUNGSTEN_INGOT);
        makeBarsItem(RegBlocks.BRONZE_BARS, RegItems.BRONZE_ALLOY);
        makeBarsItem(RegBlocks.STEEL_BARS, RegItems.STEEL_ALLOY);
        makeBarsItem(RegBlocks.TIN_BARS, RegItems.TIN_INGOT);
        makeBarsItem(RegBlocks.GOLD_BARS, () -> Items.GOLD_INGOT);

        makeChiseledStonecutting(RegBlocks.CHISELED_TUNGSTEN_BLOCK, RegBlocks.TUNGSTEN_BLOCK, consumer);
        stonecutting(RegBlocks.CHISELED_TUNGSTEN_BRICKS, RegBlocks.TUNGSTEN_BLOCK.get()).save(consumer);
        makeChiseledStonecutting(RegBlocks.CHISELED_TUNGSTEN_BRICKS, RegBlocks.TUNGSTEN_BRICKS, consumer);

        alloyLow("tin_ingot", RegItems.BRONZE_ALLOY.get(), OrevolutionTags.Items.tinIngots, Tags.Items.INGOTS_COPPER).save(consumer);
        alloyHigh("iron_ingot", RegItems.STEEL_ALLOY.get(), Tags.Items.INGOTS_IRON, Items.COAL, Items.BLAZE_POWDER).save(consumer);
        alloyHigh("aethersteel_chunk", RegItems.AETHERSTEEL_INGOT.get(), RegItems.AETHERSTEEL_CHUNK.get(), OrevolutionTags.Items.platIngots).save(consumer);

        toolSet("tin_ingot",
                RegItems.TIN_SWORD.get(),
                RegItems.TIN_PICKAXE.get(),
                RegItems.TIN_AXE.get(),
                RegItems.TIN_SHOVEL.get(),
                RegItems.TIN_HOE.get(),
                RegItemsFD.TIN_KNIFE.get(),
                RegItems.TIN_SHIELD.get(),
            OrevolutionTags.Items.tinIngots, consumer);

        toolSet("platinum_ingot",
                RegItems.PLATINUM_SWORD.get(),
                RegItems.PLATINUM_PICKAXE.get(),
                RegItems.PLATINUM_AXE.get(),
                RegItems.PLATINUM_SHOVEL.get(),
                RegItems.PLATINUM_HOE.get(),
                RegItemsFD.PLATINUM_KNIFE.get(),
                RegItems.PLATINUM_SHIELD.get(),
            OrevolutionTags.Items.platIngots, consumer);
        armorSet("platinum_ingot",
                RegItems.PLATINUM_HELMET.get(),
                RegItems.PLATINUM_CHESTPLATE.get(),
                RegItems.PLATINUM_LEGGINGS.get(),
                RegItems.PLATINUM_BOOTS.get(),
            OrevolutionTags.Items.platIngots, consumer);

        makeToolsExtra(
                RegItems.LIVINGSTONE_SWORD.get(),
                RegItems.LIVINGSTONE_PICKAXE.get(),
                RegItems.LIVINGSTONE_AXE.get(),
                RegItems.LIVINGSTONE_SHOVEL.get(),
                RegItems.LIVINGSTONE_HOE.get(),
                RegItemsFD.LIVINGSTONE_KNIFE.get(),
                RegItems.LIVINGSTONE_SHIELD.get(),
            RegBlocks.LIVINGSTONE_BLOCK.get(), RegItems.LIVINGSTONE_SHARD.get(), consumer);
        armorSet("livingstone_block",
                RegItems.LIVINGSTONE_HELMET.get(),
                RegItems.LIVINGSTONE_CHESTPLATE.get(),
                RegItems.LIVINGSTONE_LEGGINGS.get(),
                RegItems.LIVINGSTONE_BOOTS.get(),
                OrevolutionTags.Items.livingstoneStorages, consumer);

        toolSet("verdite_ingot",
                RegItems.VERDITE_SWORD.get(),
                RegItems.VERDITE_PICKAXE.get(),
                RegItems.VERDITE_AXE.get(),
                RegItems.VERDITE_SHOVEL.get(),
                RegItems.VERDITE_HOE.get(),
                RegItemsFD.VERDITE_KNIFE.get(),
                RegItems.VERDITE_SHIELD.get(),
                OrevolutionTags.Items.verditeIngots, consumer);
        armorSet("verdite_ingot",
                RegItems.VERDITE_HELMET.get(),
                RegItems.VERDITE_CHESTPLATE.get(),
                RegItems.VERDITE_LEGGINGS.get(),
                RegItems.VERDITE_BOOTS.get(),
                OrevolutionTags.Items.verditeIngots, consumer);


        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, RegItems.BASIC_TEMPLATE.get())
                .pattern("ACA")
                .pattern("ABA")
                .pattern("AAA")
                .define('A', Items.COPPER_INGOT)
                .define('B', Items.AMETHYST_SHARD)
                .define('C', RegItems.TIN_INGOT.get())
                .unlockedBy("has_copper_ingot", has(Items.COPPER_INGOT)).save(consumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, RegItems.BRONZE_CROWN.get(), 2)
                .pattern("A A")
                .pattern("AAA")
                .define('A', RegItems.BRONZE_ALLOY.get())
                .unlockedBy(getHasName(RegItems.BRONZE_ALLOY.get()), has(RegItems.BRONZE_ALLOY.get())).save(consumer);

        bronzeCrown(RegItems.BRONZE_CROWN_REDSTONE, () -> Items.REDSTONE).save(consumer);
        bronzeCrown(RegItems.BRONZE_CROWN_DIAMOND, () -> Items.DIAMOND).save(consumer);
        bronzeCrown(RegItems.BRONZE_CROWN_EMERALD, () -> Items.EMERALD).save(consumer);
        bronzeCrown(RegItems.BRONZE_CROWN_LAPIS, () -> Items.LAPIS_LAZULI).save(consumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, RegItems.AETHERSTEEL_TEMPLATE.get())
                .pattern("ACA")
                .pattern("ABA")
                .pattern("AAA")
                .define('A', RegBlocks.AETHERROCK.get())
                .define('B', Items.DIAMOND)
                .define('C', RegItems.AETHERSTEEL_TEMPLATE.get())
                .unlockedBy("has_aethersteel_template", has(RegItems.AETHERSTEEL_TEMPLATE.get())).save(consumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, RegItems.REINFORCED_TEMPLATE.get())
                .pattern("ACA")
                .pattern("ABA")
                .pattern("AAA")
                .define('A', Items.BLACKSTONE)
                .define('B', Items.DIAMOND)
                .define('C', RegItems.REINFORCED_TEMPLATE.get())
                .unlockedBy("has_reinforced_template", has(RegItems.REINFORCED_TEMPLATE.get())).save(consumer);

        smithingReinforced(() -> Items.NETHERITE_HELMET, RegItems.REINFORCED_NETHERITE_HELMET).save(consumer, modLocat("reinforced_netherite_helmet"));
        smithingReinforced(() -> Items.NETHERITE_CHESTPLATE, RegItems.REINFORCED_NETHERITE_CHESTPLATE).save(consumer, modLocat("reinforced_netherite_chestplate"));
        smithingReinforced(() -> Items.NETHERITE_LEGGINGS, RegItems.REINFORCED_NETHERITE_LEGGINGS).save(consumer, modLocat("reinforced_netherite_leggings"));
        smithingReinforced(() -> Items.NETHERITE_BOOTS, RegItems.REINFORCED_NETHERITE_BOOTS).save(consumer, modLocat("reinforced_netherite_boots"));
        
        smithingAether(() -> Items.NETHERITE_SWORD, RegItems.AETHERSTEEL_SWORD).save(consumer, modLocat("aethersteel_sword"));
        smithingAether(() -> Items.NETHERITE_PICKAXE, RegItems.AETHERSTEEL_PICKAXE).save(consumer, modLocat("aethersteel_pickaxe"));
        smithingAether(() -> Items.NETHERITE_AXE, RegItems.AETHERSTEEL_AXE).save(consumer, modLocat("aethersteel_axe"));
        smithingAether(() -> Items.NETHERITE_SHOVEL, RegItems.AETHERSTEEL_SHOVEL).save(consumer, modLocat("aethersteel_shovel"));
        smithingAether(() -> Items.NETHERITE_HOE, RegItems.AETHERSTEEL_HOE).save(consumer, modLocat("aethersteel_hoe"));
        whenLoaded(smithingAether(ModItems.NETHERITE_KNIFE, RegItemsFD.AETHERSTEEL_KNIFE), ModCompat.farmersdelight()).save(consumer, modLocat("aethersteel_knife"));
        whenLoaded(smithingAether(ItemsInit.NETHERITE_SHIELD, RegItems.AETHERSTEEL_SHIELD), ModCompat.shieldexp()).save(consumer, modLocat("aethersteel_shield"));

        smithingBasic(() -> Items.IRON_PICKAXE, RegItems.STEEL_HAMMER).save(consumer, modLocat("steel_hammer_from_smithing"));
        smithingBasic(() -> Items.IRON_SHOVEL, RegItems.STEEL_DIGGER).save(consumer, modLocat("steel_digger_from_smithing"));
        smithingBasic(() -> Items.IRON_HOE, RegItems.STEEL_SCYTHE).save(consumer, modLocat("steel_scythe_smithing"));

        smithingAether(RegItems.REINFORCED_NETHERITE_HELMET, RegItems.AETHERSTEEL_HELMET).save(consumer, modLocat("aethersteel_helmet_from_smithing_smithing"));
        smithingAether(RegItems.REINFORCED_NETHERITE_CHESTPLATE, RegItems.AETHERSTEEL_CHESTPLATE).save(consumer, modLocat("aethersteel_chestplate_smithing"));
        smithingAether(RegItems.REINFORCED_NETHERITE_LEGGINGS, RegItems.AETHERSTEEL_LEGGINGS).save(consumer, modLocat("aethersteel_leggings_smithing"));
        smithingAether(RegItems.REINFORCED_NETHERITE_BOOTS, RegItems.AETHERSTEEL_BOOTS).save(consumer, modLocat("aethersteel_boots_smithing"));
    }

    private void makeToolsExtra(ItemLike sword, ItemLike pickaxe, ItemLike axe, ItemLike shovel, ItemLike hoe, ItemLike knife, ItemLike shield, ItemLike itemIn, ItemLike itemInS, Consumer<FinishedRecipe> consumer) {
        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, hoe)
                .pattern("AA")
                .pattern(" S")
                .pattern(" S")
                .define('A', itemIn)
                .define('S', itemInS)
                .unlockedBy(getHasName(itemInS), has(itemInS)).save(consumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, shovel)
                .pattern("A")
                .pattern("S")
                .pattern("S")
                .define('A', itemIn)
                .define('S', itemInS)
                .unlockedBy(getHasName(itemInS), has(itemInS)).save(consumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, axe)
                .pattern("AA")
                .pattern("AS")
                .pattern(" S")
                .define('A', itemIn)
                .define('S', itemInS)
                .unlockedBy(getHasName(itemInS), has(itemInS)).save(consumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, pickaxe)
                .pattern("AAA")
                .pattern(" S ")
                .pattern(" S ")
                .define('A', itemIn)
                .define('S', itemInS)
                .unlockedBy(getHasName(itemInS), has(itemInS)).save(consumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, sword)
                .pattern("A")
                .pattern("A")
                .pattern("S")
                .define('A', itemIn)
                .define('S', itemInS)
                .unlockedBy(getHasName(itemInS), has(itemInS)).save(consumer);

        whenLoaded(ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, knife)
                .pattern("A")
                .pattern("S")
                .define('A', itemIn)
                .define('S', itemInS)
                .unlockedBy(getHasName(itemInS), has(itemInS)), ModCompat.farmersdelight()).save(consumer);

        whenLoaded(ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, shield)
                .pattern("AAA")
                .pattern("ASA")
                .pattern("AAA")
                .define('A', itemIn)
                .define('S', itemInS)
                .unlockedBy(getHasName(itemInS), has(itemInS)), ModCompat.shieldexp()).save(consumer);
    }
}
