package net.reuxertz.ecoai.ai;

import net.minecraft.entity.EntityCreature;
import net.minecraft.nbt.NBTTagCompound;
import net.reuxertz.ecoapi.util.CounterObj;

public class AIMetabolism extends AIBase
{
    public static final double joulesToFatGramRatio = 37000,
            joulesToProteinGramRatio = 17000,
            joulesToCarbGramRatio = 16000,
            jouleCostPerGramDay = 100;

    //6000kj per day

    protected double activeEnergyJ = 0;
    protected double protMassKg, fatMassKg, boneMassKg, waterMassKg; //.1, .2, .1, .6
    protected long lastTime = -1;
    protected CounterObj timer;

    public double totalMassKg()
    {
        return protMassKg + fatMassKg + boneMassKg + waterMassKg;
    }

    public double expectedMassKg()
    {
        return this.boneMassKg * 10;
    }

    public double percentThirst()
    {
        return 1 - (this.waterMassKg / (this.expectedMassKg() * .6));
    }

    public double percentHunger()
    {
        return 1 - ((this.fatMassKg + this.protMassKg) / (this.expectedMassKg() * .3));
    }

    public AIMetabolism(EntityCreature entity)
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

        if (this.lastTime == -1)
        {
            this.lastTime = this.entity.worldObj.getWorldTime();
            return;
        }
        long delTime = this.entity.worldObj.getWorldTime() - this.lastTime;
        this.lastTime = this.entity.worldObj.getWorldTime();


    }
}
