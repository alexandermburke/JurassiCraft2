package org.jurassicraft.common.dinosaur;

import org.jurassicraft.common.entity.EntityLudodactylus;
import org.jurassicraft.common.entity.base.EntityDinosaur;
import org.jurassicraft.common.entity.base.EnumDiet;
import org.jurassicraft.common.entity.base.EnumSleepingSchedule;
import org.jurassicraft.common.period.EnumTimePeriod;

public class DinosaurLudodactylus extends Dinosaur
{
    @Override
    public String getName()
    {
        return "Ludodactylus";
    }

    @Override
    public Class<? extends EntityDinosaur> getDinosaurClass()
    {
        return EntityLudodactylus.class;
    }

    @Override
    public EnumTimePeriod getPeriod()
    {
        return EnumTimePeriod.CRETACEOUS;
    }

    @Override
    public int getEggPrimaryColorMale()
    {
        return 0x565656;
    }

    @Override
    public int getEggSecondaryColorMale()
    {
        return 0x1D1E20;
    }

    @Override
    public int getEggPrimaryColorFemale()
    {
        return 0x884D3E;
    }

    @Override
    public int getEggSecondaryColorFemale()
    {
        return 0x35302B;
    }

    @Override
    public double getBabyHealth()
    {
        return 16;
    }

    @Override
    public double getAdultHealth()
    {
        return 40;
    }

    @Override
    public double getBabySpeed()
    {
        return 0.47;
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
        return fromDays(40);
    }

    @Override
    public float getBabyEyeHeight()
    {
        return 0.48F;
    }

    @Override
    public float getAdultEyeHeight()
    {
        return 1.25F;
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
        return 1.0F;
    }

    @Override
    public float getAdultSizeY()
    {
        return 1.35F;
    }

    @Override
    public int getStorage()
    {
        return 18;
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
        return new String[] { "leg_bones", "pelvis", "skull", "tail_vertebrae", "teeth", "wing_bones" };
    }
}
