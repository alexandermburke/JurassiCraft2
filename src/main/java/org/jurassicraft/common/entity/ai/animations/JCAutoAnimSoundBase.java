package org.jurassicraft.common.entity.ai.animations;

import net.ilexiconn.llibrary.common.animation.Animation;
import net.ilexiconn.llibrary.common.animation.IAnimated;
import net.timeless.animationapi.AIAnimation;
import org.jurassicraft.common.entity.base.EntityDinosaur;

public class JCAutoAnimSoundBase extends AIAnimation
{
    protected EntityDinosaur animatingEntity;
    protected int duration;
    protected Animation animation;
    protected String sound;
    protected float volumeOffset;

    public JCAutoAnimSoundBase(IAnimated entity, int duration, Animation animation, String sound, float volumeOffset)
    {
        super(entity);
        this.duration = duration;
        animatingEntity = (EntityDinosaur) entity;
        this.animation = animation;
        this.sound = sound;
        this.volumeOffset = volumeOffset;
    }

    public JCAutoAnimSoundBase(IAnimated entity, int duration, Animation animation, String sound)
    {
        this(entity, duration, animation, sound, 0.0F);
    }

    @Override
    public Animation getAnimation()
    {
        return animation;
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
        animatingEntity.playSound(sound, animatingEntity.getSoundVolume() + volumeOffset, animatingEntity.getSoundPitch());
    }

    @Override
    public void resetTask()
    {
        super.resetTask();
        animatingEntity.currentAnim = null;
    }
}
