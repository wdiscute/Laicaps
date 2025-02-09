package com.wdiscute.laicaps.block;

import com.wdiscute.laicaps.Laicaps;
import com.wdiscute.laicaps.blockentity.ReceiverBlockEntity;
import com.wdiscute.laicaps.blockentity.SymbolPuzzleBlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModBlockEntity
{
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES =
            DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, Laicaps.MOD_ID);

    public static final RegistryObject<BlockEntityType<ReceiverBlockEntity>> RECEIVER_BLOCK = BLOCK_ENTITIES.register("receiver_block",
            () -> BlockEntityType.Builder.of(ReceiverBlockEntity::new, ModBlocks.RECEIVER_BLOCK.get())
                    .build(null));

    public static final RegistryObject<BlockEntityType<SymbolPuzzleBlockEntity>> SYMBOL_PUZZLE_BLOCK = BLOCK_ENTITIES.register("symbol_puzzle_block",
            () -> BlockEntityType.Builder.of(SymbolPuzzleBlockEntity::new, ModBlocks.SYMBOL_PUZZLE_BLOCK.get())
                    .build(null));



}
