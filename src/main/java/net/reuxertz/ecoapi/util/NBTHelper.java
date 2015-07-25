package net.reuxertz.ecoapi.util;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.BlockPos;

public class NBTHelper
{
    public static boolean hasBlockPosInNBT(NBTTagCompound nbt, String name)
    {
        return nbt.hasKey(name + "_x");
    }

    public static BlockPos readBlockPosFromNBT(NBTTagCompound nbt, String name)
    {
        int x = nbt.getInteger(name + "_x");
        int y = nbt.getInteger(name + "_y");
        int z = nbt.getInteger(name + "_z");

        return new BlockPos(x, y, z);
    }

    public static void writeBlockPosToNBT(NBTTagCompound nbt, String name, BlockPos bp)
    {
        nbt.setInteger(name + "_x", bp.getX());
        nbt.setInteger(name + "_y", bp.getY());
        nbt.setInteger(name + "_z", bp.getZ());
    }
}
