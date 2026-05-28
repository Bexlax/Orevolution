package net.bexla.orevolution.content.data.powers.tools;

import net.bexla.orevolution.content.types.interfaces.IConditional;
import net.bexla.orevolution.content.types.power.tool.OrevolutionToolPower;
import net.minecraft.core.BlockPos;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;

import java.util.function.Supplier;

public class ToolAddEffectPerBlockAmount extends OrevolutionToolPower {
    private final Supplier<MobEffect> effect;
    private final int minHits;
    private final int effectTime;
    private final int maxAmplifier;

    public ToolAddEffectPerBlockAmount(String tooltipId, IConditional conditional, Supplier<MobEffect> effect, int minHits, int effectTime, int maxAmplifier) {
        super(tooltipId, conditional);
        this.effect = effect;
        this.minHits = minHits;
        this.effectTime = effectTime;
        this.maxAmplifier = maxAmplifier;
    }

    @Override
    public boolean onMineBlock(ItemStack stack, Level level, BlockPos pos, LivingEntity entity, BlockState state, int xpToDrop) {
        if(!getCBoolean(stack, null, level, entity, null)) return super.onMineBlock(stack, level, pos, entity, state, xpToDrop);

        MobEffect eff = effect.get();

        int blocksMined = stack.getOrCreateTag().getInt("hits");
        MobEffectInstance currentEffect = entity.getEffect(eff);
        int effectsStacked = currentEffect != null? currentEffect.getAmplifier() : 0;

        blocksMined++;
        if (blocksMined >= minHits) {
            if (effectsStacked < maxAmplifier) {
                entity.removeEffect(eff);
                entity.addEffect(new MobEffectInstance(eff, effectTime, effectsStacked, false, true));
            }
            blocksMined = 0;
        }
        stack.getOrCreateTag().putInt("hits", blocksMined);
        return super.onMineBlock(stack, level, pos, entity, state, xpToDrop);
    }
}
