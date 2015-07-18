package net.timeless.jurassicraft.common.dinosaur;

import net.timeless.jurassicraft.common.entity.EntityVelociraptor;
import net.timeless.jurassicraft.common.entity.base.EntityDinosaur;
import net.timeless.jurassicraft.common.period.EnumTimePeriod;

public class DinosaurVelociraptor extends Dinosaur
{
    private String[] maleTextures;
    private String[] femaleTextures;

    public DinosaurVelociraptor()
    {
        this.maleTextures = new String[] { getDinosaurTexture("male") };
        this.femaleTextures = new String[] { getDinosaurTexture("female") };
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
        return 55;
    }

    @Override
    public double getBabySpeed()
    {
        return 0.82;
    }

    @Override
    public double getAdultSpeed()
    {
        return 0.60;
    }

    public double getAttackSpeed()
    {
        return 1.20;
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
    public String[] getMaleTextures()
    {
        return maleTextures;
    }

    @Override
    public String[] getFemaleTextures()
    {
        return femaleTextures;
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
        return 0.6F;
    }

    @Override
    public float getBabySizeY()
    {
        return 0.6F;
    }

    @Override
    public float getAdultSizeX()
    {
        return 1.5F;
    }

    @Override
    public float getAdultSizeY()
    {
        return 1.8F;
    }
}
