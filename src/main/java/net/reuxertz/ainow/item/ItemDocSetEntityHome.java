package net.reuxertz.ainow.item;

import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemDocSetEntityHome extends ItemDocBase
{
    public ItemDocSetEntityHome()
    {
        setUnlocalizedName("itemDocumentSetEntityHome");
    }

    @SideOnly(Side.CLIENT)
    public boolean hasEffect(ItemStack par1ItemStack)
    {
        return true;
    }
}
