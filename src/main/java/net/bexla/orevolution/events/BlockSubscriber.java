package net.bexla.orevolution.events;

import net.bexla.orevolution.init.RegItems;
import net.minecraft.core.BlockPos;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.CropBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.Tags;
import net.minecraftforge.event.level.BlockEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber
public class BlockSubscriber {
    @SubscribeEvent
    public static void onBlockBreak(BlockEvent.BreakEvent evt) {
        Player player = evt.getPlayer();

        if(player == null) return;

        BlockState state = evt.getState();
        Level level = player.level();
        BlockPos pos = evt.getPos();

        if(level.random.nextFloat() > 0.4) return;

        if(state.getBlock() instanceof CropBlock || state.is(BlockTags.CROPS)) {
            if(player.getMainHandItem().is(ItemTags.HOES) && level.getBlockState(pos.below(2)).is(Tags.Blocks.STONE)) {
                ItemEntity itemEntity = new ItemEntity(level, pos.getX() + 0.5, pos.getY() + 0.5, pos.getZ() + 0.5, new ItemStack(RegItems.PETRIFIED_SEED.get()));
                level.addFreshEntity(itemEntity);
            }
        }
    }
}
