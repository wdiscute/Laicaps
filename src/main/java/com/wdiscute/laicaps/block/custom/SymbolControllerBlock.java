package com.wdiscute.laicaps.block.custom;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;

public class SymbolControllerBlock extends Block
{
    public SymbolControllerBlock(Properties properties)
    {
        super(properties);
    }

    public static final BooleanProperty ACTIVE = BooleanProperty.create("active");



    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> pBuilder)
    {
        super.createBlockStateDefinition(pBuilder);
        pBuilder.add(ACTIVE);

    }







}
