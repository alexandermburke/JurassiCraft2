package net.timeless.jurassicraft.common.event;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraftforge.event.entity.EntityEvent.EntityConstructing;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent;
import net.timeless.jurassicraft.common.achievements.JCAchievements;
import net.timeless.jurassicraft.common.block.JCBlockRegistry;
import net.timeless.jurassicraft.common.entity.data.JCPlayerData;
import net.timeless.jurassicraft.common.item.JCItemRegistry;

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
    public void onSpawn(EntityJoinWorldEvent event)
    {
        if (event.entity instanceof EntityPlayer)
        {
            EntityPlayer player = (EntityPlayer) event.entity;
            player.addStat(JCAchievements.jurassicraft, 1);
        }
    }

    @SubscribeEvent
    public void onItemPickup(PlayerEvent.ItemPickupEvent event)
    {
        if (event.pickedUp.getEntityItem().getItem() == JCItemRegistry.amber)
        {
            event.player.addStat(JCAchievements.amber, 1);
        }
    }

    @SubscribeEvent
    public void onCraft(PlayerEvent.ItemCraftedEvent event)
    {
        Item item = event.crafting.getItem();

        if (item == JCItemRegistry.plaster_and_bandage)
        {
            event.player.addStat(JCAchievements.paleontology, 1);
        }
        else if(item == Item.getItemFromBlock(JCBlockRegistry.cleaning_station))
        {
            event.player.addStat(JCAchievements.cleaningStation, 1);
        }
    }

//    @SubscribeEvent
//    public void livingHurt(LivingHurtEvent event)
//    {
//        if (event.entityLiving instanceof EntityDinosaur)
//        {
//            EntityDinosaur dino = (EntityDinosaur) event.entityLiving;
//
//            if (!dino.isCarcass() && dino.getHealth() - event.ammount <= 0)
//            {
//                event.setCanceled(true);
//                event.ammount = 0;
//                dino.setCarcass(true);
//            }
//        }
//    }
}
