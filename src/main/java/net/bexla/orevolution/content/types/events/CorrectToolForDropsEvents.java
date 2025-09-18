package net.bexla.orevolution.content.types.events;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.eventbus.api.Cancelable;
import net.minecraftforge.eventbus.api.Event;

/**
 * It's main use is to force your own progression on minecraft.
 * There's an example on how to do this at net.bexla.orevolution.events.OrevolutionBlockBreakingSubscriber
 * <br>
 * <br> There's a slight chance that this gets replaced with something else that let's me and others register their own progression.
 */
@Cancelable
public class CorrectToolForDropsEvents extends Event {
    private final BlockState state;
    private final ItemStack stack;
    private boolean canHarvest;

    public CorrectToolForDropsEvents(BlockState state, ItemStack stack, boolean vanillaResult) {
        this.state = state;
        this.stack = stack;
        this.canHarvest = vanillaResult;
    }

    public BlockState getState() {
        return state;
    }
    public ItemStack getStack() {
        return stack;
    }

    public boolean canHarvest() {
        return canHarvest;
    }
    public void setCanHarvest(boolean value) {
        this.canHarvest = value;
    }
}
