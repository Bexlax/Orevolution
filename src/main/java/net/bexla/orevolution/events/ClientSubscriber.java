package net.bexla.orevolution.events;

import net.bexla.orevolution.Orevolution;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = Orevolution.MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ClientSubscriber {
    public static void setup()
    {
    }
}
