package net.timeless.jurassicraft.dinosaur;

import net.ilexiconn.llibrary.client.model.entity.animation.IModelAnimator;
import net.ilexiconn.llibrary.client.model.tabula.ModelJson;
import net.minecraft.client.model.ModelBase;
import net.timeless.jurassicraft.block.BlockEncasedFossil;
import net.timeless.jurassicraft.client.model.animation.AnimationTyrannosaurusRex;
import net.timeless.jurassicraft.entity.EntityTyrannosaurusRex;
import net.timeless.jurassicraft.entity.base.EntityDinosaur;

public class DinosaurTyrannosaurusRex extends Dinosaur
{
    private IModelAnimator animator;
    private String[] maleTextures;
    private String[] femaleTextures;
    private ModelJson model;

    public DinosaurTyrannosaurusRex()
    {
        this.animator = new AnimationTyrannosaurusRex();
        this.maleTextures = new String[] { "jurassicraft:textures/entities/tyrannosaurus_rex/tyrannosaurus_male.png" };
        this.femaleTextures = new String[] { "jurassicraft:textures/entities/tyrannosaurus_rex/tyrannosaurus_female.png" };

        try
        {
            this.model = getTabulaModel("/assets/jurassicraft/models/entities/tyrannosaurus_rex");
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    @Override
    public String getName()
    {
        return "Tyrannosaurus Rex";
    }

    @Override
    public Class<? extends EntityDinosaur> getDinosaurClass()
    {
        return EntityTyrannosaurusRex.class;
    }

    @Override
    public int getPeriod()
    {
        return BlockEncasedFossil.EnumTimePeriod.CRETACEOUS.getMetadata();
    }

    @Override
    public int getEggPrimaryColor()
    {
        return 0x6B6628;
    }

    @Override
    public int getEggSecondaryColor()
    {
        return 0x39363B;
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
        return 36;
    }

    @Override
    public double getBabyLength()
    {
        return 2.0;
    }

    @Override
    public double getBabyHeight()
    {
        return 1.0;
    }

    @Override
    public double getAdultLength()
    {
        return 4.9;
    }

    @Override
    public double getAdultHeight()
    {
        return 1.7;
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
    public double getMinLipids()
    {
        return 500;
    }

    @Override
    public double getMinProximate()
    {
        return 1600;
    }

    @Override
    public double getMinMinerals()
    {
        return 1200;
    }

    @Override
    public double getMinVitamins()
    {
        return 1600;
    }

    @Override
    public double getRidingSpeed()
    {
        return 0;
    }

    @Override
    public float getAdultAge()
    {
        return 0.5F;
    }

    @Override
    public float getXZBoxMin()
    {
        return 0.4F;
    }

    @Override
    public float getYBoxMin()
    {
        return 0.5F;
    }

    @Override
    public float getXZBoxDelta()
    {
        return 1.2F;
    }

    @Override
    public float getYBoxDelta()
    {
        return 1.1F;
    }

    @Override
    public float getScaleAdjustment()
    {
        return 2.4F;
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
}
