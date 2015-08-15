package net.timeless.jurassicraft.common.dinosaur;

import net.timeless.jurassicraft.common.entity.EntityLeptictidium;
import net.timeless.jurassicraft.common.entity.base.EntityDinosaur;
import net.timeless.jurassicraft.common.period.EnumTimePeriod;

public class DinosaurLeptictidium extends Dinosaur
{
    private String[] maleTextures;
    private String[] femaleTextures;

    public DinosaurLeptictidium()
    {
        this.maleTextures = new String[]{getDinosaurTexture("male")};
        this.femaleTextures = new String[]{getDinosaurTexture("female")};
    }

    @Override
    public String getName(int geneticVariant)
    {
        return "Leptictidium";
    }

    @Override
    public Class<? extends EntityDinosaur> getDinosaurClass()
    {
        return EntityLeptictidium.class;
    }

    @Override
    public EnumTimePeriod getPeriod()
    {
        return EnumTimePeriod.CRETACEOUS;
    } //TODO EOCENE

    @Override
    public int getEggPrimaryColor()
    {
        return 0x362410;
    }

    @Override
    public int getEggSecondaryColor()
    {
        return 0x978A78;
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
        return 0.11;
    }

    @Override
    public double getAttackSpeed()
    {
        return 0.17;
    }

    @Override
    public double getAdultSpeed()
    {
        return 0.10;
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
        return 0.16F;
    }

    @Override
    public float getAdultEyeHeight()
    {
        return 0.63F;
    }

    @Override
    public float getBabySizeX()
    {
        return 0.15F;
    }

    @Override
    public float getBabySizeY()
    {
        return 0.2F;
    }

    @Override
    public float getAdultSizeX()
    {
        return 0.5F;
    }

    @Override
    public float getAdultSizeY()
    {
        return 0.75F;
    }

    @Override
    public boolean shouldRegister()
    {
        return false;
    }
}