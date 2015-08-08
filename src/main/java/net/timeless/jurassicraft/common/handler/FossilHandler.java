package net.timeless.jurassicraft.common.handler;

import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import net.timeless.jurassicraft.common.period.EnumTimePeriod;

public class FossilHandler
{
    /**
     * Returns the period of time (or metadata) as cretaceous.
     */
    public static EnumTimePeriod getDefaultTimePeriod()
    {
        return EnumTimePeriod.CRETACEOUS;
    }

    /**
     * Returns the period of time (or metadata) based on a position.
     */
    public static EnumTimePeriod getTimePeriod(World world, BlockPos pos)
    {
//        world.getBiomeGenForCoords(pos);

        return EnumTimePeriod.CRETACEOUS;
    }
}
