package net.reuxertz.ecoapi.base;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.reuxertz.ecoapi.item.BaseItem;

public class BaseTechnology extends BaseItem
{
    @SideOnly(Side.CLIENT)
    public boolean hasEffect(ItemStack par1ItemStack)
    {
        return true;
    }

    public BaseTechnology()
    {
        this.setMaxStackSize(1);
        setCreativeTab(CreativeTabs.tabMisc);
    }

}
