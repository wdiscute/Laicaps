package com.wdiscute.laicaps.worldgen;

import com.wdiscute.laicaps.Laicaps;
import com.wdiscute.laicaps.worldgen.biome.ModBiomes;
import net.minecraft.core.HolderSet;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BiomeTags;
import net.minecraft.world.level.biome.Biomes;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraftforge.common.world.BiomeModifier;
import net.minecraftforge.common.world.ForgeBiomeModifiers;
import net.minecraftforge.registries.ForgeRegistries;

public class ModBiomeModifiers
{

    public static final ResourceKey<BiomeModifier> ADD_ALEXANDRITE_ORE = registerKey("add_alexandrite_ore");
    public static final ResourceKey<BiomeModifier> ADD_WALNUT_TREE = registerKey("add_walnut_tree");
    public static final ResourceKey<BiomeModifier> ADD_RANDOM_BLOCKS = registerKey("add_random_blocks");

    public static void bootstrap(BootstrapContext<BiomeModifier> context)
    {
        var placedFeature = context.lookup(Registries.PLACED_FEATURE);
        var biomes = context.lookup(Registries.BIOME);

        context.register(ADD_ALEXANDRITE_ORE, new ForgeBiomeModifiers.AddFeaturesBiomeModifier(
                biomes.getOrThrow(BiomeTags.IS_OVERWORLD),
                //HolderSet.direct(biomes.getOrThrow(Biomes.PLAINS), biomes.getOrThrow(Biomes.BEACH)),
                HolderSet.direct(placedFeature.getOrThrow(ModPlacedFeatures.ALEXANDRTITE_ORE_PLACED_KEY)),
                GenerationStep.Decoration.UNDERGROUND_ORES
        ));


        context.register(ADD_WALNUT_TREE, new ForgeBiomeModifiers.AddFeaturesBiomeModifier(
                HolderSet.direct(biomes.getOrThrow(Biomes.END_HIGHLANDS), biomes.getOrThrow(Biomes.END_MIDLANDS), biomes.getOrThrow(Biomes.END_BARRENS)),
                HolderSet.direct(placedFeature.getOrThrow(ModPlacedFeatures.WALNUT_PLACED_KEY)),
                GenerationStep.Decoration.VEGETAL_DECORATION
        ));

        /*
        context.register(ADD_RANDOM_BLOCKS, new ForgeBiomeModifiers.AddFeaturesBiomeModifier(
                HolderSet.direct(biomes.getOrThrow(Biomes.END_HIGHLANDS), biomes.getOrThrow(Biomes.END_BARRENS)),
                HolderSet.direct(placedFeature.getOrThrow(ModPlacedFeatures.RANDOM_BLOCKS_PLACED_KEY)),
                GenerationStep.Decoration.VEGETAL_DECORATION
        ));
        */

    }


    private static ResourceKey<BiomeModifier> registerKey(String name)
    {
        return ResourceKey.create(ForgeRegistries.Keys.BIOME_MODIFIERS, ResourceLocation.fromNamespaceAndPath(Laicaps.MOD_ID, name));
    }

}
