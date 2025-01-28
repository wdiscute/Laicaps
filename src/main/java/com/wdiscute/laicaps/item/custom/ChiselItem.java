package com.wdiscute.laicaps.item.custom;

import com.wdiscute.laicaps.block.ModBlocks;
import com.wdiscute.laicaps.item.ModItems;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.item.*;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;

import java.util.List;
import java.util.Map;

public class ChiselItem extends Item {

    private static final Map<Block, Block> CHISEL_MAP =
            Map.of(
                    Blocks.STONE, Blocks.STONE_BRICKS,
                    Blocks.END_STONE, Blocks.END_STONE_BRICKS,
                    Blocks.DEEPSLATE, Blocks.DEEPSLATE_BRICKS,
                    Blocks.IRON_BLOCK, Blocks.DIAMOND_BLOCK,
                    Blocks.DIRT, ModBlocks.ALEXENDRITE_BLOCK.get()

            );

    public ChiselItem(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public InteractionResult useOn(UseOnContext pContext) {
        Level level = pContext.getLevel();
        Block clickedBlock = level.getBlockState(pContext.getClickedPos()).getBlock();

        //if(CHISEL_MAP.containsKey(clickedBlock) && pContext.getPlayer().isCrouching())

        if (!level.isClientSide()) {

            //level.setBlockAndUpdate(pContext.getClickedPos(), CHISEL_MAP.get(clickedBlock).defaultBlockState());
            //pContext.getItemInHand().hurtAndBreak(1, ((ServerLevel) level), ((ServerPlayer) pContext.getPlayer()),
            //        item -> pContext.getPlayer().onEquippedItemBroken(item, EquipmentSlot.MAINHAND));
            //level.playSound(null, pContext.getClickedPos(), SoundEvents.GRINDSTONE_USE, SoundSource.BLOCKS);

            ItemStack offhanditemstack = pContext.getPlayer().getOffhandItem();
            if (offhanditemstack.isEmpty()) return InteractionResult.FAIL;

            if (offhanditemstack.getItem() instanceof BlockItem) {
                Block block = ((BlockItem) offhanditemstack.getItem()).getBlock();
                level.setBlockAndUpdate(pContext.getClickedPos(), block.defaultBlockState());
                return InteractionResult.SUCCESS;
            }

            if (offhanditemstack.getItem() == Items.WATER_BUCKET) {
                level.setBlockAndUpdate(pContext.getClickedPos(), Blocks.WATER.defaultBlockState());
                return InteractionResult.SUCCESS;
            }

            if (offhanditemstack.getItem() == Items.LAVA_BUCKET) {
                level.setBlockAndUpdate(pContext.getClickedPos(), Blocks.LAVA.defaultBlockState());
                return InteractionResult.SUCCESS;
            }

        }


        return InteractionResult.FAIL;
    }


    @Override
    public void appendHoverText(ItemStack pStack, TooltipContext pContext, List<Component> pTooltipComponents, TooltipFlag pTooltipFlag) {
        if(Screen.hasShiftDown()) {
            pTooltipComponents.add(Component.translatable("toolip.laicaps.chisel.shift_down"));
        } else {
            pTooltipComponents.add(Component.translatable("toolip.laicaps.chisel"));
        }
        super.appendHoverText(pStack, pContext, pTooltipComponents, pTooltipFlag);
    }
}
