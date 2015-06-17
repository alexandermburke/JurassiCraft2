package net.ilexiconn.jurassicraft.dinosaur;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipInputStream;

import net.ilexiconn.jurassicraft.JurassiCraft;
import net.ilexiconn.jurassicraft.entity.base.EntityDinosaur;
import net.ilexiconn.jurassicraft.json.JsonHitbox;
import net.ilexiconn.llibrary.client.model.entity.animation.IModelAnimator;
import net.ilexiconn.llibrary.client.model.tabula.ModelJson;
import net.ilexiconn.llibrary.common.json.JsonHelper;
import net.minecraft.client.model.ModelBase;

import org.apache.commons.io.IOUtils;

public abstract class Dinosaur
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

    public ModelJson getTabulaModel(String tabulaModel) throws Exception
    {
        try(ZipInputStream inputStream = new ZipInputStream(JurassiCraft.class.getResourceAsStream(tabulaModel + ".tbl")))
        {
            ZipEntry entry;
            while ((entry = inputStream.getNextEntry()) != null)
            {
                if (entry.getName().equals("model.json"))
                {
                    IModelAnimator modelAnimator = getModelAnimator();

                    if (modelAnimator != null)
                    {
                        return new ModelJson(JsonHelper.parseTabulaModel(inputStream), modelAnimator);
                    }
                    else
                    {
                        return new ModelJson(JsonHelper.parseTabulaModel(inputStream));
                    }
                }
            }
        }
        return null;
    }
}
