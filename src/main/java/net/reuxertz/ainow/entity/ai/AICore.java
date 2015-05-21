package net.reuxertz.ainow.entity.ai;

        import net.minecraft.entity.EntityCreature;
        import net.reuxertz.ainow.utils.Counter;

public class AICore extends AIBase
{
    //AI Module Getters
    public static AICore GetAICore(EntityCreature e)
    {
        return (AICore)AICore.GetAI(e, AICore.class);
    }
    public static Object GetAI(EntityCreature e, Class AIClass)
    {
        for (int i = 0; i < e.tasks.taskEntries.size(); i++)
            if (AIClass.isInstance(e.tasks.taskEntries.get(i)))
                return e.tasks.taskEntries.get(i);

        return null;
    }

    //Fields
    protected Counter _timer;

    //Constructors
    public AICore(EntityCreature entity)
    {
        super(entity);

        this._timer = new Counter(entity.worldObj.getWorldTime(), 100, 20);

    }

    //AI Overrides
    @Override
    public void updateTask()
    {
        if (!this._timer.updateMSTime(this.entity().worldObj.getWorldTime()))
            return;

        System.out.println("aiTick - ");
    }

}
