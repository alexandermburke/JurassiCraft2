package org.jurassicraft.common.entity.ai.animations;

import net.timeless.animationapi.AIAnimation;
import net.timeless.animationapi.IAnimatedEntity;
import net.timeless.animationapi.client.AnimID;
import org.jurassicraft.common.entity.base.EntityDinosaur;

/**
 * Created by jnad325 on 7/23/15.
 */
public class JCNonAutoAnimSoundBase extends AIAnimation
{
    private final EntityDinosaur animatingEntity;
    private final int duration;
    private final AnimID id;
    private final int chance;
    private final String sound;
    private final float volumeOffset;

    public JCNonAutoAnimSoundBase(IAnimatedEntity entity, int duration, AnimID id, int chance, String sound, float volumeOffset)
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
    public boolean shouldExecute()
    {
        return animatingEntity.getAnimID() == AnimID.IDLE && animatingEntity.getRNG().nextInt(chance) == 0;
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
