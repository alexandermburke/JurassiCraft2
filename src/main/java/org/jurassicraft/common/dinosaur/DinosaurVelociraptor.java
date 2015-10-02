package org.jurassicraft.common.dinosaur;

import org.jurassicraft.common.entity.EntityVelociraptor;
import org.jurassicraft.common.entity.base.EntityDinosaur;
import org.jurassicraft.common.entity.base.EnumGrowthStage;
import org.jurassicraft.common.period.EnumTimePeriod;

public class DinosaurVelociraptor extends Dinosaur
{
    private String[] maleTextures;
    private String[] femaleTextures;

    private String[] maleTexturesInfant;
    private String[] femaleTexturesInfant;

    private String[] maleTexturesJuvenile;
    private String[] femaleTexturesJuvenile;

    private String[] maleTexturesAdolescent;
    private String[] femaleTexturesAdolescent;

    public DinosaurVelociraptor()
    {
        this.maleTextures = new String[]{getDinosaurTexture("male")};
        this.femaleTextures = new String[]{getDinosaurTexture("female")};

        this.maleTexturesInfant = new String[]{getDinosaurTexture("male_infant")};
        this.femaleTexturesInfant = new String[]{getDinosaurTexture("female_infant")};

        this.maleTexturesJuvenile = new String[]{getDinosaurTexture("male_juvenile")};
        this.femaleTexturesJuvenile = new String[]{getDinosaurTexture("female_juvenile")};

        this.maleTexturesAdolescent = new String[]{getDinosaurTexture("male_adolescent")};
        this.femaleTexturesAdolescent = new String[]{getDinosaurTexture("female_adolescent")};
    }

    @Override
    public String getName()
    {
        return "Velociraptor";
    }

    @Override
    public Class<? extends EntityDinosaur> getDinosaurClass()
    {
        return EntityVelociraptor.class;
    }

    @Override
    public EnumTimePeriod getPeriod()
    {
        return EnumTimePeriod.CRETACEOUS;
    }

    @Override
    public int getEggPrimaryColor()
    {
        return 0xB17041;
    }

    @Override
    public int getEggSecondaryColor()
    {
        return 0x3B1505;
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
        return fromDays(45);
    }

    @Override
    public String[] getMaleTextures(EnumGrowthStage stage)
    {
        switch (stage)
        {
            case INFANT:
                return maleTexturesInfant;
            case JUVENILE:
                return maleTexturesJuvenile;
            case ADOLESCENT:
                return maleTexturesAdolescent;
            default:
                return maleTextures;
        }
    }

    @Override
    public String[] getFemaleTextures(EnumGrowthStage stage)
    {
        switch (stage)
        {
            case INFANT:
                return femaleTexturesInfant;
            case JUVENILE:
                return femaleTexturesJuvenile;
            case ADOLESCENT:
                return femaleTexturesAdolescent;
            default:
                return femaleTextures;
        }
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
    public int getStorage()
    {
        return 27;
    }
}
