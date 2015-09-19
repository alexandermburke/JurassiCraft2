package net.timeless.jurassicraft.common.entity;

import net.minecraft.world.World;
import net.timeless.animationapi.AnimationAPI;
import net.timeless.animationapi.client.AnimID;
import net.timeless.jurassicraft.common.entity.ai.EntityAIFindPlant;
import net.timeless.jurassicraft.common.entity.ai.animations.*;
import net.timeless.jurassicraft.common.entity.base.EntityDinosaur;

public class EntityDodo extends EntityDinosaur // implements IEntityAICreature, IHerbivore
{
    private static final String[] hurtSounds = new String[]{"dodo_hurt_1", "dodo_hurt_2"};
    private static final String[] livingSounds = new String[]{"dodo_living_1", "dodo_living_2", "dodo_living_3"};
    private static final String[] deathSounds = new String[]{"dodo_death_1"};

    public EntityDodo(World world)
    {
        super(world);
        tasks.addTask(2, new JCAutoAnimBase(this, 18, AnimID.EATING));
        tasks.addTask(1, new EntityAIFindPlant(this));
    }

    public String getLivingSound()
    {
        return randomSound(livingSounds);
    }

    public String getHurtSound()
    {
        return randomSound(hurtSounds);
    }

    public String getDeathSound()
    {
        return randomSound(deathSounds);
    }

    public void onUpdate()
    {
        super.onUpdate();
    }
}
