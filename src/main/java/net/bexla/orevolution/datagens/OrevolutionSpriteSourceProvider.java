package net.bexla.orevolution.datagens;

import com.teamabnormals.blueprint.core.api.BlueprintTrims;
import net.bexla.orevolution.Orevolution;
import net.bexla.orevolution.init.RegTrimMaterials;
import net.minecraft.data.PackOutput;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.common.data.SpriteSourceProvider;

public class OrevolutionSpriteSourceProvider extends SpriteSourceProvider {
    public OrevolutionSpriteSourceProvider(PackOutput output, ExistingFileHelper helper) {
        super(output, helper, Orevolution.MODID);
    }

    @Override
    protected void addSources() {
        this.atlas(BlueprintTrims.ARMOR_TRIMS_ATLAS)
                .addSource(BlueprintTrims.materialPatternPermutations(
                        RegTrimMaterials.TIN,
                        RegTrimMaterials.PLATINUM,
                        RegTrimMaterials.TUNGSTEN
                ));
    }
}
