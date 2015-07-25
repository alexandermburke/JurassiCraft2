package net.reuxertz.ecoapi.item;


import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.entity.player.EntityInteractEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.reuxertz.ecoapi.util.IDHelper;

import java.util.List;

public abstract class BaseItem extends Item implements IItem
{
    public static boolean itemsEqual(ItemStack i1, ItemStack i2)
    {
        return (i1 == null && i2 == null) || (i1 != null && i2 != null && i1.getItem() == i2.getItem() && i1.getItemDamage() == i2.getItemDamage());
    }

    public void interactEntity(ItemStack stack, EntityInteractEvent e)
    {

    }

    public void interactBlock(ItemStack stack, PlayerInteractEvent e)
    {

    }

    public BaseItem()
    {
        this.setMaxStackSize(1);
        this.setCreativeTab(CreativeTabs.tabMisc);
    }

    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack stack, EntityPlayer playerIn, List tooltip, boolean advanced)
    {
        if (IDHelper.hasIDString(stack))
        {
            String s = IDHelper.getIDString(stack);

            if (s.trim() != "")
            {
                if (s.length() > 10)
                    tooltip.add(s.substring(0, 4));
                else
                    tooltip.add(s);
            }
        }
        else
            tooltip.add("Empty");
    }
}
