package org.jurassicraft.common.entity;

import net.minecraft.world.World;
import org.jurassicraft.common.entity.base.EntityDinosaurDefensiveHerbivore;
import net.timeless.unilib.common.animation.ChainBuffer;

public class EntityBrachiosaurus extends EntityDinosaurDefensiveHerbivore  //implements IEntityAICreature, IHerbivore
{
    private static final String[] livingSounds = new String[]{"brachiosaurus_living_1", "brachiosaurus_living_2", "brachiosaurus_living_3", "brachiosaurus_living_4"};

    private static final String[] hurtSounds = new String[]{"brachiosaurus_hurt_1", "brachiosaurus_hurt_2"};
    private static final String[] deathSounds = new String[]{"brachiosaurus_death_1", "brachiosaurus_death_2"};

    private int stepCount = 0;

    public ChainBuffer tailBuffer = new ChainBuffer(5);

    public EntityBrachiosaurus(World world)
    {
        super(world);
    }

    public void onUpdate()
    {
        super.onUpdate();
        this.tailBuffer.calculateChainSwingBuffer(68.0F, 5, 4.0F, this);

        /** Step Sound */
        if (this.moveForward > 0 && this.stepCount <= 0)
        {
            this.playSound("jurassicraft:stomp", (float) transitionFromAge(0.1F, 1.0F) + ((rand.nextFloat() - 0.5F) * 0.125F) + 0.5F, this.getSoundPitch());
            stepCount = 50;
        }

        this.stepCount -= this.moveForward * 9.5;
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
}