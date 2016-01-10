package org.jurassicraft.common.dinosaur;

import org.jurassicraft.common.entity.EntityOrnithomimus;
import org.jurassicraft.common.entity.base.EntityDinosaur;
import org.jurassicraft.common.entity.base.EnumDiet;
import org.jurassicraft.common.entity.base.EnumSleepingSchedule;
import org.jurassicraft.common.period.EnumTimePeriod;

public class DinosaurOrnithomimus extends Dinosaur
{
    @Override
    public String getName()
    {
        return "Ornithomimus";
    }

    @Override
    public Class<? extends EntityDinosaur> getDinosaurClass()
    {
        return EntityOrnithomimus.class;
    }

    @Override
    public EnumTimePeriod getPeriod()
    {
        return EnumTimePeriod.CRETACEOUS;
    }

    @Override
    public int getEggPrimaryColorMale()
    {
        return 0x92A8D5;
    }

    @Override
    public int getEggSecondaryColorMale()
    {
        return 0x475F93;
    }

    @Override
    public int getEggPrimaryColorFemale()
    {
        return 0xBDC4A9;
    }

    @Override
    public int getEggSecondaryColorFemale()
    {
        return 0x7F91C1;
    }

    @Override
    public double getBabyHealth()
    {
        return 16;
    }

    @Override
    public double getAdultHealth()
    {
        return 65;
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
        return 0.58F;
    }

    @Override
    public float getAdultEyeHeight()
    {
        return 1.95F;
    }

    @Override
    public float getBabySizeX()
    {
        return 0.2F;
    }

    @Override
    public float getBabySizeY()
    {
        return 0.45F;
    }

    @Override
    public float getAdultSizeX()
    {
        return 1.0F;
    }

    @Override
    public float getAdultSizeY()
    {
        return 1.55F;
    }

    @Override
    public int getStorage()
    {
        return 27;
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
        return new String[] { "arm_bones", "foot_bones", "leg_bones", "neck_vertebrae", "pelvis", "ribcage", "shoulder", "skull", "tail_vertebrae" };
    }
}
