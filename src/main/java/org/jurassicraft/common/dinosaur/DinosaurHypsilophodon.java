package org.jurassicraft.common.dinosaur;

import org.jurassicraft.common.entity.EntityHypsilophodon;
import org.jurassicraft.common.entity.base.EntityDinosaur;
import org.jurassicraft.common.entity.base.EnumDiet;
import org.jurassicraft.common.entity.base.EnumSleepingSchedule;
import org.jurassicraft.common.period.EnumTimePeriod;

public class DinosaurHypsilophodon extends Dinosaur
{
    // TODO: Figure out all the entities properties

    @Override
    public String getName()
    {
        return "Hypsilophodon";
    }

    @Override
    public Class<? extends EntityDinosaur> getDinosaurClass()
    {
        return EntityHypsilophodon.class;
    }

    @Override
    public EnumTimePeriod getPeriod()
    {
        return EnumTimePeriod.CRETACEOUS;
    }

    @Override
    public int getEggPrimaryColorMale()
    {
        return 0x7DAC78;
    }

    @Override
    public int getEggSecondaryColorMale()
    {
        return 0x3E6226;
    }

    @Override
    public int getEggPrimaryColorFemale()
    {
        return 0x799073;
    }

    @Override
    public int getEggSecondaryColorFemale()
    {
        return 0x33432F;
    }

    @Override
    public double getBabyHealth()
    {
        return 10;
    }

    @Override
    public double getAdultHealth()
    {
        return 25;
    }

    @Override
    public double getBabySpeed()
    {
        return 0.35;
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
        return fromDays(35);
    }

    @Override
    public float getBabyEyeHeight()
    {
        return 0.2F;
    }

    @Override
    public float getAdultEyeHeight()
    {
        return 0.7F;
    }

    @Override
    public float getBabySizeX()
    {
        return 0.2F;
    }

    @Override
    public float getBabySizeY()
    {
        return 0.25F;
    }

    @Override
    public float getAdultSizeX()
    {
        return 0.6F;
    }

    @Override
    public float getAdultSizeY()
    {
        return 0.85F;
    }

    @Override
    public int getStorage()
    {
        return 9;
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
        return new String[] { "skull", "tooth", "tail_vertebrae", "shoulder", "ribcage", "pelvis", "neck_vertebrae", "leg_bones", "arm_bones" };
    }
}
