package com.wdiscute.datagen;

import com.wdiscute.laicaps.block.ModBlocks;
import com.wdiscute.laicaps.item.ModItems;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlagSet;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.entries.LootPoolEntryContainer;
import net.minecraft.world.level.storage.loot.functions.ApplyBonusCount;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;
import net.minecraftforge.registries.RegistryObject;

import java.util.Set;

public class ModBlockLootTableProvider extends BlockLootSubProvider
{
    protected ModBlockLootTableProvider(HolderLookup.Provider pRegistries) {
        super(Set.of(), FeatureFlags.REGISTRY.allFlags(), pRegistries);
    }

    @Override
    protected void generate() {

        dropSelf(ModBlocks.ALEXENDRITE_BLOCK.get());
        dropSelf(ModBlocks.RAW_ALEXENDRITE_BLOCK.get());
        dropSelf(ModBlocks.MAGIC_BLOCK.get());

        this.add(ModBlocks.ALEXENDRITE_ORE.get(),
                block -> createOreDrop(ModBlocks.ALEXENDRITE_ORE.get(), ModItems.RAW_ALEXANDRITE.get()));

        this.add(ModBlocks.ALEXENDRITE_DEEPSLATE_ORE.get(),
                block -> createMultipleDrops(ModBlocks.ALEXENDRITE_DEEPSLATE_ORE.get(), ModItems.RAW_ALEXANDRITE.get(), 1,3));

    }

    protected LootTable.Builder createMultipleDrops(Block pBlock, Item itemToDrop, float minDrops, float maxDrops) {
        HolderLookup.RegistryLookup<Enchantment> registrylookup = this.registries.lookupOrThrow(Registries.ENCHANTMENT);
        return this.createSilkTouchDispatchTable(
                pBlock, this.applyExplosionDecay(
                        pBlock, LootItem
                                .lootTableItem(itemToDrop)
                                .apply(SetItemCountFunction.setCount(UniformGenerator.between(minDrops, maxDrops)))
                                .apply(ApplyBonusCount.addOreBonusCount(registrylookup.getOrThrow(Enchantments.FORTUNE)))
                )
        );
    }

    @Override
    protected Iterable<Block> getKnownBlocks() {
        return ModBlocks.BLOCKS.getEntries().stream().map(RegistryObject::get)::iterator;
    }
}
