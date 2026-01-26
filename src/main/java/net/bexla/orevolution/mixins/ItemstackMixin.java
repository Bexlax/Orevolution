package net.bexla.orevolution.mixins;

import net.bexla.orevolution.OrevolutionConfig;
import net.bexla.orevolution.content.types.ToolPowerRegistry;
import net.bexla.orevolution.content.types.interfaces.ToolPower;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Map;
import java.util.function.Supplier;

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

    private static final Map<Tier, Supplier<Integer>> BALANCED_DURABILITIES = Map.of(
            Tiers.IRON, OrevolutionConfig.COMMON.ironMaxUses,
            Tiers.DIAMOND, OrevolutionConfig.COMMON.diamondMaxUses,
            Tiers.GOLD, OrevolutionConfig.COMMON.goldMaxUses,
            Tiers.NETHERITE, OrevolutionConfig.COMMON.netheriteMaxUses,
            Tiers.STONE, OrevolutionConfig.COMMON.stoneMaxUses,
            Tiers.WOOD, OrevolutionConfig.COMMON.woodMaxUses
    );


    @Inject(method = "getMaxDamage", at = @At("RETURN"), cancellable = true)
    private void orevolution$injectModifierMaxUses(CallbackInfoReturnable<Integer> cir) {
        ItemStack stack = (ItemStack)(Object)this;

        int uses = cir.getReturnValue();

        if (!(stack.getItem() instanceof TieredItem tiered)) return;

        Supplier<Integer> override = BALANCED_DURABILITIES.get(tiered.getTier());
        if (override != null) {
            uses = override.get();
        }

        if(uses == tiered.getTier().getUses()) return;

        if (tiered instanceof SwordItem && OrevolutionConfig.COMMON.weaponsPowers.get()) {
            uses = ToolPowerRegistry.getSwordPowerForTier(tiered.getTier()).setMaxUses(stack, uses);
        }
        else if (tiered instanceof DiggerItem && OrevolutionConfig.COMMON.toolsPowers.get()) {
            uses = ToolPowerRegistry.getToolPowerForTier(tiered.getTier()).setMaxUses(stack, uses);
        }

        cir.setReturnValue(uses);
    }
}
