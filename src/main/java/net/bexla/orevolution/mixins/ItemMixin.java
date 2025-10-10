package net.bexla.orevolution.mixins;

import net.bexla.orevolution.OrevolutionConfig;
import net.bexla.orevolution.content.data.utility.OrevolutionTags;
import net.bexla.orevolution.content.data.utility.OrevolutionUtils;
import net.bexla.orevolution.content.types.ToolPowerRegistry;
import net.bexla.orevolution.content.types.base.interfaces.ToolPower;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.*;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import javax.annotation.Nullable;
import java.util.List;

@Mixin(Item.class)
public class ItemMixin {
    @Inject(method = "appendHoverText", at = @At("HEAD"))
    private void orevolution$injectPowerTooltip(ItemStack stack, @Nullable Level level, List<Component> lines, TooltipFlag flag, CallbackInfo ci) {
        if(stack.getItem() instanceof TieredItem tieredItem) {
            Tier nextTier = OrevolutionUtils.getTierAfter(tieredItem.getTier());

            if(nextTier == null) return;

            String return_string = String.valueOf(nextTier);

            if(return_string == null) return;

            if ((tieredItem.getTier() == Tiers.STONE || stack.is(OrevolutionTags.Items.TinProgFollow)) && !OrevolutionConfig.CLIENT.tinProgTip.get()) {
                return_string = "iron";
            }
            else if ((tieredItem.getTier() == Tiers.IRON || stack.is(OrevolutionTags.Items.PlatProgFollow)) && !OrevolutionConfig.CLIENT.platProgTip.get()) {
                return_string = "diamond";
            }

            if (OrevolutionConfig.CLIENT.harvestTip.get()) {
                lines.add(1, Component.translatable("tiers.orevolution." + return_string.toLowerCase()).withStyle(ChatFormatting.YELLOW));
                lines.add(1, Component.translatable("tooltip.orevolution.harvest_tier"));
            }

            if (tieredItem instanceof SwordItem && !OrevolutionConfig.CLIENT.weaponsPowersTip.get()) {
                ToolPower power = ToolPowerRegistry.getSwordPowerForTier(tieredItem.getTier());
                if (power != null) {
                    power.appendTooltip(stack, level, lines);
                }
            } else if (tieredItem instanceof DiggerItem && !OrevolutionConfig.CLIENT.weaponsPowersTip.get()) {
                ToolPower power = ToolPowerRegistry.getToolPowerForTier(tieredItem.getTier());
                if (power != null) {
                    power.appendTooltip(stack, level, lines);
                }
            }
        }
    }

    @Inject(method = "inventoryTick", at = @At("HEAD"))
    private void orevolution$injectInventoryTick(ItemStack stack, Level level, Entity entity, int slotIndex, boolean selectedIndex, CallbackInfo cir) {
        if(stack.getItem() instanceof TieredItem tieredItem) {
            if (tieredItem instanceof SwordItem) {
                if (!OrevolutionConfig.COMMON.weaponsPowers.get()) return;

                ToolPower power = ToolPowerRegistry.getSwordPowerForTier(tieredItem.getTier());
                power.onInventoryTick(stack, level, entity, slotIndex, selectedIndex);
            }
            if(stack.getItem() instanceof DiggerItem) {
                if (!OrevolutionConfig.COMMON.toolsPowers.get()) return;

                ToolPower power = ToolPowerRegistry.getToolPowerForTier(tieredItem.getTier());
                power.onInventoryTick(stack, level, entity, slotIndex, selectedIndex);
            }
        }
    }
}
