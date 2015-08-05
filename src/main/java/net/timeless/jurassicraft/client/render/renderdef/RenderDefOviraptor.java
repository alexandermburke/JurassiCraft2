package net.timeless.jurassicraft.client.render.renderdef;

import net.minecraft.client.model.ModelBase;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.timeless.jurassicraft.client.model.animation.AnimationOviraptor;
import net.timeless.jurassicraft.client.model.animation.AnimationTriceratops;
import net.timeless.jurassicraft.common.entity.base.JCEntityRegistry;
import net.timeless.unilib.client.model.json.IModelAnimator;
import net.timeless.unilib.client.model.json.ModelJson;

@SideOnly(Side.CLIENT)
public class RenderDefOviraptor extends RenderDinosaurDefinition
{
    private IModelAnimator animator;
    private ModelJson model;

    public RenderDefOviraptor()
    {
        super(JCEntityRegistry.oviraptor);

        this.animator = new AnimationOviraptor();

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
        return 0.65F;
    }

    @Override
    public float getBabyScaleAdjustment()
    {
        return 0.185F;
    }

    @Override
    public float getShadowSize()
    {
        return 0.55F;
    }

    @Override
    public float getRenderZOffset(int geneticVariant)
    {
        return -0.4F;
    }

    @Override
    public IModelAnimator getModelAnimator(int geneticVariant)
    {
        return animator;
    }
}
