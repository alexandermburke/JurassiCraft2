package org.jurassicraft.common.dinosaur;

import org.jurassicraft.common.entity.EntitySegisaurus;
import org.jurassicraft.common.entity.base.EntityDinosaur;
import org.jurassicraft.common.entity.base.EnumDiet;
import org.jurassicraft.common.entity.base.EnumSleepingSchedule;
import org.jurassicraft.common.period.EnumTimePeriod;

public class DinosaurSegisaurus extends Dinosaur
{
    // TODO: Figure out all the entities properties

    @Override
    public String getName()
    {
        return "Segisaurus";
    }

    @Override
    public Class<? extends EntityDinosaur> getDinosaurClass()
    {
        return EntitySegisaurus.class;
    }

    @Override
    public EnumTimePeriod getPeriod()
    {
        return EnumTimePeriod.JURASSIC;
    }

    @Override
    public int getEggPrimaryColorMale()
    {
        return 0x834B4C;
    }

    @Override
    public int getEggSecondaryColorMale()
    {
        return 0x4B8FB5;
    }

    @Override
    public int getEggPrimaryColorFemale()
    {
        return 0xCEEE99;
    }

    @Override
    public int getEggSecondaryColorFemale()
    {
        return 0x776343;
    }

    @Override
    public double getBabyHealth()
    {
        return 16;
    }

    @Override
    public double getAdultHealth()
    {
        return 30;
    }

    @Override
    public double getBabySpeed()
    {
        return 0.46;
    }

    @Override
    public double getAttackSpeed()
    {
        return 0.50;
    }

    @Override
    public double getAdultSpeed()
    {
        return 0.40;
    }

    @Override
    public double getBabyStrength()
    {
        return 6;
    }

    @Override
    public double getAdultStrength()
    {
        return 36;
    }

    @Override
    public int getMaximumAge()
    {
        return fromDays(30);
    }

    @Override
    public float getBabyEyeHeight()
    {
        return 0.3F;
    }

    @Override
    public float getAdultEyeHeight()
    {
        return 0.85F;
    }

    @Override
    public float getBabySizeX()
    {
        return 0.3F;
    }

    @Override
    public float getBabySizeY()
    {
        return 0.4F;
    }

    @Override
    public float getAdultSizeX()
    {
        return 0.5F;
    }

    @Override
    public float getAdultSizeY()
    {
        return 0.85F;
    }

    @Override
    public int getStorage()
    {
        return 9;
    }

    @Override
    public EnumDiet getDiet()
    {
        return EnumDiet.CARNIVORE;
    }

    @Override
    public EnumSleepingSchedule getSleepingSchedule()
    {
        return EnumSleepingSchedule.NOCTURNAL;
    }

    @Override
    public String[] getBones()
    {
        return new String[] { "skull", "teeth" };
    }
}
