package net.ilexiconn.jurassicraft.Handlers;


import net.ilexiconn.jurassicraft.block.BlockEncasedFossil;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;

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
