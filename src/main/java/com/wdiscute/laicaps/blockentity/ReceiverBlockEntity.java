package com.wdiscute.laicaps.blockentity;

import com.wdiscute.laicaps.block.ModBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class ReceiverBlockEntity extends BlockEntity
{
    private int counter;

    public ReceiverBlockEntity(BlockPos pPos, BlockState pBlockState)
    {
        super(ModBlockEntity.RECEIVER_BLOCK.get(), pPos, pBlockState);
    }

    @Override
    protected void loadAdditional(CompoundTag pTag, HolderLookup.Provider pRegistries)
    {
        super.loadAdditional(pTag, pRegistries);
    }
}
