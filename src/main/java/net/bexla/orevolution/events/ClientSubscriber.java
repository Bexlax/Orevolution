package net.bexla.orevolution.events;

import net.bexla.orevolution.Orevolution;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

@Mod.EventBusSubscriber(modid = Orevolution.MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ClientSubscriber {
    @SubscribeEvent
    public static void onClientSetup(FMLClientSetupEvent event)
    {

    }
}
