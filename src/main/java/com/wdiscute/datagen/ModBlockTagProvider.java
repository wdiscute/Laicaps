package com.wdiscute.datagen;

import com.wdiscute.laicaps.Laicaps;
import com.wdiscute.laicaps.block.ModBlocks;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.BlockTags;
import net.minecraftforge.common.data.BlockTagsProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class ModBlockTagProvider extends BlockTagsProvider {
    public ModBlockTagProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, @Nullable ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, Laicaps.MOD_ID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider pProvider) {
        tag(BlockTags.MINEABLE_WITH_PICKAXE)
                .add(ModBlocks.ALEXENDRITE_BLOCK.get())
                .add(ModBlocks.RAW_ALEXENDRITE_BLOCK.get())

                .add(ModBlocks.ALEXENDRITE_DEEPSLATE_ORE.get())
                .add(ModBlocks.ALEXENDRITE_ORE.get())

                .add(ModBlocks.MAGIC_BLOCK.get());

        tag(BlockTags.NEEDS_IRON_TOOL)
                .add(ModBlocks.ALEXENDRITE_DEEPSLATE_ORE.get())
                .add(ModBlocks.ALEXENDRITE_ORE.get())
                .add(ModBlocks.ALEXENDRITE_BLOCK.get())
                .add(ModBlocks.RAW_ALEXENDRITE_BLOCK.get());



                ;


    }
}
