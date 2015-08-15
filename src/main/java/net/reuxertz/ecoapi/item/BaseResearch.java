package net.reuxertz.ecoapi.item;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BaseResearch extends BaseItem
{
    @SideOnly(Side.CLIENT)
    public boolean hasEffect(ItemStack par1ItemStack)
    {
        return true;
    }

    public BaseResearch()
    {
        this.setMaxStackSize(64);
        setCreativeTab(CreativeTabs.tabMisc);
    }

}
