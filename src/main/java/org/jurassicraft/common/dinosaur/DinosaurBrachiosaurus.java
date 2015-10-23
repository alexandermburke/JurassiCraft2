package org.jurassicraft.common.dinosaur;

import org.jurassicraft.common.entity.EntityBrachiosaurus;
import org.jurassicraft.common.entity.base.EntityDinosaur;
import org.jurassicraft.common.entity.base.EnumGrowthStage;
import org.jurassicraft.common.period.EnumTimePeriod;

public class DinosaurBrachiosaurus extends Dinosaur
{
    private String[] maleTextures;
    private String[] femaleTextures;

    public DinosaurBrachiosaurus()
    {
        this.maleTextures = new String[] { getDinosaurTexture("male") };
        this.femaleTextures = new String[] { getDinosaurTexture("female") };
    }

    // TODO: Figure out all the entities properties

    @Override
    public String getName()
    {
        return "Brachiosaurus";
    }

    @Override
    public Class<? extends EntityDinosaur> getDinosaurClass()
    {
        return EntityBrachiosaurus.class;
    }

    @Override
    public EnumTimePeriod getPeriod()
    {
        return EnumTimePeriod.JURASSIC;
    }

    @Override
    public int getEggPrimaryColor()
    {
        return 0x87987F;
    }

    @Override
    public int getEggSecondaryColor()
    {
        return 0x607343;
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
        return fromDays(85);
    }

    @Override
    public String[] getMaleTextures(EnumGrowthStage stage)
    {
        return maleTextures;
    }

    @Override
    public String[] getFemaleTextures(EnumGrowthStage stage)
    {
        return femaleTextures;
    }

    @Override
    public float getBabyEyeHeight()
    {
        return 2.2F;
    }

    @Override
    public float getAdultEyeHeight()
    {
        return 18.4F;
    }

    @Override
    public float getBabySizeX()
    {
        return 0.9F;
    }

    @Override
    public float getBabySizeY()
    {
        return 1.5F;
    }

    @Override
    public float getAdultSizeX()
    {
        return 6.5F;
    }

    @Override
    public float getAdultSizeY()
    {
        return 10.8F;
    }

    @Override
    public int getStorage()
    {
        return 54;
    }
}
