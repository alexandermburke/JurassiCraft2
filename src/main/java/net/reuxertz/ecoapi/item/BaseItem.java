package net.reuxertz.ecoapi.item;


import net.minecraft.client.gui.Gui;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.player.EntityInteractEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;

public abstract class BaseItem extends Item implements IItem
{
    public static boolean itemsEqual(ItemStack i1, ItemStack i2)
    {
        return (i1 == null && i2 == null) || (i1 != null && i2 != null && i1.getItem() == i2.getItem() && i1.getItemDamage() == i2.getItemDamage());
    }

    //Interact Functions
    public void interactEntity(ItemStack stack, EntityInteractEvent e)
    {

    }
    public void interactBlock(ItemStack stack, PlayerInteractEvent e)
    {

    }

    //GUI Functions
    public boolean hasGui()
    {
        return false;
    }
    public int containerSize()
    {
        return 0;
    }
    public Container getContainer(int guiMeta, EntityPlayer player, World world, ItemStack stack, int x, int y, int z)
    {
        return null;
    }
    public Gui getGui(int guiMeta, EntityPlayer player, World world, ItemStack stack, int x, int y, int z)
    {
        return null;
    }

    public BaseItem()
    {
        this.setMaxStackSize(1);
        this.setCreativeTab(CreativeTabs.tabMisc);
    }
}
