package org.jurassicraft.common.entity;

import net.minecraft.world.World;
import org.jurassicraft.common.entity.base.EntityDinosaurDefensiveHerbivore;

import java.lang.Override;

public class EntityEdmontosaurus extends EntityDinosaurDefensiveHerbivore
{
    public EntityEdmontosaurus(World world)
    {
        super(world);
    }

    @Override
    public int getTailBoxCount()
    {
        return 6;
    }
}
