package net.ilexiconn.jurassicraft.dinosaur;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import net.ilexiconn.jurassicraft.JurassiCraft;
import net.ilexiconn.jurassicraft.entity.base.EntityDinosaur;
import net.ilexiconn.jurassicraft.json.JsonHitbox;
import net.ilexiconn.llibrary.client.model.entity.animation.IModelAnimator;
import net.ilexiconn.llibrary.client.model.tabula.ModelJson;
import net.ilexiconn.llibrary.common.json.JsonHelper;
import net.ilexiconn.llibrary.common.json.container.JsonTabulaModel;
import net.minecraft.client.model.ModelBase;

public abstract class Dinosaur implements Comparable<Dinosaur>
{
    public abstract String getName();

    public abstract Class<? extends EntityDinosaur> getDinosaurClass();

    public abstract int getEggPrimaryColor();

    public abstract int getPeriod();

    public abstract int getEggSecondaryColor();

    public abstract double getBabyHealth();

    public abstract double getAdultHealth();

    public abstract double getBabySpeed();

    public abstract double getAdultSpeed();

    public abstract double getBabyStrength();

    public abstract double getAdultStrength();

    public abstract double getBabyLength();

    public abstract double getBabyHeight();

    public abstract double getAdultLength();

    public abstract double getAdultHeight();

    public abstract double getBabyKnockback();

    public abstract double getAdultKnockback();

    public abstract double getMinLipids();

    public abstract double getMinProximate();

    public abstract double getMinMinerals();

    public abstract double getMinVitamins();

    public abstract double getRidingSpeed();

    public abstract float getAdultAge();

    public abstract float getXZBoxMin();

    public abstract float getYBoxMin();

    public abstract float getXZBoxDelta();

    public abstract float getYBoxDelta();

    public abstract float getScaleAdjustment();

    public abstract float getShadowSize();

    public abstract ModelBase getModel();

    public abstract String[] getMaleTextures();

    public abstract String[] getFemaleTextures();

    public double getAttackSpeed()
    {
        return 0.5D;
    }

    public List<JsonHitbox> getHitBoxList()
    {
        return new ArrayList<JsonHitbox>();
    }

    public IModelAnimator getModelAnimator()
    {
        return null;
    }

    public boolean shouldRegister()
    {
        return true;
    }

    public float getRenderYOffset()
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

    @Override
    public int compareTo(Dinosaur dino)
    {
        return this.getName().compareTo(dino.getName());
    }
}
