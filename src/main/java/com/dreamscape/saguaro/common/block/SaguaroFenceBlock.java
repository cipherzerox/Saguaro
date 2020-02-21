package com.dreamscape.saguaro.common.block;

import com.dreamscape.saguaro.core.registry.ModSoundEvents;
import net.minecraft.block.BlockState;
import net.minecraft.block.FenceBlock;
import net.minecraft.block.SoundType;

public class SaguaroFenceBlock extends FenceBlock {
    public SaguaroFenceBlock(Properties properties) {
        super(properties);
    }

    @Override
    @SuppressWarnings("deprecation")
    public SoundType getSoundType(BlockState p_220072_1_) {
        return ModSoundEvents.ModSoundTypes.SAGUARO;
    }
}
