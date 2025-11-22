package net.bexla.orevolution.compatibility.spelunkery;

import net.bexla.orevolution.init.RegBlocks;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.Objects;
import java.util.function.Function;
import java.util.function.Supplier;

public class RegBlocksSK {
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, "orevolution");

    public static <T extends Block> RegistryObject<T> baseRegister(String name, Supplier<? extends T> block, Function<RegistryObject<T>, Supplier<? extends Item>> item) {
        RegistryObject<T> register = BLOCKS.register(name, block);
        RegItemsSK.ITEMS.register(name, item.apply(register));
        return register;
    }

    public static <B extends Block> RegistryObject<B> register(String name, Supplier<? extends Block> block) {
        return (RegistryObject<B>) baseRegister(name, block, RegBlocksSK::registerBlockItem);
    }

    private static <T extends Block> Supplier<BlockItem> registerBlockItem(final RegistryObject<T> block) {
        return () -> new BlockItem(Objects.requireNonNull(block.get()), new Item.Properties());
    }

    public static final RegistryObject<Block> TIN_ORE_ANDESITE = register("tin_ore_andesite", () -> new Block(Block.Properties.copy(RegBlocks.TIN_ORE.get()).lootFrom(RegBlocks.TIN_ORE)));
    public static final RegistryObject<Block> TIN_ORE_GRANITE = register("tin_ore_granite", () -> new Block(Block.Properties.copy(RegBlocks.TIN_ORE.get()).lootFrom(RegBlocks.TIN_ORE)));
    public static final RegistryObject<Block> TIN_ORE_DIORITE = register("tin_ore_diorite", () -> new Block(Block.Properties.copy(RegBlocks.TIN_ORE.get()).lootFrom(RegBlocks.TIN_ORE)));
    public static final RegistryObject<Block> TIN_ORE_TUFF = register("tin_ore_tuff", () -> new Block(Block.Properties.copy(RegBlocks.TIN_ORE.get()).lootFrom(RegBlocks.TIN_ORE)));

    public static final RegistryObject<Block> NETHER_TUNGSTEN_ORE_BLACKSTONE = register("nether_tungsten_ore_blackstone", () -> new Block(Block.Properties.copy(RegBlocks.NETHER_TUNGSTEN_ORE.get()).lootFrom(RegBlocks.NETHER_TUNGSTEN_ORE)));

    public static final RegistryObject<Block> PLATINUM_ORE_ANDESITE = register("platinum_ore_andesite", () -> new Block(Block.Properties.copy(RegBlocks.PLATINUM_ORE.get()).lootFrom(RegBlocks.PLATINUM_ORE)));
    public static final RegistryObject<Block> PLATINUM_ORE_GRANITE = register("platinum_ore_granite", () -> new Block(Block.Properties.copy(RegBlocks.PLATINUM_ORE.get()).lootFrom(RegBlocks.PLATINUM_ORE)));
    public static final RegistryObject<Block> PLATINUM_ORE_DIORITE = register("platinum_ore_diorite", () -> new Block(Block.Properties.copy(RegBlocks.PLATINUM_ORE.get()).lootFrom(RegBlocks.PLATINUM_ORE)));
    public static final RegistryObject<Block> PLATINUM_ORE_TUFF = register("platinum_ore_tuff", () -> new Block(Block.Properties.copy(RegBlocks.PLATINUM_ORE.get()).lootFrom(RegBlocks.PLATINUM_ORE)));

    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);;
    }
}
