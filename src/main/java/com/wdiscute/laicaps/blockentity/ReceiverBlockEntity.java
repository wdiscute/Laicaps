package com.wdiscute.laicaps.blockentity;

import com.wdiscute.laicaps.block.ModBlockEntity;
import com.wdiscute.laicaps.block.ModBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

import java.util.Random;

public class ReceiverBlockEntity extends BlockEntity implements TickableBlockEntity
{
    private int counter = 0;
    private int tickOffset = 0;


    @Override
    public void tick()
    {
        if (this.level == null || this.level.isClientSide()) return;

        if(this.tickOffset == 0){
            this.tickOffset = (int)(Math.random() * 20 + 1);
        }
        counter++;

        if ((counter + this.tickOffset) % 20 == 0)
        {
            this.level.scheduleTick(this.getBlockPos(), ModBlocks.RECEIVER_BLOCK.get(), 1);
        }

        if(counter > 10000000) counter = 0;

    }


    public ReceiverBlockEntity(BlockPos pPos, BlockState pBlockState)
    {
        super(ModBlockEntity.RECEIVER_BLOCK.get(), pPos, pBlockState);
    }

    @Override
    protected void loadAdditional(CompoundTag pTag, HolderLookup.Provider pRegistries)
    {
    }

    @Override
    protected void saveAdditional(CompoundTag pTag, HolderLookup.Provider pRegistries)
    {
    }
}
