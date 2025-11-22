package net.bexla.orevolution.mixins;

import net.bexla.orevolution.OrevolutionConfig;
import net.bexla.orevolution.content.data.utility.OrevolutionTags;
import net.bexla.orevolution.content.types.TierProgressRegistry;
import net.bexla.orevolution.content.types.ToolPowerRegistry;
import net.bexla.orevolution.content.types.interfaces.ToolPower;
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
import java.util.ArrayList;
import java.util.List;

@Mixin(Item.class)
public class ItemMixin {
    private static final Component HARVEST_TIER = Component.translatable("tooltip.orevolution.harvest_tier");

    @Inject(method = "appendHoverText", at = @At("TAIL"))
    private void orevolution$injectPowerTooltip(ItemStack stack, @Nullable Level level, List<Component> lines, TooltipFlag flag, CallbackInfo ci) {
        if(stack.getItem() instanceof TieredItem tieredItem) {
            Tier tier = tieredItem.getTier();

            String return_string = TierProgressRegistry.isTierSorted(tier) ? TierProgressRegistry.getName(tier).getPath() : isCorrectTierVanilla(tier);

            if(TierProgressRegistry.getAssociatedTierFromSecondary(tier) != null) {
                return_string = TierProgressRegistry.getName(TierProgressRegistry.getAssociatedTierFromSecondary(tier)).getPath();
            }

            if ((stack.is(OrevolutionTags.Items.tinProgFollow) && !(stack.is(OrevolutionTags.Items.tinProgExcept))) && !OrevolutionConfig.CLIENT.tinProgTip.get()) {
                return_string = "tin";
            }
            else if ((stack.is(OrevolutionTags.Items.platProgFollow) && !(stack.is(OrevolutionTags.Items.platProgExcept))) && !OrevolutionConfig.CLIENT.platProgTip.get()) {
                return_string = "platinum";
            }

            List<Component> tip = new ArrayList<>();

            if (tieredItem instanceof SwordItem && OrevolutionConfig.CLIENT.weaponsPowersTip.get()) {
                ToolPower power = ToolPowerRegistry.getSwordPowerForTier(tier);
                if (power != null) {
                    tip.addAll(power.appendTooltip(stack, level, lines));
                }
            } else if (tieredItem instanceof DiggerItem && OrevolutionConfig.CLIENT.weaponsPowersTip.get()) {
                ToolPower power = ToolPowerRegistry.getToolPowerForTier(tier);
                if (power != null) {
                    tip.addAll(power.appendTooltip(stack, level, lines));
                }
            }

            if (OrevolutionConfig.CLIENT.harvestTip.get() && !return_string.isEmpty()) {
                tip.add(HARVEST_TIER);
                tip.add(Component.literal(" - " + Component.translatable("tiers.orevolution." + return_string.toLowerCase()).getString()).withStyle(ChatFormatting.YELLOW));
            }


            lines.addAll(1, tip);
        }
    }

    private static String isCorrectTierVanilla(Tier tier)
    {
        int i = tier.getLevel();
        return switch (i) {
            case 1 -> "wood";
            case 2 -> "tin";
            case 3 -> "platinum";
            case 4 -> "diamond";
            default -> "netherite";
        };
    }

    @Inject(method = "inventoryTick", at = @At("HEAD"))
    private void orevolution$injectInventoryTick(ItemStack stack, Level level, Entity entity, int slotIndex, boolean selectedIndex, CallbackInfo cir) {
        if(stack.getItem() instanceof TieredItem tieredItem) {
            Tier tier = tieredItem.getTier();
            if (tieredItem instanceof SwordItem) {
                if (!OrevolutionConfig.COMMON.weaponsPowers.get()) return;

                ToolPower power = ToolPowerRegistry.getSwordPowerForTier(tier);
                power.onInventoryTick(stack, level, entity, slotIndex, selectedIndex);
            }
            if(stack.getItem() instanceof DiggerItem) {
                if (!OrevolutionConfig.COMMON.toolsPowers.get()) return;

                ToolPower power = ToolPowerRegistry.getToolPowerForTier(tier);
                power.onInventoryTick(stack, level, entity, slotIndex, selectedIndex);
            }
        }
    }
}
