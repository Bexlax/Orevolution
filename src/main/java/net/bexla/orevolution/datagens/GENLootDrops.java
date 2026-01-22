package net.bexla.orevolution.datagens;

import net.bexla.orevolution.Orevolution;
import net.bexla.orevolution.content.types.providers.BlockLootProvider;
import net.bexla.orevolution.init.RegBlocks;
import net.bexla.orevolution.init.RegItems;
import net.minecraft.advancements.critereon.StatePropertiesPredicate;
import net.minecraft.data.PackOutput;
import net.minecraft.data.loot.LootTableProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.DoorBlock;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.DoubleBlockHalf;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.ValidationContext;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.minecraft.world.level.storage.loot.predicates.LootItemBlockStatePropertyCondition;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public class GENLootDrops extends LootTableProvider {

    public GENLootDrops(PackOutput output) {
        super(output, Set.of(), List.of(
                new SubProviderEntry(BlockLoot::new, LootContextParamSets.BLOCK)
        ));
    }

    @Override
    protected void validate(Map<ResourceLocation, LootTable> map, ValidationContext tracker) {
    }

    public static class BlockLoot extends BlockLootProvider {
        protected void generate() {
            ore(RegBlocks.TIN_ORE, RegItems.RAW_TIN);
            ore(RegBlocks.DEEPSLATE_TIN_ORE, RegItems.RAW_TIN);
            ore(RegBlocks.PLATINUM_ORE, RegItems.RAW_PLATINUM);
            ore(RegBlocks.DEEPSLATE_PLATINUM_ORE, RegItems.RAW_PLATINUM);
            ore(RegBlocks.NETHER_TUNGSTEN_ORE, RegItems.RAW_TUNGSTEN);

            /*
            ore(RegBlocksSK.TIN_ORE_ANDESITE, RegItems.RAW_TIN);
            ore(RegBlocksSK.TIN_ORE_GRANITE, RegItems.RAW_TIN);
            ore(RegBlocksSK.TIN_ORE_DIORITE, RegItems.RAW_TIN);
            ore(RegBlocksSK.TIN_ORE_TUFF, RegItems.RAW_TIN);
            ore(RegBlocksSK.PLATINUM_ORE_ANDESITE, RegItems.RAW_PLATINUM);
            ore(RegBlocksSK.PLATINUM_ORE_GRANITE, RegItems.RAW_PLATINUM);
            ore(RegBlocksSK.PLATINUM_ORE_DIORITE, RegItems.RAW_PLATINUM);
            ore(RegBlocksSK.PLATINUM_ORE_TUFF, RegItems.RAW_PLATINUM);
            */

            dropSelf(RegBlocks.AETHERSTEEL_BLOCK);
            dropSelf(RegBlocks.TIN_BLOCK);
            dropSelf(RegBlocks.TUNGSTEN_BLOCK);
            dropSelf(RegBlocks.LIVINGSTONE_BLOCK);
            dropSelf(RegBlocks.VERDITE_BLOCK);
            dropSelf(RegBlocks.PLATINUM_BLOCK);
            dropSelf(RegBlocks.AETHERROCK);
            dropSelf(RegBlocks.POLISHED_AETHERROCK);
            dropSelf(RegBlocks.POLISHED_TUNGSTEN);
            dropSelf(RegBlocks.CRACKED_AETHERROCK_BRICKS);
            dropSelf(RegBlocks.CUT_TUNGSTEN_BLOCK);
            dropSelf(RegBlocks.BRONZE_BLOCK);
            dropSelf(RegBlocks.STEEL_BLOCK);
            dropSelf(RegBlocks.PRIMITIVE_AETHERROCK);
            dropSelf(RegBlocks.RAW_TIN_BLOCK);
            dropSelf(RegBlocks.RAW_PLATINUM_BLOCK);
            dropSelf(RegBlocks.RAW_TUNGSTEN_BLOCK);
            dropSelf(RegBlocks.AETHERROCK_TILES);
            dropSelf(RegBlocks.AETHERROCK_BRICKS);
            dropSelf(RegBlocks.CUT_STEEL_BLOCK);
            dropSelf(RegBlocks.STEEL_PILLAR);
            dropSelf(RegBlocks.BRONZE_TILES);
            add(RegBlocks.STEEL_DOOR.get(), LootTable.lootTable()
                    .withPool(applyExplosionCondition(RegBlocks.STEEL_DOOR.get(), LootPool.lootPool()
                            .setRolls(ConstantValue.exactly(1.0F))
                            .when(LootItemBlockStatePropertyCondition.hasBlockStateProperties(RegBlocks.STEEL_DOOR.get())
                                    .setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(DoorBlock.HALF, DoubleBlockHalf.LOWER)))
                            .add(LootItem.lootTableItem(RegBlocks.STEEL_DOOR.get())))));
            dropSelf(RegBlocks.STEEL_TRAPDOOR);
            dropSelf(RegBlocks.POLISHED_AETHERROCK_STAIR);
            dropSelf(RegBlocks.POLISHED_AETHERROCK_WALL);
            slab(RegBlocks.POLISHED_AETHERROCK_SLAB);
            dropSelf(RegBlocks.PLATINUM_TILES);
            dropSelf(RegBlocks.GOLD_TILES);
            dropSelf(RegBlocks.PLATINUM_BARS);
            dropSelf(RegBlocks.GOLD_BARS);
            dropSelf(RegBlocks.TUNGSTEN_BARS);
            dropSelf(RegBlocks.STEEL_BARS);
            dropSelf(RegBlocks.TIN_BARS);
            dropSelf(RegBlocks.BRONZE_BARS);
            dropSelf(RegBlocks.TUNGSTEN_BRICKS);
            dropSelf(RegBlocks.GOLD_PILLAR);
            dropSelf(RegBlocks.PLATINUM_PILLAR);
            dropSelf(RegBlocks.CHISELED_TUNGSTEN_BLOCK);
            dropSelf(RegBlocks.CHISELED_TUNGSTEN_BRICKS);
            dropSelf(RegBlocks.TIN_TILES);
            dropSelf(RegBlocks.TIN_BRICKS);
            add(RegBlocks.LIVINGSTONE_CROP.get(), createCropDrops(RegBlocks.LIVINGSTONE_CROP.get(), RegItems.LIVINGSTONE_SHARD.get(), RegItems.PETRIFIED_SEED.get(), LootItemBlockStatePropertyCondition.hasBlockStateProperties(RegBlocks.LIVINGSTONE_CROP.get()).setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(BlockStateProperties.AGE_4, 4))));
            add(RegBlocks.VERDITE_CROP.get(), createCropDrops(RegBlocks.VERDITE_CROP.get(), RegItems.VERDITE_NUGGET.get(), RegItems.DEAD_SEED.get(), LootItemBlockStatePropertyCondition.hasBlockStateProperties(RegBlocks.VERDITE_CROP.get()).setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(AGE_6, 6))));
            dropSelf(RegBlocks.TUNGSTEN_SPONGE);
            dropSelf(RegBlocks.HOT_TUNGSTEN_SPONGE);
        }

        private static final IntegerProperty AGE_6 = IntegerProperty.create("age", 0, 6);

        @Override
        protected @NotNull Iterable<Block> getKnownBlocks() {
            return Orevolution.REGISTRY_HELPER.getBlockSubHelper().getDeferredRegister().getEntries().stream().map(Supplier::get).collect(Collectors.toList());
        }
    }
}