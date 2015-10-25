package org.jurassicraft.common.entity;

import net.minecraft.world.World;
import net.timeless.animationapi.client.AnimID;
import org.jurassicraft.common.entity.ai.metabolism.EntityAIFindPlant;
import org.jurassicraft.common.entity.ai.animations.JCAutoAnimBase;
import org.jurassicraft.common.entity.base.EntityDinosaur;

public class EntityDodo extends EntityDinosaur // implements IEntityAICreature, IHerbivore
{
    private static final String[] hurtSounds = new String[] { "dodo_hurt_1", "dodo_hurt_2" };
    private static final String[] livingSounds = new String[] { "dodo_living_1", "dodo_living_2", "dodo_living_3" };
    private static final String[] deathSounds = new String[] { "dodo_death_1" };

    public EntityDodo(World world)
    {
        super(world);
        tasks.addTask(2, new JCAutoAnimBase(this, 18, AnimID.EATING));
        tasks.addTask(1, new EntityAIFindPlant(this));
    }

    @Override
    public int getTailBoxCount()
    {
        return 0;
    }

    @Override
    public String getLivingSound()
    {
        return randomSound(livingSounds);
    }

    @Override
    public String getHurtSound()
    {
        return randomSound(hurtSounds);
    }

    @Override
    public String getDeathSound()
    {
        return randomSound(deathSounds);
    }
}
