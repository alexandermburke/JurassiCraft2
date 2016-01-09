package org.jurassicraft.common.entity.ai.util;

import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;

/**
 * Copyright 2016 Timeless Mod Team.
 * <p>
 * This mod is used to compute and update the break status on the mod.
 */
public class BlockBreaker
{
    // This is a base factor use to adjust break time.
    public static final float BASE_BREAK_FACTOR = 2.5F;

    public BlockBreaker(World world, Entity entity, BlockPos pos)
    {
        _world = world;
        _entityID = entity.getEntityId();
        _pos = pos;
        _ticksNeeded = (int)(breakSeconds(world, _entityID, pos) * 20);
    }

    /**
     * Returns the number of seconds needed to break this block based on the
     * block and digSpeed.
     * <p>
     * A stone pickaxe takes about 1 second to break a stone block.
     * <ul>
     * <li> Stone hardness = 1.5 </li>
     * <li> Stone pickaxe has a 4x dig speed  </li>
     * <li> A 1.0 workspeed is steve. </li>
     * </ul>
     * Thus, Steve with a stone pickaxe has a 4.0 work speed which results in 18.75 ticks.
     * This is based on dig factors from the base minecraft.
     *
     * @param world The world
     * @param digSpeed Dig speed of the digger.  Should combine entity dig speed
     *                 and the work speed factor.
     * @param pos The pos of the block to dig.
     * @return The number of seconds to break.
     */
    public static float breakSeconds(World world, float digSpeed, BlockPos pos)
    {

        IBlockState state = world.getBlockState(pos);
        float getHardness = state.getBlock().getBlockHardness(world, pos);

        return (BASE_BREAK_FACTOR * getHardness) / (digSpeed) ;
    }

    /**
     * Updated the break status of the block.   Returns true if the block is broken.
     * @return True if time to break.
     */
    public boolean tickUpdate()
    {
        // we have 10 stages
        ++_ticksDone;
        int i = (int)((float)_ticksDone/ _ticksNeeded * 10.0F);

        if (i != this._previousTicksDone)
        {
            _world.sendBlockBreakProgress(_entityID, _pos, i);
            this._previousTicksDone = i;
        }

        return (_ticksDone < _ticksNeeded);
    }

    /**
     * Reset the break progress on this block.
     */
    public void reset()
    {
        _ticksDone = 0;
        _previousTicksDone = 0;
        _world.sendBlockBreakProgress(_entityID, _pos, 0);
    }

    private final World _world;

    private final BlockPos _pos;
    private final int _ticksNeeded;
    private final int _entityID;

    private int _ticksDone;
    private int _previousTicksDone;
}
