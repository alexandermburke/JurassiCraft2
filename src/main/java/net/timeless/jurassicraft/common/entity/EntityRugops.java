package net.timeless.jurassicraft.common.entity;

import net.minecraft.world.World;
import net.reuxertz.ecoapi.ecology.role.ICarnivore;
import net.reuxertz.ecoapi.entity.IEntityAICreature;
import net.timeless.jurassicraft.common.entity.base.EntityDinosaurAggressive;
import net.timeless.unilib.common.animation.ChainBuffer;

public class EntityRugops extends EntityDinosaurAggressive implements IEntityAICreature, ICarnivore
{
    private static final String[] hurtSounds = new String[]{"rugops_hurt_1", "rugops_hurt_2"};
    private static final String[] livingSounds = new String[]{"rugops_living_1", "rugops_living_2", "rugops_living_3", "rugops_living_4"};
    private static final String[] deathSounds = new String[]{"rugops_death_1", "rugops_death_2"};
    public ChainBuffer tailBuffer = new ChainBuffer(6);

    public EntityRugops(World world)
    {
        super(world);
    }

    public void onUpdate()
    {
        this.tailBuffer.calculateChainSwingBuffer(68.0F, 5, 4.0F, this);
        super.onUpdate();
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