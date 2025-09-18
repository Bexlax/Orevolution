package net.bexla.orevolution.events;

import net.bexla.orevolution.OrevolutionConfig;
import net.bexla.orevolution.OrevolutionConfigClient;
import net.bexla.orevolution.content.data.utility.OrevolutionTags;
import net.bexla.orevolution.content.data.utility.OrevolutionToolTiers;
import net.bexla.orevolution.content.types.events.CorrectToolForDropsEvents;
import net.bexla.orevolution.init.RegCapabilities;
import net.minecraft.network.chat.Component;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.TieredItem;
import net.minecraft.world.item.Tiers;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.common.TierSortingRegistry;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.List;

@Mod.EventBusSubscriber
public class OrevolutionBlockBreakingSubscriber {
    // todo: add all the systems left

    @SubscribeEvent
    public static void onLeftClickBlock(PlayerInteractEvent.LeftClickBlock event) {
        BlockState state = event.getLevel().getBlockState(event.getPos());
        Entity entity = event.getEntity();

        if(event.getLevel().isClientSide && OrevolutionConfigClient.warn) warnOre(state, entity);
    }

    @SubscribeEvent
    public static void onCorrectToolForDropsEvent(CorrectToolForDropsEvents event) {
        addTierBetweenProgress(Tiers.STONE, OrevolutionToolTiers.TIN, OrevolutionConfig.tinProg, OrevolutionTags.Blocks.TinTiered, event);
        addTierBetweenProgress(Tiers.IRON, OrevolutionToolTiers.PLATINUM, OrevolutionConfig.platinumProg, OrevolutionTags.Blocks.PlatTiered, event);
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

    // A visitor? hmm... indeed, i have programmed long enough.
    private static void addTierForProgress(Tier minTier, TagKey<Block> blockTag, CorrectToolForDropsEvents event) {
        BlockState state = event.getState();
        ItemStack stack = event.getStack();

        if (!(stack.getItem() instanceof TieredItem tiered)) return;

        Tier playerTier = tiered.getTier();
        List<Tier> sortedTiers = TierSortingRegistry.getSortedTiers();

        int minIndex = sortedTiers.indexOf(minTier);
        int playerIndex = sortedTiers.indexOf(playerTier);

        if (playerIndex < minIndex && state.is(blockTag)) {
            event.setCanHarvest(false);
        }
    }
}
