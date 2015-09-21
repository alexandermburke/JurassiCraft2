package org.jurassicraft.common.dinosaur;

import org.jurassicraft.common.entity.EntityAnkylosaurus;
import org.jurassicraft.common.entity.base.EntityDinosaur;
import org.jurassicraft.common.entity.base.EnumGrowthStage;
import org.jurassicraft.common.period.EnumTimePeriod;

public class DinosaurAnkylosaurus extends Dinosaur
{
    private String[] maleTextures;
    private String[] femaleTextures;

    public DinosaurAnkylosaurus()
    {
        this.maleTextures = new String[]{getDinosaurTexture("male")};
        this.femaleTextures = new String[]{getDinosaurTexture("female")};
    }

    // TODO: Figure out all the entities properties

    @Override
    public String getName(int geneticVariant)
    {
        return "Ankylosaurus";
    }

    @Override
    public Class<? extends EntityDinosaur> getDinosaurClass()
    {
        return EntityAnkylosaurus.class;
    }

    @Override
    public EnumTimePeriod getPeriod()
    {
        return EnumTimePeriod.CRETACEOUS;
    }

    @Override
    public int getEggPrimaryColor()
    {
        return 0xAB9B82;
    }

    @Override
    public int getEggSecondaryColor()
    {
        return 0x7C6270;
    }

    @Override
    public double getBabyHealth()
    {
        return 35;
    }

    @Override
    public double getAdultHealth()
    {
        return 85;
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
        return fromDays(50);
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
        return 0.4F;
    }

    @Override
    public float getAdultEyeHeight()
    {
        return 2F;
    }

    @Override
    public float getBabySizeX()
    {
        return 0.7F;
    }

    @Override
    public float getBabySizeY()
    {
        return 0.6F;
    }

    @Override
    public float getAdultSizeX()
    {
        return 3.0F;
    }

    @Override
    public float getAdultSizeY()
    {
        return 3F;
    }

    @Override
    public int getStorage()
    {
        return 27;
    }
}