package net.bexla.orevolution.init;

import net.bexla.orevolution.Orevolution;
import net.bexla.orevolution.content.types.effects.CrushEffect;
import net.bexla.orevolution.content.types.effects.DebuffEffect;
import net.bexla.orevolution.content.types.effects.PetrifiedEffect;
import net.minecraft.world.effect.MobEffect;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class RegMobEffects {
    public static final DeferredRegister<MobEffect> EFFECTS = DeferredRegister.create(ForgeRegistries.MOB_EFFECTS, Orevolution.MODID);

    public static final RegistryObject<MobEffect> PETRIFIED = EFFECTS.register("petrified",
            PetrifiedEffect::new
    );

    public static final RegistryObject<MobEffect> CRUSHED = EFFECTS.register("crushed",
            CrushEffect::new
    );

    public static final RegistryObject<MobEffect> WEAK_SOUL = EFFECTS.register("weak_soul",
            DebuffEffect::new
    );
}
