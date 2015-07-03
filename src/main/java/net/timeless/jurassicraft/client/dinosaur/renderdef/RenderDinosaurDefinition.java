package net.timeless.jurassicraft.client.dinosaur.renderdef;

import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import net.ilexiconn.llibrary.client.model.entity.animation.IModelAnimator;
import net.ilexiconn.llibrary.client.model.tabula.ModelJson;
import net.ilexiconn.llibrary.common.json.JsonHelper;
import net.ilexiconn.llibrary.common.json.container.JsonTabulaModel;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.timeless.jurassicraft.JurassiCraft;
import net.timeless.jurassicraft.client.render.entity.RenderDinosaur;
import net.timeless.jurassicraft.client.render.entity.RenderDinosaurMultilayer;
import net.timeless.jurassicraft.dinosaur.Dinosaur;

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

    public ModelJson getTabulaModel(String tabulaModel) throws Exception
    {
        try (ZipInputStream inputStream = new ZipInputStream(JurassiCraft.class.getResourceAsStream(tabulaModel + ".tbl")))
        {
            ZipEntry entry;

            while ((entry = inputStream.getNextEntry()) != null)
            {
                if (entry.getName().equals("model.json"))
                {
                    IModelAnimator modelAnimator = getModelAnimator();

                    JsonTabulaModel parseTabulaModel = JsonHelper.parseTabulaModel(inputStream);

                    inputStream.close();

                    if (modelAnimator != null)
                    {
                        return new ModelJson(parseTabulaModel, modelAnimator);
                    }
                    else
                    {
                        return new ModelJson(parseTabulaModel);
                    }
                }
            }

            inputStream.close();
        }

        return null;
    }

    public ModelJson getDefaultTabulaModel() throws Exception
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
