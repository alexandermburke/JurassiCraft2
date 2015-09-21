package net.timeless.jurassicraft.common.entity.ai.animations;

import net.timeless.animationapi.IAnimatedEntity;
import net.timeless.animationapi.client.AnimID;

public class JCBegAnimation extends JCNonAutoAnimBase
{

    public JCBegAnimation(IAnimatedEntity entity, int duration, AnimID id, int chance)
    {
        super(entity, duration, id, chance);
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
    }

    @Override
    public void resetTask()
    {
        super.resetTask();
        animatingEntity.currentAnim = null;
    }
}
