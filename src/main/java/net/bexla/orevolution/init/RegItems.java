package net.bexla.orevolution.init;

import com.teamabnormals.blueprint.core.util.registry.ItemSubRegistryHelper;
import net.bexla.orevolution.Orevolution;
import net.bexla.orevolution.content.data.OrevolutionArmorTiers;
import net.bexla.orevolution.content.data.OrevolutionToolTiers;
import net.bexla.orevolution.content.types.item.*;
import net.bexla.orevolution.content.types.item.modeled.*;
import net.minecraft.ChatFormatting;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.*;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.RegistryObject;

import static net.bexla.orevolution.content.data.utility.OrevolutionUtils.Rarities.Aether;


@Mod.EventBusSubscriber(modid = Orevolution.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class RegItems {
    public static final ItemSubRegistryHelper HELPER = Orevolution.REGISTRY_HELPER.getItemSubHelper();

    public static RegistryObject<Item> normalItem(String name) {
        return HELPER.createItem(name, () -> new Item(new Item.Properties()));
    }

    //~//~~Crafting materials~~//~//
        /*Raw ores*/
    public static final RegistryObject<Item> RAW_TIN = normalItem("raw_tin");
    public static final RegistryObject<Item> RAW_PLATINUM = normalItem("raw_platinum");
    public static final RegistryObject<Item> RAW_TUNGSTEN = normalItem("raw_tungsten");
    public static final RegistryObject<Item> AETHERSTEEL_CHUNK = normalItem("aethersteel_chunk");
        /*Ingots*/
    public static final RegistryObject<Item> TIN_INGOT = normalItem("tin_ingot");
    public static final RegistryObject<Item> PLATINUM_INGOT = normalItem("platinum_ingot");
    public static final RegistryObject<Item> TUNGSTEN_INGOT = normalItem("tungsten_ingot");
    public static final RegistryObject<Item> AETHERSTEEL_INGOT = normalItem("aethersteel_ingot");
    public static final RegistryObject<Item> VERDITE_INGOT = normalItem("verdite_ingot");
        /*Alloys*/
    public static final RegistryObject<Item> BRONZE_ALLOY = normalItem("bronze_ingot");
    public static final RegistryObject<Item> STEEL_ALLOY = normalItem("steel_ingot");
        /*Nuggets*/
    public static final RegistryObject<Item> TIN_NUGGET = normalItem("tin_nugget");
    public static final RegistryObject<Item> PLATINUM_NUGGET = normalItem("platinum_nugget");
    public static final RegistryObject<Item> TUNGSTEN_NUGGET = normalItem("tungsten_nugget");
    public static final RegistryObject<Item> VERDITE_NUGGET = normalItem("verdite_nugget");
    public static final RegistryObject<Item> LIVINGSTONE_SHARD = normalItem("livingstone_shard");
        /*Crushed*/
    public static final RegistryObject<Item> CRUSHED_TUNGSTEN = normalItem("crushed_raw_tungsten");
    public static final RegistryObject<Item> CRUSHED_AETHERSTEEL = normalItem("crushed_raw_aethersteel");

    /*Smithing Templates*/
    public static final RegistryObject<Item> AETHERSTEEL_TEMPLATE = HELPER.createItem("aethersteel_smithing_template", OrevolutionSmithingTemplate::createAethersteelUpgradeTemplate);
    public static final RegistryObject<Item> REINFORCED_TEMPLATE = HELPER.createItem("reinforced_smithing_template", OrevolutionSmithingTemplate::createReinforcedUpgradeTemplate);
    public static final RegistryObject<Item> BASIC_TEMPLATE = HELPER.createItem("basic_smithing_template", OrevolutionSmithingTemplate::createBasicUpgradeTemplate);


    //~//~~Armors, Tools and Weapons~~//~//
    public static final RegistryObject<Item> TIN_SHIELD = HELPER.createItem("tin_shield", () -> new ShieldItem(new Item.Properties().durability(98)));
    public static final RegistryObject<Item> TIN_SWORD = HELPER.createItem("tin_sword", () -> new SwordItem(OrevolutionToolTiers.TIN, 3, -2.4f, new Item.Properties()));
    public static final RegistryObject<Item> TIN_PICKAXE = HELPER.createItem("tin_pickaxe", () -> new PickaxeItem(OrevolutionToolTiers.TIN, 1, -2.6f, new Item.Properties()));
    public static final RegistryObject<Item> TIN_AXE = HELPER.createItem("tin_axe", () -> new AxeItem(OrevolutionToolTiers.TIN, 7F, -3.2f, new Item.Properties()));
    public static final RegistryObject<Item> TIN_SHOVEL = HELPER.createItem("tin_shovel", () -> new ShovelItem(OrevolutionToolTiers.TIN, 1.5f, -3f, new Item.Properties()));
    public static final RegistryObject<Item> TIN_HOE = HELPER.createItem("tin_hoe", () -> new HoeItem(OrevolutionToolTiers.TIN, 1, -2f, new Item.Properties()));
        /*Platinum set*/
    public static final RegistryObject<Item> PLATINUM_HELMET = HELPER.createItem("platinum_helmet", () -> new ArmorItem(OrevolutionArmorTiers.PLATINUM, ArmorItem.Type.HELMET, new Item.Properties()));
    public static final RegistryObject<Item> PLATINUM_CHESTPLATE = HELPER.createItem("platinum_chestplate", () -> new ArmorItem(OrevolutionArmorTiers.PLATINUM, ArmorItem.Type.CHESTPLATE, new Item.Properties()));
    public static final RegistryObject<Item> PLATINUM_LEGGINGS = HELPER.createItem("platinum_leggings", () -> new ArmorItem(OrevolutionArmorTiers.PLATINUM, ArmorItem.Type.LEGGINGS, new Item.Properties()));
    public static final RegistryObject<Item> PLATINUM_BOOTS = HELPER.createItem("platinum_boots", () -> new ArmorItem(OrevolutionArmorTiers.PLATINUM, ArmorItem.Type.BOOTS, new Item.Properties()));

    public static final RegistryObject<Item> PLATINUM_SHIELD = HELPER.createItem("platinum_shield", () -> new ShieldItem(new Item.Properties().durability(229)));
    public static final RegistryObject<Item> PLATINUM_SWORD = HELPER.createItem("platinum_sword", () -> new SwordItem(OrevolutionToolTiers.PLATINUM, 3, -2.4f, new Item.Properties()));
    public static final RegistryObject<Item> PLATINUM_PICKAXE = HELPER.createItem("platinum_pickaxe", () -> new PickaxeItem(OrevolutionToolTiers.PLATINUM, 1, -2.6f, new Item.Properties()));
    public static final RegistryObject<Item> PLATINUM_AXE = HELPER.createItem("platinum_axe", () -> new AxeItem(OrevolutionToolTiers.PLATINUM, 6F, -3.2f, new Item.Properties()));
    public static final RegistryObject<Item> PLATINUM_SHOVEL = HELPER.createItem("platinum_shovel", () -> new ShovelItem(OrevolutionToolTiers.PLATINUM, 1.5f, -3f, new Item.Properties()));
    public static final RegistryObject<Item> PLATINUM_HOE = HELPER.createItem("platinum_hoe", () -> new HoeItem(OrevolutionToolTiers.PLATINUM, 1, -2f, new Item.Properties()));
        /*Reinforced Netherite Armor set*/
    public static final RegistryObject<Item> REINFORCED_NETHERITE_HELMET = HELPER.createItem("reinforced_netherite_helmet", () -> new ArmorItem(OrevolutionArmorTiers.REINFORCED_NETHERITE, ArmorItem.Type.HELMET, new Item.Properties().fireResistant()));
    public static final RegistryObject<Item> REINFORCED_NETHERITE_CHESTPLATE = HELPER.createItem("reinforced_netherite_chestplate", () -> new ReinforcedArmor(OrevolutionArmorTiers.REINFORCED_NETHERITE, ArmorItem.Type.CHESTPLATE, new Item.Properties().fireResistant()));
    public static final RegistryObject<Item> REINFORCED_NETHERITE_LEGGINGS = HELPER.createItem("reinforced_netherite_leggings", () -> new ReinforcedArmor(OrevolutionArmorTiers.REINFORCED_NETHERITE, ArmorItem.Type.LEGGINGS, new Item.Properties().fireResistant()));
    public static final RegistryObject<Item> REINFORCED_NETHERITE_BOOTS = HELPER.createItem("reinforced_netherite_boots", () -> new ArmorItem(OrevolutionArmorTiers.REINFORCED_NETHERITE, ArmorItem.Type.BOOTS, new Item.Properties().fireResistant()));
        /*Aethersteel set*/
    public static final RegistryObject<Item> AETHERSTEEL_HELMET = HELPER.createItem("aethersteel_helmet", () -> new ArmorItem(OrevolutionArmorTiers.AETHERSTEEL, ArmorItem.Type.HELMET, new Item.Properties().fireResistant().rarity(Aether)));
    public static final RegistryObject<Item> AETHERSTEEL_CHESTPLATE = HELPER.createItem("aethersteel_chestplate", () -> new AethersteelChestplate(OrevolutionArmorTiers.AETHERSTEEL, ArmorItem.Type.CHESTPLATE, new Item.Properties().fireResistant().rarity(Aether)));
    public static final RegistryObject<Item> AETHERSTEEL_LEGGINGS = HELPER.createItem("aethersteel_leggings", () -> new ArmorItem(OrevolutionArmorTiers.AETHERSTEEL, ArmorItem.Type.LEGGINGS, new Item.Properties().fireResistant().rarity(Aether)));
    public static final RegistryObject<Item> AETHERSTEEL_BOOTS = HELPER.createItem("aethersteel_boots", () -> new ArmorItem(OrevolutionArmorTiers.AETHERSTEEL, ArmorItem.Type.BOOTS, new Item.Properties().fireResistant().rarity(Aether)));

    public static final RegistryObject<Item> AETHERSTEEL_SHIELD = HELPER.createItem("aethersteel_shield", () -> new ShieldItem(new Item.Properties().durability(841)));
    public static final RegistryObject<Item> AETHERSTEEL_SWORD = HELPER.createItem("aethersteel_sword", () -> new SwordItem(OrevolutionToolTiers.AETHERSTEEL, 3, -2.4f, new Item.Properties().fireResistant().rarity(Aether)));
    public static final RegistryObject<Item> AETHERSTEEL_PICKAXE = HELPER.createItem("aethersteel_pickaxe", () -> new PickaxeItem(OrevolutionToolTiers.AETHERSTEEL, 1, -2.6f, new Item.Properties().fireResistant().rarity(Aether)));
    public static final RegistryObject<Item> AETHERSTEEL_AXE = HELPER.createItem("aethersteel_axe", () -> new AxeItem(OrevolutionToolTiers.AETHERSTEEL, 5F, -3.2f, new Item.Properties().fireResistant().rarity(Aether)));
    public static final RegistryObject<Item> AETHERSTEEL_SHOVEL = HELPER.createItem("aethersteel_shovel", () -> new ShovelItem(OrevolutionToolTiers.AETHERSTEEL, 1.5f, -3f, new Item.Properties().fireResistant().rarity(Aether)));
    public static final RegistryObject<Item> AETHERSTEEL_HOE = HELPER.createItem("aethersteel_hoe", () -> new HoeItem(OrevolutionToolTiers.AETHERSTEEL, 1, -2f, new Item.Properties().fireResistant().rarity(Aether)));
        /*Livingstone set*/
    public static final RegistryObject<Item> LIVINGSTONE_HELMET = HELPER.createItem("livingstone_helmet", () -> new ArmorItem(OrevolutionArmorTiers.LIVINGSTONE, ArmorItem.Type.HELMET, new Item.Properties()));
    public static final RegistryObject<Item> LIVINGSTONE_CHESTPLATE = HELPER.createItem("livingstone_chestplate", () -> new LivingstoneChestplate(OrevolutionArmorTiers.LIVINGSTONE, ArmorItem.Type.CHESTPLATE, new Item.Properties()));
    public static final RegistryObject<Item> LIVINGSTONE_LEGGINGS = HELPER.createItem("livingstone_leggings", () -> new ArmorItem(OrevolutionArmorTiers.LIVINGSTONE, ArmorItem.Type.LEGGINGS, new Item.Properties()));
    public static final RegistryObject<Item> LIVINGSTONE_BOOTS = HELPER.createItem("livingstone_boots", () -> new ArmorItem(OrevolutionArmorTiers.LIVINGSTONE, ArmorItem.Type.BOOTS, new Item.Properties()));

    public static final RegistryObject<Item> LIVINGSTONE_SHIELD = HELPER.createItem("livingstone_shield", () -> new ShieldItem(new Item.Properties().durability(98)));
    public static final RegistryObject<Item> LIVINGSTONE_SWORD = HELPER.createItem("livingstone_sword", () -> new SwordItem(OrevolutionToolTiers.LIVINGSTONE, 3, -2.4f, new Item.Properties()));
    public static final RegistryObject<Item> LIVINGSTONE_PICKAXE = HELPER.createItem("livingstone_pickaxe", () -> new PickaxeItem(OrevolutionToolTiers.LIVINGSTONE, 1, -2.6f, new Item.Properties()));
    public static final RegistryObject<Item> LIVINGSTONE_AXE = HELPER.createItem("livingstone_axe", () -> new AxeItem(OrevolutionToolTiers.LIVINGSTONE, 5F, -3.2f, new Item.Properties()));
    public static final RegistryObject<Item> LIVINGSTONE_SHOVEL = HELPER.createItem("livingstone_shovel", () -> new ShovelItem(OrevolutionToolTiers.LIVINGSTONE, 1.5f, -3f, new Item.Properties()));
    public static final RegistryObject<Item> LIVINGSTONE_HOE = HELPER.createItem("livingstone_hoe", () -> new HoeItem(OrevolutionToolTiers.LIVINGSTONE, 1, -2f, new Item.Properties()));
        /*Verdite set*/
    public static final RegistryObject<Item> VERDITE_HELMET = HELPER.createItem("verdite_helmet", () -> new ArmorItem(OrevolutionArmorTiers.VERDITE, ArmorItem.Type.HELMET, new Item.Properties()));
    public static final RegistryObject<Item> VERDITE_CHESTPLATE = HELPER.createItem("verdite_chestplate", () -> new ArmorItem(OrevolutionArmorTiers.VERDITE, ArmorItem.Type.CHESTPLATE, new Item.Properties()));
    public static final RegistryObject<Item> VERDITE_LEGGINGS = HELPER.createItem("verdite_leggings", () -> new ArmorItem(OrevolutionArmorTiers.VERDITE, ArmorItem.Type.LEGGINGS, new Item.Properties()));
    public static final RegistryObject<Item> VERDITE_BOOTS = HELPER.createItem("verdite_boots", () -> new ArmorItem(OrevolutionArmorTiers.VERDITE, ArmorItem.Type.BOOTS, new Item.Properties()));

    public static final RegistryObject<Item> VERDITE_SHIELD = HELPER.createItem("verdite_shield", () -> new ShieldItem(new Item.Properties().durability(153)));
    public static final RegistryObject<Item> VERDITE_SWORD = HELPER.createItem("verdite_sword", () -> new SwordItem(OrevolutionToolTiers.VERDITE, 3, -2.4f, new Item.Properties()));
    public static final RegistryObject<Item> VERDITE_PICKAXE = HELPER.createItem("verdite_pickaxe", () -> new PickaxeItem(OrevolutionToolTiers.VERDITE, 1, -2.6f, new Item.Properties()));
    public static final RegistryObject<Item> VERDITE_AXE = HELPER.createItem("verdite_axe", () -> new AxeItem(OrevolutionToolTiers.VERDITE, 5F, -3.2f, new Item.Properties()));
    public static final RegistryObject<Item> VERDITE_SHOVEL = HELPER.createItem("verdite_shovel", () -> new ShovelItem(OrevolutionToolTiers.VERDITE, 1.5f, -3f, new Item.Properties()));
    public static final RegistryObject<Item> VERDITE_HOE = HELPER.createItem("verdite_hoe", () -> new HoeItem(OrevolutionToolTiers.VERDITE, 1, -2f, new Item.Properties()));
        /*Amber set*/
//    public static final RegistryObject<Item> AMBER_HELMET = HELPER.createItem("amber_helmet", () -> new ArmorItem(OrevolutionArmorTiers.AMBER, ArmorItem.Type.HELMET, new Item.Properties()));
//    public static final RegistryObject<Item> AMBER_CHESTPLATE = HELPER.createItem("amber_chestplate", () -> new ArmorItem(OrevolutionArmorTiers.AMBER, ArmorItem.Type.CHESTPLATE, new Item.Properties()));
//    public static final RegistryObject<Item> AMBER_LEGGINGS = HELPER.createItem("amber_leggings", () -> new ArmorItem(OrevolutionArmorTiers.AMBER, ArmorItem.Type.LEGGINGS, new Item.Properties()));
//    public static final RegistryObject<Item> AMBER_BOOTS = HELPER.createItem("amber_boots", () -> new ArmorItem(OrevolutionArmorTiers.AMBER, ArmorItem.Type.BOOTS, new Item.Properties()));
//
//    public static final RegistryObject<Item> AMBER_SHIELD = HELPER.createItem("amber_shield", () -> new ShieldItem(new Item.Properties().durability(315)));
//    public static final RegistryObject<Item> AMBER_SWORD = HELPER.createItem("amber_sword", () -> new SwordItem(OrevolutionToolTiers.AMBER, 3, -2.4f, new Item.Properties()));
//    public static final RegistryObject<Item> AMBER_PICKAXE = HELPER.createItem("amber_pickaxe", () -> new PickaxeItem(OrevolutionToolTiers.AMBER, 1, -2.6f, new Item.Properties()));
//    public static final RegistryObject<Item> AMBER_AXE = HELPER.createItem("amber_axe", () -> new AxeItem(OrevolutionToolTiers.AMBER, 5F, -2.2f, new Item.Properties()));
//    public static final RegistryObject<Item> AMBER_SHOVEL = HELPER.createItem("amber_shovel", () -> new ShovelItem(OrevolutionToolTiers.AMBER, 1.5f, -3f, new Item.Properties()));
//    public static final RegistryObject<Item> AMBER_HOE = HELPER.createItem("amber_hoe", () -> new HoeItem(OrevolutionToolTiers.AMBER, 1, -2f, new Item.Properties()));
        /*Bronze set*/
    public static final RegistryObject<Item> BRONZE_TOTEM = normalItem("bronze_totem");
    public static final RegistryObject<Item> BRONZE_TOTEM_DIAMOND = HELPER.createItem("bronze_totem_diamond", () -> new BronzeTotemItem(new Item.Properties().stacksTo(1), () -> MobEffects.REGENERATION, "diamond", ChatFormatting.AQUA));
    public static final RegistryObject<Item> BRONZE_TOTEM_LAPIS_LAZULI = HELPER.createItem("bronze_totem_lapis_lazuli", () -> new BronzeTotemItem(new Item.Properties().stacksTo(1), () -> MobEffects.NIGHT_VISION, "lapis_lazuli", ChatFormatting.BLUE));
    public static final RegistryObject<Item> BRONZE_TOTEM_EMERALD = HELPER.createItem("bronze_totem_emerald", () -> new BronzeTotemItem(new Item.Properties().stacksTo(1), () -> MobEffects.DIG_SPEED, "emerald", ChatFormatting.GREEN));

    public static final RegistryObject<Item> BRONZE_RADAR = HELPER.createItem("radar", () -> new BronzeLocatorItem(new Item.Properties()));

    public static final RegistryObject<Item> BRONZE_HELMET = HELPER.createItem("bronze_helmet", () -> new ArmorItem(OrevolutionArmorTiers.BRONZE, ArmorItem.Type.HELMET, new Item.Properties()));
    public static final RegistryObject<Item> BRONZE_CHESTPLATE = HELPER.createItem("bronze_chestplate", () -> new BronzeChestplate(OrevolutionArmorTiers.BRONZE, ArmorItem.Type.CHESTPLATE, new Item.Properties()));
    public static final RegistryObject<Item> BRONZE_LEGGINGS = HELPER.createItem("bronze_leggings", () -> new ArmorItem(OrevolutionArmorTiers.BRONZE, ArmorItem.Type.LEGGINGS, new Item.Properties()));
    public static final RegistryObject<Item> BRONZE_BOOTS = HELPER.createItem("bronze_boots", () -> new ArmorItem(OrevolutionArmorTiers.BRONZE, ArmorItem.Type.BOOTS, new Item.Properties()));
        /*Steel set*/
    public static final RegistryObject<Item> STEEL_SCYTHE = HELPER.createItem("steel_scythe", () -> new ScytheItem(OrevolutionToolTiers.STEEL, 3, -2.4f, new Item.Properties()));
    public static final RegistryObject<Item> STEEL_HAMMER = HELPER.createItem("steel_hammer", () -> new PickaxeItem(OrevolutionToolTiers.STEEL, 5, -2.2f, new Item.Properties()));
    public static final RegistryObject<Item> STEEL_DIGGER = HELPER.createItem("steel_digger", () -> new DiggerShovelItem(OrevolutionToolTiers.STEEL, 2, -2.4f, new Item.Properties()));
    public static final RegistryObject<Item> STEEL_BROADAXE = HELPER.createItem("steel_broadaxe", () -> new AxeItem(OrevolutionToolTiers.STEEL, 3, -2.9f, new Item.Properties()));
        /*Tungsten set*/
    public static final RegistryObject<Item> TUNGSTEN_HELMET = HELPER.createItem("tungsten_helmet", () -> new ArmorItem(OrevolutionArmorTiers.TUNGSTEN, ArmorItem.Type.HELMET, new Item.Properties().fireResistant()));
    public static final RegistryObject<Item> TUNGSTEN_CHESTPLATE = HELPER.createItem("tungsten_chestplate", () -> new TungstenChestplate(OrevolutionArmorTiers.TUNGSTEN, ArmorItem.Type.CHESTPLATE, new Item.Properties().fireResistant()));
    public static final RegistryObject<Item> TUNGSTEN_LEGGINGS = HELPER.createItem("tungsten_leggings", () -> new ArmorItem(OrevolutionArmorTiers.TUNGSTEN, ArmorItem.Type.LEGGINGS, new Item.Properties().fireResistant()));
    public static final RegistryObject<Item> TUNGSTEN_BOOTS = HELPER.createItem("tungsten_boots", () -> new ArmorItem(OrevolutionArmorTiers.TUNGSTEN, ArmorItem.Type.BOOTS, new Item.Properties().fireResistant()));

    //~//~~Consumables~~//~//
    public static final RegistryObject<Item> VERDITE_APPLE = HELPER.createItem("verdite_apple", () -> new VerditeApple(new Item.Properties()));
    public static final RegistryObject<Item> VERDITE_SPIDER_EYE = HELPER.createItem("verdite_spider_eye", () -> new Item(new Item.Properties().food((new FoodProperties.Builder()).nutrition(2).saturationMod(0.8F).effect(new MobEffectInstance(MobEffects.REGENERATION, 80, 0), 1.0F).build())));
    public static final RegistryObject<Item> PLATINUM_BERRIES = HELPER.createItem("platinum_berries", () -> new Item(new Item.Properties().food((new FoodProperties.Builder()).nutrition(4).saturationMod(0.2F).effect(new MobEffectInstance(MobEffects.GLOWING, 380, 0), 1.0F).effect(new MobEffectInstance(MobEffects.NIGHT_VISION, 380, 0), 1.0F).build())));
    public static final RegistryObject<Item> CAVE_CARROT = HELPER.createItem("cave_carrot", () -> new Item(new Item.Properties().food((new FoodProperties.Builder()).nutrition(3).saturationMod(0.1F).fast().build())));
    public static final RegistryObject<Item> SHINY_CARROT = HELPER.createItem("shiny_carrot", () -> new Item(new Item.Properties().food((new FoodProperties.Builder()).nutrition(5).saturationMod(0.6F).fast().build())));

    //~//~~Others~~//~//
    public static final RegistryObject<Item> PETRIFIED_SEED = HELPER.createItem("petrified_seed", () -> new ItemNameBlockItem(RegBlocks.LIVINGSTONE_CROP.get(), new Item.Properties()));
    public static final RegistryObject<Item> DEAD_SEED = HELPER.createItem("dead_seed", () -> new ItemNameBlockItem(RegBlocks.VERDITE_CROP.get(), new Item.Properties()));
//    public static final RegistryObject<Item> AMBER_SEED = HELPER.createItem("amber_seed", () -> new ItemNameBlockItem(RegBlocks.AMBER_CROP.get(), new Item.Properties()));

}
