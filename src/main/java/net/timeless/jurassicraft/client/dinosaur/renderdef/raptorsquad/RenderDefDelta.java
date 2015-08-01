package net.timeless.jurassicraft.client.dinosaur.renderdef.raptorsquad;

import net.minecraft.client.model.ModelBase;
import net.timeless.jurassicraft.client.dinosaur.renderdef.RenderDinosaurDefinition;
import net.timeless.jurassicraft.client.model.animation.AnimationVelociraptor;
import net.timeless.jurassicraft.common.entity.base.JCEntityRegistry;
import net.timeless.unilib.client.model.json.IModelAnimator;
import net.timeless.unilib.client.model.json.ModelJson;

public class RenderDefDelta extends RenderDinosaurDefinition
{
    private IModelAnimator animator;
    private ModelJson model;

    public RenderDefDelta()
    {
        super(JCEntityRegistry.delta);

        this.animator = new AnimationVelociraptor();

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
        return 1F;
    }

    @Override
    public float getBabyScaleAdjustment()
    {
        return 0.3F;
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
