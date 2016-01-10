package org.jurassicraft.common.dinosaur;

import org.jurassicraft.common.entity.EntityHerrerasaurus;
import org.jurassicraft.common.entity.base.EntityDinosaur;
import org.jurassicraft.common.entity.base.EnumDiet;
import org.jurassicraft.common.entity.base.EnumSleepingSchedule;
import org.jurassicraft.common.period.EnumTimePeriod;

public class DinosaurHerrerasaurus extends Dinosaur
{
    @Override
    public String getName()
    {
        return "Herrerasaurus";
    }

    @Override
    public Class<? extends EntityDinosaur> getDinosaurClass()
    {
        return EntityHerrerasaurus.class;
    }

    @Override
    public EnumTimePeriod getPeriod()
    {
        return EnumTimePeriod.TRIASSIC;
    }

    @Override
    public int getEggPrimaryColorMale()
    {
        return 0x2B1919;
    }

    @Override
    public int getEggSecondaryColorMale()
    {
        return 0x932C23;
    }

    @Override
    public int getEggPrimaryColorFemale()
    {
        return 0x7C6F44;
    }

    @Override
    public int getEggSecondaryColorFemale()
    {
        return 0x2B2721;
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
        return 2.25F;
    }

    @Override
    public float getBabySizeX()
    {
        return 0.4F;
    }

    @Override
    public float getBabySizeY()
    {
        return 0.55F;
    }

    @Override
    public float getAdultSizeX()
    {
        return 1.8F;
    }

    @Override
    public float getAdultSizeY()
    {
        return 2.55F;
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
        return new String[] { "skull", "tooth", "tail_vertebrae", "shoulder", "ribcage", "pelvis", "neck_vertebrae", "leg_bones", "foot_bones", "arm_bones" };
    }
}
