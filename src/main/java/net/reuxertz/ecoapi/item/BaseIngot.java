package net.reuxertz.ecoapi.item;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class BaseIngot extends Item
{
    public BaseIngot()
    {
        this.setMaxStackSize(64);
        this.setCreativeTab(CreativeTabs.tabMisc);
    }
}
