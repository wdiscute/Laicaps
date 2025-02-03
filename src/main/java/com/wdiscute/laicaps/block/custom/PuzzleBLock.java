package com.wdiscute.laicaps.block.custom;

import com.mojang.serialization.MapCodec;
import com.wdiscute.laicaps.block.ModBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.phys.BlockHitResult;

public class PuzzleBLock extends HorizontalDirectionalBlock
{
    public PuzzleBLock(Properties properties)
    {
        super(properties);
    }

    @Override
    protected InteractionResult useWithoutItem(BlockState pState, Level pLevel, BlockPos pPos, Player pPlayer, BlockHitResult pHitResult)
    {
        if (!pLevel.isClientSide())
        {
            if (pState.getValue(FACING) == Direction.NORTH)
            {
                pLevel.setBlockAndUpdate(pPos, pState.setValue(FACING, Direction.EAST));
            }
            if (pState.getValue(FACING) == Direction.EAST)
            {
                pLevel.setBlockAndUpdate(pPos, pState.setValue(FACING, Direction.SOUTH));
            }
            if (pState.getValue(FACING) == Direction.SOUTH)
            {
                pLevel.setBlockAndUpdate(pPos, pState.setValue(FACING, Direction.WEST));
            }
            if (pState.getValue(FACING) == Direction.WEST)
            {
                pLevel.setBlockAndUpdate(pPos, pState.setValue(FACING, Direction.NORTH));
            }


            System.out.println("puzzle block level is " + pLevel);
            pLevel.scheduleTick(pPos.east(), ModBlocks.RECEIVER_BLOCK.get(), 1);
            pLevel.scheduleTick(pPos.east().east(), ModBlocks.RECEIVER_BLOCK.get(), 1);
            pLevel.scheduleTick(pPos.east().east().east(), ModBlocks.RECEIVER_BLOCK.get(), 1);
            pLevel.scheduleTick(pPos.east().east().east().east(), ModBlocks.RECEIVER_BLOCK.get(), 1);
            pLevel.scheduleTick(pPos.east().east().east().east().east(), ModBlocks.RECEIVER_BLOCK.get(), 1);
            pLevel.scheduleTick(pPos.east().east().east().east().east().east(), ModBlocks.RECEIVER_BLOCK.get(), 1);
            pLevel.scheduleTick(pPos.east().east().east().east().east().east().east(), ModBlocks.RECEIVER_BLOCK.get(), 1);
            pLevel.scheduleTick(pPos.east().east().east().east().east().east().east().east(), ModBlocks.RECEIVER_BLOCK.get(), 1);
            pLevel.scheduleTick(pPos.east().east().east().east().east().east().east().east().east(), ModBlocks.RECEIVER_BLOCK.get(), 1);
            pLevel.scheduleTick(pPos.east().east().east().east().east().east().east().east().east().east(), ModBlocks.RECEIVER_BLOCK.get(), 1);


        }
        return InteractionResult.SUCCESS;
    }

    @Override
    protected MapCodec<? extends HorizontalDirectionalBlock> codec()
    {
        return null;
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> pBuilder)
    {
        pBuilder.add(FACING);
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext pContext)
    {
        return this.defaultBlockState().setValue(FACING, pContext.getHorizontalDirection().getOpposite());
    }
}




