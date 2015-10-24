package org.jurassicraft.common.dinosaur;

import org.jurassicraft.common.entity.EntityEdmontosaurus;
import org.jurassicraft.common.entity.EntityLambeosaurus;
import org.jurassicraft.common.entity.base.EntityDinosaur;
import org.jurassicraft.common.entity.base.EnumGrowthStage;
import org.jurassicraft.common.period.EnumTimePeriod;

import java.lang.Override;

public class DinosaurLambeosaurus extends Dinosaur
{
    private String[] maleTextures;
    private String[] femaleTextures;

    public DinosaurLambeosaurus()
    {
        this.maleTextures = new String[] { getDinosaurTexture("male") };
        this.femaleTextures = new String[] { getDinosaurTexture("female") };
    }

    @Override
    public String getName()
    {
        return "Lambeosaurus";
    }

    @Override
    public Class<? extends EntityDinosaur> getDinosaurClass()
    {
        return EntityLambeosaurus.class;
    }

    @Override
    public EnumTimePeriod getPeriod()
    {
        return EnumTimePeriod.CRETACEOUS;
    }

    @Override
    public int getEggPrimaryColor()
    {
        return 0x82947A;
    }

    @Override
    public int getEggSecondaryColor()
    {
        return 0x2F3129;
    }

    @Override
    public double getBabyHealth()
    {
        return 18;
    }

    @Override
    public double getAdultHealth()
    {
        return 75;
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
        return fromDays(50);
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
        return 0.5F;
    }

    @Override
    public float getAdultEyeHeight()
    {
        return 3.45F;
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
        return 2.8F;
    }

    @Override
    public float getAdultSizeY()
    {
        return 4.25F;
    }

    @Override
    public int getStorage()
    {
        return 45;
    }
}