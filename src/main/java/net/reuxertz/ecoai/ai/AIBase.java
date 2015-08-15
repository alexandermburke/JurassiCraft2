package net.reuxertz.ecoai.ai;

import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.BlockPos;

public abstract class AIBase extends EntityAIBase
{
    //Fields
    protected EntityCreature entity;
    protected boolean isFinished = false, isEnabled = false, isRecursive;
    protected BlockPos workingPosition = null;
    protected double probRecursion = .01;

    //Modifiers
    public EntityCreature entity()
    {
        return this.entity;
    }
    public boolean isEnabled()
    {
        return this.isEnabled;
    }
    public void setEnabled(boolean enabled)
    {
        this.isEnabled = enabled;
    }
    public boolean isFinished()
    {
        return this.isFinished;
    }
    public void setFinished(boolean b)
    {
        this.isFinished = b;
    }
    public BlockPos getWorkingPosition()
    {
        return this.workingPosition;
    }
    public void setWorkingPosition(BlockPos p)
    {
        if (p == null)
            workingPosition = null;
        else
            workingPosition = new BlockPos(p);
    }

    //Constructor
    public AIBase(EntityCreature entity)
    {
        super();
        this.entity = entity;
    }

    public NBTTagCompound writeToEntityNbt()
    {
        return this.entity.getEntityData();
    }
    public void readFromEntityNbt()
    {
    }

    //Properties
    public void resetTask()
    {
        super.resetTask();

        setFinished(false);
        setWorkingPosition(null);
    }

    public boolean shouldExecute()
    {
        if (isFinished)
            return false;

        return isEnabled;

    }

    public boolean continueExecuting()
    {
        return !(!super.continueExecuting() || !isEnabled());
    }

    protected void activateTask(BlockPos workingPosition)
    {
        resetTask();
        setEnabled(true);
        setWorkingPosition(workingPosition);
    }

    protected void deactivateTask(boolean removeRecursive)
    {
        resetTask();

        if (removeRecursive)
            isRecursive = false;

        if (!isRecursive)
            setEnabled(false);
    }
}
