package org.jurassicraft.common.entity.ai;

import net.minecraft.entity.Entity;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.util.AxisAlignedBB;
import org.jurassicraft.common.entity.base.EntityDinosaur;

import java.util.ArrayList;
import java.util.List;

public class EntityAIHerd extends EntityAIBase
{
    private EntityDinosaur dinosaur;
    private Entity movingTo;

    public EntityAIHerd(EntityDinosaur dinosaur)
    {
        this.dinosaur = dinosaur;
    }

    public List<Entity> getEntitiesWithinDistance(Entity e, double xz, double y) //TODO make a base jc ai class and put this in it
    {
        return e.worldObj.getEntitiesWithinAABBExcludingEntity(e, AxisAlignedBB.fromBounds(e.posX - xz, e.posY - y, e.posZ - xz, e.posX + xz, e.posY + y, e.posZ + xz));
    }

    public List<Entity> getEntitiesOfSameTypeWithinDistance(Entity e, double xz, double y)
    {
        List<Entity> nearbyEntities = getEntitiesWithinDistance(e, xz, y);
        List<Entity> nearbyEntitiesOfType = new ArrayList<Entity>();

        for (Entity entity : nearbyEntities)
        {
            if (entity.getClass().isInstance(e))
            {
                nearbyEntitiesOfType.add(entity);
            }
        }

        return nearbyEntitiesOfType;
    }

    @Override
    public boolean shouldExecute()
    {
        return getEntityWithMostNeighbours() != null;
    }

    @Override
    public void startExecuting()
    {
        dinosaur.getNavigator().tryMoveToEntityLiving(movingTo, 1.0);
    }

    /**
     * Returns whether an in-progress EntityAIBase should continue executing
     */
    @Override
    public boolean continueExecuting()
    {
        return dinosaur != null && !this.dinosaur.getNavigator().noPath() && movingTo != null && !movingTo.isDead && !dinosaur.getEntityBoundingBox().intersectsWith(movingTo.getEntityBoundingBox().expand(0.5D, 0.5D, 0.5D));
    }

    private Entity getEntityWithMostNeighbours()
    {
        List<Entity> nearbyEntities = getEntitiesOfSameTypeWithinDistance(dinosaur, 80, 80);

        Entity entityWithMostNeighbours = null;
        int mostNeighbourCount = Integer.MIN_VALUE;

        for (Entity entity : nearbyEntities)
        {
            if ((dinosaur.getDistanceSqToEntity(entity) / 32) > 20)
            {
                int amountOfNeighbours = getEntitiesOfSameTypeWithinDistance(entity, 20, 20).size();

                if (amountOfNeighbours > mostNeighbourCount)
                {
                    mostNeighbourCount = amountOfNeighbours;
                    entityWithMostNeighbours = entity;
                }
            }
        }

        movingTo = entityWithMostNeighbours;

        return movingTo;
    }
}
