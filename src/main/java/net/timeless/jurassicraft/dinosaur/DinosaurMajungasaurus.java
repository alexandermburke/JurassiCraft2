package net.timeless.jurassicraft.dinosaur;

import net.timeless.jurassicraft.entity.EntityMajungasaurus;
import net.timeless.jurassicraft.entity.base.EntityDinosaur;
import net.timeless.jurassicraft.period.EnumTimePeriod;

public class DinosaurMajungasaurus extends Dinosaur
{
    private String[] maleTextures;
    private String[] femaleTextures;

    public DinosaurMajungasaurus()
    {
        this.maleTextures = new String[] { getDinosaurTexture("male") };
        this.femaleTextures = new String[] { getDinosaurTexture("female") };
    }

    // TODO: Figure out all the entities properties

    @Override
    public String getName()
    {
        return "Majungasaurus";
    }

    @Override
    public Class<? extends EntityDinosaur> getDinosaurClass()
    {
        return EntityMajungasaurus.class;
    }

    @Override
    public EnumTimePeriod getPeriod()
    {
        return EnumTimePeriod.CRETACEOUS;
    }

    @Override
    public int getEggPrimaryColor()
    {
        return 0xE6CC9B;
    }

    @Override
    public int getEggSecondaryColor()
    {
        return 0x7C8A7D;
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
    public double getMaximumAge()
    {
        return 20F;
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
}