package org.jurassicraft.common.entity;

import net.minecraft.world.World;
import net.timeless.unilib.common.animation.ChainBuffer;
import org.jurassicraft.common.entity.base.EntityDinosaurDefensiveHerbivore;

public class EntityGallimimus extends EntityDinosaurDefensiveHerbivore  //implements IEntityAICreature, IHerbivore
{
    private static final String[] hurtSounds = new String[]{"gallimimus_hurt_1", "gallimimus_hurt_2"};
    private static final String[] livingSounds = new String[]{"gallimimus_living_1", "gallimimus_living_2"};
    private static final String[] deathSounds = new String[]{"gallimimus_death_1", "gallimimus_death_2"};

    public EntityGallimimus(World world)
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