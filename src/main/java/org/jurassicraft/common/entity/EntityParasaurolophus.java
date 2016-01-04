package org.jurassicraft.common.entity;

import net.minecraft.world.World;
import net.timeless.animationapi.client.Animations;
import org.jurassicraft.common.entity.ai.animations.JCNonAutoAnimBase;
import org.jurassicraft.common.entity.base.EntityDinosaurDefensiveHerbivore;

public class EntityParasaurolophus extends EntityDinosaurDefensiveHerbivore // implements IEntityAICreature, IHerbivore
{
    public EntityParasaurolophus(World world)
    {
        super(world);
        tasks.addTask(2, new JCNonAutoAnimBase(this, 60, Animations.CALLING.get(), 100)); // Call
    }

    @Override
    public int getTailBoxCount()
    {
        return 6;
    }
}
