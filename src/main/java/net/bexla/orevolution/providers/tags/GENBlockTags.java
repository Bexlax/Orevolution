package net.bexla.orevolution.providers.tags;

import net.bexla.orevolution.Orevolution;
import net.bexla.orevolution.content.data.utility.OrevolutionTags;
import net.bexla.orevolution.init.RegBlocks;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.IntrinsicHolderTagsProvider;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.function.Supplier;

public class GENBlockTags extends IntrinsicHolderTagsProvider<Block> {

    public GENBlockTags(PackOutput output, CompletableFuture<HolderLookup.Provider> future, @Nullable ExistingFileHelper helper) {
        super(output, Registries.BLOCK, future, block -> block.builtInRegistryHolder().key(), Orevolution.MODID, helper);
    }

    private void tag(TagKey<Block> key, Map<DyeColor, ? extends Supplier<? extends Block>> values) {
        var tag = tag(key);
        values.entrySet().stream()
                .sorted(Map.Entry.comparingByKey())
                .map(it -> it.getValue().get())
                .map(BuiltInRegistries.BLOCK::getKey)
                .forEach(tag::addOptional);
    }

    @Override
    public @NotNull String getName() {
        return "Orevolution Block Tags";
    }

    @Override
    protected void addTags(HolderLookup.@NotNull Provider provider) {
        tag(OrevolutionTags.Blocks.TinStorages).add(RegBlocks.TIN_BLOCK.get());
        tag(OrevolutionTags.Blocks.PlatStorages).add(RegBlocks.PLATINUM_BLOCK.get());
        tag(OrevolutionTags.Blocks.TungsStorages).add(RegBlocks.TUNGSTEN_BLOCK.get());
        tag(OrevolutionTags.Blocks.EnderiteStorages).add(RegBlocks.AETHERSTEEL_BLOCK.get());
        tag(OrevolutionTags.Blocks.LivingstoneStorages).add(RegBlocks.LIVINGSTONE_BLOCK.get());
        tag(OrevolutionTags.Blocks.VerditeStorages).add(RegBlocks.VERDITE_BLOCK.get());

        tag(OrevolutionTags.Blocks.RawTinStorages).add(RegBlocks.RAW_TIN_BLOCK.get());
        tag(OrevolutionTags.Blocks.RawPlatStorages).add(RegBlocks.RAW_PLATINUM_BLOCK.get());
        tag(OrevolutionTags.Blocks.RawTungsStorages).add(RegBlocks.RAW_TUNGSTEN_BLOCK.get());

        tag(OrevolutionTags.Blocks.TinTiered).add(Blocks.IRON_BLOCK).add(Blocks.IRON_ORE).add(Blocks.DEEPSLATE_IRON_ORE).add(Blocks.RAW_IRON_BLOCK)
                .add(Blocks.IRON_BARS).add(Blocks.IRON_DOOR).add(Blocks.IRON_TRAPDOOR).add(Blocks.HOPPER)
                .addTag(BlockTags.ANVIL);

        tag(OrevolutionTags.Blocks.PlatTiered).add(Blocks.DIAMOND_BLOCK).add(Blocks.DIAMOND_ORE).add(Blocks.DEEPSLATE_DIAMOND_ORE)
                .add(Blocks.REDSTONE_BLOCK).add(Blocks.REDSTONE_ORE).add(Blocks.DEEPSLATE_REDSTONE_ORE);
    }
}
