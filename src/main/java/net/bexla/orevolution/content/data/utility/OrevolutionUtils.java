package net.bexla.orevolution.content.data.utility;

import com.mojang.logging.LogUtils;
import net.bexla.orevolution.Orevolution;
import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.stats.Stats;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import org.slf4j.Logger;

import javax.annotation.Nullable;

public class OrevolutionUtils {
    protected static final Logger LOGGER = LogUtils.getLogger();

    public static boolean isWearingFullSet(LivingEntity entity, ArmorMaterial material) {
        return entity.getItemBySlot(EquipmentSlot.HEAD).getItem() instanceof ArmorItem head &&
                entity.getItemBySlot(EquipmentSlot.CHEST).getItem() instanceof ArmorItem chest &&
                entity.getItemBySlot(EquipmentSlot.LEGS).getItem() instanceof ArmorItem legs &&
                entity.getItemBySlot(EquipmentSlot.FEET).getItem() instanceof ArmorItem feet &&
                head.getMaterial() == material &&
                chest.getMaterial() == material &&
                legs.getMaterial() == material &&
                feet.getMaterial() == material;
    }

    @Nullable
    public static ArmorMaterial getCurrentFullSet(LivingEntity entity) {
        ItemStack helmet = entity.getItemBySlot(EquipmentSlot.HEAD);

        if (!(helmet.getItem() instanceof ArmorItem armorItem))
            return null;

        ArmorMaterial material = armorItem.getMaterial();

        return isWearingFullSet(entity, material)
                ? material
                : null;
    }

    public static void simulateBlockBreaking(Player player, ItemStack stack, BlockPos pos, BlockState state, ItemStack itemToDrop, Level level) {
        player.awardStat(Stats.BLOCK_MINED.get(state.getBlock()));
        player.causeFoodExhaustion(0.005F);
        stack.hurtAndBreak(1, player, (plyr) -> plyr.broadcastBreakEvent(EquipmentSlot.MAINHAND));
        Block.popResource(level, pos, itemToDrop);
    }

    public static ResourceLocation modLocat(String id) {
        return new ResourceLocation(Orevolution.MODID, id);
    }

    public static class Rarities {
        public static Rarity Aether = Rarity.create("aether", style -> style.withColor(ChatFormatting.RED).withBold(true));
    }
}
