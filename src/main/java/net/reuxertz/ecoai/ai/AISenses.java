package net.reuxertz.ecoai.ai;

import net.minecraft.entity.EntityCreature;
import net.minecraft.nbt.NBTTagCompound;
import net.reuxertz.ecoapi.util.CounterObj;

public class AISenses extends AIBase
{
    protected CounterObj timer;

    public AISenses(EntityCreature entity)
    {
        super(entity);
        timer = new CounterObj(entity.worldObj.getWorldTime(), 200, 20);
    }

    @Override
    public NBTTagCompound writeToEntityNbt()
    {
        return this.entity().getEntityData();

    }
    @Override
    public void readFromEntityNbt()
    {
        NBTTagCompound nbt = this.entity().getEntityData();

    }

    public void updateEnergy()
    {
        //double x =
    }

    public void updateTask()
    {
        this.updateEnergy();

        if (!this.timer.updateMSTime(entity().worldObj.getWorldTime()))
            return;

    }
}
