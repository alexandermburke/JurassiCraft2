package org.jurassicraft.common.entity.ai;

import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.ai.RandomPositionGenerator;
import net.minecraft.util.Vec3;
import org.jurassicraft.common.entity.base.EntityDinosaur;

public class EntityAIMoveUnderwater extends EntityAIBase
{
    private EntityDinosaur swimmingEntity;
    private double xPosition;
    private double yPosition;
    private double zPosition;

    public EntityAIMoveUnderwater(EntityDinosaur entity)
    {
        this.swimmingEntity = entity;
        this.setMutexBits(3);
    }

    /**
     * Returns whether the EntityAIBase should begin execution.
     */
    public boolean shouldExecute()
    {
        if (this.swimmingEntity.getRNG().nextFloat() < 0.50)
        {
            return false;
        }

        Vec3 vec3 = RandomPositionGenerator.findRandomTarget(this.swimmingEntity, 6, 2);

        if (vec3 == null)
        {
            return false;
        }
        else
        {
            this.xPosition = vec3.xCoord;
            this.yPosition = vec3.yCoord;
            this.zPosition = vec3.zCoord;
            return true;
        }
    }

    /**
     * Returns whether an in-progress EntityAIBase should continue executing
     */
    public boolean continueExecuting()
    {
        return !this.swimmingEntity.getNavigator().noPath();
    }

    /**
     * Execute a one shot task or start executing a continuous task
     */
    public void startExecuting()
    {
        this.swimmingEntity.getNavigator().tryMoveToXYZ(this.xPosition, this.yPosition, this.zPosition, 1.0D);
    }
}
