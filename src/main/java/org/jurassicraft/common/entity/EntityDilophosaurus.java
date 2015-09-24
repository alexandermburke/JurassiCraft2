package org.jurassicraft.common.entity;

import net.minecraft.world.World;
import net.timeless.unilib.common.animation.ChainBuffer;
import org.jurassicraft.common.entity.base.EntityDinosaurAggressive;

public class EntityDilophosaurus extends EntityDinosaurAggressive  //implements IEntityAICreature, ICarnivore
{
    public ChainBuffer tailBuffer = new ChainBuffer(6);

    private static final String[] hurtSounds = new String[]{"dilophosaurus_hurt_1", "dilophosaurus_hurt_2"};
    private static final String[] livingSounds = new String[]{"dilophosaurus_living_1", "dilophosaurus_living_2", "dilophosaurus_living_3"};
    private static final String[] deathSounds = new String[]{"dilophosaurus_death_1"};

    public EntityDilophosaurus(World world)
    {
        super(world);
    }

    public void onUpdate()
    {
        super.onUpdate();

        this.tailBuffer.calculateChainSwingBuffer(68.0F, 5, 4.0F, this);
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