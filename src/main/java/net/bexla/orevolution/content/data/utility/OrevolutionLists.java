package net.bexla.orevolution.content.data.utility;

import net.bexla.orevolution.content.data.Conditionals;
import net.bexla.orevolution.content.data.base.OrevolutionArmorPower;
import net.bexla.orevolution.content.data.base.OrevolutionToolPower;
import net.bexla.orevolution.content.data.powers.armors.ArmorCauseEffectsOnAttacked;
import net.bexla.orevolution.content.data.powers.armors.ArmorCauseEffectsOnHit;
import net.bexla.orevolution.content.data.powers.armors.ArmorGrantEffects;
import net.bexla.orevolution.content.data.powers.armors.ArmorGrantImmunityEffects;
import net.bexla.orevolution.content.data.powers.tools.ToolAvoidDamageOnUse;
import net.bexla.orevolution.content.data.powers.tools.ToolIncreaseDrops;
import net.bexla.orevolution.init.RegItems;
import net.minecraft.tags.FluidTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.item.Item;
import net.minecraftforge.registries.RegistryObject;

import java.util.List;
import java.util.Map;

public class OrevolutionLists {
    public static final Map<TagKey<Item>, RegistryObject<Item>[]> TOOLS = Map.of(
            ItemTags.AXES, new RegistryObject[] { RegItems.TIN_AXE, RegItems.PLATINUM_AXE, RegItems.AETHERSTEEL_AXE, RegItems.TUMBAGA_AXE, RegItems.VERDITE_AXE, RegItems.LIVINGSTONE_AXE },
            ItemTags.PICKAXES, new RegistryObject[] { RegItems.TIN_PICKAXE, RegItems.PLATINUM_PICKAXE, RegItems.AETHERSTEEL_PICKAXE, RegItems.TUMBAGA_PICKAXE, RegItems.VERDITE_PICKAXE, RegItems.LIVINGSTONE_PICKAXE },
            ItemTags.SWORDS, new RegistryObject[] { RegItems.TIN_SWORD, RegItems.PLATINUM_SWORD, RegItems.AETHERSTEEL_SWORD, RegItems.TUMBAGA_SWORD, RegItems.VERDITE_SWORD, RegItems.LIVINGSTONE_SWORD },
            ItemTags.SHOVELS, new RegistryObject[] { RegItems.TIN_SHOVEL, RegItems.PLATINUM_SHOVEL, RegItems.AETHERSTEEL_SHOVEL, RegItems.TUMBAGA_SHOVEL, RegItems.VERDITE_SHOVEL, RegItems.LIVINGSTONE_SHOVEL },
            ItemTags.HOES, new RegistryObject[] { RegItems.TIN_HOE, RegItems.PLATINUM_HOE, RegItems.AETHERSTEEL_HOE, RegItems.TUMBAGA_HOE, RegItems.VERDITE_HOE, RegItems.LIVINGSTONE_HOE }
    );



    public static final List<MobEffect> PLATINUM_TOOL_EFFECTS = List.of(
            MobEffects.POISON,
            MobEffects.WEAKNESS
    );

    public static final List<MobEffect> AETHERSTEEL_TOOL_EFFECTS = List.of(
            MobEffects.POISON,
            MobEffects.WEAKNESS
    );

    public static final List<MobEffect> LIVINGSTONE_ARMOR_GRANTS_IMMUNITY = List.of(
            MobEffects.HUNGER
    );

    public static final List<MobEffect> VERDITE_ARMOR_GRANTS_IMMUNITY = List.of(
            MobEffects.HUNGER,
            MobEffects.POISON
    );

    public static final List<MobEffect> AETHERSTEEL_ARMOR_GRANTS_EFFECTS = List.of(
            MobEffects.FIRE_RESISTANCE,
            MobEffects.HEALTH_BOOST
    );

    public static final List<MobEffect> AETHERSTEEL_ARMOR_ATTACKED_EFFECTS_WEARER = List.of(
            MobEffects.DAMAGE_BOOST,
            MobEffects.REGENERATION
    );

    public static final List<MobEffect> VERDITE_ARMOR_ATTACKED_EFFECTS_WEARER = List.of(
            MobEffects.REGENERATION
    );

    public static final List<MobEffect> AETHERSTEEL_ARMOR_ATTACKED_EFFECTS_ATTACKER = List.of(
            MobEffects.MOVEMENT_SLOWDOWN
    );

    public static final List<MobEffect> REINFORCED_ARMOR_GRANTS_EFFECTS = List.of(
            MobEffects.FIRE_RESISTANCE,
            MobEffects.HEALTH_BOOST
    );

    public static final List<MobEffect> NETHERITE_ARMOR_ATTACKED_EFFECTS = List.of(
            MobEffects.WITHER
    );

    public static final List<MobEffect> DIAMOND_ARMOR_EFFECTS = List.of(
            MobEffects.ABSORPTION
    );

    public static final List<MobEffect> PLATINUM_ARMOR_EFFECTS = List.of(
            MobEffects.DAMAGE_BOOST
    );

    public static final List<MobEffect> GOLD_ARMOR_EFFECTS = List.of(
            MobEffects.MOVEMENT_SPEED,
            MobEffects.DIG_SPEED,
            MobEffects.LUCK
    );

    public static final List<MobEffect> IRON_ARMOR_EFFECTS = List.of(
            MobEffects.DAMAGE_RESISTANCE
    );

    public static final List<OrevolutionToolPower> AETHERSTEEL_TOOL_POWERS = List.of(
            new ToolIncreaseDrops("aethersteel_duplication", Conditionals.always(), 1, 0.5),
            new ToolAvoidDamageOnUse("aethersteel_avoid_damage", Conditionals.byChance(0.2))
    );

    public static final List<OrevolutionArmorPower> REINFORCED_ARMOR_POWERS = List.of(
            new ArmorGrantEffects("reinforced_grants", Conditionals.not(Conditionals.isSubmergedInLiquid(FluidTags.LAVA)), 20, 1, REINFORCED_ARMOR_GRANTS_EFFECTS),
            new ArmorCauseEffectsOnHit("netherite_on_hit", Conditionals.isCurrentHPPercentage(0.5f), 20, 0, OrevolutionLists.NETHERITE_ARMOR_ATTACKED_EFFECTS, null)
    );

    public static final List<OrevolutionArmorPower> AETHERSTEEL_ARMOR_POWERS = List.of(
            new ArmorGrantEffects("aethersteel_grants", Conditionals.always(), 20, 1, AETHERSTEEL_ARMOR_GRANTS_EFFECTS),
            new ArmorCauseEffectsOnAttacked("aethersteel_on_attacked", Conditionals.always(), 200, 0, AETHERSTEEL_ARMOR_ATTACKED_EFFECTS_WEARER, AETHERSTEEL_ARMOR_ATTACKED_EFFECTS_ATTACKER)
    );

    public static final List<OrevolutionArmorPower> VERDITE_ARMOR_POWERS = List.of(
            new ArmorGrantImmunityEffects("verdite_grants", Conditionals.isReceivingDayLight(), VERDITE_ARMOR_GRANTS_IMMUNITY),
            new ArmorCauseEffectsOnHit("verdite_on_hit", Conditionals.isReceivingDayLight(), 200, 0, VERDITE_ARMOR_ATTACKED_EFFECTS_WEARER, null)
    );
}
