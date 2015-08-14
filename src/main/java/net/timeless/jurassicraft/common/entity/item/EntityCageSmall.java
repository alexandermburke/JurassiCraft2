package net.timeless.jurassicraft.common.entity.item;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;
import net.timeless.jurassicraft.common.entity.base.EntityDinosaur;
import net.timeless.jurassicraft.common.item.JCItemRegistry;

public class EntityCageSmall extends Entity
{
    private EntityDinosaur entity;

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
    protected void entityInit()
    {
        this.dataWatcher.addObject(25, new Integer(-1));
        this.dataWatcher.addObject(26, new Integer(0));
        this.dataWatcher.addObject(27, new Integer(0));
        this.dataWatcher.addObject(28, new String(""));
    }

    @Override
    public void onUpdate()
    {
        super.onUpdate();

        if(worldObj.isRemote)
        {
            int id = dataWatcher.getWatchableObjectInt(25);

            if(id != -1)
            {
                entity = (EntityDinosaur) EntityList.createEntityByID(id, worldObj);

                if(entity != null)
                {
                    entity.setAge(dataWatcher.getWatchableObjectInt(26));
                    entity.setDNAQuality(dataWatcher.getWatchableObjectInt(27));
                    entity.setGenetics(dataWatcher.getWatchableObjectString(28));
                }
            }
            else
            {
                entity = null;
            }
        }

        if(entity != null)
        {
            entity.onUpdate();
        }

        if(!worldObj.isRemote)
        {
            if(entity != null)
            {
                dataWatcher.updateObject(25, EntityList.getEntityID(entity));
                dataWatcher.updateObject(26, entity.getDinosaurAge());
                dataWatcher.updateObject(27, entity.getDNAQuality());
                dataWatcher.updateObject(28, entity.getGenetics());
            }
            else
            {
                dataWatcher.updateObject(25, -1);
            }
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
        this.entity = (EntityDinosaur) EntityList.createEntityByID(entity, worldObj);
    }

    public void setEntity(Entity entity)
    {
        this.entity = (EntityDinosaur) entity;

        NBTTagCompound nbt = new NBTTagCompound();
        entity.writeToNBT(nbt);

        setEntityData(nbt);
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
