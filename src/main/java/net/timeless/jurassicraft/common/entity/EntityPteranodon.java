package net.timeless.jurassicraft.common.entity;

import net.minecraft.world.World;
import net.timeless.jurassicraft.common.entity.ai.animations.JCNonAutoAnimBase;
import net.timeless.jurassicraft.common.entity.ai.animations.JCNonAutoAnimSoundBase;
import net.timeless.jurassicraft.common.entity.base.EntityDinosaurFlyingAggressive;

public class EntityPteranodon extends EntityDinosaurFlyingAggressive // implements IEntityAIFlyingCreature, ICarnivore
{
    private static final String[] hurtSounds = new String[]{"pteranodon_hurt_1"};
    private static final String[] livingSounds = new String[]{"pteranodon_living_1"};
    private static final String[] deathSounds = new String[]{"pteranodon_death_1"};
    private static final String[] callSounds = new String[]{"pteranodon_call_1"};

    public EntityPteranodon(World world)
    {
        super(world);
        tasks.addTask(2, new JCNonAutoAnimBase(this, 25, 10, 100)); //Head twitch right
        tasks.addTask(2, new JCNonAutoAnimBase(this, 25, 11, 100)); //Head twitch left
        tasks.addTask(2, new JCNonAutoAnimSoundBase(this, 34, 12, 100, "jurassicraft:" + callSounds[0], 2.5F)); //Call
    }

    public void onUpdate()
    {
        super.onUpdate();
    }

    public String getLivingSound()
    {
        return null;
    }

    public String getHurtSound()
    {
        return randomSound(hurtSounds);
    }

    public String getDeathSound()
    {
        return randomSound(deathSounds);
    }
}