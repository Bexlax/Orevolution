package net.bexla.orevolution.content.data;

import net.bexla.orevolution.OrevolutionConfig;
import net.bexla.orevolution.content.data.utility.OrevolutionUtils;
import net.bexla.orevolution.content.types.TierProgressRegistry;
import net.bexla.orevolution.content.types.ToolPowerRegistry;
import net.bexla.orevolution.content.types.base.interfaces.ItemPropertiesModifiers;
import net.bexla.orevolution.content.types.base.interfaces.ToolPower;
import net.bexla.orevolution.init.RegItems;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.*;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.Tags;

import java.util.List;
import java.util.Map;
import java.util.function.Supplier;

public class OrevolutionItemModifiers implements ItemPropertiesModifiers {
    @Override
    public float setDestroySpeed(ItemStack stack, BlockState state, float defaultSpeed) {
        if (!(stack.getItem() instanceof TieredItem tiered)) return defaultSpeed;

        if (tiered instanceof SwordItem && OrevolutionConfig.COMMON.weaponsPowers.get()) {
            ToolPower power = ToolPowerRegistry.getSwordPowerForTier(tiered.getTier());
            power.setDestroySpeed(stack, state, defaultSpeed);
        }
        else if (tiered instanceof DiggerItem && OrevolutionConfig.COMMON.toolsPowers.get()) {
            ToolPower power = ToolPowerRegistry.getToolPowerForTier(tiered.getTier());
            power.setDestroySpeed(stack, state, defaultSpeed);
        }

        if (OrevolutionConfig.COMMON.safeOreBreaking.get() && !stack.isCorrectToolForDrops(state) && state.is(Tags.Blocks.ORES)) {
            return 0.0F;
        }

        return defaultSpeed;
    }

    private static final Map<Tier, Supplier<Integer>> CUSTOM_DURABILITIES = Map.of(
            Tiers.IRON, OrevolutionConfig.COMMON.ironMaxUses,
            Tiers.DIAMOND, OrevolutionConfig.COMMON.diamondMaxUses,
            Tiers.GOLD, OrevolutionConfig.COMMON.goldMaxUses,
            Tiers.NETHERITE, OrevolutionConfig.COMMON.netheriteMaxUses,
            Tiers.STONE, OrevolutionConfig.COMMON.stoneMaxUses,
            Tiers.WOOD, OrevolutionConfig.COMMON.woodMaxUses
    );

    @Override
    public int setMaxUses(ItemStack stack, int defaultUses) {
        if (!(stack.getItem() instanceof TieredItem tiered)) return defaultUses;

        boolean isSword = tiered instanceof SwordItem;
        boolean isTool = tiered instanceof DiggerItem;

        if (isSword && OrevolutionConfig.COMMON.weaponsPowers.get()) {
            return ToolPowerRegistry.getSwordPowerForTier(tiered.getTier()).setMaxUses(stack, defaultUses);
        }
        else if (isTool && OrevolutionConfig.COMMON.toolsPowers.get()) {
            return ToolPowerRegistry.getToolPowerForTier(tiered.getTier()).setMaxUses(stack, defaultUses);
        }

        Supplier<Integer> override = CUSTOM_DURABILITIES.get(tiered.getTier());
        if (override != null) return override.get();

        return defaultUses;
    }

    @Override
    public boolean setCorrectToolForDrops(ItemStack stack, BlockState state, boolean defaultCorrectTool) {
        if (stack.getItem() instanceof TieredItem tiered) {
            Tier tier = tiered.getTier();
            List<Tier> allowedTiers = OrevolutionUtils.getTiersLowerOrEqualThan(tier);
            for (Tier allowedTier : allowedTiers) {
                TagKey<Block> tag = TierProgressRegistry.getTagForTier(allowedTier);
                return tag != null && state.is(tag);
            }
        }
        return defaultCorrectTool;
    }

    @Override
    public boolean setValidRepairItem(ItemStack stack, ItemStack ingredient, boolean defaultRepairItem) {
        if(stack.getItem() instanceof TieredItem tieredItem) {
            return tieredItem.getTier() == Tiers.IRON && ingredient.is(RegItems.TIN_INGOT.get());
        }
        return defaultRepairItem;
    }
}
