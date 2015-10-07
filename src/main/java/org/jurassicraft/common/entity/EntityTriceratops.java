package org.jurassicraft.common.entity;

import net.minecraft.world.World;
import org.jurassicraft.common.entity.base.EntityDinosaurProvokableHerbivore;

public class EntityTriceratops extends EntityDinosaurProvokableHerbivore  //implements IEntityAICreature, IHerbivore
{
    private static final String[] hurtSounds = new String[]{"triceratops_hurt_1"};
    private static final String[] livingSounds = new String[]{"triceratops_living_1", "triceratops_living_2", "triceratops_living_3"};
    private static final String[] deathSounds = new String[]{"triceratops_death_1"};

    public EntityTriceratops(World world)
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