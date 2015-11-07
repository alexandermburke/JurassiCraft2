package org.jurassicraft.common.dinosaur;

import org.jurassicraft.common.entity.EntityCoelacanth;
import org.jurassicraft.common.entity.base.EntityDinosaur;
import org.jurassicraft.common.period.EnumTimePeriod;

public class DinosaurCoelacanth extends Dinosaur
{
    // TODO: Figure out all the entities properties

    @Override
    public String getName()
    {
        return "Coelacanth";
    }

    @Override
    public Class<? extends EntityDinosaur> getDinosaurClass()
    {
        return EntityCoelacanth.class;
    }

    @Override
    public EnumTimePeriod getPeriod()
    {
        return EnumTimePeriod.DEVONIAN;
    }

    @Override
    public int getEggPrimaryColorMale()
    {
        return 0x3C4B65;
    }

    @Override
    public int getEggSecondaryColorMale()
    {
        return 0x737E96;
    }

    @Override
    public int getEggPrimaryColorFemale()
    {
        return 0x4C4A3A;
    }

    @Override
    public int getEggSecondaryColorFemale()
    {
        return 0x7C775E;
    }

    @Override
    public double getBabyHealth()
    {
        return 10;
    }

    @Override
    public double getAdultHealth()
    {
        return 40;
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
        return 6;
    }

    @Override
    public double getAdultStrength()
    {
        return 36;
    }

    @Override
    public double getBabyKnockback()
    {
        return 0.3;
    }

    @Override
    public double getAdultKnockback()
    {
        return 0.6;
    }

    @Override
    public int getMaximumAge()
    {
        return fromDays(30);
    }

    @Override
    public float getBabyEyeHeight()
    {
        return 0.2F;
    }

    @Override
    public float getAdultEyeHeight()
    {
        return 0.6F;
    }

    @Override
    public float getBabySizeX()
    {
        return 0.3F;
    }

    @Override
    public float getBabySizeY()
    {
        return 0.3F;
    }

    @Override
    public float getAdultSizeX()
    {
        return 1.2F;
    }

    @Override
    public float getAdultSizeY()
    {
        return 1.0F;
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
}
