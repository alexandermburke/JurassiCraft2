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
    protected float volumeOffset;

    public JCAutoAnimSoundBase(IAnimatedEntity entity, int duration, int id, String sound, float volumeOffset)
    {
        super(entity);
        this.duration = duration;
        animatingEntity = (EntityDinosaur) entity;
        this.id = id;
        this.sound = sound;
        this.volumeOffset = volumeOffset;
    }

    public JCAutoAnimSoundBase(IAnimatedEntity entity, int duration, int id, String sound)
    {
        this(entity, duration, id, sound, 0.0F);
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
        animatingEntity.playSound(sound, animatingEntity.getSoundVolume() + volumeOffset, animatingEntity.getSoundPitch());
    }

    public void resetTask()
    {
        super.resetTask();
        animatingEntity.currentAnim = null;
    }
}
