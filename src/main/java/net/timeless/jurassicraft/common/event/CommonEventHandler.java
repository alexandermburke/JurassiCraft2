package net.timeless.jurassicraft.common.event;

import com.google.common.collect.Lists;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.event.entity.EntityEvent.EntityConstructing;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.timeless.jurassicraft.common.entity.base.EntityDinosaur;
import net.timeless.jurassicraft.common.entity.data.JCPlayerData;

import java.util.ArrayList;
import java.util.UUID;

public class CommonEventHandler
{
    @SubscribeEvent
    public void entityConstructing(EntityConstructing event)
    {
        if (event.entity instanceof EntityPlayer)
        {
            event.entity.registerExtendedProperties(JCPlayerData.identifier, new JCPlayerData());
        }
    }

    @SubscribeEvent
    public void onPlayerHurt(LivingHurtEvent event)
    {
        if (event.entityLiving instanceof EntityDinosaur)
        {
            EntityDinosaur dino = (EntityDinosaur) event.entityLiving;

            if (!dino.isCarcass() && dino.getHealth() - event.ammount <= 0)
            {
                event.setCanceled(true);
                event.ammount = 0;
                dino.setCarcass(true);
            }
        }
    }
}
