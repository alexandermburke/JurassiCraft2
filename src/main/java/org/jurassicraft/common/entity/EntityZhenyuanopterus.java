package org.jurassicraft.common.entity;

import net.minecraft.world.World;
import org.jurassicraft.common.entity.base.EntityDinosaurFlyingAggressive;

public class EntityZhenyuanopterus extends EntityDinosaurFlyingAggressive
{
    public EntityZhenyuanopterus(World world)
    {
        super(world);
    }

    @Override
    public int getTailBoxCount()
    {
        return 0;
    }
}
