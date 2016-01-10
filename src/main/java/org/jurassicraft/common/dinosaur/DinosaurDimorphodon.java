package org.jurassicraft.common.dinosaur;

import org.jurassicraft.common.entity.EntityDimorphodon;
import org.jurassicraft.common.entity.base.EntityDinosaur;
import org.jurassicraft.common.entity.base.EnumDiet;
import org.jurassicraft.common.entity.base.EnumSleepingSchedule;
import org.jurassicraft.common.period.EnumTimePeriod;

public class DinosaurDimorphodon extends Dinosaur
{
    @Override
    public String getName()
    {
        return "Dimorphodon";
    }

    @Override
    public Class<? extends EntityDinosaur> getDinosaurClass()
    {
        return EntityDimorphodon.class;
    }

    @Override
    public EnumTimePeriod getPeriod()
    {
        return EnumTimePeriod.JURASSIC;
    }

    @Override
    public int getEggPrimaryColorMale()
    {
        return 0xB2AC94;
    }

    @Override
    public int getEggSecondaryColorMale()
    {
        return 0x636644;
    }

    @Override
    public int getEggPrimaryColorFemale()
    {
        return 0xBDB4A9;
    }

    @Override
    public int getEggSecondaryColorFemale()
    {
        return 0x726B57;
    }

    @Override
    public double getBabyHealth()
    {
        return 5;
    }

    @Override
    public double getAdultHealth()
    {
        return 10;
    }

    @Override
    public double getBabySpeed()
    {
        return 0.35;
    }

    @Override
    public double getAttackSpeed()
    {
        return 0.17;
    }

    @Override
    public double getAdultSpeed()
    {
        return 0.30;
    }

    @Override
    public double getBabyStrength()
    {
        return 1;
    }

    @Override
    public double getAdultStrength()
    {
        return 5;
    }

    @Override
    public int getMaximumAge()
    {
        return fromDays(35);
    }

    @Override
    public float getBabyEyeHeight()
    {
        return 0.25F;
    }

    @Override
    public float getAdultEyeHeight()
    {
        return 0.7F;
    }

    @Override
    public float getBabySizeX()
    {
        return 0.25F;
    }

    @Override
    public float getBabySizeY()
    {
        return 0.25F;
    }

    @Override
    public float getAdultSizeX()
    {
        return 0.5F;
    }

    @Override
    public float getAdultSizeY()
    {
        return 0.75F;
    }

    @Override
    public int getStorage()
    {
        return 9;
    }

    @Override
    public int getOverlayCount()
    {
        return 5;
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
        return new String[] { "skull", "teeth", "leg_bones", "neck", "ribs_and_spine", "shoulder_blade", "tail_vertebrae", "wing_bones" };
    }
}
