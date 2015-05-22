package net.ilexiconn.jurassicraft.item;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.event.entity.player.EntityInteractEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.ilexiconn.jurassicraft.api.IItem;
import net.ilexiconn.jurassicraft.utils.NBTHelper;
import net.ilexiconn.jurassicraft.utils.StringHelper;

public abstract class ItemDocBase extends Item implements IItem
{
    protected boolean _displayNameLoc = true;
    protected String _displayName = "Document";

    public ItemDocBase()
    {
        this.setMaxStackSize(1);
        this.setCreativeTab(CreativeTabs.tabMisc);
    }

    @Override
    public String getItemStackDisplayName(ItemStack par1ItemStack)
    {
        String r = this._displayName;

        NBTTagCompound nbt = par1ItemStack.getTagCompound();
        if (this._displayNameLoc)
        {
            if (nbt != null && nbt.hasKey("blockpos_x"))
            {
                r += " - " + StringHelper.BlockPosToString(NBTHelper.ReadBlockPosFromNBT(nbt, "blockpos"));
            }
            if (nbt != null && nbt.hasKey("entityid"))
            {
                r += " - " + nbt.getString("entityid");
            }
        }

        return r;
    }

    @Override
    public void InteractEntity(ItemStack stack, EntityInteractEvent e)
    {

        stack.getTagCompound().setString("entityid", "a");
    }

    @Override
    public void InteractBlock(ItemStack stack, PlayerInteractEvent e)
    {

        NBTHelper.WriteBlockPosToNBT(stack.getTagCompound(), "blockpos", e.pos);
    }

}
