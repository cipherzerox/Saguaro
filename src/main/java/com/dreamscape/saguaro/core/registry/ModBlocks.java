package com.dreamscape.saguaro.core.registry;

import com.dreamscape.saguaro.core.Saguaro;
import com.google.common.base.Supplier;
import net.minecraft.block.*;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModBlocks {
    public static final DeferredRegister<Block> BLOCKS = new DeferredRegister<>(ForgeRegistries.BLOCKS, Saguaro.MOD_ID);

    public static final RegistryObject<Block> SAGUARO_STEM              = createBlock("saguaro_stem", () -> new LogBlock(MaterialColor.GREEN, Block.Properties.create(Material.CACTUS, MaterialColor.OBSIDIAN).hardnessAndResistance(2.0F).sound(SoundType.CLOTH)), ItemGroup.BUILDING_BLOCKS);
    public static final RegistryObject<Block> STRIPPED_SAGUARO_STEM     = createBlock("stripped_saguaro_stem", () -> new LogBlock(MaterialColor.GREEN, Block.Properties.create(Material.CACTUS, MaterialColor.OBSIDIAN).hardnessAndResistance(2.0F).sound(SoundType.CLOTH)), ItemGroup.BUILDING_BLOCKS);
    public static final RegistryObject<Block> SAGUARO_PLANKS            = createBlock("saguaro_planks", () -> new Block(Block.Properties.create(Material.CACTUS, MaterialColor.GREEN).hardnessAndResistance(2.0F, 3.0F).sound(SoundType.CLOTH)), ItemGroup.BUILDING_BLOCKS);
    public static final RegistryObject<Block> SAGUARO_SLAB              = createBlock("saguaro_slab", () -> new SlabBlock(Block.Properties.create(Material.CACTUS, MaterialColor.GREEN).hardnessAndResistance(2.0F, 3.0F).sound(SoundType.CLOTH)), ItemGroup.BUILDING_BLOCKS);
    public static final RegistryObject<Block> SAGUARO_STAIRS            = createBlock("saguaro_stairs", () -> new StairsBlock(SAGUARO_PLANKS.get().getDefaultState(), Block.Properties.create(Material.CACTUS, MaterialColor.GREEN).hardnessAndResistance(2.0F, 3.0F).sound(SoundType.CLOTH)), ItemGroup.BUILDING_BLOCKS);
    public static final RegistryObject<Block> SAGUARO_FENCE             = createBlock("saguaro_fence", () -> new FenceBlock(Block.Properties.create(Material.CACTUS, MaterialColor.OBSIDIAN).hardnessAndResistance(2.0F).sound(SoundType.CLOTH)), ItemGroup.BUILDING_BLOCKS);
    public static final RegistryObject<Block> SAGUARO_FENCE_GATE        = createBlock("saguaro_fence_gate", () -> new FenceGateBlock(Block.Properties.create(Material.CACTUS, MaterialColor.OBSIDIAN).hardnessAndResistance(2.0F).sound(SoundType.CLOTH)), ItemGroup.BUILDING_BLOCKS);
    public static final RegistryObject<Block> SAGUARO_BUTTON            = createBlock("saguaro_stem_button", () -> new WoodButtonBlock(Block.Properties.create(Material.CACTUS, MaterialColor.OBSIDIAN).hardnessAndResistance(2.0F).sound(SoundType.CLOTH)), ItemGroup.BUILDING_BLOCKS);
    public static final RegistryObject<Block> SAGUARO_PRESSURE_PLATE    = createBlock("saguaro_pressure_plate", () -> new PressurePlateBlock(PressurePlateBlock.Sensitivity.EVERYTHING, Block.Properties.create(Material.CACTUS, MaterialColor.OBSIDIAN).hardnessAndResistance(2.0F).sound(SoundType.CLOTH)), ItemGroup.BUILDING_BLOCKS);
    public static final RegistryObject<Block> SAGUARO_DOOR              = createBlock("saguaro_door", () -> new DoorBlock(Block.Properties.create(Material.CACTUS, MaterialColor.OBSIDIAN).hardnessAndResistance(2.0F).sound(SoundType.CLOTH)), ItemGroup.BUILDING_BLOCKS);
    public static final RegistryObject<Block> SAGUARO_TRAPDOOR          = createBlock("saguaro_stem_trapdoor", () -> new TrapDoorBlock(Block.Properties.create(Material.CACTUS, MaterialColor.OBSIDIAN).hardnessAndResistance(2.0F).sound(SoundType.CLOTH)), ItemGroup.BUILDING_BLOCKS);

    public static <B extends Block> RegistryObject<B> createBlock(String name, Supplier<? extends B> supplier, ItemGroup itemGroup) {
        RegistryObject<B> block = ModBlocks.BLOCKS.register(name, supplier);
        ModItems.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties().group(itemGroup)));
        return block;
    }
}
