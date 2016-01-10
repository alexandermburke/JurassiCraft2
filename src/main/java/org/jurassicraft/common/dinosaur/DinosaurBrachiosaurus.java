package org.jurassicraft.common.dinosaur;

import org.jurassicraft.common.entity.EntityBrachiosaurus;
import org.jurassicraft.common.entity.base.EntityDinosaur;
import org.jurassicraft.common.entity.base.EnumDiet;
import org.jurassicraft.common.entity.base.EnumSleepingSchedule;
import org.jurassicraft.common.period.EnumTimePeriod;

public class DinosaurBrachiosaurus extends Dinosaur
{
    @Override
    public String getName()
    {
        return "Brachiosaurus";
    }

    @Override
    public Class<? extends EntityDinosaur> getDinosaurClass()
    {
        return EntityBrachiosaurus.class;
    }

    @Override
    public EnumTimePeriod getPeriod()
    {
        return EnumTimePeriod.JURASSIC;
    }

    @Override
    public int getEggPrimaryColorMale()
    {
        return 0x87987F;
    }

    @Override
    public int getEggSecondaryColorMale()
    {
        return 0x607343;
    }

    @Override
    public int getEggPrimaryColorFemale()
    {
        return 0xAA987D;
    }

    @Override
    public int getEggSecondaryColorFemale()
    {
        return 0x4F4538;
    }

    @Override
    public double getBabyHealth()
    {
        return 20;
    }

    @Override
    public double getAdultHealth()
    {
        return 100;
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
        return 10;
    }

    @Override
    public double getAdultStrength()
    {
        return 20;
    }

    @Override
    public int getMaximumAge()
    {
        return fromDays(85);
    }

    @Override
    public float getBabyEyeHeight()
    {
        return 2.2F;
    }

    @Override
    public float getAdultEyeHeight()
    {
        return 18.4F;
    }

    @Override
    public float getBabySizeX()
    {
        return 0.9F;
    }

    @Override
    public float getBabySizeY()
    {
        return 1.5F;
    }

    @Override
    public float getAdultSizeX()
    {
        return 6.5F;
    }

    @Override
    public float getAdultSizeY()
    {
        return 10.8F;
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
        return new String[] { "skull", "tooth", "tail_vertebrae", "shoulder", "ribcage", "pelvis", "neck_vertebrae", "hind_leg_bones", "front_leg_bones" };
    }
}
