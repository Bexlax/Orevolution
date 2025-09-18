package net.bexla.orevolution;

import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.config.ModConfigEvent;

@Mod.EventBusSubscriber(modid = Orevolution.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class OrevolutionConfig
{
    private static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();

    private static final ForgeConfigSpec.BooleanValue TOOLS_POWERS = BUILDER
            .comment("Defines if tools will have their special characteristic (aka power), like tin's drop duplication")
            .define("Tool Powers", true);

    private static final ForgeConfigSpec.BooleanValue WEAPONS_POWERS = BUILDER
            .comment("Defines if weapons will have their special characteristic (aka power), like tin's drop duplication")
            .define("Weapons Powers", true);

    private static final ForgeConfigSpec.BooleanValue ARMORS_POWERS = BUILDER
            .comment("Defines if armors will have their special characteristic (aka power), like platinum's strength II effect")
            .define("Armors Powers", true);

    private static final ForgeConfigSpec.BooleanValue TIN_PROGRESS = BUILDER
            .comment("Progress changes from [ Stone -> Iron ] to [ Stone -> Tin ->  Iron ]. Can be further adjusted using datapacks")
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
        weaponsPowers = WEAPONS_POWERS.get();
        armorsPowers = ARMORS_POWERS.get();
        platinumProg = PLATINUM_PROGRESS.get();
        tinProg = TIN_PROGRESS.get();
    }
}
