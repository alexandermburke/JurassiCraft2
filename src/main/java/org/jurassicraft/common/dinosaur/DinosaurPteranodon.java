package org.jurassicraft.common.dinosaur;

import org.jurassicraft.common.entity.EntityPteranodon;
import org.jurassicraft.common.entity.base.EntityDinosaur;
import org.jurassicraft.common.entity.base.EnumDiet;
import org.jurassicraft.common.entity.base.EnumSleepingSchedule;
import org.jurassicraft.common.period.EnumTimePeriod;

public class DinosaurPteranodon extends Dinosaur
{
    // TODO: Figure out all the entities properties

    @Override
    public String getName()
    {
        return "Pteranodon";
    }

    @Override
    public Class<? extends EntityDinosaur> getDinosaurClass()
    {
        return EntityPteranodon.class;
    }

    @Override
    public EnumTimePeriod getPeriod()
    {
        return EnumTimePeriod.CRETACEOUS;
    }

    @Override
    public int getEggPrimaryColorMale()
    {
        return 0x57504C;
    }

    @Override
    public int getEggSecondaryColorMale()
    {
        return 0x24383F;
    }

    @Override
    public int getEggPrimaryColorFemale()
    {
        return 0x535F65;
    }

    @Override
    public int getEggSecondaryColorFemale()
    {
        return 0x56312C;
    }

    @Override
    public double getBabyHealth()
    {
        return 16;
    }

    @Override
    public double getAdultHealth()
    {
        return 55;
    }

    @Override
    public double getBabySpeed()
    {
        return 0.46;
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
        return 1.6F;
    }

    @Override
    public float getBabySizeX()
    {
        return 0.8F;
    }

    @Override
    public float getBabySizeY()
    {
        return 0.6F;
    }

    @Override
    public float getAdultSizeX()
    {
        return 2.0F;
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
        return new String[] { "leg_bones", "neck_vertebrae", "pelvis", "ribcage", "skull", "tail_vertebrae", "wing_bones" };
    }
}
