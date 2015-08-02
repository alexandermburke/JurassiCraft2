package net.timeless.jurassicraft.client.dinosaur.renderdef;

import net.minecraft.client.model.ModelBase;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.timeless.jurassicraft.client.model.animation.AnimationTriceratops;
import net.timeless.jurassicraft.common.entity.base.JCEntityRegistry;
import net.timeless.unilib.client.model.json.IModelAnimator;
import net.timeless.unilib.client.model.json.ModelJson;

@SideOnly(Side.CLIENT)
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
    public ModelBase getModel(int geneticVariant)
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
    public float getRenderYOffset(int geneticVariant)
    {
        return 0.75F;
    }

    @Override
    public IModelAnimator getModelAnimator(int geneticVariant)
    {
        return animator;
    }
}
