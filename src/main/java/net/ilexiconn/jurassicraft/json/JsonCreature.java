package net.ilexiconn.jurassicraft.json;

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

import com.google.common.collect.Lists;

public class JsonCreature
{
	private boolean canSwim;
	
    private String name;
    private List<JsonHitbox> hitboxList;
    private List<String> maleTextures;
    private List<String> femaleTextures;

    private boolean shouldRegister = true;
    private String modelClass;
    private String tabulaModel;

    private String entityClass;
    private String animatorClass;

    private List<String> attackTargets;
    
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

    public List<String> getAttackTargets()
    {
    	if(attackTargets == null)
    		attackTargets = Lists.newArrayList();
    	
    	return attackTargets;
    }
    
    public String getEntityClass()
    {
        return entityClass;
    }

	public boolean canSwim()
	{
		return canSwim;
	}
}