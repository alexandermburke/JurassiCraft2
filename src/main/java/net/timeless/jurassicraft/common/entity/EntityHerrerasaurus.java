package net.timeless.jurassicraft.common.entity;

import net.minecraft.world.World;
import net.reuxertz.ecoapi.ecology.role.ICarnivore;
import net.reuxertz.ecoapi.entity.IEntityAICreature;
import net.timeless.jurassicraft.common.entity.base.EntityDinosaurAggressive;
import net.timeless.unilib.common.animation.ChainBuffer;

public class EntityHerrerasaurus extends EntityDinosaurAggressive implements IEntityAICreature, ICarnivore
{
    private static final String[] deathSounds = new String[] { "herrerasaurus_death_1" };
    private static final String[] livingSounds = new String[] { "herrerasaurus_living_1" };
    public ChainBuffer tailBuffer = new ChainBuffer(6);

    public EntityHerrerasaurus(World world)
    {
        super(world);
    }

    public String getLivingSound()
    {
        return randomSound(livingSounds);
    }

    public String getHurtSound()
    {
        return randomSound(livingSounds);
    }

    public String getDeathSound()
    {
        return randomSound(deathSounds);
    }

    public float getSoundVolume()
    {
        return (float) transitionFromAge(1.3F, 2.0F);
    }

    public void onUpdate()
    {
        super.onUpdate();

        this.tailBuffer.calculateChainSwingBuffer(68.0F, 5, 4.0F, this);
    }
}
