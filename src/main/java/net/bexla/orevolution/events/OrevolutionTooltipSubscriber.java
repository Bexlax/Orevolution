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
            String return_string = tiernum >= 0 ? String.valueOf(tiernum) : String.valueOf(tiernum).replace("-", "neg.");

            boolean tinCondition = tiered.getTier() == Tiers.STONE && !itemstack.is(OrevolutionTags.Items.TinProgExcept);
            boolean platinumCondition = tiered.getTier() == Tiers.IRON && !itemstack.is(OrevolutionTags.Items.PlatProgExcept);

            if ((tinCondition || itemstack.is(OrevolutionTags.Items.TinProgFollow)) && OrevolutionConfigClient.tinProgTip) {
                return_string = "extra.1";
            }
            else if ((platinumCondition || itemstack.is(OrevolutionTags.Items.PlatProgFollow)) && OrevolutionConfigClient.platProgTip) {
                return_string = "extra.2";
            }

            if (OrevolutionConfigClient.harvestTip) {
                String combined = Component.translatable("tooltip.orevolution.harvest_tier").getString()
                        + "\n"
                        + ("§9" + Component.translatable("tooltip.orevolution." + return_string + "_harvest").getString());

                for (String line : combined.split("\n")) {
                    tooltip.add(2, Component.literal(line));
                }
            }
        }
    }
}
