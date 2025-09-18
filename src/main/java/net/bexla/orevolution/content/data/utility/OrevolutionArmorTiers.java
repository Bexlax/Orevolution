package net.bexla.orevolution.content.data.utility;

import com.teamabnormals.blueprint.core.api.BlueprintArmorMaterial;
import net.bexla.orevolution.init.RegItems;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;

import static net.bexla.orevolution.content.data.utility.OrevolutionUtils.modLocat;

public class OrevolutionArmorTiers {
    public static final ArmorMaterial PLATINUM = new BlueprintArmorMaterial(modLocat("platinum"),
            21,
            new int[]{2, 5, 6, 2},
            19,
            () -> SoundEvents.ARMOR_EQUIP_GENERIC,
            0.5F,
            0F,
            () -> Ingredient.of(RegItems.PLATINUM_INGOT.get())
    );

    public static final ArmorMaterial REINFORCED_NETHERITE = new BlueprintArmorMaterial(modLocat("reinforced_netherite"),
            39,
            new int[]{3, 6, 8, 3},
            19,
            () -> SoundEvents.ARMOR_EQUIP_NETHERITE,
            4.5F,
            0.2F,
            () -> Ingredient.of(Items.NETHERITE_INGOT)
    );

    public static final ArmorMaterial AETHERSTEEL = new BlueprintArmorMaterial(modLocat("aethersteel"),
            45,
            new int[]{4, 7, 8, 4},
            19,
            () -> SoundEvents.ARMOR_EQUIP_NETHERITE,
            4.5F,
            0.2F,
            () -> Ingredient.of(RegItems.AETHERSTEEL_INGOT.get())
    );

    public static final ArmorMaterial LIVINGSTONE = new BlueprintArmorMaterial(modLocat("livingstone"),
            13,
            new int[]{1, 3, 5, 1},
            7,
            () -> SoundEvents.ARMOR_EQUIP_GENERIC,
            0.5F,
            0F,
            () -> Ingredient.of(RegItems.LIVINGSTONE_SHARD.get())
    );

    public static final ArmorMaterial VERDITE = new BlueprintArmorMaterial(modLocat("verdite"),
            11,
            new int[]{2, 5, 6, 2},
            7,
            () -> SoundEvents.ARMOR_EQUIP_GENERIC,
            0.05F,
            0F,
            () -> Ingredient.of(RegItems.LIVINGSTONE_SHARD.get())
    );

    public static final ArmorMaterial BRONZE_CROWN = new BlueprintArmorMaterial(modLocat("bronze"),
            14,
            new int[]{1, 0, 0, 0},
            12,
            () -> SoundEvents.ARMOR_EQUIP_GOLD,
            0F,
            0F,
            () -> Ingredient.of(RegItems.BRONZE_ALLOY.get())
    );
}
