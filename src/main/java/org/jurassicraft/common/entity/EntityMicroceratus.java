package org.jurassicraft.common.entity;

import net.minecraft.world.World;
import org.jurassicraft.common.entity.base.EntityDinosaurDefensiveHerbivore;

public class EntityMicroceratus extends EntityDinosaurDefensiveHerbivore // implements IEntityAICreature, IHerbivore
{
    public EntityMicroceratus(World world)
    {
        super(world);
    }

    @Override
    public int getTailBoxCount()
    {
        return 5;
    }
}
