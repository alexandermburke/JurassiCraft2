package net.timeless.jurassicraft.common.entity;

import java.util.Random;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import net.reuxertz.ecoapi.ecology.role.IOmnivore;
import net.reuxertz.ecoapi.entity.IEntityAICreature;
import net.timeless.animationapi.AnimationAPI;
import net.timeless.jurassicraft.common.entity.ai.animations.JCAutoAnimSoundBase;
import net.timeless.jurassicraft.common.entity.base.EntityDinosaurAggressive;
import net.timeless.unilib.common.animation.ChainBuffer;

public class EntitySpinosaurus extends EntityDinosaurAggressive //  implements IEntityAICreature, IOmnivore
{
    public ChainBuffer tailBuffer = new ChainBuffer(6);

    private static final String[] hurtSounds = new String[] { "spinosaurus_hurt_1" };
    private static final String[] livingSounds = new String[] { "spinosaurus_living_1", "spinosaurus_living_2", "spinosaurus_living_3", "spinosaurus_living_4" };
    private static final String[] deathSounds = new String[] { "spinosaurus_death_1", "spinosaurus_death_2" };

    private static final Class[] targets = {EntityCompsognathus.class, EntityAnkylosaurus.class, EntityPlayer.class, EntityDilophosaurus.class, EntityDimorphodon.class, EntityDodo.class, EntityLeaellynasaura.class, EntityLudodactylus.class, EntityHypsilophodon.class, EntityGallimimus.class, EntitySegisaurus.class, EntityProtoceratops.class, EntityParasaurolophus.class, EntityOthnielia.class, EntityMicroceratus.class, EntityTriceratops.class, EntityStegosaurus.class, EntityBrachiosaurus.class, EntityApatosaurus.class, EntityRugops.class, EntityHerrerasaurus.class, EntityVelociraptor.class, EntityAchillobator.class, EntityCarnotaurus.class};

    public EntitySpinosaurus(World world)
    {
        super(world);
        for (int i = 0; i < targets.length; i++)
        {
            this.attackCreature(targets[i], new Random().nextInt(3)+1);
        }
        tasks.addTask(2, new JCAutoAnimSoundBase(this, 75, 1, "")); //Roar
    }

    public void onUpdate()
    {
        super.onUpdate();
//        if(this.getAnimID() == 0)
//            AnimationAPI.sendAnimPacket(this, 1);
        this.tailBuffer.calculateChainSwingBuffer(68.0F, 10, 4.0F, this);
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