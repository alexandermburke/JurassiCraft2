package net.timeless.animationapi;

import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.ai.EntityAIBase;
import net.timeless.animationapi.client.AnimID;

public abstract class AIAnimation extends EntityAIBase
{

    public AIAnimation(IAnimatedEntity entity)
    {
        animatedEntity = entity;
        setMutexBits(7);
    }

    public abstract int getAnimID();

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
            return animatedEntity.getAnimID() == getAnimID();
        return shouldAnimate();
    }

    @Override
    public void startExecuting()
    {
        if (!isAutomatic())
            AnimationAPI.sendAnimPacket(animatedEntity, getAnimID());
        animatedEntity.setAnimTick(0);
    }

    @Override
    public boolean continueExecuting()
    {
        return animatedEntity.getAnimTick() < getDuration();
    }

    @Override
    public void resetTask()
    {
        AnimationAPI.sendAnimPacket(animatedEntity, AnimID.IDLE);
    }

    private final IAnimatedEntity animatedEntity;
}
