package org.jurassicraft.common.entity.ai.flyer;

import net.minecraft.entity.ai.EntityAIBase;
import org.jurassicraft.common.entity.base.EntityDinosaurFlyingAggressive;

public class EntityAILand extends EntityAIBase
{
    protected EntityDinosaurFlyingAggressive flyer;

    public EntityAILand(EntityDinosaurFlyingAggressive dinosaur)
    {
        this.flyer = dinosaur;
    }

    @Override
    public boolean shouldExecute()
    {
        return flyer.isFlying() && flyer.posY - flyer.worldObj.getHeight(flyer.getPosition()).getY() > 10 && flyer.getRNG().nextFloat() < 0.1F;
    }

    @Override
    public void updateTask()
    {
        flyer.rotationPitch = -90;

        if (flyer.onGround)
        {
            flyer.rotationPitch = 0;
            flyer.setFlying(false);
        }
    }

    /**
     * Returns whether an in-progress EntityAIBase should continue executing
     */
    @Override
    public boolean continueExecuting()
    {
        return flyer != null && !this.flyer.getNavigator().noPath() && this.flyer.isFlying();
    }
}
