package org.jurassicraft.common.entity.ai;

import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.RandomPositionGenerator;
import net.minecraft.init.Blocks;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.timeless.animationapi.AnimationAPI;
import net.timeless.animationapi.client.AnimID;
import org.jurassicraft.common.entity.base.EntityDinosaur;

public class EntityAISleep extends EntityAIBase
{
    protected EntityDinosaur dinosaur;

    public EntityAISleep(EntityDinosaur dinosaur)
    {
        this.dinosaur = dinosaur;
    }

    @Override
    public boolean shouldExecute()
    {
        if (!dinosaur.worldObj.isRemote && dinosaur.shouldSleep() && !dinosaur.isSleeping() && dinosaur.shouldGoBackToSleep())
        {
            boolean findNew = true;

            BlockPos oldLoc = dinosaur.getSleepLocation();

            if (oldLoc != null)
            {
                findNew = canFit(oldLoc);
            }

            if (findNew)
            {
                int range = 20;

                int posX = (int) dinosaur.posX;
                int posY = (int) dinosaur.posY;
                int posZ = (int) dinosaur.posZ;

                for (int x = posX - range; x < posX + range; x++)
                {
                    for (int y = posY - range; y < posY + range; y++)
                    {
                        for (int z = posZ - range; z < posZ + range; z++)
                        {
                            BlockPos possiblePos = new BlockPos(x, y, z);

                            if (dinosaur.worldObj.getBlockState(possiblePos.add(0, -1, 0)).getBlock() != Blocks.water)
                            {
                                if (canFit(possiblePos) && !dinosaur.worldObj.canSeeSky(possiblePos))
                                {
                                    dinosaur.setSleepLocation(possiblePos);
                                    return true;
                                }
                            }
                        }
                    }
                }

                int tries = 0;

                while (tries < 20)
                {

                    tries++;
                }
            }

            dinosaur.setSleepLocation(dinosaur.getPosition()); //Sleep right where you are

            return true;
        }

        return false;
    }

    private boolean canFit(BlockPos pos)
    {
        double x = pos.getX() + 0.5;
        double y = pos.getY();
        double z = pos.getZ() + 0.5;

        AxisAlignedBB boundingBox = AxisAlignedBB.fromBounds(x, y, z, x + dinosaur.width, y + dinosaur.height, z + dinosaur.width);

        return dinosaur.worldObj.getCollidingBoundingBoxes(dinosaur, boundingBox).isEmpty();
    }

    @Override
    public void updateTask()
    {
        if (dinosaur.shouldGoBackToSleep())
        {
            BlockPos sleepPos = dinosaur.getSleepLocation();

            int x = sleepPos.getX();
            int y = sleepPos.getY();
            int z = sleepPos.getZ();

            dinosaur.getNavigator().tryMoveToXYZ(x, y, z, 1.0);

            if ((dinosaur.getDistanceSq(x, y, z) / 16) <= dinosaur.width)
            {
                dinosaur.setSleeping(true);
            }
        }
    }

    /**
     * Returns whether an in-progress EntityAIBase should continue executing
     */
    @Override
    public boolean continueExecuting()
    {
        return dinosaur != null && !dinosaur.isCarcass() && dinosaur.getSleepLocation() != null && !dinosaur.isSleeping() && dinosaur.shouldSleep();
    }
}
