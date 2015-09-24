package org.jurassicraft.client.render.renderdef;

import net.minecraft.client.model.ModelBase;
import org.jurassicraft.client.model.animation.AnimationMegapiranha;
import org.jurassicraft.common.entity.base.EnumGrowthStage;
import org.jurassicraft.common.entity.base.JCEntityRegistry;
import net.timeless.unilib.client.model.json.IModelAnimator;
import net.timeless.unilib.client.model.json.ModelJson;

public class RenderDefMegapiranha extends RenderDinosaurDefinition
{
    private IModelAnimator animator;
    private ModelJson model;

    public RenderDefMegapiranha()
    {
        super(JCEntityRegistry.megapiranha);

        this.animator = new AnimationMegapiranha();

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
    public ModelBase getModel(int geneticVariant, EnumGrowthStage stage)
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
        return 0.15F;
    }

    @Override
    public float getShadowSize()
    {
        return 0.65F;
    }

    @Override
    public float getRenderXOffset(int geneticVariant)
    {
        return 0.0F;
    }

    @Override
    public float getRenderYOffset(int geneticVariant)
    {
        return 0.65F;
    }

    @Override
    public float getRenderZOffset(int geneticVariant)
    {
        return -0.25F;
    }

    @Override
    public IModelAnimator getModelAnimator(int geneticVariant)
    {
        return animator;
    }
}