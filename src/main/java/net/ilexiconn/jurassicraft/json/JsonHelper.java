package net.ilexiconn.jurassicraft.json;

import java.util.List;

import net.ilexiconn.llibrary.common.entity.multipart.EntityPart;
import net.minecraft.entity.EntityLivingBase;

public class JsonHelper
{
    public static EntityPart[] parseHitboxList(EntityLivingBase parent, List<JsonHitbox> hitbox)
    {
        return parseHitboxList(parent, hitbox.toArray(new JsonHitbox[hitbox.size()]));
    }

    public static EntityPart[] parseHitboxList(EntityLivingBase parent, JsonHitbox[] hitbox)
    {
        EntityPart[] list = new EntityPart[hitbox.length];
        for (int i = 0; i < hitbox.length; i++)
            list[i] = parseHitbox(parent, hitbox[i]);
        return list;
    }

    public static EntityPart parseHitbox(EntityLivingBase parent, JsonHitbox hitbox)
    {
        return new EntityPart(parent, hitbox.getRadius(), hitbox.getAngleYaw(), hitbox.getOffsetY(), hitbox.getSizeX(), hitbox.getSizeY(), hitbox.getDamageMultiplier());
    }
}
