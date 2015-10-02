package org.jurassicraft.common.dinosaur;

import org.jurassicraft.common.entity.EntityMegapiranha;
import org.jurassicraft.common.entity.base.EntityDinosaur;
import org.jurassicraft.common.entity.base.EnumGrowthStage;
import org.jurassicraft.common.period.EnumTimePeriod;

public class DinosaurMegapiranha extends Dinosaur
{
    private String[] maleTextures;
    private String[] femaleTextures;

    public DinosaurMegapiranha()
    {
        this.maleTextures = new String[]{getDinosaurTexture("male")};
        this.femaleTextures = new String[]{getDinosaurTexture("female")};
    }

    @Override
    public String getName()
    {
        return "Megapiranha";
    }

    @Override
    public Class<? extends EntityDinosaur> getDinosaurClass()
    {
        return EntityMegapiranha.class;
    }

    @Override
    public EnumTimePeriod getPeriod()
    {
        return EnumTimePeriod.CRETACEOUS; //TODO LATE MIOCENE
    }

    @Override
    public int getEggPrimaryColor()
    {
        return 0x17100B;
    }

    @Override
    public int getEggSecondaryColor()
    {
        return 0x645C54;
    }

    @Override
    public double getBabyHealth()
    {
        return 10;
    }

    @Override
    public double getAdultHealth()
    {
        return 30;
    }

    @Override
    public double getBabySpeed()
    {
        return 0.46;
    }

    @Override
    public double getAttackSpeed()
    {
        return 0.40;
    }

    @Override
    public double getAdultSpeed()
    {
        return 0.80;
    }

    @Override
    public double getBabyStrength()
    {
        return 2;
    }

    @Override
    public double getAdultStrength()
    {
        return 6;
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
        return fromDays(30);
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
        return 0.35F;
    }

    @Override
    public float getAdultEyeHeight()
    {
        return 0.35F;
    }

    @Override
    public float getBabySizeX()
    {
        return 0.15F;
    }

    @Override
    public float getBabySizeY()
    {
        return 0.15F;
    }

    @Override
    public float getAdultSizeX()
    {
        return 0.5F;
    }

    @Override
    public float getAdultSizeY()
    {
        return 0.7F;
    }

    @Override
    public boolean isMarineAnimal()
    {
        return true;
    }

    @Override
    public int getStorage()
    {
        return 18;
    }
}
