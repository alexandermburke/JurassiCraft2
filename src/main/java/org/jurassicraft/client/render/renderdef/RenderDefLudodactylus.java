package org.jurassicraft.client.render.renderdef;

import net.minecraft.client.model.ModelBase;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.timeless.unilib.client.model.json.IModelAnimator;
import net.timeless.unilib.client.model.json.ModelJson;
import org.jurassicraft.client.model.animation.AnimationLudodactylus;
import org.jurassicraft.common.entity.base.EnumGrowthStage;
import org.jurassicraft.common.entity.base.JCEntityRegistry;

@SideOnly(Side.CLIENT)
public class RenderDefLudodactylus extends RenderDinosaurDefinition
{
    private IModelAnimator animator;
    private ModelJson model;

    public RenderDefLudodactylus()
    {
        super(JCEntityRegistry.ludodactylus);

        this.animator = new AnimationLudodactylus();

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
        return 0.8F;
    }

    @Override
    public float getBabyScaleAdjustment()
    {
        return 0.35F;
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
