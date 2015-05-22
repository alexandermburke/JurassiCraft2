package net.reuxertz.ainow.utils;

import net.minecraft.util.BlockPos;

/**
 * Created by Ryan on 5/22/2015.
 */
public class StringHelper {

    public static String BlockPosToString(BlockPos bp)
    {
        return "(" + bp.getX() + ", " + bp.getY() + ", " +bp.getZ() + ")";
    }
}
