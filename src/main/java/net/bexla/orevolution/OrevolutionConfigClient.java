package net.bexla.orevolution;

import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.config.ModConfigEvent;

// An example config class. This is not required, but it's a good idea to have one to keep your config organized.
// Demonstrates how to use Forge's config APIs
@Mod.EventBusSubscriber(modid = Orevolution.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class OrevolutionConfigClient
{
    private static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();

    private static final ForgeConfigSpec.BooleanValue WARN_ORE = BUILDER
            .comment("Display the text 'You can't harvest this block yet!'")
            .define("Display can't harvest text", true);

    private static final ForgeConfigSpec.BooleanValue HARVEST_TIER_TIP = BUILDER
            .comment("Display the 'Harvest tier' tooltip on all tiered tools")
            .define("Display harvest tier tip", true);

    private static final ForgeConfigSpec.BooleanValue TOOLS_POWERS_TIP = BUILDER
            .comment("Display the 'power' tooltip on all tools that have one")
            .define("Display power of tools tip", true);

    private static final ForgeConfigSpec.BooleanValue WEAPONS_POWERS_TIP = BUILDER
            .comment("Display the 'power' tooltip on all weapons that have one")
            .define("Display power of weapons tip", true);

    private static final ForgeConfigSpec.BooleanValue ARMORS_POWERS_TIP = BUILDER
            .comment("Display the 'power' tooltip on all armors that have one")
            .define("Display power of armors tip", true);

    private static final ForgeConfigSpec.BooleanValue TIN_PROGRESS_TIP = BUILDER
            .comment("Display the 'Tin tier' tooltip on all stone-tiered tools")
            .define("Display tin tier tip", true);

    private static final ForgeConfigSpec.BooleanValue PLATINUM_PROGRESS_TIP = BUILDER
            .comment("Display the 'Platinum tier' tooltip on all iron-tiered tools")
            .define("Display platinum tier tip", true);


    static final ForgeConfigSpec SPEC = BUILDER.build();

    public static boolean warn;
    public static boolean harvestTip;
    public static boolean toolsPowersTip;
    public static boolean weaponsPowersTip;
    public static boolean armorsPowersTip;
    public static boolean tinProgTip;
    public static boolean platProgTip;

    @SubscribeEvent
    static void onLoad(final ModConfigEvent event)
    {
        if (event.getConfig().getSpec() != SPEC) return;

        warn = WARN_ORE.get();
        harvestTip = HARVEST_TIER_TIP.get();
        toolsPowersTip = TOOLS_POWERS_TIP.get();
        weaponsPowersTip = WEAPONS_POWERS_TIP.get();
        armorsPowersTip = ARMORS_POWERS_TIP.get();
        tinProgTip = TIN_PROGRESS_TIP.get();
        platProgTip = PLATINUM_PROGRESS_TIP.get();
    }
}
