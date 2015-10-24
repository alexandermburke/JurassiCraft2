package org.jurassicraft.common.entity;

import net.minecraft.world.World;
import org.jurassicraft.common.entity.base.EntityDinosaurDefensiveHerbivore;

import java.lang.Override;

public class EntityTroodon extends EntityDinosaurDefensiveHerbivore
{
    public EntityTroodon(World world)
    {
        super(world);
    }

    @Override
    public int getTailBoxCount()
    {
        return 5;
    }
}
