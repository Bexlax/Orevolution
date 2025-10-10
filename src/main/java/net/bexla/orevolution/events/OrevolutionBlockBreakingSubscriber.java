package net.bexla.orevolution.events;

import net.bexla.orevolution.OrevolutionConfig;
import net.bexla.orevolution.init.RegCapabilities;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TieredItem;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber
public class OrevolutionBlockBreakingSubscriber {
    @SubscribeEvent
    public static void onLeftClickBlock(PlayerInteractEvent.LeftClickBlock event) {
        BlockState state = event.getLevel().getBlockState(event.getPos());
        Entity entity = event.getEntity();

        if(event.getLevel().isClientSide && OrevolutionConfig.CLIENT.warnOre.get()) warnOre(state, entity);
    }

    @OnlyIn(Dist.CLIENT)
    private static void warnOre(BlockState blockstate, Entity entity) {
        if (entity == null)
            return;

        if (entity instanceof LivingEntity livingEntity) {
            ItemStack mainHand = livingEntity.getMainHandItem();

            if (mainHand.getItem() instanceof TieredItem) {
                boolean wrongTool = !mainHand.isCorrectToolForDrops(blockstate);

                boolean dontHarvest = entity.getCapability(RegCapabilities.PLAYER_DATA)
                        .map(data -> data.dontHarvestBlock)
                        .orElse(false);

                if (wrongTool || dontHarvest) {
                    if (entity instanceof Player player && !player.level().isClientSide()) {
                        player.displayClientMessage(
                                Component.translatable("actionbar.orevolution.cant_harvest_ore"),
                                true
                        );
                    }
                }
            }
        }
    }
}
