package net.timeless.jurassicraft.common.dinosaur;

import net.timeless.jurassicraft.common.entity.EntityIndominusRex;
import net.timeless.jurassicraft.common.entity.base.EntityDinosaur;
import net.timeless.jurassicraft.common.entity.base.EnumGrowthStage;
import net.timeless.jurassicraft.common.entity.base.JCEntityRegistry;
import net.timeless.jurassicraft.common.period.EnumTimePeriod;

public class DinosaurIndominus extends Dinosaur implements IHybrid
{
    private String[] maleTextures;
    private String[] femaleTextures;

    private Class[] baseGenes;
    private Class[] extraGenes;

    public DinosaurIndominus()
    {
        this.maleTextures = new String[] { getDinosaurTexture("") };
        this.femaleTextures = new String[] { getDinosaurTexture("") };

        this.baseGenes = new Class[] { DinosaurTyrannosaurus.class, DinosaurVelociraptor.class };
        this.extraGenes = new Class[] { DinosaurGiganotosaurus.class, DinosaurRugops.class, DinosaurMajungasaurus.class, DinosaurCarnotaurus.class }; //TODO therizino
    }

    @Override
    public String getName(int geneticVariant)
    {
        return "Indominus";
    }

    @Override
    public Class<? extends EntityDinosaur> getDinosaurClass()
    {
        return EntityIndominusRex.class;
    }

    @Override
    public EnumTimePeriod getPeriod()
    {
        return EnumTimePeriod.CRETACEOUS;
    }

    @Override
    public int getEggPrimaryColor()
    {
        return 0xBEBABB;
    }

    @Override
    public int getEggSecondaryColor()
    {
        return 0x95949A;
    }

    @Override
    public double getBabyHealth()
    {
        return 35;
    }

    @Override
    public double getAdultHealth()
    {
        return 160;
    }

    @Override
    public double getBabySpeed()
    {
        return 0.45;
    }

    @Override
    public double getAdultSpeed()
    {
        return 0.40;
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
        return 3;
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
        return 0.55F;
    }

    @Override
    public float getAdultEyeHeight()
    {
        return 5.4F;
    }

    @Override
    public float getBabySizeX()
    {
        return 0.4F;
    }

    @Override
    public float getBabySizeY()
    {
        return 0.7F;
    }

    @Override
    public float getAdultSizeX()
    {
        return 4.0F;
    }

    @Override
    public float getAdultSizeY()
    {
        return 6.0F;
    }

    @Override
    public Class[] getBaseGenes()
    {
        return baseGenes;
    }

    @Override
    public Class[] getExtraGenes()
    {
        return extraGenes;
    }
}
