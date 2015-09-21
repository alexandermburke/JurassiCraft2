package org.jurassicraft.client.render.renderdef;

import net.minecraft.client.model.ModelBase;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.jurassicraft.client.model.animation.AnimationApatosaurus;
import org.jurassicraft.common.entity.base.EnumGrowthStage;
import org.jurassicraft.common.entity.base.JCEntityRegistry;
import net.timeless.unilib.client.model.json.IModelAnimator;
import net.timeless.unilib.client.model.json.ModelJson;

@SideOnly(Side.CLIENT)
public class RenderDefApatosaurus extends RenderDinosaurDefinition
{
    private IModelAnimator animator;
    private ModelJson model;

    public RenderDefApatosaurus()
    {
        super(JCEntityRegistry.apatosaurus);

        this.animator = new AnimationApatosaurus();

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
        return 2.0F;
    }

    @Override
    public float getBabyScaleAdjustment()
    {
        return 0.25F;
    }

    @Override
    public float getShadowSize()
    {
        return 1.5F;
    }

    @Override
    public float getRenderXOffset(int geneticVariant)
    {
        return 0.0F;
    }

    @Override
    public float getRenderYOffset(int geneticVariant)
    {
        return 0.0F;
    }

    @Override
    public float getRenderZOffset(int geneticVariant)
    {
        return 0.1F;
    }

    @Override
    public IModelAnimator getModelAnimator(int geneticVariant)
    {
        return animator;
    }
}
