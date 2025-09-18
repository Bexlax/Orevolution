package net.bexla.orevolution.content.data.utility;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.versions.forge.ForgeVersion;

import static net.bexla.orevolution.content.data.utility.OrevolutionUtils.modLocat;

public class OrevolutionTags {

    public static class Items {
        public static final TagKey<Item> TinProgFollow = tag("tools/tin_progress_followers");
        public static final TagKey<Item> PlatProgFollow = tag("tools/platinum_progress_followers");
        public static final TagKey<Item> TinProgExcept = tag("tools/tin_progress_exceptions");
        public static final TagKey<Item> PlatProgExcept = tag("tools/platinum_progress_exceptions");

        public static final TagKey<Item> TinIngots = forgeTag("ingots/tin");
        public static final TagKey<Item> PlatIngots = forgeTag("ingots/platinum");
        public static final TagKey<Item> TungsIngots = forgeTag("ingots/tungsten");
        public static final TagKey<Item> EnderiteIngots = forgeTag("ingots/enderite");
        public static final TagKey<Item> VerditeIngots = forgeTag("ingots/verdite");

        public static final TagKey<Item> TinNuggets = forgeTag("nuggets/silver");
        public static final TagKey<Item> PlatNuggets = forgeTag("nuggets/lead");
        public static final TagKey<Item> TungsNuggets = forgeTag("nuggets/electrum");
        public static final TagKey<Item> VerditeNuggets = forgeTag("nuggets/netherite");

        public static final TagKey<Item> TinRaws = forgeTag("raw_materials/raw_tin");
        public static final TagKey<Item> PlatRaws = forgeTag("raw_materials/raw_platinum");
        public static final TagKey<Item> TungsRaws = forgeTag("raw_materials/raw_tungsten");

        private static TagKey<Item> tag(String name) {
            return ItemTags.create(modLocat(name));
        }
        private static TagKey<Item> forgeTag(String name) {
            return ItemTags.create(new ResourceLocation(ForgeVersion.MOD_ID, name));
        }
    }

    public static class Blocks {
        public static final TagKey<Block> Ores = forgeTag("ores");

        public static final TagKey<Block> TinOres = forgeTag("ores/tin");
        public static final TagKey<Block> PlatOres = forgeTag("ores/platinum");
        public static final TagKey<Block> TungsOres = forgeTag("ores/tungsten");

        public static final TagKey<Block> TinStorages = forgeTag("storage_blocks/tin");
        public static final TagKey<Block> PlatStorages = forgeTag("storage_blocks/platinum");
        public static final TagKey<Block> TungsStorages = forgeTag("storage_blocks/tungsten");
        public static final TagKey<Block> EnderiteStorages = forgeTag("storage_blocks/enderite");
        public static final TagKey<Block> LivingstoneStorages = forgeTag("storage_blocks/livingstone");
        public static final TagKey<Block> VerditeStorages = forgeTag("storage_blocks/verdite");

        public static final TagKey<Block> RawTinStorages = forgeTag("storage_blocks/raw_tin");
        public static final TagKey<Block> RawPlatStorages = forgeTag("storage_blocks/raw_platinum");
        public static final TagKey<Block> RawTungsStorages = forgeTag("storage_blocks/raw_tungsten");

        public static final TagKey<Block> TinTiered = tag("needs_tin_tool");
        public static final TagKey<Block> PlatTiered = tag("needs_platinum_tool");
        public static final TagKey<Block> AethersteelTiered = tag("needs_aethersteel_tool");


        private static TagKey<Block> tag(String name) {
            return BlockTags.create(modLocat(name));
        }
        private static TagKey<Block> forgeTag(String name) {
            return BlockTags.create(new ResourceLocation(ForgeVersion.MOD_ID, name));
        }
    }

    public static class Biomes {
        public static final TagKey<Biome> RichTin = tag("rich_in_tin_ore");
        public static final TagKey<Biome> RichPlat = tag("rich_in_platinum_ore");
        public static final TagKey<Biome> RichTungs = tag("rich_in_tungsten_ore");

        private static TagKey<Biome> tag(String name) {
            return TagKey.create(Registries.BIOME, modLocat(name));
        }
    }
}
