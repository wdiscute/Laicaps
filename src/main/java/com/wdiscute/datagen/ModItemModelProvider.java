package com.wdiscute.datagen;

import com.wdiscute.laicaps.Laicaps;
import com.wdiscute.laicaps.item.ModItems;
import net.minecraft.data.PackOutput;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;

public class ModItemModelProvider extends ItemModelProvider {
    public ModItemModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, Laicaps.MOD_ID, existingFileHelper);
    }

    @Override
    protected void registerModels()
    {
        basicItem(ModItems.ALEXANDRITE.get());
        basicItem(ModItems.RAW_ALEXANDRITE.get());
        basicItem(ModItems.CHISEL.get());
        basicItem(ModItems.KOHLRABI.get());
        basicItem(ModItems.AURORA_ASHES.get());

    }
}
