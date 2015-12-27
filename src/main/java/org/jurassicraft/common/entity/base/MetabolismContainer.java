package org.jurassicraft.common.entity.base;

import io.netty.buffer.ByteBuf;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;

public class MetabolismContainer
{
    public final int MAX_FOOD;
    public final int MAX_WATER;

    private int food;
    private int water;

    private EntityDinosaur dinosaur;

    public MetabolismContainer(EntityDinosaur dinosaur)
    {
        this.dinosaur = dinosaur;

        MAX_FOOD = (int) (24000 * (dinosaur.getDinosaur().getAdultHealth() / 15));
        MAX_WATER = (int) (24000 * (dinosaur.getDinosaur().getAdultHealth() / 15));

        food = MAX_FOOD;
        water = MAX_WATER;
    }

    public void update()
    {
        if (!dinosaur.isDead && !dinosaur.isCarcass() && dinosaur.worldObj.getGameRules().getGameRuleBooleanValue("dinoMetabolism"))
        {
            decreaseFood(1);
            decreaseWater(1);

            if (dinosaur.isWet())
            {
                water = MAX_WATER;
            }
        }
    }

    public int getWater()
    {
        return water;
    }

    public int getFood()
    {
        return food;
    }

    public void decreaseFood(int amount)
    {
        food -= amount;

        if (food <= 0)
        {
            dinosaur.attackEntityFrom(DamageSource.outOfWorld, 1.0F);
        }
    }

    public void decreaseWater(int amount)
    {
        water -= amount;

        if (water <= 0)
        {
            dinosaur.attackEntityFrom(DamageSource.outOfWorld, 1.0F);
        }
    }

    public void setWater(int water)
    {
        this.water = Math.min(water, MAX_WATER);
    }

    public void setFood(int food)
    {
        this.food = Math.min(food, MAX_FOOD);
    }

    public void readFromNBT(NBTTagCompound nbt)
    {
        this.water = nbt.getInteger("Water");
        this.food = nbt.getInteger("Food");
    }

    public void writeToNBT(NBTTagCompound nbt)
    {
        nbt.setInteger("Water", water);
        nbt.setInteger("Food", food);
    }

    public void writeSpawnData(ByteBuf buf)
    {
        buf.writeInt(water);
        buf.writeInt(food);
    }

    public void readSpawnData(ByteBuf buf)
    {
        water = buf.readInt();
        food = buf.readInt();
    }

    public int getMaxFood()
    {
        return MAX_FOOD;
    }

    public int getMaxWater()
    {
        return MAX_WATER;
    }

    public void increaseFood(int amount)
    {
        setFood(food + amount);
    }

    public void increaseWater(int amount)
    {
        setWater(water + amount);
    }
}
