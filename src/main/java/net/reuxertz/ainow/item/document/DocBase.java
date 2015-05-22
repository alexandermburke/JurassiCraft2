package net.reuxertz.ainow.item.document;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.reuxertz.ainow.utils.NBTHelper;
import net.reuxertz.ainow.utils.StringHelper;

public abstract class DocBase extends Item
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
        String r = this._displayName + " Document - empty";

        NBTTagCompound nbt = par1ItemStack.getTagCompound();
        if (this._displayNameLoc && nbt != null && nbt.hasKey("blockpos"))
        {
            r = this._displayName + " Document - " + StringHelper.BlockPosToString(NBTHelper.GetBlockPos(nbt, "blockpos"));
        }

        return r;
    }


}
