package net.reuxertz.ecoai.state;

import net.minecraft.entity.EntityCreature;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

public class StateLife
{
    public static final double animalKgToMeatKgFactor = .5, meatKgToProteinKg = .7, meatKgToFatKg = .3;

    protected double baseWeightKg, baseSizeFactor = 1.0, curWeightKg;

    public StateLife(EntityCreature e)
    {
        weadFromEntityNbt(e);
    }

    public double getExpectedKg()
    {
        return baseWeightKg * baseSizeFactor;
    }

    public double getHungerFactor()
    {
        return curWeightKg / getExpectedKg();
    }

    public boolean isHungry()
    {
        return getHungerFactor() < 1;
    }

    public double getBiteKg()
    {
        return curWeightKg * .05;
    }

    //NBT
    public void writeToEntityNbt(EntityCreature e)
    {
        NBTTagCompound nbt = e.getEntityData();
    }

    public void weadFromEntityNbt(EntityCreature e)
    {
        NBTTagCompound nbt = e.getEntityData();
    }

    public ItemStack intakeFood(ItemStack food)
    {
        if (!isHungry())
            return food;

        return food;
    }
}
