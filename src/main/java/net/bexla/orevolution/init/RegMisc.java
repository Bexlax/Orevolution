package net.bexla.orevolution.init;

import com.github.smallinger.copperagebackport.item.armor.CopperArmorMaterial;
import com.mojang.logging.LogUtils;
import galena.oreganized.index.OArmorMaterials;
import galena.oreganized.index.OItemTiers;
import net.bexla.orevolution.OrevolutionConfig;
import net.bexla.orevolution.compatibility.ModCompat;
import net.bexla.orevolution.content.data.Conditionals;
import net.bexla.orevolution.content.data.OrevolutionArmorTiers;
import net.bexla.orevolution.content.data.OrevolutionToolTiers;
import net.bexla.orevolution.content.data.powers.armors.*;
import net.bexla.orevolution.content.data.powers.armors.hardcoded.ArmorElectrum;
import net.bexla.orevolution.content.data.powers.tools.*;
import net.bexla.orevolution.content.data.powers.tools.hardcoded.AethersteelAutosmelt;
import net.bexla.orevolution.content.data.powers.tools.hardcoded.SteelTools;
import net.bexla.orevolution.content.data.utility.OrevolutionTags;
import net.bexla.orevolution.content.types.ArmorPowerRegistry;
import net.bexla.orevolution.content.types.TierProgressRegistry;
import net.bexla.orevolution.content.types.ToolPowerRegistry;
import net.bexla.orevolution.content.types.interfaces.IConditional;
import net.bexla.orevolution.content.types.power.tool.OrevolutionToolPower;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.FluidTags;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.MobType;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.item.ArmorMaterials;
import net.minecraft.world.item.Tiers;
import net.minecraftforge.common.ForgeMod;
import net.minecraftforge.common.Tags;
import org.slf4j.Logger;

import java.util.List;
import java.util.UUID;
import java.util.function.Supplier;

import static net.bexla.orevolution.content.data.utility.OrevolutionUtils.modLocat;

public class RegMisc {
    private static final Logger LOGGER = LogUtils.getLogger();

    public static final IConditional AUTOSMELT_CONDITIONS =
            Conditionals.or(Conditionals.isBlockstateTaggedAs(Tags.Blocks.ORES),
            Conditionals.or(Conditionals.isBlockstateTaggedAs(Tags.Blocks.SAND),
            Conditionals.or(Conditionals.isBlockstateTaggedAs(BlockTags.LOGS_THAT_BURN), Conditionals.isBlockstateTaggedAs(BlockTags.CROPS))));

    // Effects
    public static final List<Supplier<MobEffect>> PLATINUM_TOOL_EFFECTS = List.of(
            () -> MobEffects.POISON,
            () -> MobEffects.WEAKNESS
    );
    public static final List<Supplier<MobEffect>> GOLD_ARMOR_EFFECTS = List.of(
            () -> MobEffects.DIG_SPEED,
            () -> MobEffects.DAMAGE_RESISTANCE
    );
    public static final List<Supplier<MobEffect>> AETHERSTEEL_ARMOR_GRANTS_EFFECTS = List.of(
            () -> MobEffects.FIRE_RESISTANCE,
            () -> MobEffects.HEALTH_BOOST
    );

    // Multi-powers
    
    public static void RegisterSortedTiers() {
        final ResourceLocation wood = new ResourceLocation("wood");
        final ResourceLocation stone = new ResourceLocation("stone");
        final ResourceLocation iron = new ResourceLocation("iron");
        final ResourceLocation diamond = new ResourceLocation("diamond");
        final ResourceLocation netherite = new ResourceLocation("netherite");

        TierProgressRegistry.registerTier(Tiers.WOOD, wood,
                List.of(), List.of(), OrevolutionTags.Blocks.woodExceptions);

        TierProgressRegistry.registerTier(Tiers.STONE, stone,
                List.of(wood), List.of(iron), OrevolutionTags.Blocks.stoneExceptions);

        TierProgressRegistry.registerTier(Tiers.IRON, iron,
                List.of(stone), List.of(diamond), OrevolutionTags.Blocks.ironExceptions);

        TierProgressRegistry.registerTier(Tiers.DIAMOND, diamond,
                List.of(iron), List.of(netherite), OrevolutionTags.Blocks.diamondExceptions);

        TierProgressRegistry.registerTier(Tiers.NETHERITE, netherite,
                List.of(diamond), List.of(), OrevolutionTags.Blocks.netheriteExceptions);

        TierProgressRegistry.registerTier(OrevolutionToolTiers.TIN, modLocat("tin"), List.of(Tiers.STONE), List.of(Tiers.IRON), OrevolutionTags.Blocks.tinExceptions);
        TierProgressRegistry.registerTier(OrevolutionToolTiers.PLATINUM, modLocat("platinum"), List.of(Tiers.IRON), List.of(Tiers.DIAMOND), OrevolutionTags.Blocks.platExceptions);
        TierProgressRegistry.registerTier(OrevolutionToolTiers.AETHERSTEEL, modLocat("aethersteel"), List.of(Tiers.NETHERITE), List.of(), OrevolutionTags.Blocks.aetherExceptions);

        LOGGER.info("Sorted all tiers");
    }

