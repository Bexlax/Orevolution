package net.bexla.orevolution.datagens.langs;

import net.bexla.orevolution.Orevolution;
import net.bexla.orevolution.compatibility.farmersdelight.RegItemsFD;
import net.bexla.orevolution.compatibility.spelunkery.RegBlocksSK;
import net.bexla.orevolution.content.types.providers.LangProvider;
import net.bexla.orevolution.init.RegBlocks;
import net.bexla.orevolution.init.RegItems;
import net.bexla.orevolution.init.RegMobEffects;
import net.minecraft.data.PackOutput;

public class GENLangENUS extends LangProvider {
    public GENLangENUS(PackOutput output) {
        super(output, Orevolution.MODID, "en_us");
    }

    public void addCondition(String tooltipID, String name) {
        add("conditional.orevolution." + tooltipID, name);
    }

    public void addTooltip(String tooltipID, String name) {
        add("tooltip.orevolution." + tooltipID, name);
    }

    public void addTier(String tooltipID, String name) {
        add("tiers.orevolution." + tooltipID, name);
    }

    public void addSmithingTemplateTips(String nameID, String name, String appliesTo, String ingredients, String baseSlotDescription, String additionsSlotDescription) {
        add("upgrade.orevolution." + nameID + "_upgrade", name);
        add("item.orevolution.smithing_template." + nameID + "_upgrade.applies_to", appliesTo);
        add("item.orevolution.smithing_template." + nameID + "_upgrade.ingredients", ingredients);
        add("item.orevolution.smithing_template." + nameID + "_upgrade.base_slot_description", baseSlotDescription);
        add("item.orevolution.smithing_template." + nameID + "_upgrade.additions_slot_description", additionsSlotDescription);
    }

