package org.jurassicraft.common.entity;

import net.minecraft.world.World;
import org.jurassicraft.common.entity.base.EntityDinosaurDefensiveHerbivore;

public class EntityTherizinosaurus extends EntityDinosaurDefensiveHerbivore // implements IEntityAICreature, IHerbivore
{
    public EntityTherizinosaurus(World world)
    {
        super(world);
        this.addAIForAttackTargets(EntityTyrannosaurus.class, 1);
    }

    @Override
    public int getTailBoxCount()
    {
        return 6;
    }
}
