package org.jurassicraft.common.entity;

import net.minecraft.world.World;
import net.timeless.unilib.common.animation.ChainBuffer;
import org.jurassicraft.common.entity.base.EntityDinosaurSwimmingAggressive;

public class EntityTylosaurus extends EntityDinosaurSwimmingAggressive // implements IEntityAISwimmingCreature, ICarnivore
{
    public EntityTylosaurus(World world)
    {
        super(world);
    }

    @Override
    public int getTailBoxCount()
    {
        return 9;
    }
}
