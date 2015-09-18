package net.timeless.jurassicraft.common.dinosaur;

import net.timeless.jurassicraft.common.entity.EntityHerrerasaurus;
import net.timeless.jurassicraft.common.entity.base.EntityDinosaur;
import net.timeless.jurassicraft.common.entity.base.EnumGrowthStage;
import net.timeless.jurassicraft.common.period.EnumTimePeriod;

public class DinosaurHerrerasaurus extends Dinosaur
{
    private String[] maleTextures;
    private String[] femaleTextures;

    public DinosaurHerrerasaurus()
    {
        this.maleTextures = new String[]{getDinosaurTexture("male")};
        this.femaleTextures = new String[]{getDinosaurTexture("female")};
    }

    @Override
    public String getName(int geneticVariant)
    {
        return "Herrerasaurus";
    }

    @Override
    public Class<? extends EntityDinosaur> getDinosaurClass()
    {
        return EntityHerrerasaurus.class;
    }

    @Override
    public EnumTimePeriod getPeriod()
    {
        return EnumTimePeriod.TRIASSIC;
    }

    @Override
    public int getEggPrimaryColor()
    {
        return 0x2B1919;
    }

    @Override
    public int getEggSecondaryColor()
    {
        return 0x932C23;
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
        return fromDays(45);
    }

    @Override
    public String[] getMaleTextures(int geneticVariant, EnumGrowthStage stage)
    {
        return maleTextures;
    }

    @Override
    public String[] getFemaleTextures(int geneticVariant, EnumGrowthStage stage)
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
        return 2.25F;
    }

    @Override
    public float getBabySizeX()
    {
        return 0.4F;
    }

    @Override
    public float getBabySizeY()
    {
        return 0.55F;
    }

    @Override
    public float getAdultSizeX()
    {
        return 1.8F;
    }

    @Override
    public float getAdultSizeY()
    {
        return 2.55F;
    }

    @Override
    public int getStorage()
    {
        return 36;
    }
}
