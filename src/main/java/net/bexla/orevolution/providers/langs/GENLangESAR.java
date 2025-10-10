package net.bexla.orevolution.providers.langs;

import galena.oreganized.data.provider.OLangProvider;
import net.bexla.orevolution.Orevolution;
import net.bexla.orevolution.init.RegBlocks;
import net.bexla.orevolution.init.RegItems;
import net.minecraft.data.PackOutput;

public class GENLangESAR extends OLangProvider {
    public GENLangESAR(PackOutput output) {
        super(output, Orevolution.MODID, "es_ar");
    }

    public void addTooltip(String tooltipID, String name) {
        add("tooltip.orevolution." + tooltipID, name);
    }

    public void addTier(String tooltipID, String name) {
        add("tiers.orevolution." + tooltipID, name);
    }

    @Override
    protected void addTranslations() {
        addTooltip("duplication", "Puede incrementar los drops hasta {} veces");
        addTooltip("duplication_crops", "Puede incrementar los drops de las plantas hasta {} veces");

        addTooltip("iron_tool_tooltip", "Velocidad de mineria incrementada en bloques de nivel piedra");
        addTooltip("gold_tool_tooltip", "Durabilidad incrementada cuando el objeto esta encantado");

        addTooltip("on_hit_effect", "Causa el siguiente efecto al atacar:");
        addTooltip("on_hit_effect_chance", "Puede causar el siguiente efecto al atacar:");
        addTooltip("on_hit_multiple_effect", "Puede causar los siguientes efectos al atacar:");
        addTooltip("attacker_on_hit_effect", "Puede causarte los siguientes efectos al atacar:");
        addTooltip("undead_on_hit", "Puede causar los siguientes efectos al atacar a los no-muertos:");

        addTooltip("avoid_damage", "Puede evitar la perdida de durabilidad");

        addTooltip("electrum", "Causa daño kinetico al moverse y atacar");

        addTooltip("armor_wearer_grants", "Da estos efectos:");
        addTooltip("armor_wearer_on_attacked", "Da estos efectos y recibir daño:");
        addTooltip("netherite_armor", "Cuando tu vida esta a menos del 50% y atacas a un enemigo, inflije:");

        addTooltip("grant_on_mine", "Cada {} cantidad de bloques, causa y aumenta el siguiente efecto:");

        add("actionbar.orevolution.cant_harvest_ore", "No podes destruir este bloque aun!");

        addTooltip("harvest_tier", "Nivel de mineria:");
        addTier("wood", "Madera");
        addTier("stone", "Piedra");
        addTier("tin", "Estaño");
        addTier("iron", "Hierro");
        addTier("platinum", "Platino");
        addTier("diamond", "Diamante");
        addTier("netherite", "Netherita");
        addTier("aethersteel", "Acero ètereo");

        addItem(RegItems.PLATINUM_SWORD, "Espada de platino");
        addItem(RegItems.PLATINUM_SHOVEL, "Pala de platino");
        addItem(RegItems.PLATINUM_PICKAXE, "Pico de platino");
        addItem(RegItems.PLATINUM_AXE, "Hacha de platino");
        addItem(RegItems.PLATINUM_HOE, "Azada de platino");
        addItem(RegItems.PLATINUM_HELMET, "Casco de platino");
        addItem(RegItems.PLATINUM_CHESTPLATE, "Pechera de platino");
        addItem(RegItems.PLATINUM_LEGGINGS, "Pantalones de platino");
        addItem(RegItems.PLATINUM_BOOTS, "Botas de platino");
        
        addItem(RegItems.TIN_SWORD, "Espada de estaño");
        addItem(RegItems.TIN_SHOVEL, "Pala de estaño");
        addItem(RegItems.TIN_PICKAXE, "Pico de estaño");
        addItem(RegItems.TIN_AXE, "Hacha de estaño");
        addItem(RegItems.TIN_HOE, "Azada de estaño");
        
        addItem(RegItems.AETHERSTEEL_SWORD, "Espada de acero ètereo");
        addItem(RegItems.AETHERSTEEL_SHOVEL, "Pala de acero ètereo");
        addItem(RegItems.AETHERSTEEL_PICKAXE, "Pico de acero ètereo");
        addItem(RegItems.AETHERSTEEL_AXE, "Hacha de acero ètereo");
        addItem(RegItems.AETHERSTEEL_HOE, "Azada de acero ètereo");
        addItem(RegItems.AETHERSTEEL_HELMET, "Casco de acero ètereo");
        addItem(RegItems.AETHERSTEEL_CHESTPLATE, "Pechera de acero ètereo");
        addItem(RegItems.AETHERSTEEL_LEGGINGS, "Pantalones de acero ètereo");
        addItem(RegItems.AETHERSTEEL_BOOTS, "Botas de acero ètereo");
        
        addItem(RegItems.REINFORCED_NETHERITE_HELMET, "Casco de netherita reforzada");
        addItem(RegItems.REINFORCED_NETHERITE_CHESTPLATE, "Pechera de netherita reforzada");
        addItem(RegItems.REINFORCED_NETHERITE_LEGGINGS, "Pantalones de netherita reforzada");
        addItem(RegItems.REINFORCED_NETHERITE_BOOTS, "Botas de netherita reforzada"); 
        
        addItem(RegItems.LIVINGSTONE_SWORD, "Espada de piedra viva");
        addItem(RegItems.LIVINGSTONE_SHOVEL, "Pala de piedra viva");
        addItem(RegItems.LIVINGSTONE_PICKAXE, "Pico de piedra viva");
        addItem(RegItems.LIVINGSTONE_AXE, "Hacha de piedra viva");
        addItem(RegItems.LIVINGSTONE_HOE, "Azada de piedra viva");
        addItem(RegItems.LIVINGSTONE_HELMET, "Casco de piedra viva");
        addItem(RegItems.LIVINGSTONE_CHESTPLATE, "Pechera de piedra viva");
        addItem(RegItems.LIVINGSTONE_LEGGINGS, "Pantalones de piedra viva");
        addItem(RegItems.LIVINGSTONE_BOOTS, "Botas de piedra viva");
        
        addItem(RegItems.VERDITE_SWORD, "Espada de verdita");
        addItem(RegItems.VERDITE_SHOVEL, "Pala de verdita");
        addItem(RegItems.VERDITE_PICKAXE, "Pico de verdita");
        addItem(RegItems.VERDITE_AXE, "Hacha de verdita");
        addItem(RegItems.VERDITE_HOE, "Azada de verdita");
        addItem(RegItems.VERDITE_HELMET, "Casco de verdita");
        addItem(RegItems.VERDITE_CHESTPLATE, "Pechera de verdita");
        addItem(RegItems.VERDITE_LEGGINGS, "Pantalones de verdita");
        addItem(RegItems.VERDITE_BOOTS, "Botas de verdita");

        addItem(RegItems.TIN_INGOT, "Lingote de estaño");
        addItem(RegItems.RAW_TIN, "Estaño crudo");
        addBlock(RegBlocks.TIN_BLOCK, "Bloque de estaño");

        addItem(RegItems.PLATINUM_INGOT, "Lingote de platino");
        addItem(RegItems.RAW_PLATINUM, "Platino crudo");
        addBlock(RegBlocks.PLATINUM_BLOCK, "Bloque de platino");

        addItem(RegItems.TUNGSTEN_INGOT, "Lingote de tungsteno");
        addItem(RegItems.RAW_TUNGSTEN, "Tungsteno crudo");
        addBlock(RegBlocks.TUNGSTEN_BLOCK, "Bloque de tungsteno");

        addItem(RegItems.BRONZE_ALLOY, "Aleación de bronce");
        addBlock(RegBlocks.BRONZE_BLOCK, "Bloque de bronce");

        addItem(RegItems.TUMBAGA_ALLOY, "Aleación de tumbaga");
        addBlock(RegBlocks.TUMBAGA_BLOCK, "Bloque de tumbaga");

        addItem(RegItems.STEEL_ALLOY, "Aleación de acero");
        addBlock(RegBlocks.STEEL_BLOCK, "Bloque de acero");

        addItem(RegItems.AETHERSTEEL_INGOT, "Lingote de acero ètereo");
        addItem(RegItems.AETHERSTEEL_CHUNK, "Trozo de acero ètereo");
        addBlock(RegBlocks.AETHERSTEEL_BLOCK, "Bloque de acero ètereo");

        addBlock(RegBlocks.REINFORCED_NETHERITE_BLOCK, "Bloque de netherita reforzada");

//        addItem(RegItems.REINFORCED_TEMPLATE, "Plantilla de reforzamiento");
//        addItem(RegItems.AETHERSTEEL_TEMPLATE, "Plantilla de acero ètereo");
//        addItem(RegItems.BASIC_TEMPLATE, "Plantilla básica");

        addItem(RegItems.TIN_NUGGET, "Pepita de estaño");
        addItem(RegItems.PLATINUM_NUGGET, "Pepita de platino");
        addItem(RegItems.TUNGSTEN_NUGGET, "Pepita de tungsteno");
        addItem(RegItems.VERDITE_NUGGET, "Pepita de verdita");
        addItem(RegItems.LIVINGSTONE_SHARD, "Fragmento de piedra viva");

        addItem(RegItems.VERDITE_APPLE, "Manzana de verdita");
    }
}
