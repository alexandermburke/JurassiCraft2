package org.jurassicraft.common.entity.ai.metabolism;

import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;
import net.timeless.animationapi.AnimationAPI;
import net.timeless.animationapi.client.AnimID;
import org.jurassicraft.common.entity.base.EntityDinosaur;
import org.jurassicraft.common.entity.base.MetabolismContainer;
import org.jurassicraft.common.food.FoodHelper;

import java.util.List;

public class EntityAIEatFoodItem extends EntityAIBase
{
    protected EntityDinosaur dinosaur;

    protected EntityItem item;

    public EntityAIEatFoodItem(EntityDinosaur dinosaur)
    {
        this.dinosaur = dinosaur;
    }

    @Override
    public boolean shouldExecute()
    {
        MetabolismContainer metabolism = dinosaur.getMetabolism();

        if (!dinosaur.isDead && !dinosaur.isCarcass() && dinosaur.ticksExisted % 4 == 0 && dinosaur.worldObj.getGameRules().getBoolean("dinoMetabolism"))
        {
            double food = metabolism.getFood();

            boolean execute = false;

            int maxFood = metabolism.getMaxFood();

            if (food / maxFood * 100 < 25)
            {
                execute = true;
            }
            else
            {
                if (food < maxFood - (maxFood / 8) && dinosaur.getDinosaur().getSleepingSchedule().isWithinEatingTime(dinosaur.getDinosaurTime(), dinosaur.getRNG()))
                {
                    execute = true;
                }
            }

            if (execute)
            {
                double posX = dinosaur.posX;
                double posY = dinosaur.posY;
                double posZ = dinosaur.posZ;

                double closestDist = Integer.MAX_VALUE;
                EntityItem closest = null;

                boolean found = false;

                World world = dinosaur.worldObj;

                List<EntityItem> items = world.getEntitiesWithinAABB(EntityItem.class, AxisAlignedBB.fromBounds(posX - 16, posY - 16, posZ - 16, posX + 16, posY + 16, posZ + 16));

                for (EntityItem e : items)
                {
                    ItemStack stack = e.getEntityItem();

                    if (stack != null)
                    {
                        Item item = stack.getItem();

                        if (FoodHelper.canDietEat(dinosaur.getDinosaur().getDiet(), item))
                        {
                            double diffX = posX - e.posX;
                            double diffY = posY - e.posY;
                            double diffZ = posZ - e.posZ;

                            double dist = (diffX * diffX) + (diffY * diffY) + (diffZ * diffZ);

                            if (dist < closestDist)
                            {
                                closestDist = dist;
                                closest = e;

                                found = true;
                            }
                        }
                    }
                }

                if (found)
                {
                    dinosaur.getNavigator().tryMoveToEntityLiving(closest, 1.0);
                    this.item = closest;

                    return true;
                }
            }
        }

        return false;
    }

    @Override
    public void updateTask()
    {
        if (dinosaur.getEntityBoundingBox().intersectsWith(item.getEntityBoundingBox().expand(0.5D, 0.5D, 0.5D)))
        {
            AnimationAPI.sendAnimPacket(dinosaur, AnimID.EATING);

            if (dinosaur.worldObj.getGameRules().getBoolean("mobGriefing"))
            {
                if (item.getEntityItem().stackSize > 1)
                {
                    item.getEntityItem().stackSize--;
                }
                else
                {
                    item.setDead();
                }
            }

            dinosaur.getMetabolism().increaseFood(2000);
            dinosaur.heal(4.0F);
        }
    }

    /**
     * Returns whether an in-progress EntityAIBase should continue executing
     */
    @Override
    public boolean continueExecuting()
    {
        return dinosaur != null && !this.dinosaur.getNavigator().noPath() && item != null && !item.isDead;
    }
}
