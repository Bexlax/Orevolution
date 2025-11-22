package net.bexla.orevolution.content.data;

import net.bexla.orevolution.Orevolution;
import net.bexla.orevolution.datagens.GenFeatures;
import net.bexla.orevolution.init.RegTrimMaterials;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.RegistrySetBuilder;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.minecraftforge.common.data.DatapackBuiltinEntriesProvider;

import java.util.Set;
import java.util.concurrent.CompletableFuture;

public class DataRegistries extends DatapackBuiltinEntriesProvider {
    public static final RegistrySetBuilder BUILDER = new RegistrySetBuilder()
            .add(Registries.CONFIGURED_FEATURE, GenFeatures.Configured::bootstrap)
            .add(Registries.PLACED_FEATURE, GenFeatures.Placed::bootstrap)
            .add(Registries.TRIM_MATERIAL, RegTrimMaterials::bootstrap);

    public DataRegistries(PackOutput output, CompletableFuture<HolderLookup.Provider> future) {
        super(output, future, BUILDER, Set.of("minecraft", Orevolution.MODID));
    }
}
