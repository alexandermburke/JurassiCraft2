package org.jurassicraft.common.dinosaur;

import org.jurassicraft.common.entity.EntityLambeosaurus;
import org.jurassicraft.common.entity.base.EntityDinosaur;
import org.jurassicraft.common.entity.base.EnumDiet;
import org.jurassicraft.common.entity.base.EnumSleepingSchedule;
import org.jurassicraft.common.period.EnumTimePeriod;

public class DinosaurLambeosaurus extends Dinosaur
{
    @Override
    public String getName()
    {
        return "Lambeosaurus";
    }

    @Override
    public Class<? extends EntityDinosaur> getDinosaurClass()
    {
        return EntityLambeosaurus.class;
    }

    @Override
    public EnumTimePeriod getPeriod()
    {
        return EnumTimePeriod.CRETACEOUS;
    }

    @Override
    public int getEggPrimaryColorMale()
    {
        return 0x82947A;
    }

    @Override
    public int getEggSecondaryColorMale()
    {
        return 0x2F3129;
    }

    @Override
    public int getEggPrimaryColorFemale()
    {
        return 0x898969;
    }

    @Override
    public int getEggSecondaryColorFemale()
    {
        return 0x464C3A;
    }

    @Override
    public double getBabyHealth()
    {
        return 18;
    }

    @Override
    public double getAdultHealth()
    {
        return 75;
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
        return 0.41;
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
        return fromDays(50);
    }

    @Override
    public float getBabyEyeHeight()
    {
        return 0.5F;
    }

    @Override
    public float getAdultEyeHeight()
    {
        return 3.45F;
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
        return 2.8F;
    }

    @Override
    public float getAdultSizeY()
    {
        return 4.25F;
    }

    @Override
    public int getStorage()
    {
        return 45;
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
        return new String[] { "skull", "cheek_teeth", "shoulder", "tail_vertebrae", "ribcage", "pelvis", "neck_vertebrae", "hind_leg_bones", "front_leg_bones" };
    }
}