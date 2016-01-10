package org.jurassicraft.common.dinosaur;

import org.jurassicraft.common.entity.EntityBaryonyx;
import org.jurassicraft.common.entity.base.EntityDinosaur;
import org.jurassicraft.common.entity.base.EnumDiet;
import org.jurassicraft.common.entity.base.EnumSleepingSchedule;
import org.jurassicraft.common.period.EnumTimePeriod;

public class DinosaurBaryonyx extends Dinosaur
{
    @Override
    public String getName()
    {
        return "Baryonyx";
    }

    @Override
    public Class<? extends EntityDinosaur> getDinosaurClass()
    {
        return EntityBaryonyx.class;
    }

    @Override
    public EnumTimePeriod getPeriod()
    {
        return EnumTimePeriod.CRETACEOUS;
    }

    @Override
    public int getEggPrimaryColorMale()
    {
        return 0x567F4F;
    }

    @Override
    public int getEggSecondaryColorMale()
    {
        return 0x13270F;
    }

    @Override
    public int getEggPrimaryColorFemale()
    {
        return 0x9D9442;
    }

    @Override
    public int getEggSecondaryColorFemale()
    {
        return 0x2A2405;
    }

    @Override
    public double getBabyHealth()
    {
        return 5;
    }

    @Override
    public double getAdultHealth()
    {
        return 30;
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
        return fromDays(55);
    }

    @Override
    public float getBabyEyeHeight()
    {
        return 0.55F;
    }

    @Override
    public float getAdultEyeHeight()
    {
        return 2.95F;
    }

    @Override
    public float getBabySizeX()
    {
        return 0.35F;
    }

    @Override
    public float getBabySizeY()
    {
        return 0.55F;
    }

    @Override
    public float getAdultSizeX()
    {
        return 1.5F;
    }

    @Override
    public float getAdultSizeY()
    {
        return 2.95F;
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
        return new String[] { "skull", "tooth", "tail_vertebrae", "shoulder", "ribcage", "pelvis", "leg_vertebrae", "leg_bones", "claw", "arm_bones" };
    }
}
