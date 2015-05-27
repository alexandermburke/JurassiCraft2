package net.reuxertz.ecocraft.core.utils;

import net.minecraft.util.BlockPos;

public class StringHelper
{

    public static String BlockPosToString(BlockPos bp)
    {
        return "(" + bp.getX() + ", " + bp.getY() + ", " + bp.getZ() + ")";
    }
}
