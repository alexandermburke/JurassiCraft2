package org.jurassicraft.common.dinosaur;

import org.jurassicraft.common.entity.EntityCearadactylus;
import org.jurassicraft.common.entity.base.EntityDinosaur;
import org.jurassicraft.common.entity.base.EnumDiet;
import org.jurassicraft.common.entity.base.EnumSleepingSchedule;
import org.jurassicraft.common.period.EnumTimePeriod;

public class DinosaurCearadactylus extends Dinosaur
{
    @Override
    public String getName()
    {
        return "Cearadactylus";
    }

    @Override
    public Class<? extends EntityDinosaur> getDinosaurClass()
    {
        return EntityCearadactylus.class;
    }

    @Override
    public EnumTimePeriod getPeriod()
    {
        return EnumTimePeriod.CRETACEOUS;
    }

    @Override
    public int getEggPrimaryColorMale()
    {
        return 0x64A0B3;
    }

    @Override
    public int getEggSecondaryColorMale()
    {
        return 0x3B3937;
    }

    @Override
    public int getEggPrimaryColorFemale()
    {
        return 0xB55252;
    }

    @Override
    public int getEggSecondaryColorFemale()
    {
        return 0x4C423A;
    }

    @Override
    public double getBabyHealth()
    {
        return 10;
    }

    @Override
    public double getAdultHealth()
    {
        return 20;
    }

    @Override
    public double getBabySpeed()
    {
        return 0.46;
    }

    @Override
    public double getAttackSpeed()
    {
        return 0.17;
    }

    @Override
    public double getAdultSpeed()
    {
        return 0.30;
    }

    @Override
    public double getBabyStrength()
    {
        return 1;
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
        return 0.45F;
    }

    @Override
    public float getAdultEyeHeight()
    {
        return 1.45F;
    }

    @Override
    public float getBabySizeX()
    {
        return 0.35F;
    }

    @Override
    public float getBabySizeY()
    {
        return 0.45F;
    }

    @Override
    public float getAdultSizeX()
    {
        return 1.5F;
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
        return new String[] { "skull", "teeth", "wing_bones", "tail_vertebrae", "leg_bones", "pelvis" };
    }
}
