package org.jurassicraft.common.entity.ai.animations;

import net.ilexiconn.llibrary.common.animation.Animation;
import net.ilexiconn.llibrary.common.animation.IAnimated;
import net.minecraft.entity.ai.EntityAIBase;
import net.timeless.animationapi.client.Animations;
import org.jurassicraft.common.entity.base.EntityDinosaur;

public class AnimationAILook extends EntityAIBase
{
    protected EntityDinosaur animatingEntity;

    public AnimationAILook(IAnimated entity)
    {
        super();
        animatingEntity = (EntityDinosaur) entity;
    }

    @Override
    public boolean shouldExecute()
    {
        if (animatingEntity.getRNG().nextDouble() < 0.01)
        {
            return true;
        }

        return false;
    }

    @Override
    public void startExecuting()
    {
        super.startExecuting();
        Animation.sendAnimationPacket(animatingEntity, animatingEntity.getRNG().nextBoolean() ? Animations.LOOKING_LEFT.get() : Animations.LOOKING_RIGHT.get());
        animatingEntity.getNavigator().clearPathEntity();
    }

    @Override
    public void resetTask()
    {
        super.resetTask();
        animatingEntity.currentAnim = null;
    }
}
