package net.timeless.jurassicraft.handler;

import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import net.timeless.jurassicraft.block.BlockEncasedFossil;

public class FossilHandler
{
    /**
     * Returns the period of time (or metadata) as cretaceous.
     */
    public static int getDefaultTimePeriod()
    {
        return BlockEncasedFossil.EnumTimePeriod.CRETACEOUS.getMetadata();
    }

    /**
     * Returns the period of time (or metadata) based on a position.
     */
    public static int getTimePeriod(World world, BlockPos pos)
    {
        world.getBiomeGenForCoords(pos);

        return BlockEncasedFossil.EnumTimePeriod.CRETACEOUS.getMetadata();
    }
}
