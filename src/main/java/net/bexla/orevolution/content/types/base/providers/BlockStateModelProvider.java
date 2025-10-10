package net.bexla.orevolution.content.types.base.providers;

import net.bexla.orevolution.Orevolution;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SlabBlock;
import net.minecraft.world.level.block.StairBlock;
import net.minecraft.world.level.block.WallBlock;
import net.minecraftforge.client.model.generators.BlockModelBuilder;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.function.Supplier;

import static net.minecraftforge.client.model.generators.ModelProvider.BLOCK_FOLDER;

// Credits to Oreganized (Team Galena)
public abstract class BlockStateModelProvider extends BlockStateProvider {

    public BlockStateModelProvider(PackOutput output, ExistingFileHelper help) {
        super(output, Orevolution.MODID, help);
    }

    protected ResourceLocation texture(String name) {
        return modLoc(BLOCK_FOLDER + "/" + name);
    }

    protected String name(Block block) {
        return ForgeRegistries.BLOCKS.getKey(block).getPath();
    }

    protected String name(Supplier<? extends Block> block) {
        return name(block.get());
    }

    public void simpleBlock(Supplier<? extends Block> block, String subfolder) {
        this.simpleBlock(block.get(), this.cubeAll(block.get(), subfolder));
    }

    public ModelFile cubeAll(Block block, String subfolder) {
        return this.models().cubeAll(this.name(block), this.blockTexture(block, subfolder));
    }

    public ResourceLocation blockTexture(Block block, String subfolder) {
        ResourceLocation name = this.key(block);
        return new ResourceLocation(name.getNamespace(), "block/" + subfolder + "/" + name.getPath());
    }

    private ResourceLocation key(Block block) {
        return ForgeRegistries.BLOCKS.getKey(block);
    }

    public void stairsBlock(Supplier<? extends StairBlock> block, Supplier<? extends Block> fullBlock) {
        stairsBlock(block.get(), texture(name(fullBlock)));
    }

    public void slabBlock(Supplier<? extends SlabBlock> block, Supplier<? extends Block> fullBlock) {
        slabBlock(block.get(), texture(name(fullBlock)), texture(name(fullBlock)));
    }

    public void wallBlock(Supplier<? extends WallBlock> wall, Supplier<? extends Block> fullBlock) {
        wallBlock(wall.get(), texture(name(fullBlock)));
    }

    public void waxedBlock(Supplier<? extends Block> block, Block origin) {
        simpleBlock(block.get(), cubeAll(origin));
    }

    public ModelFile cubeBottomTop(Supplier<? extends Block> block) {
        BlockModelBuilder model = models().getBuilder(name(block));
        model.parent(models().getExistingFile(new ResourceLocation("minecraft", "block" + "/cube_bottom_top")));
        model.texture("top", texture(name(block) + "_top"));
        model.texture("bottom", texture(name(block) + "_bottom"));
        model.texture("side", texture(name(block) + "_side"));
        return model;
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
