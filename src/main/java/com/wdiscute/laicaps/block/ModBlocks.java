package com.wdiscute.laicaps.block;

import com.wdiscute.laicaps.Laicaps;
import com.wdiscute.laicaps.item.ModItems;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.DropExperienceBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

public class ModBlocks
{
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, Laicaps.MOD_ID);

    public static final RegistryObject<Block> ALEXENDRITE_BLOCK =
            registryObject("alexandrite_block", () ->
                    new Block(BlockBehaviour.Properties.of()
                            .requiresCorrectToolForDrops()
                            .strength(0.2f, 4f)
                            .instabreak()
                            .noCollission()
                            .replaceable()
                            .sound(SoundType.TUFF)

                    )
            );

    public static final RegistryObject<Block> RAW_ALEXENDRITE_BLOCK =
            registryObject("raw_alexandrite_block", () ->
                    new Block(BlockBehaviour.Properties.of()
                            .requiresCorrectToolForDrops()
                            .strength(0.2f, 4f)
                            .sound(SoundType.BONE_BLOCK)
                    )
            );

    public static final RegistryObject<Block> RALEXENDRITE_ORE =
            registryObject("alexandrite_ore", () ->
                    new DropExperienceBlock(UniformInt.of(2, 4),BlockBehaviour.Properties.of()
                            .requiresCorrectToolForDrops()
                            .strength(4f, 6f)
                    )
            );

    public static final RegistryObject<Block> RALEXENDRITE_DEEPSLATE_ORE =
            registryObject("alexandrite_deepslate_ore", () ->
                    new DropExperienceBlock(UniformInt.of(2, 4),BlockBehaviour.Properties.of()
                            .requiresCorrectToolForDrops()
                            .strength(5f, 7f)
                    )
            );



    private static <T extends Block> RegistryObject<T> registryObject(String name, Supplier<T> block){
        RegistryObject<T> toReturn = BLOCKS.register(name, block);
        ModItems.ITEMS.register(name, () -> new BlockItem(toReturn.get(), new Item.Properties()));
        return toReturn;
    }

    public static void register(IEventBus eventBus)
    {
        BLOCKS.register(eventBus);
    }
}
