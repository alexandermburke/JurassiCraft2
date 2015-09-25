package org.jurassicraft.client.render.renderdef;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.timeless.unilib.client.model.json.IModelAnimator;
import net.timeless.unilib.client.model.json.ModelJson;
import net.timeless.unilib.client.model.json.TabulaModelHelper;
import org.jurassicraft.client.model.ModelDinosaur;
import org.jurassicraft.client.render.entity.RenderDinosaur;
import org.jurassicraft.client.render.entity.RenderDinosaurMultilayer;
import org.jurassicraft.common.dinosaur.Dinosaur;
import org.jurassicraft.common.entity.base.EnumGrowthStage;

@SideOnly(Side.CLIENT)
public class RenderDinosaurDefinition
{
    private final Dinosaur dinosaur;
    private final IModelAnimator animator;
    private final ModelJson model;
    private float adultScaleAdjustment = 1.0F;
    private float babyScaleAdjustment = 0.325F;
    private float shadowSize = 0.65F;
    private float renderXOffset = 0.0F;
    private float renderYOffset = 0.0F;
    private float renderZOffset = 0.0F;

    public RenderDinosaurDefinition(
            Dinosaur parDinosaur, 
            IModelAnimator parAnimator, 
            float parAdultScaleAdjustment, float parBabyScaleAdjustment, 
            float parShadowSize,
            float parRenderXOffset, float parRenderYOffset, float parRenderZOffset)
    {
        dinosaur = parDinosaur;
        animator = parAnimator;
        adultScaleAdjustment = parAdultScaleAdjustment;
        babyScaleAdjustment = parBabyScaleAdjustment;
        shadowSize = parShadowSize;
        renderXOffset = parRenderXOffset;
        renderYOffset = parRenderYOffset;
        renderZOffset = parRenderZOffset;
        
        model = getDefaultTabulaModel();
    }

    public ModelBase getModel(int geneticVariant, EnumGrowthStage stage)
    {
        return model;
    }

    public IModelAnimator getModelAnimator(int geneticVariant)
    {
        return animator;
    }

    public float getRenderXOffset(int geneticVariant)
    {
        return renderXOffset;
    }

    public float getRenderYOffset(int geneticVariant)
    {
        return renderYOffset;
    }

    public float getRenderZOffset(int geneticVariant)
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

    public ModelDinosaur getTabulaModel(String tabulaModel, int geneticVariant) throws Exception
    {
        return new ModelDinosaur(TabulaModelHelper.parseModel(tabulaModel), getModelAnimator(geneticVariant));
    }

    public ModelDinosaur getTabulaModel(String tabulaModel) throws Exception
    {
        return getTabulaModel(tabulaModel, 0);
    }

    public ModelDinosaur getDefaultTabulaModel(int geneticVariant) throws Exception
    {
        return getTabulaModel("/assets/jurassicraft/models/entities/"
                        + dinosaur.getName(0).toLowerCase() + "/adult/"
                        + dinosaur.getName(0).toLowerCase() + "_adult_idle"
        );
    }

    public ModelDinosaur getDefaultTabulaModel() 
    {
        try
        {
            return getDefaultTabulaModel(0);
        } catch (Exception e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }

    public Dinosaur getDinosaur()
    {
        return dinosaur;
    }

    public RenderLiving getRenderer(int geneticVariant)
    {
        String[] maleOverlayTextures = dinosaur.getMaleOverlayTextures(0, EnumGrowthStage.INFANT);

        if (maleOverlayTextures != null && maleOverlayTextures.length > 0)
            return new RenderDinosaurMultilayer(this);
        else
            return new RenderDinosaur(this);
    }
}
