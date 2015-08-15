package net.timeless.jurassicraft.client.render.renderdef;

import net.minecraft.client.model.ModelBase;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.timeless.jurassicraft.client.model.animation.AnimationVelociraptor;
import net.timeless.jurassicraft.client.model.animation.raptorsquad.AnimationBlue;
import net.timeless.jurassicraft.common.entity.base.JCEntityRegistry;
import net.timeless.unilib.client.model.json.IModelAnimator;
import net.timeless.unilib.client.model.json.ModelJson;

@SideOnly(Side.CLIENT)
public class RenderDefVelociraptor extends RenderDinosaurDefinition
{
    private IModelAnimator animator;
    private ModelJson model;

    private ModelJson modelBlue;
    private ModelJson modelEcho;
    private ModelJson modelDelta;
    private ModelJson modelCharlie;

    private IModelAnimator animatorBlue;
    private IModelAnimator animatorEcho;
    private IModelAnimator animatorDelta;
    private IModelAnimator animatorCharlie;

    public RenderDefVelociraptor()
    {
        super(JCEntityRegistry.velociraptor);

        this.animator = new AnimationVelociraptor();

        this.animatorBlue = new AnimationBlue();
        this.animatorEcho = new AnimationVelociraptor();
        this.animatorDelta = new AnimationVelociraptor();
        this.animatorCharlie = new AnimationVelociraptor();

        try
        {
            this.model = getDefaultTabulaModel();

            this.modelBlue = getDefaultTabulaModel(1);
            this.modelEcho = getDefaultTabulaModel(2);
            this.modelDelta = getDefaultTabulaModel(3);
            this.modelCharlie = getDefaultTabulaModel(4);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    @Override
    public ModelBase getModel(int geneticVariant)
    {
        switch (geneticVariant)
        {
            case 1:
                return modelBlue;
            case 2:
                return modelEcho;
            case 3:
                return modelDelta;
            case 4:
                return modelCharlie;
            default:
                return model;
        }
    }

    @Override
    public float getAdultScaleAdjustment()
    {
        return 1F;
    }

    @Override
    public float getBabyScaleAdjustment()
    {
        return 0.3F;
    }

    @Override
    public float getShadowSize()
    {
        return 0.65F;
    }

    @Override
    public IModelAnimator getModelAnimator(int geneticVariant)
    {
        switch (geneticVariant)
        {
            case 1:
                return animatorBlue;
            case 2:
                return animatorEcho;
            case 3:
                return animatorDelta;
            case 4:
                return animatorCharlie;
            default:
                return animator;
        }
    }
}
