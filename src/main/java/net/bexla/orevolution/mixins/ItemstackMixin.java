package net.bexla.orevolution.mixins;

import net.bexla.orevolution.content.types.events.CorrectToolForDropsEvents;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.MinecraftForge;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ItemStack.class)
public class ItemstackMixin {
    @Inject(method = "isCorrectToolForDrops", at = @At("HEAD"), cancellable = true)
    private void orevolution$injectConditionStart(BlockState state, CallbackInfoReturnable<Boolean> cir) {
        ItemStack stack = (ItemStack)(Object)this;

        CorrectToolForDropsEvents event = new CorrectToolForDropsEvents(state, stack, cir.getReturnValue());
        MinecraftForge.EVENT_BUS.post(event);
        if (event.isCanceled()) {
            cir.setReturnValue(false);
        } else {
            cir.setReturnValue(event.canHarvest());
        }
    }
}
