package net.bexla.orevolution.mixins;

import net.bexla.orevolution.content.types.PropertiesModifierRegistry;
import net.bexla.orevolution.content.types.base.interfaces.ItemPropertiesModifiers;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TieredItem;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(TieredItem.class)
public class TieredItemMixin {
    @Inject(method = "isValidRepairItem", at = @At("RETURN"), cancellable = true)
    private void orevolution$injectAttackDamage(ItemStack stack, ItemStack ingredient, CallbackInfoReturnable<Boolean> cir) {
        for(ItemPropertiesModifiers modifier : PropertiesModifierRegistry.getModifiers()) {
            cir.setReturnValue(modifier.setValidRepairItem(stack, ingredient, cir.getReturnValue()));
        }
    }
}
