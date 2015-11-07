package org.jurassicraft.common.dinosaur;

import org.jurassicraft.common.entity.EntityApatosaurus;
import org.jurassicraft.common.entity.base.EntityDinosaur;
import org.jurassicraft.common.period.EnumTimePeriod;

public class DinosaurApatosaurus extends Dinosaur
{
    // TODO: Figure out all the entities properties

    @Override
    public String getName()
    {
        return "Apatosaurus";
    }

    @Override
    public Class<? extends EntityDinosaur> getDinosaurClass()
    {
        return EntityApatosaurus.class;
    }

    @Override
    public EnumTimePeriod getPeriod()
    {
        return EnumTimePeriod.JURASSIC;
    }

    @Override
    public int getEggPrimaryColorMale()
    {
        return 0xA79F93;
    }

    @Override
    public int getEggSecondaryColorMale()
    {
        return 0x987664;
    }

    @Override
    public int getEggPrimaryColorFemale()
    {
        return 0x7E7D70;
    }

    @Override
    public int getEggSecondaryColorFemale()
    {
        return 0x30343E;
    }

    @Override
    public double getBabyHealth()
    {
        return 20;
    }

    @Override
    public double getAdultHealth()
    {
        return 120;
    }

    @Override
    public double getBabySpeed()
    {
        return 0.32;
    }

    @Override
    public double getAttackSpeed()
    {
        return 0.50;
    }

    @Override
    public double getAdultSpeed()
    {
        return 0.25;
    }

    @Override
    public double getBabyStrength()
    {
        return 6;
    }

    @Override
    public double getAdultStrength()
    {
        return 2;
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
        return fromDays(80);
    }

    @Override
    public float getBabyEyeHeight()
    {
        return 0.9F;
    }

    @Override
    public float getAdultEyeHeight()
    {
        return 6.8F;
    }

    @Override
    public float getBabySizeX()
    {
        return 0.9F;
    }

    @Override
    public float getBabySizeY()
    {
        return 1.0F;
    }

    @Override
    public float getAdultSizeX()
    {
        return 6.5F;
    }

    @Override
    public float getAdultSizeY()
    {
        return 6.8F;
    }

    @Override
    public int getStorage()
    {
        return 54;
    }
}
