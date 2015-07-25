package net.reuxertz.ecoapi.util;

import net.minecraft.entity.Entity;
import net.minecraft.util.AxisAlignedBB;

import java.util.List;

public class EntityHelper
{
    public static List<Entity> getEntitiesWithinDistance(Entity e, double xz, double y)
    {
        return e.worldObj.getEntitiesWithinAABBExcludingEntity(e, AxisAlignedBB.fromBounds(e.posX - xz, e.posY - y, e.posZ - xz, e.posX + xz, e.posY + y, e.posZ + xz));
    }
}
