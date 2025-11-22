package net.bexla.orevolution.compatibility.spelunkery;

import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class RegItemsSK {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, "orevolution");

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);;
    }
}
