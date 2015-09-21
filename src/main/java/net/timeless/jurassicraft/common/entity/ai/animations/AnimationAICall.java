package net.timeless.jurassicraft.common.entity.ai.animations;

import net.minecraft.entity.Entity;
import net.minecraft.util.AxisAlignedBB;
import net.timeless.animationapi.AIAnimation;
import net.timeless.animationapi.IAnimatedEntity;
import net.timeless.animationapi.client.AnimID;
import net.timeless.jurassicraft.common.entity.EntityVelociraptor;

import java.util.List;
import java.util.Random;

public class AnimationAICall extends AIAnimation
{
    protected EntityVelociraptor animatingEntity;
    protected int duration;
    protected AnimID id;
    private Random random = new Random();

    public AnimationAICall(IAnimatedEntity entity, int duration, AnimID id)
    {
        super(entity);
        this.duration = duration;
        animatingEntity = (EntityVelociraptor) entity;
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
        return false;
    }

    @Override
    public int getDuration()
    {
        return duration;
    }

    @Override
    public boolean shouldAnimate()
    {
        if (random.nextDouble() < 0.003)
        {
            List<Entity> entities = getEntitiesWithinDistance(animatingEntity, 50, 2);

            for (Entity entity : entities)
            {
                if (entity instanceof EntityVelociraptor)
                {
                    animatingEntity.playSound(animatingEntity.getCallSound(), animatingEntity.getSoundVolume() + 1.25F, animatingEntity.getSoundPitch());
                    return true;
                }
            }
        }

        return false;
    }

    public List<Entity> getEntitiesWithinDistance(Entity e, double xz, double y)
    {
        return e.worldObj.getEntitiesWithinAABBExcludingEntity(e, AxisAlignedBB.fromBounds(e.posX - xz, e.posY - y, e.posZ - xz, e.posX + xz, e.posY + y, e.posZ + xz));
    }

    @Override
    public void startExecuting()
    {
        super.startExecuting();
        animatingEntity.currentAnim = this;
        animatingEntity.getNavigator().clearPathEntity();
    }

    @Override
    public void resetTask()
    {
        super.resetTask();
        animatingEntity.currentAnim = null;
    }
}