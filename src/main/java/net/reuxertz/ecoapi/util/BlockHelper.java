package net.reuxertz.ecoapi.util;


import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;

import java.util.List;
import java.util.Random;

public class BlockHelper
{
    public static void scheduleUpdate(World w, Block block, BlockPos p, Random rand, int minTick, int maxTick)
    {
        w.scheduleUpdate(p, block, minTick + rand.nextInt(1 + maxTick - minTick));
    }

    public static boolean getBlockEquals(Block b1, List<Block> blocks)
    {
        for (Block bi: blocks)
            if (Block.isEqualTo(bi, b1))
                return true;

        return false;
    }
    public static boolean getBlockStateEquals(IBlockState b1, IBlockState b2)
    {
        if (b1.getBlock() == b2.getBlock() &&
                b1.getBlock().getMetaFromState(b1) == b2.getBlock().getMetaFromState(b2))
            return true;

        return false;
    }

}