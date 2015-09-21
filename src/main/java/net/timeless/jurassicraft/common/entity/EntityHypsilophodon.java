package net.timeless.jurassicraft.common.entity;

import net.minecraft.world.World;
import net.timeless.animationapi.client.AnimID;
import net.timeless.jurassicraft.common.entity.ai.animations.JCNonAutoAnimBase;
import net.timeless.jurassicraft.common.entity.base.EntityDinosaurDefensiveHerbivore;
import net.timeless.unilib.common.animation.ChainBuffer;

public class EntityHypsilophodon extends EntityDinosaurDefensiveHerbivore // implements IEntityAICreature, IHerbivore
{
    public ChainBuffer tailBuffer = new ChainBuffer(6);

    private static final String[] hurtSounds = new String[]{"hypsilophodon_hurt_1", "hypsilophodon_hurt_2"};
    private static final String[] livingSounds = new String[]{"hypsilophodon_living_1", "hypsilophodon_living_2",
            "hypsilophodon_living_3", "hypsilophodon_living_4"};

    public EntityHypsilophodon(World world)
    {
        super(world);
        tasks.addTask(2, new JCNonAutoAnimBase(this, 35, AnimID.SCRATCHING, 60)); // Scratch Animation
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
        return randomSound(hurtSounds);
    }

    @Override
    public void onUpdate()
    {
        super.onUpdate();

        this.tailBuffer.calculateChainSwingBuffer(68.0F, 5, 4.0F, this);
    }
}