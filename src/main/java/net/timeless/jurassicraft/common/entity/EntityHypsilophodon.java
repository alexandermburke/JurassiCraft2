package net.timeless.jurassicraft.common.entity;

import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAIPanic;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import net.timeless.jurassicraft.client.model.animation.ai.JCNonAutoAnimBase;
import net.timeless.jurassicraft.common.entity.base.EntityDinosaurDefensiveHerbivore;

public class EntityHypsilophodon extends EntityDinosaurDefensiveHerbivore
{
    private static final String[] hurtSounds = new String[] { "hypsilophodon_hurt_1", "hypsilophodon_hurt_2" };
    private static final String[] livingSounds = new String[] { "hypsilophodon_living_1", "hypsilophodon_living_2", "hypsilophodon_living_3", "hypsilophodon_living_4" };

    public static final int SCRATCH = 15;
    
    public EntityHypsilophodon(World world)
    {
        super(world);
        this.tasks.addTask(1, new EntityAIPanic(this, 2.0D));

        this.tasks.addTask(6, new EntityAIWander(this, dinosaur.getAdultSpeed()));
        this.tasks.addTask(7, new EntityAIWatchClosest(this, EntityPlayer.class, 6.0F));
        this.tasks.addTask(8, new EntityAILookIdle(this));
        tasks.addTask(2, new JCNonAutoAnimBase(this, 35, SCRATCH, 60)); //Sniff
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
        return randomSound(hurtSounds);
    }
}