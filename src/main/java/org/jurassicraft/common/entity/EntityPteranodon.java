package org.jurassicraft.common.entity;

import net.minecraft.world.World;
import net.timeless.animationapi.client.Animations;
import org.jurassicraft.common.entity.ai.animations.JCNonAutoAnimBase;
import org.jurassicraft.common.entity.ai.animations.JCNonAutoAnimSoundBase;
import org.jurassicraft.common.entity.base.EntityDinosaurFlyingAggressive;

public class EntityPteranodon extends EntityDinosaurFlyingAggressive // implements IEntityAIFlyingCreature, ICarnivore
{
    private static final String[] hurtSounds = new String[] { "pteranodon_hurt_1" };
    private static final String[] livingSounds = new String[] { "pteranodon_living_1" };
    private static final String[] deathSounds = new String[] { "pteranodon_death_1" };
    private static final String[] callSounds = new String[] { "pteranodon_call_1" };

    public EntityPteranodon(World world)
    {
        super(world);
        tasks.addTask(2, new JCNonAutoAnimBase(this, 25, Animations.LOOKING_RIGHT.get(), 100)); // Head twitch right
        tasks.addTask(2, new JCNonAutoAnimBase(this, 25, Animations.LOOKING_LEFT.get(), 100)); // Head twitch left
        tasks.addTask(2, new JCNonAutoAnimSoundBase(this, 34, Animations.CALLING.get(), 100, "jurassicraft:" + callSounds[0], 2.5F)); // Call
    }

    @Override
    public int getTailBoxCount()
    {
        return 0;
    }

    @Override
    public String getLivingSound()
    {
        return null;
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
