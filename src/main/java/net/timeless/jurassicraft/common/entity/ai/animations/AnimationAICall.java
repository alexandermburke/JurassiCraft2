package net.timeless.jurassicraft.common.entity.ai.animations;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.reuxertz.ecoapi.util.EntityHelper;
import net.timeless.animationapi.AIAnimation;
import net.timeless.animationapi.IAnimatedEntity;
import net.timeless.jurassicraft.common.entity.EntityVelociraptor;
import net.timeless.jurassicraft.common.entity.base.EntityDinosaur;

import java.util.List;

public class AnimationAICall extends AIAnimation
{
    protected EntityDinosaur animatingEntity;
    protected int duration;
    protected int id;

    public AnimationAICall(IAnimatedEntity entity, int duration, int id)
    {
        super(entity);
        this.duration = duration;
        animatingEntity = (EntityDinosaur) entity;
        this.id = id;
    }

    @Override
    public int getAnimID()
    {
        return id;
    }

    public boolean isAutomatic()
    {
        return false;
    }

    public int getDuration()
    {
        return duration;
    }

    @Override
    public boolean shouldAnimate()
    {
        List<Entity> entities = EntityHelper.getEntitiesWithinDistance(animatingEntity, 10, 2);
        for(Entity entity : entities)
        {
            if(entity.equals(EntityVelociraptor.class))
                return true;
        }
        return false;
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