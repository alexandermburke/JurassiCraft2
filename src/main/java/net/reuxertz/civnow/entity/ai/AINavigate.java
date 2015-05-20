package net.reuxertz.civnow.entity.ai;

import net.minecraft.entity.EntityLiving;
import net.minecraft.pathfinding.PathEntity;
import net.minecraft.pathfinding.PathNavigate;

public class AINavigate extends AIBase {

    //Fields
    private double _speed;

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

        if (this.entity().getNavigator().noPath() || this.WorkingPosition() == null) {
            this.SetFinished(true);
            return false;
        }
        return true;
    }
    @Override
    public void startExecuting()
    {
        PathNavigate n = this.entity().getNavigator();
        PathEntity pe = n.getPathToXYZ(this.WorkingPosition().getX(), this.WorkingPosition().getY(), this.WorkingPosition().getZ());
    }

    //Constructor
    public AINavigate(EntityLiving entity, double speed)
    {
        super(entity);

        this._speed = speed;
        this.setMutexBits(1);
    }

}
