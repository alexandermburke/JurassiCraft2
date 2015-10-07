package org.jurassicraft.common.entity;

import net.minecraft.world.World;
import org.jurassicraft.common.entity.base.EntityDinosaurDefensiveHerbivore;

public class EntityProtoceratops extends EntityDinosaurDefensiveHerbivore // implements IEntityAICreature, IHerbivore
{
    public EntityProtoceratops(World world)
    {
        super(world);
    }

    @Override
    public int getTailBoxCount()
    {
        return 4;
    }
}
