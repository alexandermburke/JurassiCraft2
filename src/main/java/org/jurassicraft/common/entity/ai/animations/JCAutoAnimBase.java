package org.jurassicraft.common.entity.ai.animations;

import net.timeless.animationapi.AIAnimation;
import net.timeless.animationapi.IAnimatedEntity;
import net.timeless.animationapi.client.AnimID;
import org.jurassicraft.common.entity.base.EntityDinosaur;

public class JCAutoAnimBase extends AIAnimation
{
    private final EntityDinosaur animatingEntity;
    private final int duration;
    private final AnimID id;

    public JCAutoAnimBase(IAnimatedEntity entity, int duration, AnimID id)
    {
        super(entity);
        this.duration = duration;
        animatingEntity = (EntityDinosaur) entity;
        this.id = id;
    }

    @Override
    public AnimID getAnimID()
    {
        return id;
    }

    @Override
    public boolean isAutomatic()
    {
        return true;
    }

    @Override
    public int getDuration()
    {
        return duration;
    }

    @Override
    public void startExecuting()
    {
        super.startExecuting();
        animatingEntity.currentAnim = this;
    }

    @Override
    public void resetTask()
    {
        super.resetTask();
        animatingEntity.currentAnim = null;
    }
}
