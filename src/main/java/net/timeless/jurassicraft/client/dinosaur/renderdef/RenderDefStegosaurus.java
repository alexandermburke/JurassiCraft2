package net.timeless.jurassicraft.client.dinosaur.renderdef;

import net.ilexiconn.llibrary.client.model.entity.animation.IModelAnimator;
import net.ilexiconn.llibrary.client.model.tabula.ModelJson;
import net.minecraft.client.model.ModelBase;
import net.timeless.jurassicraft.client.model.animation.AnimationStegosaurus;
import net.timeless.jurassicraft.entity.base.JCEntityRegistry;

public class RenderDefStegosaurus extends RenderDinosaurDefinition
{
    private IModelAnimator animator;
    private ModelJson model;

    public RenderDefStegosaurus()
    {
        super(JCEntityRegistry.stegosaurus);

        this.animator = new AnimationStegosaurus();

        try
        {
            this.model = getDefaultTabulaModel();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    @Override
    public ModelBase getModel()
    {
        return model;
    }

    @Override
    public float getAdultScaleAdjustment()
    {
        return 2.55F;
    }

    @Override
    public float getBabyScaleAdjustment()
    {
        return 0.35F;
    }

    @Override
    public float getRenderYOffset()
    {
        return 0.775F;
    }
    
    @Override
    public float getShadowSize()
    {
        return 0.65F;
    }

    @Override
    public IModelAnimator getModelAnimator()
    {
        return animator;
    }
}
