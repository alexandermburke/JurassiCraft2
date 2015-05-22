package net.reuxertz.ainow.entity.ai;

        import net.minecraft.entity.EntityCreature;
        import net.reuxertz.ainow.utils.Counter;

        import java.util.ArrayList;
        import java.util.List;

public class AICore extends AIBase
{
    //Static Properties
    public static final int MaxAINavigateDistance = 250;

    //AI Module Getters
    public AINavigate GetAINavigate()
    {
        return (AINavigate)this.GetAI(AINavigate.class);
    }
    public Object GetAI(Class AIClass)
    {
        /*for (int i = 0; i < e.tasks.taskEntries.size(); i++)
            if (AIClass.isInstance(e.tasks.taskEntries.get(i)))
                return e.tasks.taskEntries.get(i);*/
        for (int i = 0; i < this._modules.size(); i++)
            if (AIClass.isInstance(this._modules.get(i)))
                return this._modules.get(i);

        return null;
    }

    //Fields
    protected Counter _timer;
    protected List<AIBase> _modules = new ArrayList<AIBase>();
    protected int _sleepTimeCounter = 0, _workTimeCounter;

    //Modifiers
    public void AddTask(int priority, AIBase AI)
    {
        for (int i = 0; i < this._modules.size(); i++)
            if (AI.getClass().isInstance(this._modules.get(i)))
                return;
        this._modules.add(AI);
        this.entity().tasks.addTask(priority, AI);
    }

    //Constructors
    public AICore(EntityCreature entity)
    {
        super(entity);

        this._timer = new Counter(entity.worldObj.getWorldTime(), 200, 20);
    }

    //AI Overrides
    @Override
    public void updateTask()
    {
        if (!this._timer.updateMSTime(this.entity().worldObj.getWorldTime()))
            return;

        AINavigate aiNav = this.GetAINavigate();

        if (aiNav != null && !aiNav.Enabled())
            aiNav.ActivateIdleWander(.01);

        System.out.println("aiTick");
    }

}
