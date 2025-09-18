package net.bexla.orevolution.providers.tags;

import net.bexla.orevolution.Orevolution;
import net.bexla.orevolution.content.data.utility.OrevolutionLists;
import net.bexla.orevolution.content.data.utility.OrevolutionTags;
import net.bexla.orevolution.init.RegItems;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.RegistryObject;
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

        OrevolutionLists.TOOLS.forEach((tag, items) -> {
            for (RegistryObject<Item> item : items) {
                tag(tag).add(item.get());
            }
        });
    }
}
