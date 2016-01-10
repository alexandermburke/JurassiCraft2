package org.jurassicraft.common.dinosaur;

import org.jurassicraft.common.entity.EntityLeptictidium;
import org.jurassicraft.common.entity.base.EntityDinosaur;
import org.jurassicraft.common.entity.base.EnumDiet;
import org.jurassicraft.common.entity.base.EnumSleepingSchedule;
import org.jurassicraft.common.period.EnumTimePeriod;

public class DinosaurLeptictidium extends Dinosaur
{
    @Override
    public String getName()
    {
        return "Leptictidium";
    }

    @Override
    public Class<? extends EntityDinosaur> getDinosaurClass()
    {
        return EntityLeptictidium.class;
    }

    @Override
    public EnumTimePeriod getPeriod()
    {
        return EnumTimePeriod.CRETACEOUS;
    } // TODO EOCENE

    @Override
    public int getEggPrimaryColorMale()
    {
        return 0x362410;
    }

    @Override
    public int getEggSecondaryColorMale()
    {
        return 0x978A78;
    }

    @Override
    public int getEggPrimaryColorFemale()
    {
        return 0xAFA27E;
    }

    @Override
    public int getEggSecondaryColorFemale()
    {
        return 0x3E2D17;
    }

    @Override
    public double getBabyHealth()
    {
        return 8;
    }

    @Override
    public double getAdultHealth()
    {
        return 18;
    }

    @Override
    public double getBabySpeed()
    {
        return 0.42;
    }

    @Override
    public double getAttackSpeed()
    {
        return 0.17;
    }

    @Override
    public double getAdultSpeed()
    {
        return 0.38;
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
        return fromDays(25);
    }

    @Override
    public float getBabyEyeHeight()
    {
        return 0.21F;
    }

    @Override
    public float getAdultEyeHeight()
    {
        return 0.63F;
    }

    @Override
    public float getBabySizeX()
    {
        return 0.2F;
    }

    @Override
    public float getBabySizeY()
    {
        return 0.25F;
    }

    @Override
    public float getAdultSizeX()
    {
        return 0.5F;
    }

    @Override
    public float getAdultSizeY()
    {
        return 0.75F;
    }

    @Override
    public boolean isMammal()
    {
        return true;
    }

    @Override
    public boolean shouldRegister()
    {
        return false;
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
        return new String[] {};
    }
}
