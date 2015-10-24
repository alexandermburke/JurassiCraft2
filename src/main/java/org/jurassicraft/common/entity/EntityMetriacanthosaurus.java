package org.jurassicraft.common.entity;

import net.minecraft.world.World;
import org.jurassicraft.common.entity.base.EntityDinosaurAggressive;
import org.jurassicraft.common.entity.base.EntityDinosaurDefensiveHerbivore;

import java.lang.Override;

public class EntityMetriacanthosaurus extends EntityDinosaurAggressive
{
    public EntityMetriacanthosaurus(World world)
    {
        super(world);
    }

    @Override
    public int getTailBoxCount()
    {
        return 8;
    }
}
