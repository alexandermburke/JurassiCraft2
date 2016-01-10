package org.jurassicraft.common.dinosaur;

import org.jurassicraft.common.entity.EntityMajungasaurus;
import org.jurassicraft.common.entity.base.EntityDinosaur;
import org.jurassicraft.common.entity.base.EnumDiet;
import org.jurassicraft.common.entity.base.EnumSleepingSchedule;
import org.jurassicraft.common.period.EnumTimePeriod;

public class DinosaurMajungasaurus extends Dinosaur
{
    // TODO: Figure out all the entities properties

    @Override
    public String getName()
    {
        return "Majungasaurus";
    }

    @Override
    public Class<? extends EntityDinosaur> getDinosaurClass()
    {
        return EntityMajungasaurus.class;
    }

    @Override
    public EnumTimePeriod getPeriod()
    {
        return EnumTimePeriod.CRETACEOUS;
    }

    @Override
    public int getEggPrimaryColorMale()
    {
        return 0xE6CC9B;
    }

    @Override
    public int getEggSecondaryColorMale()
    {
        return 0x7C8A7D;
    }

    @Override
    public int getEggPrimaryColorFemale()
    {
        return 0xE8CF9C;
    }

    @Override
    public int getEggSecondaryColorFemale()
    {
        return 0xADAC7E;
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
        return 0.48;
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
        return fromDays(45);
    }

    @Override
    public float getBabyEyeHeight()
    {
        return 0.6F;
    }

    @Override
    public float getAdultEyeHeight()
    {
        return 2.6F;
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
        return 2.25F;
    }

    @Override
    public float getAdultSizeY()
    {
        return 3.0F;
    }

    @Override
    public int getStorage()
    {
        return 36;
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
        return new String[] { "skull", "tooth" };
    }
}
