package net.bexla.orevolution.init;

import com.teamabnormals.blueprint.core.util.registry.BlockSubRegistryHelper;
import net.bexla.orevolution.Orevolution;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.DropExperienceBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.RegistryObject;

import java.util.Objects;
import java.util.function.Function;
import java.util.function.Supplier;


@Mod.EventBusSubscriber(modid = Orevolution.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class RegBlocks {
    public static final BlockSubRegistryHelper HELPER = Orevolution.REGISTRY_HELPER.getBlockSubHelper();

    public static <T extends Block> RegistryObject<T> baseRegister(String name, Supplier<? extends T> block, Function<RegistryObject<T>, Supplier<? extends Item>> item) {
        RegistryObject<T> register = HELPER.createBlockNoItem(name, block);
        RegItems.HELPER.createItem(name, item.apply(register));
        return register;
    }

    public static <B extends Block> RegistryObject<B> register(String name, Supplier<? extends Block> block) {
        return (RegistryObject<B>) baseRegister(name, block, RegBlocks::registerBlockItem);
    }

    private static <T extends Block> Supplier<BlockItem> registerBlockItem(final RegistryObject<T> block) {
        return () -> {
            return new BlockItem(Objects.requireNonNull(block.get()), new Item.Properties());
        };
    }


    // todo: Finish adding remaining blocks
    //~//~~ Ores ~~//~//
    public static final RegistryObject<Block> TIN_ORE = register("tin_ore", () -> new DropExperienceBlock(BlockBehaviour.Properties.copy(Blocks.COPPER_ORE)));
    public static final RegistryObject<Block> PLATINUM_ORE = register("platinum_ore", () -> new DropExperienceBlock(BlockBehaviour.Properties.copy(Blocks.IRON_ORE)));
    public static final RegistryObject<Block> NETHER_TUNGSTEN_ORE = register("tungsten_ore", () -> new DropExperienceBlock(BlockBehaviour.Properties.copy(Blocks.NETHER_QUARTZ_ORE)));
    public static final RegistryObject<Block> NETHER_XP_ORE = register("nether_experience_ore", () -> new DropExperienceBlock(BlockBehaviour.Properties.copy(Blocks.NETHER_QUARTZ_ORE), UniformInt.of(6, 9)));
    public static final RegistryObject<Block> END_XP_ORE = register("end_experience_ore", () -> new DropExperienceBlock(BlockBehaviour.Properties.copy(Blocks.NETHER_QUARTZ_ORE).sound(SoundType.STONE).strength(5.0F, 4.0F), UniformInt.of(12, 22)));
    public static final RegistryObject<Block> DEEPSLATE_TIN_ORE = register("deepslate_tin_ore", () -> new DropExperienceBlock(BlockBehaviour.Properties.copy(Blocks.DEEPSLATE_COPPER_ORE)));
    public static final RegistryObject<Block> DEEPSLATE_PLATINUM_ORE = register("deepslate_platinum_ore", () -> new DropExperienceBlock(BlockBehaviour.Properties.copy(Blocks.DEEPSLATE_IRON_ORE)));
    public static final RegistryObject<Block> PRIMITIVE_AETHERROCK = register("primitive_aetherrock", () -> new DropExperienceBlock(BlockBehaviour.Properties.copy(Blocks.NETHER_QUARTZ_ORE).sound(SoundType.ANCIENT_DEBRIS).strength(5.0F, 4.0F), UniformInt.of(12, 22)));


    //~//~~ Storage Blocks ~~//~//
        /* Ore Blocks */
    public static final RegistryObject<Block> TIN_BLOCK = register("tin_block", () -> new Block(BlockBehaviour.Properties.copy(Blocks.COPPER_BLOCK)));
    public static final RegistryObject<Block> PLATINUM_BLOCK = register("platinum_block", () -> new Block(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK).strength(4.5F)));
    public static final RegistryObject<Block> TUNGSTEN_BLOCK = register("tungsten_block", () -> new Block(BlockBehaviour.Properties.copy(Blocks.DIAMOND_BLOCK)));
    public static final RegistryObject<Block> REINFORCED_NETHERITE_BLOCK = register("reinforced_netherite_block", () -> new Block(BlockBehaviour.Properties.copy(Blocks.NETHERITE_BLOCK).strength(105F, 60F)));
    public static final RegistryObject<Block> AETHERSTEEL_BLOCK = register("aethersteel_block", () -> new Block(BlockBehaviour.Properties.copy(Blocks.NETHERITE_BLOCK).strength(135F, 60F)));
    public static final RegistryObject<Block> BRONZE_BLOCK = register("bronze_block", () -> new Block(BlockBehaviour.Properties.copy(Blocks.COPPER_BLOCK)));
    public static final RegistryObject<Block> TUMBAGA_BLOCK = register("tumbaga_block", () -> new Block(BlockBehaviour.Properties.copy(Blocks.GOLD_BLOCK)));
    public static final RegistryObject<Block> STEEL_BLOCK = register("steel_block", () -> new Block(BlockBehaviour.Properties.copy(Blocks.DIAMOND_BLOCK)));
    public static final RegistryObject<Block> LIVINGSTONE_BLOCK = register("livingstone_block", () -> new Block(BlockBehaviour.Properties.copy(Blocks.COPPER_BLOCK)));
    public static final RegistryObject<Block> VERDITE_BLOCK = register("verdite_block", () -> new Block(BlockBehaviour.Properties.copy(Blocks.COPPER_BLOCK)));
        /* Raw Ore Blocks */
    public static final RegistryObject<Block> RAW_TIN_BLOCK = register("raw_tin_block", () -> new Block(BlockBehaviour.Properties.copy(Blocks.COPPER_BLOCK)));
    public static final RegistryObject<Block> RAW_PLATINUM_BLOCK = register("raw_platinum_block", () -> new Block(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK).strength(4.5F)));
    public static final RegistryObject<Block> RAW_TUNGSTEN_BLOCK = register("raw_tungsten_block", () -> new Block(BlockBehaviour.Properties.copy(Blocks.DIAMOND_BLOCK)));


    //~//~~ Decorative Blocks ~~//~//
    public static final RegistryObject<Block> AETHERROCK = register("aetherrock", () -> new Block(BlockBehaviour.Properties.copy(Blocks.END_STONE).strength(25F, 100F)));

    public static final RegistryObject<Block> POLISHED_AETHERROCK = register("polished_aetherrock", () -> new Block(BlockBehaviour.Properties.copy(AETHERROCK.get())));
    public static final RegistryObject<Block> POLISHED_TUNGSTEN = register("polished_tungsten", () -> new Block(BlockBehaviour.Properties.copy(TUNGSTEN_BLOCK.get())));

    public static final RegistryObject<Block> AETHERROCK_BRICKS = register("aetherrock_bricks", () -> new Block(BlockBehaviour.Properties.copy(AETHERROCK.get())));

    public static final RegistryObject<Block> AETHERROCK_TILES = register("aetherrock_tiles", () -> new Block(BlockBehaviour.Properties.copy(AETHERROCK.get())));
    public static final RegistryObject<Block> BRONZE_TILES = register("bronze_tiles", () -> new Block(BlockBehaviour.Properties.copy(BRONZE_BLOCK.get())));

    public static final RegistryObject<Block> CUT_TUNGSTEN_BLOCK = register("cut_tungsten_block", () -> new Block(BlockBehaviour.Properties.copy(TUNGSTEN_BLOCK.get())));

        /* Stairs */
        /* Slabs */
        /* Doors */
        /* Bars */
        /* Trapdoors */
}
