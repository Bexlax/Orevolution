package net.bexla.orevolution;

import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.common.ForgeConfigSpec.ConfigValue;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import org.apache.commons.lang3.tuple.Pair;

@Mod.EventBusSubscriber(modid = Orevolution.MODID)
public class OrevolutionConfig
{
    public static final Common COMMON;
    public static final Client CLIENT;
    public static final Powers POWERS;
    public static final ModCompat MODCOMPAT;
    public static final ToolStats TOOLSTATS;
    private static final ForgeConfigSpec COMMON_SPEC;
    private static final ForgeConfigSpec CLIENT_SPEC;
    private static final ForgeConfigSpec POWERS_SPEC;
    private static final ForgeConfigSpec MODCOMPAT_SPEC;
    private static final ForgeConfigSpec TOOLSTATS_SPEC;


    public static class Powers {
        public final ConfigValue<Boolean> tinToolPowers;
        public final ConfigValue<Boolean> ironToolPowers;
        public final ConfigValue<Boolean> goldToolPowers;
        public final ConfigValue<Boolean> platinumToolPowers;
        public final ConfigValue<Boolean> diamondToolPowers;
        public final ConfigValue<Boolean> netheriteToolPowers;
        public final ConfigValue<Boolean> aethersteelToolPowers;
        public final ConfigValue<Boolean> livingstoneToolPowers;
        public final ConfigValue<Boolean> verditeToolPowers;
        public final ConfigValue<Boolean> steelToolPowers;

        public final ConfigValue<Boolean> tinWeaponPowers;
        public final ConfigValue<Boolean> ironWeaponPowers;
        public final ConfigValue<Boolean> goldWeaponPowers;
        public final ConfigValue<Boolean> platinumWeaponPowers;
        public final ConfigValue<Boolean> diamondWeaponPowers;
        public final ConfigValue<Boolean> netheriteWeaponPowers;
        public final ConfigValue<Boolean> aethersteelWeaponPowers;
        public final ConfigValue<Boolean> livingstoneWeaponPowers;
        public final ConfigValue<Boolean> verditeWeaponPowers;
        public final ConfigValue<Boolean> steelWeaponPowers;
        
        public final ConfigValue<Boolean> ironArmorPowers;
        public final ConfigValue<Boolean> goldArmorPowers;
        public final ConfigValue<Boolean> platinumArmorPowers;
        public final ConfigValue<Boolean> diamondArmorPowers;
        public final ConfigValue<Boolean> netheriteArmorPowers;
        public final ConfigValue<Boolean> reinforcedNetheriteArmorPowers;
        public final ConfigValue<Boolean> aethersteelArmorPowers;
        public final ConfigValue<Boolean> livingstoneArmorPowers;
        public final ConfigValue<Boolean> verditeArmorPowers;
        public final ConfigValue<Boolean> bronzeArmorPowers;
        public final ConfigValue<Boolean> tungstenArmorPowers;

