package org.jurassicraft.common.entity;

import net.minecraft.entity.ai.EntityAIAttackOnCollide;
import net.minecraft.entity.ai.EntityAILeapAtTarget;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAIOpenDoor;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import net.timeless.animationapi.client.AnimID;
import net.timeless.unilib.common.animation.ControlledAnimation;
import org.jurassicraft.common.entity.ai.animations.AnimationAICall;
import org.jurassicraft.common.entity.ai.animations.JCAutoAnimBase;
import org.jurassicraft.common.entity.ai.animations.JCNonAutoAnimBase;
import org.jurassicraft.common.entity.base.EntityDinosaurAggressive;

import java.util.Random;

public class EntityVelociraptor extends EntityDinosaurAggressive // implements ICarnivore, IEntityAICreature
{
    private static final String[] hurtSounds = new String[]{"velociraptor_hurt_1"};
    private static final String[] livingSounds = new String[]{"velociraptor_living_1", "velociraptor_living_2", "velociraptor_living_3"};
    private static final String[] deathSounds = new String[]{"velociraptor_death_1"};
    private static final String[] barkSounds = new String[]{"velociraptor_bark_1", "velociraptor_bark_2", "velociraptor_bark_3"};

    private static final Class[] targets = {EntityCompsognathus.class, EntityPlayer.class, EntityDilophosaurus.class,
            EntityDimorphodon.class, EntityDodo.class, EntityLeaellynasaura.class, EntityHypsilophodon.class,
            EntitySegisaurus.class, EntityProtoceratops.class, EntityOthnielia.class, EntityMicroceratus.class};

    private static final Class[] deftargets = {EntityPlayer.class, EntityTyrannosaurus.class,
            EntityGiganotosaurus.class, EntitySpinosaurus.class};

    public ControlledAnimation dontLean = new ControlledAnimation(5);

    public EntityVelociraptor(World world)
    {
        super(world);

        tasks.addTask(2, new JCAutoAnimBase(this, 25, AnimID.IDLE)); // Call
        this.tasks.addTask(4, new EntityAIOpenDoor(this, true));

        // tasks.addTask(2, new JCAutoAnimBase(this, 25, 2)); //Attack
        // tasks.addTask(2, new JCAutoAnimBase(this, 25, 3)); //Die
        // tasks.addTask(2, new JCAutoAnimBase(this, 6, 4)); //Hurt
        tasks.addTask(2, new JCNonAutoAnimBase(this, 25, AnimID.LOOKING_RIGHT, 100)); // Head twitch right
        tasks.addTask(2, new JCNonAutoAnimBase(this, 25, AnimID.LOOKING_LEFT, 100)); // Head twitch left
        tasks.addTask(2, new JCNonAutoAnimBase(this, 45, AnimID.SNIFFING, 150)); // Sniff
        tasks.addTask(2, new AnimationAICall(this, 78, AnimID.CALLING));

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
        return 6;
    }

    // NOTE: This adds an attack target. Class should be the entity class for the target, lower prio get executed
    // earlier
    @Override
    protected void addAIForAttackTargets(Class entity, int prio)
    {
        this.tasks.addTask(0, new EntityAIAttackOnCollide(this, entity, 1.0D, false));
        this.tasks.addTask(1, new EntityAILeapAtTarget(this, 0.5F));
        this.targetTasks.addTask(0, new EntityAINearestAttackableTarget(this, entity, false));
    }

    public String getCallSound()
    {
        return randomSound(barkSounds);
    }

    @Override
    public String getLivingSound()
    {
        if (getAnimID() == AnimID.IDLE)
        {
            return randomSound(livingSounds);
        }

        return null;
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

//        if (getAttackTarget() != null)
//            circleEntity(getAttackTarget(), 7, 1.0f, true, 0);

        if (getAnimID() == AnimID.RESTING || getAnimID() == AnimID.ATTACKING)
        {
            dontLean.decreaseTimer();
        }
        else
        {
            dontLean.increaseTimer();
        }
    }

//    public void circleEntity(Entity target, float radius, float speed, boolean direction, float offset)
//    {
//        EntityVelociraptor[] pack;
//        int directionInt = direction ? 1 : -1;
//
//        if (getDistanceSqToEntity(target) > radius - 1)
//        {
//            getNavigator().tryMoveToXYZ(target.posX + radius * Math.cos(directionInt * (ticksExisted + offset) * 0.5 * speed / radius), target.posY, target.posZ + radius * Math.sin(directionInt * (ticksExisted + offset) * 0.5 * speed / radius), speed);
//        }
//    }
}
