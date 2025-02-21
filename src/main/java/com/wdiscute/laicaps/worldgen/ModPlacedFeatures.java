package com.wdiscute.laicaps.worldgen;

import com.wdiscute.laicaps.Laicaps;
import com.wdiscute.laicaps.block.ModBlocks;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.data.worldgen.placement.VegetationPlacements;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.placement.HeightRangePlacement;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraft.world.level.levelgen.placement.PlacementModifier;

import java.util.List;

public class ModPlacedFeatures
{
    public static final ResourceKey<PlacedFeature> ALEXANDRTITE_ORE_PLACED_KEY = registerKey("alexandrite_ore_placed");

    public static final ResourceKey<PlacedFeature> WALNUT_PLACED_KEY = registerKey("walnut_placed");


    public static void bootstrap(BootstrapContext<PlacedFeature> context)
    {
        var configuredFeatures = context.lookup(Registries.CONFIGURED_FEATURE);

        //places about 12 veins per chunks from -64 to 80,
        // since deepslate ore can only replace deepslate there's no need to make 2 entries for deep/non deep
        //uniform means it will be seperated uniform, triangle does something else lol
        register(context, ALEXANDRTITE_ORE_PLACED_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.OVERWORLD_ALEXANDRITE_ORE_KEY),
                ModOrePlacements.commonOrePlacement(12,
                        HeightRangePlacement.uniform(VerticalAnchor.absolute(-64), VerticalAnchor.absolute(80)))
                );


        //PlacementUtils.countExtra(3, 0.1f, 2) means place 3 with 10% of placing another 2
        //VegetationPlacements.java for more stuff on trees
        register(context, WALNUT_PLACED_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.WALNUT_KEY),
                VegetationPlacements.treePlacement(PlacementUtils.countExtra(0, 0.01f, 1),
                        ModBlocks.WALNUL_SAPLING.get()));

    }


    public static ResourceKey<PlacedFeature> registerKey(String name)
    {
        return ResourceKey.create(Registries.PLACED_FEATURE, ResourceLocation.fromNamespaceAndPath(Laicaps.MOD_ID, name));
    }

    private static void register(BootstrapContext<PlacedFeature> context, ResourceKey<PlacedFeature> key, Holder<ConfiguredFeature<?, ?>> configuration, List<PlacementModifier> modifiers)
    {
        context.register(key, new PlacedFeature(configuration, List.copyOf(modifiers)));
    }
}