        private Powers(ForgeConfigSpec.Builder builder) {
            builder.push("Powers - Tools");

            tinToolPowers = builder
                    .define("tin_tool_powers", true);
            ironToolPowers = builder
                    .define("iron_tool_powers", true);
            goldToolPowers = builder
                    .define("gold_tool_powers", true);
            platinumToolPowers = builder
                    .define("platinum_tool_powers", true);
            diamondToolPowers = builder
                    .define("diamond_tool_powers", true);
            netheriteToolPowers = builder
                    .define("netherite_tool_powers", true);
            aethersteelToolPowers = builder
                    .define("aethersteel_tool_powers", true);
            livingstoneToolPowers = builder
                    .define("livingstone_tool_powers", true);
            verditeToolPowers = builder
                    .define("verdite_tool_powers", true);
            steelToolPowers = builder
                    .define("steel_tool_powers", true);
            
            builder.pop();

            builder.push("Powers - Weapons");

            tinWeaponPowers = builder
                    .define("tin_weapon_powers", true);
            ironWeaponPowers = builder
                    .define("iron_weapon_powers", true);
            goldWeaponPowers = builder
                    .define("gold_weapon_powers", true);
            platinumWeaponPowers = builder
                    .define("platinum_weapon_powers", true);
            diamondWeaponPowers = builder
                    .define("diamond_weapon_powers", true);
            netheriteWeaponPowers = builder
                    .define("netherite_weapon_powers", true);
            aethersteelWeaponPowers = builder
                    .define("aethersteel_weapon_powers", true);
            livingstoneWeaponPowers = builder
                    .define("livingstone_weapon_powers", true);
            verditeWeaponPowers = builder
                    .define("verdite_weapon_powers", true);
            steelWeaponPowers = builder
                    .define("steel_weapon_powers", true);

            builder.pop();

            builder.push("Powers - Armors");

            ironArmorPowers = builder
                    .define("iron_armor_powers", true);
            goldArmorPowers = builder
                    .define("gold_armor_powers", true);
            platinumArmorPowers = builder
                    .define("platinum_armor_powers", true);
            diamondArmorPowers = builder
                    .define("diamond_armor_powers", true);
            netheriteArmorPowers = builder
                    .define("netherite_armor_powers", true);
            reinforcedNetheriteArmorPowers = builder
                    .define("reinforced_netherite_armor_powers", true);
            aethersteelArmorPowers = builder
                    .define("aethersteel_armor_powers", true);
            livingstoneArmorPowers = builder
                    .define("livingstone_armor_powers", true);
            verditeArmorPowers = builder
                    .define("verdite_armor_powers", true);
            bronzeArmorPowers = builder
                    .define("bronze_armor_powers", true);
            tungstenArmorPowers = builder
                    .define("tungsten_armor_powers", true);
            
            builder.pop();
        }

    }

    public static class ToolStats {
        public final ConfigValue<Integer> woodMaxUses;
        public final ConfigValue<Integer> stoneMaxUses;
        public final ConfigValue<Integer> tinMaxUses;
        public final ConfigValue<Integer> goldMaxUses;
        public final ConfigValue<Integer> ironMaxUses;
        public final ConfigValue<Integer> platMaxUses;
        public final ConfigValue<Integer> diamondMaxUses;
        public final ConfigValue<Integer> netheriteMaxUses;
        public final ConfigValue<Integer> aetherMaxUses;
        public final ConfigValue<Integer> livingstoneMaxUses;
        public final ConfigValue<Integer> verditeMaxUses;
        public final ConfigValue<Integer> steelMaxUses;

        private ToolStats(ForgeConfigSpec.Builder builder) {
            builder.push("Durability");

            woodMaxUses = builder
                    .comment("Modifies the max uses of wood tools and weapons. vanilla is 59")
                    .defineInRange("wood_max_uses", 64, 1, Integer.MAX_VALUE);

            goldMaxUses = builder
                    .comment("Modifies the max uses of gold tools and weapons. vanilla is 32")
                    .defineInRange("gold_max_uses", 128, 1, Integer.MAX_VALUE);
            stoneMaxUses = builder
                    .comment("Modifies the max uses of stone tools and weapons. vanilla is 131")
                    .defineInRange("stone_max_uses", 192, 1, Integer.MAX_VALUE);
            tinMaxUses = builder
                    .comment("Modifies the max uses of tin tools and weapons. vanilla is 256")
                    .defineInRange("tin_max_uses", 256, 1, Integer.MAX_VALUE);
            ironMaxUses = builder
                    .comment("Modifies the max uses of iron tools and weapons. vanilla is 250")
                    .defineInRange("iron_max_uses", 448, 1, Integer.MAX_VALUE);
            platMaxUses = builder
                    .comment("Modifies the max uses of platinum tools and weapons. vanilla is 768")
                    .defineInRange("platinum_max_uses", 768, 1, Integer.MAX_VALUE);
            diamondMaxUses = builder
                    .comment("Modifies the max uses of diamond tools and weapons. vanilla is 1561")
                    .defineInRange("diamond_max_uses", 1600, 1, Integer.MAX_VALUE);
            netheriteMaxUses = builder
                    .comment("Modifies the max uses of netherite tools and weapons. vanilla is 2031")
                    .defineInRange("netherite_max_uses", 2432, 1, Integer.MAX_VALUE);
            aetherMaxUses = builder
                    .comment("Modifies the max uses of aethersteel tools and weapons. vanilla is 3520")
                    .defineInRange("aetherite_max_uses", 3520, 1, Integer.MAX_VALUE);
            livingstoneMaxUses = builder
                    .comment("Modifies the max uses of livingstone tools and weapons. vanilla is 192")
                    .defineInRange("livingstone_max_uses", 192, 1, Integer.MAX_VALUE);
            verditeMaxUses = builder
                    .comment("Modifies the max uses of verdite tools and weapons. vanilla is 448")
                    .defineInRange("verdite_max_uses", 448, 1, Integer.MAX_VALUE);
            steelMaxUses = builder
                    .comment("Modifies the max uses of steel tools. vanilla is 1152")
                    .defineInRange("steel_max_uses", 1152, 1, Integer.MAX_VALUE);

            builder.pop();
        }
    }

