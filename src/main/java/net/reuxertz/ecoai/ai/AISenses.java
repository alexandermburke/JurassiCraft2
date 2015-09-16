package net.reuxertz.ecoai.ai;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCreature;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.Vec3;
import net.reuxertz.ecoapi.util.CounterObj;

import java.util.List;

public class AISenses extends AIBase
{
    protected CounterObj timer;

    public AISenses(EntityCreature entity)
    {
        super(entity);
        timer = new CounterObj(entity.worldObj.getWorldTime(), 200, 20);
    }

    @Override
    public NBTTagCompound writeToEntityNbt()
    {
        return this.entity().getEntityData();

    }
    @Override
    public void readFromEntityNbt()
    {
        NBTTagCompound nbt = this.entity().getEntityData();

    }

    public void updateTask()
    {

        if (!this.timer.updateMSTime(entity().worldObj.getWorldTime()))
            return;

    }

    public boolean canEntityBeSeen(Entity entitySeeing, Entity entitySeen) {
        return entitySeeing.worldObj.rayTraceBlocks(new Vec3(entitySeeing.posX, entitySeeing.posY + (double)entitySeeing.getEyeHeight(), entitySeeing.posZ), new Vec3(entitySeen.posX, entitySeen.posY + (double)entitySeen.getEyeHeight(), entitySeen.posZ)) == null;
    }

    public List <Entity> getEntitiesNearby(Entity entity)
    {
        List entitiesNearby = entity.worldObj.getEntitiesWithinAABB(Entity.class, AxisAlignedBB.fromBounds(entity.posX, entity.posY, entity.posZ, (entity.posX + 25), (entity.posY + 25), (entity.posZ + 25)));
        return entitiesNearby;
    }

    public List <Entity> getEntitiesVisual(Entity entitySeeing, List <Entity> entitiesNearby)
    {
        List <Entity> entitiesVisual = null;
        for (int i = 0; i < entitiesNearby.size(); i++) {
            if (canEntityBeSeen(entitySeeing, entitiesNearby.get(i))) {
                entitiesVisual.add(entitiesNearby.get(i));
            }
        }
        return entitiesVisual;
    }

    public List <Entity> getEntitiesAudible(Entity entity, int radius)
    {
        List entitiesAudible = entity.worldObj.getEntitiesWithinAABB(Entity.class, AxisAlignedBB.fromBounds(entity.posX, entity.posY, entity.posZ, (entity.posX + radius), (entity.posY + radius), (entity.posZ + radius)));
        return entitiesAudible;
    }
}
