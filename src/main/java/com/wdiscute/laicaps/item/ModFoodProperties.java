package com.wdiscute.laicaps.item;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;

public class ModFoodProperties
{
    public static final FoodProperties KOHLRABI = new FoodProperties.Builder()
            .nutrition(4)
            .saturationModifier(4)
            .effect(new MobEffectInstance(
                    MobEffects.ABSORPTION, 2000, 4), 0.42f)
            .alwaysEdible()
            .build();
}

