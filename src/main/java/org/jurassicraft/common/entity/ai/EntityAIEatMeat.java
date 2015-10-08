package org.jurassicraft.common.entity.ai;

import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;
import net.timeless.animationapi.AnimationAPI;
import net.timeless.animationapi.client.AnimID;
import org.jurassicraft.common.entity.base.EntityDinosaur;
import org.jurassicraft.common.item.JCItemRegistry;

import java.util.List;

public class EntityAIEatMeat extends EntityAIBase
{
    protected EntityDinosaur dinosaur;

    protected EntityItem item;

    public EntityAIEatMeat(EntityDinosaur dinosaur)
    {
        this.dinosaur = dinosaur;
    }

    @Override
    public boolean shouldExecute()
    {
        double energy = dinosaur.getEnergy();

        if (!dinosaur.isDead && dinosaur.ticksExisted % 4 == 0 && dinosaur.worldObj.getGameRules().getGameRuleBooleanValue("dinoMetabolism"))
        {
            if (energy < 12000 + (dinosaur.getRNG().nextInt(50) - 25))
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

                        if (item == JCItemRegistry.dino_meat || item == JCItemRegistry.dino_steak || item == Items.porkchop || item == Items.cooked_porkchop || item == Items.beef || item == Items.cooked_beef || item == Items.chicken || item == Items.cooked_chicken || item == Items.fish || item == Items.cooked_fish || item == Items.rabbit || item == Items.cooked_rabbit || item == Items.mutton || item == Items.cooked_mutton)
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

            if (dinosaur.worldObj.getGameRules().getGameRuleBooleanValue("mobGriefing"))
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

            dinosaur.setEnergy(dinosaur.getEnergy() + 600);
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
