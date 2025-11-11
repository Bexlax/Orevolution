package net.bexla.orevolution.init;

import com.teamabnormals.blueprint.core.util.registry.ItemSubRegistryHelper;
import net.bexla.orevolution.Orevolution;
import net.bexla.orevolution.content.data.OrevolutionArmorTiers;
import net.bexla.orevolution.content.data.OrevolutionToolTiers;
import net.bexla.orevolution.content.types.item.OrevolutionSmithingTemplate;
import net.bexla.orevolution.content.types.item.ScytheItem;
import net.bexla.orevolution.content.types.item.VerditeApple;
import net.bexla.orevolution.content.types.item.modeled.AethersteelChestplate;
import net.bexla.orevolution.content.types.item.modeled.LivingstoneChestplate;
import net.bexla.orevolution.content.types.item.modeled.ReinforcedArmor;
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
        /*Smithing Templates*/
    public static final RegistryObject<Item> AETHERSTEEL_TEMPLATE = HELPER.createItem("aethersteel_smithing_template", OrevolutionSmithingTemplate::createAethersteelUpgradeTemplate);
    public static final RegistryObject<Item> REINFORCED_TEMPLATE = HELPER.createItem("reinforced_smithing_template", OrevolutionSmithingTemplate::createReinforcedUpgradeTemplate);
    public static final RegistryObject<Item> BASIC_TEMPLATE = HELPER.createItem("basic_smithing_template", OrevolutionSmithingTemplate::createBasicUpgradeTemplate);


    //~//~~Armors, Tools and Weapons~~//~//
    public static final RegistryObject<Item> TIN_SWORD = HELPER.createItem("tin_sword", () -> new SwordItem(OrevolutionToolTiers.TIN, 3, -2.4f, new Item.Properties()));
    public static final RegistryObject<Item> TIN_PICKAXE = HELPER.createItem("tin_pickaxe", () -> new PickaxeItem(OrevolutionToolTiers.TIN, 1, -2.6f, new Item.Properties()));
    public static final RegistryObject<Item> TIN_AXE = HELPER.createItem("tin_axe", () -> new AxeItem(OrevolutionToolTiers.TIN, 5F, -3.2f, new Item.Properties()));
    public static final RegistryObject<Item> TIN_SHOVEL = HELPER.createItem("tin_shovel", () -> new ShovelItem(OrevolutionToolTiers.TIN, 1.5f, -3f, new Item.Properties()));
    public static final RegistryObject<Item> TIN_HOE = HELPER.createItem("tin_hoe", () -> new HoeItem(OrevolutionToolTiers.TIN, 1, -2f, new Item.Properties()));
        /*Platinum set*/
    public static final RegistryObject<Item> PLATINUM_HELMET = HELPER.createItem("platinum_helmet", () -> new ArmorItem(OrevolutionArmorTiers.PLATINUM, ArmorItem.Type.HELMET, new Item.Properties()));
    public static final RegistryObject<Item> PLATINUM_CHESTPLATE = HELPER.createItem("platinum_chestplate", () -> new ArmorItem(OrevolutionArmorTiers.PLATINUM, ArmorItem.Type.CHESTPLATE, new Item.Properties()));
    public static final RegistryObject<Item> PLATINUM_LEGGINGS = HELPER.createItem("platinum_leggings", () -> new ArmorItem(OrevolutionArmorTiers.PLATINUM, ArmorItem.Type.LEGGINGS, new Item.Properties()));
    public static final RegistryObject<Item> PLATINUM_BOOTS = HELPER.createItem("platinum_boots", () -> new ArmorItem(OrevolutionArmorTiers.PLATINUM, ArmorItem.Type.BOOTS, new Item.Properties()));

    public static final RegistryObject<Item> PLATINUM_SWORD = HELPER.createItem("platinum_sword", () -> new SwordItem(OrevolutionToolTiers.PLATINUM, 3, -2.4f, new Item.Properties()));
    public static final RegistryObject<Item> PLATINUM_PICKAXE = HELPER.createItem("platinum_pickaxe", () -> new PickaxeItem(OrevolutionToolTiers.PLATINUM, 1, -2.6f, new Item.Properties()));
    public static final RegistryObject<Item> PLATINUM_AXE = HELPER.createItem("platinum_axe", () -> new AxeItem(OrevolutionToolTiers.PLATINUM, 5F, -3.2f, new Item.Properties()));
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

    public static final RegistryObject<Item> AETHERSTEEL_SWORD = HELPER.createItem("aethersteel_sword", () -> new SwordItem(OrevolutionToolTiers.AETHERSTEEL, 3, -2.4f, new Item.Properties().fireResistant().rarity(Aether)));
    public static final RegistryObject<Item> AETHERSTEEL_PICKAXE = HELPER.createItem("aethersteel_pickaxe", () -> new PickaxeItem(OrevolutionToolTiers.AETHERSTEEL, 1, -2.6f, new Item.Properties().fireResistant().rarity(Aether)));
    public static final RegistryObject<Item> AETHERSTEEL_AXE = HELPER.createItem("aethersteel_axe", () -> new AxeItem(OrevolutionToolTiers.AETHERSTEEL, 5F, -2.2f, new Item.Properties().fireResistant().rarity(Aether)));
    public static final RegistryObject<Item> AETHERSTEEL_SHOVEL = HELPER.createItem("aethersteel_shovel", () -> new ShovelItem(OrevolutionToolTiers.AETHERSTEEL, 1.5f, -3f, new Item.Properties().fireResistant().rarity(Aether)));
    public static final RegistryObject<Item> AETHERSTEEL_HOE = HELPER.createItem("aethersteel_hoe", () -> new HoeItem(OrevolutionToolTiers.AETHERSTEEL, 1, -2f, new Item.Properties().fireResistant().rarity(Aether)));
        /*Livingstone set*/
    public static final RegistryObject<Item> LIVINGSTONE_HELMET = HELPER.createItem("livingstone_helmet", () -> new ArmorItem(OrevolutionArmorTiers.LIVINGSTONE, ArmorItem.Type.HELMET, new Item.Properties()));
    public static final RegistryObject<Item> LIVINGSTONE_CHESTPLATE = HELPER.createItem("livingstone_chestplate", () -> new LivingstoneChestplate(OrevolutionArmorTiers.LIVINGSTONE, ArmorItem.Type.CHESTPLATE, new Item.Properties()));
    public static final RegistryObject<Item> LIVINGSTONE_LEGGINGS = HELPER.createItem("livingstone_leggings", () -> new ArmorItem(OrevolutionArmorTiers.LIVINGSTONE, ArmorItem.Type.LEGGINGS, new Item.Properties()));
    public static final RegistryObject<Item> LIVINGSTONE_BOOTS = HELPER.createItem("livingstone_boots", () -> new ArmorItem(OrevolutionArmorTiers.LIVINGSTONE, ArmorItem.Type.BOOTS, new Item.Properties()));

    public static final RegistryObject<Item> LIVINGSTONE_SWORD = HELPER.createItem("livingstone_sword", () -> new SwordItem(OrevolutionToolTiers.LIVINGSTONE, 3, -2.4f, new Item.Properties()));
    public static final RegistryObject<Item> LIVINGSTONE_PICKAXE = HELPER.createItem("livingstone_pickaxe", () -> new PickaxeItem(OrevolutionToolTiers.LIVINGSTONE, 1, -2.6f, new Item.Properties()));
    public static final RegistryObject<Item> LIVINGSTONE_AXE = HELPER.createItem("livingstone_axe", () -> new AxeItem(OrevolutionToolTiers.LIVINGSTONE, 5F, -2.2f, new Item.Properties()));
    public static final RegistryObject<Item> LIVINGSTONE_SHOVEL = HELPER.createItem("livingstone_shovel", () -> new ShovelItem(OrevolutionToolTiers.LIVINGSTONE, 1.5f, -3f, new Item.Properties()));
    public static final RegistryObject<Item> LIVINGSTONE_HOE = HELPER.createItem("livingstone_hoe", () -> new HoeItem(OrevolutionToolTiers.LIVINGSTONE, 1, -2f, new Item.Properties()));
        /*Verdite set*/
    public static final RegistryObject<Item> VERDITE_HELMET = HELPER.createItem("verdite_helmet", () -> new ArmorItem(OrevolutionArmorTiers.VERDITE, ArmorItem.Type.HELMET, new Item.Properties()));
    public static final RegistryObject<Item> VERDITE_CHESTPLATE = HELPER.createItem("verdite_chestplate", () -> new ArmorItem(OrevolutionArmorTiers.VERDITE, ArmorItem.Type.CHESTPLATE, new Item.Properties()));
    public static final RegistryObject<Item> VERDITE_LEGGINGS = HELPER.createItem("verdite_leggings", () -> new ArmorItem(OrevolutionArmorTiers.VERDITE, ArmorItem.Type.LEGGINGS, new Item.Properties()));
    public static final RegistryObject<Item> VERDITE_BOOTS = HELPER.createItem("verdite_boots", () -> new ArmorItem(OrevolutionArmorTiers.VERDITE, ArmorItem.Type.BOOTS, new Item.Properties()));

    public static final RegistryObject<Item> VERDITE_SWORD = HELPER.createItem("verdite_sword", () -> new SwordItem(OrevolutionToolTiers.VERDITE, 3, -2.4f, new Item.Properties()));
    public static final RegistryObject<Item> VERDITE_PICKAXE = HELPER.createItem("verdite_pickaxe", () -> new PickaxeItem(OrevolutionToolTiers.VERDITE, 1, -2.6f, new Item.Properties()));
    public static final RegistryObject<Item> VERDITE_AXE = HELPER.createItem("verdite_axe", () -> new AxeItem(OrevolutionToolTiers.VERDITE, 5F, -2.2f, new Item.Properties()));
    public static final RegistryObject<Item> VERDITE_SHOVEL = HELPER.createItem("verdite_shovel", () -> new ShovelItem(OrevolutionToolTiers.VERDITE, 1.5f, -3f, new Item.Properties()));
    public static final RegistryObject<Item> VERDITE_HOE = HELPER.createItem("verdite_hoe", () -> new HoeItem(OrevolutionToolTiers.VERDITE, 1, -2f, new Item.Properties()));
        /*Bronze crowns set*/
    public static final RegistryObject<Item> BRONZE_CROWN = HELPER.createItem("bronze_crown", () -> new ArmorItem(OrevolutionArmorTiers.BRONZE_CROWN, ArmorItem.Type.HELMET, new Item.Properties()));
    public static final RegistryObject<Item> BRONZE_CROWN_DIAMOND = HELPER.createItem("bronze_crown_diamond", () -> new ArmorItem(OrevolutionArmorTiers.BRONZE_CROWN_DIAMOND, ArmorItem.Type.HELMET, new Item.Properties()));
    public static final RegistryObject<Item> BRONZE_CROWN_EMERALD = HELPER.createItem("bronze_crown_emerald", () -> new ArmorItem(OrevolutionArmorTiers.BRONZE_CROWN_EMERALD, ArmorItem.Type.HELMET, new Item.Properties()));
    public static final RegistryObject<Item> BRONZE_CROWN_LAPIS = HELPER.createItem("bronze_crown_lapis", () -> new ArmorItem(OrevolutionArmorTiers.BRONZE_CROWN_LAPIS, ArmorItem.Type.HELMET, new Item.Properties()));
    public static final RegistryObject<Item> BRONZE_CROWN_REDSTONE = HELPER.createItem("bronze_crown_redstone", () -> new ArmorItem(OrevolutionArmorTiers.BRONZE_CROWN_REDSTONE, ArmorItem.Type.HELMET, new Item.Properties()));
        /*Steel set*/
    public static final RegistryObject<Item> STEEL_SCYTHE = HELPER.createItem("steel_scythe", () -> new ScytheItem(OrevolutionToolTiers.STEEL, 3, -2.4f, new Item.Properties()));
    public static final RegistryObject<Item> STEEL_HAMMER = HELPER.createItem("steel_hammer", () -> new PickaxeItem(OrevolutionToolTiers.STEEL, 5, -2.2f, new Item.Properties()));
    public static final RegistryObject<Item> STEEL_DIGGER = HELPER.createItem("steel_digger", () -> new ShovelItem(OrevolutionToolTiers.STEEL, 3, -2.4f, new Item.Properties()));

    //~//~~Consumables~~//~//
    public static final RegistryObject<Item> VERDITE_APPLE = HELPER.createItem("verdite_apple", () -> new VerditeApple(new Item.Properties()));
}
