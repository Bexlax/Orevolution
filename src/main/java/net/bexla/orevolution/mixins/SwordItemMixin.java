package net.bexla.orevolution.mixins;

import net.bexla.orevolution.OrevolutionConfig;
import net.bexla.orevolution.content.types.ToolPowerRegistry;
import net.bexla.orevolution.content.types.interfaces.ToolPower;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.DiggerItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.TieredItem;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(SwordItem.class)
public class SwordItemMixin {
    @Inject(method = "getDamage", at = @At("RETURN"), cancellable = true)
    private void orevolution$injectAttackDamage(CallbackInfoReturnable<Float> cir) {
        SwordItem item = (SwordItem)(Object)this;

        ItemStack stack = item.getDefaultInstance();

        if (!(stack.getItem() instanceof TieredItem tiered)) return;

        float dmg = cir.getReturnValue();

        if (tiered instanceof SwordItem && OrevolutionConfig.COMMON.weaponsPowers.get()) {
            ToolPower power = ToolPowerRegistry.getSwordPowerForTier(tiered.getTier());
            dmg = power.setAttackDamage(stack, cir.getReturnValue());
        }
        else if (tiered instanceof DiggerItem && OrevolutionConfig.COMMON.toolsPowers.get()) {
            ToolPower power = ToolPowerRegistry.getToolPowerForTier(tiered.getTier());
            dmg = power.setAttackDamage(stack, cir.getReturnValue());
        }

        cir.setReturnValue(dmg);
    }


    @Inject(method = "mineBlock", at = @At("HEAD"))
    private void orevolution$injectPowerMining(ItemStack stack, Level level, BlockState state, BlockPos pos, LivingEntity entity, CallbackInfoReturnable<Boolean> cir) {
        if(stack.getItem() instanceof SwordItem tieredItem) {
            if (!OrevolutionConfig.COMMON.toolsPowers.get()) return;

            ToolPower power = ToolPowerRegistry.getSwordPowerForTier(tieredItem.getTier());
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
        if(stack.getItem() instanceof SwordItem tieredItem) {
            if (!OrevolutionConfig.COMMON.toolsPowers.get()) return;

            ToolPower power = ToolPowerRegistry.getSwordPowerForTier(tieredItem.getTier());
            if (power == null) return;
            power.onHitEntity(stack, target, attacker);
            if(power.onUseOverride(stack, attacker.level(), attacker) && cir.isCancellable()) {
                cir.setReturnValue(true);
                cir.cancel();
            }
        }
    }
}
