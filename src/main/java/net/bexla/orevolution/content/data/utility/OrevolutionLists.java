package net.bexla.orevolution.content.data.utility;

import net.bexla.orevolution.content.data.base.OrevolutionArmorPower;
import net.bexla.orevolution.content.data.base.OrevolutionToolPower;
import net.bexla.orevolution.content.data.powers.armors.ArmorCauseEffectsOnAttacked;
import net.bexla.orevolution.content.data.powers.armors.ArmorGrantEffects;
import net.bexla.orevolution.content.data.powers.tools.ToolAvoidDamageOnUse;
import net.bexla.orevolution.content.data.powers.tools.ToolIncreaseDrops;
import net.bexla.orevolution.content.init.RegItems;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;

import java.util.List;
import java.util.function.Supplier;

public class OrevolutionLists {
    public static final List<Item> IRON_ARMOR_SET = List.of(
            Items.IRON_HELMET,
            Items.IRON_CHESTPLATE,
            Items.IRON_LEGGINGS,
            Items.IRON_BOOTS
    );

    public static final List<Supplier<Item>> PLATINUM_ARMOR_SET = List.of(
            RegItems.PLATINUM_HELMET,
            RegItems.PLATINUM_CHESTPLATE,
            RegItems.PLATINUM_LEGGINGS,
            RegItems.PLATINUM_BOOTS
    );

    public static final List<Supplier<Item>> AETHERSTEEL_ARMOR_SET = List.of(
            RegItems.AETHERSTEEL_HELMET,
            RegItems.AETHERSTEEL_CHESTPLATE,
            RegItems.AETHERSTEEL_LEGGINGS,
            RegItems.AETHERSTEEL_BOOTS
    );

    public static final List<MobEffect> PLATINUM_TOOL_EFFECTS = List.of(
            MobEffects.POISON,
            MobEffects.WEAKNESS
    );

    public static final List<MobEffect> AETHERSTEEL_TOOL_EFFECTS = List.of(
            MobEffects.POISON,
            MobEffects.WEAKNESS
    );

    public static final List<MobEffect> AETHERSTEEL_ARMOR_GRANTS_EFFECTS = List.of(
            MobEffects.FIRE_RESISTANCE,
            MobEffects.HEALTH_BOOST
    );

    public static final List<MobEffect> AETHERSTEEL_ARMOR_ATTACKED_EFFECTS_WEARER = List.of(
            MobEffects.DAMAGE_BOOST,
            MobEffects.REGENERATION
    );

    public static final List<MobEffect> AETHERSTEEL_ARMOR_ATTACKED_EFFECTS_ATTACKER = List.of(
            MobEffects.MOVEMENT_SLOWDOWN
    );

    public static final List<MobEffect> PLATINUM_ARMOR_EFFECTS = List.of(
            MobEffects.DAMAGE_BOOST
    );

    public static final List<MobEffect> IRON_ARMOR_EFFECTS = List.of(
            MobEffects.DAMAGE_RESISTANCE
    );

    public static final List<OrevolutionToolPower> AETHERSTEEL_TOOL_POWERS = List.of(
            new ToolIncreaseDrops("aethersteel_duplication", 1, 0.5),
            new ToolAvoidDamageOnUse("aethersteel_avoid_damage", 0.2)
    );

    public static final List<OrevolutionArmorPower> AETHERSTEEL_ARMOR_POWERS = List.of(
            new ArmorGrantEffects("aethersteel_grants", 20, 1, AETHERSTEEL_ARMOR_GRANTS_EFFECTS),
            new ArmorCauseEffectsOnAttacked("aethersteel_on_attacked", 200, 0, AETHERSTEEL_ARMOR_ATTACKED_EFFECTS_WEARER, AETHERSTEEL_ARMOR_ATTACKED_EFFECTS_ATTACKER)
    );
}
