package net.reuxertz.ecoapi.util;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

public class IDHelper
{
    public static boolean hasIDString(ItemStack is)
    {
        return is.hasTagCompound() && is.getTagCompound().hasKey("idobj_id");
    }

    public static String getIDString(ItemStack is)
    {
        if (is.hasTagCompound() && is.getTagCompound().hasKey("idobj_id"))
            return is.getTagCompound().getString("idobj_id");
        else
            return null;
    }

    public static void setID(ItemStack is, String s)
    {
        NBTTagCompound n = new NBTTagCompound();

        NBTTagCompound nbt;
        if (is.hasTagCompound())
            nbt = is.getTagCompound();
        else
            nbt = new NBTTagCompound();

        nbt.setString("idobj_id", s);

        is.setTagCompound(nbt);
    }

    public static void setIDRandom(ItemStack is)
    {
        NBTTagCompound n = new NBTTagCompound();

        String s = IdObject.generateNewId(15);

        NBTTagCompound nbt;
        if (is.hasTagCompound())
            nbt = is.getTagCompound();
        else
            nbt = new NBTTagCompound();

        nbt.setString("idobj_id", s);

        is.setTagCompound(nbt);
    }
}
