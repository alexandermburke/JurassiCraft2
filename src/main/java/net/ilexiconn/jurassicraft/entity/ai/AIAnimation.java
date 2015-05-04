package net.ilexiconn.jurassicraft.entity.ai;

import cpw.mods.fml.common.FMLCommonHandler;
import net.ilexiconn.jurassicraft.JurassiCraft;
import net.ilexiconn.jurassicraft.api.IAnimatedEntity;
import net.ilexiconn.jurassicraft.message.MessageAnimation;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.ai.EntityAIBase;

public abstract class AIAnimation extends EntityAIBase
{
    private IAnimatedEntity animatedEntity;

    public AIAnimation(IAnimatedEntity entity)
    {
        animatedEntity = entity;
        setMutexBits(7);
    }

    public abstract int getAnimationId();

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

    public boolean shouldExecute()
    {
        if (isAutomatic()) return animatedEntity.getAnimationId() == getAnimationId();
        return shouldAnimate();
    }

    public void startExecuting()
    {
        MESSAGE:
        {
            if (!isAutomatic())
            {
                if (FMLCommonHandler.instance().getEffectiveSide().isClient()) break MESSAGE;
                animatedEntity.setAnimationId(getAnimationId());
                JurassiCraft.wrapper.sendToAll(new MessageAnimation((byte) getAnimationId(), ((Entity) animatedEntity).getEntityId()));
            }
        }
        animatedEntity.setAnimationTick(0);
    }

    public boolean continueExecuting()
    {
        return animatedEntity.getAnimationTick() < getDuration();
    }

    public void resetTask()
    {
        MESSAGE:
        {
            if (!isAutomatic())
            {
                if (FMLCommonHandler.instance().getEffectiveSide().isClient()) break MESSAGE;
                animatedEntity.setAnimationId(0);
                JurassiCraft.wrapper.sendToAll(new MessageAnimation((byte) 0, ((Entity) animatedEntity).getEntityId()));
            }
        }
    }
}