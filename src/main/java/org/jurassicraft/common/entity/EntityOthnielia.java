package org.jurassicraft.common.entity;

import net.minecraft.world.World;
import org.jurassicraft.common.entity.base.EntityDinosaurDefensiveHerbivore;

public class EntityOthnielia extends EntityDinosaurDefensiveHerbivore  //implements IEntityAICreature, IHerbivore
{
    public EntityOthnielia(World world)
    {
        super(world);
    }

    @Override
    public int getTailBoxCount()
    {
        return 6;
    }
}
