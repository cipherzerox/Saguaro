package com.dreamscape.saguaro.common.block;

import com.dreamscape.saguaro.core.registry.ModSoundEvents;
import net.minecraft.block.BlockState;
import net.minecraft.block.SoundType;
import net.minecraft.block.StairsBlock;

import java.util.function.Supplier;

public class SaguaroStairsBlock extends StairsBlock {
    public SaguaroStairsBlock(Supplier<BlockState> blockStateSupplier, Properties properties) {
        super(blockStateSupplier, properties);
    }

    @Override
    @SuppressWarnings("deprecation")
    public SoundType getSoundType(BlockState p_220072_1_) {
        return ModSoundEvents.ModSoundTypes.SAGUARO;
    }
}
