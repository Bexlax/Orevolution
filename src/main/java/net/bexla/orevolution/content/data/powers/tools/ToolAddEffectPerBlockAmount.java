package net.bexla.orevolution.content.data.powers.tools;

import net.bexla.orevolution.content.types.OrevolutionToolPower;
import net.bexla.orevolution.content.types.TierProgressRegistry;
import net.bexla.orevolution.content.types.interfaces.Conditional;
import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TieredItem;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

public class ToolAddEffectPerBlockAmount extends OrevolutionToolPower {
    private final int minimalBlocks;
    private final Supplier<MobEffect> effect;
    private final int effectTime;
    private final int maxStacks;

    public ToolAddEffectPerBlockAmount(String id, Conditional condition, Supplier<MobEffect> effect, int minBlocks, int minEffectTime, int maxStacks) {
        super(id, condition);
        this.minimalBlocks = minBlocks;
        this.effect = effect;
        this.effectTime = minEffectTime;
        this.maxStacks = maxStacks;
    }

    @Override
    public List<Component> appendTooltip(ItemStack stack, Level level, List<Component> lines) {
        List<Component> tips = new ArrayList<>();
        tips.add(Component.translatable("tooltip.orevolution." + getTooltipID(), minimalBlocks).withStyle(ChatFormatting.GREEN));
        tips.add(Component.literal(" - " + effect.get().getDisplayName().getString()).withStyle(ChatFormatting.AQUA));
        return tips;
    }

    @Override
    public void onMineBlock(ItemStack stack, Level level, BlockPos pos, LivingEntity player, BlockState state) {
        if(!getCondition(stack, state, level, player, null)) return;

        MobEffect eff = effect.get();

        Item item = stack.getItem();
        if (item instanceof TieredItem tieredItem && TierProgressRegistry.isCorrectTierForDrops(tieredItem.getTier(), state)) {
            int blocksMined = stack.getOrCreateTag().getInt("blocksMined");
            MobEffectInstance currentEffect = player.getEffect(eff);
            int effectsStacked = currentEffect != null? currentEffect.getAmplifier() : 0;

            blocksMined++;
            if (blocksMined >= minimalBlocks + effectsStacked) {
                if (effectsStacked < maxStacks) {
                    int newDuration = effectTime * effectsStacked;
                    player.removeEffect(eff);
                    player.addEffect(new MobEffectInstance(eff, newDuration, effectsStacked, false, true));
                }
                blocksMined = 0;
            }
            stack.getOrCreateTag().putInt("blocksMined", blocksMined);
        }
    }
}