    public static class ModCompat {
        public final ConfigValue<Boolean> electrumToolPowers;
        public final ConfigValue<Boolean> electrumWeaponPowers;
        public final ConfigValue<Boolean> electrumArmorPowers;

        public final ConfigValue<Integer> electrumMaxUses;

        public final ConfigValue<Boolean> copperArmorPowers;

        public final ConfigValue<Integer> copperMaxUses;

        private ModCompat(ForgeConfigSpec.Builder builder) {
            builder.push("- Oreganized -");

            electrumToolPowers = builder
                    .define("electrum_tool_powers", true);
            electrumWeaponPowers = builder
                    .define("electrum_weapon_powers", true);
            electrumArmorPowers = builder
                    .define("electrum_armor_powers", true);

            electrumMaxUses = builder
                    .comment("Modifies the max uses of electrum tools and weapons. vanilla is 1561, modded is 1920")
                    .defineInRange("electrum_max_uses", 1920, 1, Integer.MAX_VALUE);

            builder.pop();

            builder.push("- Copper Age Backport -");

            copperArmorPowers = builder
                    .define("copper_armor_powers", true);

            copperMaxUses = builder
                    .comment("Modifies the max uses of electrum tools and weapons. vanilla is 190, modded is 320")
                    .defineInRange("electrum_max_uses", 320, 1, Integer.MAX_VALUE);

            builder.pop();
        }
    }

    public static class Common {
        public final ConfigValue<Boolean> toolsPowers;
        public final ConfigValue<Boolean> weaponsPowers;
        public final ConfigValue<Boolean> armorsPowers;

        public final ConfigValue<Boolean> generateTinOre;
        public final ConfigValue<Boolean> generateExperienceOre;
        public final ConfigValue<Boolean> generateTungstenOre;
        public final ConfigValue<Boolean> generatePlatOre;
        public final ConfigValue<Boolean> generateLimestone;
        public final ConfigValue<Boolean> generateAethersteelOre;
        public final ConfigValue<Boolean> generateAetherrockMeteor;

        public final ConfigValue<Boolean> safeOreBreaking;

        public final ConfigValue<Boolean> modProgression;

        public final ConfigValue<Boolean> steelEfficiencyNerf;

        private Common(ForgeConfigSpec.Builder builder) {
            builder.push("Gameplay - Powers");

            toolsPowers = builder
                    .comment("Defines if tools will have their special characteristic (aka power), like tin's drop duplication. check client configs to disable the tooltip too")
                    .define("tools_powers", true);
            weaponsPowers = builder
                    .comment("Defines if weapons will have their special characteristic (aka power), like tin's drop duplication. check client configs to disable the tooltip too")
                    .define("weapons_powers", true);
            armorsPowers = builder
                    .comment("Defines if armors will have their special characteristic (aka power), like platinum's strength II effect. check client configs to disable the tooltip too")
                    .define("armors_powers", true);

            builder.pop();

            builder.push("Gameplay - General Changes");

            generateTinOre = builder
                    .comment("Defines if tin ore will generate in your world")
                    .define("generate_tin_ore", true);


            generatePlatOre = builder
                    .comment("Defines if platinum ore will generate in your world")
                    .define("generate_platinum_ore", true);

            generateLimestone = builder
                    .comment("Defines if limestone will generate in your world")
                    .define("generate_limestone", true);


            generateTungstenOre = builder
                    .comment("Defines if tungsten ore will generate in your world")
                    .define("generate_tungsten_ore", true);


            generateExperienceOre = builder
                    .comment("Defines if experience ore will generate in your world")
                    .define("generate_experience_ore", true);


            generateAethersteelOre = builder.
                    comment("Defines if aethersteel ore will generate in your world")
                    .define("generate_aethersteel_ore", true);

            generateAetherrockMeteor = builder.
                    comment("Defines if aetherrock meteor will generate in your world (this will also disable aethersteel)")
                    .define("generate_aetherrock_meteor", true);

            safeOreBreaking = builder
                    .comment("Ores won't break if mined with the incorrect tool")
                    .define("safe_ore_breaking", true);

            modProgression = builder
                    .comment("Replaces the original ore progression of the game")
                    .define("modded_progression", true);

            builder.pop();

            builder.push("Gameplay - Other Changes and Balances");

            steelEfficiencyNerf = builder
                    .comment("Defines if steel tools will increase their durability cost for each mined block per efficiency level")
                    .define("steel_efficiency_nerf", true);
        }
    }

