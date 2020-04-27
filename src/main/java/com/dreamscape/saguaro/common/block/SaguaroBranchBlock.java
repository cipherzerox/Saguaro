package com.dreamscape.saguaro.common.block;

import com.dreamscape.saguaro.core.registry.ModBlocks;
import com.dreamscape.saguaro.core.registry.ModSoundEvents;
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
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.common.IPlantable;
import net.minecraftforge.common.PlantType;

import javax.annotation.Nullable;
import java.util.Random;

public class SaguaroBranchBlock extends BushBlock implements IGrowable {
    public static final DirectionProperty FACING;
    private static final VoxelShape DOWN_SHAPE;
    private static final VoxelShape EAST_SHAPE;
    private static final VoxelShape NORTH_SHAPE;
    private static final VoxelShape SOUTH_SHAPE;
    private static final VoxelShape WEST_SHAPE;

    public SaguaroBranchBlock(Properties p_i48437_1_) {
        super(p_i48437_1_);
        this.setDefaultState(this.stateContainer.getBaseState().with(FACING,  Direction.DOWN));
    }

    protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
        builder.add(FACING);
    }

    @SuppressWarnings("deprecation")
    public VoxelShape getShape(BlockState blockState, IBlockReader blockReader, BlockPos blockPos, ISelectionContext selectionContext) {
        switch(blockState.get(FACING)) {
            case NORTH:
                return NORTH_SHAPE;
            case SOUTH:
                return SOUTH_SHAPE;
            case WEST:
                return WEST_SHAPE;
            case EAST:
                return EAST_SHAPE;
            default:
                return DOWN_SHAPE;
        }
    }

    public BlockState getStateForPlacement(BlockItemUseContext context) {
        Direction direction = context.getFace().getOpposite();
        return this.getDefaultState().with(FACING, direction.getAxis() == Direction.Axis.Y ? Direction.DOWN : direction);
    }

    @SuppressWarnings("deprecation")
    public boolean isValidPosition(BlockState state, IWorldReader worldIn, BlockPos pos) {
        Direction direction = state.get(FACING);
        Block block = worldIn.getBlockState(pos.offset(direction)).getBlock();
        if (direction == Direction.DOWN) {
            return block.isIn(BlockTags.BAMBOO_PLANTABLE_ON) || block == ModBlocks.SAGUARO_BRANCH.get();
        }
        return block == ModBlocks.SAGUARO_STEM.get();
    }

    @SuppressWarnings("deprecation")
    public void neighborChanged(BlockState p_220069_1_, World p_220069_2_, BlockPos p_220069_3_, Block p_220069_4_, BlockPos p_220069_5_, boolean p_220069_6_) {
        this.updateState(p_220069_2_, p_220069_3_, p_220069_1_);
    }

    private void updateState(World p_176427_1_, BlockPos p_176427_2_, BlockState p_176427_3_) {

    }

    @SuppressWarnings("deprecation")
    public PushReaction getPushReaction(BlockState p_149656_1_) {
        return PushReaction.DESTROY;
    }

    @Override
    public void grow(ServerWorld serverWorld, Random random, BlockPos blockPos, BlockState blockState) {

    }

    @Override
    public boolean canGrow(IBlockReader iBlockReader, BlockPos blockPos, BlockState blockState, boolean b) {
        return false;
    }

    public boolean canUseBonemeal(World worldIn, Random rand, BlockPos pos, BlockState state) {
        return (double)worldIn.rand.nextFloat() < 0.45D;
    }

    @Override
    @SuppressWarnings("deprecation")
    public SoundType getSoundType(BlockState p_220072_1_) {
        return ModSoundEvents.ModSoundTypes.SAGUARO;
    }

    static {
        FACING = BlockStateProperties.FACING_EXCEPT_UP;
        DOWN_SHAPE = Block.makeCuboidShape(4.0D, 0.0D, 4.0D, 12.0D, 16.0D, 12.0D);
        EAST_SHAPE = Block.makeCuboidShape(4.0D, 0.0D, 4.0D, 12.0D, 16.0D, 12.0D);
        NORTH_SHAPE = Block.makeCuboidShape(4.0D, 0.0D, 4.0D, 12.0D, 16.0D, 12.0D);
        SOUTH_SHAPE = Block.makeCuboidShape(4.0D, 0.0D, 4.0D, 12.0D, 16.0D, 12.0D);
        WEST_SHAPE = Block.makeCuboidShape(4.0D, 0.0D, 4.0D, 12.0D, 16.0D, 12.0D);
    }
}
