package net.bexla.orevolution.content.types;

import net.bexla.orevolution.content.data.base.OrevolutionToolMaterial;
import net.bexla.orevolution.content.data.interfaces.ToolPower;
import net.minecraft.world.item.Tier;

import java.util.HashMap;
import java.util.Map;

public class ExternalToolsPowerRegistry {
    private static final Map<Tier, ToolPower> TOOL_POWERS = new HashMap<>();
    private static final Map<Tier, ToolPower> SWORD_POWERS = new HashMap<>();

    public static void register(OrevolutionToolMaterial mat) {
        TOOL_POWERS.put(mat.getTier(), mat.getToolPowers());
        SWORD_POWERS.put(mat.getTier(), mat.getSwordPowers());
    }

    public static ToolPower getToolPower(Tier tier) {
        return TOOL_POWERS.get(tier);
    }

    public static ToolPower getSwordPower(Tier tier) {
        return SWORD_POWERS.get(tier);
    }
}