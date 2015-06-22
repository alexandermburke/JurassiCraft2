package net.ilexiconn.jurassicraft.entity.ai;

import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.ai.RandomPositionGenerator;
import net.minecraft.util.Vec3;

public class EntityAIJCWander extends EntityAIBase
{
    private EntityCreature entity;
    private double xPosition;
    private double yPosition;
    private double zPosition;
    private double speed;
    private int probability;
    private boolean field_179482_g;
    private int distance;
    
    public EntityAIJCWander(EntityCreature entity, double speed, int distance)
    {
        this(entity, speed, 120, distance);
    }

    public EntityAIJCWander(EntityCreature entity, double speed, int probability, int distance)
    {
        this.entity = entity;
        this.speed = speed;
        this.probability = probability;
        this.setMutexBits(1);
        this.distance = distance;
    }

    /**
     * Returns whether the EntityAIBase should begin execution.
     */
    public boolean shouldExecute()
    {
        if (!this.field_179482_g)
        {
            if (this.entity.getAge() >= 100)
            {
                return false;
            }

            if (this.entity.getRNG().nextInt(this.probability) != 0)
            {
                return false;
            }
        }

        Vec3 vec3 = RandomPositionGenerator.findRandomTarget(this.entity, distance, 7);

        if (vec3 == null)
        {
            return false;
        }
        else
        {
            this.xPosition = vec3.xCoord;
            this.yPosition = vec3.yCoord;
            this.zPosition = vec3.zCoord;
            this.field_179482_g = false;
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

    public void func_179480_f()
    {
        this.field_179482_g = true;
    }

    public void setProbability(int probability)
    {
        this.probability = probability;
    }
}