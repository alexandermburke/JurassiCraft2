package org.jurassicraft.common.entity;

import net.minecraft.world.World;
import net.timeless.animationapi.client.Animations;
import org.jurassicraft.common.entity.ai.animations.JCAutoAnimBase;
import org.jurassicraft.common.entity.base.EntityDinosaurAggressive;

public class EntityCompsognathus extends EntityDinosaurAggressive // implements IEntityAICreature, ICarnivore
{
    public EntityCompsognathus(World world)
    {
        super(world);

        tasks.addTask(2, new JCAutoAnimBase(this, 25, Animations.BEGGING.get())); // Beg
    }

    @Override
    public int getTailBoxCount()
    {
        return 5;
    }
}
