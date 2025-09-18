package net.bexla.orevolution.content.data.powers.tools.specifics;

import net.bexla.orevolution.content.data.base.OrevolutionToolPower;
import net.bexla.orevolution.content.data.interfaces.OrevolutionConditional;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;

public class ToolIronPower extends OrevolutionToolPower {
    private static final int COOLDOWN_TICKS = 30; // 1.5s
    private static final int BLOCKS_FOR_HASTE1 = 6;
    private static final int BLOCKS_FOR_HASTE2 = 32;

    public ToolIronPower(String tooltipId, OrevolutionConditional conditional) {
        super(tooltipId, conditional);
    }

    @Override
    public void onMineBlock(ItemStack stack, Level level, BlockPos pos, Player player, BlockState state) {
        if(!getCondition(stack, level, player, null)) return;

        CompoundTag tag = stack.getOrCreateTag();
        int counter = tag.getInt("OverclockCounter");
        int cooldown = tag.getInt("OverclockCooldown");

        // Check cooldown
        if (cooldown > 0) {
            counter = 1; // Reset counter if too slow
        } else {
            counter++;
        }

        tag.putInt("OverclockCounter", counter);
        tag.putInt("OverclockCooldown", COOLDOWN_TICKS);

        if (!level.isClientSide) {
            if (counter >= BLOCKS_FOR_HASTE2) {
                player.addEffect(new MobEffectInstance(MobEffects.DIG_SPEED, 30, 1, true, false));
            } else if (counter >= BLOCKS_FOR_HASTE1) {
                player.addEffect(new MobEffectInstance(MobEffects.DIG_SPEED, 30, 0, true, false));
            }
        }
    }

    @Override
    public void onInventoryTick(ItemStack stack, Level level, Entity entity, int slot, boolean selected) {
        if (!(entity instanceof LivingEntity ent)) return;

        if(getCondition(stack, level, ent, null)) return;

        CompoundTag tag = stack.getOrCreateTag();
        int cooldown = tag.getInt("OverclockCooldown");

        if (cooldown > 0) {
            tag.putInt("OverclockCooldown", cooldown - 1);
        } else {
            tag.putInt("OverclockCounter", 0); // Reset streak if cooldown expired
        }
    }
}