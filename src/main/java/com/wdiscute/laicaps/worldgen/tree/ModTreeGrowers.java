package com.wdiscute.laicaps.worldgen.tree;

import com.wdiscute.laicaps.Laicaps;
import com.wdiscute.laicaps.worldgen.ModConfiguredFeatures;
import net.minecraft.world.level.block.grower.TreeGrower;

import java.util.Optional;

public class ModTreeGrowers
{
    public static final TreeGrower WALNUL = new TreeGrower(Laicaps.MOD_ID + ":walnul",
            Optional.empty(), Optional.of(ModConfiguredFeatures.WALNUT_KEY), Optional.empty());
}
