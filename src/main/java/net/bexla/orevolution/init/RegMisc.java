package net.bexla.orevolution.init;

import com.mojang.logging.LogUtils;
import com.teamabnormals.caverns_and_chasms.core.other.CCTiers;
import galena.oreganized.index.OItemTiers;
import net.bexla.orevolution.compatibility.ModCompat;
import net.bexla.orevolution.content.data.Conditionals;
import net.bexla.orevolution.content.data.OrevolutionArmorTiers;
import net.bexla.orevolution.content.data.OrevolutionToolTiers;
import net.bexla.orevolution.content.data.powers.armors.*;
import net.bexla.orevolution.content.data.powers.tools.*;
import net.bexla.orevolution.content.data.utility.OrevolutionLists;
import net.bexla.orevolution.content.data.utility.OrevolutionTags;
import net.bexla.orevolution.content.types.ArmorPowerRegistry;
import net.bexla.orevolution.content.types.OrevolutionToolPower;
import net.bexla.orevolution.content.types.TierProgressRegistry;
import net.bexla.orevolution.content.types.ToolPowerRegistry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.MobType;
import net.minecraft.world.item.ArmorMaterials;
import net.minecraft.world.item.Tiers;
import org.slf4j.Logger;

import java.util.List;

import static net.bexla.orevolution.content.data.utility.OrevolutionUtils.Conditions.IronConditionals;
import static net.bexla.orevolution.content.data.utility.OrevolutionUtils.modLocat;

public class RegMisc {
    private static final Logger LOGGER = LogUtils.getLogger();

    private static boolean loadOrevolutionTiers = true;

    /**
     * Allows external mods to enable/disable vanilla tier registration before Orevolution initializes.
     */
    public static void setLoadOrevolutionTiers(boolean shouldLoad) {
        loadOrevolutionTiers = shouldLoad;
    }

    public static void RegisterSortedTiers() {
        if(!loadOrevolutionTiers) return;

        var wood = new ResourceLocation("wood");
        var stone = new ResourceLocation("stone");
        var iron = new ResourceLocation("iron");
        var diamond = new ResourceLocation("diamond");
        var netherite = new ResourceLocation("netherite");

        TierProgressRegistry.registerTier(Tiers.WOOD, wood,
                List.of(), List.of(), OrevolutionTags.Blocks.woodTiered);

        TierProgressRegistry.registerTier(Tiers.STONE, stone,
                List.of(wood), List.of(iron), OrevolutionTags.Blocks.stoneTiered);

        TierProgressRegistry.registerTier(Tiers.IRON, iron,
                List.of(stone), List.of(diamond), OrevolutionTags.Blocks.ironTiered);

        TierProgressRegistry.registerTier(Tiers.DIAMOND, diamond,
                List.of(iron), List.of(netherite), OrevolutionTags.Blocks.diamondTiered);

        TierProgressRegistry.registerTier(Tiers.NETHERITE, netherite,
                List.of(diamond), List.of(), OrevolutionTags.Blocks.netheriteTiered);

        TierProgressRegistry.registerTier(OrevolutionToolTiers.TIN, modLocat("tin"), List.of(Tiers.STONE), List.of(Tiers.IRON), OrevolutionTags.Blocks.tinTiered);
        TierProgressRegistry.registerTier(OrevolutionToolTiers.PLATINUM, modLocat("platinum"), List.of(Tiers.IRON), List.of(Tiers.DIAMOND), OrevolutionTags.Blocks.platTiered);
        TierProgressRegistry.registerTier(OrevolutionToolTiers.AETHERSTEEL, modLocat("aethersteel"), List.of(Tiers.NETHERITE), List.of(), OrevolutionTags.Blocks.aethersteelTiered);

        TierProgressRegistry.registerSecondaryTier(OrevolutionToolTiers.LIVINGSTONE, OrevolutionToolTiers.TIN);
        TierProgressRegistry.registerSecondaryTier(OrevolutionToolTiers.VERDITE, OrevolutionToolTiers.PLATINUM);
        TierProgressRegistry.registerSecondaryTier(Tiers.GOLD, Tiers.IRON);
        TierProgressRegistry.registerSecondaryTier(OrevolutionToolTiers.STEEL, OrevolutionToolTiers.PLATINUM);

        LOGGER.info("Sorted all tiers");
    }

