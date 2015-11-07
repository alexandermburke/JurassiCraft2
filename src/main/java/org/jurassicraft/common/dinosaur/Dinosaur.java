package org.jurassicraft.common.dinosaur;

import net.minecraft.util.ResourceLocation;
import org.jurassicraft.JurassiCraft;
import org.jurassicraft.common.api.IHybrid;
import org.jurassicraft.common.entity.base.EntityDinosaur;
import org.jurassicraft.common.entity.base.EnumGrowthStage;
import org.jurassicraft.common.period.EnumTimePeriod;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class Dinosaur implements Comparable<Dinosaur>
{
    private Map<EnumGrowthStage, List<ResourceLocation>> overlays = new HashMap<EnumGrowthStage, List<ResourceLocation>>();
    private Map<EnumGrowthStage, ResourceLocation> maleTextures = new HashMap<EnumGrowthStage, ResourceLocation>();
    private Map<EnumGrowthStage, ResourceLocation> femaleTextures = new HashMap<EnumGrowthStage, ResourceLocation>();

    public void init()
    {
        String dinosaurName = getName().toLowerCase().replaceAll(" ", "_");

        File file = new File(JurassiCraft.class.getResource("/assets/jurassicraft/textures/entities/" + dinosaurName).getFile());

        for (File image : file.listFiles())
        {
            String name = image.getName();

            EnumGrowthStage stage = null;

            for (EnumGrowthStage growthStage : EnumGrowthStage.values())
            {
                if (name.contains(growthStage.name().toLowerCase()))
                {
                    stage = growthStage;
                    break;
                }
            }

            if (stage != null)
            {
                ResourceLocation resourceLocation = new ResourceLocation(JurassiCraft.MODID, "textures/entities/" + dinosaurName + "/" + name);

                if (name.contains("overlay"))
                {
                    List<ResourceLocation> growthStageOverlays = overlays.get(stage);

                    if (growthStageOverlays == null)
                    {
                        growthStageOverlays = new ArrayList<ResourceLocation>();
                    }

                    growthStageOverlays.add(resourceLocation);

                    overlays.put(stage, growthStageOverlays);
                }
                else
                {
                    if (this instanceof IHybrid)
                    {
                        maleTextures.put(stage, resourceLocation);
                        femaleTextures.put(stage, resourceLocation);
                    }
                    else
                    {
                        if (name.contains("female"))
                        {
                            femaleTextures.put(stage, resourceLocation);
                        }
                        else
                        {
                            maleTextures.put(stage, resourceLocation);
                        }
                    }
                }
            }
        }
    }

    public abstract String getName();

    public abstract Class<? extends EntityDinosaur> getDinosaurClass();

    public abstract int getEggPrimaryColorMale();

    public abstract int getEggSecondaryColorMale();

    public abstract int getEggPrimaryColorFemale();

    public abstract int getEggSecondaryColorFemale();

    public abstract EnumTimePeriod getPeriod();

    public abstract double getBabyHealth();

    public abstract double getAdultHealth();

    public abstract double getBabySpeed();

    public abstract double getAdultSpeed();

    public abstract double getBabyStrength();

    public abstract double getAdultStrength();

    public abstract double getBabyKnockback();

    public abstract double getAdultKnockback();

    public abstract float getBabySizeX();

    public abstract float getBabySizeY();

    public abstract float getAdultSizeX();

    public abstract float getAdultSizeY();

    public abstract float getBabyEyeHeight();

    public abstract float getAdultEyeHeight();

    public abstract int getMaximumAge();

    public ResourceLocation getMaleTexture(EnumGrowthStage stage)
    {
        return maleTextures.get(stage);
    }

    public ResourceLocation getFemaleTexture(EnumGrowthStage stage)
    {
        return femaleTextures.get(stage);
    }

    public double getAttackSpeed()
    {
        return 0.5D;
    }

    public boolean shouldRegister()
    {
        return true;
    }

    protected String getDinosaurTexture(String subtype)
    {
        String dinosaurName = getName().toLowerCase().replaceAll(" ", "_");

        String texture = "jurassicraft:textures/entities/" + dinosaurName + "/" + dinosaurName;

        if (subtype != "")
        {
            texture += "_" + subtype;
        }

        return texture + ".png";
    }

    @Override
    public int hashCode()
    {
        return getName().hashCode();
    }

    protected int fromDays(int days)
    {
        return (days * 24000) / 8;
    }

    @Override
    public int compareTo(Dinosaur dinosaur)
    {
        return this.getName().compareTo(dinosaur.getName());
    }

    public boolean isMarineAnimal()
    {
        return false;
    }

    public boolean isMammal()
    {
        return false;
    }

    public int getLipids()
    {
        return 1500;
    }

    public int getMinerals()
    {
        return 1500;
    }

    public int getVitamins()
    {
        return 1500;
    }

    public int getProximates() // TODO
    {
        return 1500;
    }

    public abstract int getStorage();

    public ResourceLocation getOverlayTexture(EnumGrowthStage stage, int overlay)
    {
        return overlay != 255 && overlays.containsKey(stage) ? overlays.get(stage).get(overlay) : null;
    }

    public int getOverlayCount()
    {
        List<ResourceLocation> overlayTextures = overlays.get(EnumGrowthStage.ADULT);
        return overlayTextures != null ? overlayTextures.size() : 0; //TODO what growth stage do you use here???
    }
}
