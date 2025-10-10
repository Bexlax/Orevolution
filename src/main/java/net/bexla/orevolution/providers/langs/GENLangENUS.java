package net.bexla.orevolution.providers.langs;

import galena.oreganized.data.provider.OLangProvider;
import net.bexla.orevolution.Orevolution;
import net.bexla.orevolution.init.RegBlocks;
import net.bexla.orevolution.init.RegItems;
import net.minecraft.data.PackOutput;

public class GENLangENUS extends OLangProvider {
    public GENLangENUS(PackOutput output) {
        super(output, Orevolution.MODID, "en_us");
    }

    public void addTooltip(String tooltipID, String name) {
        add("tooltip.orevolution." + tooltipID, name);
    }

    public void addTier(String tooltipID, String name) {
        add("tiers.orevolution." + tooltipID, name);
    }

    @Override
    protected void addTranslations() {
        addTooltip("duplication", "Has a chance to increase most block drops up to {}x times");
        addTooltip("duplication_crops", "Has a chance to increase crop drops up to {}x times");

        addTooltip("iron_tool_tooltip", "Increased breaking speed on stone-tiered blocks");
        addTooltip("gold_tool_tooltip", "Increased durability when enchanted");

        addTooltip("on_hit_effect", "Inflicts an effect on attacked mobs:");
        addTooltip("on_hit_effect_chance", "Has a chance to inflict an effect on attacked mobs:");
        addTooltip("on_hit_multiple_effect", "Has a chance to inflict multiple effects when attacking:");
        addTooltip("attacker_on_hit_effect", "Has a chance to inflict an effect to the player when attacking:");
        addTooltip("undead_on_hit", "Has a chance to inflict multiple effects on attacked undead mobs:");

        addTooltip("avoid_damage", "Has a chance to avoid durability damage on use");

        addTooltip("electrum", "Causes kinetic damage when attacking and moving");

        addTooltip("armor_wearer_grants", "Grants these effects:");
        addTooltip("armor_wearer_on_attacked", "Grants these effects when you receive damage:");
        addTooltip("netherite_armor", "When your health is below 50% and you attack an enemy, inflicts:");

        addTooltip("grant_on_mine", "Each {} amount (increases for every stacked level) of blocks mined, grants and increases:");

        add("actionbar.orevolution.cant_harvest_ore", "You can't harvest this block yet!");

        addTooltip("harvest_tier", "Nivel de mineria:");
        addTier("wood", "Wood");
        addTier("stone", "Stone");
        addTier("tin", "Tin");
        addTier("iron", "Iron");
        addTier("platinum", "Platinum");
        addTier("diamond", "Diamond");
        addTier("netherite", "Netherite");
        addTier("aethersteel", "Aethersteel");

        addItem(RegItems.PLATINUM_SWORD, "Platinum Sword");
        addItem(RegItems.PLATINUM_SHOVEL, "Platinum Shovel");
        addItem(RegItems.PLATINUM_PICKAXE, "Platinum Pickaxe");
        addItem(RegItems.PLATINUM_AXE, "Platinum Axe");
        addItem(RegItems.PLATINUM_HOE, "Platinum Hoe");
        addItem(RegItems.PLATINUM_HELMET, "Platinum Helmet");
        addItem(RegItems.PLATINUM_CHESTPLATE, "Platinum Chestplate");
        addItem(RegItems.PLATINUM_LEGGINGS, "Platinum Leggings");
        addItem(RegItems.PLATINUM_BOOTS, "Platinum Boots");

        addItem(RegItems.TIN_SWORD, "Tin Sword");
        addItem(RegItems.TIN_SHOVEL, "Tin Shovel");
        addItem(RegItems.TIN_PICKAXE, "Tin Pickaxe");
        addItem(RegItems.TIN_AXE, "Tin Axe");
        addItem(RegItems.TIN_HOE, "Tin Hoe");

        addItem(RegItems.AETHERSTEEL_SWORD, "Aethersteel Sword");
        addItem(RegItems.AETHERSTEEL_SHOVEL, "Aethersteel Shovel");
        addItem(RegItems.AETHERSTEEL_PICKAXE, "Aethersteel Pickaxe");
        addItem(RegItems.AETHERSTEEL_AXE, "Aethersteel Axe");
        addItem(RegItems.AETHERSTEEL_HOE, "Aethersteel Hoe");
        addItem(RegItems.AETHERSTEEL_HELMET, "Aethersteel Helmet");
        addItem(RegItems.AETHERSTEEL_CHESTPLATE, "Aethersteel Chestplate");
        addItem(RegItems.AETHERSTEEL_LEGGINGS, "Aethersteel Leggings");
        addItem(RegItems.AETHERSTEEL_BOOTS, "Aethersteel Boots");

        addItem(RegItems.REINFORCED_NETHERITE_HELMET, "Reinforced Netherite Helmet");
        addItem(RegItems.REINFORCED_NETHERITE_CHESTPLATE, "Reinforced Netherite Chestplate");
        addItem(RegItems.REINFORCED_NETHERITE_LEGGINGS, "Reinforced Netherite Leggings");
        addItem(RegItems.REINFORCED_NETHERITE_BOOTS, "Reinforced Netherite Boots");

        addItem(RegItems.LIVINGSTONE_SWORD, "Livingstone Sword");
        addItem(RegItems.LIVINGSTONE_SHOVEL, "Livingstone Shovel");
        addItem(RegItems.LIVINGSTONE_PICKAXE, "Livingstone Pickaxe");
        addItem(RegItems.LIVINGSTONE_AXE, "Livingstone Axe");
        addItem(RegItems.LIVINGSTONE_HOE, "Livingstone Hoe");
        addItem(RegItems.LIVINGSTONE_HELMET, "Livingstone Helmet");
        addItem(RegItems.LIVINGSTONE_CHESTPLATE, "Livingstone Chestplate");
        addItem(RegItems.LIVINGSTONE_LEGGINGS, "Livingstone Leggings");
        addItem(RegItems.LIVINGSTONE_BOOTS, "Livingstone Boots");

        addItem(RegItems.VERDITE_SWORD, "Verdite Sword");
        addItem(RegItems.VERDITE_SHOVEL, "Verdite Shovel");
        addItem(RegItems.VERDITE_PICKAXE, "Verdite Pickaxe");
        addItem(RegItems.VERDITE_AXE, "Verdite Axe");
        addItem(RegItems.VERDITE_HOE, "Verdite Hoe");
        addItem(RegItems.VERDITE_HELMET, "Verdite Helmet");
        addItem(RegItems.VERDITE_CHESTPLATE, "Verdite Chestplate");
        addItem(RegItems.VERDITE_LEGGINGS, "Verdite Leggings");
        addItem(RegItems.VERDITE_BOOTS, "Verdite Boots");

        addItem(RegItems.TIN_INGOT, "Tin Ingot");
        addItem(RegItems.RAW_TIN, "Raw Tin");
        addBlock(RegBlocks.TIN_BLOCK, "Block of Tin");

        addItem(RegItems.PLATINUM_INGOT, "Platinum Ingot");
        addItem(RegItems.RAW_PLATINUM, "Raw Platinum");
        addBlock(RegBlocks.PLATINUM_BLOCK, "Block of Platinum");

        addItem(RegItems.TUNGSTEN_INGOT, "Tungsten Ingot");
        addItem(RegItems.RAW_TUNGSTEN, "Raw Tungsten");
        addBlock(RegBlocks.TUNGSTEN_BLOCK, "Block of Tungsten");

        addItem(RegItems.BRONZE_ALLOY, "Bronze Alloy");
        addBlock(RegBlocks.BRONZE_BLOCK, "Block of Bronze");

        addItem(RegItems.TUMBAGA_ALLOY, "Tumbaga Alloy");
        addBlock(RegBlocks.TUMBAGA_BLOCK, "Block of Tumbaga");

        addItem(RegItems.STEEL_ALLOY, "Steel Alloy");
        addBlock(RegBlocks.STEEL_BLOCK, "Block of Steel");

        addItem(RegItems.AETHERSTEEL_INGOT, "Aethersteel Ingot");
        addItem(RegItems.AETHERSTEEL_CHUNK, "Aethersteel Chunk");
        addBlock(RegBlocks.AETHERSTEEL_BLOCK, "Block of Aethersteel");

        addBlock(RegBlocks.REINFORCED_NETHERITE_BLOCK, "Block of Reinforced Netherite");

//       addItem(RegItems.REINFORCED_TEMPLATE, "Reinforcement Template");
//       addItem(RegItems.AETHERSTEEL_TEMPLATE, "Aethersteel Template");
//       addItem(RegItems.BASIC_TEMPLATE, "Basic Template");

        addItem(RegItems.TIN_NUGGET, "Tin Nugget");
        addItem(RegItems.PLATINUM_NUGGET, "Platinum Nugget");
        addItem(RegItems.TUNGSTEN_NUGGET, "Tungsten Nugget");
        addItem(RegItems.VERDITE_NUGGET, "Verdite Nugget");
        addItem(RegItems.LIVINGSTONE_SHARD, "Livingstone Shard");

        addItem(RegItems.VERDITE_APPLE, "Verdite Apple");
    }
}
