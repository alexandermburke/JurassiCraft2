package org.jurassicraft.common.entity;

import net.minecraft.world.World;
import org.jurassicraft.common.entity.base.EntityDinosaurProvokableHerbivore;

public class EntityStegosaurus extends EntityDinosaurProvokableHerbivore  //implements IEntityAICreature, IHerbivore
{
    private static final String[] hurtSounds = new String[]{"stegosaurus_hurt_1", "stegosaurus_hurt_2"};
    private static final String[] livingSounds = new String[]{"stegosaurus_living_1", "stegosaurus_living_2"};
    private static final String[] deathSounds = new String[]{"stegosaurus_death_1", "stegosaurus_death_2"};

    public EntityStegosaurus(World world)
    {
        super(world);
    }

    @Override
    public int getTailBoxCount()
    {
        return 6;
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