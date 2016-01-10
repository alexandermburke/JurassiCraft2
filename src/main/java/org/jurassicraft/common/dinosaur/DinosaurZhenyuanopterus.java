package org.jurassicraft.common.dinosaur;

import org.jurassicraft.common.entity.EntityZhenyuanopterus;
import org.jurassicraft.common.entity.base.EntityDinosaur;
import org.jurassicraft.common.entity.base.EnumDiet;
import org.jurassicraft.common.entity.base.EnumSleepingSchedule;
import org.jurassicraft.common.period.EnumTimePeriod;

public class DinosaurZhenyuanopterus extends Dinosaur
{
    @Override
    public String getName()
    {
        return "Zhenyuanopterus";
    }

    @Override
    public Class<? extends EntityDinosaur> getDinosaurClass()
    {
        return EntityZhenyuanopterus.class;
    }

    @Override
    public EnumTimePeriod getPeriod()
    {
        return EnumTimePeriod.CRETACEOUS;
    }

    @Override
    public int getEggPrimaryColorMale()
    {
        return 0x434F4E;
    }

    @Override
    public int getEggSecondaryColorMale()
    {
        return 0x0F1010;
    }

    @Override
    public int getEggPrimaryColorFemale()
    {
        return 0x4A5957;
    }

    @Override
    public int getEggSecondaryColorFemale()
    {
        return 0xB9B7A3;
    }

    @Override
    public double getBabyHealth()
    {
        return 16;
    }

    @Override
    public double getAdultHealth()
    {
        return 55;
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
        return fromDays(40);
    }

    @Override
    public float getBabyEyeHeight()
    {
        return 0.225F;
    }

    @Override
    public float getAdultEyeHeight()
    {
        return 1.3F;
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
        return 1.0F;
    }

    @Override
    public float getAdultSizeY()
    {
        return 1.3F;
    }

    @Override
    public int getStorage()
    {
        return 27;
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
        return new String[] { "leg_bones", "pelvis", "skull", "tail_vertebrae", "teeth", "wing_bones" };
    }
}
