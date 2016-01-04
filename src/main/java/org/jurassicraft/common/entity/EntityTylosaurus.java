package org.jurassicraft.common.entity;

import net.minecraft.world.World;
import org.jurassicraft.common.entity.base.EntityDinosaurSwimmingAggressive;

public class EntityTylosaurus extends EntityDinosaurSwimmingAggressive // implements IEntityAISwimmingCreature, ICarnivore
{
    private static final Class[] targets = { EntityCoelacanth.class, EntityMegapiranha.class };

    public EntityTylosaurus(World world)
    {
        super(world);

        for (Class target : targets) {
            this.addAIForAttackTargets(target);
        }
    }

    @Override
    public int getTailBoxCount()
    {
        return 9;
    }
}
