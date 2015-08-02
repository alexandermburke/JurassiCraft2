package net.timeless.jurassicraft.common.entity.ai.animations;

import net.timeless.animationapi.AIAnimation;
import net.timeless.animationapi.IAnimatedEntity;
import net.timeless.jurassicraft.common.entity.base.EntityDinosaur;

public class JCAutoAnimSoundBase extends AIAnimation
{
    protected EntityDinosaur animatingEntity;
    protected int duration;
    protected int id;
    protected String sound;

    public JCAutoAnimSoundBase(IAnimatedEntity entity, int duration, int id, String sound)
    {
        super(entity);
        this.duration = duration;
        animatingEntity = (EntityDinosaur) entity;
        this.id = id;
        this.sound = sound;
    }

    @Override
    public int getAnimID()
    {
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
        animatingEntity.playSound(sound, animatingEntity.getSoundVolume(), animatingEntity.getSoundPitch());
    }

    public void resetTask()
    {
        super.resetTask();
        animatingEntity.currentAnim = null;
    }
}
