package net.timeless.jurassicraft.common.dinosaur;

import net.timeless.jurassicraft.common.entity.EntityDodo;
import net.timeless.jurassicraft.common.entity.EntityMicroceratus;
import net.timeless.jurassicraft.common.entity.base.EntityDinosaur;
import net.timeless.jurassicraft.common.period.EnumTimePeriod;

public class DinosaurMicroceratus extends Dinosaur
{
    private String[] maleTextures;
    private String[] femaleTextures;

    public DinosaurMicroceratus()
    {
        this.maleTextures = new String[] { getDinosaurTexture("male") };
        this.femaleTextures = new String[] { getDinosaurTexture("female") };
    }

    @Override
    public String getName(int geneticVariant)
    {
        return "Microceratus";
    }

    @Override
    public Class<? extends EntityDinosaur> getDinosaurClass()
    {
        return EntityMicroceratus.class;
    }

    @Override
    public EnumTimePeriod getPeriod()
    {
        return EnumTimePeriod.CRETACEOUS;
    }

    @Override
    public int getEggPrimaryColor()
    {
        return 0x956F2D;
    }

    @Override
    public int getEggSecondaryColor()
    {
        return 0x92442C;
    }

    @Override
    public double getBabyHealth()
    {
        return 10;
    }

    @Override
    public double getAdultHealth()
    {
        return 25;
    }

    @Override
    public double getBabySpeed()
    {
        return 0.41;
    }

    @Override
    public double getAttackSpeed()
    {
        return 0.17;
    }

    @Override
    public double getAdultSpeed()
    {
        return 0.35;
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
        return fromDays(20);
    }

    @Override
    public String[] getMaleTextures(int geneticVariant)
    {
        return maleTextures;
    }

    @Override
    public String[] getFemaleTextures(int geneticVariant)
    {
        return femaleTextures;
    }

    @Override
    public float getBabyEyeHeight()
    {
        return 0.14F;
    }

    @Override
    public float getAdultEyeHeight()
    {
        return 0.36F;
    }

    @Override
    public float getBabySizeX()
    {
        return 0.15F;
    }

    @Override
    public float getBabySizeY()
    {
        return 0.18F;
    }

    @Override
    public float getAdultSizeX()
    {
        return 0.4F;
    }

    @Override
    public float getAdultSizeY()
    {
        return 0.55F;
    }
}