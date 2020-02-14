package com.dreamscape.saguaro.core.registry;

import com.dreamscape.saguaro.core.Saguaro;
import com.google.common.base.Supplier;
import net.minecraft.item.Item;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModItems {
    public static final DeferredRegister<Item> ITEMS = new DeferredRegister<>(ForgeRegistries.ITEMS, Saguaro.MOD_ID);

    public static <I extends Item> RegistryObject<I> createItem(String name, Supplier<? extends I> supplier) {
        RegistryObject<I> item = ModItems.ITEMS.register(name, supplier);
        return item;
    }
}
