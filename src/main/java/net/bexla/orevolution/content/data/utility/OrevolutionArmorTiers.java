package net.bexla.orevolution.content.data.utility;

import com.teamabnormals.blueprint.core.api.BlueprintArmorMaterial;
import net.bexla.orevolution.content.init.RegItems;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.item.*;
import net.minecraft.world.item.crafting.Ingredient;

import static net.bexla.orevolution.content.data.utility.OrevolutionHelperMethods.modLocat;

public class OrevolutionArmorTiers {
    public static final ArmorMaterial PLATINUM = new BlueprintArmorMaterial(modLocat("platinum"),
            12,
            new int[]{2, 5, 6, 2},
            19,
            () -> SoundEvents.ARMOR_EQUIP_GENERIC,
            0.5F,
            0F,
            () -> Ingredient.of(RegItems.PLATINUM_INGOT.get())
    );

    public static final ArmorMaterial AETHERSTEEL = new BlueprintArmorMaterial(modLocat("aethersteel"),
            41,
            new int[]{4, 7, 8, 3},
            19,
            () -> SoundEvents.ARMOR_EQUIP_GENERIC,
            4.5F,
            0.2F,
            () -> Ingredient.of(RegItems.AETHERSTEEL_INGOT.get())
    );
}
