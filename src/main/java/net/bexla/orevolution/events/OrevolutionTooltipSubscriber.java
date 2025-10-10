package net.bexla.orevolution.events;

import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber
public class OrevolutionTooltipSubscriber {
//    @OnlyIn(Dist.CLIENT)
//    @SubscribeEvent
//    public static void onItemTooltip(ItemTooltipEvent event) {
//        ItemStack itemstack = event.getItemStack();
//        List<Component> tooltip = event.getToolTip();
//
//        if (tooltip == null) return;
//
//        if(itemstack.getItem() instanceof TieredItem tiered) {
//            int tiernum = tiered.getTier().getLevel();
//            String return_string = tiernum >= 0 ? String.valueOf(tiernum) : String.valueOf(tiernum).replace("-", "neg.");
//
//            boolean tinCondition = tiered.getTier() == Tiers.STONE && !itemstack.is(OrevolutionTags.Items.TinProgExcept);
//            boolean platinumCondition = tiered.getTier() == Tiers.IRON && !itemstack.is(OrevolutionTags.Items.PlatProgExcept);
//
//            if ((tinCondition || itemstack.is(OrevolutionTags.Items.TinProgFollow)) && OrevolutionConfig.CLIENT.tinProgTip.get()) {
//                return_string = "extra.1";
//            }
//            else if ((platinumCondition || itemstack.is(OrevolutionTags.Items.PlatProgFollow)) && OrevolutionConfig.CLIENT.platProgTip.get()) {
//                return_string = "extra.2";
//            }
//
//            if (OrevolutionConfig.CLIENT.harvestTip.get()) {
//                String combined = ("§9" + Component.translatable("tooltip.orevolution." + return_string + "_harvest").getString())
//                        + "\n"
//                        + Component.translatable("tooltip.orevolution.harvest_tier").getString();
//
//                for (String line : combined.split("\n")) {
//                    tooltip.add(2, Component.literal(line));
//                }
//            }
//        }
//    }
}
