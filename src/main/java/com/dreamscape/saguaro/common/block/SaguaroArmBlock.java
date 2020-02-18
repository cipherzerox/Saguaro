package com.dreamscape.saguaro.common.block;

import com.dreamscape.saguaro.core.registry.ModBlocks;
import net.minecraft.block.*;
import net.minecraft.block.material.PushReaction;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.DirectionProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.IWorldReader;

import javax.annotation.Nullable;

public class SaguaroArmBlock extends BushBlock {
    public static final BooleanProperty ATTACHED;
    public static final DirectionProperty FACING;

    protected static final VoxelShape ATTACHED_SHAPE;
    protected static final VoxelShape TOP_SHAPE;

    public SaguaroArmBlock(Properties p_i48437_1_) {
        super(p_i48437_1_);
        this.setDefaultState(this.stateContainer.getBaseState().with(ATTACHED, false));
    }



    protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
        builder.add(ATTACHED, FACING);
    }

    @SuppressWarnings("deprecation")
    public VoxelShape getShape(BlockState state, IBlockReader blockReader, BlockPos pos, ISelectionContext context) {
        return state.get(ATTACHED) ? ATTACHED_SHAPE : TOP_SHAPE;
    }

    @Nullable
    public BlockState getStateForPlacement(BlockItemUseContext context) {
        IWorldReader worldIn = context.getWorld();
        BlockPos pos = context.getPos();
        BlockPos north = pos.north();
        BlockPos east = pos.east();
        BlockPos south = pos.south();
        BlockPos west = pos.west();
        BlockPos down = pos.down();
        BlockState northState = worldIn.getBlockState(north);
        BlockState eastState = worldIn.getBlockState(east);
        BlockState southState = worldIn.getBlockState(south);
        BlockState westState = worldIn.getBlockState(west);
        BlockState downState = worldIn.getBlockState(down);
        boolean isNorth = isAttachable(northState);
        boolean isEast = isAttachable(eastState);
        boolean isSouth = isAttachable(southState);
        boolean isWest = isAttachable(westState);
        boolean isValidGround = isValidGround(downState, worldIn, down);
        return this.getDefaultState().with(ATTACHED, (isNorth || isEast || isSouth || isWest) && !isValidGround).with(FACING, isNorth ? Direction.NORTH : isEast ? Direction.EAST : isSouth ? Direction.SOUTH : Direction.WEST);
    }

    @Override
    public BlockState updatePostPlacement(BlockState stateIn, Direction facing, BlockState facingState, IWorld worldIn, BlockPos currentPos, BlockPos facingPos) {
        BlockPos down = currentPos.down();
        BlockState downState = worldIn.getBlockState(down);
        boolean isValidGround = isValidGround(downState, worldIn, down);
        if (!isValidGround) {
            BlockPos north = currentPos.north();
            BlockPos east = currentPos.east();
            BlockPos south = currentPos.south();
            BlockPos west = currentPos.west();
            BlockState northState = worldIn.getBlockState(north);
            BlockState eastState = worldIn.getBlockState(east);
            BlockState southState = worldIn.getBlockState(south);
            BlockState westState = worldIn.getBlockState(west);
            boolean isNorth = isAttachable(northState);
            boolean isEast = isAttachable(eastState);
            boolean isSouth = isAttachable(southState);
            boolean isWest = isAttachable(westState);
            if (!stateIn.get(ATTACHED)) {
                if (isValidGround(stateIn, worldIn, currentPos)) {
                    return Blocks.AIR.getDefaultState();
                }
            }
            if ((!isNorth || !isSouth || !isEast || !isWest) && !stateIn.get(ATTACHED)) {
                return Blocks.AIR.getDefaultState();
            }
            return this.getDefaultState().with(ATTACHED, true).with(FACING, isNorth ? Direction.NORTH : isEast ? Direction.EAST : isSouth ? Direction.SOUTH : Direction.WEST);
        } else {
            return stateIn;
        }
    }

    private boolean isAttachable(BlockState p_220113_1_) {
        Block lvt_4_1_ = p_220113_1_.getBlock();
        boolean isCactus = lvt_4_1_ == ModBlocks.SAGUARO_STEM.get();
        return isCactus;
    }

    public boolean isValidPosition(BlockState state, IWorldReader worldIn, BlockPos pos) {
        BlockPos north = pos.north();
        BlockPos east = pos.east();
        BlockPos south = pos.south();
        BlockPos west = pos.west();
        BlockState northState = worldIn.getBlockState(north);
        BlockState eastState = worldIn.getBlockState(east);
        BlockState southState = worldIn.getBlockState(south);
        BlockState westState = worldIn.getBlockState(west);
        boolean isNorth = isAttachable(northState);
        boolean isEast = isAttachable(eastState);
        boolean isSouth = isAttachable(southState);
        boolean isWest = isAttachable(westState);
        return isNorth || isSouth || isEast || isWest || isValidGround(state, worldIn, pos);
    }

    public boolean isValidGround(BlockState state, IBlockReader worldIn, BlockPos pos) {
        Block block = state.getBlock();
        return block.isIn(BlockTags.BAMBOO_PLANTABLE_ON) || block == ModBlocks.SAGUARO_ARM.get();
    }

    @SuppressWarnings("deprecation")
    public PushReaction getPushReaction(BlockState p_149656_1_) {
        return PushReaction.DESTROY;
    }

    static {
        ATTACHED = BlockStateProperties.ATTACHED;
        FACING = HorizontalBlock.HORIZONTAL_FACING;
        ATTACHED_SHAPE = Block.makeCuboidShape(4.0D, 0.0D, 4.0D, 12.0D, 16.0D, 12.0D);
        TOP_SHAPE = Block.makeCuboidShape(4.0D, 0.0D, 4.0D, 12.0D, 16.0D, 12.0D);
    }
}
