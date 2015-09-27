package org.jurassicraft.common.entity;

import java.util.Random;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import net.timeless.animationapi.IAnimatedEntity;
import net.timeless.animationapi.client.AnimID;
import net.timeless.unilib.common.animation.ChainBuffer;
import net.timeless.unilib.common.animation.ControlledParam;

import org.jurassicraft.JurassiCraft;
import org.jurassicraft.common.entity.ai.animations.AnimationAIEating;
import org.jurassicraft.common.entity.ai.animations.JCNonAutoAnimBase;
import org.jurassicraft.common.entity.ai.animations.JCNonAutoAnimSoundBase;
import org.jurassicraft.common.entity.base.EntityDinosaurAggressive;

public class EntityTyrannosaurus extends EntityDinosaurAggressive implements IAnimatedEntity // , IEntityAICreature,
        // ICarnivore
{
    public ChainBuffer tailBuffer = new ChainBuffer(6);

    private static final String[] hurtSounds = new String[]{"tyrannosaurus_hurt_1", "tyrannosaurus_hurt_2"};
    private static final String[] deathSounds = new String[]{"tyrannosaurus_death_1"};
    private static final String[] roarSounds = new String[]{"tyrannosaurus_roar_1"};
    private static final String[] breathSounds = new String[]{"tyrannosaurus_breath_1"};

    private static final Class[] targets = {EntityCompsognathus.class, EntityAnkylosaurus.class, EntityPlayer.class,
            EntityDilophosaurus.class, EntityDimorphodon.class, EntityDodo.class, EntityLeaellynasaura.class,
            EntityLudodactylus.class, EntityHypsilophodon.class, EntityGallimimus.class, EntitySegisaurus.class,
            EntityProtoceratops.class, EntityParasaurolophus.class, EntityOthnielia.class, EntityMicroceratus.class,
            EntityTriceratops.class, EntityStegosaurus.class, EntityBrachiosaurus.class, EntityApatosaurus.class,
            EntityRugops.class, EntityHerrerasaurus.class, EntityVelociraptor.class, EntityAchillobator.class,
            EntityCarnotaurus.class};

    private int stepCount = 0;

    public ControlledParam roarCount = new ControlledParam(0F, 0F, 0.5F, 0F);
    public ControlledParam roarTiltDegree = new ControlledParam(0F, 0F, 1F, 0F);

    public EntityTyrannosaurus(World world)
    {
        super(world);

        tasks.addTask(2, new JCNonAutoAnimSoundBase(this, 75, AnimID.IDLE, 750, "jurassicraft:" + roarSounds[0], 1.5F));
        tasks.addTask(2, new JCNonAutoAnimBase(this, 75, AnimID.INJURED, 750));
        tasks.addTask(2, new AnimationAIEating(this, 20, true));

        for (int i = 0; i < targets.length; i++)
        {
            this.attackCreature(targets[i], new Random().nextInt(3) + 1);
        }
    }

    @Override
    public String getLivingSound()
    {
        JurassiCraft.instance.getLogger().debug("Getting living sound");
        return randomSound(roarSounds);
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

    @Override
    public void onUpdate()
    {
        this.tailBuffer.calculateChainSwingBuffer(68.0F, 5, 4.0F, this);
        super.onUpdate();

        this.roarCount.update();
        this.roarTiltDegree.update();

        if (this.ticksExisted % 62 == 0)
            this.playSound(randomSound(breathSounds), this.getSoundVolume(), this.getSoundPitch());

        /** Step Sound */
        if (this.moveForward > 0 && this.stepCount <= 0)
        {
            this.playSound("jurassicraft:stomp", (float) transitionFromAge(0.1F, 1.0F), this.getSoundPitch());
            stepCount = 65;
        }

        this.stepCount -= this.moveForward * 9.5;
    }
}
