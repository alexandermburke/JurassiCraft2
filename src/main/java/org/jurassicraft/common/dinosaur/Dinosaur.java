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

    private String name;
    private Class<? extends EntityDinosaur> dinoClazz;
    private int primaryEggColorMale, primaryEggColorFemale;
    private int secondaryEggColorMale, secondaryEggColorFemale;
    private EnumTimePeriod timePeriod;
    private double babyHealth, adultHealth;
    private double babyStrength, adultStrength;
    private double babySpeed, adultSpeed;
    private float babySizeX, adultSizeX;
    private float babySizeY, adultSizeY;
    private float babyEyeHeight, adultEyeHeight;
    private double attackSpeed = 1.0;
    private boolean shouldRegister = true;
    private boolean isMarineAnimal;
    private boolean isMammal;
    private int storage;
    private int overlayCount;
    private EnumDiet diet;
    private EnumSleepingSchedule sleepingSchedule = EnumSleepingSchedule.DIURNAL;
    private String[] bones;
    private int maximumAge;

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

    public void setName(String name)
    {
        this.name = name;
    }

    public void setDinosaurClass(Class<? extends EntityDinosaur> clazz)
    {
        this.dinoClazz = clazz;
    }

    public void setEggColorMale(int primary, int secondary)
    {
        this.primaryEggColorMale = primary;
        this.secondaryEggColorMale = secondary;
    }

    public void setEggColorFemale(int primary, int secondary)
    {
        this.primaryEggColorFemale = primary;
        this.secondaryEggColorFemale = secondary;
    }

    public void setTimePeriod(EnumTimePeriod timePeriod)
    {
        this.timePeriod = timePeriod;
    }

    public void setHealth(double baby, double adult)
    {
        this.babyHealth = baby;
        this.adultHealth = adult;
    }

    public void setStrength(double baby, double adult)
    {
        this.babyStrength = baby;
        this.adultStrength = adult;
    }

    public void setSpeed(double baby, double adult)
    {
        this.babySpeed = baby;
        this.adultSpeed = adult;
    }

    public void setSizeX(float baby, float adult)
    {
        this.babySizeX = baby;
        this.adultSizeX = adult;
    }

    public void setSizeY(float baby, float adult)
    {
        this.babySizeY = baby;
        this.adultSizeY = adult;
    }

    public void setEyeHeight(float baby, float adult)
    {
        this.babyEyeHeight = baby;
        this.adultEyeHeight = adult;
    }

    public void disableRegistry()
    {
        this.shouldRegister = false;
    }

    public void setMarineAnimal(boolean marineAnimal)
    {
        this.isMarineAnimal = marineAnimal;
    }

    public void setMammal(boolean isMammal)
    {
        this.isMammal = isMammal;
    }

    public void setAttackSpeed(double attackSpeed)
    {
        this.attackSpeed = attackSpeed;
    }

    public void setStorage(int storage)
    {
        this.storage = storage;
    }

    public void setOverlayCount(int count)
    {
        this.overlayCount = count;
    }

    public void setDiet(EnumDiet diet)
    {
        this.diet = diet;
    }

    public void setSleepingSchedule(EnumSleepingSchedule sleepingSchedule)
    {
        this.sleepingSchedule = sleepingSchedule;
    }

    public void setBones(String... bones)
    {
        this.bones = bones;
    }

    public String getName()
    {
        return name;
    }

    public Class<? extends EntityDinosaur> getDinosaurClass()
    {
        return dinoClazz;
    }

    public int getEggPrimaryColorMale()
    {
        return primaryEggColorMale;
    }

    public int getEggSecondaryColorMale()
    {
        return secondaryEggColorMale;
    }

    public int getEggPrimaryColorFemale()
    {
        return primaryEggColorFemale;
    }

    public int getEggSecondaryColorFemale()
    {
        return secondaryEggColorFemale;
    }

    public EnumTimePeriod getPeriod()
    {
        return timePeriod;
    }

    public double getBabyHealth()
    {
        return babyHealth;
    }

    public double getAdultHealth()
    {
        return adultHealth;
    }

    public double getBabySpeed()
    {
        return babySpeed;
    }

    public double getAdultSpeed()
    {
        return adultSpeed;
    }

    public double getBabyStrength()
    {
        return babyStrength;
    }

    public double getAdultStrength()
    {
        return adultStrength;
    }

    public float getBabySizeX()
    {
        return babySizeX;
    }

    public float getBabySizeY()
    {
        return babySizeY;
    }

    public float getAdultSizeX()
    {
        return adultSizeX;
    }

    public float getAdultSizeY()
    {
        return adultSizeY;
    }

    public float getBabyEyeHeight()
    {
        return babyEyeHeight;
    }

    public float getAdultEyeHeight()
    {
        return adultEyeHeight;
    }

    public int getMaximumAge()
    {
        return maximumAge;
    }

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
        return attackSpeed;
    }

    public boolean shouldRegister()
    {
        return shouldRegister;
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
        return isMarineAnimal;
    }

    public boolean isMammal()
    {
        return isMammal;
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

    public int getStorage()
    {
        return storage;
    }

    public ResourceLocation getOverlayTexture(EnumGrowthStage stage, int overlay)
    {
        return overlay != -1 && overlay != 255 && overlays.containsKey(stage) ? overlays.get(stage).get(overlay) : null;
    }

    public int getOverlayCount()
    {
        return overlayCount;
    }

    public boolean useAllGrowthStages()
    {
        return false;
    }

    public EnumDiet getDiet()
    {
        return diet;
    }

    public EnumSleepingSchedule getSleepingSchedule()
    {
        return sleepingSchedule;
    }

    public String[] getBones()
    {
        return bones;
    }

    @Override
    public boolean equals(Object object)
    {
        if (object instanceof Dinosaur)
        {
            return ((Dinosaur) object).getName().equalsIgnoreCase(getName());
        }

        return false;
    }

    public void setMaximumAge(int age)
    {
        this.maximumAge = age;
    }
}
