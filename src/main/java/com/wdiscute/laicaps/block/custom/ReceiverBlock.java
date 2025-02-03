package com.wdiscute.laicaps.block.custom;

import com.mojang.serialization.MapCodec;
import com.wdiscute.laicaps.block.ModBlockEntity;
import com.wdiscute.laicaps.blockentity.ReceiverBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.phys.BlockHitResult;
import org.jetbrains.annotations.Nullable;


public class ReceiverBlock extends HorizontalDirectionalBlock implements EntityBlock
{

    @Override
    protected InteractionResult useWithoutItem(BlockState pState, Level pLevel, BlockPos pPos, Player pPlayer, BlockHitResult pHitResult)
    {
        if(!pLevel.isClientSide)
        {
            BlockEntity be = pLevel.getBlockEntity(pPos);
            if(be instanceof ReceiverBlockEntity bloclEntity){
                int counter = bloclEntity.incrementCounter();
                pPlayer.sendSystemMessage(Component.literal("BlockEntity has been used %d times".formatted(counter)));
            }
        }
    }

    public ReceiverBlock(Properties properties) {
        super(properties);
    }

    @Override
    protected MapCodec<? extends HorizontalDirectionalBlock> codec()
    {
        return null;
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> pBuilder)
    {
        super.createBlockStateDefinition(pBuilder);
        pBuilder.add(FACING);
    }

    @Override
    public @Nullable BlockEntity newBlockEntity(BlockPos pPos, BlockState pState)
    {
        return ModBlockEntity.RECEIVER_BLOCK.get().create(pPos, pState);
    }
}




