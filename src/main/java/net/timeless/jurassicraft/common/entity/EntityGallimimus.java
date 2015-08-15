package net.timeless.jurassicraft.common.entity;

import net.minecraft.world.World;
import net.reuxertz.ecoapi.ecology.role.IHerbivore;
import net.reuxertz.ecoapi.entity.IEntityAICreature;
import net.timeless.jurassicraft.common.entity.base.EntityDinosaurDefensiveHerbivore;
import net.timeless.unilib.common.animation.ChainBuffer;

public class EntityGallimimus extends EntityDinosaurDefensiveHerbivore implements IEntityAICreature, IHerbivore
{
    private static final String[] hurtSounds = new String[] { "gallimimus_hurt_1", "gallimimus_hurt_2" };
    private static final String[] livingSounds = new String[] { "gallimimus_living_1", "gallimimus_living_2" };
    private static final String[] deathSounds = new String[] { "gallimimus_death_1", "gallimimus_death_2" };
    public ChainBuffer tailBuffer = new ChainBuffer(6);

    public EntityGallimimus(World world)
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