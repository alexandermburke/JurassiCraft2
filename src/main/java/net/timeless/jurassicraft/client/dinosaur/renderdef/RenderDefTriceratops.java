package net.timeless.jurassicraft.client.dinosaur.renderdef;

import net.ilexiconn.llibrary.client.model.entity.animation.IModelAnimator;
import net.ilexiconn.llibrary.client.model.tabula.ModelJson;
import net.minecraft.client.model.ModelBase;
import net.timeless.jurassicraft.client.model.animation.AnimationTriceratops;
import net.timeless.jurassicraft.entity.base.JCEntityRegistry;

public class RenderDefTriceratops extends RenderDinosaurDefinition
{
    private IModelAnimator animator;
    private ModelJson model;

    public RenderDefTriceratops()
    {
        super(JCEntityRegistry.triceratops);

        this.animator = new AnimationTriceratops();

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
        return 1.8F;
    }

    @Override
    public float getBabyScaleAdjustment()
    {
        return 0.325F;
    }

    @Override
    public float getShadowSize()
    {
        return 0.65F;
    }
    
    @Override
    public float getRenderYOffset()
    {
        return 0.75F;
    }

    @Override
    public IModelAnimator getModelAnimator()
    {
        return animator;
    }
}
