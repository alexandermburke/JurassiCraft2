package net.timeless.jurassicraft.common.tileentity;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;

public class TileCage extends TileEntity
{
    private Entity entity;

    public void setEntity(Entity entity)
    {
        this.entity = entity;
    }

    public Entity getEntity()
    {
        return entity;
    }

    @Override
    public void readFromNBT(NBTTagCompound compound)
    {
        super.readFromNBT(compound);

        int entityID = compound.getInteger("EntityID");

        if(entityID != -1)
        {
            NBTTagCompound entityTag = compound.getCompoundTag("Entity");

            entity = EntityList.createEntityByID(entityID, worldObj);

            entity.readFromNBT(entityTag);
        }
    }

    @Override
    public void writeToNBT(NBTTagCompound compound)
    {
        super.writeToNBT(compound);

        NBTTagCompound entityTag = new NBTTagCompound();

        if(entity != null)
        {
            entity.writeToNBT(entityTag);

            compound.setInteger("EntityID", EntityList.getEntityID(entity));
        }
        else
        {
            compound.setInteger("EntityID", -1);
        }

        compound.setTag("Entity", entityTag);
    }
}
