package com.wdiscute.laicaps.blockentity;

import com.wdiscute.laicaps.block.ModBlockEntity;
import com.wdiscute.laicaps.block.ModBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class ReceiverBlockEntity extends BlockEntity implements TickableBlockEntity
{
    private int counter = 0;
    private Direction directionBeingChanged = Direction.NORTH;

    public Direction getDirection()
    {
        return directionBeingChanged;
    }

    public void setDirection(Direction dir)
    {
        directionBeingChanged = dir;
    }


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
        this.setDirection(Direction.byName(pTag.getString("dir")));
    }

    @Override
    protected void saveAdditional(CompoundTag pTag, HolderLookup.Provider pRegistries)
    {
        pTag.putString("dir", directionBeingChanged.getName());
    }
}
