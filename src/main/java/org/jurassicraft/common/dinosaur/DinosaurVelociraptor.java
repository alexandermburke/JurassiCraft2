package org.jurassicraft.common.dinosaur;

import org.jurassicraft.common.entity.EntityVelociraptor;
import org.jurassicraft.common.entity.base.EntityDinosaur;
import org.jurassicraft.common.entity.base.EnumDiet;
import org.jurassicraft.common.entity.base.EnumSleepingSchedule;
import org.jurassicraft.common.period.EnumTimePeriod;

public class DinosaurVelociraptor extends Dinosaur
{
    @Override
    public String getName()
    {
        return "Velociraptor";
    }

    @Override
    public Class<? extends EntityDinosaur> getDinosaurClass()
    {
        return EntityVelociraptor.class;
    }

    @Override
    public EnumTimePeriod getPeriod()
    {
        return EnumTimePeriod.CRETACEOUS;
    }

    @Override
    public int getEggPrimaryColorMale()
    {
        return 0xB17041;
    }

    @Override
    public int getEggSecondaryColorMale()
    {
        return 0x3B1505;
    }

    @Override
    public int getEggPrimaryColorFemale()
    {
        return 0x91765D;
    }

    @Override
    public int getEggSecondaryColorFemale()
    {
        return 0x5A4739;
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
    public double getAdultSpeed()
    {
        return 0.40;
    }

    public double getAttackSpeed()
    {
        return 0.50;
    }

    @Override
    public double getBabyStrength()
    {
        return 6;
    }

    @Override
    public double getAdultStrength()
    {
        return 21;
    }

    @Override
    public int getMaximumAge()
    {
        return fromDays(45);
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
        return 0.4F;
    }

    @Override
    public float getBabySizeY()
    {
        return 0.5F;
    }

    @Override
    public float getAdultSizeX()
    {
        return 0.8F;
    }

    @Override
    public float getAdultSizeY()
    {
        return 1.8F;
    }

    @Override
    public int getStorage()
    {
        return 27;
    }

    @Override
    public int getOverlayCount()
    {
        return 1;
    }

    @Override
    public boolean useAllGrowthStages()
    {
        return true;
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
        return new String[] { "claw", "tooth", "skull" };
    }
}
