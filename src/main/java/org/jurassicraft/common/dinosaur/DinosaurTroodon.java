package org.jurassicraft.common.dinosaur;

import org.jurassicraft.common.entity.EntityTroodon;
import org.jurassicraft.common.entity.base.EntityDinosaur;
import org.jurassicraft.common.entity.base.EnumDiet;
import org.jurassicraft.common.entity.base.EnumSleepingSchedule;
import org.jurassicraft.common.period.EnumTimePeriod;

public class DinosaurTroodon extends Dinosaur
{
    @Override
    public String getName()
    {
        return "Troodon";
    }

    @Override
    public Class<? extends EntityDinosaur> getDinosaurClass()
    {
        return EntityTroodon.class;
    }

    @Override
    public EnumTimePeriod getPeriod()
    {
        return EnumTimePeriod.CRETACEOUS;
    }

    @Override
    public int getEggPrimaryColorMale()
    {
        return 0x9DAA7A;
    }

    @Override
    public int getEggSecondaryColorMale()
    {
        return 0x636B67;
    }

    @Override
    public int getEggPrimaryColorFemale()
    {
        return 0xA2A67C;
    }

    @Override
    public int getEggSecondaryColorFemale()
    {
        return 0x646D66;
    }

    @Override
    public double getBabyHealth()
    {
        return 16;
    }

    @Override
    public double getAdultHealth()
    {
        return 30;
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
        return fromDays(35);
    }

    @Override
    public float getBabyEyeHeight()
    {
        return 0.3F;
    }

    @Override
    public float getAdultEyeHeight()
    {
        return 0.95F;
    }

    @Override
    public float getBabySizeX()
    {
        return 0.3F;
    }

    @Override
    public float getBabySizeY()
    {
        return 0.4F;
    }

    @Override
    public float getAdultSizeX()
    {
        return 0.7F;
    }

    @Override
    public float getAdultSizeY()
    {
        return 0.95F;
    }

    @Override
    public int getStorage()
    {
        return 18;
    }

    @Override
    public EnumDiet getDiet()
    {
        return EnumDiet.HERBIVORE;
    }

    @Override
    public EnumSleepingSchedule getSleepingSchedule()
    {
        return EnumSleepingSchedule.NOCTURNAL;
    }

    @Override
    public String[] getBones()
    {
        return new String[] { "arm_bones", "foot_bones", "leg_bones", "neck_vertebrae", "pelvis", "ribcage", "shoulder", "skull", "tail_vertebrae", "tooth" };
    }
}
