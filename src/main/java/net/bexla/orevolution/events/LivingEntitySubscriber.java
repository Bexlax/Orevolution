package net.bexla.orevolution.events;

import net.bexla.orevolution.init.RegItems;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TieredItem;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.ComposterBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.event.entity.living.LivingDropsEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Mod.EventBusSubscriber
public class LivingEntitySubscriber {
    @SubscribeEvent
    public static void onLivingDrops(LivingDropsEvent evt) {
        LivingEntity entity = evt.getEntity();
        Level level = entity.level();
        if (level.isClientSide) return;

        CompoundTag data = entity.getPersistentData();
        if (!data.contains("orev_loot_multiplier")) return;

        long expire = data.getLong("orev_loot_expire");
        if (expire < level.getGameTime()) return;

        float multiplier = data.getFloat("orev_loot_multiplier");
        UUID attackerId = data.getUUID("orev_loot_attacker");
        int tier = data.getInt("orev_loot_tier");
        String itemId = data.getString("orev_loot_item");

        if (evt.getSource().getEntity() instanceof Player player) {

            if (!player.getUUID().equals(attackerId)) return;

            ItemStack held = player.getMainHandItem();
            int heldTier = held.getItem() instanceof TieredItem t ? t.getTier().getLevel() : 0;
            if (!held.getItem().toString().equals(itemId) || heldTier != tier) return;

            List<ItemEntity> drops = new ArrayList<>(evt.getDrops());
            for (ItemEntity itemEntity : drops) {
                ItemStack stack = itemEntity.getItem();
                ItemStack bonus = stack.copy();
                int extra = Math.max(1, (int) Math.floor(stack.getCount() * (multiplier - 1f)));
                bonus.setCount(extra);
                evt.getDrops().add(new ItemEntity(level, entity.getX(), entity.getY(), entity.getZ(), bonus));
            }
        }

        // Clean up
        data.remove("orev_loot_multiplier");
        data.remove("orev_loot_attacker");
        data.remove("orev_loot_tier");
        data.remove("orev_loot_item");
        data.remove("orev_loot_expire");
    }

    @SubscribeEvent
    public static void onInteractWithBlock(PlayerInteractEvent.RightClickBlock evt) {
        Level level = evt.getLevel();
        if (level.isClientSide) return;

        BlockPos pos = evt.getPos();
        BlockState state = level.getBlockState(pos);

        if (state.getBlock() instanceof ComposterBlock && state.getValue(ComposterBlock.LEVEL) == 8) {
            if (level.random.nextFloat() < 0.4) {
                double x = (double)pos.getX() + 0.5D;
                double y = (double)pos.getY() + 1.05D;
                double z = (double)pos.getZ() + 0.5D;
                ItemEntity itementity = new ItemEntity(level, x, y, z, new ItemStack(RegItems.DEAD_SEED.get()));
                itementity.setDeltaMovement(level.random.nextGaussian() * 0.05D, level.random.nextGaussian() * 0.05D + 0.2D, level.random.nextGaussian() * 0.05D);
                level.addFreshEntity(itementity);
            }
        }
    }
}
