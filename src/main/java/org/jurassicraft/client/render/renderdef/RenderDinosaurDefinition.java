package org.jurassicraft.client.render.renderdef;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.timeless.unilib.client.model.json.IModelAnimator;
import net.timeless.unilib.client.model.json.TabulaModelHelper;
import org.jurassicraft.client.model.ModelDinosaur;
import org.jurassicraft.client.render.entity.RenderDinosaur;
import org.jurassicraft.client.render.entity.RenderDinosaurMultilayer;
import org.jurassicraft.common.dinosaur.Dinosaur;
import org.jurassicraft.common.entity.base.EnumGrowthStage;

@SideOnly(Side.CLIENT)
public abstract class RenderDinosaurDefinition
{
    private final Dinosaur dinosaur;

    public RenderDinosaurDefinition(Dinosaur dinosaur)
    {
        this.dinosaur = dinosaur;
    }

    public abstract ModelBase getModel(int geneticVariant, EnumGrowthStage stage);

    public IModelAnimator getModelAnimator(int geneticVariant)
    {
        return null;
    }

    public float getRenderXOffset(int geneticVariant)
    {
        return 0.0F;
    }

    public float getRenderYOffset(int geneticVariant)
    {
        return 0.0F;
    }

    public float getRenderZOffset(int geneticVariant)
    {
        return 0.0F;
    }

    public abstract float getAdultScaleAdjustment();

    public abstract float getBabyScaleAdjustment();

    public abstract float getShadowSize();

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

    public ModelDinosaur getDefaultTabulaModel() throws Exception
    {
        return getDefaultTabulaModel(0);
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
