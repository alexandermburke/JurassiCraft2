package net.timeless.jurassicraft.dinosaur;

import net.ilexiconn.llibrary.client.model.entity.animation.IModelAnimator;
import net.ilexiconn.llibrary.client.model.tabula.ModelJson;
import net.minecraft.client.model.ModelBase;
import net.timeless.jurassicraft.block.BlockEncasedFossil;
import net.timeless.jurassicraft.client.model.animation.AnimationParasaurolophus;
import net.timeless.jurassicraft.entity.EntityParasaurolophus;
import net.timeless.jurassicraft.entity.base.EntityDinosaur;

public class DinosaurParasaurolophus extends Dinosaur
{
    private IModelAnimator animator;
    private String[] maleTextures;
    private String[] femaleTextures;
    private ModelJson model;

    public DinosaurParasaurolophus()
    {
        this.animator = new AnimationParasaurolophus();
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
        return "Parasaurolophus";
    }

    @Override
    public Class<? extends EntityDinosaur> getDinosaurClass()
    {
        return EntityParasaurolophus.class;
    }

    @Override
    public int getPeriod()
    {
        return BlockEncasedFossil.EnumTimePeriod.CRETACEOUS.getMetadata();
    }

    @Override
    public int getEggPrimaryColor()
    {
        return 0x9F8138;
    }

    @Override
    public int getEggSecondaryColor()
    {
        return 0x422306;
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
    public float getMaximumAge()
    {
        return 0.5F;
    }

    @Override
    public float getScaleAdjustment()
    {
        return 1.9F;
    }

    @Override
    public float getShadowSize()
    {
        return 0.65F;
    }

    @Override
    public float getRenderYOffset()
    {
        return 1.43F;
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
}