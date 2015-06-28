package net.timeless.jurassicraft.dinosaur;

import net.ilexiconn.llibrary.client.model.entity.animation.IModelAnimator;
import net.ilexiconn.llibrary.client.model.tabula.ModelJson;
import net.minecraft.client.model.ModelBase;
import net.timeless.jurassicraft.client.model.animation.AnimationCompsognathus;
import net.timeless.jurassicraft.entity.EntityCompsognathus;
import net.timeless.jurassicraft.entity.base.EntityDinosaur;
import net.timeless.jurassicraft.period.EnumTimePeriod;

public class DinosaurCompsognathus extends Dinosaur
{
    private IModelAnimator animator;
    private String[] maleTextures;
    private String[] femaleTextures;
    private ModelJson model;

    public DinosaurCompsognathus()
    {
        this.animator = new AnimationCompsognathus();
        this.maleTextures = new String[] { getDinosaurTexture("male") };
        this.femaleTextures = new String[] { getDinosaurTexture("female") };

        try
        {
            this.model = getDefaultTabulaModel();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    // TODO: Figure out all the entities properties

    @Override
    public String getName()
    {
        return "Compsognathus";
    }

    @Override
    public Class<? extends EntityDinosaur> getDinosaurClass()
    {
        return EntityCompsognathus.class;
    }

    @Override
    public EnumTimePeriod getPeriod()
    {
        return EnumTimePeriod.CRETACEOUS;
    }

    @Override
    public int getEggPrimaryColor()
    {
        return 0x7B8042;
    }

    @Override
    public int getEggSecondaryColor()
    {
        return 0x454B3B;
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
        return 0.3;
    }

    @Override
    public double getAttackSpeed()
    {
        return 0.50;
    }

    @Override
    public double getAdultSpeed()
    {
        return 0.2;
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
        return 0.7;
    }

    @Override
    public double getAdultKnockback()
    {
        return 0.5;
    }

    @Override
    public double getMaximumAge()
    {
        return 0.5F;
    }

    @Override
    public float getAdultScaleAdjustment()
    {
        return 0.1F;
    }

    @Override
    public float getBabyScaleAdjustment()
    {
        return 0.02F;
    }
    
    @Override
    public float getRenderYOffset()
    {
        return -1.2F;
    }

    @Override
    public float getShadowSize()
    {
        return 1.8F;
    }

    @Override
    public ModelBase getModel()
    {
        return model;
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
    public IModelAnimator getModelAnimator()
    {
        return animator;
    }

    @Override
    public float getRenderZOffset()
    {
        return -0.1F;
    }
}