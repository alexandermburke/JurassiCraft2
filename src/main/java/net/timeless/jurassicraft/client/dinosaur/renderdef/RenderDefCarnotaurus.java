package net.timeless.jurassicraft.client.dinosaur.renderdef;

import net.ilexiconn.llibrary.client.model.entity.animation.IModelAnimator;
import net.ilexiconn.llibrary.client.model.tabula.ModelJson;
import net.minecraft.client.model.ModelBase;
import net.timeless.jurassicraft.client.model.animation.AnimationCarnotaurus;
import net.timeless.jurassicraft.entity.base.JCEntityRegistry;

public class RenderDefCarnotaurus extends RenderDinosaurDefinition
{
    private IModelAnimator animator;
    private ModelJson model;

    public RenderDefCarnotaurus()
    {
        super(JCEntityRegistry.carnotaurus);

        this.animator = new AnimationCarnotaurus();

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
        return 1.3F;
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
    public IModelAnimator getModelAnimator()
    {
        return animator;
    }
}
