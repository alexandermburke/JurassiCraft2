package org.jurassicraft.common.dinosaur;

import org.jurassicraft.common.entity.EntityApatosaurus;
import org.jurassicraft.common.entity.base.EntityDinosaur;
import org.jurassicraft.common.entity.base.EnumDiet;
import org.jurassicraft.common.entity.base.EnumSleepingSchedule;
import org.jurassicraft.common.period.EnumTimePeriod;

public class DinosaurApatosaurus extends Dinosaur
{
    @Override
    public String getName()
    {
        return "Apatosaurus";
    }

    @Override
    public Class<? extends EntityDinosaur> getDinosaurClass()
    {
        return EntityApatosaurus.class;
    }

    @Override
    public EnumTimePeriod getPeriod()
    {
        return EnumTimePeriod.JURASSIC;
    }

    @Override
    public int getEggPrimaryColorMale()
    {
        return 0xA79F93;
    }

    @Override
    public int getEggSecondaryColorMale()
    {
        return 0x987664;
    }

    @Override
    public int getEggPrimaryColorFemale()
    {
        return 0x7E7D70;
    }

    @Override
    public int getEggSecondaryColorFemale()
    {
        return 0x30343E;
    }

    @Override
    public double getBabyHealth()
    {
        return 10;
    }

    @Override
    public double getAdultHealth()
    {
        return 60;
    }

    @Override
    public double getBabySpeed()
    {
        return 0.32;
    }

    @Override
    public double getAttackSpeed()
    {
        return 0.50;
    }

    @Override
    public double getAdultSpeed()
    {
        return 0.25;
    }

    @Override
    public double getBabyStrength()
    {
        return 5;
    }

    @Override
    public double getAdultStrength()
    {
        return 20;
    }

    @Override
    public int getMaximumAge()
    {
        return fromDays(80);
    }

    @Override
    public float getBabyEyeHeight()
    {
        return 0.9F;
    }

    @Override
    public float getAdultEyeHeight()
    {
        return 6.8F;
    }

    @Override
    public float getBabySizeX()
    {
        return 0.9F;
    }

    @Override
    public float getBabySizeY()
    {
        return 1.0F;
    }

    @Override
    public float getAdultSizeX()
    {
        return 6.5F;
    }

    @Override
    public float getAdultSizeY()
    {
        return 6.8F;
    }

    @Override
    public int getStorage()
    {
        return 54;
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
        return new String[] { "skull", "tooth" };
    }
}
