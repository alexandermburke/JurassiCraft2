package net.timeless.jurassicraft.dinosaur;

import net.timeless.jurassicraft.entity.EntityAnklyosaurus;
import net.timeless.jurassicraft.entity.base.EntityDinosaur;
import net.timeless.jurassicraft.period.EnumTimePeriod;

public class DinosaurAnkylosaurus extends Dinosaur
{
    private String[] maleTextures;
    private String[] femaleTextures;

    private String[] maleOverlayTextures;
    private String[] femaleOverlayTextures;

    public DinosaurAnkylosaurus()
    {
        this.maleTextures = new String[] { getDinosaurTexture("male") };
        this.femaleTextures = new String[] { getDinosaurTexture("female") };

        this.maleOverlayTextures = new String[] { getDinosaurTexture("male_detail") };
        this.femaleOverlayTextures = new String[] { getDinosaurTexture("female_detail") };
    }

    // TODO: Figure out all the entities properties

    @Override
    public String getName()
    {
        return "Ankylosaurus";
    }

    @Override
    public Class<? extends EntityDinosaur> getDinosaurClass()
    {
        return EntityAnklyosaurus.class;
    }

    @Override
    public EnumTimePeriod getPeriod()
    {
        return EnumTimePeriod.CRETACEOUS;
    }

    @Override
    public int getEggPrimaryColor()
    {
        return 0x7A7268;
    }

    @Override
    public int getEggSecondaryColor()
    {
        return 0x7E4941;
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
        return 10000;
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
    public String[] getMaleOverlayTextures()
    {
        return maleOverlayTextures;
    }

    @Override
    public String[] getFemaleOverlayTextures()
    {
        return femaleOverlayTextures;
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
        return 1.0F;
    }

    @Override
    public float getBabySizeY()
    {
        return 0.8F;
    }

    @Override
    public float getAdultSizeX()
    {
        return 4.0F;
    }

    @Override
    public float getAdultSizeY()
    {
        return 3F;
    }
}