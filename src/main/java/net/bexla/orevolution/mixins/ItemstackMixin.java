package net.bexla.orevolution.mixins;

import net.bexla.orevolution.OrevolutionConfig;
import net.bexla.orevolution.content.data.base.interfaces.ItemPropertiesModifiers;
import net.bexla.orevolution.content.data.base.interfaces.ToolPower;
import net.bexla.orevolution.content.types.PropertiesModifierRegistry;
import net.bexla.orevolution.content.types.ToolPowerRegistry;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.DiggerItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.TieredItem;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ItemStack.class)
public class ItemstackMixin {
    @Inject(method = "mineBlock", at = @At("HEAD"))
    private void orevolution$injectPowerMining(Level level, BlockState state, BlockPos pos, Player entity, CallbackInfo cir) {
        ItemStack stack = (ItemStack)(Object)this;

        if(stack.getItem() instanceof TieredItem tieredItem) {
            if (tieredItem instanceof SwordItem) {
                if (!OrevolutionConfig.COMMON.weaponsPowers.get()) return;

                ToolPower power = ToolPowerRegistry.getSwordPowerForTier(tieredItem.getTier());
                if (power == null) return;

                power.onMineBlock(stack, level, pos, entity, state);
            } else if (tieredItem instanceof DiggerItem) {
                if (!OrevolutionConfig.COMMON.toolsPowers.get()) return;

                ToolPower power = ToolPowerRegistry.getToolPowerForTier(tieredItem.getTier());
                if (power == null) return;

                power.onMineBlock(stack, level, pos, entity, state);
            }
        }
    }

    @Inject(method = "hurtEnemy", at = @At("HEAD"))
    private void orevolution$injectPowerAttackEnemy(LivingEntity target, Player attacker, CallbackInfo cir) {
        ItemStack stack = (ItemStack)(Object)this;

        if(stack.getItem() instanceof TieredItem tieredItem) {
            if (tieredItem instanceof SwordItem) {
                if (!OrevolutionConfig.COMMON.weaponsPowers.get()) return;

                ToolPower power = ToolPowerRegistry.getSwordPowerForTier(tieredItem.getTier());
                if (power == null) return;

                power.onHitEntity(stack, target, attacker);
            } else if (tieredItem instanceof DiggerItem) {
                if (!OrevolutionConfig.COMMON.toolsPowers.get()) return;

                ToolPower power = ToolPowerRegistry.getToolPowerForTier(tieredItem.getTier());
                if (power == null) return;

                power.onHitEntity(stack, target, attacker);
            }
        }
    }

    @Inject(method = "getDestroySpeed", at = @At("TAIL"), cancellable = true)
    private void orevolution$injectModifierDestroySpeed(BlockState state, CallbackInfoReturnable<Float> cir) {
        ItemStack stack = (ItemStack)(Object)this;

        for(ItemPropertiesModifiers modifier : PropertiesModifierRegistry.getModifiers()) {
            cir.setReturnValue(modifier.setDestroySpeed(stack, state, cir.getReturnValue()));
        }
    }

    @Inject(method = "isCorrectToolForDrops", at = @At("HEAD"), cancellable = true)
    private void orevolution$injectConditionStart(BlockState state, CallbackInfoReturnable<Boolean> cir) {
        ItemStack stack = (ItemStack)(Object)this;

        for(ItemPropertiesModifiers modifier : PropertiesModifierRegistry.getModifiers()) {
            cir.setReturnValue(modifier.setCorrectToolForDrops(stack, state, cir.getReturnValue()));
        }
    }

    @Inject(method = "getMaxDamage", at = @At("RETURN"), cancellable = true)
    private void orevolution$injectModifierMaxUses(CallbackInfoReturnable<Integer> cir) {
        ItemStack stack = (ItemStack)(Object)this;

        for(ItemPropertiesModifiers modifier : PropertiesModifierRegistry.getModifiers()) {
            cir.setReturnValue(modifier.setMaxUses(stack, cir.getReturnValue()));
        }
    }
}
