package org.jurassicraft.common.dinosaur;

import org.jurassicraft.common.entity.EntityCoelacanth;
import org.jurassicraft.common.entity.base.EntityDinosaur;
import org.jurassicraft.common.entity.base.EnumDiet;
import org.jurassicraft.common.entity.base.EnumSleepingSchedule;
import org.jurassicraft.common.period.EnumTimePeriod;

public class DinosaurCoelacanth extends Dinosaur
{
    @Override
    public String getName()
    {
        return "Coelacanth";
    }

    @Override
    public Class<? extends EntityDinosaur> getDinosaurClass()
    {
        return EntityCoelacanth.class;
    }

    @Override
    public EnumTimePeriod getPeriod()
    {
        return EnumTimePeriod.DEVONIAN;
    }

    @Override
    public int getEggPrimaryColorMale()
    {
        return 0x3C4B65;
    }

    @Override
    public int getEggSecondaryColorMale()
    {
        return 0x737E96;
    }

    @Override
    public int getEggPrimaryColorFemale()
    {
        return 0x4C4A3A;
    }

    @Override
    public int getEggSecondaryColorFemale()
    {
        return 0x7C775E;
    }

    @Override
    public double getBabyHealth()
    {
        return 5;
    }

    @Override
    public double getAdultHealth()
    {
        return 10;
    }

    @Override
    public double getBabySpeed()
    {
        return 0.62;
    }

    @Override
    public double getAttackSpeed()
    {
        return 0.50;
    }

    @Override
    public double getAdultSpeed()
    {
        return 0.50;
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
        return fromDays(30);
    }

    @Override
    public float getBabyEyeHeight()
    {
        return 0.2F;
    }

    @Override
    public float getAdultEyeHeight()
    {
        return 0.6F;
    }

    @Override
    public float getBabySizeX()
    {
        return 0.3F;
    }

    @Override
    public float getBabySizeY()
    {
        return 0.3F;
    }

    @Override
    public float getAdultSizeX()
    {
        return 1.2F;
    }

    @Override
    public float getAdultSizeY()
    {
        return 1.0F;
    }

    @Override
    public boolean isMarineAnimal()
    {
        return true;
    }

    @Override
    public int getStorage()
    {
        return 18;
    }

    @Override
    public EnumDiet getDiet()
    {
        return EnumDiet.PISCIVORE;
    }

    @Override
    public EnumSleepingSchedule getSleepingSchedule()
    {
        return EnumSleepingSchedule.DIURNAL;
    }

    @Override
    public String[] getBones()
    {
        return new String[] { "skull", "teeth", "spine", "second_dorsal_fin", "pelvic_fin_bones", "pectoral_fin_bones", "first_dorsal_fin", "caudal_fin", "anal_fin" };
    }
}
