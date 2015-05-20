package net.reuxertz.civnow.ai;

import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.pathfinding.PathEntity;
import net.minecraft.pathfinding.PathNavigate;
import net.minecraft.util.MathHelper;

import javax.vecmath.Point3i;

/**
 * Created by Ryan on 5/20/2015.
 */
public class AINavigate extends AIBase {

    //Fields
    private double _speed;

    //Properties
    public boolean PositionReached(boolean includeY, double dist) {

        double r1 = dist * dist;
        double r2x = this.entity().posX - this.WorkingPosition().x, r2z = this.entity().posZ - this.WorkingPosition().z;
        //double r2x = entity.posX - this.WorkingPosition.x, r2z = entity.posZ - this.WorkingPosition.z;
        double r2 = r2x * r2x + r2z * r2z;

        if (includeY) {
            double r2y = this.entity().posY -this.WorkingPosition().y;
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
        PathEntity pe = n.getPathToXYZ(this.WorkingPosition().x, this.WorkingPosition().y, this.WorkingPosition().z);
    }

    //Constructor
    public AINavigate(EntityLiving entity, double speed)
    {
        super(entity);

        this._speed = speed;
        this.setMutexBits(1);
    }

}
