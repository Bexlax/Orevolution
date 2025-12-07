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
    private static final ForgeConfigSpec COMMON_SPEC;
    private static final ForgeConfigSpec CLIENT_SPEC;

    public static class Common {
        public final ConfigValue<Boolean> toolsPowers;
        public final ConfigValue<Boolean> weaponsPowers;
        public final ConfigValue<Boolean> armorsPowers;

        public final ConfigValue<Boolean> generateTinOre;
        public final ConfigValue<Boolean> generateExperienceOre;
        public final ConfigValue<Boolean> generateTungstenOre;
        public final ConfigValue<Boolean> generatePlatOre;
        public final ConfigValue<Boolean> generateAethersteelOre;
        public final ConfigValue<Boolean> generateAetherrockMeteor;

        public final ConfigValue<Boolean> tinRepair;

        public final ConfigValue<Boolean> safeOreBreaking;

        public final ConfigValue<Boolean> modProgression;

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

        private Common(ForgeConfigSpec.Builder builder) {
            builder.push("Gameplay - Powers");

            toolsPowers = builder
                    .comment("Defines if tools will have their special characteristic (aka power), like tin's drop duplication")
                    .define("tools_powers", true);
            weaponsPowers = builder
                    .comment("Defines if weapons will have their special characteristic (aka power), like tin's drop duplication")
                    .define("weapons_powers", true);
            armorsPowers = builder
                    .comment("Defines if armors will have their special characteristic (aka power), like platinum's strength II effect")
                    .define("armors_powers", true);

            builder.pop();

            builder.push("Gameplay - General Changes");

            tinRepair = builder
                    .comment("Defines if tin can be used to repair other items")
                    .define("tin_repairs_items", true);

            generateTinOre = builder
                    .comment("Defines if tin ore will generate in your world")
                    .define("generate_tin_ore", true);


            generatePlatOre = builder
                    .comment("Defines if platinum ore will generate in your world")
                    .define("generate_platinum_ore", true);


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


            builder.push("Gameplay - Item Durability");

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

        COMMON = commonSpecPair.getLeft();
        CLIENT = clientSpecPair.getLeft();
        COMMON_SPEC = commonSpecPair.getRight();
        CLIENT_SPEC = clientSpecPair.getRight();
    }

    public static void register() {
        var context = ModLoadingContext.get();
        context.registerConfig(ModConfig.Type.COMMON, COMMON_SPEC);
        context.registerConfig(ModConfig.Type.CLIENT, CLIENT_SPEC);
    }
}
