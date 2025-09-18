package net.bexla.orevolution.providers;

import net.bexla.orevolution.Orevolution;
import net.bexla.orevolution.content.data.utility.OrevolutionLists;
import net.bexla.orevolution.content.types.ItemModelProvider;
import net.bexla.orevolution.init.RegBlocks;
import net.bexla.orevolution.init.RegItems;
import net.minecraft.data.PackOutput;
import net.minecraft.world.item.Item;
import net.minecraftforge.client.model.generators.ItemModelBuilder;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.RegistryObject;
import org.jetbrains.annotations.NotNull;

import java.util.function.Supplier;

public class GENItemModels extends ItemModelProvider {
    public GENItemModels(PackOutput output, ExistingFileHelper helper) {
        super(output, helper);
    }

    @Override
    public @NotNull String getName() {
        return Orevolution.MODID + " Item Models";
    }

    private ItemModelBuilder ingredient(Supplier<? extends Item> item) {
        return normalItem(item, "ingredient");
    }

    private ItemModelBuilder consumable(Supplier<? extends Item> item) {
        return normalItem(item, "consumable");
    }

    private ItemModelBuilder armor(Supplier<? extends Item> item) {
        return normalItem(item, "armor");
    }

    @Override
    protected void registerModels() {
        // items //
        // raws
        ingredient(RegItems.RAW_TIN);
        ingredient(RegItems.RAW_PLATINUM);
        ingredient(RegItems.RAW_TUNGSTEN);
        ingredient(RegItems.AETHERSTEEL_CHUNK);
        // ingots
        ingredient(RegItems.TIN_INGOT);
        ingredient(RegItems.PLATINUM_INGOT);
        ingredient(RegItems.TUNGSTEN_INGOT);
        ingredient(RegItems.AETHERSTEEL_INGOT);
        // alloys
        ingredient(RegItems.BRONZE_ALLOY);
        ingredient(RegItems.STEEL_ALLOY);
        ingredient(RegItems.TUMBAGA_ALLOY);
        // nuggets
        ingredient(RegItems.TIN_NUGGET);
        ingredient(RegItems.PLATINUM_NUGGET);
        ingredient(RegItems.TUNGSTEN_NUGGET);
        ingredient(RegItems.VERDITE_NUGGET);
        ingredient(RegItems.LIVINGSTONE_SHARD);
        // template
        ingredient(RegItems.AETHERSTEEL_TEMPLATE);
        ingredient(RegItems.REINFORCED_TEMPLATE);
        ingredient(RegItems.BASIC_TEMPLATE);

        // food //
        consumable(RegItems.VERDITE_APPLE);

        OrevolutionLists.TOOLS.forEach((tag, items) -> {
            for (RegistryObject<Item> item : items) {
                toolItem(item);
            }
        });

        // tin set
        toolItem(RegItems.TIN_SWORD);
        toolItem(RegItems.TIN_PICKAXE);
        toolItem(RegItems.TIN_AXE);
        toolItem(RegItems.TIN_SHOVEL);
        toolItem(RegItems.TIN_HOE);

        // platinum set
        armor(RegItems.PLATINUM_HELMET);
        armor(RegItems.PLATINUM_CHESTPLATE);
        armor(RegItems.PLATINUM_LEGGINGS);
        armor(RegItems.PLATINUM_BOOTS);

        toolItem(RegItems.PLATINUM_SWORD);
        toolItem(RegItems.PLATINUM_PICKAXE);
        toolItem(RegItems.PLATINUM_AXE);
        toolItem(RegItems.PLATINUM_SHOVEL);
        toolItem(RegItems.PLATINUM_HOE);

        // reinforced set
        armor(RegItems.REINFORCED_NETHERITE_HELMET);
        armor(RegItems.REINFORCED_NETHERITE_CHESTPLATE);
        armor(RegItems.REINFORCED_NETHERITE_LEGGINGS);
        armor(RegItems.REINFORCED_NETHERITE_BOOTS);

        // aethersteel set
        armor(RegItems.AETHERSTEEL_HELMET);
        armor(RegItems.AETHERSTEEL_CHESTPLATE);
        armor(RegItems.AETHERSTEEL_LEGGINGS);
        armor(RegItems.AETHERSTEEL_BOOTS);

        toolItem(RegItems.AETHERSTEEL_SWORD);
        toolItem(RegItems.AETHERSTEEL_PICKAXE);
        toolItem(RegItems.AETHERSTEEL_AXE);
        toolItem(RegItems.AETHERSTEEL_SHOVEL);
        toolItem(RegItems.AETHERSTEEL_HOE);

        // livingstone set
        armor(RegItems.LIVINGSTONE_HELMET);
        armor(RegItems.LIVINGSTONE_CHESTPLATE);
        armor(RegItems.LIVINGSTONE_LEGGINGS);
        armor(RegItems.LIVINGSTONE_BOOTS);

        toolItem(RegItems.LIVINGSTONE_SWORD);
        toolItem(RegItems.LIVINGSTONE_PICKAXE);
        toolItem(RegItems.LIVINGSTONE_AXE);
        toolItem(RegItems.LIVINGSTONE_SHOVEL);
        toolItem(RegItems.LIVINGSTONE_HOE);

        // verdite set
        armor(RegItems.VERDITE_HELMET);
        armor(RegItems.VERDITE_CHESTPLATE);
        armor(RegItems.VERDITE_LEGGINGS);
        armor(RegItems.VERDITE_BOOTS);

        toolItem(RegItems.VERDITE_SWORD);
        toolItem(RegItems.VERDITE_PICKAXE);
        toolItem(RegItems.VERDITE_AXE);
        toolItem(RegItems.VERDITE_SHOVEL);
        toolItem(RegItems.VERDITE_HOE);

        // bronze set
        armor(RegItems.BRONZE_CROWN);
        armor(RegItems.BRONZE_CROWN_EMERALD);
        armor(RegItems.BRONZE_CROWN_DIAMOND);
        armor(RegItems.BRONZE_CROWN_LAPIS);
        armor(RegItems.BRONZE_CROWN_REDSTONE);

        // tumbaga set
        toolItem(RegItems.TUMBAGA_SWORD);
        toolItem(RegItems.TUMBAGA_PICKAXE);
        toolItem(RegItems.TUMBAGA_AXE);
        toolItem(RegItems.TUMBAGA_SHOVEL);
        toolItem(RegItems.TUMBAGA_HOE);

        // MISC
        // shieldItem(RegItems.TungstenShield);


        // blocks //
        // ores
        block(RegBlocks.TIN_ORE);
        block(RegBlocks.DEEPSLATE_TIN_ORE);
        block(RegBlocks.PLATINUM_ORE);
        block(RegBlocks.DEEPSLATE_PLATINUM_ORE);
        block(RegBlocks.NETHER_TUNGSTEN_ORE);
    }
}
