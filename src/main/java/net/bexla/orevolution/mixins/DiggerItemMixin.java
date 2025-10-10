package net.bexla.orevolution.mixins;

import net.bexla.orevolution.OrevolutionConfig;
import net.bexla.orevolution.content.types.PropertiesModifierRegistry;
import net.bexla.orevolution.content.types.ToolPowerRegistry;
import net.bexla.orevolution.content.types.base.interfaces.ItemPropertiesModifiers;
import net.bexla.orevolution.content.types.base.interfaces.ToolPower;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.DiggerItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(DiggerItem.class)
public class DiggerItemMixin {
    @Inject(method = "getAttackDamage", at = @At("RETURN"), cancellable = true)
    private void orevolution$injectAttackDamage(CallbackInfoReturnable<Float> cir) {
        DiggerItem item = (DiggerItem)(Object)this;

        ItemStack stack = item.getDefaultInstance();

        if(stack == null || item == null) return;

        for(ItemPropertiesModifiers modifier : PropertiesModifierRegistry.getModifiers()) {
            cir.setReturnValue(modifier.setAttackDamage(stack, cir.getReturnValue()));
        }
    }


    @Inject(method = "mineBlock", at = @At("HEAD"))
    private void orevolution$injectPowerMining(ItemStack stack, Level level, BlockState state, BlockPos pos, LivingEntity entity, CallbackInfoReturnable<Boolean> cir) {
        if(stack.getItem() instanceof DiggerItem tieredItem) {
            if (!OrevolutionConfig.COMMON.toolsPowers.get()) return;

            ToolPower power = ToolPowerRegistry.getToolPowerForTier(tieredItem.getTier());
            if (power == null) return;
            power.onMineBlock(stack, level, pos, entity, state);
            if(power.onUseOverride(stack, level, entity) && cir.isCancellable()) {
                cir.setReturnValue(true);
                cir.cancel();
            }
        }
    }

    @Inject(method = "hurtEnemy", at = @At("HEAD"))
    private void orevolution$injectPowerAttackEnemy(ItemStack stack, LivingEntity target, LivingEntity attacker, CallbackInfoReturnable<Boolean> cir) {
        if(stack.getItem() instanceof DiggerItem tieredItem) {
            if (!OrevolutionConfig.COMMON.toolsPowers.get()) return;

            ToolPower power = ToolPowerRegistry.getToolPowerForTier(tieredItem.getTier());
            if (power == null) return;
            power.onHitEntity(stack, target, attacker);
            if(power.onUseOverride(stack, attacker.level(), attacker) && cir.isCancellable()) {
                cir.setReturnValue(true);
                cir.cancel();
            }
        }
    }
}
