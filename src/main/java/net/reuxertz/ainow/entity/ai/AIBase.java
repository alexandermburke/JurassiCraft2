package net.reuxertz.ainow.entity.ai;

import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.util.BlockPos;

public abstract class AIBase extends EntityAIBase {

    //Fields
    protected EntityCreature _entity;
    protected boolean _isFinished = false, _isEnabled = false, _isRecursive;
    protected BlockPos _workingPosition = null;
    protected double _probRecursion = .01;

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
        if (p == null)
            this._workingPosition = null;
        else
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
        this.ActivateTask(workingPosition, false);
    }
    protected void ActivateTask(BlockPos workingPosition, boolean setRecursive)
    {
        this.resetTask();
        this.SetEnabled(true);
        this.SetWorkingPosition(workingPosition);
    }
    protected void DeactivateTask()
    {
        this.DeactivateTask(true);
    }
    protected void DeactivateTask(boolean removeRecursive)
    {
        this.resetTask();
        this.SetEnabled(false);
        this.SetWorkingPosition(null);

        if (removeRecursive)
            this._isRecursive = false;
    }
}
