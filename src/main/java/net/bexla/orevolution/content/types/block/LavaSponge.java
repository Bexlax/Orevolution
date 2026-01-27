package net.bexla.orevolution.content.types.block;

import net.bexla.orevolution.init.RegBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.tags.FluidTags;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.BucketPickup;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;

public class LavaSponge extends Block {
    public static final int MAX_DEPTH = 6;
    public static final int MAX_COUNT = 64;
    private static final Direction[] ALL_DIRECTIONS = Direction.values();

    public LavaSponge(BlockBehaviour.Properties p_56796_) {
        super(p_56796_);
    }

    public void onPlace(BlockState state, Level level, BlockPos pos, BlockState prev, boolean idk) {
        if (!prev.is(state.getBlock())) {
            this.tryAbsorbLava(level, pos);
        }
    }

    public void neighborChanged(BlockState state, Level level, BlockPos pos, Block block, BlockPos blockPosOrSomething, boolean idkeither) {
        this.tryAbsorbLava(level, pos);
        super.neighborChanged(state, level, pos, block, blockPosOrSomething, idkeither);
    }

    protected void tryAbsorbLava(Level level, BlockPos pos) {
        if (this.removeLava(level, pos)) {
            level.setBlock(pos, RegBlocks.HOT_TUNGSTEN_SPONGE.get().defaultBlockState(), 2);
            level.levelEvent(2001, pos, Block.getId(Blocks.LAVA.defaultBlockState()));
        }
    }

    private boolean removeLava(Level level, BlockPos pos) {
        return BlockPos.breadthFirstTraversal(pos, MAX_DEPTH, MAX_COUNT, (p_277519_, p_277492_) -> {
            for(Direction direction : ALL_DIRECTIONS) {
                p_277492_.accept(p_277519_.relative(direction));
            }
        }, (currentPos) -> {
            if (currentPos.equals(pos)) {
                return true;
            }
            BlockState blockState = level.getBlockState(currentPos);
            if (!level.getFluidState(currentPos).is(FluidTags.LAVA)) {
                return false;
            }

            if (blockState.getBlock() instanceof BucketPickup bucketPickup) {
                if (!bucketPickup.pickupBlock(level, currentPos, blockState).isEmpty()) {
                    return true; // You make even the devil cry
                }
            }

            level.setBlock(currentPos, Blocks.AIR.defaultBlockState(), 3);
            return true;
        }) > 1;
    }
}