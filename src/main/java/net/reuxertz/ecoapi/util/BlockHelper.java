package net.reuxertz.ecoapi.util;


import net.minecraft.block.Block;

import java.util.List;

public class BlockHelper
{
    public static boolean getBlockEquals(Block b1, List<Block> blocks)
    {
        for (Block bi: blocks)
            if (Block.isEqualTo(bi, b1))
                return true;

        return false;
    }
    public static boolean getBlockEquals(List<Block> bs, List<Block> blocks)
    {
        for (Block bi: bs)
            if (BlockHelper.getBlockEquals(bi, blocks))
                return true;

        return false;
    }
}
