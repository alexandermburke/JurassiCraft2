package org.jurassicraft.common.entity.ai;

import net.minecraft.entity.Entity;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.util.AxisAlignedBB;
import org.jurassicraft.common.entity.base.EntityDinosaur;

import java.util.ArrayList;
import java.util.List;

public class EntityAIHerd extends EntityAIBase
{

    EntityDinosaur host;
    double speed = 1.0D;
    private Entity movingTo;
    private int updateRate;

    public EntityAIHerd(EntityDinosaur setHost)
    {
        this.host = setHost;
    }

    public Entity getTarget()
    {
        return getEntityWithMostNeighbours();
    }

    public boolean shouldExecute()
    {
        //System.out.println("CHECKING EXECUTE");
        Entity target = this.getTarget();
        if (target == null)
        {
            //System.out.println("TARGET IS NULL");
            return false;
        }
        if (!target.isEntityAlive())
        {
            // System.out.println("TARGET IS DEAD");
            return false;
        }

        //System.out.println("CHECKING EXECUTE DONE");
        return true;
    }

    public void startExecuting()
    {
        this.updateRate = 0;
    }

    @Override
    public boolean continueExecuting()
    {
        //System.out.println("CHECKING CONTINUE EXECUTE");
        Entity target = this.getTarget();

        if (target == null)
        {
            //System.out.println("TARGET IS NULL");
            return false;
        }

        if (!target.isEntityAlive())
        {
            // System.out.println("TARGET IS DEAD");
            return false;
        }

        //System.out.println("CHECKING CONTINUE EXECUTE DONE");
        return true;
    }

    public void updateTask()
    {
        if (--this.updateRate <= 0)
        {
            //System.out.println("AI STARTING");
            this.updateRate = 10;
            Entity target = this.getTarget();

            if (target.worldObj != null)
            {
                int rand = target.worldObj.rand.nextInt(10);
                int rand2 = target.worldObj.rand.nextInt(10);

                this.host.getNavigator().tryMoveToXYZ(target.posX + rand - rand2, target.posY, target.posZ + rand - rand2, speed);
                // System.out.println("AI STARTING DONE");
            }
        }
    }

    private Entity getEntityWithMostNeighbours()
    {
        List<Entity> nearbyEntities = getEntitiesOfSameTypeWithinDistance(host, 80, 80);

        //System.out.println("AI STARTING MOST NEIGHBOURS");
        Entity entityWithMostNeighbours = null;
        int mostNeighbourCount = Integer.MIN_VALUE;

        for (Entity entity : nearbyEntities)
        {
            if ((host.getDistanceToEntity(entity)) > 15)
            {
                int amountOfNeighbours = getEntitiesOfSameTypeWithinDistance(entity, 20, 20).size();

                if (amountOfNeighbours > mostNeighbourCount)
                {
                    // System.out.println("AI STARTING MOST NEIGHBOURS DONE");
                    mostNeighbourCount = amountOfNeighbours;
                    entityWithMostNeighbours = entity;
                }
            }
        }

        movingTo = entityWithMostNeighbours;

        //if(movingTo != null)
        //System.out.println(movingTo);

        return movingTo;
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

    public void resetTask()
    {
        //System.out.println("AI RESET");
        this.host.getNavigator().clearPathEntity();
    }

}