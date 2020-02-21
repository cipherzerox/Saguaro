package com.dreamscape.saguaro.core.registry;

import com.dreamscape.saguaro.core.Saguaro;
import net.minecraft.block.SoundType;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.function.Supplier;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModSoundEvents {
    public static final DeferredRegister<SoundEvent> SOUNDS = new DeferredRegister<>(ForgeRegistries.SOUND_EVENTS, Saguaro.MOD_ID);

    public static final RegistryObject<SoundEvent> SAGUARO_BREAK    = createSound("block.saguaro.break");
    public static final RegistryObject<SoundEvent> SAGUARO_STEP     = createSound("block.saguaro.step");
    public static final RegistryObject<SoundEvent> SAGUARO_PLACE    = createSound("block.saguaro.place");
    public static final RegistryObject<SoundEvent> SAGUARO_HIT      = createSound("block.saguaro.hit");
    public static final RegistryObject<SoundEvent> SAGUARO_FALL     = createSound("block.saguaro.fall");

    public static <S extends SoundEvent> RegistryObject<S> createSound(String name) {
        RegistryObject<S> sound = ModSoundEvents.SOUNDS.register(name, () -> (S) new SoundEvent(new ResourceLocation(Saguaro.MOD_ID, name)));
        return sound;
    }

    public static class ModSoundTypes {
        public static final SoundType SAGUARO = new SoundType(1.0F, 1.0F, ModSoundEvents.SAGUARO_BREAK.get(), ModSoundEvents.SAGUARO_STEP.get(), ModSoundEvents.SAGUARO_PLACE.get(), ModSoundEvents.SAGUARO_HIT.get(), ModSoundEvents.SAGUARO_FALL.get());
    }
}

