package org.jurassicraft.common.dinosaur;

import org.jurassicraft.common.entity.EntityCompsognathus;
import org.jurassicraft.common.entity.base.EntityDinosaur;
import org.jurassicraft.common.entity.base.EnumDiet;
import org.jurassicraft.common.entity.base.EnumSleepingSchedule;
import org.jurassicraft.common.period.EnumTimePeriod;

public class DinosaurCompsognathus extends Dinosaur
{
    @Override
    public String getName()
    {
        return "Compsognathus";
    }

    @Override
    public Class<? extends EntityDinosaur> getDinosaurClass()
    {
        return EntityCompsognathus.class;
    }

    @Override
    public EnumTimePeriod getPeriod()
    {
        return EnumTimePeriod.JURASSIC;
    }

    @Override
    public int getEggPrimaryColorMale()
    {
        return 0x7B8042;
    }

    @Override
    public int getEggSecondaryColorMale()
    {
        return 0x454B3B;
    }

    @Override
    public int getEggPrimaryColorFemale()
    {
        return 0x7D734A;
    }

    @Override
    public int getEggSecondaryColorFemale()
    {
        return 0x484A3D;
    }

    @Override
    public double getBabyHealth()
    {
        return 2;
    }

    @Override
    public double getAdultHealth()
    {
        return 5;
    }

    @Override
    public double getBabySpeed()
    {
        return 0.3;
    }

    @Override
    public double getAttackSpeed()
    {
        return 0.50;
    }

    @Override
    public double getAdultSpeed()
    {
        return 0.2;
    }

    @Override
    public double getBabyStrength()
    {
        return 1;
    }

    @Override
    public double getAdultStrength()
    {
        return 3;
    }

    @Override
    public int getMaximumAge()
    {
        return fromDays(20);
    }

    @Override
    public float getBabyEyeHeight()
    {
        return 0.2F;
    }

    @Override
    public float getAdultEyeHeight()
    {
        return 0.5F;
    }

    @Override
    public float getBabySizeX()
    {
        return 0.1F;
    }

    @Override
    public float getBabySizeY()
    {
        return 0.25F;
    }

    @Override
    public float getAdultSizeX()
    {
        return 0.3F;
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
        return new String[] { "skull", "teeth", "tail_vertebrae", "shoulder", "ribcage", "pelvis", "neck_vertebrae", "leg_bones", "arm_bones" };
    }
}
