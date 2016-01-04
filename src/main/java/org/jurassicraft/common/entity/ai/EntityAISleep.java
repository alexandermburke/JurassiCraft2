package org.jurassicraft.common.entity.ai;

import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.RandomPositionGenerator;
import net.minecraft.init.Blocks;
import net.minecraft.pathfinding.PathEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import net.timeless.animationapi.AnimationAPI;
import net.timeless.animationapi.client.AnimID;
import org.jurassicraft.common.entity.base.EntityDinosaur;

public class EntityAISleep extends EntityAIBase
{
    protected EntityDinosaur dinosaur;

    protected BlockPos sleepPos;

    public EntityAISleep(EntityDinosaur dinosaur)
    {
        this.dinosaur = dinosaur;
    }

    @Override
    public boolean shouldExecute()
    {
        World world = dinosaur.worldObj;

        if (!world.isRemote && dinosaur.shouldSleep() && !dinosaur.isSleeping() && dinosaur.shouldGoBackToSleep())
        {
            int range = 16;

            int posX = (int) dinosaur.posX;
            int posZ = (int) dinosaur.posZ;

            for (int x = posX - range; x < posX + range; x++)
            {
                for (int z = posZ - range; z < posZ + range; z++)
                {
                    BlockPos possiblePos = world.getTopSolidOrLiquidBlock(new BlockPos(x, 0, z));

                    if (world.isAirBlock(possiblePos) && world.getBlockState(possiblePos.add(0, -1, 0)).getBlock() != Blocks.water)
                    {
                        if (canFit(possiblePos) && !world.canSeeSky(possiblePos) && dinosaur.setSleepLocation(possiblePos, true))
                        {
                            sleepPos = possiblePos;
                            return true;
                        }
                    }
                }
            }

            dinosaur.setSleepLocation(dinosaur.getPosition(), false); //Sleep right where you are
            sleepPos = dinosaur.getPosition();

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
            int x = sleepPos.getX();
            int y = sleepPos.getY();
            int z = sleepPos.getZ();

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
        return dinosaur != null && !dinosaur.isCarcass() && sleepPos != null && !dinosaur.isSleeping() && dinosaur.shouldSleep();
    }
}
