package net.bexla.orevolution.datagens.langs;

import net.bexla.orevolution.Orevolution;
import net.bexla.orevolution.compatibility.farmersdelight.RegItemsFD;
import net.bexla.orevolution.compatibility.spelunkery.RegBlocksSK;
import net.bexla.orevolution.content.types.providers.LangProvider;
import net.bexla.orevolution.init.RegBlocks;
import net.bexla.orevolution.init.RegItems;
import net.bexla.orevolution.init.RegMobEffects;
import net.minecraft.data.PackOutput;

public class GENLangESAR extends LangProvider {
    public GENLangESAR(PackOutput output) {
        super(output, Orevolution.MODID, "es_ar");
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

    public void addAdvancemente(String id, String title, String desc) {
        String advancementID = Orevolution.MODID + "." + id;
        addAdvTitle(advancementID, title);
        addAdvDesc(advancementID, desc);
    }

    @Override
    protected void addTranslations() {
        addAdvancemente("aethersteel_hoe",
                "Herramienta sin motivo",
                "Gasta 1 Lingote de acero étereo para mejorar tu azada de netherita sin ningun motivo aparente"
        );
        addAdvancemente("aethersteel_armor",
                "Fuerza imparable",
                "Consigue el conjunto completo de la armadura de acero étereo"
        );
        addAdvancemente("obtain_primitive_aetherrock",
                "Encontrado en el abismo",
                "Consigue piedra éterea primitiva"
        );
        addAdvancemente("obtain_tungsten",
                "El metal mas resistente del Nether",
                "Crea un pico de platino"
        );
        addAdvancemente("submerge_in_lava",
                "Este calor, un calor temible",
                "Sumergete en lava con el conjunto de la armadura de netherita reforzada"
        );
        addAdvancemente("reinforced_armor",
                "Objeto inamovible",
                "Consigue el conjunto completo de la armadura de netherita reforzada"
        );
        addAdvancemente("platinum_gear",
                "Podre minar bastante rapido",
                "Crea un pico de platino"
        );
        addAdvancemente("platinum_armor",
                "El Rey en Azul",
                "Crea el conjunto completo de la armadura de platino"
        );
        addAdvancemente("obtain_platinum",
                "El metal mas resistente del mundo",
                "Funde un lingote de platino"
        );
        addAdvancemente("tin_upgrade",
                "Metal ligero",
                "Crea un pico de estaño"
        );

        addTooltip("duplication", "Tiene una probabilidad (Maxima de %s) de duplicar los drops de los bloques");
        addTooltip("triplication", "Tiene una probabilidad (Maxima de %s) de triplicar los drops de los bloques");

        addTooltip("duplication_explanation",
                "Lista de probabilidades dependiendo de los bloques:\n" +
                        " - Cualquier mineral (ej. Mineral de cobre) -> prob. del 5%\n" +
                        " - Bloques inusuales (ej. Yunque) -> mitad de la prob. maxima\n" +
                        " - Bloques raros (ej. Cualquier bloque de mineral) -> un quinto de la prob. maxima\n" +
                        " - Bloques 'siempre' (ej. Cualquier hoja de arbol) -> prob. del 100%\n" +
                        " - Bloques 'nunca' (ej. Bloque de acero ètereo) -> prob. del 0%"
        );

        addTooltip("triplication_explanation",
                "Lista de probabilidades dependiendo de los bloques:\n" +
                        " - Bloques 'siempre' (ej. Cualquier hoja de arbol) -> prob. del 100%\n" +
                        " - Bloques inusuales (ej Yunque) -> mitad de la prob. maxima\n" +
                        " - Bloques raros (ej. Cualquier bloque de mineral) -> un quinto de la prob. maxima\n" +
                        " - Cualquier mineral (ej. Mineral de cobre) -> prob. del 5%\n" +
                        " - Bloques 'nunca' (ej. Bloque de acero ètereo) -> prob. del 0%"
        );

        addTooltip("duplication_crops", "Tiene una probabilidad (Maxima de %s porciento) de duplicar los drops de las plantas");
        addTooltip("triplication_crops", "Tiene una probabilidad (Maxima de %s porciento) de triplicar los drops de las plantas");

        addTooltip("iron_tool_tooltip", "Velocidad de mineria incrementada en Pizarra Profunda, Tablones de madera y Tierra");
        addTooltip("gold_tool_tooltip", "Durabilidad incrementada por 160 cuando el objeto esta encantado");

        addTooltip("increase_loot", "Tiene una probabilidad del %s de aumentar el botin de los mobs");

        addTooltip("aethersteel_loot", "Tiene una probabilidad del %s de triplicar el botin de los mobs");

        addTooltip("on_hit_effect", "Causa el siguiente efecto(s) al atacar:");
        addTooltip("attacker_on_hit_effect", "Te causa el siguiente efecto(s) a vos al atacar");

        addTooltip("on_hit_effect_chance", "Tiene una probabilidad de causar el siguiente efecto(s) al atacar:");
        addTooltip("attacker_on_hit_effect_chance", "Tiene una probabilidad de causarte el siguiente efecto(s) a vos al attackar");

        addTooltip("undead_on_hit", "Tiene una probabilidad de causar los siguientes efecto(s) al atacar a los no-muertos:");

        addTooltip("avoid_damage", "Tiene una probabilidad de evitar la perdida de durabilidad");

        addTooltip("electrum", "Causa daño cinético al atacar y moverte. Cuanto más impulso tengas, más daño causará");
        addTooltip("necromium", "La velocidad de mineria incrementa dependiendo del desgaste");
        addTooltip("necromium_explanation", "La durabilidad funciona como un porcentaje de la velocidad base \nMientras menos durabilidad tenga, mayor será la velocidad de mineria");

        addTooltip("full_set_bonus", "Bono por set completo:");

        addTooltip("armor_wearer_grants", "Da los siguientes efecto(s):");
        addTooltip("armor_wearer_on_attacked", "Da los siguientes efecto(s) al recibir daño:");
        addTooltip("armor_wearer_on_attacked_target", "Da los siguientes efecto(s) al enemigo cuando te atacan:");

        addTooltip("armor_wearer_on_hit_wearer", "Da los siguientes efecto(s) al atacar:");
        addTooltip("armor_wearer_on_hit_target", "Causa los siguientes efecto(s) al enemigo al atacarlo:");

        addTooltip("armor_immunity", "Da los siguientes efecto(s):");
        addTooltip("armor_immunity_daylight", "Da inmunidad los siguientes efecto(s):");

        addTooltip("netherite_armor", "Cuando tu vida esta a menos del 50%, inflije los siguientes efecto(s) al atacar:");
        addTooltip("reinforced_netherite_armor", "Mientras no esta sumergido en lava, da los siguientes efecto(s):");

        addTooltip("electrum_armor", "Da los siguientes efecto(s) despues de recorrer cierta cantidad de bloques sin recibir daño:");
        addTooltip("electrum_armor_explanation",
                "Saltar o volar no aumenta la distancia recorrida\n" +
                        "Lista de distancia requerida para cada amplificador:\n" +
                        " - 50 bloques -> Velocidad I\n" +
                        " - 90 bloques -> Velocidad II\n" +
                        " - 140 bloques -> Velocidad III"
        );

        addTooltip("necromium_armor", "Te permite volver de la muerte \nTras morir, la abilidad se recargara por 5 minutos");

        addTooltip("grant_on_mine", "Cada %s cantidad de bloques, causa y aumenta los siguientes efecto(s):");

        addTooltip("multi_break", "Rompe bloques en un area de 3x3");
        addTooltip("multi_break_explanation",
                "Cada bloque que rompas reduce la durabilidad en 1 punto\n" +
                        "Romper 9 bloques resulta en perder 9 puntos de durabilidad\n" +
                        "Pierde 4 puntos de durabilidad por cada nivel de eficiencia\n" +
                        "Romper 9 bloques con eficiencia I resulta en perder 36 puntos de durabilidad"
        );

        add("actionbar.orevolution.cant_harvest_ore", "No podes destruir este bloque todavia");

        add("trim_material.orevolution.platinum", "Material de platino");
        add("trim_material.orevolution.tin", "Material de estaño");
        add("trim_material.orevolution.tungsten", "Material de tungsteno");

        addTooltip("press_key", "Presiona %s para ver mas informacion");

        addTooltip("harvest_tier", "Nivel de mineria:");
        addTier("wood", "Piedra");
        addTier("stone", "Estaño");
        addTier("tin", "Hierro");
        addTier("iron", "Platino");
        addTier("platinum", "Diamante");
        addTier("diamond", "Netherita");
        addTier("netherite", "Acero étereo");
        addTier("aethersteel", "Acero étereo");

        addEffect(RegMobEffects.CRUSHED, "Aplastado");
        addEffect(RegMobEffects.PETRIFIED, "Petrificado");
        addEffect(RegMobEffects.WEAK_SOUL, "Alma debilitada");

        addItem(RegItems.DEAD_SEED, "Semilla muerta");
        addBlock(RegBlocks.VERDITE_CROP, "Cultivo de verdita");
        addBlock(RegBlocks.LIVINGSTONE_BLOCK, "Bloque de piedra viva");
        addItem(RegItems.PETRIFIED_SEED, "Semilla petrificada");
        addBlock(RegBlocks.LIVINGSTONE_CROP, "Cultivo de piedra viva");

        addItem(RegItems.PLATINUM_SHIELD, "Escudo de platino");
        addItem(RegItemsFD.PLATINUM_KNIFE, "Cuchillo de platino");
        addItem(RegItems.PLATINUM_SWORD, "Espada de platino");
        addItem(RegItems.PLATINUM_SHOVEL, "Pala de platino");
        addItem(RegItems.PLATINUM_PICKAXE, "Pico de platino");
        addItem(RegItems.PLATINUM_AXE, "Hacha de platino");
        addItem(RegItems.PLATINUM_HOE, "Azada de platino");
        addItem(RegItems.PLATINUM_HELMET, "Casco de platino");
        addItem(RegItems.PLATINUM_CHESTPLATE, "Pechera de platino");
        addItem(RegItems.PLATINUM_LEGGINGS, "Pantalones de platino");
        addItem(RegItems.PLATINUM_BOOTS, "Botas de platino");

        addItem(RegItems.TIN_SHIELD, "Escudo de estaño");
        addItem(RegItemsFD.TIN_KNIFE, "Cuchillo de estaño");
        addItem(RegItems.TIN_SWORD, "Espada de estaño");
        addItem(RegItems.TIN_SHOVEL, "Pala de estaño");
        addItem(RegItems.TIN_PICKAXE, "Pico de estaño");
        addItem(RegItems.TIN_AXE, "Hacha de estaño");
        addItem(RegItems.TIN_HOE, "Azada de estaño");

        addItem(RegItems.AETHERSTEEL_SHIELD, "Escudo de acero étereo");
        addItem(RegItemsFD.AETHERSTEEL_KNIFE, "Cuchillo de acero étereo");
        addItem(RegItems.AETHERSTEEL_SWORD, "Espada de acero étereo");
        addItem(RegItems.AETHERSTEEL_SHOVEL, "Pala de acero étereo");
        addItem(RegItems.AETHERSTEEL_PICKAXE, "Pico de acero étereo");
        addItem(RegItems.AETHERSTEEL_AXE, "Hacha de acero étereo");
        addItem(RegItems.AETHERSTEEL_HOE, "Azada de acero étereo");
        addItem(RegItems.AETHERSTEEL_HELMET, "Casco de acero étereo");
        addItem(RegItems.AETHERSTEEL_CHESTPLATE, "Pechera de acero étereo");
        addItem(RegItems.AETHERSTEEL_LEGGINGS, "Pantalones de acero étereo");
        addItem(RegItems.AETHERSTEEL_BOOTS, "Botas de acero étereo");

        addItem(RegItems.REINFORCED_NETHERITE_HELMET, "Casco de netherita reforzada");
        addItem(RegItems.REINFORCED_NETHERITE_CHESTPLATE, "Pechera de netherita reforzada");
        addItem(RegItems.REINFORCED_NETHERITE_LEGGINGS, "Pantalones de netherita reforzada");
        addItem(RegItems.REINFORCED_NETHERITE_BOOTS, "Botas de netherita reforzada");

        addItem(RegItems.LIVINGSTONE_SHIELD, "Escudo de piedra viva");
        addItem(RegItemsFD.LIVINGSTONE_KNIFE, "Cuchillo de piedra viva");
        addItem(RegItems.LIVINGSTONE_SWORD, "Espada de piedra viva");
        addItem(RegItems.LIVINGSTONE_SHOVEL, "Pala de piedra viva");
        addItem(RegItems.LIVINGSTONE_PICKAXE, "Pico de piedra viva");
        addItem(RegItems.LIVINGSTONE_AXE, "Hacha de piedra viva");
        addItem(RegItems.LIVINGSTONE_HOE, "Azada de piedra viva");
        addItem(RegItems.LIVINGSTONE_HELMET, "Casco de piedra viva");
        addItem(RegItems.LIVINGSTONE_CHESTPLATE, "Pechera de piedra viva");
        addItem(RegItems.LIVINGSTONE_LEGGINGS, "Pantalones de piedra viva");
        addItem(RegItems.LIVINGSTONE_BOOTS, "Botas de piedra viva");

        addItem(RegItems.VERDITE_SHIELD, "Escudo de verdita");
        addItem(RegItemsFD.VERDITE_KNIFE, "Cuchillo de verdita");
        addItem(RegItems.VERDITE_SWORD, "Espada de verdita");
        addItem(RegItems.VERDITE_SHOVEL, "Pala de verdita");
        addItem(RegItems.VERDITE_PICKAXE, "Pico de verdita");
        addItem(RegItems.VERDITE_AXE, "Hacha de verdita");
        addItem(RegItems.VERDITE_HOE, "Azada de verdita");
        addItem(RegItems.VERDITE_HELMET, "Casco de verdita");
        addItem(RegItems.VERDITE_CHESTPLATE, "Pechera de verdita");
        addItem(RegItems.VERDITE_LEGGINGS, "Pantalones de verdita");
        addItem(RegItems.VERDITE_BOOTS, "Botas de verdita");

        addItem(RegItems.STEEL_DIGGER, "Excavador de acero");
        addItem(RegItems.STEEL_HAMMER, "Martillo de acero");
        addItem(RegItems.STEEL_SCYTHE, "Guadaña de acero");

        addItem(RegItems.BRONZE_CROWN, "Corona de bronce");
        addItem(RegItems.BRONZE_CROWN_DIAMOND, "Corona de bronce (Diamond)");
        addItem(RegItems.BRONZE_CROWN_EMERALD, "Corona de bronce (Emerald)");
        addItem(RegItems.BRONZE_CROWN_LAPIS, "Corona de bronce (Lapis)");
        addItem(RegItems.BRONZE_CROWN_REDSTONE, "Corona de bronce (Redstone)");

        addItem(RegItems.TIN_INGOT, "Lingote de estaño");
        addItem(RegItems.RAW_TIN, "Estaño crudo");
        addBlock(RegBlocks.TIN_BLOCK, "Bloque de estaño");
        addBlock(RegBlocks.RAW_TIN_BLOCK, "Bloque de estaño crudo");

        addItem(RegItems.PLATINUM_INGOT, "Lingote de platino");
        addItem(RegItems.RAW_PLATINUM, "Platino crudo");
        addBlock(RegBlocks.PLATINUM_BLOCK, "Bloque de platino");
        addBlock(RegBlocks.RAW_PLATINUM_BLOCK, "Bloque de platino crudo");

        addItem(RegItems.TUNGSTEN_INGOT, "Lingote de tungsteno");
        addItem(RegItems.RAW_TUNGSTEN, "Tungsteno crudo");
        addBlock(RegBlocks.TUNGSTEN_BLOCK, "Bloque de tungsteno");
        addBlock(RegBlocks.RAW_TUNGSTEN_BLOCK, "Bloque de tungsteno crudo");

        addItem(RegItems.BRONZE_ALLOY, "Aleación de bronce");
        addBlock(RegBlocks.BRONZE_BLOCK, "Bloque de bronce");

        addItem(RegItems.STEEL_ALLOY, "Aleación de acero");
        addBlock(RegBlocks.STEEL_BLOCK, "Bloque de acero");

        addItem(RegItems.VERDITE_INGOT, "Lingote de verdita");
        addBlock(RegBlocks.VERDITE_BLOCK, "Bloque de verdita");

        addItem(RegItems.AETHERSTEEL_INGOT, "Lingote de acero étereo");
        addItem(RegItems.AETHERSTEEL_CHUNK, "Trozo de acero étereo");
        addBlock(RegBlocks.AETHERSTEEL_BLOCK, "Bloque de acero étereo");

        addSmithingTemplateTips("basic", "Mejora basica", "Equipamiento de hierro", "Aleacion de acero", "Agregá una armadura, arma o herramienta de hierro", "Agregá un lingote de acero");
        addSmithingTemplateTips("tungsten", "Mejora de reforzamiento", "Equipamiento de netherita", "Lingote de tungsteno", "Agregá una armadura, arma o herramienta de netherita", "Agregá un lingote de tungsteno");
        addSmithingTemplateTips("aethersteel", "Mejora del cielo", "Equipamiento de netherita y netherita reforzada", "Lingote de acero étereo", "Agregá una armadura de netherita reforzada, arma o herramienta de netherita", "Agregá un lingote de acero étereo");

        addItem(RegItems.TIN_NUGGET, "Pepita de estaño");
        addItem(RegItems.PLATINUM_NUGGET, "Pepita de platino");
        addItem(RegItems.TUNGSTEN_NUGGET, "Pepita de tungsteno");
        addItem(RegItems.VERDITE_NUGGET, "Pepita de verdita");
        addItem(RegItems.LIVINGSTONE_SHARD, "Fragmento de piedra viva");

        addItem(RegItems.VERDITE_APPLE, "Manzana de verdita");

        addBlock(RegBlocks.TIN_ORE, "Mineral de estaño");
        addBlock(RegBlocks.DEEPSLATE_TIN_ORE, "Mineral de estaño de pizarra profunda");
        addBlock(RegBlocks.PLATINUM_ORE, "Mineral de platino");
        addBlock(RegBlocks.DEEPSLATE_PLATINUM_ORE, "Mineral de platino de pizarra profunda");
        addBlock(RegBlocks.NETHER_TUNGSTEN_ORE, "Mineral de tungsteno del Nether");
        addBlock(RegBlocks.NETHER_XP_ORE, "Mineral de experiencia del Nether");
        addBlock(RegBlocks.END_XP_ORE, "Mineral de experiencia del End");
        addBlock(RegBlocks.AETHERROCK, "Piedra éterea");
        addBlock(RegBlocks.AETHERROCK_TILES, "Losetas de piedra éterea");
        addBlock(RegBlocks.POLISHED_AETHERROCK, "Piedra éterea pulida");
        addBlock(RegBlocks.AETHERROCK_BRICKS, "Ladrillos de piedra éterea");
        addBlock(RegBlocks.CRACKED_AETHERROCK_BRICKS, "Ladrillos de piedra éterea agrietados");
        addBlock(RegBlocks.PRIMITIVE_AETHERROCK, "Piedra éterea primitiva");
        addBlock(RegBlocks.CUT_STEEL_BLOCK, "Bloque de acero cortado");
        addBlock(RegBlocks.CUT_TUNGSTEN_BLOCK, "Bloque de tungsteno cortado");
        addBlock(RegBlocks.BRONZE_TILES, "Losetas de bronce");
        addBlock(RegBlocks.STEEL_PILLAR, "Pilar de acero");
        addBlock(RegBlocks.STEEL_DOOR, "Puerta de acero");
        addBlock(RegBlocks.CHISELED_TUNGSTEN_BLOCK, "Bloque de tungsteno cinselado");
        addBlock(RegBlocks.CHISELED_TUNGSTEN_BRICKS, "Ladrillos de tungsteno cinselado");
        addBlock(RegBlocks.TUNGSTEN_BRICKS, "Ladrillos de tungsteno");
        addBlock(RegBlocks.TIN_BRICKS, "Ladrillos de estaño");

        addBlock(RegBlocks.TIN_TILES, "Losetas de estaño");
        addBlock(RegBlocks.PLATINUM_TILES, "Losetas de platino");
        addBlock(RegBlocks.GOLD_TILES, "Losetas de oro");
        addBlock(RegBlocks.PLATINUM_PILLAR, "Pilar de platino");
        addBlock(RegBlocks.GOLD_PILLAR, "Pilar de oro");
        addBlock(RegBlocks.BRONZE_BARS, "Barras de bronce");
        addBlock(RegBlocks.GOLD_BARS, "Barras de oro");
        addBlock(RegBlocks.TUNGSTEN_BARS, "Barras de tungsteno");
        addBlock(RegBlocks.TIN_BARS, "Barras de estaño");
        addBlock(RegBlocks.PLATINUM_BARS, "Barras de platino");

        addBlock(RegBlocks.POLISHED_AETHERROCK_WALL, "Muro de piedra éterea pulida");
        addBlock(RegBlocks.POLISHED_AETHERROCK_STAIR, "Escalera de piedra éterea pulida");
        addBlock(RegBlocks.POLISHED_AETHERROCK_SLAB, "Baldosa de piedra éterea pulida");

        addBlock(RegBlocksSK.TIN_ORE_ANDESITE, "Mineral de estaño de andesita");
        addBlock(RegBlocksSK.TIN_ORE_GRANITE, "Mineral de estaño de granito");
        addBlock(RegBlocksSK.TIN_ORE_DIORITE, "Mineral de estaño de diorita");
        addBlock(RegBlocksSK.TIN_ORE_TUFF, "Mineral de estaño de toba");
        addBlock(RegBlocksSK.PLATINUM_ORE_ANDESITE, "Mineral de platino de andesita");
        addBlock(RegBlocksSK.PLATINUM_ORE_GRANITE, "Mineral de platino de granito");
        addBlock(RegBlocksSK.PLATINUM_ORE_DIORITE, "Mineral de platino de diorita");
        addBlock(RegBlocksSK.PLATINUM_ORE_TUFF, "Mineral de platino de toba");
        addBlock(RegBlocksSK.NETHER_TUNGSTEN_ORE_BLACKSTONE, "Mineral de tungsteno del Nether de piedra negra");
    }
}
