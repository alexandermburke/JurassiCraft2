package org.jurassicraft.common.event;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.world.GameRules;
import net.minecraftforge.event.entity.EntityEvent.EntityConstructing;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.entity.living.LivingSpawnEvent.CheckSpawn;
import net.minecraftforge.event.world.WorldEvent;
import net.minecraftforge.fml.common.eventhandler.Event.Result;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent;

import org.jurassicraft.JurassiCraft;
import org.jurassicraft.common.achievements.JCAchievements;
import org.jurassicraft.common.block.JCBlockRegistry;
import org.jurassicraft.common.entity.base.EntityDinosaur;
import org.jurassicraft.common.entity.data.JCPlayerData;
import org.jurassicraft.common.item.JCItemRegistry;

public class CommonEventHandler
{
    @SubscribeEvent(priority=EventPriority.NORMAL, receiveCanceled=true)
    public void entityConstructing(EntityConstructing event)
    {
        if (event.entity instanceof EntityPlayer)
        {
            event.entity.registerExtendedProperties(JCPlayerData.identifier, new JCPlayerData());
        }
    }

    @SubscribeEvent(priority=EventPriority.NORMAL, receiveCanceled=true)
    public void onEntityJoinWorld(EntityJoinWorldEvent event)
    {
        if (event.entity instanceof EntityPlayer)
        {
            EntityPlayer player = (EntityPlayer) event.entity;
            player.addStat(JCAchievements.jurassicraft, 1);
        }
    }
    
    @SubscribeEvent(priority=EventPriority.HIGHEST, receiveCanceled=true)
    public void onCheckSpawn(CheckSpawn event)
    {
        if (event.entityLiving instanceof EntityDinosaur)
        {
            if (JurassiCraft.spawnDinosNaturally)
            {
                event.setResult(Result.ALLOW);
            }
            else
            {
                event.setResult(Result.DENY);
            }
        }
        else
        {
            if (JurassiCraft.spawnNonDinoMobsNaturally)
            {
                event.setResult(Result.ALLOW);
            }
            else
            {
                event.setResult(Result.DENY);
            }      
        }
        
    }

    @SubscribeEvent(priority=EventPriority.NORMAL, receiveCanceled=true)
    public void onItemPickup(PlayerEvent.ItemPickupEvent event)
    {
        if (event.pickedUp.getEntityItem().getItem() == JCItemRegistry.amber)
        {
            event.player.addStat(JCAchievements.amber, 1);
        }
    }

    @SubscribeEvent(priority=EventPriority.NORMAL, receiveCanceled=true)
    public void worldLoad(WorldEvent.Load event)
    {
        event.world.getGameRules().addGameRule("dinoMetabolism", "true", GameRules.ValueType.BOOLEAN_VALUE);
    }

    @SubscribeEvent(priority=EventPriority.NORMAL, receiveCanceled=true)
    public void onCraft(PlayerEvent.ItemCraftedEvent event)
    {
        Item item = event.crafting.getItem();

        if (item == JCItemRegistry.plaster_and_bandage)
        {
            event.player.addStat(JCAchievements.paleontology, 1);
        }
        else if (item == Item.getItemFromBlock(JCBlockRegistry.cleaning_station))
        {
            event.player.addStat(JCAchievements.cleaningStation, 1);
        }
        else if (item == Item.getItemFromBlock(JCBlockRegistry.fossil_grinder))
        {
            event.player.addStat(JCAchievements.fossilGrinder, 1);
        }
        else if (item == Item.getItemFromBlock(JCBlockRegistry.reinforced_stone))
        {
            event.player.addStat(JCAchievements.reinforcedStone, 1);
        }
        else if (item == Item.getItemFromBlock(JCBlockRegistry.reinforced_bricks))
        {
            event.player.addStat(JCAchievements.reinforcedStone, 1);
        }
    }

    @SubscribeEvent(priority=EventPriority.NORMAL, receiveCanceled=true)
    public void livingHurt(LivingHurtEvent event)
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
