package org.jurassicraft.common.dinosaur;

import org.jurassicraft.common.entity.EntityTyrannosaurus;
import org.jurassicraft.common.entity.base.EntityDinosaur;
import org.jurassicraft.common.entity.base.EnumDiet;
import org.jurassicraft.common.entity.base.EnumSleepingSchedule;
import org.jurassicraft.common.period.EnumTimePeriod;

public class DinosaurTyrannosaurus extends Dinosaur
{
    @Override
    public String getName()
    {
        return "Tyrannosaurus";
    }

    @Override
    public Class<? extends EntityDinosaur> getDinosaurClass()
    {
        return EntityTyrannosaurus.class;
    }

    @Override
    public EnumTimePeriod getPeriod()
    {
        return EnumTimePeriod.CRETACEOUS;
    }

    @Override
    public int getEggPrimaryColorMale()
    {
        return 0x6B6628;
    }

    @Override
    public int getEggSecondaryColorMale()
    {
        return 0x39363B;
    }

    @Override
    public int getEggPrimaryColorFemale()
    {
        return 0xBA997E;
    }

    @Override
    public int getEggSecondaryColorFemale()
    {
        return 0x7D5D48;
    }

    @Override
    public double getBabyHealth()
    {
        return 10;
    }

    @Override
    public double getAdultHealth()
    {
        return 80;
    }

    @Override
    public double getBabySpeed()
    {
        return 0.46;
    }

    @Override
    public double getAdultSpeed()
    {
        return 0.42;
    }

    @Override
    public double getAttackSpeed()
    {
        return 0.50;
    }

    @Override
    public double getBabyStrength()
    {
        return 5;
    }

    @Override
    public double getAdultStrength()
    {
        return 35;
    }

    @Override
    public int getMaximumAge()
    {
        return fromDays(60);
    }

    @Override
    public float getBabyEyeHeight()
    {
        return 0.6F;
    }

    @Override
    public float getAdultEyeHeight()
    {
        return 3.8F;
    }

    @Override
    public float getBabySizeX()
    {
        return 0.45F;
    }

    @Override
    public float getBabySizeY()
    {
        return 0.8F;
    }

    @Override
    public float getAdultSizeX()
    {
        return 4.5F;
    }

    @Override
    public float getAdultSizeY()
    {
        return 4F;
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
        return new String[] { "arm_bones", "foot_bones", "leg_bones", "neck_vertebrae", "pelvis", "ribcage", "shoulder_bone", "skull", "tail_vertebrae", "tooth" };
    }
}
