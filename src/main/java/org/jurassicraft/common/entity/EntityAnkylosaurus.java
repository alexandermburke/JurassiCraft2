package org.jurassicraft.common.entity;

import net.minecraft.world.World;
import net.timeless.unilib.common.animation.ChainBuffer;
import org.jurassicraft.common.entity.base.EntityDinosaurProvokableHerbivore;

public class EntityAnkylosaurus extends EntityDinosaurProvokableHerbivore  //implements IEntityAICreature, IHerbivore
{
    public ChainBuffer tailBuffer = new ChainBuffer(5);

    private static final String[] hurtSounds = new String[]{"ankylosaurus_hurt_1", "ankylosaurus_hurt_2"};
    private static final String[] livingSounds = new String[]{"ankylosaurus_living_1", "ankylosaurus_living_2", "ankylosaurus_living_3", "ankylosaurus_living_4"};

    public EntityAnkylosaurus(World world)
    {
        super(world);
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