package net.bexla.orevolution.events;

import net.bexla.orevolution.content.data.interfaces.ToolPower;
import net.bexla.orevolution.content.types.ExternalToolsPowerRegistry;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.TieredItem;
import net.minecraft.world.level.Level;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.event.level.BlockEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.List;

import static net.bexla.orevolution.OrevolutionConfig.toolsPowers;
import static net.bexla.orevolution.OrevolutionConfig.weaponsPowers;
import static net.bexla.orevolution.OrevolutionConfigClient.toolsPowersTip;

@Mod.EventBusSubscriber
public class OrevolutionToolPowersSubscriber {
    @OnlyIn(Dist.CLIENT)
    @SubscribeEvent
    public static void onItemTooltip(ItemTooltipEvent event) {
        if (!toolsPowersTip) return;

        ItemStack itemstack = event.getItemStack();
        List<Component> tooltip = event.getToolTip();

        if (tooltip == null) return;
        if (!(itemstack.getItem() instanceof TieredItem tiered)) return;

        ToolPower power = ExternalToolsPowerRegistry.getSwordPower(tiered.getTier());

        if(power == null) return;

        Player player = event.getEntity();

        if (player == null) return;

        power.appendTooltip(itemstack, player.level(), tooltip);
    }

    @SubscribeEvent
    public static void onLivingHurt(LivingHurtEvent event) {
        if(!weaponsPowers) return;

        if (!(event.getSource().getEntity() instanceof LivingEntity attacker)) return;

        ItemStack held = attacker.getMainHandItem();
        if (!(held.getItem() instanceof TieredItem tiered)) return;

        ToolPower power = (held.getItem() instanceof SwordItem)
                ? ExternalToolsPowerRegistry.getSwordPower(tiered.getTier())
                : ExternalToolsPowerRegistry.getToolPower(tiered.getTier());

        if (power != null) {
            power.onHitEntity(held, event.getEntity(), attacker);
        }
    }

    @SubscribeEvent
    public static void onBlockBreak(BlockEvent.BreakEvent event) {
        if(!toolsPowers) return;

        Player player = event.getPlayer();
        if (player == null) return;

        ItemStack held = player.getMainHandItem();
        if (!(held.getItem() instanceof TieredItem tiered)) return;

        ToolPower power = (held.getItem() instanceof SwordItem)
                ? ExternalToolsPowerRegistry.getSwordPower(tiered.getTier())
                : ExternalToolsPowerRegistry.getToolPower(tiered.getTier());
        if (power != null) {
            power.onMineBlock(held, (Level)event.getLevel(), event.getPos(), player, event.getState());
        }
    }

    @SubscribeEvent
    public static void onInventoryTick(TickEvent.PlayerTickEvent event) {
        if(!toolsPowers) return;

        Player player = event.player;
        if (event.phase != TickEvent.Phase.END) return;

        for (int i = 0; i < player.getInventory().getContainerSize(); i++) {
            ItemStack stack = player.getInventory().getItem(i);
            if (!(stack.getItem() instanceof TieredItem tiered)) continue;

            ToolPower power = (stack.getItem() instanceof SwordItem)
                    ? ExternalToolsPowerRegistry.getSwordPower(tiered.getTier())
                    : ExternalToolsPowerRegistry.getToolPower(tiered.getTier());
            if (power != null) {
                power.onInventoryTick(stack, player.level(), player, i, stack == player.getMainHandItem());
            }
        }
    }
}
