package net.reuxertz.ainow.entity.ai;

import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.ai.RandomPositionGenerator;
import net.minecraft.util.BlockPos;

import java.util.Random;

public class AINavigate extends AIBase
{
    private Random random = new Random();
    //private BlockPos navPos;
    protected double _speed, _wanderDist = 15, _wanderPower = 1.71, _destinationBuffer = 2.0;
    private static final String __OBFID = "CL_00001608";

    public boolean PositionReached(boolean includeY, double dist)
    {
        //if (this.WorkingPosition == null)
        //	return false;

        double r1 = dist * dist;
        double r2x = _entity.posX - this.WorkingPosition().getX(), r2z = _entity.posZ - this.WorkingPosition().getZ();
        //double r2x = entity.posX - this.WorkingPosition.x, r2z = entity.posZ - this.WorkingPosition.z;
        double r2 = r2x * r2x + r2z * r2z;

        if (includeY)
        {
            double r2y = _entity.posY - this.WorkingPosition().getY();
            //double r2y = entity.posY - this.WorkingPosition.y;
            r2 += r2y * r2y;
        }

        return r2 <= r1;
    }

    public double Distance(BlockPos b1, BlockPos b2)
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

        this._speed = speed;
        this.setMutexBits(1);
    }

    @Override
    public boolean shouldExecute()
    {
        if (!super.shouldExecute())
            return false;

        if (this.WorkingPosition() == null)
        {

            if (this._isRecursive && random.nextDouble() > this._probRecursion)
                return false;

            BlockPos bp;
            bp = new BlockPos(RandomPositionGenerator.findRandomTarget(this.entity(), (int) this._wanderDist, 7));

            if (bp == null)
                return false;

            this.SetWorkingPosition(new BlockPos(bp));
            return true;
        }

        return this.WorkingPosition() != null;
    }

    @Override
    public boolean continueExecuting()
    {
        if (!super.continueExecuting())
            return false;

        boolean noPath = this._entity.getNavigator().noPath();
        boolean cont = true;
        boolean posReached = this.PositionReached(true, this._destinationBuffer);

        if (noPath || posReached)
        {
            this.DeactivateTask(false);
            cont = false;
        }
        return cont;
    }

    @Override
    public void startExecuting()
    {
        if (this.WorkingPosition() != null)
            this._entity.getNavigator().tryMoveToXYZ(this.WorkingPosition().getX(), this.WorkingPosition().getY(), this.WorkingPosition().getZ(), this._speed);
        //this.entity.getNavigator().tryMoveToXYZ(navPos.getX(), navPos.getY(), navPos.getZ(), this._speed);
    }

    @Override
    protected void ActivateTask(BlockPos workingPosition)
    {
        super.ActivateTask(workingPosition);
        //this.navPos = workingPosition;
    }

    protected void ActivateWander()
    {
        this.ActivateTask(null);
        //this.navPos = null;
    }

    protected void ActivateIdleWander(double probNewWander)
    {
        this.ActivateTask(null);
        this._probRecursion = probNewWander;
        this._isRecursive = true;
        //this.navPos = null;
    }

    protected void ActivateSearch(double probNewWander)
    {
        this.ActivateTask(null);
        this._probRecursion = probNewWander;
        this._isRecursive = true;
        //this.navPos = null;
    }
}