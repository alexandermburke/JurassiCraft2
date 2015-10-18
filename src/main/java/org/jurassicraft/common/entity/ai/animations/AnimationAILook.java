package org.jurassicraft.common.entity.ai.animations;

import net.minecraft.entity.ai.EntityAIBase;
import net.timeless.animationapi.AnimationAPI;
import net.timeless.animationapi.IAnimatedEntity;
import net.timeless.animationapi.client.AnimID;
import org.jurassicraft.common.entity.base.EntityDinosaur;

public class AnimationAILook extends EntityAIBase
{
    protected EntityDinosaur animatingEntity;

    public AnimationAILook(IAnimatedEntity entity)
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
        AnimationAPI.sendAnimPacket(animatingEntity, animatingEntity.getRNG().nextBoolean() ? AnimID.LOOKING_LEFT : AnimID.LOOKING_RIGHT);
        animatingEntity.getNavigator().clearPathEntity();
    }

    @Override
    public void resetTask()
    {
        super.resetTask();
        animatingEntity.currentAnim = null;
    }
}