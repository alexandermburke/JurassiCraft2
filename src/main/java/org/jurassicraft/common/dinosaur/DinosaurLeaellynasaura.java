package org.jurassicraft.common.dinosaur;

import org.jurassicraft.common.entity.EntityLeaellynasaura;
import org.jurassicraft.common.entity.base.EntityDinosaur;
import org.jurassicraft.common.entity.base.EnumDiet;
import org.jurassicraft.common.entity.base.EnumSleepingSchedule;
import org.jurassicraft.common.period.EnumTimePeriod;

public class DinosaurLeaellynasaura extends Dinosaur
{
    @Override
    public String getName()
    {
        return "Leaellynasaura";
    }

    @Override
    public Class<? extends EntityDinosaur> getDinosaurClass()
    {
        return EntityLeaellynasaura.class;
    }

    @Override
    public EnumTimePeriod getPeriod()
    {
        return EnumTimePeriod.CRETACEOUS;
    }

    @Override
    public int getEggPrimaryColorMale()
    {
        return 0xE1D0A6;
    }

    @Override
    public int getEggSecondaryColorMale()
    {
        return 0x262B27;
    }

    @Override
    public int getEggPrimaryColorFemale()
    {
        return 0xC8B50C;
    }

    @Override
    public int getEggSecondaryColorFemale()
    {
        return 0x926045;
    }

    @Override
    public double getBabyHealth()
    {
        return 10;
    }

    @Override
    public double getAdultHealth()
    {
        return 25;
    }

    @Override
    public double getBabySpeed()
    {
        return 0.45;
    }

    @Override
    public double getAttackSpeed()
    {
        return 0.17;
    }

    @Override
    public double getAdultSpeed()
    {
        return 0.50;
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
        return fromDays(35);
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
        return 0.6F;
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
        return new String[] { "skull", "tooth", "tail_vertebrae", "shoulder" };
    }
}
