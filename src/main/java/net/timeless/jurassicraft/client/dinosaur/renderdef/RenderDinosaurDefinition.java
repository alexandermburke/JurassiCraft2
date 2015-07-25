package net.timeless.jurassicraft.client.dinosaur.renderdef;

import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import net.timeless.unilib.client.model.json.IModelAnimator;
import net.timeless.unilib.client.model.json.ModelJson;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.timeless.jurassicraft.JurassiCraft;
import net.timeless.jurassicraft.client.model.ModelDinosaur;
import net.timeless.jurassicraft.client.render.entity.RenderDinosaur;
import net.timeless.jurassicraft.client.render.entity.RenderDinosaurMultilayer;
import net.timeless.jurassicraft.common.dinosaur.Dinosaur;
import net.timeless.unilib.client.model.json.TabulaModelHelper;

public abstract class RenderDinosaurDefinition
{
    private Dinosaur dinosaur;

    public RenderDinosaurDefinition(Dinosaur dinosaur)
    {
        this.dinosaur = dinosaur;
    }

    public abstract ModelBase getModel();

    public IModelAnimator getModelAnimator()
    {
        return null;
    }

    public float getRenderXOffset()
    {
        return 0.0F;
    }

    public float getRenderYOffset()
    {
        return 0.0F;
    }

    public float getRenderZOffset()
    {
        return 0.0F;
    }

    public abstract float getAdultScaleAdjustment();

    public abstract float getBabyScaleAdjustment();

    public abstract float getShadowSize();

    public ModelDinosaur getTabulaModel(String tabulaModel) throws Exception
    {
        return new ModelDinosaur(TabulaModelHelper.parseModel(tabulaModel), getModelAnimator());
    }

    public ModelDinosaur getDefaultTabulaModel() throws Exception
    {
        return getTabulaModel("/assets/jurassicraft/models/entities/" + dinosaur.getName().toLowerCase().replaceAll(" ", "_"));
    }

    public Dinosaur getDinosaur()
    {
        return dinosaur;
    }

    public RenderLiving getRenderer()
    {
        String[] maleOverlayTextures = dinosaur.getMaleOverlayTextures();

        if (maleOverlayTextures != null && maleOverlayTextures.length > 0)
            return new RenderDinosaurMultilayer(this);
        else
            return new RenderDinosaur(this);
    }
}
