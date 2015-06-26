package net.timeless.jurassicraft.dinosaur;

import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import net.ilexiconn.llibrary.client.model.entity.animation.IModelAnimator;
import net.ilexiconn.llibrary.client.model.tabula.ModelJson;
import net.ilexiconn.llibrary.common.json.JsonHelper;
import net.ilexiconn.llibrary.common.json.container.JsonTabulaModel;
import net.minecraft.client.model.ModelBase;
import net.timeless.jurassicraft.JurassiCraft;
import net.timeless.jurassicraft.entity.base.EntityDinosaur;
import net.timeless.jurassicraft.entity.base.EntityHitbox;
import net.timeless.jurassicraft.period.EnumTimePeriod;

public abstract class Dinosaur
{
    public abstract String getName();

    public abstract Class<? extends EntityDinosaur> getDinosaurClass();

    public abstract int getEggPrimaryColor();

    public abstract EnumTimePeriod getPeriod();

    public abstract int getEggSecondaryColor();

    public abstract double getBabyHealth();

    public abstract double getAdultHealth();

    public abstract double getBabySpeed();

    public abstract double getAdultSpeed();

    public abstract double getBabyStrength();

    public abstract double getAdultStrength();

    public abstract double getBabyKnockback();

    public abstract double getAdultKnockback();

    public abstract float getMaximumAge();

    public abstract float getScaleAdjustment();

    public abstract float getShadowSize();

    public abstract ModelBase getModel();

    public abstract String[] getMaleTextures();

    public abstract String[] getFemaleTextures();

    public String[] getMaleOverlayTextures()
    {
        return new String[0];
    }

    public String[] getFemaleOverlayTextures()
    {
        return new String[0];
    }

    public double getAttackSpeed()
    {
        return 0.5D;
    }

    public List<EntityHitbox> getHitBoxList()
    {
        return new ArrayList<>();
    }

    public IModelAnimator getModelAnimator()
    {
        return null;
    }

    public boolean shouldRegister()
    {
        return true;
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

    protected String getDinosaurTexture(String subtype)
    {
        String dinosaurName = getName().toLowerCase().replaceAll(" ", "_");

        String texture = "jurassicraft:textures/entities/" + dinosaurName + "/" + dinosaurName;

        if (subtype != "")
            texture += "_" + subtype;

        return texture + ".png";
    }

    public ModelJson getDefaultTabulaModel() throws Exception
    {
        return getTabulaModel("/assets/jurassicraft/models/entities/" + getName().toLowerCase().replaceAll(" ", "_"));
    }
}
