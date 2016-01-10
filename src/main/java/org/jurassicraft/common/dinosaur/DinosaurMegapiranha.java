package org.jurassicraft.common.dinosaur;

import org.jurassicraft.common.entity.EntityMegapiranha;
import org.jurassicraft.common.entity.base.EntityDinosaur;
import org.jurassicraft.common.entity.base.EnumDiet;
import org.jurassicraft.common.entity.base.EnumSleepingSchedule;
import org.jurassicraft.common.period.EnumTimePeriod;

public class DinosaurMegapiranha extends Dinosaur
{
    @Override
    public String getName()
    {
        return "Megapiranha";
    }

    @Override
    public Class<? extends EntityDinosaur> getDinosaurClass()
    {
        return EntityMegapiranha.class;
    }

    @Override
    public EnumTimePeriod getPeriod()
    {
        return EnumTimePeriod.CRETACEOUS; // TODO LATE MIOCENE
    }

    @Override
    public int getEggPrimaryColorMale()
    {
        return 0x17100B;
    }

    @Override
    public int getEggSecondaryColorMale()
    {
        return 0x645C54;
    }

    @Override
    public int getEggPrimaryColorFemale()
    {
        return 0x7D735D;
    }

    @Override
    public int getEggSecondaryColorFemale()
    {
        return 0x322922;
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
        return 0.62;
    }

    @Override
    public double getAttackSpeed()
    {
        return 0.50;
    }

    @Override
    public double getAdultSpeed()
    {
        return 0.50;
    }

    @Override
    public double getBabyStrength()
    {
        return 2;
    }

    @Override
    public double getAdultStrength()
    {
        return 6;
    }

    @Override
    public int getMaximumAge()
    {
        return fromDays(30);
    }

    @Override
    public float getBabyEyeHeight()
    {
        return 0.35F;
    }

    @Override
    public float getAdultEyeHeight()
    {
        return 0.35F;
    }

    @Override
    public float getBabySizeX()
    {
        return 0.15F;
    }

    @Override
    public float getBabySizeY()
    {
        return 0.15F;
    }

    @Override
    public float getAdultSizeX()
    {
        return 0.5F;
    }

    @Override
    public float getAdultSizeY()
    {
        return 0.7F;
    }

    @Override
    public boolean isMarineAnimal()
    {
        return true;
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
        return new String[] { "anal_fin", "body_fins", "caudal_fin", "dorsal_fin", "skull", "spine", "teeth" };
    }
}
