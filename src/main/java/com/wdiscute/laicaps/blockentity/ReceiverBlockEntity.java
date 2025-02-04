package com.wdiscute.laicaps.blockentity;

import com.wdiscute.laicaps.block.ModBlockEntity;
import com.wdiscute.laicaps.block.ModBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class ReceiverBlockEntity extends BlockEntity implements TickableBlockEntity
{
    private int counter = 0;

    @Override
    public void tick()
    {
        if (this.level == null || this.level.isClientSide()) return;
        counter++;
        if (counter % 20 == 0)
        {
            this.level.scheduleTick(this.getBlockPos(), ModBlocks.RECEIVER_BLOCK.get(), 1);
        }



    }


    public ReceiverBlockEntity(BlockPos pPos, BlockState pBlockState)
    {
        super(ModBlockEntity.RECEIVER_BLOCK.get(), pPos, pBlockState);
    }


    //private int counter;
    //public int IncrementCounter(){
    //    ++this.counter;
    //    setChanged();
    //    return this.counter;
    //}
    @Override
    protected void loadAdditional(CompoundTag pTag, HolderLookup.Provider pRegistries)
    {
        super.loadAdditional(pTag, pRegistries);
        //this.counter = pTag.getInt("Counter");
    }

    @Override
    protected void saveAdditional(CompoundTag pTag, HolderLookup.Provider pRegistries)
    {
        super.saveAdditional(pTag, pRegistries);
        //pTag.putInt("Counter", this.counter);
    }
}
