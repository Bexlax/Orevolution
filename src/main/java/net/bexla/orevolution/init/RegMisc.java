package net.bexla.orevolution.init;

import com.mojang.logging.LogUtils;
import com.teamabnormals.caverns_and_chasms.core.other.CCTiers;
import galena.oreganized.index.OItemTiers;
import net.bexla.orevolution.content.data.Conditionals;
import net.bexla.orevolution.content.data.OrevolutionItemModifiers;
import net.bexla.orevolution.content.data.powers.armors.*;
import net.bexla.orevolution.content.data.powers.tools.*;
import net.bexla.orevolution.content.data.utility.*;
import net.bexla.orevolution.content.types.ArmorPowerRegistry;
import net.bexla.orevolution.content.types.PropertiesModifierRegistry;
import net.bexla.orevolution.content.types.TierProgressRegistry;
import net.bexla.orevolution.content.types.ToolPowerRegistry;
import net.bexla.orevolution.content.types.base.OrevolutionToolPower;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.MobType;
import net.minecraft.world.item.ArmorMaterials;
import net.minecraft.world.item.Tiers;
import net.minecraftforge.common.Tags;
import org.slf4j.Logger;

import static net.bexla.orevolution.content.data.utility.OrevolutionUtils.modLocat;

public class RegMisc {
    private static final Logger LOGGER = LogUtils.getLogger();

    public static void RegisterSortedTiers() {
        TierProgressRegistry.registerTier(OrevolutionToolTiers.TIN, modLocat("tin"), Tiers.STONE, Tiers.IRON, OrevolutionTags.Blocks.TinTiered);
        TierProgressRegistry.registerTier(OrevolutionToolTiers.LIVINGSTONE, modLocat("livingstone"), Tiers.STONE, Tiers.IRON, OrevolutionTags.Blocks.TinTiered);
        TierProgressRegistry.registerTier(OrevolutionToolTiers.VERDITE, modLocat("verdite"), Tiers.IRON, Tiers.DIAMOND, OrevolutionTags.Blocks.PlatTiered);
        TierProgressRegistry.registerTier(OrevolutionToolTiers.PLATINUM, modLocat("platinum"), Tiers.IRON, Tiers.DIAMOND, OrevolutionTags.Blocks.PlatTiered);
        TierProgressRegistry.registerTier(OrevolutionToolTiers.AETHERSTEEL, modLocat("aethersteel"), Tiers.NETHERITE, null, OrevolutionTags.Blocks.AethersteelTiered);

        TierProgressRegistry.registerTierNoSort(Tiers.WOOD, Tags.Blocks.NEEDS_WOOD_TOOL);
        TierProgressRegistry.registerTierNoSort(Tiers.STONE, BlockTags.NEEDS_STONE_TOOL);
        TierProgressRegistry.registerTierNoSort(Tiers.GOLD, Tags.Blocks.NEEDS_GOLD_TOOL);
        TierProgressRegistry.registerTierNoSort(Tiers.IRON, BlockTags.NEEDS_IRON_TOOL);
        TierProgressRegistry.registerTierNoSort(Tiers.DIAMOND, BlockTags.NEEDS_IRON_TOOL);
        TierProgressRegistry.registerTierNoSort(Tiers.NETHERITE, Tags.Blocks.NEEDS_NETHERITE_TOOL);



        LOGGER.debug("Sorted all tiers + registered their blocktags");
    }

    public static void RegisterArmorPowers() {
        ArmorPowerRegistry.register(ArmorMaterials.GOLD,
                new ArmorGrantMultipleEffects("armor_wearer_grants", Conditionals.always(), 20, 1, OrevolutionLists.GOLD_ARMOR_EFFECTS));
        ArmorPowerRegistry.register(OrevolutionArmorTiers.PLATINUM,
                new ArmorCauseEffectOnAttacked("armor_wearer_on_attacked", Conditionals.always(), 100, 1, MobEffects.DAMAGE_BOOST, null));
        ArmorPowerRegistry.register(ArmorMaterials.NETHERITE,
                new ArmorCauseEffectOnHit("netherite_armor", Conditionals.isCurrentHPPercentage(0.5f), 20, 0, MobEffects.WITHER, null));
        ArmorPowerRegistry.register(OrevolutionArmorTiers.REINFORCED_NETHERITE,
                new ArmorMultiPower(OrevolutionLists.REINFORCED_ARMOR_POWERS));
        ArmorPowerRegistry.register(OrevolutionArmorTiers.AETHERSTEEL,
                new ArmorMultiPower(OrevolutionLists.AETHERSTEEL_ARMOR_POWERS));

        ArmorPowerRegistry.register(OrevolutionArmorTiers.LIVINGSTONE,
                new ArmorGrantImmunityEffects("armor_wearer_immunity", Conditionals.isReceivingDayLight(), OrevolutionLists.LIVINGSTONE_ARMOR_GRANTS_IMMUNITY));
        ArmorPowerRegistry.register(OrevolutionArmorTiers.VERDITE,
                new ArmorMultiPower(OrevolutionLists.VERDITE_ARMOR_POWERS));

        LOGGER.debug("Registered all armor powers");
    }

