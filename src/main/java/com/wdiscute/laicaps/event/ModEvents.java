package com.wdiscute.laicaps.event;


import com.wdiscute.laicaps.Laicaps;
import net.minecraft.world.entity.animal.Sheep;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Items;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.event.entity.living.LivingDamageEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = Laicaps.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class ModEvents
{

    @SubscribeEvent
    public static void onLivingDamage(LivingDamageEvent event)
    {
        if (event.getEntity() instanceof Sheep sheep && event.getSource().getDirectEntity() instanceof Player player)
        {
            if (player.isCrouching() && player.getMainHandItem().getItem() == Items.SLIME_BALL)
            {
                sheep.moveTo(sheep.position().x, sheep.position().y + 4f, sheep.position().z);
            }
        }
    }

}
