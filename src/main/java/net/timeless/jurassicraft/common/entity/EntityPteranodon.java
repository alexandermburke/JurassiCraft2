package net.timeless.jurassicraft.common.entity;

import net.minecraft.world.World;
import net.timeless.animationapi.client.AnimID;
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
        tasks.addTask(2, new JCNonAutoAnimBase(this, 25, AnimID.LOOKING_RIGHT, 100)); // Head twitch right
        tasks.addTask(2, new JCNonAutoAnimBase(this, 25, AnimID.LOOKING_LEFT, 100)); // Head twitch left
        tasks.addTask(2, new JCNonAutoAnimSoundBase(this, 34, AnimID.CALLING, 100, "jurassicraft:" + callSounds[0], 2.5F)); // Call
    }

    @Override
    public void onUpdate()
    {
        super.onUpdate();
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