package net.timeless.jurassicraft.common.entity;

import net.minecraft.entity.Entity;
import net.minecraft.world.World;
import net.timeless.animationapi.AnimationAPI;
import net.timeless.jurassicraft.common.entity.ai.animations.AnimationAICall;
import net.timeless.jurassicraft.common.entity.ai.animations.JCAutoAnimBase;
import net.timeless.jurassicraft.common.entity.ai.animations.JCNonAutoAnimBase;
import net.timeless.jurassicraft.common.entity.base.EntityDinosaurAggressive;
import net.timeless.unilib.common.animation.ChainBuffer;
import net.timeless.unilib.common.animation.ControlledAnimation;

public class EntityVelociraptor extends EntityDinosaurAggressive
{
    public ChainBuffer tailBuffer = new ChainBuffer(6);

    private static final String[] hurtSounds = new String[]{"velociraptor_hurt_1"};
    private static final String[] livingSounds = new String[]{"velociraptor_living_1", "velociraptor_living_2", "velociraptor_living_3"};
    private static final String[] deathSounds = new String[]{"velociraptor_death_1"};
    private static final String[] callSounds = new String[]{"velociraptor_call_1", "velociraptor_call_2", "velociraptor_call_3"};
    private static final String[] barkSounds = new String[]{"velociraptor_bark_1", "velociraptor_bark_2", "velociraptor_bark_3"};
    private static final String[] hissSounds = new String[]{"velociraptor_hiss_1", "velociraptor_hiss_2", "velociraptor_hiss_3"};

    public ControlledAnimation dontLean = new ControlledAnimation(5);
    private int frame = this.ticksExisted;

    public EntityVelociraptor(World world)
    {
        super(world);

        tasks.addTask(2, new JCAutoAnimBase(this, 25, 1)); //Call

        //        tasks.addTask(2, new JCAutoAnimBase(this, 25, 2)); //Attack
        //        tasks.addTask(2, new JCAutoAnimBase(this, 25, 3)); //Die
        //        tasks.addTask(2, new JCAutoAnimBase(this, 6, 4)); //Hurt
        tasks.addTask(2, new JCNonAutoAnimBase(this, 25, 10, 100)); //Head twitch right
        tasks.addTask(2, new JCNonAutoAnimBase(this, 25, 11, 100)); //Head twitch left
        tasks.addTask(2, new JCNonAutoAnimBase(this, 45, 12, 150)); //Sniff
        tasks.addTask(2, new AnimationAICall(this, 78, 30));
    }

    public String getLivingSound()
    {
        if (getAnimID() == 0)
        {
            AnimationAPI.sendAnimPacket(this, 1);
            return randomSound(livingSounds);
        }

        return null;
    }

    public String getHurtSound()
    {
        if (getAnimID() == 0)
        {
            AnimationAPI.sendAnimPacket(this, 1);
            return randomSound(hurtSounds);
        }

        return null;
    }

    public String getDeathSound()
    {
        return randomSound(deathSounds);
    }

    public void onUpdate()
    {
        this.tailBuffer.calculateChainSwingBuffer(68.0F, 5, 4.0F, this);
        super.onUpdate();

//        if(getAnimID() == 0)
//            AnimationAPI.sendAnimPacket(this, 30);

        if (getAttackTarget() != null)
            circleEntity(getAttackTarget(), 7, 0.3f, true, 0);

        if (getAnimID() == 12 || getAnimID() == 1)
            dontLean.decreaseTimer();
        else
            dontLean.increaseTimer();
    }

    public void circleEntity(Entity target, float radius, float speed, boolean direction, float offset)
    {
        EntityVelociraptor[] pack;
        int directionInt = direction ? 1 : -1;
        getNavigator().tryMoveToXYZ(target.posX + radius * Math.cos(directionInt * (frame + offset) * 0.5 * speed / radius), target.posY, target.posZ + radius * Math.sin(directionInt * (frame + offset) * 0.5 * speed / radius), speed);
    }
}
