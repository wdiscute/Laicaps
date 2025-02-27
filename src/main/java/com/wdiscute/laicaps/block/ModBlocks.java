package com.wdiscute.laicaps.block;

import com.wdiscute.laicaps.Laicaps;
import com.wdiscute.laicaps.block.custom.*;
import com.wdiscute.laicaps.item.ModItems;
import com.wdiscute.laicaps.sound.ModSounds;
import com.wdiscute.laicaps.worldgen.tree.ModTreeGrowers;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraft.world.level.block.state.properties.WoodType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

public class ModBlocks
{
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, Laicaps.MOD_ID);

    public static final RegistryObject<Block> ALEXENDRITE_BLOCK =
            registryObject("alexandrite_block", () ->
                    new Block(BlockBehaviour.Properties.of()
                            .requiresCorrectToolForDrops()
                            .strength(4f, 4f)
                            .noCollission()
                            .replaceable()
                            .sound(SoundType.TUFF)
                    )
            );

    public static final RegistryObject<Block> RAW_ALEXENDRITE_BLOCK =
            registryObject("raw_alexandrite_block", () ->
                    new Block(BlockBehaviour.Properties.of()
                            .requiresCorrectToolForDrops()
                            .strength(4f, 4f)
                            .sound(SoundType.BONE_BLOCK)
                    )
            );

    public static final RegistryObject<Block> ALEXENDRITE_ORE =
            registryObject("alexandrite_ore", () ->
                    new DropExperienceBlock(UniformInt.of(2, 4),BlockBehaviour.Properties.of()
                            .requiresCorrectToolForDrops()
                            .strength(4f, 6f)
                    )
            );

    public static final RegistryObject<Block> ALEXENDRITE_DEEPSLATE_ORE =
            registryObject("alexandrite_deepslate_ore", () ->
                    new DropExperienceBlock(UniformInt.of(2, 4),BlockBehaviour.Properties.of()
                            .requiresCorrectToolForDrops()
                            .strength(5f, 7f)
                    )
            );

    public static final RegistryObject<Block> MAGIC_BLOCK =
            registryObject("magic_block", () ->
                    new MagicBlock(BlockBehaviour.Properties.of()
                            .strength(5f, 7f)
                            .sound(SoundType.AMETHYST)
                    )
            );

    public static final RegistryObject<StairBlock> ALEXANDRITE_STAIRS =
            registryObject("alexandrite_stairs", () ->
                    new StairBlock(ModBlocks.ALEXENDRITE_BLOCK.get().defaultBlockState(), BlockBehaviour.Properties.of()
                    .strength(3)
                    .requiresCorrectToolForDrops()
                    )
            );

    public static final RegistryObject<SlabBlock> ALEXANDRITE_SLAB =
            registryObject("alexandrite_slab", () ->
                    new SlabBlock(BlockBehaviour.Properties.of()
                            .strength(3)
                            .requiresCorrectToolForDrops()
                    )
            );

    public static final RegistryObject<PressurePlateBlock> ALEXANDRITE_PRESSURE_PLATE =
            registryObject("alexandrite_pressure_plate", () ->
                    new PressurePlateBlock(BlockSetType.IRON, BlockBehaviour.Properties.of()
                            .strength(3)
                            .requiresCorrectToolForDrops()
                    )
            );

    public static final RegistryObject<ButtonBlock> ALEXANDRITE_BUTTON =
            registryObject("alexandrite_button", () ->
                    new ButtonBlock(BlockSetType.IRON, 20, BlockBehaviour.Properties.of()
                            .strength(3)
                            .requiresCorrectToolForDrops()
                            .noCollission()
                    )
            );

    public static final RegistryObject<FenceBlock> ALEXANDRITE_FENCE =
            registryObject("alexandrite_fence", () ->
                    new FenceBlock(BlockBehaviour.Properties.of()
                            .strength(3)
                            .requiresCorrectToolForDrops()
                    )
            );

    public static final RegistryObject<FenceGateBlock> ALEXANDRITE_FENCE_GATE =
            registryObject("alexandrite_fence_gate", () ->
                    new FenceGateBlock(WoodType.ACACIA, BlockBehaviour.Properties.of()
                            .strength(3)
                            .requiresCorrectToolForDrops()
                    )
            );

    public static final RegistryObject<WallBlock> ALEXANDRITE_WALL =
            registryObject("alexandrite_wall", () ->
                    new WallBlock(BlockBehaviour.Properties.of()
                            .strength(3)
                            .requiresCorrectToolForDrops()
                    )
            );

    public static final RegistryObject<DoorBlock> ALEXANDRITE_DOOR =
            registryObject("alexandrite_door", () ->
                    new DoorBlock(BlockSetType.OAK, BlockBehaviour.Properties.of()
                            .strength(3)
                            .requiresCorrectToolForDrops()
                            .noOcclusion() //noOcclusion needs to be set otherwise transparent pixels will make the world see through like xray
                    )
            );

    public static final RegistryObject<TrapDoorBlock> ALEXANDRITE_TRAPDOOR =
            registryObject("alexandrite_trapdoor", () ->
                    new TrapDoorBlock(BlockSetType.OAK, BlockBehaviour.Properties.of()
                            .strength(3)
                            .requiresCorrectToolForDrops()
                            .noOcclusion() //noOcclusion needs to be set otherwise transparent pixels will make the world see through like xray
                    )
            );

    public static final RegistryObject<Block> ALEXANDRITE_LAMP =
            registryObject("alexandrite_lamp", () ->
                    new AlexandriteLampBlock(BlockBehaviour.Properties.of()
                            .strength(3)
                            .requiresCorrectToolForDrops()
                            .lightLevel(state -> state.getValue(AlexandriteLampBlock.CLICKED) ? 15 : 0)
                    )
            );

    public static final RegistryObject<Block> SENDER_PUZZLE_BLOCK =
            registryObject("sender_puzzle_block", () ->
                    new SenderPuzzleBLock(BlockBehaviour.Properties.of()
                            .strength(30)
                            .sound(ModSounds.MAGIC_BLOCK_SOUNDS)
                    )
            );

    public static final RegistryObject<ReceiverBlock> RECEIVER_BLOCK =
            registryObject("receiver_block", () ->
                    new ReceiverBlock(BlockBehaviour.Properties.of()
                            .strength(30)
                            .sound(ModSounds.MAGIC_BLOCK_SOUNDS)
                    )
            );

    public static final RegistryObject<Block> SYMBOL_PUZZLE_BLOCK =
            registryObject("symbol_puzzle_block", () ->
                    new SymbolPuzzleBlock(BlockBehaviour.Properties.of()
                            .strength(30)
                            .sound(ModSounds.MAGIC_BLOCK_SOUNDS)
                    )
            );

    public static final RegistryObject<Block> SYMBOL_CONTROLLER_BLOCK =
            registryObject("symbol_controller_block", () ->
                    new SymbolControllerBlock(BlockBehaviour.Properties.of()
                            .strength(30)
                            .sound(ModSounds.MAGIC_BLOCK_SOUNDS)
                    )
            );


    public static final RegistryObject<Block> SYMBOL_PUZZLE_BLOCK_INACTIVE =
            registryObject("symbol_puzzle_block_inactive", () ->
                    new SymbolPuzzleBlockInactive(BlockBehaviour.Properties.of()
                            .strength(30)
                            .sound(ModSounds.MAGIC_BLOCK_SOUNDS)
                    )
            );


    //sapling requires tree grower which is being provided on ModTreeGrowers
    //we use ModSaplingBlock so we can change what block it can be placed/grown on etc
    public static final RegistryObject<Block> WALNUT_SAPLING =
            registryObject("walnut_sapling", () ->
                    new ModSaplingBlock(ModTreeGrowers.WALNUT, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_SAPLING), () -> Blocks.GRASS_BLOCK));


    public static final RegistryObject<Block> OAKHEART_SAPLING =
            registryObject("oakheart_sapling", () ->
                    new ModSaplingBlock(ModTreeGrowers.OAKHEART, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_SAPLING), () -> Blocks.GRASS_BLOCK));










    private static <T extends Block> RegistryObject<T> registryObject(String name, Supplier<T> block){
        RegistryObject<T> toReturn = BLOCKS.register(name, block);
        ModItems.ITEMS.register(name, () -> new BlockItem(toReturn.get(), new Item.Properties()));
        return toReturn;
    }

    public static void register(IEventBus eventBus)
    {
        BLOCKS.register(eventBus);
    }
}
