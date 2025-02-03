package com.wdiscute.laicaps.block.custom;

import com.wdiscute.laicaps.block.ModBlocks;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.phys.BlockHitResult;

public class ReceiverBlock extends Block
{
    public static final BooleanProperty ACTIVE = BooleanProperty.create("active");


    private void ConfirmActive(Level level, BlockPos pos)
    {
        level.setBlockAndUpdate(pos, level.getBlockState(pos).setValue(ACTIVE, true));
    }

    @Override
    protected void tick(BlockState pState, ServerLevel pLevel, BlockPos pPos, RandomSource pRandom)
    {
        for (int k = 0; k < 4; k++)
        {
            Direction dir = Direction.NORTH;
            if(k == 1) dir = Direction.EAST;
            if(k == 2) dir = Direction.SOUTH;
            if(k == 3) dir = Direction.WEST;

            for (int i = 0; i <= 10; i++)
            {
                // initialize a new variable currentPos which will be given a .direction.bla() for each i increase
                BlockPos currentPos = pPos.west();
                for (int j = 0; j < i; j++)
                {
                    currentPos = currentPos.relative(dir);
                }
                BlockState blockStateBeingTested = pLevel.getBlockState(currentPos);

                if (blockStateBeingTested.getBlock().defaultBlockState() == ModBlocks.PUZZLE_BLOCK.get().defaultBlockState()
                        && blockStateBeingTested.getValue(HorizontalDirectionalBlock.FACING) == dir.getOpposite())
                {
                    ConfirmActive(pLevel, pPos);
                }

                //if block is not air
                if (!blockStateBeingTested.getBlock().defaultBlockState().isAir())
                {
                    break;
                }
            }
        }
    }


    @Override
    protected InteractionResult useWithoutItem(BlockState pState, Level pLevel, BlockPos pPos, Player
            pPlayer, BlockHitResult pHitResult)
    {

        if (!pLevel.isClientSide())
        {
            boolean currentState = pState.getValue(ACTIVE);
            pLevel.setBlockAndUpdate(pPos, pState.setValue(ACTIVE, !currentState));
        }
        return InteractionResult.SUCCESS;
    }

    public ReceiverBlock(Properties properties)
    {
        super(properties);
        this.registerDefaultState(this.defaultBlockState().setValue(ACTIVE, false));
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> pBuilder)
    {
        pBuilder.add(ACTIVE);
    }

}




