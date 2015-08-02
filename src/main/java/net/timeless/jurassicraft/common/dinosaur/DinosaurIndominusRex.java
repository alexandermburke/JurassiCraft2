package net.timeless.jurassicraft.common.dinosaur;

import net.timeless.jurassicraft.common.entity.EntityIndominusRex;
import net.timeless.jurassicraft.common.entity.base.EntityDinosaur;
import net.timeless.jurassicraft.common.entity.base.JCEntityRegistry;
import net.timeless.jurassicraft.common.period.EnumTimePeriod;

public class DinosaurIndominusRex extends Dinosaur implements IHybrid
{
    private String[] maleTextures;
    private String[] femaleTextures;
    private String[] maleOverlayTextures;
    private String[] femaleOverlayTextures;

    private Dinosaur[] combo;

    public DinosaurIndominusRex()
    {
        this.maleTextures = new String[] { getDinosaurTexture("") };
        this.femaleTextures = new String[] { getDinosaurTexture("") };

        this.maleOverlayTextures = new String[] { getDinosaurTexture("detail") };
        this.femaleOverlayTextures = new String[] { getDinosaurTexture("detail") };

        this.combo = new Dinosaur[] { JCEntityRegistry.tyrannosaurus_rex, JCEntityRegistry.velociraptor, JCEntityRegistry.giganotosaurus, JCEntityRegistry.rugops, JCEntityRegistry.majungasaurus };
    }

    @Override
    public String getName(int geneticVariant)
    {
        return "Indominus Rex";
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
        return 16;
    }

    @Override
    public double getAdultHealth()
    {
        return 55;
    }

    @Override
    public double getBabySpeed()
    {
        return 0.82;
    }

    @Override
    public double getAdultSpeed()
    {
        return 0.60;
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
    public String[] getMaleOverlayTextures(int geneticVariant)
    {
        return maleOverlayTextures;
    }

    @Override
    public String[] getFemaleOverlayTextures(int geneticVariant)
    {
        return femaleOverlayTextures;
    }

    @Override
    public float getBabyEyeHeight()
    {
        return 0.85F;
    }

    @Override
    public float getAdultEyeHeight()
    {
        return 6.8F;
    }

    @Override
    public float getBabySizeX()
    {
        return 1.2F;
    }

    @Override
    public float getBabySizeY()
    {
        return 1.0F;
    }

    @Override
    public float getAdultSizeX()
    {
        return 6.0F;
    }

    @Override
    public float getAdultSizeY()
    {
        return 8.0F;
    }

    @Override
    public Dinosaur[] getCombination()
    {
        return combo;
    }
}
