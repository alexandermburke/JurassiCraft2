package net.ilexiconn.jurassicraft.json;

import net.ilexiconn.llibrary.client.model.entity.animation.IModelAnimator;
import net.ilexiconn.llibrary.client.model.tabula.ModelJson;
import net.ilexiconn.llibrary.json.JsonFactory;
import net.ilexiconn.llibrary.json.JsonHelper;
import net.ilexiconn.llibrary.json.container.JsonHitbox;
import net.minecraft.client.model.ModelBase;

import java.util.List;

public class JsonCreature
{
    private String name;
    private List<JsonHitbox> hitboxList;
    private List<String> maleTextures;
    private List<String> femaleTextures;

    private boolean shouldRegister = true;
    private String modelClass;
    private String tabulaModel;

    private String animatorClass;
    
    private double minHealth;
    private double minStrength;
    private double minSpeed;
    private double minKnockback;
    private double minProximate;
    private double minMinerals;
    private double minVitamins;
    private double minLipids;
    private double maxHealth;
    private double maxStrength;
    private double maxSpeed;
    private double maxKnockback;
    private double ridingSpeed;

    private float adultAge;
    private float minLength;
    private float minHeight;
    private float maxLength;
    private float maxHeight;
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

    public double getMinHealth()
    {
        return minHealth;
    }

    public double getMinStrength()
    {
        return minStrength;
    }

    public double getMinSpeed()
    {
        return minSpeed;
    }

    public double getMinKnockback()
    {
        return minKnockback;
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

    public double getMaxHealth()
    {
        return maxHealth;
    }

    public double getMaxStrength()
    {
        return maxStrength;
    }

    public double getMaxSpeed()
    {
        return maxSpeed;
    }

    public double getMaxKnockback()
    {
        return maxKnockback;
    }

    public double getRidingSpeed()
    {
        return ridingSpeed;
    }

    public float getAdultAge()
    {
        return adultAge;
    }

    public float getMinLength()
    {
        return minLength;
    }

    public float getMinHeight()
    {
        return minHeight;
    }

    public float getMaxLength()
    {
        return maxLength;
    }

    public float getMaxHeight()
    {
        return maxHeight;
    }

    public float getXzBoxMin()
    {
        return xzBoxMin;
    }

    public float getyBoxMin()
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
                if(animatorClass != null)
                {
                    model = new ModelJson(JsonHelper.parseTabulaModel(tabulaModel), (IModelAnimator) Class.forName(animatorClass).newInstance());
                }
                else
                {
                    model = new ModelJson(JsonHelper.parseTabulaModel(tabulaModel));
                }
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
}