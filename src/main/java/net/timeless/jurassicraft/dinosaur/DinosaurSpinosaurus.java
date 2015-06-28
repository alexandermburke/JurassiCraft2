package net.timeless.jurassicraft.dinosaur;

import net.ilexiconn.llibrary.client.model.entity.animation.IModelAnimator;
import net.ilexiconn.llibrary.client.model.tabula.ModelJson;
import net.minecraft.client.model.ModelBase;
import net.timeless.jurassicraft.client.model.animation.AnimationSpinosaurus;
import net.timeless.jurassicraft.entity.EntitySpinosaurus;
import net.timeless.jurassicraft.entity.base.EntityDinosaur;
import net.timeless.jurassicraft.period.EnumTimePeriod;

public class DinosaurSpinosaurus extends Dinosaur
{
    private IModelAnimator animator;
    private String[] maleTextures;
    private String[] femaleTextures;
    private String[] maleOverlayTextures;
    private String[] femaleOverlayTextures;
    private ModelJson model;

    public DinosaurSpinosaurus()
    {
        this.animator = new AnimationSpinosaurus();
        this.maleTextures = new String[] { getDinosaurTexture("male") };
        this.femaleTextures = new String[] { getDinosaurTexture("female") };

        this.maleOverlayTextures = new String[] { getDinosaurTexture("male_detail") };
        this.femaleOverlayTextures = new String[] { getDinosaurTexture("female_detail") };

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
        return "Spinosaurus";
    }

    @Override
    public Class<? extends EntityDinosaur> getDinosaurClass()
    {
        return EntitySpinosaurus.class;
    }

    @Override
    public EnumTimePeriod getPeriod()
    {
        return EnumTimePeriod.CRETACEOUS;
    }

    @Override
    public int getEggPrimaryColor()
    {
        return 0x48403D;
    }

    @Override
    public int getEggSecondaryColor()
    {
        return 0xC5CFDA;
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
    public double getMaximumAge()
    {
        return 0.5F;
    }

    @Override
    public float getAdultScaleAdjustment()
    {
        return 2.37F;
    }
    
    @Override
    public float getBabyScaleAdjustment()
    {
        return 0.1F;
    }

    @Override
    public float getShadowSize()
    {
        return 0.65F;
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
    public String[] getMaleOverlayTextures()
    {
        return maleOverlayTextures;
    }

    @Override
    public String[] getFemaleOverlayTextures()
    {
        return femaleOverlayTextures;
    }
}