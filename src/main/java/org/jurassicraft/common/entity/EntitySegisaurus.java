package org.jurassicraft.common.entity;

import net.minecraft.world.World;
import org.jurassicraft.common.entity.base.EntityDinosaurAggressive;

public class EntitySegisaurus extends EntityDinosaurAggressive // implements IEntityAICreature, ICarnivore
{
    public EntitySegisaurus(World world)
    {
        super(world);
    }

    @Override
    public int getTailBoxCount()
    {
        return 5;
    }
}
