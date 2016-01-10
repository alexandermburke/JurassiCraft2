package org.jurassicraft.common.dinosaur;

import org.jurassicraft.common.entity.EntityTylosaurus;
import org.jurassicraft.common.entity.base.EntityDinosaur;
import org.jurassicraft.common.entity.base.EnumDiet;
import org.jurassicraft.common.entity.base.EnumSleepingSchedule;
import org.jurassicraft.common.period.EnumTimePeriod;

public class DinosaurTylosaurus extends Dinosaur
{
    @Override
    public String getName()
    {
        return "Tylosaurus";
    }

    @Override
    public Class<? extends EntityDinosaur> getDinosaurClass()
    {
        return EntityTylosaurus.class;
    }

    @Override
    public EnumTimePeriod getPeriod()
    {
        return EnumTimePeriod.CRETACEOUS;
    }

    @Override
    public int getEggPrimaryColorMale()
    {
        return 0x187D75;
    }

    @Override
    public int getEggSecondaryColorMale()
    {
        return 0x15544F;
    }

    @Override
    public int getEggPrimaryColorFemale()
    {
        return 0x798A8F;
    }

    @Override
    public int getEggSecondaryColorFemale()
    {
        return 0x101517;
    }

    @Override
    public double getBabyHealth()
    {
        return 20;
    }

    @Override
    public double getAdultHealth()
    {
        return 95;
    }

    @Override
    public double getBabySpeed()
    {
        return 0.72;
    }

    @Override
    public double getAttackSpeed()
    {
        return 0.50;
    }

    @Override
    public double getAdultSpeed()
    {
        return 0.75;
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
        return fromDays(60);
    }

    @Override
    public float getBabyEyeHeight()
    {
        return 0.35F;
    }

    @Override
    public float getAdultEyeHeight()
    {
        return 2.35F;
    }

    @Override
    public float getBabySizeX()
    {
        return 0.85F;
    }

    @Override
    public float getBabySizeY()
    {
        return 0.55F;
    }

    @Override
    public float getAdultSizeX()
    {
        return 4.5F;
    }

    @Override
    public float getAdultSizeY()
    {
        return 2.95F;
    }

    @Override
    public boolean isMarineAnimal()
    {
        return true;
    }

    @Override
    public boolean isMammal()
    {
        return true;
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
        return new String[] { "front_flipper", "hind_flipper", "inner_teeth", "ribcage", "skull", "spine", "tail_fluke", "tail_vertebrae", "tooth" };
    }
}
