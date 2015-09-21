package org.jurassicraft.common.entity;

import net.minecraft.world.World;
import org.jurassicraft.common.entity.base.EntityDinosaurProvokableHerbivore;
import net.timeless.unilib.common.animation.ChainBuffer;

public class EntityTriceratops extends EntityDinosaurProvokableHerbivore  //implements IEntityAICreature, IHerbivore
{
    public ChainBuffer tailBuffer = new ChainBuffer(6);

    private static final String[] hurtSounds = new String[]{"triceratops_hurt_1"};
    private static final String[] livingSounds = new String[]{"triceratops_living_1", "triceratops_living_2", "triceratops_living_3", "triceratops_living_4"};
    private static final String[] deathSounds = new String[]{"triceratops_death_1"};

    public EntityTriceratops(World world)
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