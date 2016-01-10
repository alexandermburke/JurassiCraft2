package org.jurassicraft.common.dinosaur;

import org.jurassicraft.common.entity.EntityTherizinosaurus;
import org.jurassicraft.common.entity.base.EntityDinosaur;
import org.jurassicraft.common.entity.base.EnumDiet;
import org.jurassicraft.common.entity.base.EnumSleepingSchedule;
import org.jurassicraft.common.period.EnumTimePeriod;

public class DinosaurTherizinosaurus extends Dinosaur
{
    @Override
    public String getName()
    {
        return "Therizinosaurus";
    }

    @Override
    public Class<? extends EntityDinosaur> getDinosaurClass()
    {
        return EntityTherizinosaurus.class;
    }

    @Override
    public EnumTimePeriod getPeriod()
    {
        return EnumTimePeriod.CRETACEOUS;
    }

    @Override
    public int getEggPrimaryColorMale()
    {
        return 0x787878;
    }

    @Override
    public int getEggSecondaryColorMale()
    {
        return 0x2B2B2B;
    }

    @Override
    public int getEggPrimaryColorFemale()
    {
        return 0x7F7F7F;
    }

    @Override
    public int getEggSecondaryColorFemale()
    {
        return 0x272727;
    }

    @Override
    public double getBabyHealth()
    {
        return 15;
    }

    @Override
    public double getAdultHealth()
    {
        return 75;
    }

    @Override
    public double getBabySpeed()
    {
        return 0.45;
    }

    @Override
    public double getAttackSpeed()
    {
        return 0.17;
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
        return fromDays(65);
    }

    @Override
    public float getBabyEyeHeight()
    {
        return 0.95F;
    }

    @Override
    public float getAdultEyeHeight()
    {
        return 5.85F;
    }

    @Override
    public float getBabySizeX()
    {
        return 0.65F;
    }

    @Override
    public float getBabySizeY()
    {
        return 1.0F;
    }

    @Override
    public float getAdultSizeX()
    {
        return 2.25F;
    }

    @Override
    public float getAdultSizeY()
    {
        return 5.95F;
    }

    @Override
    public int getStorage()
    {
        return 36;
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
        return new String[] { "skull", "teeth" };
    }
}
