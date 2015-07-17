package net.timeless.jurassicraft.common.entity.base;

import io.netty.buffer.ByteBuf;
import net.minecraft.entity.Entity;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.registry.IEntityAdditionalSpawnData;
import net.timeless.jurassicraft.common.dinosaur.Dinosaur;

public class EntityCarcass extends Entity implements IEntityAdditionalSpawnData
{
    private EntityDinosaur deadDinosaur;
    private int dinoId;

    public EntityCarcass(World world)
    {
        super(world);
    }

    public EntityCarcass(EntityDinosaur dead)
    {
        this(dead.worldObj);

        this.deadDinosaur = dead;

        updateProperties();
    }

    @Override
    protected void entityInit()
    {
    }

    /**
     * Returns true if other Entities should be prevented from moving through this Entity.
     */
    public boolean canBeCollidedWith()
    {
        return true;
    }

    public void updateProperties()
    {
        this.setSize(deadDinosaur.width, deadDinosaur.height);
        this.setPositionAndRotation(deadDinosaur.posX, deadDinosaur.posY, deadDinosaur.posZ, deadDinosaur.rotationYaw, deadDinosaur.rotationPitch);
        this.dinoId = JCEntityRegistry.getDinosaurId(deadDinosaur.getDinosaur());
    }

    @Override
    protected void readEntityFromNBT(NBTTagCompound tagCompound)
    {
        this.dinoId = tagCompound.getInteger("DinoId");
        
        NBTTagCompound entity = (NBTTagCompound) tagCompound.getTag("DeadEntity");

        Dinosaur dinosaurById = JCEntityRegistry.getDinosaurById(dinoId);

        if(dinosaurById != null)
        {
            Class<? extends EntityDinosaur> dinoClass = dinosaurById.getDinosaurClass();

            try
            {
                EntityDinosaur dinosaur = dinoClass.getConstructor(World.class).newInstance(worldObj);

                dinosaur.readFromNBT(entity);
                dinosaur.readEntityFromNBT(entity);
                dinosaur.width = entity.getFloat("DinoWidth");
                dinosaur.height = entity.getFloat("DinoHeight");
                
                this.deadDinosaur = dinosaur;

                updateProperties();
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }
    }

    @Override
    protected void writeEntityToNBT(NBTTagCompound tagCompound)
    {
        NBTTagCompound entity = new NBTTagCompound();
        deadDinosaur.writeToNBT(entity);
        deadDinosaur.writeEntityToNBT(entity);
        entity.setFloat("DinoWidth", deadDinosaur.width);
        entity.setFloat("DinoHeight", deadDinosaur.height);
        
        tagCompound.setTag("DeadEntity", entity);
        tagCompound.setInteger("DinoId", dinoId);
    }

    @Override
    public void writeSpawnData(ByteBuf buffer)
    {
        if(deadDinosaur != null)
        {
            NBTTagCompound entity = new NBTTagCompound();
            deadDinosaur.writeToNBT(entity);
            deadDinosaur.writeEntityToNBT(entity);
            entity.setFloat("DinoWidth", deadDinosaur.width);
            entity.setFloat("DinoHeight", deadDinosaur.height);
            
            ByteBufUtils.writeTag(buffer, entity);
            
            buffer.writeInt(dinoId);
        }
    }

    @Override
    public void readSpawnData(ByteBuf additionalData)
    {
        NBTTagCompound entity = (NBTTagCompound) ByteBufUtils.readTag(additionalData);

        dinoId = additionalData.readInt();

        Dinosaur dinosaurById = JCEntityRegistry.getDinosaurById(dinoId);

        if(dinosaurById != null)
        {
            Class<? extends EntityDinosaur> dinoClass = dinosaurById.getDinosaurClass();

            try
            {
                EntityDinosaur dinosaur = dinoClass.getConstructor(World.class).newInstance(worldObj);

                dinosaur.readFromNBT(entity);
                dinosaur.readEntityFromNBT(entity);
                dinosaur.width = entity.getFloat("DinoWidth");
                dinosaur.height = entity.getFloat("DinoHeight");
                
                this.deadDinosaur = dinosaur;

                updateProperties();
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }

        updateProperties();
    }

    /**
     * Called when the entity is attacked.
     */
     public boolean attackEntityFrom(DamageSource source, float amount)
    {
        if (this.isEntityInvulnerable(source) && ticksExisted < 20)
        {
            return false;
        }
        else
        {
            if (!this.isDead && !this.worldObj.isRemote)
            {
                this.setDead();
                this.setBeenAttacked();
            }

            return true;
        }
    }

     public EntityDinosaur getDinosaur()
     {
         return deadDinosaur;
     }
}
