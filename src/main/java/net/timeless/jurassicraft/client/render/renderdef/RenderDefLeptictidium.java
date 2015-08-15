package net.timeless.jurassicraft.client.render.renderdef;

import net.minecraft.client.model.ModelBase;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.timeless.jurassicraft.client.model.animation.AnimationLeptictidium;
import net.timeless.jurassicraft.common.entity.base.JCEntityRegistry;
import net.timeless.unilib.client.model.json.IModelAnimator;
import net.timeless.unilib.client.model.json.ModelJson;

@SideOnly(Side.CLIENT)
public class RenderDefLeptictidium extends RenderDinosaurDefinition
{
    private IModelAnimator animator;
    private ModelJson model;

    public RenderDefLeptictidium()
    {
        super(JCEntityRegistry.leptictidium);

        this.animator = new AnimationLeptictidium();

        try
        {
            this.model = getDefaultTabulaModel();
        } catch (Exception e)
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
        return 0.6F;
    }

    @Override
    public float getBabyScaleAdjustment()
    {
        return 0.15F;
    }

    @Override
    public float getShadowSize()
    {
        return 0.45F;
    }

    @Override
    public IModelAnimator getModelAnimator(int geneticVariant)
    {
        return animator;
    }
}
