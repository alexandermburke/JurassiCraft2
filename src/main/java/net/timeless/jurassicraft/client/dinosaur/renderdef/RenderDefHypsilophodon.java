package net.timeless.jurassicraft.client.dinosaur.renderdef;

import net.timeless.unilib.client.model.json.IModelAnimator;
import net.timeless.unilib.client.model.json.ModelJson;
import net.minecraft.client.model.ModelBase;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.timeless.jurassicraft.client.model.animation.AnimationHypsilophodon;
import net.timeless.jurassicraft.common.entity.base.JCEntityRegistry;

@SideOnly(Side.CLIENT)
public class RenderDefHypsilophodon extends RenderDinosaurDefinition
{
    private IModelAnimator animator;
    private ModelJson model;

    public RenderDefHypsilophodon()
    {
        super(JCEntityRegistry.hypsilophodon);

        this.animator = new AnimationHypsilophodon();

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
        return 0.65F;
    }

    @Override
    public float getBabyScaleAdjustment()
    {
        return 0.2F;
    }

    @Override
    public float getShadowSize()
    {
        return 0.65F;
    }

    public float getRenderXOffset()
    {
        return 0.0F;
    }

    public float getRenderYOffset()
    {
        return -0.05F;
    }

    public float getRenderZOffset()
    {
        return 0.0F;
    }

    @Override
    public IModelAnimator getModelAnimator()
    {
        return animator;
    }
}
