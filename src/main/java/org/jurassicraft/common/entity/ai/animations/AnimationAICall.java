package org.jurassicraft.common.entity.ai.animations;

import net.minecraft.entity.Entity;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.util.AxisAlignedBB;
import net.timeless.animationapi.AnimationAPI;
import net.timeless.animationapi.IAnimatedEntity;
import net.timeless.animationapi.client.AnimID;
import org.jurassicraft.common.entity.base.EntityDinosaur;

import java.util.List;

public class AnimationAICall extends EntityAIBase
{
    private final EntityDinosaur animatingEntity;

    public AnimationAICall(IAnimatedEntity entity)
    {
        super();
        animatingEntity = (EntityDinosaur) entity;
    }

    private List<Entity> getEntitiesWithinDistance(Entity e, double xz)
    {
        return e.worldObj.getEntitiesWithinAABBExcludingEntity(e, AxisAlignedBB.fromBounds(e.posX - (double) 50, e.posY - (double) 10, e.posZ - (double) 50, e.posX + (double) 50, e.posY + (double) 10, e.posZ + (double) 50));
    }

    @Override
    public boolean shouldExecute()
    {
        if (animatingEntity.getRNG().nextDouble() < 0.003)
        {
            List<Entity> entities = getEntitiesWithinDistance(animatingEntity, 50);

            for (Entity entity : entities)
            {
                if (animatingEntity.getClass().isInstance(entity))
                {
                    animatingEntity.playSound(animatingEntity.getCallSound(), animatingEntity.getSoundVolume() + 1.25F, animatingEntity.getSoundPitch());
                    return true;
                }
            }
        }

        return false;
    }

    @Override
    public void startExecuting()
    {
        super.startExecuting();
        AnimationAPI.sendAnimPacket(animatingEntity, AnimID.CALLING);
        animatingEntity.getNavigator().clearPathEntity();
    }

    @Override
    public void resetTask()
    {
        super.resetTask();
        animatingEntity.currentAnim = null;
    }
}
