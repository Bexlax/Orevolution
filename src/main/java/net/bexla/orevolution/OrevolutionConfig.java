package net.bexla.orevolution;

import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.config.ModConfigEvent;

// An example config class. This is not required, but it's a good idea to have one to keep your config organized.
// Demonstrates how to use Forge's config APIs
@Mod.EventBusSubscriber(modid = Orevolution.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class OrevolutionConfig
{
    private static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();

    private static final ForgeConfigSpec.BooleanValue TOOLS_POWERS = BUILDER
            .comment("Defines if modded tools will have their special characteristic (aka power), like tin's drop duplication")
            .define("Modded Tool Powers", true);

    private static final ForgeConfigSpec.BooleanValue ARMORS_POWERS = BUILDER
            .comment("Defines if modded armors will have their special characteristic (aka power), like platinum's strength II effect")
            .define("Modded Armors Powers", true);

    private static final ForgeConfigSpec.BooleanValue TIN_PROGRESS = BUILDER
            .comment("Progress changes from [ Stone -> Iron ] to [ Stone -> Tin ->  Iron ]. Can be further adjusted via 'orevolution:tools/tin_progress_followers' tag")
            .define("Tin progression", true);

    private static final ForgeConfigSpec.BooleanValue PLATINUM_PROGRESS = BUILDER
            .comment("Progress changes from [ Iron -> Diamond ] to [ Iron -> Platinum ->  Diamond ]. Can be further adjusted via 'orevolution:tools/platinum_progress_followers' tag")
            .define("Platinum progression", true);

    static final ForgeConfigSpec SPEC = BUILDER.build();

    public static boolean toolsPowers;
    public static boolean weaponsPowers;
    public static boolean armorsPowers;
    public static boolean platinumProg;
    public static boolean tinProg;

    @SubscribeEvent
    static void onLoad(final ModConfigEvent event)
    {
        if (event.getConfig().getSpec() != SPEC) return;

        toolsPowers = TOOLS_POWERS.get();
        armorsPowers = ARMORS_POWERS.get();
        platinumProg = PLATINUM_PROGRESS.get();
        tinProg = TIN_PROGRESS.get();
    }
}
