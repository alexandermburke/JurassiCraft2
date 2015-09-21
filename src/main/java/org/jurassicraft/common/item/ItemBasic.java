package org.jurassicraft.common.item;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class ItemBasic extends Item
{
    public ItemBasic(String name, CreativeTabs tab)
    {
        super();
        this.setUnlocalizedName(name.replaceAll(" ", "_").toLowerCase());
        this.setCreativeTab(tab);
    }
}