    public static void RegisterArmorPowers() {
        ArmorPowerRegistry.register(ArmorMaterials.CHAIN,
                new ArmorGrantEffects("armor_wearer_grants", Conditionals.always(), 20, 1, OrevolutionLists.GOLD_ARMOR_EFFECTS));
        ArmorPowerRegistry.register(ArmorMaterials.GOLD,
                new ArmorGrantEffects("armor_wearer_grants", Conditionals.always(), 20, 1, OrevolutionLists.GOLD_ARMOR_EFFECTS));
        ArmorPowerRegistry.register(OrevolutionArmorTiers.PLATINUM,
                new ArmorCauseEffectsOnAttacked("", "armor_wearer_on_attacked_wearer", Conditionals.always(), 100, 1, () -> MobEffects.DAMAGE_BOOST, null));
        ArmorPowerRegistry.register(ArmorMaterials.NETHERITE,
                new ArmorCauseEffectsOnHit("netherite_armor", "", Conditionals.isCurrentHPPercentage(0.5f), 20, 0, () -> MobEffects.WITHER, null));
        ArmorPowerRegistry.register(OrevolutionArmorTiers.REINFORCED_NETHERITE,
                new ArmorMultiPower(OrevolutionLists.REINFORCED_ARMOR_POWERS));
        ArmorPowerRegistry.register(OrevolutionArmorTiers.AETHERSTEEL,
                new ArmorMultiPower(OrevolutionLists.AETHERSTEEL_ARMOR_POWERS));

        ArmorPowerRegistry.register(OrevolutionArmorTiers.LIVINGSTONE,
                new ArmorGrantImmunityEffects("armor_immunity_daylight", Conditionals.isReceivingDayLight(), () -> MobEffects.HUNGER));
        ArmorPowerRegistry.register(OrevolutionArmorTiers.VERDITE,
                new ArmorMultiPower(OrevolutionLists.VERDITE_ARMOR_POWERS));

        ArmorPowerRegistry.registerItem(RegItems.BRONZE_CROWN,
                new ArmorGrantImmunityEffects("armor_immunity", Conditionals.always(), () -> MobEffects.HUNGER));

        ArmorPowerRegistry.registerItem(RegItems.BRONZE_CROWN_REDSTONE,
                new ArmorGrantEffects("armor_grants", Conditionals.always(), 20, 0, OrevolutionLists.BRONZE_REDSTONE_EFFECTS));

        ArmorPowerRegistry.registerItem(RegItems.BRONZE_CROWN_DIAMOND,
                new ArmorCauseEffectsOnAttacked("", "armor_wearer_on_attacked_wearer", Conditionals.always(), 140, 0, null, () -> MobEffects.REGENERATION));

        ArmorPowerRegistry.registerItem(RegItems.BRONZE_CROWN_EMERALD,
                new ArmorGrantEffects("armor_immunity", Conditionals.always(), 20, 0, () -> MobEffects.HERO_OF_THE_VILLAGE));

        ArmorPowerRegistry.registerItem(RegItems.BRONZE_CROWN_LAPIS,
                new ArmorGrantEffects("armor_grants", Conditionals.always(), 30, 0, () -> MobEffects.NIGHT_VISION));

        LOGGER.debug("Registered all armor powers");
    }

