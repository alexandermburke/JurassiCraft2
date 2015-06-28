package net.timeless.jurassicraft.client.dinosaur.renderdef;

import net.ilexiconn.llibrary.client.model.entity.animation.IModelAnimator;
import net.ilexiconn.llibrary.client.model.tabula.ModelJson;
import net.minecraft.client.model.ModelBase;
import net.timeless.jurassicraft.client.model.animation.AnimationTyrannosaurusRex;
import net.timeless.jurassicraft.entity.base.JCEntityRegistry;

public class RenderDefTyrannosaurusRex extends RenderDinosaurDefinition
{
    private IModelAnimator animator;
    private ModelJson model;

    public RenderDefTyrannosaurusRex()
    {
        super(JCEntityRegistry.tyrannosaurus_rex);

        this.animator = new AnimationTyrannosaurusRex();

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
        return 2.4F;
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
