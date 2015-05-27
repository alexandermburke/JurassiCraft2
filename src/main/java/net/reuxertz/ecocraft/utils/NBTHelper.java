package net.reuxertz.ecocraft.utils;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.BlockPos;

/**
 * Created by Ryan on 5/22/2015.
 */
public class NBTHelper
{
    public static boolean HasBlockPosInNBT(NBTTagCompound nbt, String name)
    {
        return nbt.hasKey(name + "_x");
    }

    public static BlockPos ReadBlockPosFromNBT(NBTTagCompound nbt, String name)
    {
        int x = nbt.getInteger(name + "_x");
        int y = nbt.getInteger(name + "_y");
        int z = nbt.getInteger(name + "_z");

        return new BlockPos(x, y, z);
    }

    public static void WriteBlockPosToNBT(NBTTagCompound nbt, String name, BlockPos bp)
    {
        nbt.setInteger(name + "_x", bp.getX());
        nbt.setInteger(name + "_y", bp.getY());
        nbt.setInteger(name + "_z", bp.getZ());
    }
}