    public static void RegisterArmorPowers() {
        ArmorPowerRegistry.register(OrevolutionConfig.POWERS.goldArmorPowers, ArmorMaterials.GOLD,
                new ArmorGrantEffects("armor_wearer_grants", Conditionals.always(), 20, 1, GOLD_ARMOR_EFFECTS));
        ArmorPowerRegistry.register(OrevolutionConfig.POWERS.ironArmorPowers, ArmorMaterials.IRON,
                new ArmorReduceDamageType("iron_armor", Conditionals.byChance(0.25), DamageTypes.ARROW, 0));
        ArmorPowerRegistry.register(OrevolutionConfig.POWERS.bronzeArmorPowers, OrevolutionArmorTiers.BRONZE,
                new ArmorGrantEffects("bronze_armor", Conditionals.not(Conditionals.isSubmergedInLiquid(FluidTags.WATER)), 900, 0, () -> MobEffects.WATER_BREATHING));
        ArmorPowerRegistry.register(OrevolutionConfig.POWERS.platinumArmorPowers, OrevolutionArmorTiers.PLATINUM,
                new ArmorCauseEffectsOnAttacked("", "armor_wearer_on_attacked_wearer", Conditionals.always(), 100, 1, () -> MobEffects.DAMAGE_BOOST, null));
        ArmorPowerRegistry.register(OrevolutionConfig.POWERS.diamondArmorPowers, ArmorMaterials.DIAMOND,
                new ArmorReduceDamageType("diamond_armor", Conditionals.always(),
                        List.of(
                                DamageTypes.FALL,
                                DamageTypes.FALLING_ANVIL,
                                DamageTypes.FALLING_STALACTITE,
                                DamageTypes.FALLING_BLOCK,
                                DamageTypes.EXPLOSION
                        ), 0.4F));
        ArmorPowerRegistry.register(OrevolutionConfig.POWERS.netheriteArmorPowers, ArmorMaterials.NETHERITE,
                new ArmorReduceDamageType("diamond_armor", Conditionals.always(),
                        List.of(
                                DamageTypes.FALL,
                                DamageTypes.FALLING_ANVIL,
                                DamageTypes.FALLING_STALACTITE,
                                DamageTypes.FALLING_BLOCK,
                                DamageTypes.EXPLOSION
                        ), 0.5F));
        ArmorPowerRegistry.register(OrevolutionConfig.POWERS.reinforcedNetheriteArmorPowers, OrevolutionArmorTiers.REINFORCED_NETHERITE,
                new ArmorMultiPower(List.of(
                        new ArmorReduceDamageType("diamond_armor", Conditionals.always(),
                                List.of(
                                        DamageTypes.FALL,
                                        DamageTypes.FALLING_ANVIL,
                                        DamageTypes.FALLING_STALACTITE,
                                        DamageTypes.FALLING_BLOCK,
                                        DamageTypes.EXPLOSION
                                ), 0.5F),
                        new ArmorGrantEffects("reinforced_netherite_armor", Conditionals.not(Conditionals.isTouchingLava()), 160, 0, () -> MobEffects.FIRE_RESISTANCE)
                ))
        );
        ArmorPowerRegistry.register(OrevolutionConfig.POWERS.aethersteelArmorPowers, OrevolutionArmorTiers.AETHERSTEEL,
                new ArmorMultiPower(List.of(
                        new ArmorReduceDamageType("diamond_armor", Conditionals.always(),
                                List.of(
                                        DamageTypes.FALL,
                                        DamageTypes.FALLING_ANVIL,
                                        DamageTypes.FALLING_STALACTITE,
                                        DamageTypes.FALLING_BLOCK,
                                        DamageTypes.EXPLOSION
                                ), 0.6F),
                        new ArmorGrantEffects("armor_wearer_grants", Conditionals.always(), 20, 1, AETHERSTEEL_ARMOR_GRANTS_EFFECTS)
                ))
        );

        ArmorPowerRegistry.register(OrevolutionConfig.POWERS.livingstoneArmorPowers, OrevolutionArmorTiers.LIVINGSTONE,
                new ArmorMultiPower(List.of(
                        new ArmorRegeneration("regenerates_daylight", Conditionals.isReceivingDayLight(), 2, 100),
                        new ArmorGrantEffects("armor_wearer_grants_daylight", Conditionals.isReceivingDayLight(), 20, 0, () -> MobEffects.REGENERATION)
                ))
        );
        ArmorPowerRegistry.register(OrevolutionConfig.POWERS.verditeArmorPowers, OrevolutionArmorTiers.VERDITE,
                new ArmorMultiPower(List.of(
                        new ArmorRegeneration("regenerates_daylight", Conditionals.isReceivingDayLight(), 2, 80),
                        new ArmorGrantEffects("armor_wearer_grants_daylight", Conditionals.isReceivingDayLight(), 20, 0, () -> MobEffects.REGENERATION)
                ))
        );

        ArmorPowerRegistry.register(OrevolutionConfig.POWERS.tungstenArmorPowers, OrevolutionArmorTiers.TUNGSTEN,
                new ArmorMultiPower(List.of(
                        new ArmorGrantEffects("bronze_armor", Conditionals.not(Conditionals.isSubmergedInLiquid(FluidTags.WATER)), 900, 0, () -> MobEffects.WATER_BREATHING),
                        new ArmorGrantEffects("tungsten_armor", Conditionals.not(Conditionals.isSubmergedInLiquid(FluidTags.LAVA)), 900, 0, () -> MobEffects.FIRE_RESISTANCE)
                ))
        );

        if(ModCompat.isModLoaded(ModCompat.oreganized())) {
            ArmorPowerRegistry.register(OrevolutionConfig.MODCOMPAT.electrumArmorPowers, OArmorMaterials.ELECTRUM,
                    new ArmorElectrum("electrum_armor")
            );
        }
        if(ModCompat.isModLoaded(ModCompat.backport())) {
            ArmorPowerRegistry.register(OrevolutionConfig.MODCOMPAT.copperArmorPowers, CopperArmorMaterial.COPPER,
                    new ArmorMultiPower(List.of(
                            new ArmorExtendPickUp("armor_extended_pickup", Conditionals.always(), 6.0D),
                            new ArmorModifyAttribute("copper_armor", Conditionals.always(), ForgeMod.BLOCK_REACH,
                                    () -> new AttributeModifier(UUID.fromString("2d6f7b4e-65d2-4f3c-b1a7-9274db6f1123"), "copper_reach_orevolution", 2.0D, AttributeModifier.Operation.ADDITION))
                    ))
            );
        }

        LOGGER.debug("Registered all armor powers");
    }


