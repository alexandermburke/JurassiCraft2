package net.ilexiconn.jurassicraft.event;


import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.entity.player.EntityInteractEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.ilexiconn.jurassicraft.entity.EntityAICreature;
import net.ilexiconn.jurassicraft.api.IItem;

public class AINowForgeEventHandler
{

    //Entity Events
    @SubscribeEvent
    public void onEntityJoinWorld(EntityJoinWorldEvent e)
    {

        boolean isAICreature = EntityAICreature.class.isInstance(e.entity);
        boolean isAnimal = EntityAnimal.class.isInstance(e.entity);
        boolean isVillager = EntityVillager.class.isInstance(e.entity);

        //if (isVillager || isAnimal)
        //    EntityAICreature.ConstructAI((EntityCreature)e.entity);

        return;
    }

    //Player InteractionsEvents
    @SubscribeEvent
    public void onEntityInteractEvent(EntityInteractEvent e)
    {

        if (e.entityPlayer.getHeldItem() != null && IItem.class.isInstance(e.entityPlayer.getHeldItem()))
            IItem.class.cast(e.entityPlayer.getHeldItem()).InteractEntity(e.entityPlayer.inventory.getCurrentItem(), e);

        return;
    }

    @SubscribeEvent
    public void onPlayerInteractEvent(PlayerInteractEvent e)
    {

        if (e.entityPlayer.getHeldItem() != null && IItem.class.isInstance(e.entityPlayer.getHeldItem()))
            IItem.class.cast(e.entityPlayer.getHeldItem()).InteractBlock(e.entityPlayer.inventory.getCurrentItem(), e);


        return;

    }
}
