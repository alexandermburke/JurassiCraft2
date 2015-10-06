package org.jurassicraft.common.entity;

import net.minecraft.world.World;
import org.jurassicraft.common.entity.base.EntityDinosaurSwimmingAggressive;

public class EntityCoelacanth extends EntityDinosaurSwimmingAggressive //implements IEntityAISwimmingCreature, ICarnivore
{
    public EntityCoelacanth(World world)
    {
        super(world);
    }

    @Override
    public int getTailBoxCount()
    {
        return 0;
    }
}
