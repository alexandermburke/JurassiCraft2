package org.jurassicraft.common.dinosaur;

import org.jurassicraft.common.entity.EntityOrnithomimus;
import org.jurassicraft.common.entity.base.EntityDinosaur;
import org.jurassicraft.common.period.EnumTimePeriod;

public class DinosaurOrnithomimus extends Dinosaur
{
    @Override
    public String getName()
    {
        return "Ornithomimus";
    }

    @Override
    public Class<? extends EntityDinosaur> getDinosaurClass()
    {
        return EntityOrnithomimus.class;
    }

    @Override
    public EnumTimePeriod getPeriod()
    {
        return EnumTimePeriod.CRETACEOUS;
    }

    @Override
    public int getEggPrimaryColor()
    {
        return 0x92A8D5;
    }

    @Override
    public int getEggSecondaryColor()
    {
        return 0x475F93;
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
        return 0.52;
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
        return fromDays(35);
    }

    @Override
    public float getBabyEyeHeight()
    {
        return 0.58F;
    }

    @Override
    public float getAdultEyeHeight()
    {
        return 1.95F;
    }

    @Override
    public float getBabySizeX()
    {
        return 0.2F;
    }

    @Override
    public float getBabySizeY()
    {
        return 0.45F;
    }

    @Override
    public float getAdultSizeX()
    {
        return 1.0F;
    }

    @Override
    public float getAdultSizeY()
    {
        return 1.55F;
    }

    @Override
    public int getStorage()
    {
        return 27;
    }
}
