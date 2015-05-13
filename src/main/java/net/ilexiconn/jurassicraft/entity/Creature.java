package net.ilexiconn.jurassicraft.entity;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

import net.ilexiconn.jurassicraft.JurassiCraft;
import net.ilexiconn.llibrary.client.model.entity.animation.IModelAnimator;
import net.ilexiconn.llibrary.client.model.tabula.ModelJson;
import net.ilexiconn.llibrary.json.JsonFactory;
import net.ilexiconn.llibrary.json.JsonHelper;
import net.ilexiconn.llibrary.json.container.JsonHitbox;
import net.minecraft.client.model.ModelBase;

import org.apache.commons.io.IOUtils;

public class Creature
{
    private String name;
    private List<JsonHitbox> hitboxList;
    private List<String> maleTextures;
    private List<String> femaleTextures;

    private boolean shouldRegister = true;
    private String modelClass;
    private String tabulaModel;

    private String entityClass;
    private String animatorClass;
    
    private double babyHealth;
    private double babyStrength;
    private double babySpeed;
    private double babyKnockback;
    private double minProximate;
    private double minMinerals;
    private double minVitamins;
    private double minLipids;
    private double adultHealth;
    private double adultStrength;
    private double adultSpeed;
    private double adulKnockback;
    private double ridingSpeed;

    private float adultAge;
    private float babyLength;
    private float babyHeight;
    private float adultLength;
    private float adultHeight;
    private float xzBoxMin;
    private float yBoxMin;
    private float xzBoxDelta;
    private float yBoxDelta;
    private float scaleAdjustment;
    private float shadowSize;

    private int eggPrimaryColor;
    private int eggSecondaryColor;

    public String getName()
    {
        return name;
    }

    public List<JsonHitbox> getHitboxList()
    {
        return hitboxList;
    }

    public List<String> getMaleTextures()
    {
        return maleTextures;
    }

    public List<String> getFemaleTextures()
    {
        return femaleTextures;
    }

    public double getBabyHealth()
    {
        return babyHealth;
    }

    public double getBabyStrength()
    {
        return babyStrength;
    }

    public double getBabySpeed()
    {
        return babySpeed;
    }

    public double getBabyKnockback()
    {
        return babyKnockback;
    }

    public double getMinProximate()
    {
        return minProximate;
    }

    public double getMinMinerals()
    {
        return minMinerals;
    }

    public double getMinVitamins()
    {
        return minVitamins;
    }

    public double getMinLipids()
    {
        return minLipids;
    }

    public double getAdultHealth()
    {
        return adultHealth;
    }

    public double getAdultStrength()
    {
        return adultStrength;
    }

    public double getAdultSpeed()
    {
        return adultSpeed;
    }

    public double getAdultKnockback()
    {
        return adulKnockback;
    }

    public double getRidingSpeed()
    {
        return ridingSpeed;
    }

    public float getAdultAge()
    {
        return adultAge;
    }

    public float getBabyLength()
    {
        return babyLength;
    }

    public float getBabyHeight()
    {
        return babyHeight;
    }

    public float getAdultLength()
    {
        return adultLength;
    }

    public float getAdultHeight()
    {
        return adultHeight;
    }

    public float getXzBoxMin()
    {
        return xzBoxMin;
    }

    public float getYBoxMin()
    {
        return yBoxMin;
    }

    public float getXzBoxDelta()
    {
        return xzBoxDelta;
    }

    public float getyBoxDelta()
    {
        return yBoxDelta;
    }

    public float getScaleAdjustment()
    {
        return scaleAdjustment;
    }

    public float getShadowSize()
    {
        return shadowSize;
    }

    public int getEggPrimaryColor()
    {
        return eggPrimaryColor;
    }

    public int getEggSecondaryColor()
    {
        return eggSecondaryColor;
    }

    public String toString()
    {
        return JsonFactory.getGson().toJson(this);
    }

    public boolean shouldRegister()
    {
        return shouldRegister;
    }

    public ModelBase getModel()
    {
        ModelBase model = null;

        try
        {
            if (modelClass != null)
            {
                model = (ModelBase) Class.forName(modelClass).newInstance();
            }
            else if (tabulaModel != null)
            {
                File tempFile = File.createTempFile(tabulaModel, ".tbl");
                tempFile.deleteOnExit();

                try (FileOutputStream out = new FileOutputStream(tempFile))
                {
                    IOUtils.copy(JurassiCraft.class.getResourceAsStream(tabulaModel + ".tbl"), out);
                }

                ZipFile zipFile = new ZipFile(tempFile);
                Enumeration<? extends ZipEntry> entries = zipFile.entries();

                while (entries.hasMoreElements())
                {
                    ZipEntry entry = entries.nextElement();

                    if (entry.getName().equals("model.json"))
                    {
                        InputStream stream = zipFile.getInputStream(entry);
                        if (animatorClass != null)
                        {
                            model = new ModelJson(JsonHelper.parseTabulaModel(stream), (IModelAnimator) Class.forName(animatorClass).newInstance());
                        }
                        else
                        {
                            model = new ModelJson(JsonHelper.parseTabulaModel(stream));
                        }
                    }
                }

                zipFile.close();
            }
            else
            {
                model = (ModelBase) Class.forName("net.ilexiconn.jurassicraft.client.model.entity.Model" + getName()).newInstance();
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        return model;
    }
    
    public String getEntityClass()
    {
        return entityClass;
    }
}