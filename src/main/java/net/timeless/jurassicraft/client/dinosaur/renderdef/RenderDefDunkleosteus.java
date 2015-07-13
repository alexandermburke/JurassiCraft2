package net.timeless.jurassicraft.client.dinosaur.renderdef;

import net.ilexiconn.llibrary.client.model.entity.animation.IModelAnimator;
import net.ilexiconn.llibrary.client.model.tabula.ModelJson;
import net.minecraft.client.model.ModelBase;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.timeless.jurassicraft.client.model.animation.AnimationCoelacanth;
import net.timeless.jurassicraft.entity.base.JCEntityRegistry;

@SideOnly(Side.CLIENT)
public class RenderDefDunkleosteus extends RenderDinosaurDefinition
{
    private IModelAnimator animator;
    private ModelJson model;

    public RenderDefDunkleosteus()
    {
        super(JCEntityRegistry.dunkleosteus);

        this.animator = new AnimationCoelacanth();

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
        return 2.1F;
    }

    @Override
    public float getBabyScaleAdjustment()
    {
        return 0.55F;
    }

    @Override
    public float getShadowSize()
    {
        return 0.35F;
    }

    @Override
    public float getRenderXOffset()
    {
        return 0.0F;
    }

    @Override
    public float getRenderYOffset()
    {
        return 1.0F;
    }

    @Override
    public float getRenderZOffset()
    {
        return -0.25F;
    }

    @Override
    public IModelAnimator getModelAnimator()
    {
        return animator;
    }
}
