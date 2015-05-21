package net.reuxertz.ainow.entity.ai;

import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.RandomPositionGenerator;
import net.minecraft.util.Vec3;

public class AINavigate extends AIBase
{
    private EntityCreature entity;
    private double xPosition;
    private double yPosition;
    private double zPosition;
    private double speed;
    private static final String __OBFID = "CL_00001608";

    public AINavigate(EntityCreature e, double speed)
    {
        super(e);

        this.entity = e;
        this.speed = speed;
        this.setMutexBits(1);
    }

    public boolean shouldExecute()
    {
        if (!this.Enabled())
            return false;

        Vec3 vec3 = RandomPositionGenerator.findRandomTarget(this.entity, 10, 7);

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
        return !this.entity.getNavigator().noPath();
    }

    /**
     * Execute a one shot task or start executing a continuous task
     */
    public void startExecuting()
    {
        this.entity.getNavigator().tryMoveToXYZ(this.xPosition, this.yPosition, this.zPosition, this.speed);
    }
}