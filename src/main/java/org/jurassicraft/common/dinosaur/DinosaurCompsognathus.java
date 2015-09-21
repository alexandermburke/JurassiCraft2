package org.jurassicraft.common.dinosaur;

import org.jurassicraft.common.entity.EntityCompsognathus;
import org.jurassicraft.common.entity.base.EntityDinosaur;
import org.jurassicraft.common.entity.base.EnumGrowthStage;
import org.jurassicraft.common.period.EnumTimePeriod;

public class DinosaurCompsognathus extends Dinosaur
{
    private String[] maleTextures;
    private String[] femaleTextures;

    public DinosaurCompsognathus()
    {
        this.maleTextures = new String[]{getDinosaurTexture("male")};
        this.femaleTextures = new String[]{getDinosaurTexture("female")};
    }

    // TODO: Figure out all the entities properties

    @Override
    public String getName(int geneticVariant)
    {
        return "Compsognathus";
    }

    @Override
    public Class<? extends EntityDinosaur> getDinosaurClass()
    {
        return EntityCompsognathus.class;
    }

    @Override
    public EnumTimePeriod getPeriod()
    {
        return EnumTimePeriod.JURASSIC;
    }

    @Override
    public int getEggPrimaryColor()
    {
        return 0x7B8042;
    }

    @Override
    public int getEggSecondaryColor()
    {
        return 0x454B3B;
    }

    @Override
    public double getBabyHealth()
    {
        return 5;
    }

    @Override
    public double getAdultHealth()
    {
        return 15;
    }

    @Override
    public double getBabySpeed()
    {
        return 0.3;
    }

    @Override
    public double getAttackSpeed()
    {
        return 0.50;
    }

    @Override
    public double getAdultSpeed()
    {
        return 0.2;
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
        return 0.7;
    }

    @Override
    public double getAdultKnockback()
    {
        return 0.5;
    }

    @Override
    public int getMaximumAge()
    {
        return fromDays(20);
    }

    @Override
    public String[] getMaleTextures(int geneticVariant, EnumGrowthStage stage)
    {
        return maleTextures;
    }

    @Override
    public String[] getFemaleTextures(int geneticVariant, EnumGrowthStage stage)
    {
        return femaleTextures;
    }

    @Override
    public float getBabyEyeHeight()
    {
        return 0.2F;
    }

    @Override
    public float getAdultEyeHeight()
    {
        return 0.5F;
    }

    @Override
    public float getBabySizeX()
    {
        return 0.1F;
    }

    @Override
    public float getBabySizeY()
    {
        return 0.25F;
    }

    @Override
    public float getAdultSizeX()
    {
        return 0.3F;
    }

    @Override
    public float getAdultSizeY()
    {
        return 0.55F;
    }

    @Override
    public int getStorage()
    {
        return 9;
    }
}