package org.jurassicraft.client.render.renderdef;

import org.jurassicraft.JurassiCraft;
import org.jurassicraft.client.model.ModelDinosaur;
import org.jurassicraft.client.render.entity.RenderDinosaur;
import org.jurassicraft.client.render.entity.RenderDinosaurMultilayer;
import org.jurassicraft.client.render.entity.RenderIndominus;
import org.jurassicraft.common.dinosaur.Dinosaur;
import org.jurassicraft.common.dinosaur.DinosaurIndominus;
import org.jurassicraft.common.entity.base.EnumGrowthStage;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.timeless.unilib.client.model.json.IModelAnimator;
import net.timeless.unilib.client.model.json.ModelJson;
import net.timeless.unilib.client.model.json.TabulaModelHelper;

@SideOnly(Side.CLIENT)
public class RenderDinosaurDefinition
{
    private final Dinosaur dinosaur;
    private final IModelAnimator animator;

    private final ModelJson modelAdult;
    private final ModelJson modelJuvenile;
    private final ModelJson modelAdolescent;
    private final ModelJson modelInfant;

    private float adultScaleAdjustment = 1.0F;
    private float babyScaleAdjustment = 0.325F;
    private float shadowSize = 0.65F;
    private float renderXOffset = 0.0F;
    private float renderYOffset = 0.0F;
    private float renderZOffset = 0.0F;

    public RenderDinosaurDefinition(Dinosaur parDinosaur, IModelAnimator parAnimator, float parAdultScaleAdjustment, float parBabyScaleAdjustment, float parShadowSize, float parRenderXOffset, float parRenderYOffset, float parRenderZOffset)
    {
        dinosaur = parDinosaur;
        animator = parAnimator;
        adultScaleAdjustment = parAdultScaleAdjustment;
        babyScaleAdjustment = parBabyScaleAdjustment;
        shadowSize = parShadowSize;
        renderXOffset = parRenderXOffset;
        renderYOffset = parRenderYOffset;
        renderZOffset = parRenderZOffset;

        modelAdult = getDefaultTabulaModel("adult");
        modelInfant = getDefaultTabulaModel("infant");
        modelJuvenile = getDefaultTabulaModel("juvenile");
        modelAdolescent = getDefaultTabulaModel("adolescent");
    }

    public ModelBase getModel(EnumGrowthStage stage)
    {
        switch (stage)
        {
            case INFANT:
                return modelInfant;
            case JUVENILE:
                return modelJuvenile;
            case ADOLESCENT:
                return modelAdolescent;
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
        if (dinosaur instanceof DinosaurIndominus)
        {
            return new RenderIndominus(this);
        }
        else
        {
            String[] maleOverlayTextures = dinosaur.getMaleOverlayTextures(EnumGrowthStage.INFANT);

            if (maleOverlayTextures != null && maleOverlayTextures.length > 0)
            {
                return new RenderDinosaurMultilayer(this);
            }
            else
            {
                return new RenderDinosaur(this);
            }
        }
    }
}
