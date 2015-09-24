package org.jurassicraft.common.entity;

import java.util.Random;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import net.timeless.animationapi.client.AnimID;
import net.timeless.unilib.common.animation.ChainBuffer;

import org.jurassicraft.common.entity.ai.animations.JCNonAutoAnimSoundBase;
import org.jurassicraft.common.entity.base.EntityDinosaurAggressive;

public class EntitySpinosaurus extends EntityDinosaurAggressive // implements IEntityAICreature, IOmnivore
{
    public ChainBuffer tailBuffer = new ChainBuffer(6);

    private static final String[] hurtSounds = new String[]{"spinosaurus_hurt_1"};
    private static final String[] livingSounds = new String[]{"spinosaurus_living_1", "spinosaurus_living_2", "spinosaurus_living_3", "spinosaurus_living_4"};
    private static final String[] deathSounds = new String[]{"spinosaurus_death_1", "spinosaurus_death_2"};

    private static final Class[] targets = {EntityCompsognathus.class, EntityAnkylosaurus.class, EntityPlayer.class,
            EntityDilophosaurus.class, EntityDimorphodon.class, EntityDodo.class, EntityLeaellynasaura.class,
            EntityLudodactylus.class, EntityHypsilophodon.class, EntityGallimimus.class, EntitySegisaurus.class,
            EntityProtoceratops.class, EntityParasaurolophus.class, EntityOthnielia.class, EntityMicroceratus.class,
            EntityTriceratops.class, EntityStegosaurus.class, EntityBrachiosaurus.class, EntityApatosaurus.class,
            EntityRugops.class, EntityHerrerasaurus.class, EntityVelociraptor.class, EntityAchillobator.class,
            EntityCarnotaurus.class};

    private int stepCount = 0;

    public EntitySpinosaurus(World world)
    {
        super(world);

        for (int i = 0; i < targets.length; i++)
        {
            this.attackCreature(targets[i], new Random().nextInt(3) + 1);
        }

        tasks.addTask(2, new JCNonAutoAnimSoundBase(this, 75, AnimID.INJURED, 750, "jurassicraft:spinosaurus_hurt_1", 1.5F));
    }

    @Override
    public void onUpdate()
    {
        super.onUpdate();

        /** Step Sound */
        if (this.moveForward > 0 && this.stepCount <= 0)
        {
            this.playSound("jurassicraft:stomp", this.getSoundVolume() + 0.5F, this.getSoundPitch());
            stepCount = 65;
        }

        this.stepCount -= this.moveForward * 9.5;

        // if(this.getAnimID() == 0)
        // AnimationAPI.sendAnimPacket(this, 1);
        this.tailBuffer.calculateChainSwingBuffer(68.0F, 10, 4.0F, this);

//        // DEBUG
//        if (this.getRNG().nextInt(1000) <= 10)
//        {
//            AnimationAPI.sendAnimPacket(this, AnimID.ROARING);
//        }
    }

    @Override
    public String getLivingSound()
    {
        return randomSound(livingSounds);
    }

    @Override
    public String getHurtSound()
    {
        return randomSound(hurtSounds);
    }

    @Override
    public String getDeathSound()
    {
        return randomSound(deathSounds);
    }
}