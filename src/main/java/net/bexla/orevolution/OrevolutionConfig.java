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

        public final ConfigValue<Boolean> safeOreBreaking;

        public final ConfigValue<Boolean> tinProgression;
        public final ConfigValue<Boolean> platinumProgression;

        public final ConfigValue<Integer> woodMaxUses;
        public final ConfigValue<Integer> stoneMaxUses;
        public final ConfigValue<Integer> goldMaxUses;
        public final ConfigValue<Integer> ironMaxUses;
        public final ConfigValue<Integer> diamondMaxUses;
        public final ConfigValue<Integer> netheriteMaxUses;

        private Common(ForgeConfigSpec.Builder builder) {
            builder.push("powers");

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

            builder.push("gameplay_general");

            safeOreBreaking = builder
                    .comment("Ores won't break if mined with the incorrect tool")
                    .define("safe_ore_breaking", true);

            builder.pop();


            builder.push("gameplay_progression");

            tinProgression = builder
                    .comment("Progress changes from [ Stone -> Iron ] to [ Stone -> Tin ->  Iron ]. Can be further adjusted through datapacks")
                    .define("tin_progression", true);

            platinumProgression = builder
                    .comment("Progress changes from [ Iron -> Diamond ] to [ Iron -> Platinum ->  Diamond ]. Can be further adjusted through datapacks")
                    .define("platinum_progression", true);

            builder.pop();


            builder.push("gameplay_durability");

            woodMaxUses = builder
                    .comment("Modifies the max uses of wood tools and weapons. vanilla is 59")
                    .defineInRange("wood_max_uses", 64, 1, Integer.MAX_VALUE);
            goldMaxUses = builder
                    .comment("Modifies the max uses of gold tools and weapons. vanilla is 32")
                    .defineInRange("gold_max_uses", 128, 1, Integer.MAX_VALUE);
            stoneMaxUses = builder
                    .comment("Modifies the max uses of stone tools and weapons. vanilla is 131")
                    .defineInRange("stone_max_uses", 192, 1, Integer.MAX_VALUE);
            ironMaxUses = builder
                    .comment("Modifies the max uses of iron tools and weapons. vanilla is 250")
                    .defineInRange("iron_max_uses", 448, 1, Integer.MAX_VALUE);
            diamondMaxUses = builder
                    .comment("Modifies the max uses of diamond tools and weapons. vanilla is 1561")
                    .defineInRange("diamond_max_uses", 1600, 1, Integer.MAX_VALUE);
            netheriteMaxUses = builder
                    .comment("Modifies the max uses of netherite tools and weapons. vanilla is 2031")
                    .defineInRange("netherite_max_uses", 2432, 1, Integer.MAX_VALUE);

            builder.pop();
        }
    }

    public static class Client {
        public final ConfigValue<Boolean> warnOre;
        public final ConfigValue<Boolean> harvestTip;
        public final ConfigValue<Boolean> toolsPowersTip;
        public final ConfigValue<Boolean> weaponsPowersTip;
        public final ConfigValue<Boolean> armorsPowersTip;
        public final ForgeConfigSpec.BooleanValue tinProgTip;
        public final ForgeConfigSpec.BooleanValue platProgTip;

        private Client(ForgeConfigSpec.Builder builder) {
            builder.push("gameplay");

            warnOre = builder
                    .comment("Display the text 'You can't harvest this block yet!'")
                    .define("warn_ore", true);

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
