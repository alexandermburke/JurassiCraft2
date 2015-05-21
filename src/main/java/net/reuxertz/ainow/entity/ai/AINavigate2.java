package net.reuxertz.ainow.entity.ai;

import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.ai.RandomPositionGenerator;
import net.minecraft.pathfinding.PathEntity;
import net.minecraft.pathfinding.PathNavigate;
import net.minecraft.util.BlockPos;
import net.minecraft.util.Vec3;

public class AINavigate2 extends AIBase {

    //Fields
    private double _speed;
    private int _wanderDist = 10;
    private boolean _includeY = true;

    //Properties
    public boolean PositionReached(boolean includeY, double dist) {

        double r1 = dist * dist;
        double r2x = this.entity().posX - this.WorkingPosition().getX(), r2z = this.entity().posZ - this.WorkingPosition().getZ();
        //double r2x = entity.posX - this.WorkingPosition.x, r2z = entity.posZ - this.WorkingPosition.z;
        double r2 = r2x * r2x + r2z * r2z;

        if (includeY) {
            double r2y = this.entity().posY -this.WorkingPosition().getY();
            r2 += r2y * r2y;
        }

        return r2 <= r1;
    }

    //Overridden Functions
    @Override
    public boolean continueExecuting() {

        if (super.continueExecuting())
            return false;

        if (this.entity().getNavigator().noPath() || this.PositionReached(this._includeY, this._wanderDist) || this.WorkingPosition() == null) {
            //this.SetFinished(true);
            //return false;
            this.ActivateIdleWander();
        }
        return true;
    }
    @Override
    public void startExecuting()
    {
        if (this.WorkingPosition() == null)
            return;

        PathNavigate n = this.entity().getNavigator();
        PathEntity pe = n.getPathToXYZ(this.WorkingPosition().getX(), this.WorkingPosition().getY(), this.WorkingPosition().getZ());
    }

    //Constructor
    public AINavigate2(EntityCreature entity, double speed)
    {
        super(entity);

        this._speed = speed;
        this.setMutexBits(1);
    }

    //Function
    public void ActivateTravelTo(BlockPos dest)
    {
        this.ActivateTask(dest);
    }
    public void ActivateIdleWander()
    {
        Vec3 vec3 = RandomPositionGenerator.findRandomTarget(this.entity(), this._wanderDist, (int) (this._wanderDist / 2.0));
        vec3 = vec3.addVector(this.entity().posX, this.entity().posY, this.entity().posZ);
        if (vec3 == null)
            return;

        this.ActivateTask(new BlockPos(vec3));

        return;
    }

}
