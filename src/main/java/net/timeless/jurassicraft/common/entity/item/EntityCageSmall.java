package net.timeless.jurassicraft.common.entity.item;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.player.EntityPlayer;
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
        this.dataWatcher.addObject(17, new Integer(0));
        this.dataWatcher.addObject(18, new Integer(0));
        this.dataWatcher.addObject(19, new String(""));
        this.dataWatcher.addObject(20, new Integer(0));
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
                    entity.setMale(dataWatcher.getWatchableObjectInt(20) == 0);
                    entity.setAge(dataWatcher.getWatchableObjectInt(17));
                    entity.setDNAQuality(dataWatcher.getWatchableObjectInt(18));
                    entity.setGenetics(dataWatcher.getWatchableObjectString(19));
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
                dataWatcher.updateObject(17, entity.getDinosaurAge());
                dataWatcher.updateObject(18, entity.getDNAQuality());
                dataWatcher.updateObject(19, entity.getGenetics().toString());
                dataWatcher.updateObject(20, entity.isMale() ? 0 : 1);
            }
            else
            {
                dataWatcher.updateObject(25, -1);
            }
        }
    }

    /**
     * First layer of player interaction
     */
    public boolean interactFirst(EntityPlayer playerIn)
    {
        if(entity != null && !worldObj.isRemote)
        {
            entity.setPosition(posX, posY, posZ);
            worldObj.spawnEntityInWorld(entity);
            entity.motionX = 0;
            entity.motionY = 0;
            entity.motionZ = 0;
            entity.fallDistance = 0;

            this.setDead();
            this.entityDropItem(new ItemStack(JCItemRegistry.cage_small), 0.5F);

            entity = null;
        }

        return true;
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
            entity = (EntityDinosaur) EntityList.createEntityByID(cagedId, worldObj);
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
