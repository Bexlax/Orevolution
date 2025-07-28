package net.bexla.orevolution.content.capabilities;

public class OrevolutionPlayerData {
    public boolean dontHarvestBlock = false;

    public void copyFrom(OrevolutionPlayerData other) {
        this.dontHarvestBlock = other.dontHarvestBlock;
    }
}
