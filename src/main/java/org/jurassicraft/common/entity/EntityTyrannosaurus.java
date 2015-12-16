package org.jurassicraft.common.entity;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.passive.IAnimals;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import net.timeless.animationapi.client.AnimID;
import net.timeless.unilib.common.animation.ControlledParam;

import org.jurassicraft.JurassiCraft;
import org.jurassicraft.common.entity.ai.animations.AnimationAIEating;
import org.jurassicraft.common.entity.ai.animations.JCNonAutoAnimBase;
import org.jurassicraft.common.entity.ai.animations.JCNonAutoAnimSoundBase;
import org.jurassicraft.common.entity.base.EntityDinosaurAggressive;

import java.util.Random;

public class EntityTyrannosaurus extends EntityDinosaurAggressive // , IEntityAICreature,
// ICarnivore
{
    private static final String[] hurtSounds = new String[] { "tyrannosaurus_hurt_1", "tyrannosaurus_hurt_2" };
    private static final String[] deathSounds = new String[] { "tyrannosaurus_death_1" };
    private static final String[] roarSounds = new String[] { "tyrannosaurus_roar_1" };
    private static final String[] breathSounds = new String[] { "tyrannosaurus_breath_1" };

    private static final Class[] targets = { EntityMob.class, EntityAnimal.class, EntityIndominus.class, EntityCompsognathus.class, EntityAnkylosaurus.class, EntityPlayer.class, EntityDilophosaurus.class, EntityDimorphodon.class, EntityDodo.class, EntityLeaellynasaura.class, EntityLudodactylus.class, EntityHypsilophodon.class, EntityGallimimus.class, EntitySegisaurus.class, EntityProtoceratops.class, EntityParasaurolophus.class, EntityOthnielia.class, EntityMicroceratus.class, EntityTriceratops.class, EntityStegosaurus.class, EntityBrachiosaurus.class, EntityApatosaurus.class, EntityRugops.class, EntityHerrerasaurus.class, EntityVelociraptor.class, EntitySpinosaurus.class, EntityAchillobator.class, EntityCarnotaurus.class, EntityTherizinosaurus.class };

    private int stepCount = 0;

    public ControlledParam roarCount = new ControlledParam(0F, 0F, 0.5F, 0F);
    public ControlledParam roarTiltDegree = new ControlledParam(0F, 0F, 1F, 0F);
	private boolean hasTarget;

    public EntityTyrannosaurus(World world)
    {
        super(world);

        tasks.addTask(2, new JCNonAutoAnimSoundBase(this, 75, AnimID.IDLE, 750, "jurassicraft:" + roarSounds[0], 1.5F));
        tasks.addTask(2, new JCNonAutoAnimBase(this, 75, AnimID.INJURED, 750));
        tasks.addTask(2, new AnimationAIEating(this, 20, true));

        for (int i = 0; i < targets.length; i++)
        {
            this.addAIForAttackTargets(targets[i], new Random().nextInt(3) + 1);
        }
        
        this.defendFromAttacker(EntityLivingBase.class, 0);
    }
    
    public float getNewHealth()
    {
        return 200.0F;
    }
    
    public void updateCreatureData()
    {
        double prevHealth = getMaxHealth();
        double newHealth = getNewHealth();

        getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(newHealth);
        getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(transitionFromAge(dinosaur.getBabySpeed(), dinosaur.getAdultSpeed()));
        getEntityAttribute(SharedMonsterAttributes.knockbackResistance).setBaseValue(transitionFromAge(dinosaur.getBabyKnockback(), dinosaur.getAdultKnockback()));

        // adjustHitbox();

        getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(50.0F);

        if (prevHealth != newHealth)
        {
            heal((float) (newHealth - lastDamage));
        }
    }

    @Override
    public int getTailBoxCount()
    {
        return 6;
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
        super.onUpdate();

        this.roarCount.update();
        this.roarTiltDegree.update();

        if (this.ticksExisted % 62 == 0)
        {
            this.playSound(randomSound(breathSounds), this.getSoundVolume(), this.getSoundPitch());
        }

        /** Step Sound */
        if (this.moveForward > 0 && this.stepCount <= 0)
        {
            this.playSound("jurassicraft:stomp", (float) transitionFromAge(0.1F, 1.0F), this.getSoundPitch());
            stepCount = 65;
        }

        this.stepCount -= this.moveForward * 9.5;
        
        if (this.hasTarget == false && this.getAttackTarget() != null)
        {
        	this.hasTarget = true;
            this.playSound(this.getLivingSound(), (float) transitionFromAge(0.1F, 1.0F), this.getSoundPitch());
            this.roarCount = new ControlledParam(0F, 0F, 1.0F, 0F);
        }
        
        if (this.hasTarget == true && this.getAttackTarget() == null)
        {
        	this.hasTarget = true;
            this.roarCount = new ControlledParam(0F, 0F, 0.5F, 0F);
        }
    }
}
