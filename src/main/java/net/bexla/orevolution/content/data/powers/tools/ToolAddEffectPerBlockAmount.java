package net.bexla.orevolution.content.data.powers.tools;

import net.bexla.orevolution.content.types.base.OrevolutionToolPower;
import net.bexla.orevolution.content.types.base.interfaces.Conditional;
import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;

import java.util.List;

public class ToolAddEffectPerBlockAmount extends OrevolutionToolPower {
    private final int minimalBlocks;
    private final MobEffect effect;
    private final int effectTime;
    private final int maxStacks;

    public ToolAddEffectPerBlockAmount(String id, Conditional condition, MobEffect effect, int minBlocks, int minEffectTime, int maxStacks) {
        super(id, condition);
        this.minimalBlocks = minBlocks;
        this.effect = effect;
        this.effectTime = minEffectTime;
        this.maxStacks = maxStacks;
    }

    @Override
    public void appendTooltip(ItemStack stack, Level level, List<Component> lines) {
        lines.add(1, Component.translatable("tooltip.orevolution." + getTooltipID(), minimalBlocks).withStyle(ChatFormatting.GREEN));
        lines.add(Component.literal(" - " + effect.getDisplayName().getString()).withStyle(ChatFormatting.AQUA));
    }

    @Override
    public void onMineBlock(ItemStack stack, Level level, BlockPos pos, LivingEntity player, BlockState state) {
        if(!getCondition(stack, state, level, player, null)) return;

        Item item = stack.getItem();
        if (!level.isClientSide && item.isCorrectToolForDrops(state)) {
            int blocksMined = stack.getOrCreateTag().getInt("blocksMined");
            MobEffectInstance currentEffect = player.getEffect(effect);
            int effectsStacked = currentEffect != null? currentEffect.getAmplifier() : 0;

            blocksMined++;
            if (blocksMined >= minimalBlocks + effectsStacked) {
                if (effectsStacked < maxStacks) {
                    int newDuration = effectTime * effectsStacked;
                    player.removeEffect(effect);
                    player.addEffect(new MobEffectInstance(effect, newDuration, effectsStacked, false, true));
                }
                blocksMined = 0;
            }
            stack.getOrCreateTag().putInt("blocksMined", blocksMined);
        }
    }
}
