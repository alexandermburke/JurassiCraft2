package net.reuxertz.ecoai.ai.modules;

import net.minecraft.entity.EntityCreature;
import net.minecraft.item.ItemStack;
import net.reuxertz.ecoai.ai.AICore;
import net.reuxertz.ecoai.ai.AINavigate;
import net.reuxertz.ecoai.demand.IDemand;
import net.reuxertz.ecoapi.entity.Target;
import net.reuxertz.ecoapi.util.BlockPosHelper;

public abstract class AIModule
{
    public enum WorkState { Init, Search, Move, Work }

    public static final int MaxTickWait = 400;

    //Fields
    protected int[] timeMSDelays = new int[] { 0, 0, 0, 0 };
    protected float tickCount = 0, maxWorkDistance = 20, minWorkDistance = 2;
    protected boolean includeY = false, markDead = false, afterMaxKill = true, afterDoneKill = false;
    protected IDemand demand;
    protected AICore agentAI;
    protected AINavigate navigate;
    protected Target workTarget;
    protected WorkState myState = WorkState.Init;

    //Getters/Setters
    public void setTarget(Target target)
    {
        this.workTarget = target;
    }
    public void setState(WorkState state)
    {
        this.myState = state;
        this.tickCount = (Math.abs(this.timeMSDelays[state.ordinal()]) * -1) - (AICore.RND.nextInt(20) + 10);
    }
    public EntityCreature getAgent()
    {
        return this.agentAI.entity();
    }
    public boolean isDead()
    {
        return this.markDead;
    }

    //AIObj Base Functions Functions
    public ItemStack[] getWorkTool()
    {
        return null;
    }
    public abstract Target nextNavigatePosition();
    public abstract boolean doWorkContinue();
    public abstract boolean evaluateTarget(Target t);
    public boolean isAtWorkPosition()
    {
        return BlockPosHelper.isWithinDistance(this.workTarget.getRealTimePos(), this.getAgent().getPosition(), this.minWorkDistance, !this.includeY);
    }
    public AIModule(IDemand demand, AICore entity, AINavigate navigate)
    {
        this(demand, entity, navigate, null);
    }
    public AIModule(IDemand demand, AICore entity, AINavigate navigate, Target t)
    {
        this.demand = demand;
        this.agentAI = entity;
        this.navigate = navigate;
        this.workTarget = t;
        this.tickCount = AICore.RND.nextInt(20) * -1;
    }

    //Functions
    public void update()
    {
        if (this.getAgent().worldObj.isRemote)
            return;

        this.tickCount++;
        if (this.tickCount < 0)
            return;

        if (this.tickCount > AIModule.MaxTickWait)
        {
            if (this.afterMaxKill)
            {
                this.markDead = true;
                return;
            }
            else
                this.setState(WorkState.Init);
        }

        if (this.myState == WorkState.Init)
        {
            //this._currentJobSite = this.GetRandomWorkSite();
            //this.SetMoveToLocation(this._currentJobSite);
            this.setState(WorkState.Search);
            return;
        }

        //If searching
        if (this.myState == WorkState.Search)
        {
            Target nbp = null;
            if (this.workTarget == null)
                nbp = this.nextNavigatePosition();
            else if (this.evaluateTarget(this.workTarget))
                nbp = this.workTarget;
            else
                this.workTarget = null;

            if (nbp != null && BlockPosHelper.isWithinDistance(this.getAgent().getPosition(), nbp.getRealTimePos(), this.maxWorkDistance, !this.includeY))
            {
                this.workTarget = nbp;
                this.workTarget.activateNavigate(this.navigate);
                this.setState(WorkState.Move);
                this.navigate.deactivateTask(true);
            }
            else if (!AICore.getAiNavigate(this.getAgent()).isEnabled())
                this.navigate.activateIdleWander(.05);

            return;
        }

        if (this.workTarget == null || !this.evaluateTarget(this.workTarget))
        {
            this.setState(WorkState.Search);
            return;
        }

        boolean pReached = this.isAtWorkPosition();

        //If ready to work but not at location, goto location
        if (!pReached && this.myState == WorkState.Work)
        {
            this.clearState();
            return;
        }

        //If not at location, not moving, and has locationo, goto location
        if (!pReached && this.myState != WorkState.Move && this.workTarget != null)
        {
            this.setState(WorkState.Move);
            this.workTarget.activateNavigate(this.navigate);
            return;
        }

        if (!pReached && this.myState == WorkState.Move && this.workTarget != null &&
                this.getAgent().motionX + this.getAgent().motionY + this.getAgent().motionZ < .01)
        {
            this.workTarget.activateNavigate(this.navigate);
            return;
        }

        //If At location but set to work, set to work
        if (pReached && this.myState != WorkState.Work)
        {
            this.setState(WorkState.Work);
            return;
        }

        //If ready to work, do work
        if (this.myState == WorkState.Work)
        {
            if (this.doWorkContinue())
            {
                this.setState(WorkState.Work);
                return;
            }

            if (this.afterDoneKill)
            {
                this.markDead = true;
                return;
            }

            this.clearState();
            return;
        }

    }

    public void clearState()
    {
        this.clearState(null);
    }
    public void clearState(WorkState state)
    {
        if (state != null)
            this.setState(state);
        else
            this.setState(WorkState.Init);

        this.workTarget = null;
    }


}
