package net.bexla.orevolution.content.types;

import com.teamabnormals.blueprint.core.data.client.BlueprintItemModelProvider;
import net.bexla.orevolution.Orevolution;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.WallBlock;
import net.minecraftforge.client.model.generators.ItemModelBuilder;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.function.Supplier;

// Credits to Oreganized (Team Galena)
public abstract class ItemModelProvider extends BlueprintItemModelProvider {

    public ItemModelProvider(PackOutput output, ExistingFileHelper help) {
        super(output, Orevolution.MODID, help);
    }

    protected String blockName(Supplier<? extends Block> block) {
        return ForgeRegistries.BLOCKS.getKey(block.get()).getPath();
    }

    private ResourceLocation blockTexture(Supplier<? extends Block> block) {
        return key(block.get()).withPrefix("block/");
    }

    public ItemModelBuilder block(Supplier<? extends Block> block) {
        return block(block, blockName(block));
    }

    public ItemModelBuilder block(Supplier<? extends Block> block, String name) {
        return withExistingParent(blockName(block), modLoc("block/" + name));
    }

    public ItemModelBuilder blockFlat(Supplier<? extends Block> block) {
        return blockFlat(block, blockName(block));
    }

    public ItemModelBuilder blockFlat(Supplier<? extends Block> block, Supplier<? extends Block> fullBlock) {
        return blockFlat(block, blockName(fullBlock));
    }

    public ItemModelBuilder generated(String name, ResourceLocation texture) {
        return withExistingParent(name, mcLoc("item/generated"))
                .texture("layer0", texture);
    }

    public ItemModelBuilder generated(Supplier<? extends ItemLike> itemLike, ResourceLocation texture) {
        return generated(name(itemLike.get()), texture);
    }

    public ItemModelBuilder blockFlat(Supplier<? extends Block> block, String name) {
        return generated(block, modLoc("block/" + name));
    }

    public ItemModelBuilder normalItem(Supplier<? extends Item> item, String subfolder) {
        return generated(item, itemTex(item.get(), subfolder));
    }

    public static ResourceLocation itemTex(ItemLike item, String subfolder) {
        ResourceLocation name = key(item);
        return new ResourceLocation(name.getNamespace(), "item/" + (subfolder + "/") + name.getPath());
    }

    public ItemModelBuilder toolItem(Supplier<? extends Item> item) {
        return withExistingParent(ForgeRegistries.ITEMS.getKey(item.get()).getPath(), mcLoc("item/handheld"))
                .texture("layer0", itemTex(item.get(), "tool"));
    }

    /*public ItemModelBuilder shieldItem(Supplier<? extends Item> item) {
        var texture = itemTexture(item.get());
        var name = name(item.get());

        var blockingModel = withExistingParent(name + "_blocking", new ResourceLocation(SHIELD_EXPANSION_ID, "item/netherite_shield_blocking"))
                .guiLight(BlockModel.GuiLight.FRONT)
                .texture("1", texture)
                .texture("particle", texture);

        return withExistingParent(name, new ResourceLocation(SHIELD_EXPANSION_ID, "item/netherite_shield"))
                .guiLight(BlockModel.GuiLight.FRONT)
                .texture("1", texture)
                .texture("particle", texture)
                .override()
                .predicate(new ResourceLocation("blocking"), 1.0F)
                .model(blockingModel)
                .end();
    }*/

    public ItemModelBuilder wall(Supplier<? extends WallBlock> wall, Supplier<? extends Block> fullBlock) {
        return wallInventory(ForgeRegistries.BLOCKS.getKey(wall.get()).getPath(), blockTexture(fullBlock));
    }
}