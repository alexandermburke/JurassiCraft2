package org.jurassicraft.client.render.renderdef;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.timeless.unilib.client.model.json.IModelAnimator;
import net.timeless.unilib.client.model.json.ModelJson;
import net.timeless.unilib.client.model.json.TabulaModelHelper;
import org.jurassicraft.JurassiCraft;
import org.jurassicraft.client.model.ModelDinosaur;
import org.jurassicraft.client.render.entity.RenderDinosaur;
import org.jurassicraft.common.dinosaur.Dinosaur;
import org.jurassicraft.common.entity.base.EnumGrowthStage;

@SideOnly(Side.CLIENT)
public class RenderDinosaurDefinition
{
    private final Dinosaur dinosaur;
    private final IModelAnimator animator;

    private final ModelJson modelAdult;
    private final ModelJson modelInfant;
    private ModelJson modelJuvenile;
    private ModelJson modelAdolescent;

    private float adultScaleAdjustment = 1.0F;
    private float babyScaleAdjustment = 0.325F;
    private float shadowSize = 0.65F;
    private float renderXOffset = 0.0F;
    private float renderYOffset = 0.0F;
    private float renderZOffset = 0.0F;

    public RenderDinosaurDefinition(Dinosaur dinosaur, IModelAnimator animator, float adultScaleAdjustment, float babyScaleAdjustment, float parShadowSize, float parRenderXOffset, float parRenderYOffset, float parRenderZOffset)
    {
        this.dinosaur = dinosaur;
        this.animator = animator;
        this.adultScaleAdjustment = adultScaleAdjustment;
        this.babyScaleAdjustment = babyScaleAdjustment;
        this.shadowSize = parShadowSize;
        this.renderXOffset = parRenderXOffset;
        this.renderYOffset = parRenderYOffset;
        this.renderZOffset = parRenderZOffset;

        this.modelAdult = getDefaultTabulaModel("adult");
        this.modelInfant = getDefaultTabulaModel("infant");

        if (dinosaur.useAllGrowthStages())
        {
            this.modelJuvenile = getDefaultTabulaModel("juvenile");
            this.modelAdolescent = getDefaultTabulaModel("adolescent");
        }
    }

    public ModelBase getModel(EnumGrowthStage stage)
    {
        switch (stage)
        {
            case INFANT:
                return modelInfant;
            case JUVENILE:
                return dinosaur.useAllGrowthStages() ? modelJuvenile : modelInfant;
            case ADOLESCENT:
                return dinosaur.useAllGrowthStages() ? modelAdolescent : modelAdult;
            default:
                return modelAdult;
        }
    }

    public IModelAnimator getModelAnimator()
    {
        return animator;
    }

    public float getRenderXOffset()
    {
        return renderXOffset;
    }

    public float getRenderYOffset()
    {
        return renderYOffset;
    }

    public float getRenderZOffset()
    {
        return renderZOffset;
    }

    public float getAdultScaleAdjustment()
    {
        return adultScaleAdjustment;
    }

    public float getBabyScaleAdjustment()
    {
        return babyScaleAdjustment;
    }

    public float getShadowSize()
    {
        return shadowSize;
    }

    public ModelDinosaur getTabulaModel(String tabulaModel) throws Exception
    {
        return new ModelDinosaur(TabulaModelHelper.parseModel(tabulaModel), getModelAnimator());
    }

    public ModelDinosaur getDefaultTabulaModel(String stage)
    {
        String model = "/assets/jurassicraft/models/entities/" + dinosaur.getName().toLowerCase() + "/" + stage + "/" + dinosaur.getName().toLowerCase() + "_" + stage + "_idle";
        try
        {
            return getTabulaModel(model);
        }
        catch (Exception e)
        {
            JurassiCraft.instance.getLogger().fatal("Couldn't load model " + model, e);
            return null;
        }
    }

    public Dinosaur getDinosaur()
    {
        return dinosaur;
    }

    public RenderLiving getRenderer()
    {
        return new RenderDinosaur(this);
    }
}
