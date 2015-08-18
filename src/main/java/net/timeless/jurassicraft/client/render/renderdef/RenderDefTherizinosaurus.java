package net.timeless.jurassicraft.client.render.renderdef;

import net.minecraft.client.model.ModelBase;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.timeless.jurassicraft.client.model.animation.AnimationTherizinosaurus;
import net.timeless.jurassicraft.common.entity.base.EnumGrowthStage;
import net.timeless.jurassicraft.common.entity.base.JCEntityRegistry;
import net.timeless.unilib.client.model.json.IModelAnimator;
import net.timeless.unilib.client.model.json.ModelJson;

@SideOnly(Side.CLIENT)
public class RenderDefTherizinosaurus extends RenderDinosaurDefinition
{
    private IModelAnimator animator;
    private ModelJson model;

    public RenderDefTherizinosaurus()
    {
        super(JCEntityRegistry.therizinosaurus);

        this.animator = new AnimationTherizinosaurus();

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
    public float getRenderYOffset(int geneticVariant)
    {
        return 1.0F;
    }

    @Override
    public float getAdultScaleAdjustment()
    {
        return 3.5F;
    }

    @Override
    public float getBabyScaleAdjustment()
    {
        return 0.55F;
    }

    @Override
    public float getShadowSize()
    {
        return 0.55F;
    }

    @Override
    public IModelAnimator getModelAnimator(int geneticVariant)
    {
        return animator;
    }
}
