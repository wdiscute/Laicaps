package com.wdiscute.laicaps.block.custom;

import com.mojang.serialization.MapCodec;
import com.wdiscute.laicaps.block.ModBlockEntity;
import com.wdiscute.laicaps.block.ModBlocks;
import com.wdiscute.laicaps.blockentity.ReceiverBlockEntity;
import com.wdiscute.laicaps.blockentity.TickableBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.DustParticleOptions;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.sound.midi.Receiver;
import java.util.Vector;


public class ReceiverBlock extends Block implements EntityBlock
{

    public static BooleanProperty ACTIVE = BooleanProperty.create("active");

    @Override
    public void animateTick(BlockState pState, Level pLevel, BlockPos pPos, RandomSource pRandom)
    {
        if(pState.getValue(ACTIVE)){

            pLevel.addParticle(
                    new DustParticleOptions(new Vec3(0.678f,0.847f,0.922f).toVector3f(), 3.0F) //FLOAT = SCALE
                    {
                    },
                    (double) pPos.getX() + 0.5f,
                    (double) pPos.getY() + 1.2f,
                    (double) pPos.getZ() + 0.5f,
                    3.0,
                    3.0,
                    3.0
            );
        }
    }

    @Override
    protected void tick(BlockState pState, ServerLevel pLevel, BlockPos pPos, RandomSource pRandom)
    {
        for (int k = 0; k < 4; k++)
        {
            Direction dir = Direction.NORTH;
            if (k == 1) dir = Direction.EAST;
            if (k == 2) dir = Direction.SOUTH;
            if (k == 3) dir = Direction.WEST;

            for (int i = 0; i <= 10; i++)
            {
                // initialize a new variable currentPos which will be given a .direction.bla() for each i increase
                BlockPos currentPos = pPos.relative(dir);
                for (int j = 0; j < i; j++)
                {
                    currentPos = currentPos.relative(dir);
                }
                BlockState blockStateBeingTested = pLevel.getBlockState(currentPos);

                if (blockStateBeingTested.getBlock().defaultBlockState() == ModBlocks.PUZZLE_BLOCK.get().defaultBlockState()
                        && blockStateBeingTested.getValue(HorizontalDirectionalBlock.FACING) == dir.getOpposite())
                {
                    ChangeActive(pLevel, pPos, true);
                    return;
                }

                //if block is not air finished looking in this direction
                if (!blockStateBeingTested.getBlock().defaultBlockState().isAir())
                {
                    break;
                }
            }
        }
        ChangeActive(pLevel, pPos, false);
    }

    private void ChangeActive(Level level, BlockPos pos, boolean bActive)
    {
        level.setBlockAndUpdate(pos, level.getBlockState(pos).setValue(ACTIVE, bActive));
    }

    @Override
    protected InteractionResult useWithoutItem(BlockState pState, Level pLevel, BlockPos pPos, Player pPlayer, BlockHitResult pHitResult)
    {
        if (!pLevel.isClientSide)
        {
            BlockEntity be = pLevel.getBlockEntity(pPos);
            if (be instanceof ReceiverBlockEntity bEntity)
            {
                //run code that calls method in bEntity
                //int counter = bEntity.IncrementCounter();

            }
        }
        return InteractionResult.SUCCESS;
    }


    public ReceiverBlock(Properties properties)
    {
        super(properties);
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> pBuilder)
    {
        super.createBlockStateDefinition(pBuilder);
        pBuilder.add(ACTIVE);
    }

    @Override
    public BlockEntity newBlockEntity(@NotNull BlockPos pPos, @NotNull BlockState pState)
    {
        return ModBlockEntity.RECEIVER_BLOCK.get().create(pPos, pState);
    }

    @Override
    public @Nullable <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level pLevel, BlockState pState, BlockEntityType<T> pBlockEntityType)
    {
        return TickableBlockEntity.getTicketHBelper(pLevel);
    }
}




