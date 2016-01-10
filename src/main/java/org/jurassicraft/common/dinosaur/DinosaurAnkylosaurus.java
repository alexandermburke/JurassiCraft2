package org.jurassicraft.common.dinosaur;

import org.jurassicraft.common.entity.EntityAnkylosaurus;
import org.jurassicraft.common.entity.base.EntityDinosaur;
import org.jurassicraft.common.entity.base.EnumDiet;
import org.jurassicraft.common.entity.base.EnumSleepingSchedule;
import org.jurassicraft.common.period.EnumTimePeriod;

public class DinosaurAnkylosaurus extends Dinosaur
{
    @Override
    public String getName()
    {
        return "Ankylosaurus";
    }

    @Override
    public Class<? extends EntityDinosaur> getDinosaurClass()
    {
        return EntityAnkylosaurus.class;
    }

    @Override
    public EnumTimePeriod getPeriod()
    {
        return EnumTimePeriod.CRETACEOUS;
    }

    @Override
    public int getEggPrimaryColorMale()
    {
        return 0xAB9B82;
    }

    @Override
    public int getEggSecondaryColorMale()
    {
        return 0x7C6270;
    }

    @Override
    public int getEggPrimaryColorFemale()
    {
        return 0x554E45;
    }

    @Override
    public int getEggSecondaryColorFemale()
    {
        return 0x3F3935;
    }

    @Override
    public double getBabyHealth()
    {
        return 20;
    }

    @Override
    public double getAdultHealth()
    {
        return 120;
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
        return 5;
    }

    @Override
    public double getAdultStrength()
    {
        return 10;
    }

    @Override
    public int getMaximumAge()
    {
        return fromDays(50);
    }

    @Override
    public float getBabyEyeHeight()
    {
        return 0.4F;
    }

    @Override
    public float getAdultEyeHeight()
    {
        return 2F;
    }

    @Override
    public float getBabySizeX()
    {
        return 0.7F;
    }

    @Override
    public float getBabySizeY()
    {
        return 0.6F;
    }

    @Override
    public float getAdultSizeX()
    {
        return 3.0F;
    }

    @Override
    public float getAdultSizeY()
    {
        return 3F;
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
        return new String[] { "skull", "tooth", "tail_vertebrae", "tail_club", "shoulder", "ribcage", "pelvis", "neck_vertebrae", "hind_leg_bones", "front_leg_bones", "armor_plating" };
    }
}
