package net.reuxertz.ecoapi.item;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class BaseNugget extends Item
{
    public BaseNugget()
    {
        this.setMaxStackSize(64);
        this.setCreativeTab(CreativeTabs.tabMisc);
    }
}
