package net.timeless.jurassicraft.client.dinosaur.renderdef;

import net.timeless.unilib.client.model.json.IModelAnimator;
import net.timeless.unilib.client.model.json.ModelJson;
import net.minecraft.client.model.ModelBase;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.timeless.jurassicraft.client.model.animation.AnimationCarnotaurus;
import net.timeless.jurassicraft.common.entity.base.JCEntityRegistry;

@SideOnly(Side.CLIENT)
public class RenderDefCarnotaurus extends RenderDinosaurDefinition
{
    private IModelAnimator animator;
    private ModelJson model;

    public RenderDefCarnotaurus()
    {
        super(JCEntityRegistry.carnotaurus);

        this.animator = new AnimationCarnotaurus();

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
        return 1.3F;
    }

    @Override
    public float getBabyScaleAdjustment()
    {
        return 0.25F;
    }

    @Override
    public float getShadowSize()
    {
        return 0.65F;
    }

    @Override
    public IModelAnimator getModelAnimator()
    {
        return animator;
    }
}
