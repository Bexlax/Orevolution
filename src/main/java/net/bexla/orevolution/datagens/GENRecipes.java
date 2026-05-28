package net.bexla.orevolution.datagens;

import com.simibubi.create.AllItems;
import com.simibubi.create.content.kinetics.crusher.CrushingRecipe;
import com.simibubi.create.content.kinetics.mixer.MixingRecipe;
import com.simibubi.create.content.processing.recipe.HeatCondition;
import com.simibubi.create.content.processing.recipe.ProcessingRecipeBuilder;
import net.bexla.orevolution.compatibility.ModCompat;
import net.bexla.orevolution.compatibility.farmersdelight.RegItemsFD;
import net.bexla.orevolution.content.data.utility.OrevolutionTags;
import net.bexla.orevolution.content.types.providers.RecipesProvider;
import net.bexla.orevolution.init.RegBlocks;
import net.bexla.orevolution.init.RegItems;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.common.Tags;
import org.infernalstudios.shieldexp.init.ItemsInit;
import vectorwing.farmersdelight.common.registry.ModItems;

import java.util.List;
import java.util.function.Consumer;
import java.util.function.Supplier;

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

    public GENRecipes(PackOutput output) {
        super(output);
    }

    @Override
    protected void buildRecipes(Consumer<FinishedRecipe> consumer) {
        ore(RegItems.TIN_INGOT.get(), TIN_ORES, 150, 0.4F, "orevolution:tin_ingot", consumer);
        ore(RegItems.PLATINUM_INGOT.get(), PLATINUM_ORE, 200, 0.4F, "orevolution:platinum_ingot", consumer);
        ore(RegItems.TUNGSTEN_INGOT.get(), List.of(RegBlocks.NETHER_TUNGSTEN_ORE.get(), RegItems.RAW_TUNGSTEN.get()), 300, 0.7F, "orevolution:tungsten_ingot", consumer);
        ore(RegItems.AETHERSTEEL_CHUNK.get(), List.of(RegBlocks.PRIMITIVE_AETHERROCK.get()), 600, 3.0F, "orevolution:aethersteel_chunk", consumer);
//        ore(RegItems.AMBER.get(), List.of(RegItems.ROUGH_AMBER.get()), 200, 0.5F, "orevolution:amber", consumer);

        SimpleCookingRecipeBuilder.smelting(Ingredient.of(AllItems.CRUSHED_TIN.asItem()), RecipeCategory.MISC, RegItems.TIN_INGOT.get(), 0.1F, 200);
        SimpleCookingRecipeBuilder.blasting(Ingredient.of(AllItems.CRUSHED_TIN.asItem()), RecipeCategory.MISC, RegItems.TIN_INGOT.get(), 0.1F, 100);

        SimpleCookingRecipeBuilder.smelting(Ingredient.of(AllItems.CRUSHED_PLATINUM.asItem()), RecipeCategory.MISC, RegItems.PLATINUM_INGOT.get(), 0.1F, 200);
        SimpleCookingRecipeBuilder.blasting(Ingredient.of(AllItems.CRUSHED_PLATINUM.asItem()), RecipeCategory.MISC, RegItems.PLATINUM_INGOT.get(), 0.1F, 100);

        SimpleCookingRecipeBuilder.smelting(Ingredient.of(RegItems.CRUSHED_TUNGSTEN.get()), RecipeCategory.MISC, RegItems.TUNGSTEN_INGOT.get(), 0.1F, 200);
        SimpleCookingRecipeBuilder.blasting(Ingredient.of(RegItems.CRUSHED_TUNGSTEN.get()), RecipeCategory.MISC, RegItems.TUNGSTEN_INGOT.get(), 0.1F, 100);

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



        //nugget(RegItems.TIN_NUGGET.get(), TIN_TOOLS_SMELTABLES, 0.1F, "orevolution:tin_nugget", consumer);
        //nugget(RegItems.PLATINUM_NUGGET.get(), PLATINUM_TOOLS_SMELTABLES, 0.2F, "orevolution:platinum_nugget", consumer);

        autoCompact(RegBlocks.TIN_BLOCK.get().asItem(), RegItems.TIN_INGOT.get(), consumer);
        autoCompact(RegBlocks.PLATINUM_BLOCK.get().asItem(), RegItems.PLATINUM_INGOT.get(), consumer);
        autoCompact(RegBlocks.TUNGSTEN_BLOCK.get().asItem(), RegItems.TUNGSTEN_INGOT.get(), consumer);
        autoCompact(RegBlocks.AETHERSTEEL_BLOCK.get().asItem(), RegItems.AETHERSTEEL_INGOT.get(), consumer);
        autoCompact(RegBlocks.BRONZE_BLOCK.get().asItem(), RegItems.BRONZE_ALLOY.get(), consumer);
        autoCompact(RegBlocks.STEEL_BLOCK.get().asItem(), RegItems.STEEL_ALLOY.get(), consumer);
        autoCompact(RegBlocks.VERDITE_BLOCK.get().asItem(), RegItems.VERDITE_INGOT.get(), consumer);
//        autoCompact(RegBlocks.AMBER_BLOCK.get().asItem(), RegItems.AMBER.get(), consumer);

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

        quadTransform(RegBlocks.LIVINGSTONE_BRICKS, RegBlocks.LIVINGSTONE_BLOCK).save(consumer);
        quadTransform(RegBlocks.VERDITE_BRICKS, RegBlocks.LIVINGSTONE_BLOCK).save(consumer);

        makePillar(RegBlocks.AETHERROCK_BRICKS, RegBlocks.AETHERROCK).save(consumer);

        quadTransform(RegBlocks.AETHERROCK_TILES, RegBlocks.POLISHED_AETHERROCK).save(consumer);

        quadTransformItem(RegBlocks.POLISHED_TUNGSTEN, RegItems.TUNGSTEN_INGOT, 2).save(consumer);
        quadTransform(RegBlocks.TUNGSTEN_BRICKS, RegBlocks.POLISHED_TUNGSTEN).save(consumer);

        makePillarItem(RegBlocks.STEEL_PILLAR, RegItems.STEEL_ALLOY).save(consumer);

        quadTransformItem(RegBlocks.PLATINUM_TILES, RegItems.PLATINUM_INGOT, 2).save(consumer);
        quadTransformItem(RegBlocks.GOLD_TILES, () -> Items.GOLD_INGOT, 2).save(consumer);
        makePillarItem(RegBlocks.PLATINUM_PILLAR, RegItems.PLATINUM_INGOT).save(consumer);
        makePillarItem(RegBlocks.GOLD_PILLAR, () -> Items.GOLD_INGOT).save(consumer);

        makeBarsItem(RegBlocks.PLATINUM_BARS, RegItems.PLATINUM_INGOT).save(consumer);
        makeBarsItem(RegBlocks.TUNGSTEN_BARS, RegItems.TUNGSTEN_INGOT).save(consumer);
        makeBarsItem(RegBlocks.BRONZE_BARS, RegItems.BRONZE_ALLOY).save(consumer);
        makeBarsItem(RegBlocks.STEEL_BARS, RegItems.STEEL_ALLOY).save(consumer);
        makeBarsItem(RegBlocks.TIN_BARS, RegItems.TIN_INGOT).save(consumer);
        makeBarsItem(RegBlocks.GOLD_BARS, () -> Items.GOLD_INGOT).save(consumer);

        makeChiseledStonecutting(RegBlocks.CHISELED_TUNGSTEN_BLOCK, RegBlocks.TUNGSTEN_BLOCK, consumer);
        stonecutting(RegBlocks.CHISELED_TUNGSTEN_BRICKS, RegBlocks.TUNGSTEN_BLOCK.get()).save(consumer);
        makeChiseledStonecutting(RegBlocks.CHISELED_TUNGSTEN_BRICKS, RegBlocks.TUNGSTEN_BRICKS, consumer);

        alloyLow("tin_ingot", RegItems.BRONZE_ALLOY.get(), OrevolutionTags.Items.tinIngots, Tags.Items.INGOTS_COPPER).save(consumer);
        unlessLoaded(alloyHigh("iron_ingot", RegItems.STEEL_ALLOY.get(), Tags.Items.INGOTS_IRON, Items.COAL, Items.BLAZE_POWDER), ModCompat.create()).save(consumer);
        unlessLoaded(alloyHigh("aethersteel_ingot", RegItems.AETHERSTEEL_INGOT.get(), RegItems.AETHERSTEEL_CHUNK.get(), OrevolutionTags.Items.platIngots), ModCompat.create()).save(consumer);

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

        ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, RegItems.SHINY_CARROT.get())
                .requires(RegItems.PLATINUM_INGOT.get()).requires(RegItems.PLATINUM_INGOT.get()).requires(RegItems.CAVE_CARROT.get())
                .unlockedBy("has_platinum_ingot", has(RegItems.PLATINUM_INGOT.get())).save(consumer);

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

        armorSet("bronze_ingot",
                RegItems.BRONZE_HELMET.get(),
                RegItems.BRONZE_CHESTPLATE.get(),
                RegItems.BRONZE_LEGGINGS.get(),
                RegItems.BRONZE_BOOTS.get(),
                OrevolutionTags.Items.bronzeIngots, consumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, RegItems.BASIC_TEMPLATE.get())
                .pattern("ACA")
                .pattern("ABA")
                .pattern("AAA")
                .define('A', Items.COPPER_INGOT)
                .define('B', Items.AMETHYST_SHARD)
                .define('C', RegItems.TIN_INGOT.get())
                .unlockedBy("has_copper_ingot", has(Items.COPPER_INGOT)).save(consumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, RegItems.AETHERSTEEL_TEMPLATE.get(), 2)
                .pattern("ACA")
                .pattern("ABA")
                .pattern("AAA")
                .define('A', RegBlocks.AETHERROCK.get())
                .define('B', Items.DIAMOND)
                .define('C', RegItems.AETHERSTEEL_TEMPLATE.get())
                .unlockedBy("has_aethersteel_template", has(RegItems.AETHERSTEEL_TEMPLATE.get())).save(consumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, RegItems.REINFORCED_TEMPLATE.get(), 2)
                .pattern("ACA")
                .pattern("ABA")
                .pattern("AAA")
                .define('A', Items.BLACKSTONE)
                .define('B', Items.DIAMOND)
                .define('C', RegItems.REINFORCED_TEMPLATE.get())
                .unlockedBy("has_reinforced_template", has(RegItems.REINFORCED_TEMPLATE.get())).save(consumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, RegBlocks.TUNGSTEN_SPONGE.get(), 1)
                .pattern(" A ")
                .pattern("AXA")
                .pattern(" A ")
                .define('A', RegItems.TUNGSTEN_INGOT.get())
                .define('X', Items.SPONGE)
                .unlockedBy("has_sponge", has(Items.SPONGE)).save(consumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, RegItems.BRONZE_RADAR.get(), 1)
                .pattern(" A ")
                .pattern("AXA")
                .pattern("AAA")
                .define('A', RegItems.BRONZE_ALLOY.get())
                .define('X', Items.REDSTONE)
                .unlockedBy("has_bronze_alloy", has(RegItems.BRONZE_ALLOY.get())).save(consumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, RegItems.PLATINUM_BERRIES.get(), 1)
                .pattern("AAA")
                .pattern("AXA")
                .pattern("AAA")
                .define('A', RegItems.PLATINUM_NUGGET.get())
                .define('X', Items.SWEET_BERRIES)
                .unlockedBy("has_sweet_berries", has(Items.SWEET_BERRIES)).save(consumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, RegItems.VERDITE_SPIDER_EYE.get(), 1)
                .pattern("AAA")
                .pattern("AXA")
                .pattern("AAA")
                .define('A', RegItems.VERDITE_NUGGET.get())
                .define('X', Items.SPIDER_EYE)
                .unlockedBy("has_sweet_berries", has(Items.SWEET_BERRIES)).save(consumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, RegItems.BRONZE_TOTEM.get(), 1)
                .pattern("AAA")
                .pattern(" A ")
                .define('A', RegItems.BRONZE_ALLOY.get())
                .unlockedBy("has_bronze_alloy", has(RegItems.BRONZE_ALLOY.get())).save(consumer);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, RegItems.BRONZE_TOTEM_EMERALD.get(), 1)
                .requires(RegItems.BRONZE_TOTEM.get())
                .requires(Items.EMERALD)
                .requires(Items.EMERALD)
                .requires(Items.EMERALD)
                .unlockedBy("has_bronze_totem", has(RegItems.BRONZE_TOTEM.get())).save(consumer);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, RegItems.BRONZE_TOTEM_DIAMOND.get(), 1)
                .requires(RegItems.BRONZE_TOTEM.get())
                .requires(Items.DIAMOND)
                .requires(Items.DIAMOND)
                .requires(Items.DIAMOND)
                .unlockedBy("has_bronze_totem", has(RegItems.BRONZE_TOTEM.get())).save(consumer);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, RegItems.BRONZE_TOTEM_LAPIS_LAZULI.get(), 1)
                .requires(RegItems.BRONZE_TOTEM.get())
                .requires(Items.LAPIS_LAZULI)
                .requires(Items.LAPIS_LAZULI)
                .requires(Items.LAPIS_LAZULI)
                .unlockedBy("has_bronze_totem", has(RegItems.BRONZE_TOTEM.get())).save(consumer);

        smithingReinforced(() -> Items.NETHERITE_HELMET, RegItems.REINFORCED_NETHERITE_HELMET).save(consumer, modLocat("reinforced_netherite_helmet"));
        smithingReinforced(() -> Items.NETHERITE_CHESTPLATE, RegItems.REINFORCED_NETHERITE_CHESTPLATE).save(consumer, modLocat("reinforced_netherite_chestplate"));
        smithingReinforced(() -> Items.NETHERITE_LEGGINGS, RegItems.REINFORCED_NETHERITE_LEGGINGS).save(consumer, modLocat("reinforced_netherite_leggings"));
        smithingReinforced(() -> Items.NETHERITE_BOOTS, RegItems.REINFORCED_NETHERITE_BOOTS).save(consumer, modLocat("reinforced_netherite_boots"));

        smithingReinforced(RegItems.BRONZE_HELMET, RegItems.TUNGSTEN_HELMET).save(consumer, modLocat("tungsten_helmet"));
        smithingReinforced(RegItems.BRONZE_CHESTPLATE, RegItems.TUNGSTEN_CHESTPLATE).save(consumer, modLocat("tungsten_chestplate"));
        smithingReinforced(RegItems.BRONZE_LEGGINGS, RegItems.TUNGSTEN_LEGGINGS).save(consumer, modLocat("tungsten_leggings"));
        smithingReinforced(RegItems.BRONZE_BOOTS, RegItems.TUNGSTEN_BOOTS).save(consumer, modLocat("tungsten_boots"));

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
        smithingBasic(() -> Items.IRON_AXE, RegItems.STEEL_BROADAXE).save(consumer, modLocat("steel_broadaxe_smithing"));

        smithingAether(RegItems.REINFORCED_NETHERITE_HELMET, RegItems.AETHERSTEEL_HELMET).save(consumer, modLocat("aethersteel_helmet_from_smithing_smithing"));
        smithingAether(RegItems.REINFORCED_NETHERITE_CHESTPLATE, RegItems.AETHERSTEEL_CHESTPLATE).save(consumer, modLocat("aethersteel_chestplate_smithing"));
        smithingAether(RegItems.REINFORCED_NETHERITE_LEGGINGS, RegItems.AETHERSTEEL_LEGGINGS).save(consumer, modLocat("aethersteel_leggings_smithing"));
        smithingAether(RegItems.REINFORCED_NETHERITE_BOOTS, RegItems.AETHERSTEEL_BOOTS).save(consumer, modLocat("aethersteel_boots_smithing"));

        crushedToRaw("crushed_tungsten", Ingredient.of(OrevolutionTags.Items.tungsOres), RegItems.CRUSHED_TUNGSTEN, Blocks.NETHERRACK::asItem).build(consumer);
        processing(CrushingRecipe::new, "raw_crushed_tungsten")
                .require(RegItems.RAW_TUNGSTEN.get())
                .output(RegItems.CRUSHED_TUNGSTEN.get(), 1)
                .output(0.75f, AllItems.EXP_NUGGET.get(), 1)
                .duration(200);
        processing(CrushingRecipe::new, "raw_block_crushed_tungsten")
                .require(RegBlocks.RAW_TUNGSTEN_BLOCK.get())
                .output(RegItems.CRUSHED_TUNGSTEN.get(), 9)
                .output(0.75f, AllItems.EXP_NUGGET.get(), 9)
                .duration(300);

        crushedToRaw("crushed_aethersteel", Ingredient.of(RegBlocks.PRIMITIVE_AETHERROCK.get()), RegItems.CRUSHED_AETHERSTEEL, Blocks.END_STONE::asItem).build(consumer);
        processing(CrushingRecipe::new, "aetherrock_crushed")
                .require(RegBlocks.AETHERROCK.get())
                .output(0.40f, RegItems.CRUSHED_AETHERSTEEL.get(), 1)
                .output(0.25f, RegItems.CRUSHED_AETHERSTEEL.get(), 1)
                .output(0.1f, AllItems.EXP_NUGGET.get())
                .duration(250);

        processing(CrushingRecipe::new, "end_xp_block_crushed")
                .require(RegBlocks.END_XP_ORE.get())
                .output(AllItems.EXP_NUGGET.get(), 3)
                .output(.75f, AllItems.EXP_NUGGET.get())
                .output(.35f, AllItems.EXP_NUGGET.get(), 2)
                .output(.12f, Blocks.END_STONE)
                .duration(200);
        processing(CrushingRecipe::new, "nether_xp_block_crushed")
                .require(RegBlocks.NETHER_XP_ORE.get())
                .output(AllItems.EXP_NUGGET.get(), 2)
                .output(0.65f, AllItems.EXP_NUGGET.get())
                .output(0.25f, AllItems.EXP_NUGGET.get(), 2)
                .output(0.12f, Blocks.NETHERRACK)
                .duration(200);

        processing(MixingRecipe::new, "aethersteel_ingot")
                .output(RegItems.AETHERSTEEL_INGOT.get(), 1)
                .require(OrevolutionTags.Items.platIngots)
                .require(OrevolutionTags.Items.platIngots)
                .require(OrevolutionTags.Items.platIngots)
                .require(OrevolutionTags.Items.platIngots)
                .require(RegItems.AETHERSTEEL_CHUNK.get())
                .require(RegItems.AETHERSTEEL_CHUNK.get())
                .require(RegItems.AETHERSTEEL_CHUNK.get())
                .require(RegItems.AETHERSTEEL_CHUNK.get())
                .requiresHeat(HeatCondition.SUPERHEATED)
                .build(consumer);

        processing(MixingRecipe::new, "aethersteel_ingot_crushed_aethersteel")
                .output(RegItems.AETHERSTEEL_INGOT.get(), 1)
                .require(OrevolutionTags.Items.platIngots)
                .require(OrevolutionTags.Items.platIngots)
                .require(OrevolutionTags.Items.platIngots)
                .require(OrevolutionTags.Items.platIngots)
                .require(RegItems.CRUSHED_AETHERSTEEL.get())
                .require(RegItems.CRUSHED_AETHERSTEEL.get())
                .require(RegItems.CRUSHED_AETHERSTEEL.get())
                .require(RegItems.CRUSHED_AETHERSTEEL.get())
                .requiresHeat(HeatCondition.SUPERHEATED)
                .build(consumer);

        processing(MixingRecipe::new, "aethersteel_ingot_crushed_platinum")
                .output(RegItems.AETHERSTEEL_INGOT.get(), 1)
                .require(AllItems.CRUSHED_PLATINUM.asItem())
                .require(AllItems.CRUSHED_PLATINUM.asItem())
                .require(AllItems.CRUSHED_PLATINUM.asItem())
                .require(AllItems.CRUSHED_PLATINUM.asItem())
                .require(RegItems.AETHERSTEEL_CHUNK.get())
                .require(RegItems.AETHERSTEEL_CHUNK.get())
                .require(RegItems.AETHERSTEEL_CHUNK.get())
                .require(RegItems.AETHERSTEEL_CHUNK.get())
                .requiresHeat(HeatCondition.SUPERHEATED)
                .build(consumer);

        processing(MixingRecipe::new, "aethersteel_ingot_both_crushed")
                .output(RegItems.AETHERSTEEL_INGOT.get(), 1)
                .require(AllItems.CRUSHED_PLATINUM.asItem())
                .require(AllItems.CRUSHED_PLATINUM.asItem())
                .require(AllItems.CRUSHED_PLATINUM.asItem())
                .require(AllItems.CRUSHED_PLATINUM.asItem())
                .require(RegItems.CRUSHED_AETHERSTEEL.get())
                .require(RegItems.CRUSHED_AETHERSTEEL.get())
                .require(RegItems.CRUSHED_AETHERSTEEL.get())
                .require(RegItems.CRUSHED_AETHERSTEEL.get())
                .requiresHeat(HeatCondition.SUPERHEATED)
                .build(consumer);

        processing(MixingRecipe::new, "steel_alloy")
                .output(RegItems.STEEL_ALLOY.get(), 1)
                .require(Items.IRON_INGOT)
                .require(Items.IRON_INGOT)
                .require(Items.IRON_INGOT)
                .require(Items.COAL)
                .require(Items.COAL)
                .require(Items.COAL)
                .requiresHeat(HeatCondition.HEATED)
                .build(consumer);
    }

    protected ProcessingRecipeBuilder<CrushingRecipe> crushedToRaw(String id, Ingredient tag, Supplier<Item> result, Supplier<Item> residue) {
        return processing(CrushingRecipe::new, id)
                .require(tag)
                .output(result.get(), 1)
                .output(0.75f, result.get(), 1)
                .output(0.75f, AllItems.EXP_NUGGET.get())
                .output(0.12f, residue.get())
                .duration(200);
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

        whenLoaded(ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, shield)
                .pattern("AAA")
                .pattern("ASA")
                .pattern("AAA")
                .define('A', itemIn)
                .define('S', itemInS)
                .unlockedBy(getHasName(itemInS), has(itemInS)), ModCompat.shieldexp()).save(consumer);
    }
}
