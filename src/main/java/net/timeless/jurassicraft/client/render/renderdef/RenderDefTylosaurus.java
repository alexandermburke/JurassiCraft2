package net.timeless.jurassicraft.client.render.renderdef;

import net.minecraft.client.model.ModelBase;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.timeless.jurassicraft.client.model.animation.AnimationTylosaurus;
import net.timeless.jurassicraft.common.entity.base.JCEntityRegistry;
import net.timeless.unilib.client.model.json.IModelAnimator;
import net.timeless.unilib.client.model.json.ModelJson;

@SideOnly(Side.CLIENT)
public class RenderDefTylosaurus extends RenderDinosaurDefinition
{
    private IModelAnimator animator;
    private ModelJson model;

    public RenderDefTylosaurus()
    {
        super(JCEntityRegistry.tylosaurus);

        this.animator = new AnimationTylosaurus();

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
        return 2.2F;
    }

    @Override
    public float getBabyScaleAdjustment()
    {
        return 0.45F;
    }

    @Override
    public float getRenderZOffset(int geneticVariant)
    {
        return 1.0F;
    }

    @Override
    public float getShadowSize()
    {
        return 0.85F;
    }

    @Override
    public IModelAnimator getModelAnimator(int geneticVariant)
    {
        return animator;
    }
}
