package org.jurassicraft.common.entity.ai;

import net.minecraft.block.Block;
import net.minecraft.block.BlockBush;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import net.timeless.animationapi.AnimationAPI;
import net.timeless.animationapi.client.AnimID;
import org.jurassicraft.common.entity.base.EntityDinosaur;

public class EntityAIFindPlant extends EntityAIBase
{
    protected EntityDinosaur dinosaur;

    protected int x, y, z;

    public EntityAIFindPlant(EntityDinosaur dinosaur)
    {
        this.dinosaur = dinosaur;
    }

    @Override
    public boolean shouldExecute()
    {
        double energy = dinosaur.getEnergy();

        if (energy > 0 && dinosaur.ticksExisted % 8 == 0)
        {
            if (dinosaur.getRNG().nextInt((int) energy) < (energy / 4))
            {
                int posX = (int) dinosaur.posX;
                int posY = (int) dinosaur.posY;
                int posZ = (int) dinosaur.posZ;

                int closestDist = Integer.MAX_VALUE;
                int closestX = 0;
                int closestY = 0;
                int closestZ = 0;

                boolean found = false;

                World world = dinosaur.worldObj;

                for (int x = posX - 16; x < posX + 16; x++)
                {
                    for (int y = posY - 16; y < posY + 16; y++)
                    {
                        for (int z = posZ - 16; z < posZ + 16; z++)
                        {
                            Block block = world.getBlockState(new BlockPos(x, y, z)).getBlock();

                            if (block instanceof BlockBush)
                            {
                                int diffX = posX - x;
                                int diffY = posY - y;
                                int diffZ = posZ - z;

                                int dist = (diffX * diffX) + (diffY * diffY) + (diffZ * diffZ);

                                if (dist < closestDist)
                                {
                                    closestDist = dist;
                                    closestX = x;
                                    closestY = y;
                                    closestZ = z;

                                    found = true;
                                }
                            }
                        }
                    }
                }

                if (found)
                {
                    this.x = closestX;
                    this.y = closestY;
                    this.z = closestZ;
                    dinosaur.getNavigator().tryMoveToXYZ(x, y, z, 1.0);

                    return true;
                }
            }
        }

        return false;
    }

    @Override
    public void updateTask()
    {
        if ((dinosaur.getDistanceSq(x, y, z) / 16) <= dinosaur.width)
        {
            AnimationAPI.sendAnimPacket(dinosaur, AnimID.EATING);

            if (dinosaur.worldObj.getGameRules().getGameRuleBooleanValue("mobGriefing"))
            {
                dinosaur.worldObj.destroyBlock(new BlockPos(x, y, z), false);
            }

            dinosaur.setEnergy(dinosaur.getEnergy() + 100);
        }
    }

    /**
     * Returns whether an in-progress EntityAIBase should continue executing
     */
    @Override
    public boolean continueExecuting()
    {
        return !this.dinosaur.getNavigator().noPath();
    }
}
