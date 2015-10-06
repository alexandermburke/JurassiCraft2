package org.jurassicraft.common.entity.ai;

import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;
import net.timeless.animationapi.AnimationAPI;
import net.timeless.animationapi.client.AnimID;
import org.jurassicraft.common.entity.base.EntityDinosaur;

import java.util.List;

public class EntityAIMate extends EntityAIBase
{
    protected EntityDinosaur dinosaur;
    protected EntityDinosaur mate;

    public EntityAIMate(EntityDinosaur dinosaur)
    {
        this.dinosaur = dinosaur;
    }

    @Override
    public boolean shouldExecute()
    {
//        int minEnergy = 12000;
//
//        if (dinosaur.getEnergy() > minEnergy)
//        {
//            World world = dinosaur.worldObj;
//
//            double posX = dinosaur.posX;
//            double posY = dinosaur.posY;
//            double posZ = dinosaur.posZ;
//
//            List<EntityDinosaur> entities = world.getEntitiesWithinAABB(dinosaur.getClass(), AxisAlignedBB.fromBounds(posX - 16, posY - 16, posZ - 16, posX + 16, posY + 16, posZ + 16));
//
//            for (EntityDinosaur entity : entities)
//            {
//                if (entity.isMale() != dinosaur.isMale() && entity.getEnergy() > minEnergy)
//                {
//                    dinosaur.getNavigator().tryMoveToEntityLiving(entity, 1.0D);
//                    mate = entity;
//
//                    return true;
//                }
//            }
//        }

        return false;
    }

    @Override
    public void updateTask()
    {
        if (dinosaur.getEntityBoundingBox().intersectsWith(mate.getEntityBoundingBox().expand(0.5D, 0.5D, 0.5D)))
        {
            AnimationAPI.sendAnimPacket(dinosaur, AnimID.MATING);

            dinosaur.setEnergy(dinosaur.getEnergy() - 100);
        }
    }

    /**
     * Returns whether an in-progress EntityAIBase should continue executing
     */
    @Override
    public boolean continueExecuting()
    {
        return dinosaur != null && !this.dinosaur.getNavigator().noPath() && mate != null && !mate.isDead;
    }
}
