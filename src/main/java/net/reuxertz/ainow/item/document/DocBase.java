package net.reuxertz.ainow.item.document;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.event.entity.player.EntityInteractEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.reuxertz.ainow.api.IItem;
import net.reuxertz.ainow.utils.NBTHelper;
import net.reuxertz.ainow.utils.StringHelper;

public abstract class DocBase extends Item implements IItem
{
    protected boolean _displayNameLoc = true;
    protected String _displayName = "Document";

    public DocBase()
    {
        this.setMaxStackSize(1);
        this.setCreativeTab(CreativeTabs.tabMisc);
    }

    @Override
    public String getItemStackDisplayName(ItemStack par1ItemStack)
    {
        String r = this._displayName;

        NBTTagCompound nbt = par1ItemStack.getTagCompound();
        if (this._displayNameLoc) {
            if (nbt != null && nbt.hasKey("blockpos_x")) {
                r += " - " + StringHelper.BlockPosToString(NBTHelper.ReadBlockPosFromNBT(nbt, "blockpos"));
            }
            if (nbt != null && nbt.hasKey("entityid")) {
                r += " - " + nbt.getString("entityid");
            }
        }

        return r;
    }

    @Override
    public void InteractEntity(ItemStack stack, EntityInteractEvent e) {

        stack.getTagCompound().setString("entityid", "a");
    }

    @Override
    public void InteractBlock(ItemStack stack, PlayerInteractEvent e) {

       NBTHelper.WriteBlockPosToNBT(stack.getTagCompound(), "blockpos", e.pos);
    }

}