    public static void RegisterToolsPowers() {
        ToolPowerRegistry.register(OrevolutionToolTiers.TIN, OrevolutionConfig.POWERS.tinToolPowers, OrevolutionConfig.POWERS.tinWeaponPowers,
                new ToolIncreaseDrops("duplication", Conditionals.always(), 1, 0.2),
                new ToolCauseEffectOnHit("on_hit_effect_chance", "", Conditionals.byChance(0.3), 120, 0, () -> MobEffects.POISON, null)
        );
        ToolPowerRegistry.register(Tiers.IRON, OrevolutionConfig.POWERS.ironToolPowers, OrevolutionConfig.POWERS.ironWeaponPowers,
                new ToolsDurabilitySpeed("durability_speed", Conditionals.always()),
                new ToolIncreaseDamageOnHit("on_hit_effect_armored", Conditionals.targetHasArmor(), 2F)
        );
        ToolPowerRegistry.register(Tiers.GOLD, OrevolutionConfig.POWERS.goldToolPowers, OrevolutionConfig.POWERS.goldWeaponPowers,
                new ToolAvoidDamageOnUse("avoid_damage_chance", Conditionals.byChance(0.5)),
                new ToolGiveXPOnKill("xp_looting", Conditionals.always(), 4)
        );
        ToolPowerRegistry.register(OrevolutionToolTiers.STEEL, OrevolutionConfig.POWERS.steelToolPowers, OrevolutionConfig.POWERS.steelWeaponPowers,
                new SteelTools(),
                new ToolCauseEffectOnHit("", "steel_scythe", Conditionals.always(), 180, 0, null, () -> MobEffects.DAMAGE_BOOST)
        );
        ToolPowerRegistry.register(OrevolutionToolTiers.PLATINUM, OrevolutionConfig.POWERS.platinumToolPowers, OrevolutionConfig.POWERS.platinumWeaponPowers,
                new ToolDuplicateXPDrops("xp_duplicate", Conditionals.always()),
                new ToolCauseMultipleEffectsOnHit("undead_on_hit", "", Conditionals.targetMobType(MobType.UNDEAD), 160, 0, PLATINUM_TOOL_EFFECTS, null)
        );
        ToolPowerRegistry.register(Tiers.DIAMOND, OrevolutionConfig.POWERS.diamondToolPowers, OrevolutionConfig.POWERS.diamondWeaponPowers,
                new ToolsDurabilitySpeed("durability_speed", Conditionals.always()),
                new ToolIncreaseDamageOnHit("on_hit_effect_armored", Conditionals.targetHasArmor(), 3F)
        );
        ToolPowerRegistry.register(Tiers.NETHERITE, OrevolutionConfig.POWERS.netheriteToolPowers, OrevolutionConfig.POWERS.netheriteWeaponPowers,
                new ToolAutosmelt("autosmelt", Conditionals.listConditionals(AUTOSMELT_CONDITIONS)),
                new ToolSetOnFireOnHit("fire_on_hit", Conditionals.always(), 6)
        );
        ToolPowerRegistry.register(OrevolutionToolTiers.AETHERSTEEL, OrevolutionConfig.POWERS.aethersteelToolPowers, OrevolutionConfig.POWERS.aethersteelWeaponPowers,
                new ToolMultiPower(List.of(
                        new AethersteelAutosmelt(),
                        new OrevolutionToolPower("aethersteel", Conditionals.always()) // Power is handled in net.bexla.orevolution.events.PlayerSubscriber
                )),
                new ToolMultiPower(List.of(
                        new ToolSetOnFireOnHit("fire_on_hit", Conditionals.always(), 12),
                        new OrevolutionToolPower("aethersteel", Conditionals.always()) // Power is handled in net.bexla.orevolution.events.PlayerSubscriber
                ))
        );
        ToolPowerRegistry.register(OrevolutionToolTiers.LIVINGSTONE, OrevolutionConfig.POWERS.livingstoneToolPowers, OrevolutionConfig.POWERS.livingstoneWeaponPowers,
                new ToolMultiPower(List.of(
                        new ToolRegeneration("regenerates_daylight", Conditionals.isReceivingDayLight(), 1, 100),
                        new ToolIncreaseCropDrops("duplication_crops", Conditionals.always(), 1, 0.4)
                )),
                new ToolMultiPower(List.of(
                        new ToolRegeneration("regenerates_daylight", Conditionals.isReceivingDayLight(), 1, 100),
                        new ToolCauseEffectOnHit("on_hit_effect_chance", "", Conditionals.byChance(0.1), 1, 0, RegMobEffects.PETRIFIED, null)
                ))
        );
        ToolPowerRegistry.register(OrevolutionToolTiers.VERDITE, OrevolutionConfig.POWERS.verditeToolPowers, OrevolutionConfig.POWERS.verditeWeaponPowers,
                new ToolMultiPower(List.of(
                        new ToolRegeneration("regenerates_daylight", Conditionals.isReceivingDayLight(), 1, 80),
                        new ToolIncreaseCropDrops("duplication_crops", Conditionals.always(), 2, 0.6)
                )),
                new ToolMultiPower(List.of(
                        new ToolRegeneration("regenerates_daylight", Conditionals.isReceivingDayLight(), 1, 80),
                        new ToolCauseEffectOnHit("", "attacker_on_hit_effect_chance", Conditionals.byChance(0.2), 2, 0, null, () -> MobEffects.SATURATION)
                ))
        );

        if(ModCompat.isModLoaded(ModCompat.oreganized())) {
            ToolPowerRegistry.register(OItemTiers.ELECTRUM, OrevolutionConfig.MODCOMPAT.electrumToolPowers, OrevolutionConfig.MODCOMPAT.electrumWeaponPowers,
                    new ToolMultiPower(List.of(
                            new ToolAddEffectPerBlockAmount("grant_on_mine", Conditionals.always(), () -> MobEffects.DIG_SPEED, 7, 120, 3),
                            new OrevolutionToolPower("electrum", Conditionals.always())
                    )),
                    new ToolMultiPower(List.of(
                            new ToolAddEffectPerBlockAmount("grant_on_mine", Conditionals.always(), () -> MobEffects.DIG_SPEED, 7, 120, 3),
                            new OrevolutionToolPower("electrum", Conditionals.always())
                    ))
            );
        }

        LOGGER.debug("Registered all tool powers");
    }

    public static void RegisterAll() {
        RegisterSortedTiers();
        RegisterArmorPowers();
        RegisterToolsPowers();
    }
}
