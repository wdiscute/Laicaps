package com.wdiscute.laicaps.worldgen;

import com.wdiscute.laicaps.Laicaps;
import com.wdiscute.laicaps.block.ModBlocks;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.commands.PublishCommand;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.OreConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.featuresize.FeatureSize;
import net.minecraft.world.level.levelgen.feature.featuresize.FeatureSizeType;
import net.minecraft.world.level.levelgen.feature.featuresize.TwoLayersFeatureSize;
import net.minecraft.world.level.levelgen.feature.foliageplacers.BlobFoliagePlacer;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.feature.trunkplacers.ForkingTrunkPlacer;
import net.minecraft.world.level.levelgen.structure.templatesystem.RuleTest;
import net.minecraft.world.level.levelgen.structure.templatesystem.TagMatchTest;

import java.util.List;
import java.util.OptionalInt;

public class ModConfiguredFeatures
{

    public static final ResourceKey<ConfiguredFeature<?, ?>> OVERWORLD_ALEXANDRITE_ORE_KEY = registerKey("alexandrite_ore");
    public static final ResourceKey<ConfiguredFeature<?, ?>> WALNUT_KEY = registerKey("walnut");


    public static void bootstrap(BootstrapContext<ConfiguredFeature<?, ?>> context)
    {

        if(true) //OVERWORLD_ALEXANDRITE_ORE_KEY + list etc
        {
            RuleTest stoneReplacables = new TagMatchTest(BlockTags.STONE_ORE_REPLACEABLES);
            RuleTest deepslateReplacables = new TagMatchTest(BlockTags.DEEPSLATE_ORE_REPLACEABLES);

            List<OreConfiguration.TargetBlockState> overworldAlexandriteOresList = List.of(
                    OreConfiguration.target(stoneReplacables, ModBlocks.ALEXENDRITE_ORE.get().defaultBlockState()),
                    OreConfiguration.target(deepslateReplacables, ModBlocks.ALEXENDRITE_DEEPSLATE_ORE.get().defaultBlockState())
            );
            register(context, OVERWORLD_ALEXANDRITE_ORE_KEY, Feature.ORE, new OreConfiguration(overworldAlexandriteOresList, 9));
        }



        //check TreeFeatures.java for all vanilla trees
        register(context, WALNUT_KEY, Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
                BlockStateProvider.simple(ModBlocks.ALEXENDRITE_ORE.get()),
                new ForkingTrunkPlacer(4, 4, 3),

                BlockStateProvider.simple(Blocks.DIAMOND_BLOCK),
                new BlobFoliagePlacer(ConstantInt.of(3), ConstantInt.of(3), 3),

                new TwoLayersFeatureSize(1,1,1))

                .dirt(BlockStateProvider.simple(Blocks.END_STONE))
                .build());





    }




    public static ResourceKey<ConfiguredFeature<?, ?>> registerKey(String name)
    {
        return ResourceKey.create(Registries.CONFIGURED_FEATURE, ResourceLocation.fromNamespaceAndPath(Laicaps.MOD_ID, name));
    }

    public static <FC extends FeatureConfiguration, F extends Feature<FC>> void register(BootstrapContext<ConfiguredFeature<?, ?>> context, ResourceKey<ConfiguredFeature<?, ?>> key, F feature, FC configuration)
    {
        context.register(key, new ConfiguredFeature<>(feature, configuration));
    }


}

