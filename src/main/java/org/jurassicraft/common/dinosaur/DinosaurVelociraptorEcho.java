package org.jurassicraft.common.dinosaur;

import org.jurassicraft.common.api.IHybrid;
import org.jurassicraft.common.entity.EntityVelociraptorEcho;
import org.jurassicraft.common.entity.base.EntityDinosaur;
import org.jurassicraft.common.entity.base.EnumDiet;
import org.jurassicraft.common.entity.base.EnumSleepingSchedule;
import org.jurassicraft.common.period.EnumTimePeriod;

public class DinosaurVelociraptorEcho extends Dinosaur implements IHybrid
{
    @Override
    public String getName()
    {
        return "Echo";
    }

    @Override
    public Class<? extends EntityDinosaur> getDinosaurClass()
    {
        return EntityVelociraptorEcho.class;
    }

    @Override
    public EnumTimePeriod getPeriod()
    {
        return EnumTimePeriod.CRETACEOUS;
    }

    @Override
    public int getEggPrimaryColorMale()
    {
        return 0x665941;
    }

    @Override
    public int getEggSecondaryColorMale()
    {
        return 0x363E43;
    }

    @Override
    public int getEggPrimaryColorFemale()
    {
        return getEggPrimaryColorMale();
    }

    @Override
    public int getEggSecondaryColorFemale()
    {
        return getEggSecondaryColorMale();
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
        return 0.48;
    }

    @Override
    public double getAdultSpeed()
    {
        return 0.40;
    }

    public double getAttackSpeed()
    {
        return 0.50;
    }

    @Override
    public double getBabyStrength()
    {
        return 6;
    }

    @Override
    public double getAdultStrength()
    {
        return 21;
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
        return 1.45F;
    }

    @Override
    public float getBabySizeX()
    {
        return 0.4F;
    }

    @Override
    public float getBabySizeY()
    {
        return 0.5F;
    }

    @Override
    public float getAdultSizeX()
    {
        return 0.8F;
    }

    @Override
    public float getAdultSizeY()
    {
        return 1.8F;
    }

    @Override
    public Class[] getBaseGenes()
    {
        return new Class[] { DinosaurVelociraptor.class }; // TODO
    }

    @Override
    public Class[] getExtraGenes()
    {
        return new Class[] { DinosaurVelociraptor.class };
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
        return new String[] { "claw", "tooth", "skull" };
    }
}
