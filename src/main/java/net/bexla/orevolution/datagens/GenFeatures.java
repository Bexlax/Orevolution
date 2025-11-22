package net.bexla.orevolution.datagens;

import net.bexla.orevolution.Orevolution;
import net.bexla.orevolution.OrevolutionConfig;
import net.bexla.orevolution.compatibility.ModCompat;
import net.bexla.orevolution.compatibility.spelunkery.RegBlocksSK;
import net.bexla.orevolution.content.data.utility.OreType;
import net.bexla.orevolution.content.data.utility.OrevolutionUtils;
import net.bexla.orevolution.content.types.features.MeteoriteFeature;
import net.bexla.orevolution.content.types.features.OreConditionalFeature;
import net.bexla.orevolution.init.RegBlocks;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.OreConfiguration;
import net.minecraft.world.level.levelgen.placement.*;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GenFeatures {
    public static final DeferredRegister<Feature<?>> FEATURES =
            DeferredRegister.create(ForgeRegistries.FEATURES, Orevolution.MODID);


    public static final RegistryObject<Feature<OreConfiguration>> TIN_ORE =
            FEATURES.register("tin_ore_feature",
                    () -> new OreConditionalFeature(OreConfiguration.CODEC, OrevolutionConfig.COMMON.generateTinOre));

    public static final RegistryObject<Feature<OreConfiguration>> PLATINUM_ORE =
            FEATURES.register("platinum_ore_feature",
                    () -> new OreConditionalFeature(OreConfiguration.CODEC, OrevolutionConfig.COMMON.generatePlatOre));

    public static final RegistryObject<Feature<OreConfiguration>> TIN_ORE_SK =
            FEATURES.register("tin_ore_spelunkery_feature",
                    () -> new OreConditionalFeature(OreConfiguration.CODEC, () -> (OrevolutionConfig.COMMON.generateTinOre.get() && ModCompat.isModLoaded(ModCompat.spelunkery()))));

    public static final RegistryObject<Feature<OreConfiguration>> PLATINUM_ORE_SK =
            FEATURES.register("platinum_ore_spelunkery_feature",
                    () -> new OreConditionalFeature(OreConfiguration.CODEC, () -> (OrevolutionConfig.COMMON.generatePlatOre.get() && ModCompat.isModLoaded(ModCompat.spelunkery()))));

    public static final RegistryObject<Feature<OreConfiguration>> TUNGSTEN_ORE =
            FEATURES.register("tungsten_ore_feature",
                    () -> new OreConditionalFeature(OreConfiguration.CODEC, OrevolutionConfig.COMMON.generateTungstenOre));

    public static final RegistryObject<Feature<OreConfiguration>> TUNGSTEN_ORE_SK =
            FEATURES.register("tungsten_ore_spelunkery_feature",
                    () -> new OreConditionalFeature(OreConfiguration.CODEC, () -> (OrevolutionConfig.COMMON.generateTungstenOre.get() && ModCompat.isModLoaded(ModCompat.spelunkery()))));

    public static final RegistryObject<Feature<OreConfiguration>> EXPERIENCE_ORE =
            FEATURES.register("experience_ore_feature",
                    () -> new OreConditionalFeature(OreConfiguration.CODEC, OrevolutionConfig.COMMON.generateExperienceOre));


    public static final RegistryObject<Feature<NoneFeatureConfiguration>> METEORITE =
            FEATURES.register("meteorite_feature", () -> new MeteoriteFeature(NoneFeatureConfiguration.CODEC));


    public static final class Configured {
        public static final Map<String, ResourceKey<ConfiguredFeature<?, ?>>> ALL = new HashMap<>();

        private static ResourceKey<ConfiguredFeature<?, ?>> create(String name) {
            return ALL.computeIfAbsent(name, n -> ResourceKey.create(Registries.CONFIGURED_FEATURE, OrevolutionUtils.modLocat(n)));
        }

        public static final List<Block> TIN_ORES = List.of(RegBlocks.TIN_ORE.get(), RegBlocks.DEEPSLATE_TIN_ORE.get());
        public static final List<Block> PLATINUM_ORES = List.of(RegBlocks.PLATINUM_ORE.get(), RegBlocks.DEEPSLATE_PLATINUM_ORE.get());

        public static final List<Block> TIN_ORES_SK = List.of(RegBlocksSK.TIN_ORE_TUFF.get(), RegBlocksSK.TIN_ORE_ANDESITE.get(), RegBlocksSK.TIN_ORE_GRANITE.get(), RegBlocksSK.TIN_ORE_DIORITE.get());
        public static final List<Block> PLATINUM_ORES_SK = List.of(RegBlocksSK.PLATINUM_ORE_TUFF.get(), RegBlocksSK.PLATINUM_ORE_ANDESITE.get(), RegBlocksSK.PLATINUM_ORE_GRANITE.get(), RegBlocksSK.PLATINUM_ORE_DIORITE.get());

        public static void bootstrap(BootstapContext<ConfiguredFeature<?, ?>> ctx) {
            registerOre(ctx, "tin_ore", TIN_ORE, OreType.OVERWORLD, TIN_ORES,
                    13, 0F);
            registerOre(ctx, "tin_ore_extra", TIN_ORE, OreType.OVERWORLD, TIN_ORES,
                    12, 0F);

            registerOre(ctx, "platinum_ore", PLATINUM_ORE, OreType.OVERWORLD, PLATINUM_ORES,
                    8, 0.2F);
            registerOre(ctx, "platinum_ore_extra", PLATINUM_ORE, OreType.OVERWORLD, PLATINUM_ORES,
                    7, 0.3F);

            registerOre(ctx, "tungsten_ore", TUNGSTEN_ORE, OreType.NETHER, List.of(RegBlocks.NETHER_TUNGSTEN_ORE.get()),
                    8, 0.4F);
            registerOre(ctx, "tungsten_ore_extra", TUNGSTEN_ORE, OreType.NETHER, List.of(RegBlocks.NETHER_TUNGSTEN_ORE.get()),
                    10, 0.6F);

            registerOre(ctx, "nether_experience_ore", EXPERIENCE_ORE, OreType.NETHER, List.of(RegBlocks.NETHER_XP_ORE.get()),
                    8, 0.7F);
            registerOre(ctx, "end_experience_ore", EXPERIENCE_ORE, OreType.END, List.of(RegBlocks.END_XP_ORE.get()),
                    6, 0.75F);

            registerOre(ctx, "tin_ore_spelunkery", TIN_ORE_SK, OreType.OVERWORLD_SPELUNKERY, TIN_ORES_SK,
                    13, 0F);
            registerOre(ctx, "platinum_ore_spelunkery", PLATINUM_ORE_SK, OreType.OVERWORLD_SPELUNKERY, PLATINUM_ORES_SK,
                    8, 0.2F);
            registerOre(ctx, "tungsten_ore_spelunkery", TUNGSTEN_ORE_SK, OreType.NETHER, List.of(RegBlocksSK.NETHER_TUNGSTEN_ORE_BLACKSTONE.get()),
                    4, 0.95F);

            ctx.register(Configured.create("meteorite_high"),
                    new ConfiguredFeature<>(METEORITE.get(), NoneFeatureConfiguration.INSTANCE));
            ctx.register(Configured.create("meteorite_low"),
                    new ConfiguredFeature<>(METEORITE.get(), NoneFeatureConfiguration.INSTANCE));
        }

        private static void registerOre(BootstapContext<ConfiguredFeature<?, ?>> ctx, String name, RegistryObject<Feature<OreConfiguration>> feature, OreType type, List<Block> variants, int size, float discardChance) {
            var key = create(name);
            List<OreConfiguration.TargetBlockState> targets = new ArrayList<>();
            for (int i = 0; i < Math.min(type.getTargets().size(), variants.size()); i++) {
                targets.add(OreConfiguration.target(type.getTargets().get(i), variants.get(i).defaultBlockState()));
            }

            ctx.register(key, new ConfiguredFeature<>(
                    feature.get(),
                    new OreConfiguration(targets, size, discardChance)
            ));
        }
    }

    public static final class Placed {
        public static final Map<String, ResourceKey<PlacedFeature>> ALL = new HashMap<>();

        private static ResourceKey<PlacedFeature> create(String name) {
            return ALL.computeIfAbsent(name, n -> ResourceKey.create(Registries.PLACED_FEATURE, OrevolutionUtils.modLocat(n)));
        }

        public static void bootstrap(BootstapContext<PlacedFeature> ctx) {
            var features = ctx.lookup(Registries.CONFIGURED_FEATURE);

            registerOrePlacement(ctx, features, "tin_ore", 10, 0, 110);
            registerOrePlacement(ctx, features, "tin_ore_extra", 10, 60, 320);

            registerOrePlacement(ctx, features, "platinum_ore", 7, -60, 53);
            registerOrePlacement(ctx, features, "platinum_ore_extra", 7, -60, 53);

            registerOrePlacement(ctx, features, "nether_experience_ore", 7, -40, 40);
            registerOrePlacement(ctx, features, "end_experience_ore", 9, -40, 60);

            registerOrePlacement(ctx, features, "tungsten_ore", 12, -15, 40);
            registerRareOrePlacement(ctx, features, "tungsten_ore_extra", 20, -40, -10);

            registerMeteoritePlacementTop(ctx, features, "meteorite_high", 120, 180);
            registerMeteoritePlacementRange(ctx, features, "meteorite_low", 210, 95, 140);

            registerOrePlacement(ctx, features, "tin_ore_spelunkery", 9, 0, 110);
            registerOrePlacement(ctx, features, "platinum_ore_spelunkery", 6, -20, 53);
            registerOrePlacement(ctx, features, "tungsten_ore_spelunkery", 12, -15, 40);
        }

        private static void registerMeteoritePlacementTop(BootstapContext<PlacedFeature> ctx, HolderGetter<ConfiguredFeature<?, ?>> features, String name, int rarity, int minY) {
            ctx.register(
                    create(name),
                    new PlacedFeature(
                            features.getOrThrow(Configured.ALL.get(name)),
                            rareOrePlacement(rarity,
                                    HeightRangePlacement.uniform(VerticalAnchor.absolute(minY), VerticalAnchor.top()))
                    )
            );
        }

        private static void registerMeteoritePlacementRange(BootstapContext<PlacedFeature> ctx, HolderGetter<ConfiguredFeature<?, ?>> features, String name, int rarity, int minY, int maxY) {
            ctx.register(
                    create(name),
                    new PlacedFeature(
                            features.getOrThrow(Configured.ALL.get(name)),
                            rareOrePlacement(rarity,
                                    HeightRangePlacement.uniform(VerticalAnchor.absolute(minY), VerticalAnchor.absolute(maxY)))
                    )
            );
        }

        private static void registerOrePlacement(BootstapContext<PlacedFeature> ctx, HolderGetter<ConfiguredFeature<?, ?>> features, String name, int count, int minY, int maxY) {
            ctx.register(
                    create(name),
                    new PlacedFeature(
                            features.getOrThrow(Configured.ALL.get(name)),
                            commonOrePlacement(count,
                                    HeightRangePlacement.uniform(VerticalAnchor.absolute(minY), VerticalAnchor.absolute(maxY)))
                    )
            );
        }

        private static void registerRareOrePlacement(BootstapContext<PlacedFeature> ctx, HolderGetter<ConfiguredFeature<?, ?>> features, String name, int count, int minY, int maxY) {
            ctx.register(
                    create(name),
                    new PlacedFeature(
                            features.getOrThrow(Configured.ALL.get(name)),
                            rareOrePlacement(count,
                                    HeightRangePlacement.uniform(VerticalAnchor.absolute(minY), VerticalAnchor.absolute(maxY)))
                    )
            );
        }

        private static List<PlacementModifier> orePlacement(PlacementModifier p_195347_, PlacementModifier p_195348_) {
            return List.of(p_195347_, InSquarePlacement.spread(), p_195348_, BiomeFilter.biome());
        }

        private static List<PlacementModifier> commonOrePlacement(int p_195344_, PlacementModifier p_195345_) {
            return orePlacement(CountPlacement.of(p_195344_), p_195345_);
        }

        private static List<PlacementModifier> rareOrePlacement(int p_195350_, PlacementModifier p_195351_) {
            return orePlacement(RarityFilter.onAverageOnceEvery(p_195350_), p_195351_);
        }
    }
}
