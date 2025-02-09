package com.wdiscute.laicaps.block.custom;

import com.mojang.serialization.MapCodec;
import com.wdiscute.component.ModDataComponentTypes;
import com.wdiscute.laicaps.block.ModBlockEntity;
import com.wdiscute.laicaps.block.ModBlocks;
import com.wdiscute.laicaps.blockentity.ReceiverBlockEntity;
import com.wdiscute.laicaps.blockentity.SymbolPuzzleBlockEntity;
import com.wdiscute.laicaps.item.ModItems;
import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.ItemInteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.phys.BlockHitResult;
import org.jetbrains.annotations.Nullable;

public class SymbolPuzzleBlock extends HorizontalDirectionalBlock implements EntityBlock
{
    public SymbolPuzzleBlock(Properties properties)
    {
        super(properties);
    }


    public static final EnumProperty<SymbolsEnum> SYMBOLS = EnumProperty.create("symbol", SymbolsEnum.class);

    private static SymbolsEnum CycleSymbol(SymbolsEnum sym)
    {

        if (sym == SymbolsEnum.ONE) return SymbolsEnum.TWO;
        if (sym == SymbolsEnum.TWO) return SymbolsEnum.THREE;
        if (sym == SymbolsEnum.THREE) return SymbolsEnum.FOUR;
        if (sym == SymbolsEnum.FOUR) return SymbolsEnum.FIVE;
        if (sym == SymbolsEnum.FIVE) return SymbolsEnum.SIX;
        if (sym == SymbolsEnum.SIX) return SymbolsEnum.SEVEN;
        if (sym == SymbolsEnum.SEVEN) return SymbolsEnum.EIGHT;
        if (sym == SymbolsEnum.EIGHT) return SymbolsEnum.NINE;
        if (sym == SymbolsEnum.NINE) return SymbolsEnum.TEN;

        return SymbolsEnum.ONE;


    }

    @Override
    protected ItemInteractionResult useItemOn(ItemStack pStack, BlockState pState, Level pLevel, BlockPos pPos, Player pPlayer, InteractionHand pHand, BlockHitResult pHitResult)
    {
        if (!pLevel.isClientSide() && pStack.getItem() == ModItems.CHISEL.get())
        {
            BlockEntity be = pLevel.getBlockEntity(pPos);
            if (be instanceof SymbolPuzzleBlockEntity blockEntity)
            {
                blockEntity.setBlockLinked(pStack.get(ModDataComponentTypes.COORDINATES.get()));
                return ItemInteractionResult.SUCCESS;
            }
        }

        if (!pLevel.isClientSide())
        {
            SymbolsEnum next = CycleSymbol(pState.getValue(SYMBOLS));
            pLevel.setBlockAndUpdate(pPos, pState.setValue(SYMBOLS, next));

            BlockEntity be = pLevel.getBlockEntity(pPos);
            if (be instanceof SymbolPuzzleBlockEntity blockEntity)
            {
                if (pLevel.getBlockState(blockEntity.getBlockLinked()).getBlock() == ModBlocks.SYMBOL_PUZZLE_BLOCK_INACTIVE.get())
                {
                    System.out.println("worked");
                }
            }
        }
        return ItemInteractionResult.SUCCESS;
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext pContext)
    {

        this.defaultBlockState().setValue(SYMBOLS, SymbolsEnum.ONE);
        return this.defaultBlockState().setValue(FACING, pContext.getHorizontalDirection().getOpposite());

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
        pBuilder.add(SYMBOLS);
        pBuilder.add(FACING);
    }


    @Override
    public @Nullable BlockEntity newBlockEntity(BlockPos pPos, BlockState pState)
    {
        return ModBlockEntity.SYMBOL_PUZZLE_BLOCK.get().create(pPos, pState);
    }
}
