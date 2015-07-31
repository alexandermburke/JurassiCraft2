package net.timeless.jurassicraft.client.dinosaur.renderdef;

import net.minecraft.client.model.ModelBase;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.timeless.jurassicraft.client.model.animation.AnimationCompsognathus;
import net.timeless.jurassicraft.common.entity.base.JCEntityRegistry;
import net.timeless.unilib.client.model.json.IModelAnimator;
import net.timeless.unilib.client.model.json.ModelJson;

@SideOnly(Side.CLIENT)
public class RenderDefCompsognathus extends RenderDinosaurDefinition
{
    private IModelAnimator animator;
    private ModelJson model;

    public RenderDefCompsognathus()
    {
        super(JCEntityRegistry.compsognathus);

        this.animator = new AnimationCompsognathus();

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
        return 0.1F;
    }

    @Override
    public float getBabyScaleAdjustment()
    {
        return 0.04F;
    }

    @Override
    public float getRenderYOffset()
    {
        return -12F;
    }

    @Override
    public float getShadowSize()
    {
        return 1.8F;
    }

    @Override
    public float getRenderZOffset()
    {
        return -0.8F;
    }

    @Override
    public IModelAnimator getModelAnimator()
    {
        return animator;
    }
}
