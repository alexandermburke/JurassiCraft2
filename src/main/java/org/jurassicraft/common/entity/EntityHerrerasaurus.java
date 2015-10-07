package org.jurassicraft.common.entity;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import org.jurassicraft.common.entity.base.EntityDinosaurAggressive;

import java.util.Random;

public class EntityHerrerasaurus extends EntityDinosaurAggressive  //implements IEntityAICreature, ICarnivore
{
    private static final String[] deathSounds = new String[]{"herrerasaurus_death_1"};
    private static final String[] livingSounds = new String[]{"herrerasaurus_living_1"};

    private static final Class[] targets = {EntityCompsognathus.class, EntityAnkylosaurus.class, EntityPlayer.class, EntityDilophosaurus.class, EntityDimorphodon.class, EntityDodo.class, EntityLeaellynasaura.class, EntityLudodactylus.class, EntityHypsilophodon.class, EntityGallimimus.class, EntitySegisaurus.class, EntityProtoceratops.class, EntityParasaurolophus.class, EntityOthnielia.class, EntityMicroceratus.class, EntityTriceratops.class, EntityStegosaurus.class};
    private static final Class[] deftargets = {EntityPlayer.class, EntityTyrannosaurus.class, EntityGiganotosaurus.class, EntitySpinosaurus.class};

    public EntityHerrerasaurus(World world)
    {
        super(world);

        for (int i = 0; i < targets.length; i++)
        {
            this.addAIForAttackTargets(targets[i], new Random().nextInt(3) + 1);
        }

        for (int j = 0; j < deftargets.length; j++)
        {
            this.defendFromAttacker(deftargets[j], new Random().nextInt(3) + 1);
        }
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
}
