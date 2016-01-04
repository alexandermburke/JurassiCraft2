package org.jurassicraft.common.entity.ai.animations;

import net.timeless.animationapi.AIAnimation;
import net.timeless.animationapi.IAnimatedEntity;
import net.timeless.animationapi.client.AnimID;
import org.jurassicraft.common.entity.base.EntityDinosaur;

class JCAutoAnimSoundBase extends AIAnimation
{
    private final EntityDinosaur animatingEntity;
    private final int duration;
    private final AnimID id;
    private final String sound;
    private final float volumeOffset;

    private JCAutoAnimSoundBase(IAnimatedEntity entity, int duration, AnimID id, String sound)
    {
        super(entity);
        this.duration = duration;
        animatingEntity = (EntityDinosaur) entity;
        this.id = id;
        this.sound = sound;
        this.volumeOffset = 0.0F;
    }

    public JCAutoAnimSoundBase(IAnimatedEntity entity, int duration, AnimID id, String sound)
    {
        this(entity, duration, id, sound);
    }

    @Override
    public AnimID getAnimID()
    {
        return id;
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
