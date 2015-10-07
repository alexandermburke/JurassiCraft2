package org.jurassicraft.common.entity;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import org.jurassicraft.common.entity.base.EntityDinosaurAggressive;

import java.util.Random;

public class EntityBaryonyx extends EntityDinosaurAggressive // implements IEntityAICreature, ICarnivore
{
    private static final Class[] targets = {EntityCompsognathus.class, EntityAnkylosaurus.class, EntityPlayer.class, EntityDilophosaurus.class, EntityDimorphodon.class, EntityDodo.class, EntityLeaellynasaura.class, EntityLudodactylus.class, EntityHypsilophodon.class, EntityGallimimus.class, EntitySegisaurus.class, EntityProtoceratops.class, EntityParasaurolophus.class, EntityOthnielia.class, EntityMicroceratus.class, EntityTriceratops.class, EntityStegosaurus.class, EntityBrachiosaurus.class, EntityApatosaurus.class, EntityRugops.class, EntityHerrerasaurus.class, EntityVelociraptor.class, EntityAchillobator.class, EntityCarnotaurus.class};

    public EntityBaryonyx(World world)
    {
        super(world);

        for (int i = 0; i < targets.length; i++)
        {
            this.addAIForAttackTargets(targets[i], new Random().nextInt(3) + 1);
        }
    }

    @Override
    public int getTailBoxCount()
    {
        return 6;
    }
}