package net.timeless.jurassicraft.common.dinosaur;

import net.timeless.jurassicraft.common.entity.base.EntityDinosaur;
import net.timeless.jurassicraft.common.period.EnumTimePeriod;

public abstract class Dinosaur implements Comparable<Dinosaur>
{
    public abstract String getName();

    public abstract Class<? extends EntityDinosaur> getDinosaurClass();

    public abstract int getEggPrimaryColor();

    public abstract EnumTimePeriod getPeriod();

    public abstract int getEggSecondaryColor();

    public abstract double getBabyHealth();

    public abstract double getAdultHealth();

    public abstract double getBabySpeed();

    public abstract double getAdultSpeed();

    public abstract double getBabyStrength();

    public abstract double getAdultStrength();

    public abstract double getBabyKnockback();

    public abstract double getAdultKnockback();

    public abstract float getBabySizeX();

    public abstract float getBabySizeY();

    public abstract float getAdultSizeX();

    public abstract float getAdultSizeY();

    public abstract float getBabyEyeHeight();

    public abstract float getAdultEyeHeight();

    public abstract int getMaximumAge();

    public abstract String[] getMaleTextures();

    public abstract String[] getFemaleTextures();

    public String[] getMaleOverlayTextures()
    {
        return new String[0];
    }

    public String[] getFemaleOverlayTextures()
    {
        return new String[0];
    }

    public double getAttackSpeed()
    {
        return 0.5D;
    }

    public boolean shouldRegister()
    {
        return true;
    }

    protected String getDinosaurTexture(String subtype)
    {
        String dinosaurName = getName().toLowerCase().replaceAll(" ", "_");

        String texture = "jurassicraft:textures/entities/" + dinosaurName + "/" + dinosaurName;

        if (subtype != "")
            texture += "_" + subtype;

        return texture + ".png";
    }

    @Override
    public int hashCode()
    {
        return getName().hashCode();
    }

    protected int fromDays(int days)
    {
        return (days * 24000) / 32;
    }

    @Override
    public int compareTo(Dinosaur dinosaur)
    {
        return this.getName().compareTo(dinosaur.getName());
    }
}
