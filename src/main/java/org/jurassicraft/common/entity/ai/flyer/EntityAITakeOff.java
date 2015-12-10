package org.jurassicraft.common.entity.ai.flyer;

import net.minecraft.entity.ai.EntityAIBase;
import org.jurassicraft.common.entity.base.EntityDinosaurFlyingAggressive;

public class EntityAITakeOff extends EntityAIBase
{
    protected EntityDinosaurFlyingAggressive flyer;

    public EntityAITakeOff(EntityDinosaurFlyingAggressive dinosaur)
    {
        this.flyer = dinosaur;
    }

    @Override
    public boolean shouldExecute()
    {
        return !flyer.isFlying() && flyer.onGround && flyer.getRNG().nextFloat() < 0.01F;
    }

    @Override
    public void updateTask()
    {
        flyer.rotationPitch = 80;
        flyer.setFlying(true);

        if (!flyer.onGround && flyer.posY - flyer.worldObj.getHeight(flyer.getPosition()).getY() > 10)
        {
            flyer.rotationPitch = 0;
        }
    }

    /**
     * Returns whether an in-progress EntityAIBase should continue executing
     */
    @Override
    public boolean continueExecuting()
    {
        return flyer != null && !this.flyer.getNavigator().noPath();
    }
}
