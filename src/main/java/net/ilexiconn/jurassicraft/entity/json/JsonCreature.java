package net.ilexiconn.jurassicraft.entity.json;

import net.ilexiconn.jurassicraft.json.JsonFactory;

import java.util.List;

public class JsonCreature
{
    private String name;
    private List<JsonHitbox> hitboxList;

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
}
