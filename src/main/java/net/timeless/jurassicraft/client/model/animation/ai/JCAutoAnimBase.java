package net.timeless.jurassicraft.client.model.animation.ai;

import net.timeless.animationapi.AIAnimation;
import net.timeless.animationapi.IAnimatedEntity;
import net.timeless.jurassicraft.common.entity.base.EntityDinosaur;

public abstract class JCAutoAnimBase extends AIAnimation
{
    protected EntityDinosaur animatingEntity;
    protected int duration;
    protected int id;

    public JCAutoAnimBase(IAnimatedEntity entity, int duration, int id)
    {
        super(entity);
        this.duration = duration;
        animatingEntity = (EntityDinosaur) entity;
        this.id = id;
    }

    @Override
    public int getAnimID() {
        return id;
    }

    public boolean isAutomatic()
    {
        return true;
    }

    public int getDuration()
    {
        return duration;
    }

    public void startExecuting()
    {
        super.startExecuting();
        animatingEntity.currentAnim = this;
    }

    public void resetTask()
    {
        super.resetTask();
        animatingEntity.currentAnim = null;
    }
}
