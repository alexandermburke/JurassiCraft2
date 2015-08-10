package net.timeless.jurassicraft.common.dinosaur;

import net.timeless.jurassicraft.common.entity.EntityVelociraptor;
import net.timeless.jurassicraft.common.entity.base.EntityDinosaur;
import net.timeless.jurassicraft.common.period.EnumTimePeriod;

public class DinosaurVelociraptor extends Dinosaur
{
    private String[] maleTextures;
    private String[] femaleTextures;

    private String[] blueTextures;
    private String[] echoTextures;
    private String[] deltaTextures;
    private String[] charlieTextures;

    public DinosaurVelociraptor()
    {
        this.maleTextures = new String[] { getDinosaurTexture("male") };
        this.femaleTextures = new String[] { getDinosaurTexture("female") };

        this.blueTextures = new String[] { getDinosaurTexture("", 1)};
        this.echoTextures = new String[] { getDinosaurTexture("", 2)};
        this.deltaTextures = new String[] { getDinosaurTexture("", 3)};
        this.charlieTextures = new String[] { getDinosaurTexture("", 4)};

    }

    @Override
    public String getName(int geneticVariant)
    {
        switch (geneticVariant)
        {
            case 1:
                return "Blue";
            case 2:
                return "Echo";
            case 3:
                return "Delta";
            case 4:
                return "Charlie";
            default:
                return "Velociraptor";
        }
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
        return 65;
    }

    @Override
    public double getBabySpeed()
    {
        return 0.48;
    }

    @Override
    public double getAdultSpeed()
    {
        return 0.40;
    }

    public double getAttackSpeed()
    {
        return 0.50;
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
    public String[] getMaleTextures(int geneticVariant)
    {
        switch (geneticVariant)
        {
            case 1:
                return blueTextures;
            case 2:
                return echoTextures;
            case 3:
                return deltaTextures;
            case 4:
                return charlieTextures;
            default:
                return maleTextures;
        }
    }

    @Override
    public String[] getFemaleTextures(int geneticVariant)
    {
        switch (geneticVariant)
        {
            case 1:
                return blueTextures;
            case 2:
                return echoTextures;
            case 3:
                return deltaTextures;
            case 4:
                return charlieTextures;
            default:
                return femaleTextures;
        }
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
        return 0.4F;
    }

    @Override
    public float getBabySizeY()
    {
        return 0.5F;
    }

    @Override
    public float getAdultSizeX()
    {
        return 1.25F;
    }

    @Override
    public float getAdultSizeY()
    {
        return 1.8F;
    }

    @Override
    public int getGeneticVariants()
    {
        return 5;
    }
}
