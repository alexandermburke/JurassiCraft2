package net.timeless.jurassicraft.client.dinosaur.renderdef;

import net.timeless.unilib.client.model.json.IModelAnimator;
import net.timeless.unilib.client.model.json.ModelJson;
import net.minecraft.client.model.ModelBase;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.timeless.jurassicraft.client.model.animation.AnimationDilophosaurus;
import net.timeless.jurassicraft.common.entity.base.JCEntityRegistry;

@SideOnly(Side.CLIENT)
public class RenderDefDilophosaurus extends RenderDinosaurDefinition
{
    private IModelAnimator animator;
    private ModelJson model;

    public RenderDefDilophosaurus()
    {
        super(JCEntityRegistry.dilophosaurus);

        this.animator = new AnimationDilophosaurus();

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
        return 1.7F;
    }

    @Override
    public float getBabyScaleAdjustment()
    {
        return 0.32F;
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
