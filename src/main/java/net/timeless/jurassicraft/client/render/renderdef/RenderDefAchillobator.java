package net.timeless.jurassicraft.client.render.renderdef;

import net.minecraft.client.model.ModelBase;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.timeless.jurassicraft.client.model.animation.AnimationAchillobator;
import net.timeless.jurassicraft.common.entity.base.JCEntityRegistry;
import net.timeless.unilib.client.model.json.IModelAnimator;
import net.timeless.unilib.client.model.json.ModelJson;

@SideOnly(Side.CLIENT)
public class RenderDefAchillobator extends RenderDinosaurDefinition
{
    private IModelAnimator animator;
    private ModelJson model;

    public RenderDefAchillobator()
    {
        super(JCEntityRegistry.achillobator);

        this.animator = new AnimationAchillobator();

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
        return 1.1F;
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
    public IModelAnimator getModelAnimator(int geneticVariant)
    {
        return animator;
    }
}
