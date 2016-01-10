package org.jurassicraft.common.dinosaur;

import org.jurassicraft.common.entity.EntityParasaurolophus;
import org.jurassicraft.common.entity.base.EntityDinosaur;
import org.jurassicraft.common.entity.base.EnumDiet;
import org.jurassicraft.common.entity.base.EnumSleepingSchedule;
import org.jurassicraft.common.period.EnumTimePeriod;

public class DinosaurParasaurolophus extends Dinosaur
{
    // TODO: Figure out all the entities properties

    @Override
    public String getName()
    {
        return "Parasaurolophus";
    }

    @Override
    public Class<? extends EntityDinosaur> getDinosaurClass()
    {
        return EntityParasaurolophus.class;
    }

    @Override
    public EnumTimePeriod getPeriod()
    {
        return EnumTimePeriod.CRETACEOUS;
    }

    @Override
    public int getEggPrimaryColorMale()
    {
        return 0x9F8138;
    }

    @Override
    public int getEggSecondaryColorMale()
    {
        return 0x422306;
    }

    @Override
    public int getEggPrimaryColorFemale()
    {
        return 0x5F653E;
    }

    @Override
    public int getEggSecondaryColorFemale()
    {
        return 0x3C3F44;
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
        return 0.41;
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
        return 2.45F;
    }

    @Override
    public float getBabySizeX()
    {
        return 0.5F;
    }

    @Override
    public float getBabySizeY()
    {
        return 0.8F;
    }

    @Override
    public float getAdultSizeX()
    {
        return 2.5F;
    }

    @Override
    public float getAdultSizeY()
    {
        return 3.5F;
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
        return EnumSleepingSchedule.DIURNAL;
    }

    @Override
    public String[] getBones()
    {
        return new String[] { "ribcage", "front_leg_bones", "hind_leg_bones", "neck_vertebrae", "pelvis", "shoulder_bone", "skull", "tail_vertebrae", "teeth" };
    }
}
