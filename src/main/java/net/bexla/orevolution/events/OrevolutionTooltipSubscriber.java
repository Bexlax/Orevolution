package net.bexla.orevolution.events;

import net.bexla.orevolution.OrevolutionConfigClient;
import net.bexla.orevolution.content.data.utility.OrevolutionTags;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TieredItem;
import net.minecraft.world.item.Tiers;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.List;

@Mod.EventBusSubscriber
public class OrevolutionTooltipSubscriber {
    @OnlyIn(Dist.CLIENT)
    @SubscribeEvent
    public static void onItemTooltip(ItemTooltipEvent event) {
        ItemStack itemstack = event.getItemStack();
        List<Component> tooltip = event.getToolTip();

        if (tooltip == null) return;

        if(itemstack.getItem() instanceof TieredItem tiered) {
            int tiernum = tiered.getTier().getLevel();
            String return_string = String.valueOf(tiernum);

            if (tiered.getTier() == Tiers.STONE && !itemstack.is(OrevolutionTags.TinProgExcept) && OrevolutionConfigClient.tinProgTip) {
                return_string = "ex1";
            }
            else if (tiered.getTier() == Tiers.IRON && !itemstack.is(OrevolutionTags.PlatProgExcept) && OrevolutionConfigClient.platProgTip) {
                return_string = "ex2";
            }
            else if (tiernum == -1 || tiernum == -2) {
                return_string = "all";
            }

            if (OrevolutionConfigClient.harvestTip) {
                String combined = Component.translatable("tooltip.orevolution.harvest_tier").getString()
                        + "\n"
                        + (" \u00A79" + Component.translatable("tooltip.orevolution." + return_string + "_harvest").getString());

                int insertIndex = 1;
                for (String line : combined.split("\n")) {
                    tooltip.add(insertIndex++, Component.literal(line));
                }
            }
        }
    }
}
