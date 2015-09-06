package net.timeless.jurassicraft.common.entity.ai.animations;

import net.minecraft.entity.ai.EntityAIBase;
import net.timeless.animationapi.AnimationAPI;
import net.timeless.jurassicraft.common.entity.base.EntityDinosaur;

public class AnimationAIEating extends EntityAIBase
{
    private EntityDinosaur creature;
    private boolean shouldAnimate;
    private int animationID;
    private int duration;
    private int timer;

    public AnimationAIEating(EntityDinosaur creature, int duration)
    {
        this(creature, duration, false, 0);
    }

    public AnimationAIEating(EntityDinosaur creature, int duration, boolean shouldAnimate, int animationID)
    {
        this.creature = creature;

        if (duration > 0)
            this.duration = duration;
        else
            this.duration = 10;

        this.shouldAnimate = shouldAnimate;
        this.animationID = animationID;
        this.timer = 0;
    }

    public boolean shouldExecute()
    {
        return this.creature.isEating();
    }

    public void startExecuting()
    {
        this.timer = this.duration;


        if (this.shouldAnimate && this.creature.getAnimID() == 0)
            AnimationAPI.sendAnimPacket(this.creature, this.animationID);
    }

    public void updateTask()
    {
        this.timer--;
    }

    public boolean continueExecuting()
    {
        return this.timer >= 0 && this.creature.riddenByEntity == null;
    }

    public void resetTask()
    {
        this.creature.setEating(false);
        this.timer = 0;
    }
}