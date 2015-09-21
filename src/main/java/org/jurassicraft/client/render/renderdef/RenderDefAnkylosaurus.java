package org.jurassicraft.client.render.renderdef;

import net.minecraft.client.model.ModelBase;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.jurassicraft.client.model.animation.AnimationAnkylosaurus;
import org.jurassicraft.common.entity.base.EnumGrowthStage;
import org.jurassicraft.common.entity.base.JCEntityRegistry;
import net.timeless.unilib.client.model.json.IModelAnimator;
import net.timeless.unilib.client.model.json.ModelJson;

@SideOnly(Side.CLIENT)
public class RenderDefAnkylosaurus extends RenderDinosaurDefinition
{
    private IModelAnimator animator;
    private ModelJson model;

    public RenderDefAnkylosaurus()
    {
        super(JCEntityRegistry.anklyosaurus);

        this.animator = new AnimationAnkylosaurus();

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
        return 2.3F;
    }

    @Override
    public float getBabyScaleAdjustment()
    {
        return 0.45F;
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
