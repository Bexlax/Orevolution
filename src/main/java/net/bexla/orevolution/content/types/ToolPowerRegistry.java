package net.bexla.orevolution.content.types;

import com.mojang.logging.LogUtils;
import net.bexla.orevolution.content.types.base.interfaces.ToolPower;
import net.minecraft.world.item.Tier;
import org.slf4j.Logger;

import java.util.HashMap;
import java.util.Map;

public class ToolPowerRegistry {
    private static final Logger LOGGER = LogUtils.getLogger();

    private static final Map<Tier, ToolPowerPair> tierTagMap = new HashMap<>();

    public record ToolPowerPair(ToolPower toolPower, ToolPower swordPower) {}

    /**
     * Registers a ToolPower and SwordPower for a given Tier.
     *
     * @param tier       The material tier to associate with the powers.
     * @param toolPower  The ToolPower to register for tools of this tier.
     * @param swordPower The ToolPower to register for swords of this tier.
     */
    public static void registerTier(Tier tier, ToolPower toolPower, ToolPower swordPower) {
        tierTagMap.put(tier, new ToolPowerPair(toolPower, swordPower));
        LOGGER.debug("Registered tool power for: {}", tier);
    }

    public static ToolPower getToolPowerForTier(Tier tier) {
        ToolPowerPair pair = tierTagMap.get(tier);
        return pair != null ? pair.toolPower() : ToolPower.EMPTY;
    }

    public static ToolPower getSwordPowerForTier(Tier tier) {
        ToolPowerPair pair = tierTagMap.get(tier);
        return pair != null ? pair.swordPower() : ToolPower.EMPTY;
    }
}
