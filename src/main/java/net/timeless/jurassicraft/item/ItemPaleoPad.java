package net.timeless.jurassicraft.item;

import net.minecraft.item.Item;
import net.timeless.jurassicraft.creativetab.JCCreativeTabs;

public class ItemPaleoPad extends Item
{
    public ItemPaleoPad()
    {
        super();

        this.setUnlocalizedName("paleo_pad");
        this.setMaxStackSize(1);

        this.setCreativeTab(JCCreativeTabs.items);
    }
}
