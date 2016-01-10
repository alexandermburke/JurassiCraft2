package org.jurassicraft.common.dinosaur;

import net.minecraft.util.ResourceLocation;
import org.jurassicraft.JurassiCraft;
import org.jurassicraft.common.api.IHybrid;
import org.jurassicraft.common.entity.base.EntityDinosaur;
import org.jurassicraft.common.entity.base.EnumDiet;
import org.jurassicraft.common.entity.base.EnumGrowthStage;
import org.jurassicraft.common.entity.base.EnumSleepingSchedule;
import org.jurassicraft.common.period.EnumTimePeriod;

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

        String baseTextures = "textures/entities/" + dinosaurName + "/";

        for (EnumGrowthStage growthStage : EnumGrowthStage.values())
        {
            String growthStageName = growthStage.name().toLowerCase();

            if (!useAllGrowthStages())
            {
                if (growthStage == EnumGrowthStage.ADOLESCENT)
                {
                    growthStageName = EnumGrowthStage.ADULT.name().toLowerCase();
                }
                else if (growthStage == EnumGrowthStage.JUVENILE)
                {
                    growthStageName = EnumGrowthStage.INFANT.name().toLowerCase();
                }
            }

            if (this instanceof IHybrid)
            {
                ResourceLocation hybridTexture = new ResourceLocation(JurassiCraft.MODID, baseTextures + dinosaurName + "_" + growthStageName + ".png");

                maleTextures.put(growthStage, hybridTexture);
                femaleTextures.put(growthStage, hybridTexture);
            }
            else
            {
                maleTextures.put(growthStage, new ResourceLocation(JurassiCraft.MODID, baseTextures + dinosaurName + "_male_" + growthStageName + ".png"));
                femaleTextures.put(growthStage, new ResourceLocation(JurassiCraft.MODID, baseTextures + dinosaurName + "_female_" + growthStageName + ".png"));
            }

            List<ResourceLocation> overlaysForGrowthStage = new ArrayList<ResourceLocation>();

            for (int i = 1; i <= getOverlayCount(); i++)
            {
                overlaysForGrowthStage.add(new ResourceLocation(JurassiCraft.MODID, baseTextures + dinosaurName + "_overlay_" + growthStageName + "_" + i + ".png"));
            }

            overlays.put(growthStage, overlaysForGrowthStage);
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

        if (subtype.length() > 0)
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
        return overlay != -1 && overlay != 255 && overlays.containsKey(stage) ? overlays.get(stage).get(overlay) : null;
    }

    public int getOverlayCount()
    {
        return 0;
    }

    public boolean useAllGrowthStages()
    {
        return false;
    }

    public abstract EnumDiet getDiet();

    public abstract EnumSleepingSchedule getSleepingSchedule();

    public abstract String[] getBones();

    @Override
    public boolean equals(Object object)
    {
        if (object instanceof Dinosaur)
        {
            return ((Dinosaur) object).getName().equalsIgnoreCase(getName());
        }

        return false;
    }
}
