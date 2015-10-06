package org.jurassicraft.common.entity;

import net.minecraft.world.World;
import org.jurassicraft.common.entity.base.EntityDinosaurFlyingAggressive;

public class EntityLudodactylus extends EntityDinosaurFlyingAggressive  //implements IEntityAIFlyingCreature, ICarnivore
{
    public EntityLudodactylus(World world)
    {
        super(world);
    }

    @Override
    public int getTailBoxCount()
    {
        return 0;
    }
}