    public static void RegisterToolsPowers() {
        ToolPowerRegistry.registerTier(OrevolutionToolTiers.TIN,
                new ToolIncreaseDrops("duplication", Conditionals.always(), 1, 2),
                new ToolCauseEffectOnHit("on_hit_effect_chance", Conditionals.byChance(0.3), 120, 0, MobEffects.POISON, null)
        );
        ToolPowerRegistry.registerTier(Tiers.IRON,
                new ToolMineBlockFaster("iron_tool_tooltip", Conditionals.isBlockstateTaggedAs(Tags.Blocks.NEEDS_WOOD_TOOL), 3F),
                new ToolCauseEffectOnHit("on_hit_effect_chance", Conditionals.byChance(0.3), 120, 0, MobEffects.POISON, null)
        );
        ToolPowerRegistry.registerTier(Tiers.GOLD,
                new ToolAvoidDamageOnUse("gold_tool_tooltip", Conditionals.and(Conditionals.toolHasAnyEnchantment(), Conditionals.byChance(0.65))),
                new ToolCauseEffectOnHit("on_hit_effect_chance", Conditionals.byChance(0.3), 120, 0, MobEffects.POISON, null)
        );
        ToolPowerRegistry.registerTier(OrevolutionToolTiers.PLATINUM,
                new ToolAvoidDamageOnUse("avoid_damage", Conditionals.byChance(0.25)),
                new ToolCauseMultipleEffectsOnHit("undead_on_hit", Conditionals.isTargetMobType(MobType.UNDEAD), 160, 0, OrevolutionLists.PLATINUM_TOOL_EFFECTS, null)
        );
        ToolPowerRegistry.registerTier(OrevolutionToolTiers.AETHERSTEEL,
                new ToolAvoidDamageOnUse("avoid_damage", Conditionals.byChance(0.055)),
                new ToolCauseEffectOnHit("attacker_on_hit_effect", Conditionals.byChance(0.7), 80, 0, MobEffects.REGENERATION, null)
        );
        ToolPowerRegistry.registerTier(OrevolutionToolTiers.LIVINGSTONE,
                new ToolIncreaseCropDrops("livingstone_crops", Conditionals.byChance(0.75), 1),
                new ToolCauseEffectOnHit("on_hit_effect_chance", Conditionals.byChance(0.35), 1, 0, null, MobEffects.SATURATION)
        );
        ToolPowerRegistry.registerTier(OrevolutionToolTiers.VERDITE,
                new ToolIncreaseCropDrops("verdite_crops", Conditionals.byChance(0.8), 2),
                new ToolCauseEffectOnHit("on_hit_effect_chance", Conditionals.byChance(0.4), 2, 0, null, MobEffects.SATURATION)
        );
        ToolPowerRegistry.registerTier(Tiers.DIAMOND,
                new ToolIncreaseDrops("tin_duplication", Conditionals.always(), 1, 1),
                new ToolCauseEffectOnHit("tin_on_hit", Conditionals.byChance(0.3), 120, 0, MobEffects.POISON, null)
        );
        ToolPowerRegistry.registerTier(Tiers.NETHERITE,
                new ToolIncreaseDrops("tin_duplication", Conditionals.always(), 1, 1),
                new ToolCauseEffectOnHit("tin_on_hit", Conditionals.byChance(0.3), 120, 0, MobEffects.POISON, null)
        );

        if(OrevolutionUtils.ModCompat.isModLoaded(OrevolutionUtils.ModCompat.OREGANIZED)) {
            // I've also removed the kinetic damage effect to all tools except the sword via tags.
            ToolPowerRegistry.registerTier(OItemTiers.ELECTRUM,
                    new ToolAddEffectPerBlockAmount("grant_on_mine", Conditionals.always(), MobEffects.DIG_SPEED, 6, 120, 3),
                    new OrevolutionToolPower("tooltip.orevolution.electrum", Conditionals.always()) // doesn't do anything
            );
        }

        if (OrevolutionUtils.ModCompat.isModLoaded(OrevolutionUtils.ModCompat.CAC)) {
            // silver has the exact same powers as platinum.
            ToolPowerRegistry.registerTier(CCTiers.CCItemTiers.SILVER,
                    new ToolAvoidDamageOnUse("avoid_damage", Conditionals.byChance(0.25)),
                    new ToolCauseMultipleEffectsOnHit("undead_on_hit", Conditionals.isTargetMobType(MobType.UNDEAD), 160, 0, OrevolutionLists.PLATINUM_TOOL_EFFECTS, null)
            );
            ToolPowerRegistry.registerTier(CCTiers.CCItemTiers.NECROMIUM,
                    new ToolIncreaseDrops("copper_duplication", Conditionals.always(), 1, 1),
                    new ToolCauseEffectOnHit("on_hit_effect_chance", Conditionals.byChance(0.60), 140, 0, null, MobEffects.REGENERATION)
            );
        }

        LOGGER.debug("Registered all tool powers");
    }

    public static void RegisterModifiers() {
        PropertiesModifierRegistry.register(new OrevolutionItemModifiers());

        LOGGER.debug("Registered OrevolutionItemModifiers");
    }

    public static void RegisterAll() {
        RegisterSortedTiers();
        RegisterArmorPowers();
        RegisterToolsPowers();
        RegisterModifiers();
    }
}
