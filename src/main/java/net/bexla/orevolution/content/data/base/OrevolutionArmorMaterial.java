package net.bexla.orevolution.content.data.base;

import net.bexla.orevolution.content.data.interfaces.ArmorPower;
import net.minecraft.world.item.ArmorMaterial;

// basically a copy of OrevolutionToolMaterial
public record OrevolutionArmorMaterial(
        ArmorMaterial material,
        ArmorPower armorPowers
) {
    // Helper methods
    public ArmorMaterial getTier() {
        return this.material; // Somebody wake me from this nightmare
    }

    public ArmorPower getArmorPowers() {
        return this.armorPowers; // i can't escape this hell
    }
}