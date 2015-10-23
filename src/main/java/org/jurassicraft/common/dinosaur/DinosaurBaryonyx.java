package org.jurassicraft.common.dinosaur;

import org.jurassicraft.common.entity.EntityBaryonyx;
import org.jurassicraft.common.entity.base.EntityDinosaur;
import org.jurassicraft.common.entity.base.EnumGrowthStage;
import org.jurassicraft.common.period.EnumTimePeriod;

public class DinosaurBaryonyx extends Dinosaur
{
    private String[] maleTextures;
    private String[] femaleTextures;

    public DinosaurBaryonyx()
    {
        this.maleTextures = new String[] { getDinosaurTexture("male") };
        this.femaleTextures = new String[] { getDinosaurTexture("female") };
    }

    @Override
    public String getName()
    {
        return "Baryonyx";
    }

    @Override
    public Class<? extends EntityDinosaur> getDinosaurClass()
    {
        return EntityBaryonyx.class;
    }

    @Override
    public EnumTimePeriod getPeriod()
    {
        return EnumTimePeriod.CRETACEOUS;
    }

    @Override
    public int getEggPrimaryColor()
    {
        return 0x567F4F;
    }

    @Override
    public int getEggSecondaryColor()
    {
        return 0x13270F;
    }

    @Override
    public double getBabyHealth()
    {
        return 25;
    }

    @Override
    public double getAdultHealth()
    {
        return 75;
    }

    @Override
    public double getBabySpeed()
    {
        return 0.45;
    }

    @Override
    public double getAttackSpeed()
    {
        return 0.17;
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
        return fromDays(55);
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
        return 0.55F;
    }

    @Override
    public float getAdultEyeHeight()
    {
        return 2.95F;
    }

    @Override
    public float getBabySizeX()
    {
        return 0.35F;
    }

    @Override
    public float getBabySizeY()
    {
        return 0.55F;
    }

    @Override
    public float getAdultSizeX()
    {
        return 1.5F;
    }

    @Override
    public float getAdultSizeY()
    {
        return 2.95F;
    }

    @Override
    public int getStorage()
    {
        return 36;
    }
}
