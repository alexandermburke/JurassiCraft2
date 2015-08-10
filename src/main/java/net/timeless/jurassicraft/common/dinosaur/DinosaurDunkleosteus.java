package net.timeless.jurassicraft.common.dinosaur;

import net.timeless.jurassicraft.common.entity.EntityDunkleosteus;
import net.timeless.jurassicraft.common.entity.base.EntityDinosaur;
import net.timeless.jurassicraft.common.period.EnumTimePeriod;

public class DinosaurDunkleosteus extends Dinosaur
{
    private String[] maleTextures;
    private String[] femaleTextures;

    public DinosaurDunkleosteus()
    {
        this.maleTextures = new String[] { getDinosaurTexture("male") };
        this.femaleTextures = new String[] { getDinosaurTexture("female") };
    }

    // TODO: Figure out all the entities properties

    @Override
    public String getName(int geneticVariant)
    {
        return "Dunkleosteus";
    }

    @Override
    public Class<? extends EntityDinosaur> getDinosaurClass()
    {
        return EntityDunkleosteus.class;
    }

    @Override
    public EnumTimePeriod getPeriod()
    {
        return EnumTimePeriod.DEVONIAN;
    }

    @Override
    public int getEggPrimaryColor()
    {
        return 0xA89B8C;
    }

    @Override
    public int getEggSecondaryColor()
    {
        return 0x753A28;
    }

    @Override
    public double getBabyHealth()
    {
        return 18;
    }

    @Override
    public double getAdultHealth()
    {
        return 70;
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
        return 0.45;
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
        return fromDays(30);
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
        return 0.3F;
    }

    @Override
    public float getAdultEyeHeight()
    {
        return 1.2F;
    }

    @Override
    public float getBabySizeX()
    {
        return 0.8F;
    }

    @Override
    public float getBabySizeY()
    {
        return 0.5F;
    }

    @Override
    public float getAdultSizeX()
    {
        return 2.7F;
    }

    @Override
    public float getAdultSizeY()
    {
        return 2.0F;
    }
}