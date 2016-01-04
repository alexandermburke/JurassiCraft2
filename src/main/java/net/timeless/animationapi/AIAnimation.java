package net.timeless.animationapi;

import net.ilexiconn.llibrary.common.animation.Animation;
import net.ilexiconn.llibrary.common.animation.IAnimated;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.ai.EntityAIBase;
import net.timeless.animationapi.client.Animations;

public abstract class AIAnimation extends EntityAIBase
{
    public AIAnimation(IAnimated entity)
    {
        animatedEntity = entity;
        setMutexBits(7);
    }

    public abstract Animation getAnimation();

    public <T extends EntityLiving> T getEntity()
    {
        return (T) animatedEntity;
    }

    public abstract boolean isAutomatic();

    public abstract int getDuration();

    public boolean shouldAnimate()
    {
        return false;
    }

    @Override
    public boolean shouldExecute()
    {
        if (isAutomatic())
        {
            return animatedEntity.getAnimation() == getAnimation();
        }
        return shouldAnimate();
    }

    @Override
    public void startExecuting()
    {
        if (!isAutomatic())
        {
            Animation.sendAnimationPacket(animatedEntity, getAnimation());
        }
        animatedEntity.setAnimationTick(0);
    }

    @Override
    public boolean continueExecuting()
    {
        return animatedEntity.getAnimationTick() < getDuration();
    }

    @Override
    public void resetTask()
    {
        Animation.sendAnimationPacket(animatedEntity, Animations.IDLE.get());
    }

    private final IAnimated animatedEntity;
}
