package org.jurassicraft.common.entity.ai.flyer;

import net.minecraft.entity.ai.EntityAIBase;
import org.jurassicraft.common.entity.base.EntityDinosaurFlyingAggressive;

public class EntityAIGlide extends EntityAIBase
{
    protected EntityDinosaurFlyingAggressive flyer;

    private float desiredYaw;

    private boolean landing = false;

    public EntityAIGlide(EntityDinosaurFlyingAggressive dinosaur)
    {
        this.flyer = dinosaur;
    }

    @Override
    public boolean shouldExecute()
    {
        // TODO check when bumping into wall and more complex stuff
        boolean execute = flyer.isFlying() && flyer.posY - flyer.worldObj.getHorizon(flyer.getPosition()).getY() > 10;

        if (execute)
        {
            desiredYaw = flyer.getRNG().nextInt(360) - 180;
        }

        return execute;
    }

    @Override
    public void updateTask()
    {
        if (flyer.getRNG().nextFloat() < 0.1F)
        {
            landing = true;
        }

        if (landing)
        {
            flyer.rotationPitch = -90;

            if (flyer.onGround)
            {
                flyer.rotationPitch = 0;
                flyer.setFlying(false);
            }
        }
        else
        {
            flyer.rotationPitch = 0;

            int rotDiff = (int) (flyer.rotationYaw - desiredYaw);

            if (rotDiff != 0)
            {
                if (rotDiff < 0)
                {
                    flyer.rotationYaw++;
                }
                else
                {
                    flyer.rotationYaw--;
                }
            }
        }
    }

    /**
     * Returns whether an in-progress EntityAIBase should continue executing
     */
    @Override
    public boolean continueExecuting()
    {
        return flyer != null && !this.flyer.getNavigator().noPath() && !this.flyer.onGround;
    }
}