    public static void RegisterToolsPowers() {
        ToolPowerRegistry.registerTier(OrevolutionToolTiers.TIN,
                new ToolIncreaseDrops("duplication", Conditionals.always(), 1, 0.3, 1),
                new ToolCauseEffectOnHit("on_hit_effect_chance", "", Conditionals.byChance(0.3), 120, 0, () -> MobEffects.POISON, null)
        );
        ToolPowerRegistry.registerTier(Tiers.IRON,
                new ToolChangeBreakSpeed("iron_tool_tooltip", IronConditionals, 3F),
                new ToolIncreaseMobLoot("increase_loot", Conditionals.always(), 1.5F, 0.4)
        );
        ToolPowerRegistry.registerTier(Tiers.GOLD,
                new ToolChangeDurability("gold_tool_tooltip", Conditionals.toolHasAnyEnchantment(), 160),
                new ToolCauseEffectOnHit("on_hit_effect", "", Conditionals.always(), 120, 0, () -> MobEffects.WEAKNESS, null)
        );
        ToolPowerRegistry.registerTier(OrevolutionToolTiers.STEEL,
                new ToolMultiBreaking("multi_break", Conditionals.always()),
                new ToolCauseEffectOnHit("on_hit_effect_chance", "", Conditionals.always(), 180, 0, RegMobEffects.CRUSHED, null)
        );
        ToolPowerRegistry.registerTier(OrevolutionToolTiers.PLATINUM,
                new ToolAvoidDamageOnUse("avoid_damage", Conditionals.byChance(0.25)),
                new ToolCauseMultipleEffectsOnHit("undead_on_hit", "", Conditionals.isTargetMobType(MobType.UNDEAD), 160, 0, OrevolutionLists.PLATINUM_TOOL_EFFECTS, null)
        );
        ToolPowerRegistry.registerTier(OrevolutionToolTiers.AETHERSTEEL,
                new ToolAvoidDamageOnUse("avoid_damage", Conditionals.byChance(0.05)),
                new ToolCauseEffectOnHit("attacker_on_hit_effect", "", Conditionals.byChance(0.7), 80, 0, () -> MobEffects.REGENERATION, null)
        );
        ToolPowerRegistry.registerTier(OrevolutionToolTiers.LIVINGSTONE,
                new ToolIncreaseCropDrops("duplication_crops", Conditionals.always(), 1, 0.4),
                new ToolCauseEffectOnHit("", "on_hit_effect_chance", Conditionals.byChance(0.1), 1, 0, null, () -> MobEffects.SATURATION)
        );
        ToolPowerRegistry.registerTier(OrevolutionToolTiers.VERDITE,
                new ToolIncreaseCropDrops("duplication_crops", Conditionals.always(), 2, 0.6),
                new ToolCauseEffectOnHit("", "on_hit_effect_chance", Conditionals.byChance(0.2), 2, 0, null, () -> MobEffects.SATURATION)
        );

        if(ModCompat.isModLoaded(ModCompat.OREGANIZED)) {
            // I've also removed the kinetic damage effect to all tools except the sword via tags.
            ToolPowerRegistry.registerTier(OItemTiers.ELECTRUM,
                    new ToolAddEffectPerBlockAmount("grant_on_mine", Conditionals.always(), () -> MobEffects.DIG_SPEED, 6, 120, 3),
                    new OrevolutionToolPower("electrum", Conditionals.always()) // doesn't do anything, only adds a tooltip
            );
        }

        if (ModCompat.isModLoaded(ModCompat.CAC)) {
            // silver has the exact same powers as platinum.
            ToolPowerRegistry.registerTier(CCTiers.CCItemTiers.SILVER,
                    new ToolAvoidDamageOnUse("avoid_damage", Conditionals.byChance(0.25)),
                    new ToolCauseMultipleEffectsOnHit("undead_on_hit", "", Conditionals.isTargetMobType(MobType.UNDEAD), 160, 0, OrevolutionLists.PLATINUM_TOOL_EFFECTS, null)
            );
            ToolPowerRegistry.registerTier(CCTiers.CCItemTiers.NECROMIUM,
                    new ToolChangeDamage("copper_duplication", Conditionals.always(), 2.0F),
                    new ToolCauseEffectOnHit("on_hit_effect_chance", "", Conditionals.isTargetHPPercentage(0.25F), 140, 0, null, () -> MobEffects.REGENERATION)
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
