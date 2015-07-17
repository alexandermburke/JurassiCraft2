package net.timeless.jurassicraft.common.entity.base;

import java.util.List;

import net.ilexiconn.llibrary.common.entity.multipart.EntityPart;
import net.ilexiconn.llibrary.common.json.JsonFactory;
import net.minecraft.entity.EntityLivingBase;

public class EntityHitbox
{
    private float radius;
    private float angleYaw;
    private float offsetY;
    private float sizeX;
    private float sizeY;
    private float damageMultiplier;

    public EntityHitbox(float radius, float angleYaw, float offsetY, float sizeX, float sizeY, float damageMultiplier)
    {
        this.radius = radius;
        this.angleYaw = angleYaw;
        this.offsetY = offsetY;
        this.sizeX = sizeX;
        this.sizeY = sizeY;
        this.damageMultiplier = damageMultiplier;
    }

    public float getRadius()
    {
        return radius;
    }

    public float getAngleYaw()
    {
        return angleYaw;
    }

    public float getOffsetY()
    {
        return offsetY;
    }

    public float getSizeX()
    {
        return sizeX;
    }

    public float getSizeY()
    {
        return sizeY;
    }

    public float getDamageMultiplier()
    {
        return damageMultiplier;
    }

    public String toString()
    {
        return JsonFactory.getGson().toJson(this);
    }

    public static EntityPart[] parseHitboxList(EntityLivingBase parent, List<EntityHitbox> hitbox)
    {
        return parseHitboxList(parent, hitbox.toArray(new EntityHitbox[hitbox.size()]));
    }

    public static EntityPart[] parseHitboxList(EntityLivingBase parent, EntityHitbox[] hitbox)
    {
        EntityPart[] list = new EntityPart[hitbox.length];

        for (int i = 0; i < hitbox.length; i++)
            list[i] = parseHitbox(parent, hitbox[i]);

        return list;
    }

    public static EntityPart parseHitbox(EntityLivingBase parent, EntityHitbox hitbox)
    {
        return new EntityPart(parent, hitbox.getRadius(), hitbox.getAngleYaw(), hitbox.getOffsetY(), hitbox.getSizeX(), hitbox.getSizeY(), hitbox.getDamageMultiplier());
    }
}