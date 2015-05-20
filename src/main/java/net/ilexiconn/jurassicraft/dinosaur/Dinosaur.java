package net.ilexiconn.jurassicraft.dinosaur;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

import net.ilexiconn.jurassicraft.JurassiCraft;
import net.ilexiconn.jurassicraft.entity.base.EntityDinosaur;
import net.ilexiconn.llibrary.client.model.entity.animation.IModelAnimator;
import net.ilexiconn.llibrary.client.model.tabula.ModelJson;
import net.ilexiconn.llibrary.json.JsonHelper;
import net.ilexiconn.llibrary.json.container.JsonHitbox;
import net.minecraft.client.model.ModelBase;

import org.apache.commons.io.IOUtils;

public abstract class Dinosaur
{
    public abstract Class<? extends EntityDinosaur> getDinosaurClass();
    
    public abstract int getEggPrimaryColor();
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
    public abstract Object Obj();
    
    public abstract String[] getMaleTextures();
    public abstract String[] getFemaleTextures();
    
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
            
                IModelAnimator modelAnimator = getModelAnimator();
                
                if (modelAnimator != null)
                {
                   return new ModelJson(JsonHelper.parseTabulaModel(stream), modelAnimator);
                }
                else
                {
                    return new ModelJson(JsonHelper.parseTabulaModel(stream));
                }
            }
        }

        zipFile.close();
        
        return null;
    }
}
