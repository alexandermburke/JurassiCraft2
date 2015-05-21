package net.reuxertz.ainow.entity.ai;

        import net.minecraft.entity.EntityCreature;
        import net.reuxertz.ainow.utils.Counter;

public class AICore extends AIBase
{
    protected Counter _timer = new Counter(60, 0, 20);

    public AICore(EntityCreature entity)
    {
        super(entity);
    }

    @Override
    public void updateTask()
    {
        if (!this._timer.updateMSTime(this.entity().worldObj.getWorldTime()))
            return;

        return;
    }

}
