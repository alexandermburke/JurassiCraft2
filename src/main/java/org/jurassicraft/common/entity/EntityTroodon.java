package org.jurassicraft.common.entity;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import org.jurassicraft.common.entity.base.EntityDinosaurAggressive;
import org.jurassicraft.common.entity.base.EntityDinosaurDefensiveHerbivore;

import java.lang.Override;
import java.util.Random;

public class EntityTroodon extends EntityDinosaurAggressive
{

    private static final Class[] targets = { EntityCompsognathus.class, EntityPlayer.class, EntityDilophosaurus.class, EntityDimorphodon.class, EntityDodo.class, EntityLeaellynasaura.class, EntityHypsilophodon.class, EntitySegisaurus.class, EntityProtoceratops.class, EntityOthnielia.class, EntityMicroceratus.class };
    private static final Class[] deftargets = { EntityPlayer.class, EntityTyrannosaurus.class, EntityGiganotosaurus.class, EntitySpinosaurus.class };

    public EntityTroodon(World world)
    {
        super(world);

        this.addAIForAttackTargets(EntityPlayer.class, 1);

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
        return 5;
    }
}
