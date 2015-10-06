package org.jurassicraft.common.entity;

import net.minecraft.world.World;
import org.jurassicraft.common.entity.base.EntityDinosaurDefensiveHerbivore;

public class EntityOviraptor extends EntityDinosaurDefensiveHerbivore  //implements IEntityAICreature, IHerbivore
{
    public EntityOviraptor(World world)
    {
        super(world);
    }

    @Override
    public int getTailBoxCount()
    {
        return 0;
    }
}