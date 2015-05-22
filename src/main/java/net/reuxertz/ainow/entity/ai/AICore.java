package net.reuxertz.ainow.entity.ai;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.ai.EntityAITasks;
import net.minecraft.world.World;
import net.reuxertz.ainow.utils.CounterObj;

public class AICore
        extends AIBase
{
    public static final int MaxAINavigateDistance = 250;
    protected CounterObj _timer;

    public AINavigate GetAINavigate()
    {
        return (AINavigate)GetAI(AINavigate.class);
    }

    public Object GetAI(Class AIClass)
    {
        for (int i = 0; i < this._modules.size(); i++) {
            if (AIClass.isInstance(this._modules.get(i))) {
                return this._modules.get(i);
            }
        }
        return null;
    }

    protected List<AIBase> _modules = new ArrayList();
    protected int _sleepTimeCounter = 0;
    protected int _workTimeCounter;

    public void AddTask(int priority, AIBase AI)
    {
        for (int i = 0; i < this._modules.size(); i++) {
            if (AI.getClass().isInstance(this._modules.get(i))) {
                return;
            }
        }
        this._modules.add(AI);
        entity().tasks.addTask(priority, AI);
    }

    public AICore(EntityCreature entity)
    {
        super(entity);

        this._timer = new CounterObj(entity.worldObj.getWorldTime(), 200, 20);
    }

    public void updateTask()
    {
        if (!this._timer.updateMSTime(entity().worldObj.getWorldTime())) {
            return;
        }
        AINavigate aiNav = GetAINavigate();
        if ((aiNav != null) && (!aiNav.Enabled())) {
            aiNav.ActivateIdleWander(0.01D);
        }
        System.out.println("aiTick");
    }
}
