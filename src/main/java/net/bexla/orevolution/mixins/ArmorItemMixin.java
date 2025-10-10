package net.bexla.orevolution.mixins;

import com.google.common.collect.Multimap;
import net.bexla.orevolution.content.types.PropertiesModifierRegistry;
import net.bexla.orevolution.content.types.base.interfaces.ItemPropertiesModifiers;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ArmorItem.class)
public class ArmorItemMixin {
    @Inject(method = "getDefense", at = @At("RETURN"), cancellable = true)
    private void orevolution$injectModifierDefense(CallbackInfoReturnable<Integer> ci) {
        ArmorItem item = (ArmorItem)(Object)this;

        ItemStack stack = item.getDefaultInstance();

        if(item == null || stack == null) return;

        for(ItemPropertiesModifiers modifier : PropertiesModifierRegistry.getModifiers()) {
            ci.setReturnValue(modifier.setMaxDefense(stack, ci.getReturnValue()));
        }
    }

    @Inject(method = "getToughness", at = @At("RETURN"), cancellable = true)
    private void orevolution$injectModifierToughness(CallbackInfoReturnable<Float> ci) {
        ArmorItem item = (ArmorItem)(Object)this;

        ItemStack stack = item.getDefaultInstance();

        if(item == null || stack == null) return;

        for(ItemPropertiesModifiers modifier : PropertiesModifierRegistry.getModifiers()) {
            ci.setReturnValue(modifier.setToughness(stack, ci.getReturnValue()));
        }
    }

    @Inject(method = "getDefaultAttributeModifiers", at = @At("RETURN"), cancellable = true)
    private void orevolution$injectAttributes(EquipmentSlot slot, CallbackInfoReturnable<Multimap<Attribute, AttributeModifier>> cir) {
        ArmorItem item = (ArmorItem)(Object)this;

        ItemStack stack = item.getDefaultInstance();

        if(item == null || stack == null) return;

        for(ItemPropertiesModifiers modifier : PropertiesModifierRegistry.getModifiers()) {
            cir.setReturnValue(modifier.setDefaultAttributeModifiers(slot, cir.getReturnValue()));
        }
    }

    @Inject(method = "isValidRepairItem", at = @At("RETURN"), cancellable = true)
    private void orevolution$injectRepairItem(ItemStack stack, ItemStack ingredient, CallbackInfoReturnable<Boolean> cir) {
        for(ItemPropertiesModifiers modifier : PropertiesModifierRegistry.getModifiers()) {
            cir.setReturnValue(modifier.setValidRepairItem(stack, ingredient, cir.getReturnValue()));
        }
    }
}
