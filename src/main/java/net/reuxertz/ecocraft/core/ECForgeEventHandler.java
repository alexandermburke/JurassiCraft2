package net.reuxertz.ecocraft.core;


import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.entity.player.EntityInteractEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.reuxertz.ecocraft.entity.EntityAICreature;
import net.reuxertz.ecocraft.api.IItem;

public class ECForgeEventHandler
{
    public static boolean AllowMonstersInUpperOverworld = false;

    //Entity Events
    @SubscribeEvent
    public void onEntityJoinWorld(EntityJoinWorldEvent e)
    {

        boolean isAICreature = EntityAICreature.class.isInstance(e.entity);
        boolean isAnimal = EntityAnimal.class.isInstance(e.entity);
        boolean isMonster = EntityMob.class.isInstance(e.entity);
        boolean isVillager = EntityVillager.class.isInstance(e.entity);

        if (isAnimal)
            EntityAICreature.ConstructAIEntity((EntityCreature)e.entity);

        if (!AllowMonstersInUpperOverworld && isMonster)
            e.setCanceled(true);

        return;
    }

    //Player InteractionsEvents
    @SubscribeEvent
    public void onEntityInteractEvent(EntityInteractEvent e)
    {

        if (e.entityPlayer.worldObj.isRemote)
            return;

        ItemStack heldItem = e.entityPlayer.getHeldItem();
        if (heldItem != null && IItem.class.isInstance(heldItem.getItem()))
            IItem.class.cast(heldItem.getItem()).InteractEntity(heldItem, e);

        return;
    }

    @SubscribeEvent
    public void onPlayerInteractEvent(PlayerInteractEvent e)
    {

        if (e.entityPlayer.worldObj.isRemote)
            return;

        if (e.entityPlayer.worldObj.isRemote || e.action == PlayerInteractEvent.Action.RIGHT_CLICK_AIR)
            return;

        ItemStack heldItem = e.entityPlayer.getHeldItem();
        if (heldItem != null && IItem.class.isInstance(heldItem.getItem()))
            IItem.class.cast(heldItem.getItem()).InteractBlock(heldItem, e);


        return;

    }
}
