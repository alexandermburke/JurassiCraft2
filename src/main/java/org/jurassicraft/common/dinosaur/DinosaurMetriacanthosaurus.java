package org.jurassicraft.common.dinosaur;

import org.jurassicraft.common.entity.EntityMetriacanthosaurus;
import org.jurassicraft.common.entity.base.EntityDinosaur;
import org.jurassicraft.common.entity.base.EnumGrowthStage;
import org.jurassicraft.common.period.EnumTimePeriod;

import java.lang.Override;

public class DinosaurMetriacanthosaurus extends Dinosaur
{
    private String[] maleTextures;
    private String[] femaleTextures;

    public DinosaurMetriacanthosaurus()
    {
        this.maleTextures = new String[] { getDinosaurTexture("male") };
        this.femaleTextures = new String[] { getDinosaurTexture("female") };
    }

    @Override
    public String getName()
    {
        return "Metriacanthosaurus";
    }

    @Override
    public Class<? extends EntityDinosaur> getDinosaurClass()
    {
        return EntityMetriacanthosaurus.class;
    }

    @Override
    public EnumTimePeriod getPeriod()
    {
        return EnumTimePeriod.JURASSIC;
    }

    @Override
    public int getEggPrimaryColor()
    {
        return 0xB05E1C;
    }

    @Override
    public int getEggSecondaryColor()
    {
        return 0xE7DB27;
    }

    @Override
    public double getBabyHealth()
    {
        return 16;
    }

    @Override
    public double getAdultHealth()
    {
        return 5;
    }

    @Override
    public double getBabySpeed()
    {
        return 0.44;
    }

    @Override
    public double getAttackSpeed()
    {
        return 0.5;
    }

    @Override
    public double getAdultSpeed()
    {
        return 0.4;
    }

    @Override
    public double getBabyStrength()
    {
        return 12;
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
        return fromDays(40);
    }

    @Override
    public String[] getMaleTextures(EnumGrowthStage growthStage)
    {
        return maleTextures;
    }

    @Override
    public String[] getFemaleTextures(EnumGrowthStage growthStage)
    {
        return femaleTextures;
    }

    @Override
    public float getBabyEyeHeight()
    {
        return 0.35F;
    }

    @Override
    public float getAdultEyeHeight()
    {
        return 1.75F;
    }

    @Override
    public float getBabySizeX()
    {
        return 0.25F;
    }

    @Override
    public float getBabySizeY()
    {
        return 0.35F;
    }

    @Override
    public float getAdultSizeX()
    {
        return 1.15F;
    }

    @Override
    public float getAdultSizeY()
    {
        return 1.95F;
    }

    @Override
    public int getStorage()
    {
        return 27;
    }
}
