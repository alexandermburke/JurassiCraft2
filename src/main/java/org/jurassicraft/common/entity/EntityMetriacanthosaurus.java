package org.jurassicraft.common.entity;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import org.jurassicraft.common.entity.base.EntityDinosaurAggressive;

import java.util.Random;

public class EntityMetriacanthosaurus extends EntityDinosaurAggressive
{

    private static final Class[] targets = { EntityCompsognathus.class, EntityAnkylosaurus.class, EntityPlayer.class, EntityDilophosaurus.class, EntityDimorphodon.class, EntityDodo.class, EntityLeaellynasaura.class, EntityLudodactylus.class, EntityHypsilophodon.class, EntityGallimimus.class, EntitySegisaurus.class, EntityProtoceratops.class, EntityParasaurolophus.class, EntityOthnielia.class, EntityMicroceratus.class, EntityTriceratops.class, EntityStegosaurus.class };
    private static final Class[] deftargets = { EntityPlayer.class, EntityTyrannosaurus.class, EntityGiganotosaurus.class, EntitySpinosaurus.class };

    public EntityMetriacanthosaurus(World world)
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
        return 8;
    }
}
