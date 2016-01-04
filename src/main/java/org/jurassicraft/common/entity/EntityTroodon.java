package org.jurassicraft.common.entity;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import org.jurassicraft.common.entity.base.EntityDinosaurAggressive;

import java.util.Random;

public class EntityTroodon extends EntityDinosaurAggressive
{

    private static final Class[] targets = { EntityCompsognathus.class, EntityPlayer.class, EntityDilophosaurus.class, EntityDimorphodon.class, EntityDodo.class, EntityLeaellynasaura.class, EntityHypsilophodon.class, EntitySegisaurus.class, EntityProtoceratops.class, EntityOthnielia.class, EntityMicroceratus.class };
    private static final Class[] deftargets = { EntityPlayer.class, EntityTyrannosaurus.class, EntityGiganotosaurus.class, EntitySpinosaurus.class };

    public EntityTroodon(World world)
    {
        super(world);

        this.addAIForAttackTargets(EntityPlayer.class);

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
        return 5;
    }
}
