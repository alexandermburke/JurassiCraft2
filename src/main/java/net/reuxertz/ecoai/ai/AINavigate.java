package net.reuxertz.ecoai.ai;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.ai.RandomPositionGenerator;
import net.minecraft.pathfinding.PathEntity;
import net.minecraft.util.BlockPos;
import net.reuxertz.ecoapi.entity.IEntityAISwimmingCreature;

public class AINavigate extends AIBase
{
    public enum NavState
    {
        Null, IdleWander, SearchWander
    }

    protected double speed, wanderDist = 15, wanderPower = 1.71, destinationBlockBuffer = 2.0, destinationEntityBuffer = 1.5;
    protected boolean finishOnArrival = false;
    protected Entity seekEntity = null;
    protected float sinkFactor = 1.0f;
    protected NavState navState = NavState.Null;

    public boolean positionReached(boolean includeY)
    {
        return workPositionReached(includeY, destinationBlockBuffer) || this.seekEntityReached(includeY, destinationEntityBuffer);
    }

    public boolean workPositionReached(boolean includeY)
    {
        return this.workPositionReached(includeY, destinationBlockBuffer);
    }

    public boolean workPositionReached(boolean includeY, double dist)
    {
        if (this.getWorkingPosition() == null)
            return false;

        //if (this.getWorkingPosition == null)
        //	return false;

        double r1 = dist * dist;
        double r2x = entity.posX - this.getWorkingPosition().getX(), r2z = entity.posZ - this.getWorkingPosition().getZ();
        //double r2x = agentAI.posX - this.getWorkingPosition.x, r2z = agentAI.posZ - this.getWorkingPosition.z;
        double r2 = r2x * r2x + r2z * r2z;

        if (includeY)
        {
            double r2y = entity.posY - this.getWorkingPosition().getY();
            //double r2y = agentAI.posY - this.getWorkingPosition.y;
            r2 += r2y * r2y;
        }

        return r2 <= r1;
    }

    public boolean seekEntityReached(boolean includeY, double dist)
    {
        if (this.seekEntity == null)
            return false;

        if (this.seekEntity.isDead)
        {
            this.seekEntity = null;
            return true;
        }

        //if (this.getWorkingPosition == null)
        //	return false;

        double r1 = dist * dist;
        double r2x = entity.posX - this.seekEntity.posX, r2z = entity.posZ - this.seekEntity.posX;
        //double r2x = agentAI.posX - this.getWorkingPosition.x, r2z = agentAI.posZ - this.getWorkingPosition.z;
        double r2 = r2x * r2x + r2z * r2z;

        if (includeY)
        {
            double r2y = entity.posY - this.seekEntity.posY;
            //double r2y = agentAI.posY - this.getWorkingPosition.y;
            r2 += r2y * r2y;
        }

        return r2 <= r1;
    }

    public boolean seekEntityReached(boolean includeY)
    {
        return this.seekEntityReached(includeY, destinationEntityBuffer);
    }

    public double distance(BlockPos b1, BlockPos b2)
    {
        double x = b1.getX() - b2.getX();
        double y = b1.getX() - b2.getX();
        double z = b1.getX() - b2.getX();
        x = x * x;
        y = y * y;
        z = z * z;

        return Math.sqrt(x + y + z);
    }

    //Constructor
    public AINavigate(EntityCreature e, double speed)
    {
        super(e);

        //float ei = e.stepHeight;
        //e.stepHeight = 1.0f * (float)(e.getBoundingBox().maxY - e.getBoundingBox().minY);
        //..e.stepHeight = 1.2f;
        this.speed = speed;
        //this.setMutexBits(1);
    }

    //Functions
    public void updateTask()
    {

        if (IEntityAISwimmingCreature.class.isInstance(this.entity()))
        {
            int getNextY = 0;
            PathEntity pe = this.entity.getNavigator().getPath();

            if (pe == null)
                return;

            int j = 0, dy = 0;
            for (int i = pe.getCurrentPathIndex(); i < pe.getCurrentPathLength(); i++)
            {
                if (pe.getPathPointFromIndex(i).yCoord > this.entity.posY + dy)
                    dy++;
                if (pe.getPathPointFromIndex(i).yCoord < this.entity.posY + dy)
                    dy--;


                if (j > 5)
                    break;
                else
                    j++;
            }

            if (dy > 0)
            {
                this.entity.jumpMovementFactor = 1.0f;
                this.entity.getJumpHelper().setJumping();

                //if (this.entity.motionY <= 0)
                this.entity.posY += 1.1;
                this.entity.motionY += 1.1;
                //else
                //    this.entity.motionY *= 1.1;
            }
        }
    }

    public boolean shouldExecute()
    {
        boolean b = super.shouldExecute();

        if (!b)
            return false;

        if(entity.getLeashed())
            return false;

        if (this.seekEntity != null)
        {
            this.setWorkingPosition(null);
            return true;
        }
        if (this.getWorkingPosition() == null)
        {
            if (this.isRecursive && AICore.RND.nextDouble() > this.probRecursion)
                return false;

            BlockPos bp;
            bp = new BlockPos(RandomPositionGenerator.findRandomTarget(this.entity(), (int) this.wanderDist, 7));

            this.setWorkingPosition(new BlockPos(bp));
            return true;
        }

        return true;
    }

    public boolean continueExecuting()
    {
        if (!super.continueExecuting())
            return false;

        boolean noPath = this.entity.getNavigator().noPath();
        boolean cont = true;
        boolean posReached = this.positionReached(true);

        //if (noPath)
        //    this.agentAI.getNavigator().tryMoveToXYZ(this.getWorkingPosition().getX(), this.getWorkingPosition().getY(), this.getWorkingPosition().getZ(), this.speed);

        if (posReached || noPath)//(this.workPositionReached(true) &&  noPath) || this.seekEntityReached(true))
        {
            this.deactivateTask(false);
            cont = false;
        }
        return cont;
    }

    public void startExecuting()
    {
        if (this.seekEntity != null)
            this.entity().getNavigator().tryMoveToEntityLiving(this.seekEntity, this.speed);
        else if (this.getWorkingPosition() != null)
            this.entity.getNavigator().tryMoveToXYZ(this.getWorkingPosition().getX(), this.getWorkingPosition().getY(), this.getWorkingPosition().getZ(), this.speed);
        //this.agentAI.getNavigator().tryMoveToXYZ(navPos.getX(), navPos.getY(), navPos.getZ(), this.speed);

    }

    //Activate Functions
    public void activateSeekPosTask(BlockPos workingPosition)
    {
        super.activateTask(workingPosition);
    }

    public void activateSeekEntityTask(Entity e)
    {
        super.activateTask(null);
        this.seekEntity = e;
        //this.probRecursion = probNewWander;
        //this.isRecursive = true;
        //this.navPos = null;
    }

    public void deactivateTask(boolean removeRecursive)
    {
        super.deactivateTask(removeRecursive);

        this.seekEntity = null;
        this.navState = NavState.Null;
    }


    public void activateWander()
    {
        this.activateTask(null);
    }

    public void activateIdleWander(double probNewWander)
    {
        this.activateTask(null);
        this.probRecursion = probNewWander;
        this.isRecursive = true;
        //this.navPos = null;
    }
}