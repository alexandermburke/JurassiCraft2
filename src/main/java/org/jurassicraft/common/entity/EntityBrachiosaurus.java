package org.jurassicraft.common.entity;

import net.minecraft.world.World;
import net.timeless.unilib.common.animation.ChainBuffer;
import org.jurassicraft.common.entity.base.EntityDinosaurDefensiveHerbivore;

public class EntityBrachiosaurus extends EntityDinosaurDefensiveHerbivore  //implements IEntityAICreature, IHerbivore
{
    private static final String[] livingSounds = new String[]{"brachiosaurus_living_1", "brachiosaurus_living_2", "brachiosaurus_living_3", "brachiosaurus_living_4"};

    private static final String[] hurtSounds = new String[]{"brachiosaurus_hurt_1", "brachiosaurus_hurt_2"};
    private static final String[] deathSounds = new String[]{"brachiosaurus_death_1", "brachiosaurus_death_2"};

    private int stepCount = 0;

    public EntityBrachiosaurus(World world)
    {
        super(world);
    }

    @Override
    public int getTailBoxCount()
    {
        return 5;
    }

    public void onUpdate()
    {
        super.onUpdate();

        /** Step Sound */
        if (this.moveForward > 0 && this.stepCount <= 0)
        {
            this.playSound("jurassicraft:stomp", (float) transitionFromAge(0.1F, 1.0F), this.getSoundPitch());
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