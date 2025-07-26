package net.bexla.orevolution.content.init;

import com.teamabnormals.blueprint.core.util.registry.ItemSubRegistryHelper;
import net.bexla.orevolution.Orevolution;
import net.bexla.orevolution.content.data.OrevolutionArmorMats;
import net.bexla.orevolution.content.data.OrevolutionToolMats;
import net.bexla.orevolution.content.types.item.*;
import net.minecraft.world.item.ArmorItem;
import net.minecraftforge.fml.common.Mod;
import net.minecraft.world.item.Item;
import java.util.function.Function;
import java.util.function.Supplier;

import net.minecraftforge.fml.ModList;
import net.minecraftforge.registries.RegistryObject;


@Mod.EventBusSubscriber(modid = Orevolution.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class RegItems {
    public static final ItemSubRegistryHelper HELPER = Orevolution.REGISTRY_HELPER.getItemSubHelper();

    public static Supplier<? extends Item> compat(String modid, Function<Item.Properties, ? extends Item> supplier, Item.Properties properties) {
        if (ModList.get().isLoaded(modid)) return () -> supplier.apply(properties);
        return () -> new Item(properties);
    }

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
    public static final RegistryObject<Item> TUMBAGA_ALLOY = normalItem("tumbaga_ingot");
        /*Nuggets*/
    public static final RegistryObject<Item> TIN_NUGGET = normalItem("tin_nugget");
    public static final RegistryObject<Item> PLATINUM_NUGGET = normalItem("platinum_nugget");
    public static final RegistryObject<Item> VERDITE_NUGGET = normalItem("verdite_nugget");
        /*Plant ores*/
    public static final RegistryObject<Item> LIVINGSTONE_SHARD = normalItem("livingstone_shard");


    //~//~~Armors, Tools and Weapons~~//~//
        /*Tin set*/
    public static final RegistryObject<Item> TIN_SWORD = HELPER.createItem("tin_sword", () -> new OrevolutionSword(OrevolutionToolMats.TIN, 3, -2.4f, new Item.Properties()));
    public static final RegistryObject<Item> TIN_PICKAXE = HELPER.createItem("tin_pickaxe", () -> new OrevolutionPickaxe(OrevolutionToolMats.TIN, 1, -2.6f, new Item.Properties()));
    public static final RegistryObject<Item> TIN_AXE = HELPER.createItem("tin_axe", () -> new OrevolutionAxe(OrevolutionToolMats.TIN, 5, -3.2f, new Item.Properties()));
    public static final RegistryObject<Item> TIN_SHOVEL = HELPER.createItem("tin_shovel", () -> new OrevolutionShovel(OrevolutionToolMats.TIN, 1.5f, -3f, new Item.Properties()));
    public static final RegistryObject<Item> TIN_HOE = HELPER.createItem("tin_hoe", () -> new OrevolutionHoe(OrevolutionToolMats.TIN, -2f, new Item.Properties()));
        /*Platinum set*/
    public static final RegistryObject<Item> PLATINUM_HELMET = HELPER.createItem("platinum_helmet", () -> new OrevolutionArmor(OrevolutionArmorMats.PLATINUM, ArmorItem.Type.HELMET, new Item.Properties()));
    public static final RegistryObject<Item> PLATINUM_CHESTPLATE = HELPER.createItem("platinum_chestplate", () -> new OrevolutionArmor(OrevolutionArmorMats.PLATINUM, ArmorItem.Type.CHESTPLATE, new Item.Properties()));
    public static final RegistryObject<Item> PLATINUM_LEGGINGS = HELPER.createItem("platinum_leggings", () -> new OrevolutionArmor(OrevolutionArmorMats.PLATINUM, ArmorItem.Type.LEGGINGS, new Item.Properties()));
    public static final RegistryObject<Item> PLATINUM_BOOTS = HELPER.createItem("platinum_boots", () -> new OrevolutionArmor(OrevolutionArmorMats.PLATINUM, ArmorItem.Type.BOOTS, new Item.Properties()));

    public static final RegistryObject<Item> PLATINUM_SWORD = HELPER.createItem("platinum_sword", () -> new OrevolutionSword(OrevolutionToolMats.PLATINUM, 3, -2.4f, new Item.Properties()));
    public static final RegistryObject<Item> PLATINUM_PICKAXE = HELPER.createItem("platinum_pickaxe", () -> new OrevolutionPickaxe(OrevolutionToolMats.PLATINUM, 1, -2.6f, new Item.Properties()));
    public static final RegistryObject<Item> PLATINUM_AXE = HELPER.createItem("platinum_axe", () -> new OrevolutionAxe(OrevolutionToolMats.PLATINUM, 5, -3.2f, new Item.Properties()));
    public static final RegistryObject<Item> PLATINUM_SHOVEL = HELPER.createItem("platinum_shovel", () -> new OrevolutionShovel(OrevolutionToolMats.PLATINUM, 1.5f, -3f, new Item.Properties()));
    public static final RegistryObject<Item> PLATINUM_HOE = HELPER.createItem("platinum_hoe", () -> new OrevolutionHoe(OrevolutionToolMats.PLATINUM, -2f, new Item.Properties()));
        /*Aethersteel set*/
    public static final RegistryObject<Item> AETHERSTEEL_HELMET = HELPER.createItem("aethersteel_helmet", () -> new OrevolutionArmor(OrevolutionArmorMats.AETHERSTEEL, ArmorItem.Type.HELMET, new Item.Properties()));
    public static final RegistryObject<Item> AETHERSTEEL_CHESTPLATE = HELPER.createItem("aethersteel_chestplate", () -> new OrevolutionArmor(OrevolutionArmorMats.AETHERSTEEL, ArmorItem.Type.CHESTPLATE, new Item.Properties()));
    public static final RegistryObject<Item> AETHERSTEEL_LEGGINGS = HELPER.createItem("aethersteel_leggings", () -> new OrevolutionArmor(OrevolutionArmorMats.AETHERSTEEL, ArmorItem.Type.LEGGINGS, new Item.Properties()));
    public static final RegistryObject<Item> AETHERSTEEL_BOOTS = HELPER.createItem("aethersteel_boots", () -> new OrevolutionArmor(OrevolutionArmorMats.AETHERSTEEL, ArmorItem.Type.BOOTS, new Item.Properties()));

    public static final RegistryObject<Item> AETHERSTEEL_SWORD = HELPER.createItem("aethersteel_sword", () -> new OrevolutionSword(OrevolutionToolMats.AETHERSTEEL, 3, -2.4f, new Item.Properties()));
    public static final RegistryObject<Item> AETHERSTEEL_PICKAXE = HELPER.createItem("aethersteel_pickaxe", () -> new OrevolutionPickaxe(OrevolutionToolMats.AETHERSTEEL, 1, -2.6f, new Item.Properties()));
    public static final RegistryObject<Item> AETHERSTEEL_AXE = HELPER.createItem("aethersteel_axe", () -> new OrevolutionAxe(OrevolutionToolMats.AETHERSTEEL, 5, -2.2f, new Item.Properties()));
    public static final RegistryObject<Item> AETHERSTEEL_SHOVEL = HELPER.createItem("aethersteel_shovel", () -> new OrevolutionShovel(OrevolutionToolMats.AETHERSTEEL, 1.5f, -3f, new Item.Properties()));
    public static final RegistryObject<Item> AETHERSTEEL_HOE = HELPER.createItem("aethersteel_hoe", () -> new OrevolutionHoe(OrevolutionToolMats.AETHERSTEEL, -2f, new Item.Properties()));
        /*Livingstone set*/


    //~//~~Consumables~~//~//
    public static final RegistryObject<Item> VERDITE_APPLE = HELPER.createItem("verdite_apple", () -> new VerditeApple(new Item.Properties()));
}
