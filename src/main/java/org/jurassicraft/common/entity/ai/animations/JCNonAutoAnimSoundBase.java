package org.jurassicraft.common.entity.ai.animations;

import net.ilexiconn.llibrary.common.animation.Animation;
import net.ilexiconn.llibrary.common.animation.IAnimated;
import net.timeless.animationapi.AIAnimation;
import net.timeless.animationapi.client.Animations;
import org.jurassicraft.common.entity.base.EntityDinosaur;

/**
 * Created by jnad325 on 7/23/15.
 */
public class JCNonAutoAnimSoundBase extends AIAnimation
{
    protected EntityDinosaur animatingEntity;
    protected int duration;
    protected Animation animation;
    protected int chance;
    protected String sound;
    protected float volumeOffset;

    public JCNonAutoAnimSoundBase(IAnimated entity, int duration, Animation animation, int chance, String sound, float volumeOffset)
    {
        super(entity);
        this.duration = duration;
        animatingEntity = (EntityDinosaur) entity;
        this.animation = animation;
        this.chance = chance;
        this.sound = sound;
        this.volumeOffset = volumeOffset;
    }

    @Override
    public Animation getAnimation()
    {
        return animation;
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
    public boolean shouldExecute()
    {
        return animatingEntity.getAnimation() == Animations.IDLE.get() && animatingEntity.getRNG().nextInt(chance) == 0;
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
