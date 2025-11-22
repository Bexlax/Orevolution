package net.bexla.orevolution.compatibility.farmersdelight;

import net.bexla.orevolution.content.data.OrevolutionToolTiers;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import vectorwing.farmersdelight.common.item.KnifeItem;

import static net.bexla.orevolution.content.data.utility.OrevolutionUtils.Rarities.Aether;

public class RegItemsFD {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, "orevolution");

    public static final RegistryObject<Item> TIN_KNIFE = ITEMS.register("tin_knife", () -> new KnifeItem(OrevolutionToolTiers.TIN, 0.5F, -2.0F, new Item.Properties()));
    public static final RegistryObject<Item> PLATINUM_KNIFE = ITEMS.register("platinum_knife", () -> new KnifeItem(OrevolutionToolTiers.PLATINUM, 0.5F, -2.0F, new Item.Properties()));
    public static final RegistryObject<Item> AETHERSTEEL_KNIFE = ITEMS.register("aethersteel_knife", () -> new KnifeItem(OrevolutionToolTiers.AETHERSTEEL, 0.5F, -2.0F, new Item.Properties().fireResistant().rarity(Aether)));
    public static final RegistryObject<Item> LIVINGSTONE_KNIFE = ITEMS.register("livingstone_knife", () -> new KnifeItem(OrevolutionToolTiers.LIVINGSTONE, 0.5F, -2.0F, new Item.Properties()));
    public static final RegistryObject<Item> VERDITE_KNIFE = ITEMS.register("verdite_knife", () -> new KnifeItem(OrevolutionToolTiers.VERDITE, 0.5F, -2.0F, new Item.Properties()));

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);;
    }
}
