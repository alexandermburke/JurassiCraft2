package net.timeless.jurassicraft.common.dinosaur;

import net.timeless.jurassicraft.common.entity.EntityStegosaurus;
import net.timeless.jurassicraft.common.entity.base.EntityDinosaur;
import net.timeless.jurassicraft.common.period.EnumTimePeriod;

public class DinosaurStegosaurus extends Dinosaur
{
    private String[] maleTextures;
    private String[] femaleTextures;

    public DinosaurStegosaurus()
    {
        this.maleTextures = new String[] { getDinosaurTexture("male") };
        this.femaleTextures = new String[] { getDinosaurTexture("female") };
    }

    public float getRenderYOffset()
    {
        return 0.76F;
    }

    // TODO: Figure out all the entities properties

    @Override
    public String getName()
    {
        return "Stegosaurus";
    }

    @Override
    public Class<? extends EntityDinosaur> getDinosaurClass()
    {
        return EntityStegosaurus.class;
    }

    @Override
    public EnumTimePeriod getPeriod()
    {
        return EnumTimePeriod.JURASSIC;
    }

    @Override
    public int getEggPrimaryColor()
    {
        return 0xBABF83;
    }

    @Override
    public int getEggSecondaryColor()
    {
        return 0x75964E;
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
        return 0.80;
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
        return 0.25F;
    }

    @Override
    public float getAdultEyeHeight()
    {
        return 2.2F;
    }

    @Override
    public float getBabySizeX()
    {
        return 1.0F;
    }

    @Override
    public float getBabySizeY()
    {
        return 0.7F;
    }

    @Override
    public float getAdultSizeX()
    {
        return 5.0F;
    }

    @Override
    public float getAdultSizeY()
    {
        return 4.8F;
    }
}