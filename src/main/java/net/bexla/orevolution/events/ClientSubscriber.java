package net.bexla.orevolution.events;

import net.bexla.orevolution.Orevolution;
import net.bexla.orevolution.init.RegItems;
import net.minecraft.client.renderer.item.ItemProperties;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.registries.RegistryObject;

import java.util.List;

@Mod.EventBusSubscriber(modid = Orevolution.MODID, value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ClientSubscriber {
    @SubscribeEvent
    public static void setup(FMLClientSetupEvent event) {
        ClientSubscriber.registerItemProperties();
    }

    private static void registerItemProperties() {
        List<RegistryObject<Item>> shieldItems = List.of(RegItems.TIN_SHIELD, RegItems.PLATINUM_SHIELD, RegItems.AETHERSTEEL_SHIELD,
                RegItems.LIVINGSTONE_SHIELD, RegItems.VERDITE_SHIELD);
        
        for(RegistryObject<Item> item : shieldItems) {
            ItemProperties.register(item.get(), new ResourceLocation("blocking"), (stack, level, user, i) ->
                    user != null && user.isUsingItem() && user.getUseItem() == stack ? 1.0F : 0.0F
            );
        }
    }
}