    @Override
    protected void addTranslations() {
        addTooltip("duplication", "Has a chance (Max of %s) to duplicate most block drops");
        addTooltip("triplication", "Has a chance (Max of %s) to triplicate most block drops");

        addTooltip("duplication_explanation",
                "List of chances depending on blocks:\n" +
                        " - 'Always' blocks (eg. Any Leave Block) -> 100% chance\n" +
                        " - Uncommon blocks (eg. Anvil) -> half of Max\n" +
                        " - Rare blocks (eg. Any Ore Block) -> fifth of Max\n" +
                        " - Any Ore (eg. Copper Ore) -> tenth of Max\n" +
                        " - 'Never' blocks (eg. Aethersteel Block) -> 0% chance"
        );

        addTooltip("triplication_explanation",
                "List of chances depending on blocks:\n" +
                        " - 'Always' blocks (eg. Any Leave Block) -> 100% chance\n" +
                        " - Uncommon blocks (eg. Anvil) -> half of Max\n" +
                        " - Rare blocks (eg. Any Ore Block) -> tenth of Max\n" +
                        " - Any Ore (eg. Copper Ore) -> fifth of Max\n" +
                        " - 'Never' blocks (eg. Aethersteel Block) -> 0% chance"
        );

        addTooltip("duplication_crops", "Has a chance (Max of %s) to duplicate crop drops");
        addTooltip("triplication_crops", "Has a chance (Max of %s) to triplicate crop drops");

        addTooltip("iron_tool_tooltip", "Breaking speed increased by 2.5X on Deepslate, Planks and Dirt");
        addTooltip("gold_tool_tooltip", "Durability increased by 160 when enchanted");

        addTooltip("increase_loot", "Has a chance of %s to increase mob loot");
        addTooltip("triplicate_loot", "Has a chance of %s to triplicate mob loot");

        addTooltip("on_hit_effect", "Inflicts the following effect(s) to attacked mobs:");
        addTooltip("attacker_on_hit_effect", "Has a chance to inflict the following effect(s) to you when attacking mobs:");

        addTooltip("on_hit_effect_chance", "Has a chance to inflict the following effect(s) to attacked mobs:");
        addTooltip("attacker_on_hit_effect_chance", "Has a chance to inflict the following effect(s) to you when attacking:");

        addTooltip("undead_on_hit", "Has a chance to inflict the following effect(s) on attacked undead mobs:");

        addTooltip("avoid_damage", "Has a chance to avoid durability damage on use");

        addTooltip("electrum", "Inflicts kinetic damage when attacking while moving. The more momentum you have, the more damage you'll deal");
        addTooltip("necromium", "Mining speed increases depending on durability");
        addTooltip("necromium_explanation", "Durability is treated as a percentage for mining speed \nThe lower it is, the higher mining speed you'll have");

        addTooltip("full_set_bonus", "Full set bonus:");

        addTooltip("armor_wearer_grants", "Grants the following effect(s):");
        addTooltip("armor_wearer_on_attacked_wearer", "Grants the following effect(s) to you when you receive damage:");
        addTooltip("armor_wearer_on_attacked_target", "Causes the following effect(s) to the attacker when you receive damage:");

        addTooltip("armor_wearer_on_hit_wearer", "Grants the following effect(s) to you when attacking:");
        addTooltip("armor_wearer_on_hit_target", "Causes the following effect(s) to the target when attacking:");

        addTooltip("armor_immunity", "Grants immunity to the following effect(s):");
        addTooltip("armor_immunity_daylight", "Grants immunity to the following effect(s) when receiving daylight:");

        addTooltip("netherite_armor", "When your health is below 50%, inflicts the following effect(s) on hit:");
        addTooltip("reinforced_netherite_armor", "While not submerged in lava, grants the following effect(s):");

        addTooltip("electrum_armor", "After traveling a certain amount of blocks without receiving damage, grants:");
        addTooltip("electrum_armor_explanation",
                "Jumping or flying doesn't increase the traveled distance\n" +
                "List of required distance:\n" +
                        " - 50 blocks -> Speed I\n" +
                        " - 90 blocks -> Speed II\n" +
                        " - 160 blocks -> Speed III"
        );

        addTooltip("necromium_armor", "Allows you to come back from the death. \nEach time you die, a 5 minute cooldown is applied");

        addTooltip("grant_on_mine", "Each %s amount of blocks mined, grants and increases the following effect(s):");

        addTooltip("multi_break", "Breaks blocks in a 3x3 area");
        addTooltip("multi_break_explanation",
                "Each block you mine will reduce durability by 1\n" +
                        "Breaking 9 blocks results in losing 9 points of durability\n" +
                        "Loses 4 points of durability per efficiency level\n" +
                        "Breaking 9 blocks with efficiency I results in losing 36 points of durability"
        );

        add("actionbar.orevolution.cant_harvest_ore", "You can't harvest this block yet");

        add("trim_material.orevolution.platinum", "Platinum Material");
        add("trim_material.orevolution.tin", "Tin Material");
        add("trim_material.orevolution.tungsten", "Tungsten Material");

        addTooltip("press_key", "Press %s to view more information");

        addTooltip("harvest_tier", "Mining tier:");
        addTier("wood", "Stone");
        addTier("stone", "Tin");
        addTier("tin", "Iron");
        addTier("iron", "Platinum");
        addTier("platinum", "Diamond");
        addTier("diamond", "Netherite");
        addTier("netherite", "Aethersteel");
        addTier("aethersteel", "Aethersteel");

        addEffect(RegMobEffects.CRUSHED, "Crushed");
        addEffect(RegMobEffects.PETRIFIED, "Petrified");
        addEffect(RegMobEffects.WEAK_SOUL, "Weakened Soul");

        addItem(RegItems.DEAD_SEED, "Dead Seed");
        addBlock(RegBlocks.VERDITE_CROP, "Verdite Crop");
        addBlock(RegBlocks.LIVINGSTONE_BLOCK, "Livingstone Block");
        addItem(RegItems.PETRIFIED_SEED, "Petrified Seed");
        addBlock(RegBlocks.LIVINGSTONE_CROP, "Livingstone Crop");

        addItem(RegItems.PLATINUM_SHIELD, "Platinum Shield");
        addItem(RegItemsFD.PLATINUM_KNIFE, "Platinum Knife");
        addItem(RegItems.PLATINUM_SWORD, "Platinum Sword");
        addItem(RegItems.PLATINUM_SHOVEL, "Platinum Shovel");
        addItem(RegItems.PLATINUM_PICKAXE, "Platinum Pickaxe");
        addItem(RegItems.PLATINUM_AXE, "Platinum Axe");
        addItem(RegItems.PLATINUM_HOE, "Platinum Hoe");
        addItem(RegItems.PLATINUM_HELMET, "Platinum Helmet");
        addItem(RegItems.PLATINUM_CHESTPLATE, "Platinum Chestplate");
        addItem(RegItems.PLATINUM_LEGGINGS, "Platinum Leggings");
        addItem(RegItems.PLATINUM_BOOTS, "Platinum Boots");

        addItem(RegItems.TIN_SHIELD, "Tin Shield");
        addItem(RegItemsFD.TIN_KNIFE, "Tin Knife");
        addItem(RegItems.TIN_SWORD, "Tin Sword");
        addItem(RegItems.TIN_SHOVEL, "Tin Shovel");
        addItem(RegItems.TIN_PICKAXE, "Tin Pickaxe");
        addItem(RegItems.TIN_AXE, "Tin Axe");
        addItem(RegItems.TIN_HOE, "Tin Hoe");

        addItem(RegItems.AETHERSTEEL_SHIELD, "Aethersteel Shield");
        addItem(RegItemsFD.AETHERSTEEL_KNIFE, "Aethersteel Knife");
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

        addItem(RegItems.LIVINGSTONE_SHIELD, "Livingstone Shield");
        addItem(RegItemsFD.LIVINGSTONE_KNIFE, "Livingstone Knife");
        addItem(RegItems.LIVINGSTONE_SWORD, "Livingstone Sword");
        addItem(RegItems.LIVINGSTONE_SHOVEL, "Livingstone Shovel");
        addItem(RegItems.LIVINGSTONE_PICKAXE, "Livingstone Pickaxe");
        addItem(RegItems.LIVINGSTONE_AXE, "Livingstone Axe");
        addItem(RegItems.LIVINGSTONE_HOE, "Livingstone Hoe");
        addItem(RegItems.LIVINGSTONE_HELMET, "Livingstone Helmet");
        addItem(RegItems.LIVINGSTONE_CHESTPLATE, "Livingstone Chestplate");
        addItem(RegItems.LIVINGSTONE_LEGGINGS, "Livingstone Leggings");
        addItem(RegItems.LIVINGSTONE_BOOTS, "Livingstone Boots");

        addItem(RegItems.VERDITE_SHIELD, "Verdite Shield");
        addItem(RegItemsFD.VERDITE_KNIFE, "Verdite Knife");
        addItem(RegItems.VERDITE_SWORD, "Verdite Sword");
        addItem(RegItems.VERDITE_SHOVEL, "Verdite Shovel");
        addItem(RegItems.VERDITE_PICKAXE, "Verdite Pickaxe");
        addItem(RegItems.VERDITE_AXE, "Verdite Axe");
        addItem(RegItems.VERDITE_HOE, "Verdite Hoe");
        addItem(RegItems.VERDITE_HELMET, "Verdite Helmet");
        addItem(RegItems.VERDITE_CHESTPLATE, "Verdite Chestplate");
        addItem(RegItems.VERDITE_LEGGINGS, "Verdite Leggings");
        addItem(RegItems.VERDITE_BOOTS, "Verdite Boots");

        addItem(RegItems.STEEL_DIGGER, "Steel Digger");
        addItem(RegItems.STEEL_HAMMER, "Steel Hammer");
        addItem(RegItems.STEEL_SCYTHE, "Steel Scythe");

        addItem(RegItems.BRONZE_CROWN, "Bronze Crown");
        addItem(RegItems.BRONZE_CROWN_DIAMOND, "Bronze Crown (Diamond)");
        addItem(RegItems.BRONZE_CROWN_EMERALD, "Bronze Crown (Emerald)");
        addItem(RegItems.BRONZE_CROWN_LAPIS, "Bronze Crown (Lapis)");
        addItem(RegItems.BRONZE_CROWN_REDSTONE, "Bronze Crown (Redstone)");

        addItem(RegItems.TIN_INGOT, "Tin Ingot");
        addItem(RegItems.RAW_TIN, "Raw Tin");
        addBlock(RegBlocks.TIN_BLOCK, "Block of Tin");
        addBlock(RegBlocks.RAW_TIN_BLOCK, "Block of Raw Tin");

        addItem(RegItems.PLATINUM_INGOT, "Platinum Ingot");
        addItem(RegItems.RAW_PLATINUM, "Raw Platinum");
        addBlock(RegBlocks.PLATINUM_BLOCK, "Block of Platinum");
        addBlock(RegBlocks.RAW_PLATINUM_BLOCK, "Block of Raw Platinum");

        addItem(RegItems.TUNGSTEN_INGOT, "Tungsten Ingot");
        addItem(RegItems.RAW_TUNGSTEN, "Raw Tungsten");
        addBlock(RegBlocks.TUNGSTEN_BLOCK, "Block of Tungsten");
        addBlock(RegBlocks.RAW_TUNGSTEN_BLOCK, "Block of Raw Tungsten");

        addItem(RegItems.BRONZE_ALLOY, "Bronze Alloy");
        addBlock(RegBlocks.BRONZE_BLOCK, "Block of Bronze");

        addItem(RegItems.STEEL_ALLOY, "Steel Alloy");
        addBlock(RegBlocks.STEEL_BLOCK, "Block of Steel");

        addItem(RegItems.VERDITE_INGOT, "Verdite Ingot");
        addBlock(RegBlocks.VERDITE_BLOCK, "Verdite Block");

        addItem(RegItems.AETHERSTEEL_INGOT, "Aethersteel Ingot");
        addItem(RegItems.AETHERSTEEL_CHUNK, "Aethersteel Chunk");
        addBlock(RegBlocks.AETHERSTEEL_BLOCK, "Block of Aethersteel");

        addSmithingTemplateTips("basic", "Basic Upgrade", "Iron Equipment", "Steel Alloy", "Add Iron armor, weapon, or tool", "Add Steel Alloy");
        addSmithingTemplateTips("tungsten", "Reinforcement Upgrade", "Netherite Equipment", "Tungsten Ingot", "Add Netherite armor, weapon, or tool", "Add Tungsten Ingot");
        addSmithingTemplateTips("aethersteel", "Aether Upgrade", "Netherite and Reinforced Netherite Equipment", "Aethersteel Ingot", "Add Reinforced Netherite armor, netherite weapon, or tool", "Add Aethersteel Ingot");

        addItem(RegItems.TIN_NUGGET, "Tin Nugget");
        addItem(RegItems.PLATINUM_NUGGET, "Platinum Nugget");
        addItem(RegItems.TUNGSTEN_NUGGET, "Tungsten Nugget");
        addItem(RegItems.VERDITE_NUGGET, "Verdite Nugget");
        addItem(RegItems.LIVINGSTONE_SHARD, "Livingstone Shard");

        addItem(RegItems.VERDITE_APPLE, "Verdite Apple");

        addBlock(RegBlocks.TIN_ORE, "Tin Ore");
        addBlock(RegBlocks.DEEPSLATE_TIN_ORE, "Deepslate Tin Ore");
        addBlock(RegBlocks.PLATINUM_ORE, "Platinum Ore");
        addBlock(RegBlocks.DEEPSLATE_PLATINUM_ORE, "Deepslate Platinum Ore");
        addBlock(RegBlocks.NETHER_TUNGSTEN_ORE, "Nether Tungsten Ore");
        addBlock(RegBlocks.NETHER_XP_ORE, "Nether Experience Ore");
        addBlock(RegBlocks.END_XP_ORE, "End Experience Ore");
        addBlock(RegBlocks.AETHERROCK, "Aetherrock");
        addBlock(RegBlocks.AETHERROCK_TILES, "Aetherrock Tiles");
        addBlock(RegBlocks.POLISHED_AETHERROCK, "Polished Aetherrock");
        addBlock(RegBlocks.POLISHED_TUNGSTEN, "Polished Tungsten Block");
        addBlock(RegBlocks.AETHERROCK_BRICKS, "Aetherrock Bricks");
        addBlock(RegBlocks.CRACKED_AETHERROCK_BRICKS, "Cracked Aetherrock Bricks");
        addBlock(RegBlocks.PRIMITIVE_AETHERROCK, "Primitive Aetherrock");
        addBlock(RegBlocks.CUT_STEEL_BLOCK, "Cut Steel Block");
        addBlock(RegBlocks.CUT_TUNGSTEN_BLOCK, "Cut Tungsten Block");
        addBlock(RegBlocks.BRONZE_TILES, "Bronze Tiles");
        addBlock(RegBlocks.STEEL_PILLAR, "Steel Pillar");
        addBlock(RegBlocks.STEEL_DOOR, "Steel Door");
        addBlock(RegBlocks.CHISELED_TUNGSTEN_BLOCK, "Chiseled Tungsten Block");
        addBlock(RegBlocks.CHISELED_TUNGSTEN_BRICKS, "Chiseled Tungsten Bricks");
        addBlock(RegBlocks.TUNGSTEN_BRICKS, "Tungsten Bricks");
        addBlock(RegBlocks.TIN_BRICKS, "Tin Bricks");

        addBlock(RegBlocks.TIN_TILES, "Tin Tiles");
        addBlock(RegBlocks.PLATINUM_TILES, "Platinum Tiles");
        addBlock(RegBlocks.GOLD_TILES, "Gold Tiles");
        addBlock(RegBlocks.PLATINUM_PILLAR, "Platinum Pillar");
        addBlock(RegBlocks.GOLD_PILLAR, "Gold Pillar");
        addBlock(RegBlocks.BRONZE_BARS, "Bronze Bars");
        addBlock(RegBlocks.GOLD_BARS, "Gold Bars");
        addBlock(RegBlocks.TUNGSTEN_BARS, "Tungsten Bars");
        addBlock(RegBlocks.TIN_BARS, "Tin Bars");
        addBlock(RegBlocks.PLATINUM_BARS, "Platinum Bars");

        addBlock(RegBlocks.POLISHED_AETHERROCK_WALL, "Polished Aetherrock Wall");
        addBlock(RegBlocks.POLISHED_AETHERROCK_STAIR, "Polished Aetherrock Stair");
        addBlock(RegBlocks.POLISHED_AETHERROCK_SLAB, "Polished Aetherrock Slab");

        addBlock(RegBlocksSK.TIN_ORE_ANDESITE, "Andesite Tin Ore");
        addBlock(RegBlocksSK.TIN_ORE_GRANITE, "Granite Tin Ore");
        addBlock(RegBlocksSK.TIN_ORE_DIORITE, "Diorite Tin Ore");
        addBlock(RegBlocksSK.TIN_ORE_TUFF, "Tuff Tin Ore");
        addBlock(RegBlocksSK.PLATINUM_ORE_ANDESITE, "Andesite Platinum Ore");
        addBlock(RegBlocksSK.PLATINUM_ORE_GRANITE, "Granite Platinum Ore");
        addBlock(RegBlocksSK.PLATINUM_ORE_DIORITE, "Diorite Platinum Ore");
        addBlock(RegBlocksSK.PLATINUM_ORE_TUFF, "Tuff Platinum Ore");
        addBlock(RegBlocksSK.NETHER_TUNGSTEN_ORE_BLACKSTONE, "Blackstone Nether Tungsten Ore");
    }
}
