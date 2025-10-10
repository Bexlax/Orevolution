package net.bexla.orevolution.content.data.powers.tools;

import net.bexla.orevolution.content.data.base.OrevolutionToolPower;
import net.bexla.orevolution.content.data.base.interfaces.Conditional;
import net.minecraft.core.BlockPos;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.Tags;
import org.jetbrains.annotations.NotNull;

public class ToolIncreaseDrops extends OrevolutionToolPower {
    private final int dropIncrement;
    private final double chanceMultiplier;

    public ToolIncreaseDrops(String tooltip_id, Conditional conditional, int dropIncrement, double chanceMultiplier) {
        super(tooltip_id, conditional);
        this.dropIncrement = dropIncrement;
        this.chanceMultiplier = chanceMultiplier;
    }

    @Override
    public @NotNull Object addTooltipValue() {
        return dropIncrement;
    }

    @Override
    public void onMineBlock(ItemStack stack, Level level, BlockPos pos, LivingEntity player, BlockState state) {
        if(!getCondition(stack, state, level, player, null)) return;

        double chance = 0.30;
        Item item = stack.getItem();
        if (!level.isClientSide && item.isCorrectToolForDrops(state)) {
            if(state.is(Tags.Blocks.STORAGE_BLOCKS)) chance = 0.01;
            else if(state.is(BlockTags.NEEDS_DIAMOND_TOOL)) chance = 0.035;
            else if(state.is(BlockTags.NEEDS_IRON_TOOL)) chance = 0.05;
            else if(state.is(BlockTags.NEEDS_STONE_TOOL)) chance = 0.20;

            if(state.is(Tags.Blocks.ORES)) chance = 0.10;


            chance = chance * chanceMultiplier;

            if(Math.random() < chance) {
                for(int i = 1; i < dropIncrement; i++) {
                    Block.dropResources(state, level, pos);
                }
            }
        }
    }
}
