package net.bexla.orevolution.content.data.base;

import net.bexla.orevolution.content.data.interfaces.ArmorPower;
import net.bexla.orevolution.content.data.interfaces.ToolPower;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Tier;

import java.util.List;
import java.util.function.Supplier;

// basically a copy of OrevolutionToolMaterial
public record OrevolutionArmorMaterial(
        List<Supplier<Item>> armorSetList,
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

    public List<Supplier<Item>> getSetList() {
        return this.armorSetList; // i can't escape this hell
    }
}