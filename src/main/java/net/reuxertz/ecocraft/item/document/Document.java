package net.reuxertz.ecocraft.item.document;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.event.entity.player.EntityInteractEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.reuxertz.ecocraft.api.IItem;
import net.reuxertz.ecocraft.core.utils.IDObj;
import net.reuxertz.ecocraft.core.utils.NBTHelper;
import net.reuxertz.ecocraft.core.utils.StringHelper;

public class Document extends Item implements IItem
{
    protected boolean _displayNameLoc = true;
    protected String _displayName = "Document";

    public static String GetID(ItemStack stack)
    {
        if (stack.hasTagCompound() && stack.getTagCompound().hasKey("entityid"))
            return stack.getTagCompound().getString("entityid");

        return null;
    }
    public String getItemStackDisplayName(ItemStack par1ItemStack)
    {
        String r = this._displayName;
        boolean changed = false;

        NBTTagCompound nbt = par1ItemStack.getTagCompound();
        if ((this._displayNameLoc) && (nbt != null))
        {
            if (nbt.hasKey("entityid"))
            {
                r = r + " - " + nbt.getString("entityid");
                changed = true;
            }
            if (nbt.hasKey("blockpos_x"))
            {
                r = r + " - " + StringHelper.BlockPosToString(NBTHelper.ReadBlockPosFromNBT(nbt, "blockpos"));
                changed = true;
            }
        }
        if (!changed) {
            r = r + " - empty";
        }
        return r;
    }
    public Document()
    {
        super();

        setMaxStackSize(1);
        setCreativeTab(CreativeTabs.tabMisc);
    }

    public void InteractEntity(ItemStack stack, EntityInteractEvent e)
    {
        stack.setTagCompound(new NBTTagCompound());

        NBTTagCompound nbt = stack.getTagCompound();
        String id = IDObj.GetID(e.target, false);
        if (id != null) {
            nbt.setString("entityid", id);
            stack.setItemDamage(1);
        }
        stack.setTagCompound(nbt);
    }

    public void InteractBlock(ItemStack stack, PlayerInteractEvent e)
    {
        stack.setTagCompound(new NBTTagCompound());

        NBTTagCompound nbt = stack.getTagCompound();

        NBTHelper.WriteBlockPosToNBT(nbt, "blockpos", e.pos);

        stack.setTagCompound(nbt);

        stack.setItemDamage(1);
    }
}
