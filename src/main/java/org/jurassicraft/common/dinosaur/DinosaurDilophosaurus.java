package org.jurassicraft.common.dinosaur;

import org.jurassicraft.common.entity.EntityDilophosaurus;
import org.jurassicraft.common.entity.base.EntityDinosaur;
import org.jurassicraft.common.entity.base.EnumDiet;
import org.jurassicraft.common.entity.base.EnumSleepingSchedule;
import org.jurassicraft.common.period.EnumTimePeriod;

public class DinosaurDilophosaurus extends Dinosaur
{
    // TODO: Figure out all the entities properties

    @Override
    public String getName()
    {
        return "Dilophosaurus";
    }

    @Override
    public Class<? extends EntityDinosaur> getDinosaurClass()
    {
        return EntityDilophosaurus.class;
    }

    @Override
    public EnumTimePeriod getPeriod()
    {
        return EnumTimePeriod.JURASSIC;
    }

    @Override
    public int getEggPrimaryColorMale()
    {
        return 0x62702B;
    }

    @Override
    public int getEggSecondaryColorMale()
    {
        return 0x26292A;
    }

    @Override
    public int getEggPrimaryColorFemale()
    {
        return 0x5E6E2B;
    }

    @Override
    public int getEggSecondaryColorFemale()
    {
        return 0x2D221A;
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
        return 20;
    }

    @Override
    public int getMaximumAge()
    {
        return fromDays(40);
    }

    @Override
    public float getBabyEyeHeight()
    {
        return 0.45F;
    }

    @Override
    public float getAdultEyeHeight()
    {
        return 2.15F;
    }

    @Override
    public float getBabySizeX()
    {
        return 0.3F;
    }

    @Override
    public float getBabySizeY()
    {
        return 0.5F;
    }

    @Override
    public float getAdultSizeX()
    {
        return 1.25F;
    }

    @Override
    public float getAdultSizeY()
    {
        return 2.5F;
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
        return EnumSleepingSchedule.CREPUSCULAR;
    }

    @Override
    public String[] getBones()
    {
        return new String[] { "skull", "tooth", "arm_bones", "leg_bones", "neck", "pelvis", "ribcage", "shoulder", "tail_vertebrae" };
    }
}
