package net.reuxertz.civnow.entity.ai;

import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.util.BlockPos;

import javax.vecmath.Point3i;

public abstract class AIBase extends EntityAIBase {

    //Fields
    private EntityCreature _entity;
    private boolean _isFinished = false, _isEnabled = false;
    private BlockPos _workingPosition = null;

    //Properties
    public EntityCreature entity()
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
    public BlockPos WorkingPosition()
    {
        return this._workingPosition;
    }
    public void SetWorkingPosition(BlockPos p)
    {
        this._workingPosition = new BlockPos(p);
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
        return !(!this.Enabled() || this.Finished());
    }
    @Override
    public boolean continueExecuting() {

        if (!super.continueExecuting() || !this.Enabled())
            return false;

        return true;
    }

    //Constructor
    public AIBase(EntityCreature entity)
    {
        super();
        this._entity = entity;
    }

    //Functions
    protected void ActivateTask(BlockPos workingPosition)
    {
        this.resetTask();
        this.SetEnabled(true);
        this.SetWorkingPosition(workingPosition);
    }
}
