package net.bexla.orevolution.content.types;

import com.mojang.logging.LogUtils;
import net.bexla.orevolution.content.data.base.OrevolutionToolMaterial;
import net.bexla.orevolution.content.data.interfaces.ToolPower;
import net.minecraft.world.item.Tier;
import org.slf4j.Logger;

import java.util.HashMap;
import java.util.Map;

public class ExternalToolsPowerRegistry {
    private static final Logger LOGGER = LogUtils.getLogger();
    private static final Map<Tier, ToolPower> TOOL_POWERS = new HashMap<>();
    private static final Map<Tier, ToolPower> SWORD_POWERS = new HashMap<>();

    public static void register(OrevolutionToolMaterial material) {
        if (TOOL_POWERS.containsKey(material.getTier())) {
            LOGGER.error("Found duplicate registration for tier: {}, stopping duplicate registration", material.getTier());
            return;
        }

        TOOL_POWERS.put(material.getTier(), material.getToolPowers());
        SWORD_POWERS.put(material.getTier(), material.getToolPowers());
    }

    public static ToolPower getToolPower(Tier tier) {
        return TOOL_POWERS.get(tier);
    }

    public static ToolPower getSwordPower(Tier tier) {
        return SWORD_POWERS.get(tier);
    }
}