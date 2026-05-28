package net.bexla.orevolution.events;

import net.bexla.orevolution.Orevolution;
import net.bexla.orevolution.content.types.menu.SteelAnvilScreen;
import net.bexla.orevolution.init.RegItems;
import net.bexla.orevolution.init.RegMenus;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.client.renderer.item.ItemProperties;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

@Mod.EventBusSubscriber(modid = Orevolution.MODID, value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ClientSubscriber {
    @SubscribeEvent
    public static void setup(FMLClientSetupEvent event) {
        ClientSubscriber.registerItemProperties();

        event.enqueueWork(() -> {
            MenuScreens.register(
                    RegMenus.STEEL_ANVIL.get(),
                    SteelAnvilScreen::new
            );
        });
    }

    private static void registerItemProperties() {
        ItemProperties.register(RegItems.TIN_SHIELD.get(), new ResourceLocation("blocking"), (stack, level, user, i) ->
                user != null && user.isUsingItem() && user.getUseItem() == stack ? 1.0F : 0.0F
        );

        ItemProperties.register(RegItems.PLATINUM_SHIELD.get(), new ResourceLocation("blocking"), (stack, level, user, i) ->
                user != null && user.isUsingItem() && user.getUseItem() == stack ? 1.0F : 0.0F
        );

        ItemProperties.register(RegItems.AETHERSTEEL_SHIELD.get(), new ResourceLocation("blocking"), (stack, level, user, i) ->
                user != null && user.isUsingItem() && user.getUseItem() == stack ? 1.0F : 0.0F
        );

        ItemProperties.register(RegItems.LIVINGSTONE_SHIELD.get(), new ResourceLocation("blocking"), (stack, level, user, i) ->
                user != null && user.isUsingItem() && user.getUseItem() == stack ? 1.0F : 0.0F
        );

        ItemProperties.register(RegItems.VERDITE_SHIELD.get(), new ResourceLocation("blocking"), (stack, level, user, i) ->
                user != null && user.isUsingItem() && user.getUseItem() == stack ? 1.0F : 0.0F
        );
    }
}
