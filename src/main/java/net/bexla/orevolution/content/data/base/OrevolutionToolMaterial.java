package net.bexla.orevolution.content.data.base;

import net.bexla.orevolution.content.data.interfaces.ToolPower;
import net.minecraft.world.item.Tier;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public record OrevolutionToolMaterial(
        Tier tier,          // Vanilla/Modded Tier: mining level, speed, uses
        ToolPower toolPowers,          // Tool Powers attached to this material
        ToolPower swordPowers         // Sword Powers attached to this material
) {
    // Helper methods
    public Tier getTier() {
        return this.tier; // i tried so hard
    }

    public ToolPower getToolPowers() {
        return this.toolPowers; // and got so far
    }

    public ToolPower getSwordPowers() {
        return this.swordPowers; // but in the end, it doesn't even matter
    }
}