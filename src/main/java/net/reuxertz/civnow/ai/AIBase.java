package net.reuxertz.civnow.ai;

import net.minecraft.entity.ai.EntityAIBase;

import javax.vecmath.Point3i;

/**
 * Created by Ryan on 5/20/2015.
 */
public abstract class AIBase extends EntityAIBase {

    protected boolean IsFinished = false, Enabled = false;
    protected Point3i WorkingPosition = null;

    public boolean Enabled()
    {
        return this.Enabled;
    }
    public void SetEnabled(boolean enabled)
    {
        this.Enabled = enabled;
    }
    public boolean IsFinished()
    {
        return this.IsFinished;
    }
    public void SetFinished(boolean b)
    {
        this.IsFinished = b;
    }
    public Point3i GetWorkingPosition()
    {
        return this.WorkingPosition;
    }
    public void SetWorkingPosition(Point3i p)
    {
        this.WorkingPosition = new Point3i(p);
    }

    public AIBase()
    {
        super();
    }

    @Override
    public void resetTask()
    {
        super.resetTask();

        this.IsFinished = false;
        this.WorkingPosition = null;
    }
    @Override
    public boolean shouldExecute()
    {
        return this.Enabled;
    }
}
