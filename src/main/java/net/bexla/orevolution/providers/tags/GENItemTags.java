package net.bexla.orevolution.providers.tags;

import net.bexla.orevolution.Orevolution;
import net.bexla.orevolution.content.data.utility.OrevolutionTags;
import net.bexla.orevolution.init.RegItems;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class GENItemTags extends ItemTagsProvider {

    public GENItemTags(PackOutput output, CompletableFuture<HolderLookup.Provider> future, CompletableFuture<TagLookup<Block>> provider, @Nullable ExistingFileHelper helper) {
        super(output, future, provider, Orevolution.MODID, helper);
    }

    @Override
    public @NotNull String getName() {
        return "Orevolution Item Tags";
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
        tag(OrevolutionTags.Items.TinIngots).add(RegItems.TIN_INGOT.get());
        tag(OrevolutionTags.Items.PlatIngots).add(RegItems.PLATINUM_INGOT.get());
        tag(OrevolutionTags.Items.TungsIngots).add(RegItems.TUNGSTEN_INGOT.get());
        tag(OrevolutionTags.Items.EnderiteIngots).add(RegItems.AETHERSTEEL_INGOT.get());
        tag(OrevolutionTags.Items.VerditeIngots).add(RegItems.VERDITE_INGOT.get());

        tag(OrevolutionTags.Items.TinNuggets).add(RegItems.TIN_NUGGET.get());
        tag(OrevolutionTags.Items.PlatNuggets).add(RegItems.PLATINUM_NUGGET.get());
        tag(OrevolutionTags.Items.TungsNuggets).add(RegItems.TUNGSTEN_NUGGET.get());
        tag(OrevolutionTags.Items.VerditeNuggets).add(RegItems.VERDITE_NUGGET.get());

        tag(OrevolutionTags.Items.TinRaws).add(RegItems.RAW_TIN.get());
        tag(OrevolutionTags.Items.PlatRaws).add(RegItems.RAW_PLATINUM.get());
        tag(OrevolutionTags.Items.TungsRaws).add(RegItems.RAW_TUNGSTEN.get());

        tag(Tags.Items.INGOTS).addTags(
                OrevolutionTags.Items.TinIngots,
                OrevolutionTags.Items.PlatIngots,
                OrevolutionTags.Items.TungsIngots,
                OrevolutionTags.Items.EnderiteIngots,
                OrevolutionTags.Items.VerditeIngots
        );
        tag(Tags.Items.NUGGETS).addTags(
                OrevolutionTags.Items.TinNuggets,
                OrevolutionTags.Items.PlatNuggets,
                OrevolutionTags.Items.TungsNuggets,
                OrevolutionTags.Items.VerditeNuggets
        );
        tag(Tags.Items.RAW_MATERIALS).addTags(
                OrevolutionTags.Items.TinRaws,
                OrevolutionTags.Items.PlatRaws,
                OrevolutionTags.Items.TungsRaws
        );

        copy(OrevolutionTags.Blocks.TinStorages, OrevolutionTags.Items.TinStorages);
        copy(OrevolutionTags.Blocks.PlatStorages, OrevolutionTags.Items.PlatStorages);
        copy(OrevolutionTags.Blocks.TungsStorages, OrevolutionTags.Items.TungsStorages);
        copy(OrevolutionTags.Blocks.VerditeStorages, OrevolutionTags.Items.VerditeStorages);
        copy(OrevolutionTags.Blocks.EnderiteStorages, OrevolutionTags.Items.EnderiteStorages);
        copy(OrevolutionTags.Blocks.LivingstoneStorages, OrevolutionTags.Items.LivingstoneStorages);

        copy(OrevolutionTags.Blocks.PlatOres, OrevolutionTags.Items.PlatOres);
        copy(OrevolutionTags.Blocks.TinOres, OrevolutionTags.Items.TinOres);
        copy(OrevolutionTags.Blocks.TungsOres, OrevolutionTags.Items.TungsOres);
        copy(OrevolutionTags.Blocks.XPOres, OrevolutionTags.Items.XPOres);

        tag(Tags.Items.STORAGE_BLOCKS).addTags(
                OrevolutionTags.Items.TinStorages,
                OrevolutionTags.Items.PlatStorages,
                OrevolutionTags.Items.TungsStorages,
                OrevolutionTags.Items.VerditeStorages,
                OrevolutionTags.Items.EnderiteStorages,
                OrevolutionTags.Items.LivingstoneStorages
        );
    }
}
