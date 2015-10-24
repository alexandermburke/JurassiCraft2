package org.jurassicraft.common.entity;

import net.minecraft.world.World;
import org.jurassicraft.common.entity.base.EntityDinosaurDefensiveHerbivore;
import org.jurassicraft.common.entity.base.EntityDinosaurFlyingAggressive;

import java.lang.Override;

public class EntityMoganopterus extends EntityDinosaurFlyingAggressive
{
    public EntityMoganopterus(World world)
    {
        super(world);
    }

    @Override
    public int getTailBoxCount()
    {
        return 0;
    }
}
