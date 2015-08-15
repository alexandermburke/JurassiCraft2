package net.timeless.jurassicraft.client.render.renderdef;

import net.minecraft.client.model.ModelBase;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.timeless.jurassicraft.client.model.animation.AnimationBrachiosaurus;
import net.timeless.jurassicraft.common.entity.base.JCEntityRegistry;
import net.timeless.unilib.client.model.json.IModelAnimator;
import net.timeless.unilib.client.model.json.ModelJson;

@SideOnly(Side.CLIENT)
public class RenderDefBrachiosaurus extends RenderDinosaurDefinition
{
    private IModelAnimator animator;
    private ModelJson model;

    public RenderDefBrachiosaurus()
    {
        super(JCEntityRegistry.brachiosaurus);

        this.animator = new AnimationBrachiosaurus();

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
        return 1.0F;
    }

    @Override
    public IModelAnimator getModelAnimator(int geneticVariant)
    {
        return animator;
    }
}
