package org.jurassicraft.common.entity.ai;

import net.minecraft.block.Block;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.init.Blocks;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import net.timeless.animationapi.AnimationAPI;
import net.timeless.animationapi.client.AnimID;
import org.jurassicraft.common.entity.base.EntityDinosaur;

public class EntityAIDrink extends EntityAIBase
{
    protected EntityDinosaur dinosaur;

    protected int x, y, z;

    public EntityAIDrink(EntityDinosaur dinosaur)
    {
        this.dinosaur = dinosaur;
    }

    @Override
    public boolean shouldExecute()
    {
        double water = dinosaur.getWater();

        if (((int) water) > 0 && dinosaur.ticksExisted % 8 == 0)
        {
            if (dinosaur.getRNG().nextInt((int) water) < (water / 4))
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

                            if (block == Blocks.water)
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
            if (dinosaur.getAnimID() != AnimID.DRINKING)
            {
                AnimationAPI.sendAnimPacket(dinosaur, AnimID.DRINKING);
            }

            dinosaur.setWater(24000);
        }
    }

    /**
     * Returns whether an in-progress EntityAIBase should continue executing
     */
    @Override
    public boolean continueExecuting()
    {
        return dinosaur != null && !this.dinosaur.getNavigator().noPath() && dinosaur.worldObj.getBlockState(new BlockPos(x, y, z)).getBlock() == Blocks.water;
    }
}
