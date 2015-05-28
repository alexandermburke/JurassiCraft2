package net.reuxertz.ecocraft.core.handlers;


import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.ServerChatEvent;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.entity.player.EntityInteractEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.reuxertz.ecocraft.core.EcoRegistry;
import net.reuxertz.ecocraft.core.EcoResources;
import net.reuxertz.ecocraft.core.handlers.ChatHandler;
import net.reuxertz.ecocraft.entity.EntityAICreature;
import net.reuxertz.ecocraft.api.IItem;
import net.reuxertz.ecocraft.tileentity.TileEntityDroppedItem;

public class ForgeHandler
{
    public static boolean AllowMonstersInUpperOverworld = false;

    public static double MonstersUpperOverworldThreshold = 58;

    //Entity Events
    @SubscribeEvent
    public void onEntityJoinWorld(EntityJoinWorldEvent e)
    {
        //Server Only
        if (e.world.isRemote)
            return;

        boolean isAICreature = EntityAICreature.class.isInstance(e.entity);
        boolean isAnimal = EntityAnimal.class.isInstance(e.entity);
        boolean isMonster = EntityMob.class.isInstance(e.entity);
        boolean isVillager = EntityVillager.class.isInstance(e.entity);

        if (isAnimal)
            EntityAICreature.ConstructAIEntity((EntityCreature)e.entity);

        if (!AllowMonstersInUpperOverworld && isMonster && e.entity.posY > MonstersUpperOverworldThreshold)
            e.setCanceled(true);

        return;
    }

    //Player InteractionsEvents
    @SubscribeEvent
    public void onEntityInteractEvent(EntityInteractEvent e)
    {
        //Server Only
        if (e.entityPlayer.worldObj.isRemote)
            return;

        //General Item Interact Method
        ItemStack heldItem = e.entityPlayer.getHeldItem();
        if (heldItem != null && IItem.class.isInstance(heldItem.getItem()))
            IItem.class.cast(heldItem.getItem()).InteractEntity(heldItem, e);

        return;
    }
    @SubscribeEvent
    public void onPlayerInteractEvent(PlayerInteractEvent e)
    {
        //Server Only
        if (e.entityPlayer.worldObj.isRemote)
            return;

        //Click Nothing
        if (e.action == PlayerInteractEvent.Action.RIGHT_CLICK_AIR)
            return;

        //General Item Interact Method
        ItemStack heldItem = e.entityPlayer.getHeldItem();
        if (heldItem != null && IItem.class.isInstance(heldItem.getItem()))
            IItem.class.cast(heldItem.getItem()).InteractBlock(heldItem, e);

        /*if (e.action == PlayerInteractEvent.Action.RIGHT_CLICK_BLOCK)
        {
            e.world.setBlockState(e.pos, EcoRegistry.blockDroppedItem.getDefaultState());
            ((TileEntityDroppedItem)e.world.getTileEntity(e.pos)).SetStack(new ItemStack(heldItem.getItem(), 1));
            e.entityPlayer.inventory.mainInventory[e.entityPlayer.inventory.currentItem].stackSize--;
        }*/

        return;

    }

    //Block Drop Events
    @SubscribeEvent
    public void onHarvestDropsEvent(BlockEvent.HarvestDropsEvent dropEvent) {

        boolean remote = dropEvent.world.isRemote;
        if (remote)
            return;

        if (!dropEvent.isSilkTouching)
            EcoResources.HandleRandomEarthDrops(dropEvent);
    }


}
