package net.bexla.orevolution.content.data.powers.tools;

import net.bexla.orevolution.content.data.base.OrevolutionToolPower;
import net.bexla.orevolution.content.data.tags.OrevolutionBlockTags;
import net.minecraft.core.BlockPos;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;

public class ToolIncreaseCropDrops extends OrevolutionToolPower {
    private final int dropMultiplier;
    private final double chance;

    public ToolIncreaseCropDrops(String tooltip_id, int dropMultiplier, double chance) {
        super(tooltip_id);
        this.dropMultiplier = dropMultiplier;
        this.chance = chance;
    }

    @Override
    public void onMineBlock(ItemStack stack, Level level, BlockPos pos, Player player, BlockState state) {
        if(!state.is(BlockTags.CROPS)) return;

        if(Math.random() < this.chance) {
            for(int i = 0; i < this.dropMultiplier; i++) {
                Block.dropResources(state, level, pos);
            }
        }
    }
}
