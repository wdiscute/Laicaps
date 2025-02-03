package com.wdiscute.laicaps.item.custom;

import com.wdiscute.component.ModDataComponentTypes;
import com.wdiscute.laicaps.block.ModBlocks;
import com.wdiscute.laicaps.item.ModItems;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.core.component.DataComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.*;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.fml.ISystemReportExtender;

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
            System.out.print("wad");
            pContext.getItemInHand().set(ModDataComponentTypes.COORDINATES.get(), pContext.getClickedPos());
            pContext.getItemInHand().hurtAndBreak(2, pContext.getPlayer(), EquipmentSlot.MAINHAND);

            ItemStack offhanditemstack = pContext.getPlayer().getOffhandItem();
            if (offhanditemstack.isEmpty()) return InteractionResult.FAIL;

            if (offhanditemstack.getItem() instanceof BlockItem) {
                Block block = ((BlockItem) offhanditemstack.getItem()).getBlock();
                level.setBlockAndUpdate(pContext.getClickedPos(), block.defaultBlockState());

                level.playSound(null, pContext.getClickedPos(), SoundEvents.GRINDSTONE_USE, SoundSource.BLOCKS);

                return InteractionResult.SUCCESS;
            }

            if (offhanditemstack.getItem() == Items.WATER_BUCKET) {
                level.setBlockAndUpdate(pContext.getClickedPos(), Blocks.WATER.defaultBlockState());
                level.playSound(null, pContext.getClickedPos(), SoundEvents.BUCKET_EMPTY_AXOLOTL, SoundSource.BLOCKS);

                return InteractionResult.SUCCESS;
            }

            if (offhanditemstack.getItem() == Items.LAVA_BUCKET) {
                level.setBlockAndUpdate(pContext.getClickedPos(), Blocks.LAVA.defaultBlockState());
                level.playSound(null, pContext.getClickedPos(), SoundEvents.BUCKET_EMPTY_LAVA, SoundSource.BLOCKS);

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

        if(pStack.get(ModDataComponentTypes.COORDINATES.get()) != null)
        {
            pTooltipComponents.add(Component.literal("Last Block Clicked at " + pStack.get(ModDataComponentTypes.COORDINATES.get())));
        }
        super.appendHoverText(pStack, pContext, pTooltipComponents, pTooltipFlag);
    }
}
