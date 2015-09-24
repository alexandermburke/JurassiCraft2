package org.jurassicraft.common.entity;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import net.timeless.unilib.common.animation.ChainBuffer;
import org.jurassicraft.common.entity.base.EntityDinosaurAggressive;

import java.util.Random;

public class EntityBaryonyx extends EntityDinosaurAggressive // implements IEntityAICreature, ICarnivore
{
    private static final Class[] targets = {EntityCompsognathus.class, EntityAnkylosaurus.class, EntityPlayer.class, EntityDilophosaurus.class, EntityDimorphodon.class, EntityDodo.class, EntityLeaellynasaura.class, EntityLudodactylus.class, EntityHypsilophodon.class, EntityGallimimus.class, EntitySegisaurus.class, EntityProtoceratops.class, EntityParasaurolophus.class, EntityOthnielia.class, EntityMicroceratus.class, EntityTriceratops.class, EntityStegosaurus.class, EntityBrachiosaurus.class, EntityApatosaurus.class, EntityRugops.class, EntityHerrerasaurus.class, EntityVelociraptor.class, EntityAchillobator.class, EntityCarnotaurus.class};

    public ChainBuffer tailBuffer = new ChainBuffer(6);

    public EntityBaryonyx(World world)
    {
        super(world);
        for (int i = 0; i < targets.length; i++)
        {
            this.attackCreature(targets[i], new Random().nextInt(3) + 1);
        }
    }

    public void onUpdate()
    {
        this.tailBuffer.calculateChainSwingBuffer(68.0F, 5, 4.0F, this);
        super.onUpdate();
    }
}