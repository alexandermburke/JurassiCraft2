package net.timeless.jurassicraft.common.entity.item;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;
import net.timeless.jurassicraft.common.item.JCItemRegistry;

public class EntityCageSmall extends Entity
{
    private Entity entity;

    public EntityCageSmall(World world)
    {
        super(world);
        this.setSize(1.0F, 1.0F);
    }

    /**
     * Returns a boundingBox used to collide the entity with other entities and blocks. This enables the entity to be
     * pushable on contact, like boats or minecarts.
     */
    public AxisAlignedBB getCollisionBox(Entity entityIn)
    {
        return entityIn.getEntityBoundingBox();
    }

    /**
     * returns the bounding box for this entity
     */
    public AxisAlignedBB getBoundingBox()
    {
        return this.getEntityBoundingBox();
    }

    /**
     * Returns true if other Entities should be prevented from moving through this Entity.
     */
    public boolean canBeCollidedWith()
    {
        return true;
    }

    /**
     * Returns true if this entity should push and be pushed by other entities when colliding.
     */
    public boolean canBePushed()
    {
        return false;
    }

    @Override
    public void onUpdate()
    {
        super.onUpdate();

        if(entity != null)
        {
            entity.onUpdate();
        }
    }

    @Override
    public boolean attackEntityFrom(DamageSource source, float amount)
    {
        this.setDead();

        if(!worldObj.isRemote)
        {
            ItemStack stack = new ItemStack(JCItemRegistry.cage_small);

            if(entity != null)
            {
                NBTTagCompound nbt = new NBTTagCompound();

                this.writeEntityToNBT(nbt);

                stack.setTagCompound(nbt);
            }

            this.entityDropItem(stack, 0.5F);
        }

        return true;
    }

    @Override
    protected void entityInit()
    {
    }

    @Override
    protected void readEntityFromNBT(NBTTagCompound tagCompund)
    {
        int cagedId = tagCompund.getInteger("CagedID");

        if(cagedId != -1)
        {
            Entity entity = EntityList.createEntityByID(cagedId, worldObj);
            entity.readFromNBT(tagCompund.getCompoundTag("Entity"));
        }
    }

    @Override
    protected void writeEntityToNBT(NBTTagCompound tagCompound)
    {
        if(entity != null)
        {
            int entityId = EntityList.getEntityID(entity);

            NBTTagCompound nbt = new NBTTagCompound();
            entity.writeToNBT(nbt);

            tagCompound.setTag("Entity", nbt);
            tagCompound.setInteger("CagedID", entityId);
        }
        else
        {
            tagCompound.setInteger("CagedID", -1);
        }
    }

    public void setEntity(int entity)
    {
        this.entity = EntityList.createEntityByID(entity, worldObj);
    }

    public void setEntityData(NBTTagCompound entityData)
    {
        if(entity != null)
        {
            entity.readFromNBT(entityData);
        }
    }

    public Entity getEntity()
    {
        return entity;
    }
}
