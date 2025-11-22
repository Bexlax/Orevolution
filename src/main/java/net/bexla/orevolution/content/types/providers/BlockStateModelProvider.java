package net.bexla.orevolution.content.types.providers;

import com.teamabnormals.blueprint.core.data.client.BlueprintBlockStateProvider;
import net.bexla.orevolution.Orevolution;
import net.bexla.orevolution.content.types.block.OreCropBlock;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraftforge.client.model.generators.BlockModelBuilder;
import net.minecraftforge.client.model.generators.ConfiguredModel;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.function.Supplier;

import static net.minecraftforge.client.model.generators.ModelProvider.BLOCK_FOLDER;

// Credits to Oreganized (Team Galena)
public abstract class BlockStateModelProvider extends BlueprintBlockStateProvider {

    public BlockStateModelProvider(PackOutput output, ExistingFileHelper help) {
        super(output, Orevolution.MODID, help);
    }

    protected ResourceLocation texture(String name) {
        return modLoc(BLOCK_FOLDER + "/" + name);
    }

    protected String name(Supplier<? extends Block> block) {
        return name(block.get());
    }

    public void simpleBlock(Supplier<? extends Block> block, String subfolder) {
        this.simpleBlock(block.get(), this.cubeAll(block.get(), subfolder));
    }

    public ModelFile cubeAll(Block block, String subfolder) {
        return this.models().cubeAll(name(block), this.blockTexture(block, subfolder));
    }

    public void doorBlock(Supplier<? extends Block> door) {
        Block block = door.get();

        if (block instanceof DoorBlock doorBlock) {
            this.doorBlock(doorBlock, suffix(this.blockTexture(block, "decorative/door"), "_bottom"), suffix(this.blockTexture(block, "decorative/door"), "_top"));
            this.generatedItem(block, "item/blockitem");
        }
    }

    public void trapdoorBlock(Supplier<? extends Block> trapdoor) {
        Block block = trapdoor.get();

        if (block instanceof TrapDoorBlock trapDoorBlock) {
            this.trapdoorBlock(trapDoorBlock, this.blockTexture(block, "decorative/trapdoor"), true);
        }
    }


    public ResourceLocation blockTexture(Block block, String subfolder) {
        ResourceLocation name = this.key(block);
        return new ResourceLocation(name.getNamespace(), "block/" + subfolder + "/" + name.getPath());
    }

    private ResourceLocation key(Block block) {
        return ForgeRegistries.BLOCKS.getKey(block);
    }

    public void stairsBlock(Supplier<? extends Block> block, Supplier<? extends Block> fullBlock) {
        if(block.get() instanceof StairBlock block1) {
            stairsBlock(block1, blockTexture(fullBlock.get(), "decorative"));
        }
    }

    public void slabBlock(Supplier<? extends Block> block, Supplier<? extends Block> fullBlock) {
        if(block.get() instanceof SlabBlock block1) {
            slabBlock(block1, texture(name(fullBlock)), blockTexture(fullBlock.get(), "decorative"));
        }
    }

    public void wallBlock(Supplier<? extends Block> wall, Supplier<? extends Block> fullBlock) {
        if(wall.get() instanceof WallBlock block) {
            wallBlock(block, blockTexture(fullBlock.get(), "decorative"));
        }
    }

    public ModelFile cubeBottomTop(Supplier<? extends Block> block, String subfolder) {
        BlockModelBuilder model = models().getBuilder(name(block));
        model.parent(models().getExistingFile(new ResourceLocation("minecraft", "block" + "/cube_bottom_top")));
        model.texture("top", suffix(this.blockTexture(block.get(), subfolder), "_top"));
        model.texture("bottom", suffix(this.blockTexture(block.get(), subfolder), "_top"));
        model.texture("side", suffix(this.blockTexture(block.get(), subfolder), "_side"));
        return model;
    }

    public void makeCrop(OreCropBlock block, String modelName, String textureName) {
        getVariantBuilder(block).forAllStates(state -> {
            IntegerProperty ageProperty = block.getAgeProperty();
            int age = state.getValue(ageProperty);
            String stage = "_" + age;
            return ConfiguredModel.builder()
                    .modelFile(models().crop(modelName + stage, modLoc("block/crops/" + textureName + stage)).renderType("cutout")).build();
        });
    }

    public ModelFile directionalBlockModel(Supplier<? extends Block> block, String name, String side, String front, String back, String top) {
        return models().withExistingParent(name, BLOCK_FOLDER + "/observer")
                .texture("bottom", texture(back))
                .texture("side", texture(side))
                .texture("top", texture(top))
                .texture("front", texture(front))
                .texture("particle", texture(front));
    }
}
