package org.jurassicraft.common.dinosaur;

import org.jurassicraft.common.entity.EntityCarnotaurus;
import org.jurassicraft.common.entity.base.EntityDinosaur;
import org.jurassicraft.common.entity.base.EnumDiet;
import org.jurassicraft.common.entity.base.EnumSleepingSchedule;
import org.jurassicraft.common.period.EnumTimePeriod;

public class DinosaurCarnotaurus extends Dinosaur
{
    // TODO: Figure out all the entities properties

    @Override
    public String getName()
    {
        return "Carnotaurus";
    }

    @Override
    public Class<? extends EntityDinosaur> getDinosaurClass()
    {
        return EntityCarnotaurus.class;
    }

    @Override
    public EnumTimePeriod getPeriod()
    {
        return EnumTimePeriod.CRETACEOUS;
    }

    @Override
    public int getEggPrimaryColorMale()
    {
        return 0xA2996E;
    }

    @Override
    public int getEggSecondaryColorMale()
    {
        return 0x545338;
    }

    @Override
    public int getEggPrimaryColorFemale()
    {
        return 0x9C8E6A;
    }

    @Override
    public int getEggSecondaryColorFemale()
    {
        return 0x635639;
    }

    @Override
    public double getBabyHealth()
    {
        return 10;
    }

    @Override
    public double getAdultHealth()
    {
        return 30;
    }

    @Override
    public double getBabySpeed()
    {
        return 0.42;
    }

    @Override
    public double getAttackSpeed()
    {
        return 0.50;
    }

    @Override
    public double getAdultSpeed()
    {
        return 0.30;
    }

    @Override
    public double getBabyStrength()
    {
        return 5;
    }

    @Override
    public double getAdultStrength()
    {
        return 20;
    }

    @Override
    public int getMaximumAge()
    {
        return fromDays(45);
    }

    @Override
    public float getBabyEyeHeight()
    {
        return 0.4F;
    }

    @Override
    public float getAdultEyeHeight()
    {
        return 2.4F;
    }

    @Override
    public float getBabySizeX()
    {
        return 0.45F;
    }

    @Override
    public float getBabySizeY()
    {
        return 0.6F;
    }

    @Override
    public float getAdultSizeX()
    {
        return 2.25F;
    }

    @Override
    public float getAdultSizeY()
    {
        return 2.8F;
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
        return EnumSleepingSchedule.CREPUSCULAR;
    }

    @Override
    public String[] getBones()
    {
        return new String[] { "skull", "tooth" };
    }
}
