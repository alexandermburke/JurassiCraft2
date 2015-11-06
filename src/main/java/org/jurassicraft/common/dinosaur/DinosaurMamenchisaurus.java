package org.jurassicraft.common.dinosaur;

import org.jurassicraft.common.entity.EntityMamenchisaurus;
import org.jurassicraft.common.entity.base.EntityDinosaur;
import org.jurassicraft.common.period.EnumTimePeriod;

public class DinosaurMamenchisaurus extends Dinosaur
{
    @Override
    public String getName()
    {
        return "Mamenchisaurus";
    }

    @Override
    public Class<? extends EntityDinosaur> getDinosaurClass()
    {
        return EntityMamenchisaurus.class;
    }

    @Override
    public EnumTimePeriod getPeriod()
    {
        return EnumTimePeriod.JURASSIC;
    }

    @Override
    public int getEggPrimaryColor()
    {
        return 0xD1BA49;
    }

    @Override
    public int getEggSecondaryColor()
    {
        return 0x909B1D;
    }

    @Override
    public double getBabyHealth()
    {
        return 25;
    }

    @Override
    public double getAdultHealth()
    {
        return 140;
    }

    @Override
    public double getBabySpeed()
    {
        return 0.32;
    }

    @Override
    public double getAttackSpeed()
    {
        return 0.17;
    }

    @Override
    public int getStorage()
    {
        return 52;
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
        return fromDays(95);
    }

    @Override
    public float getBabyEyeHeight()
    {
        return 1.55F;
    }

    @Override
    public float getAdultEyeHeight()
    {
        return 13.95F;
    }

    @Override
    public float getBabySizeX()
    {
        return 0.75F;
    }

    @Override
    public float getBabySizeY()
    {
        return 0.75F;
    }

    @Override
    public float getAdultSizeX()
    {
        return 5.5F;
    }

    @Override
    public float getAdultSizeY()
    {
        return 5.95F;
    }
}
