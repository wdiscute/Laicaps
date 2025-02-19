package com.wdiscute.laicaps.blockentity;

import com.wdiscute.laicaps.block.ModBlockEntity;
import com.wdiscute.laicaps.block.ModBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

import javax.sound.midi.SysexMessage;
import java.util.Objects;

public class SymbolControllerBlockEntity extends BlockEntity implements TickableBlockEntity
{
    private int counter = 0;

    private BlockPos link1 = new BlockPos(0, 0, 0);
    private BlockPos link2 = new BlockPos(0, 0, 0);
    private BlockPos link3 = new BlockPos(0, 0, 0);
    private BlockPos link4 = new BlockPos(0, 0, 0);
    private BlockPos link5 = new BlockPos(0, 0, 0);
    private BlockPos link6 = new BlockPos(0, 0, 0);
    private BlockPos link7 = new BlockPos(0, 0, 0);
    private BlockPos link8 = new BlockPos(0, 0, 0);
    private BlockPos link9 = new BlockPos(0, 0, 0);
    private BlockPos link10 = new BlockPos(0, 0, 0);


    public BlockPos getLinkedBlock(int blockposlink)
    {
        return switch (blockposlink)
        {
            case 1 -> link1;
            case 2 -> link2;
            case 3 -> link3;
            case 4 -> link4;
            case 5 -> link5;
            case 6 -> link6;
            case 7 -> link7;
            case 8 -> link8;
            case 9 -> link9;
            default -> link10;
        };
    }

    public void reset()
    {
        BlockPos zero = new BlockPos(0,0,0);
        link1 = zero;
        link2 = zero;
        link3 = zero;
        link4 = zero;
        link5 = zero;
        link6 = zero;
        link7 = zero;
        link8 = zero;
        link9 = zero;
        link10 = zero;
    }

    public void setNextLinkedBlock(BlockPos blockPos)
    {
        System.out.println("entered setNextLinkedBlock");
        BlockPos blockPosZero = new BlockPos(0, 0, 0);
        if (Objects.equals(link1, blockPosZero))
        {
            System.out.println("set link1 to " + blockPos);
            link1 = blockPos;
            System.out.println("link1 is " + link1);
            return;
        }
        if (Objects.equals(link2, blockPosZero))
        {
            System.out.println("set link2 to " + blockPos);
            link2 = blockPos;
            return;
        }
        if (Objects.equals(link3, blockPosZero))
        {
            System.out.println("set link3 to " + blockPos);
            link3 = blockPos;
            return;
        }
        if (Objects.equals(link4, blockPosZero))
        {
            System.out.println("set link4 to " + blockPos);
            link4 = blockPos;
            return;
        }
        if (Objects.equals(link5, blockPosZero))
        {
            System.out.println("set link5 to " + blockPos);
            link5 = blockPos;
            return;
        }
        if (Objects.equals(link6, blockPosZero))
        {
            System.out.println("set link6 to " + blockPos);
            link6 = blockPos;
            return;
        }
        if (Objects.equals(link7, blockPosZero))
        {
            System.out.println("set link7 to " + blockPos);
            link7 = blockPos;
            return;
        }
        if (Objects.equals(link8, blockPosZero))
        {
            System.out.println("set link8 to " + blockPos);
            link8 = blockPos;
            return;
        }
        if (Objects.equals(link9, blockPosZero))
        {
            System.out.println("set link9 to " + blockPos);
            link9 = blockPos;
            return;
        }
        if (Objects.equals(link10, blockPosZero))
        {
            System.out.println("set link10 to " + blockPos);
            link10 = blockPos;
        }
    }


    @Override
    public void tick()
    {
        if (this.level == null || this.level.isClientSide()) return;
        counter++;
        if (counter % 20 == 0)
        {
            this.level.scheduleTick(this.getBlockPos(), ModBlocks.SYMBOL_CONTROLLER_BLOCK.get(), 1);
        }
    }

    public SymbolControllerBlockEntity(BlockPos pPos, BlockState pBlockState)
    {
        super(ModBlockEntity.SYMBOL_CONTROLLER_BLOCK.get(), pPos, pBlockState);
    }

    @Override
    protected void loadAdditional(CompoundTag pTag, HolderLookup.Provider pRegistries)
    {
        link1 = new BlockPos(pTag.getIntArray("link1")[0] , pTag.getIntArray("link1")[1], pTag.getIntArray("link1")[2]);
        link2 = new BlockPos(pTag.getIntArray("link2")[0] , pTag.getIntArray("link2")[1], pTag.getIntArray("link2")[2]);
        link3 = new BlockPos(pTag.getIntArray("link3")[0] , pTag.getIntArray("link3")[1], pTag.getIntArray("link3")[2]);
        link4 = new BlockPos(pTag.getIntArray("link4")[0] , pTag.getIntArray("link4")[1], pTag.getIntArray("link4")[2]);
        link5 = new BlockPos(pTag.getIntArray("link5")[0] , pTag.getIntArray("link5")[1], pTag.getIntArray("link5")[2]);
        link6 = new BlockPos(pTag.getIntArray("link6")[0] , pTag.getIntArray("link6")[1], pTag.getIntArray("link6")[2]);
        link7 = new BlockPos(pTag.getIntArray("link7")[0] , pTag.getIntArray("link7")[1], pTag.getIntArray("link7")[2]);
        link8 = new BlockPos(pTag.getIntArray("link8")[0] , pTag.getIntArray("link8")[1], pTag.getIntArray("link8")[2]);
        link9 = new BlockPos(pTag.getIntArray("link9")[0] , pTag.getIntArray("link9")[1], pTag.getIntArray("link9")[2]);
        link10 = new BlockPos(pTag.getIntArray("link10")[0] , pTag.getIntArray("link10")[1], pTag.getIntArray("link10")[2]);
    }

    @Override
    protected void saveAdditional(CompoundTag pTag, HolderLookup.Provider pRegistries)
    {
        pTag.putIntArray("link1", new int[]{link1.getX(), link1.getY(), link1.getZ()});
        pTag.putIntArray("link2", new int[]{link2.getX(), link2.getY(), link2.getZ()});
        pTag.putIntArray("link3", new int[]{link3.getX(), link3.getY(), link3.getZ()});
        pTag.putIntArray("link4", new int[]{link4.getX(), link4.getY(), link4.getZ()});
        pTag.putIntArray("link5", new int[]{link5.getX(), link5.getY(), link5.getZ()});
        pTag.putIntArray("link6", new int[]{link6.getX(), link6.getY(), link6.getZ()});
        pTag.putIntArray("link7", new int[]{link7.getX(), link7.getY(), link7.getZ()});
        pTag.putIntArray("link8", new int[]{link8.getX(), link8.getY(), link8.getZ()});
        pTag.putIntArray("link9", new int[]{link9.getX(), link9.getY(), link9.getZ()});
        pTag.putIntArray("link10", new int[]{link10.getX(), link10.getY(), link10.getZ()});
    }
}
