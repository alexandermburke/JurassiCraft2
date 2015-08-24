package net.timeless.jurassicraft.common.entity.ai.animations;

import net.timeless.animationapi.AIAnimation;
import net.timeless.animationapi.IAnimatedEntity;
import net.timeless.jurassicraft.common.entity.base.EntityDinosaur;

/**
 * Created by jnad325 on 7/23/15.
 */
public class JCNonAutoAnimSoundBase extends AIAnimation
{
    protected EntityDinosaur animatingEntity;
    protected int duration;
    protected int id;
    protected int chance;
    protected String sound;
    protected float volumeOffset;

    public JCNonAutoAnimSoundBase(IAnimatedEntity entity, int duration, int id, int chance, String sound, float volumeOffset)
    {
        super(entity);
        this.duration = duration;
        animatingEntity = (EntityDinosaur) entity;
        this.id = id;
        this.chance = chance;
        this.sound = sound;
        this.volumeOffset = volumeOffset;
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
    public boolean shouldExecute()
    {
        return animatingEntity.getAnimID() == 0 && animatingEntity.getRNG().nextInt(chance) == 0;
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
