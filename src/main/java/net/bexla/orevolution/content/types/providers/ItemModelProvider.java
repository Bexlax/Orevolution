package net.bexla.orevolution.content.types.providers;

import com.teamabnormals.blueprint.core.data.client.BlueprintItemModelProvider;
import net.bexla.orevolution.Orevolution;
import net.minecraft.client.renderer.block.model.BlockModel;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.PackType;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.client.model.generators.ItemModelBuilder;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

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

    @SafeVarargs
    public final void trimArmorItem(RegistryObject<? extends ItemLike>... items) {
        for(RegistryObject<? extends ItemLike> item : items) {
            Item var7 = ((ItemLike)item.get()).asItem();
            if (var7 instanceof ArmorItem armor) {
                ResourceLocation location = ForgeRegistries.ITEMS.getKey(armor);
                ItemModelBuilder itemModel = this.withExistingParent(name((ItemLike)item.get()), "item/generated").texture("layer0", new ResourceLocation(this.modid, "item/armor/" + name(armor)));
                int trimType = 1;

                for(String trim : new String[]{"quartz", "iron", "netherite", "redstone", "copper", "gold", "emerald", "diamond", "lapis", "amethyst"}) {
                    String var10002 = location.getNamespace();
                    String var10003 = location.getPath();
                    ResourceLocation name = new ResourceLocation(var10002, "item/armor/" + var10003 + "_" + trim + "_trim");
                    itemModel.override().model(new ModelFile.UncheckedModelFile(name)).predicate(new ResourceLocation("trim_type"), (float)((double)trimType / (double)10.0F));
                    var10002 = armor.getType().getName();
                    ResourceLocation texture = new ResourceLocation("trims/items/" + var10002 + "_trim_" + trim);
                    this.existingFileHelper.trackGenerated(texture, PackType.CLIENT_RESOURCES, ".png", "textures");
                    ((ItemModelBuilder)((ItemModelBuilder)this.withExistingParent(name.getPath(), "item/generated")).texture("layer0", new ResourceLocation(this.modid, "item/armor/" + location.getPath()))).texture("layer1", texture);
                    ++trimType;
                }
            }
        }
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

    public ItemModelBuilder shieldItem(Supplier<? extends Item> item, String type) {
        var texture = itemTex(item.get(), "compat/shieldexp");
        var name = name(item.get());

        var blockingModel = withExistingParent(name + "_blocking", modLoc("item/" + type + "_shield_blocking"))
                .guiLight(BlockModel.GuiLight.FRONT)
                .texture("1", texture)
                .texture("particle", texture);

        return withExistingParent(name, modLoc("item/" + type + "_shield"))
                .guiLight(BlockModel.GuiLight.FRONT)
                .texture("1", texture)
                .texture("particle", texture)
                .override()
                .predicate(new ResourceLocation("blocking"), 1.0F)
                .model(blockingModel)
                .end();
    }

    public ItemModelBuilder wall(Supplier<? extends Block> wall, Supplier<? extends Block> fullBlock) {
        return wallInventory(ForgeRegistries.BLOCKS.getKey(wall.get()).getPath(), key(fullBlock.get()).withPrefix("block/decorative/"));
    }
}