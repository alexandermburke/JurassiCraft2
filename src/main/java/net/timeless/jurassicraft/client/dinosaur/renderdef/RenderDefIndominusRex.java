package net.timeless.jurassicraft.client.dinosaur.renderdef;

import net.ilexiconn.llibrary.client.model.entity.animation.IModelAnimator;
import net.ilexiconn.llibrary.client.model.tabula.ModelJson;
import net.minecraft.client.model.ModelBase;
import net.timeless.jurassicraft.client.model.animation.AnimationIndominusRex;
import net.timeless.jurassicraft.entity.base.JCEntityRegistry;

public class RenderDefIndominusRex extends RenderDinosaurDefinition
{
    private IModelAnimator animator;
    private ModelJson model;

    public RenderDefIndominusRex()
    {
        super(JCEntityRegistry.indominus_rex);

        this.animator = new AnimationIndominusRex();

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
    public float getBabyScaleAdjustment()
    {
        return 0.5F;
    }

    @Override
    public float getAdultScaleAdjustment()
    {
        return 3.6F;
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
