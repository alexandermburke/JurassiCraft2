package org.jurassicraft.common.dinosaur;

import org.jurassicraft.common.entity.EntityDunkleosteus;
import org.jurassicraft.common.entity.base.EntityDinosaur;
import org.jurassicraft.common.entity.base.EnumDiet;
import org.jurassicraft.common.entity.base.EnumSleepingSchedule;
import org.jurassicraft.common.period.EnumTimePeriod;

public class DinosaurDunkleosteus extends Dinosaur
{
    @Override
    public String getName()
    {
        return "Dunkleosteus";
    }

    @Override
    public Class<? extends EntityDinosaur> getDinosaurClass()
    {
        return EntityDunkleosteus.class;
    }

    @Override
    public EnumTimePeriod getPeriod()
    {
        return EnumTimePeriod.DEVONIAN;
    }

    @Override
    public int getEggPrimaryColorMale()
    {
        return 0xA89B8C;
    }

    @Override
    public int getEggSecondaryColorMale()
    {
        return 0x753A28;
    }

    @Override
    public int getEggPrimaryColorFemale()
    {
        return 0xA6A588;
    }

    @Override
    public int getEggSecondaryColorFemale()
    {
        return 0x785F2A;
    }

    @Override
    public double getBabyHealth()
    {
        return 15;
    }

    @Override
    public double getAdultHealth()
    {
        return 60;
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
        return 0.45;
    }

    @Override
    public double getBabyStrength()
    {
        return 10;
    }

    @Override
    public double getAdultStrength()
    {
        return 40;
    }

    @Override
    public int getMaximumAge()
    {
        return fromDays(30);
    }

    @Override
    public float getBabyEyeHeight()
    {
        return 0.3F;
    }

    @Override
    public float getAdultEyeHeight()
    {
        return 1.2F;
    }

    @Override
    public float getBabySizeX()
    {
        return 0.8F;
    }

    @Override
    public float getBabySizeY()
    {
        return 0.5F;
    }

    @Override
    public float getAdultSizeX()
    {
        return 2.7F;
    }

    @Override
    public float getAdultSizeY()
    {
        return 2.0F;
    }

    @Override
    public boolean isMarineAnimal()
    {
        return true;
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
        return new String[] { "skull", "mouth_plates" };
    }
}
