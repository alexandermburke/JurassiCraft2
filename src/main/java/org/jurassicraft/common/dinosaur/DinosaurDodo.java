package org.jurassicraft.common.dinosaur;

import org.jurassicraft.common.entity.EntityDodo;
import org.jurassicraft.common.entity.base.EntityDinosaur;
import org.jurassicraft.common.entity.base.EnumDiet;
import org.jurassicraft.common.entity.base.EnumSleepingSchedule;
import org.jurassicraft.common.period.EnumTimePeriod;

public class DinosaurDodo extends Dinosaur
{
    @Override
    public String getName()
    {
        return "Dodo";
    }

    @Override
    public Class<? extends EntityDinosaur> getDinosaurClass()
    {
        return EntityDodo.class;
    }

    @Override
    public EnumTimePeriod getPeriod()
    {
        return EnumTimePeriod.CRETACEOUS;
    }

    @Override
    public int getEggPrimaryColorMale()
    {
        return 0xA2996E;
    }

    @Override
    public int getEggSecondaryColorMale()
    {
        return 0x545338;
    }

    @Override
    public int getEggPrimaryColorFemale()
    {
        return 0x908B80;
    }

    @Override
    public int getEggSecondaryColorFemale()
    {
        return 0x665C51;
    }

    @Override
    public double getBabyHealth()
    {
        return 5;
    }

    @Override
    public double getAdultHealth()
    {
        return 15;
    }

    @Override
    public double getBabySpeed()
    {
        return 0.35;
    }

    @Override
    public double getAttackSpeed()
    {
        return 0.17;
    }

    @Override
    public double getAdultSpeed()
    {
        return 0.30;
    }

    @Override
    public double getBabyStrength()
    {
        return 1;
    }

    @Override
    public double getAdultStrength()
    {
        return 5;
    }

    @Override
    public int getMaximumAge()
    {
        return fromDays(20);
    }

    @Override
    public float getBabyEyeHeight()
    {
        return 0.35F;
    }

    @Override
    public float getAdultEyeHeight()
    {
        return 0.95F;
    }

    @Override
    public float getBabySizeX()
    {
        return 0.25F;
    }

    @Override
    public float getBabySizeY()
    {
        return 0.35F;
    }

    @Override
    public float getAdultSizeX()
    {
        return 0.5F;
    }

    @Override
    public float getAdultSizeY()
    {
        return 0.95F;
    }

    @Override
    public int getStorage()
    {
        return 9;
    }

    @Override
    public EnumDiet getDiet()
    {
        return EnumDiet.HERBIVORE;
    }

    @Override
    public EnumSleepingSchedule getSleepingSchedule()
    {
        return EnumSleepingSchedule.DIURNAL;
    }

    @Override
    public String[] getBones()
    {
        return new String[] { "skull" };
    }
}
