package com.wdiscute.datagen;

import com.wdiscute.laicaps.Laicaps;
import com.wdiscute.laicaps.block.ModBlocks;
import net.minecraft.data.PackOutput;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.RegistryObject;

public class ModBlockStateProvider extends BlockStateProvider
{

    public ModBlockStateProvider(PackOutput output, ExistingFileHelper exFileHelper) {
        super(output, Laicaps.MOD_ID, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        blockWithItem(ModBlocks.ALEXENDRITE_BLOCK);
        blockWithItem(ModBlocks.RAW_ALEXENDRITE_BLOCK);

        blockWithItem(ModBlocks.ALEXENDRITE_DEEPSLATE_ORE);
        blockWithItem(ModBlocks.ALEXENDRITE_ORE);

        blockWithItem(ModBlocks.MAGIC_BLOCK);

    }

    private void blockWithItem(RegistryObject<Block> blockRegistryObject)
    {
        simpleBlockWithItem(blockRegistryObject.get(), cubeAll(blockRegistryObject.get()));
    }

}
