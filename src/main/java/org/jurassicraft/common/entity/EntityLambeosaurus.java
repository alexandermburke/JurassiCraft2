package org.jurassicraft.common.entity;

import net.minecraft.world.World;
import org.jurassicraft.common.entity.base.EntityDinosaurDefensiveHerbivore;

import java.lang.Override;

public class EntityLambeosaurus extends EntityDinosaurDefensiveHerbivore
{
    public EntityLambeosaurus(World world)
    {
        super(world);
    }

    @Override
    public int getTailBoxCount()
    {
        return 6;
    }
}
