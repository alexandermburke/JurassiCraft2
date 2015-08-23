package net.timeless.jurassicraft.common.entity;

import net.minecraft.world.World;
import net.reuxertz.ecoapi.ecology.role.IHerbivore;
import net.reuxertz.ecoapi.entity.IEntityAICreature;
import net.timeless.jurassicraft.common.entity.ai.animations.JCNonAutoAnimBase;
import net.timeless.jurassicraft.common.entity.base.EntityDinosaurDefensiveHerbivore;
import net.timeless.unilib.common.animation.ChainBuffer;

public class EntityHypsilophodon extends EntityDinosaurDefensiveHerbivore // implements IEntityAICreature, IHerbivore
{
    public ChainBuffer tailBuffer = new ChainBuffer(6);

    private static final String[] hurtSounds = new String[] { "hypsilophodon_hurt_1", "hypsilophodon_hurt_2" };
    private static final String[] livingSounds = new String[] { "hypsilophodon_living_1", "hypsilophodon_living_2", "hypsilophodon_living_3", "hypsilophodon_living_4" };

    public static final int SCRATCH = 15;
    
    public EntityHypsilophodon(World world)
    {
        super(world);
        tasks.addTask(2, new JCNonAutoAnimBase(this, 35, SCRATCH, 60)); //Scratch Animation
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

    public void onUpdate()
    {
        super.onUpdate();

        this.tailBuffer.calculateChainSwingBuffer(68.0F, 5, 4.0F, this);
    }
}