package net.reuxertz.civnow.ai;

import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.ai.EntityAIBase;

import javax.vecmath.Point3i;

/**
 * Created by Ryan on 5/20/2015.
 */
public abstract class AIBase extends EntityAIBase {

    //Fields
    private EntityLiving _entity;
    private boolean _isFinished = false, _isEnabled = false;
    private Point3i _workingPosition = null;

    //Properties
    public EntityLiving entity()
    {
        return this._entity;
    }
    public boolean Enabled()
    {
        return this._isEnabled;
    }
    public void SetEnabled(boolean enabled)
    {
        this._isEnabled = enabled;
    }
    public boolean Finished()
    {
        return this._isFinished;
    }
    public void SetFinished(boolean b)
    {
        this._isFinished = b;
    }
    public Point3i WorkingPosition()
    {
        return this._workingPosition;
    }
    public void SetWorkingPosition(Point3i p)
    {
        this._workingPosition = new Point3i(p);
    }

    //Overridden Functions
    @Override
    public void resetTask()
    {
        super.resetTask();

        this.SetFinished(false);
        this.SetWorkingPosition(null);
    }
    @Override
    public boolean shouldExecute()
    {
        return this.Enabled() && !this.Finished();
    }
    @Override
    public boolean continueExecuting() {

        if (!super.continueExecuting() || !this.Enabled())
            return false;

        return true;
    }

    //Constructor
    public AIBase(EntityLiving entity)
    {
        super();
        this._entity = entity;
    }

    //Function
    public void ActivateTask(Point3i workingPosition)
    {
        this.resetTask();
        this.SetEnabled(true);
        this.SetWorkingPosition(workingPosition);
    }
}
