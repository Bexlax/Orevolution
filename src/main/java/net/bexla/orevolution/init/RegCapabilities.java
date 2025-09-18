package net.bexla.orevolution.init;

import net.bexla.orevolution.content.capabilities.OrevolutionPlayerData;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.common.capabilities.CapabilityToken;

public class RegCapabilities {
    public static final Capability<OrevolutionPlayerData> PLAYER_DATA = CapabilityManager.get(new CapabilityToken<>(){});
}
