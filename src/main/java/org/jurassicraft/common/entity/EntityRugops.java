package org.jurassicraft.common.entity;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import org.jurassicraft.common.entity.base.EntityDinosaurAggressive;

import java.util.Random;

public class EntityRugops extends EntityDinosaurAggressive // implements IEntityAICreature, ICarnivore
{
    private static final String[] hurtSounds = new String[] { "rugops_hurt_1", "rugops_hurt_2" };
    private static final String[] livingSounds = new String[] { "rugops_living_1", "rugops_living_2", "rugops_living_3", "rugops_living_4" };
    private static final String[] deathSounds = new String[] { "rugops_death_1", "rugops_death_2" };

    private static final Class[] targets = { EntityCompsognathus.class, EntityAnkylosaurus.class, EntityPlayer.class, EntityDilophosaurus.class, EntityDimorphodon.class, EntityDodo.class, EntityLeaellynasaura.class, EntityLudodactylus.class, EntityHypsilophodon.class, EntityGallimimus.class, EntitySegisaurus.class, EntityProtoceratops.class, EntityParasaurolophus.class, EntityOthnielia.class, EntityMicroceratus.class, EntityTriceratops.class, EntityStegosaurus.class };
    private static final Class[] deftargets = { EntityPlayer.class, EntityTyrannosaurus.class, EntityGiganotosaurus.class, EntitySpinosaurus.class };

    public EntityRugops(World world)
    {
        super(world);

        for (Class target : targets) {
            this.addAIForAttackTargets(target);
        }

        for (Class deftarget : deftargets) {
            this.defendFromAttacker(deftarget, new Random().nextInt(3) + 1);
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
        return randomSound(hurtSounds);
    }

    public String getDeathSound()
    {
        return randomSound(deathSounds);
    }
}
