package com.dreamscape.saguaro.common.block;

import com.dreamscape.saguaro.core.registry.ModBlocks;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.RotatedPillarBlock;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.AxeItem;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraftforge.common.ToolType;

public class SaguaroStemBlock extends RotatedPillarBlock {
    public SaguaroStemBlock(Properties p_i48339_1_) {
        super(p_i48339_1_);
    }

    @Override
    public int getFlammability(BlockState state, IBlockReader world, BlockPos pos, Direction face) {
        return 60;
    }

    @Override
    public boolean isFireSource(BlockState state, IBlockReader world, BlockPos pos, Direction side) {
        return false;
    }

    @Override
    public ToolType getHarvestTool(BlockState state) {
        return ToolType.AXE;
    }

    @Override
    @SuppressWarnings("deprecation")
    public ActionResultType func_225533_a_(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand hand, BlockRayTraceResult rayTraceResult) {
        Block block = state.getBlock();
        if((block == ModBlocks.SAGUARO_STEM.get() || block == ModBlocks.SAGUARO.get()) && player.getHeldItemMainhand().getItem() instanceof AxeItem) {
            worldIn.playSound(player, pos, SoundEvents.ITEM_AXE_STRIP, SoundCategory.BLOCKS, 2.0F, 1.0F);
            if (block == ModBlocks.SAGUARO_STEM.get()) {
                worldIn.setBlockState(pos, ModBlocks.STRIPPED_SAGUARO_STEM.get().getDefaultState().with(AXIS, state.get(AXIS)));
            } else {
                worldIn.setBlockState(pos, ModBlocks.STRIPPED_SAGUARO.get().getDefaultState().with(AXIS, state.get(AXIS)));
            }
            return ActionResultType.SUCCESS;
        }
        return super.func_225533_a_(state, worldIn, pos, player, hand, rayTraceResult);
    }
}
