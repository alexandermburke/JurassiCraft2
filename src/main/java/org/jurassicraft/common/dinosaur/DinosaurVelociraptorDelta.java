package org.jurassicraft.common.dinosaur;

import org.jurassicraft.common.api.IHybrid;
import org.jurassicraft.common.entity.EntityVelociraptorDelta;
import org.jurassicraft.common.entity.base.EntityDinosaur;
import org.jurassicraft.common.entity.base.EnumGrowthStage;
import org.jurassicraft.common.period.EnumTimePeriod;

public class DinosaurVelociraptorDelta extends Dinosaur implements IHybrid
{
    private String[] textures;

    public DinosaurVelociraptorDelta()
    {
        this.textures = new String[]{getDinosaurTexture("")};
    }

    @Override
    public String getName(int geneticVariant)
    {
        return "Delta";
    }

    @Override
    public Class<? extends EntityDinosaur> getDinosaurClass()
    {
        return EntityVelociraptorDelta.class;
    }

    @Override
    public EnumTimePeriod getPeriod()
    {
        return EnumTimePeriod.CRETACEOUS;
    }

    @Override
    public int getEggPrimaryColor()
    {
        return 0x526353;
    }

    @Override
    public int getEggSecondaryColor()
    {
        return 0x3D4F40;
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
    public String[] getMaleTextures(int geneticVariant, EnumGrowthStage stage)
    {
        return textures;
    }

    @Override
    public String[] getFemaleTextures(int geneticVariant, EnumGrowthStage stage)
    {
        return textures;
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
        return 0.8F;
    }

    @Override
    public float getAdultSizeY()
    {
        return 1.8F;
    }

    @Override
    public Class[] getBaseGenes()
    {
        return new Class[]{DinosaurVelociraptor.class}; //TODO
    }

    @Override
    public Class[] getExtraGenes()
    {
        return new Class[]{DinosaurVelociraptor.class};
    }

    @Override
    public int getStorage()
    {
        return 27;
    }
}
