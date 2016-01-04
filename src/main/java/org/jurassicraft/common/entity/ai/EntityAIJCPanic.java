package org.jurassicraft.common.entity.ai;

import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.ai.RandomPositionGenerator;
import net.minecraft.util.Vec3;
import net.timeless.animationapi.AnimationAPI;
import net.timeless.animationapi.IAnimatedEntity;
import net.timeless.animationapi.client.AnimID;

public class EntityAIJCPanic extends EntityAIBase
{
    private final EntityCreature theEntityCreature;
    private final double speed;
    private double randPosX;
    private double randPosY;
    private double randPosZ;

    public EntityAIJCPanic(EntityCreature creature)
    {
        theEntityCreature = creature;
        speed = 1.25D;
        setMutexBits(1);
    }

    /**
     * Returns whether the EntityAIBase should begin execution.
     */
    @Override
    public boolean shouldExecute()
    {
        if (theEntityCreature.getAITarget() == null && !theEntityCreature.isBurning())
        {
            return false;
        }
        else
        {
            Vec3 vec3 = RandomPositionGenerator.findRandomTarget(theEntityCreature, 5, 4);

            if (vec3 == null)
            {
                return false;
            }
            else
            {
                randPosX = vec3.xCoord;
                randPosY = vec3.yCoord;
                randPosZ = vec3.zCoord;
                return true;
            }
        }
    }

    /**
     * Execute a one shot task or start executing a continuous task
     */
    @Override
    public void startExecuting()
    {
        theEntityCreature.getNavigator().tryMoveToXYZ(randPosX, randPosY, randPosZ, speed);
        if (theEntityCreature instanceof IAnimatedEntity)
        {
            AnimationAPI.sendAnimPacket((IAnimatedEntity) theEntityCreature, AnimID.HISSING);
        }

        // DEBUG
        System.out.println("Starting panic AI for entity " + theEntityCreature.getEntityId());

    }

    /**
     * Returns whether an in-progress EntityAIBase should continue executing
     */
    @Override
    public boolean continueExecuting()
    {
        return !theEntityCreature.getNavigator().noPath();
    }
}