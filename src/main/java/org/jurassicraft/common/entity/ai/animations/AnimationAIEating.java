package org.jurassicraft.common.entity.ai.animations;

import net.minecraft.entity.ai.EntityAIBase;
import net.timeless.animationapi.client.AnimID;
import org.jurassicraft.common.entity.base.EntityDinosaur;

public class AnimationAIEating extends EntityAIBase
{
    private final EntityDinosaur creature;
    private final boolean shouldAnimate;
    private int duration;
    private int timer;

    public AnimationAIEating(EntityDinosaur creature, int duration)
    {
        this(creature, duration, false);
    }

    public AnimationAIEating(EntityDinosaur creature, int duration, boolean shouldAnimate)
    {
        this.creature = creature;

        if (duration > 0)
            this.duration = duration;
        else
            this.duration = 10;

        this.shouldAnimate = shouldAnimate;
        this.timer = 0;
    }

    @Override
    public boolean shouldExecute()
    {
        return this.creature.isEating();
    }

    @Override
    public void startExecuting()
    {
        this.timer = this.duration;

        if (this.shouldAnimate && this.creature.getAnimID() == AnimID.IDLE)
            creature.setAnimID(AnimID.EATING);
    }

    @Override
    public void updateTask()
    {
        this.timer--;
    }

    @Override
    public boolean continueExecuting()
    {
        return this.timer >= 0 && this.creature.riddenByEntity == null;
    }

    @Override
    public void resetTask()
    {
        this.creature.setEating(false);
        this.timer = 0;
    }
}