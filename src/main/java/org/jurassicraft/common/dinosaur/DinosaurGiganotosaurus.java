package org.jurassicraft.common.dinosaur;

import org.jurassicraft.common.entity.EntityGiganotosaurus;
import org.jurassicraft.common.entity.base.EntityDinosaur;
import org.jurassicraft.common.entity.base.EnumDiet;
import org.jurassicraft.common.entity.base.EnumSleepingSchedule;
import org.jurassicraft.common.period.EnumTimePeriod;

public class DinosaurGiganotosaurus extends Dinosaur
{
    // TODO: Figure out all the entities properties

    @Override
    public String getName()
    {
        return "Giganotosaurus";
    }

    @Override
    public Class<? extends EntityDinosaur> getDinosaurClass()
    {
        return EntityGiganotosaurus.class;
    }

    @Override
    public EnumTimePeriod getPeriod()
    {
        return EnumTimePeriod.CRETACEOUS;
    }

    @Override
    public int getEggPrimaryColorMale()
    {
        return 0x4F3F33;
    }

    @Override
    public int getEggSecondaryColorMale()
    {
        return 0x4F3F33;
    }

    @Override
    public int getEggPrimaryColorFemale()
    {
        return 0x756E54;
    }

    @Override
    public int getEggSecondaryColorFemale()
    {
        return 0x4B474A;
    }

    @Override
    public double getBabyHealth()
    {
        return 20;
    }

    @Override
    public double getAdultHealth()
    {
        return 95;
    }

    @Override
    public double getBabySpeed()
    {
        return 0.52;
    }

    @Override
    public double getAttackSpeed()
    {
        return 0.50;
    }

    @Override
    public double getAdultSpeed()
    {
        return 0.4;
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
        return fromDays(65);
    }

    @Override
    public float getBabyEyeHeight()
    {
        return 0.6F;
    }

    @Override
    public float getAdultEyeHeight()
    {
        return 4.8F;
    }

    @Override
    public float getBabySizeX()
    {
        return 0.5F;
    }

    @Override
    public float getBabySizeY()
    {
        return 0.8F;
    }

    @Override
    public float getAdultSizeX()
    {
        return 4F;
    }

    @Override
    public float getAdultSizeY()
    {
        return 5.8F;
    }

    @Override
    public int getStorage()
    {
        return 54;
    }

    @Override
    public EnumDiet getDiet()
    {
        return EnumDiet.CARNIVORE;
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
