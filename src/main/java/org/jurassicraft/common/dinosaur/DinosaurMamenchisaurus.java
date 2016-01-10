package org.jurassicraft.common.dinosaur;

import org.jurassicraft.common.entity.EntityMamenchisaurus;
import org.jurassicraft.common.entity.base.EntityDinosaur;
import org.jurassicraft.common.entity.base.EnumDiet;
import org.jurassicraft.common.entity.base.EnumSleepingSchedule;
import org.jurassicraft.common.period.EnumTimePeriod;

public class DinosaurMamenchisaurus extends Dinosaur
{
    @Override
    public String getName()
    {
        return "Mamenchisaurus";
    }

    @Override
    public Class<? extends EntityDinosaur> getDinosaurClass()
    {
        return EntityMamenchisaurus.class;
    }

    @Override
    public EnumTimePeriod getPeriod()
    {
        return EnumTimePeriod.JURASSIC;
    }

    @Override
    public int getEggPrimaryColorMale()
    {
        return 0xD1BA49;
    }

    @Override
    public int getEggSecondaryColorMale()
    {
        return 0x909B1D;
    }

    @Override
    public int getEggPrimaryColorFemale()
    {
        return 0x98764E;
    }

    @Override
    public int getEggSecondaryColorFemale()
    {
        return 0x545028;
    }

    @Override
    public double getBabyHealth()
    {
        return 25;
    }

    @Override
    public double getAdultHealth()
    {
        return 140;
    }

    @Override
    public double getBabySpeed()
    {
        return 0.32;
    }

    @Override
    public double getAttackSpeed()
    {
        return 0.17;
    }

    @Override
    public int getStorage()
    {
        return 52;
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
    public double getAdultSpeed()
    {
        return 0.25;
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
        return fromDays(95);
    }

    @Override
    public float getBabyEyeHeight()
    {
        return 1.55F;
    }

    @Override
    public float getAdultEyeHeight()
    {
        return 13.95F;
    }

    @Override
    public float getBabySizeX()
    {
        return 0.75F;
    }

    @Override
    public float getBabySizeY()
    {
        return 0.75F;
    }

    @Override
    public float getAdultSizeX()
    {
        return 5.5F;
    }

    @Override
    public float getAdultSizeY()
    {
        return 5.95F;
    }

    @Override
    public String[] getBones()
    {
        return new String[] { "skull" };
    }
}