    public static class Client {
        public final ConfigValue<Boolean> warnBreak;
        public final ConfigValue<Boolean> harvestTip;
        public final ConfigValue<Boolean> toolsPowersTip;
        public final ConfigValue<Boolean> weaponsPowersTip;
        public final ConfigValue<Boolean> armorsPowersTip;
        public final ConfigValue<Boolean> tinProgTip;
        public final ConfigValue<Boolean> platProgTip;

        private Client(ForgeConfigSpec.Builder builder) {
            builder.push("gameplay");

            warnBreak = builder
                    .comment("Display the text 'You can't harvest this block yet!'")
                    .define("warn_break", true);

            builder.pop();


            builder.push("tooltips");

            harvestTip = builder
                    .comment("Display the 'Harvest tier' tooltip on all tiered tools")
                    .define("harvest_tip", true);
            toolsPowersTip = builder
                    .comment("Display the 'power' tooltip on all tools that have one")
                    .define("tools_powers_tip", true);
            weaponsPowersTip = builder
                    .comment("Display the 'power' tooltip on all weapons that have one")
                    .define("weapons_powers_tip", true);
            armorsPowersTip = builder
                    .comment("Display the 'power' tooltip on all armors that have one")
                    .define("armors_powers_tip", true);
            tinProgTip = builder
                    .comment("Display the 'Tin tier' tooltip on all stone-tiered tools")
                    .define("tin_prog_tip", true);
            platProgTip = builder
                    .comment("Display the 'Platinum tier' tooltip on all iron-tiered tools")
                    .define("plat_prog_tip", true);

            builder.pop();
        }
    }

    static {
        final Pair<Common, ForgeConfigSpec> commonSpecPair = new ForgeConfigSpec.Builder().configure(Common::new);
        final Pair<Client, ForgeConfigSpec> clientSpecPair = new ForgeConfigSpec.Builder().configure(Client::new);
        final Pair<Powers, ForgeConfigSpec> powerSpecPair = new ForgeConfigSpec.Builder().configure(Powers::new);
        final Pair<ToolStats, ForgeConfigSpec> toolStatsSpecPair = new ForgeConfigSpec.Builder().configure(ToolStats::new);
        final Pair<ModCompat, ForgeConfigSpec> modCompatSpecPair = new ForgeConfigSpec.Builder().configure(ModCompat::new);

        MODCOMPAT = modCompatSpecPair.getLeft();
        POWERS = powerSpecPair.getLeft();
        TOOLSTATS = toolStatsSpecPair.getLeft();
        COMMON = commonSpecPair.getLeft();
        CLIENT = clientSpecPair.getLeft();
        MODCOMPAT_SPEC = modCompatSpecPair.getRight();
        POWERS_SPEC = powerSpecPair.getRight();
        TOOLSTATS_SPEC = toolStatsSpecPair.getRight();
        COMMON_SPEC = commonSpecPair.getRight();
        CLIENT_SPEC = clientSpecPair.getRight();
    }

    public static void register() {
        var context = ModLoadingContext.get();
        context.registerConfig(ModConfig.Type.COMMON, COMMON_SPEC);
        context.registerConfig(ModConfig.Type.CLIENT, CLIENT_SPEC);
        context.registerConfig(ModConfig.Type.COMMON, MODCOMPAT_SPEC, "orevolution-modcompat.toml");
        context.registerConfig(ModConfig.Type.COMMON, POWERS_SPEC, "orevolution-powers.toml");
        context.registerConfig(ModConfig.Type.COMMON, TOOLSTATS_SPEC, "orevolution-toolstats.toml");

    }
}
