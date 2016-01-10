package org.jurassicraft.common.dinosaur;

import org.jurassicraft.common.entity.EntityMicroceratus;
import org.jurassicraft.common.entity.base.EntityDinosaur;
import org.jurassicraft.common.entity.base.EnumDiet;
import org.jurassicraft.common.entity.base.EnumSleepingSchedule;
import org.jurassicraft.common.period.EnumTimePeriod;

public class DinosaurMicroceratus extends Dinosaur
{
    @Override
    public String getName()
    {
        return "Microceratus";
    }

    @Override
    public Class<? extends EntityDinosaur> getDinosaurClass()
    {
        return EntityMicroceratus.class;
    }

    @Override
    public EnumTimePeriod getPeriod()
    {
        return EnumTimePeriod.CRETACEOUS;
    }

    @Override
    public int getEggPrimaryColorMale()
    {
        return 0x956F2D;
    }

    @Override
    public int getEggSecondaryColorMale()
    {
        return 0x92442C;
    }

    @Override
    public int getEggPrimaryColorFemale()
    {
        return 0x958331;
    }

    @Override
    public int getEggSecondaryColorFemale()
    {
        return 0x7E4A1F;
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
        return 0.41;
    }

    @Override
    public double getAttackSpeed()
    {
        return 0.17;
    }

    @Override
    public double getAdultSpeed()
    {
        return 0.35;
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
        return 0.14F;
    }

    @Override
    public float getAdultEyeHeight()
    {
        return 0.36F;
    }

    @Override
    public float getBabySizeX()
    {
        return 0.15F;
    }

    @Override
    public float getBabySizeY()
    {
        return 0.18F;
    }

    @Override
    public float getAdultSizeX()
    {
        return 0.4F;
    }

    @Override
    public float getAdultSizeY()
    {
        return 0.55F;
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
        return new String[] { "skull", "teeth" };
    }
}
