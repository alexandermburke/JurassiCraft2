package net.ilexiconn.jurassicraft.entity.base;

import io.netty.buffer.ByteBuf;
import net.ilexiconn.jurassicraft.dinosaur.Dinosaur;
import net.ilexiconn.jurassicraft.json.JsonHelper;
import net.ilexiconn.llibrary.common.entity.multipart.EntityPart;
import net.ilexiconn.llibrary.common.entity.multipart.IEntityMultiPart;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.IEntityAdditionalSpawnData;

public class EntityDinosaur extends EntityCreature implements IEntityMultiPart, IEntityAdditionalSpawnData
{
    protected EntityPart[] parts;
    protected Dinosaur dinosaur;
    protected int randTexture;

    protected boolean gender;

    public EntityDinosaur(World world)
    {
        super(world);

        parts = JsonHelper.parseHitboxList(this, dinosaur.getHitBoxList());
        gender = rand.nextBoolean();

        if (gender)
        {
            randTexture = rand.nextInt(dinosaur.getMaleTextures().length);
        }
        else
        {
            randTexture = rand.nextInt(dinosaur.getFemaleTextures().length);
        }
    }

    public void entityInit()
    {
        super.entityInit();
    }

    protected void applyEntityAttributes()
    {
        super.applyEntityAttributes();

        dinosaur = JCEntityRegistry.getDinosaurByClass(getClass());

        if (this.isChild())
        {
            this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(dinosaur.getBabyHealth());
            this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(dinosaur.getBabySpeed());
            this.getEntityAttribute(SharedMonsterAttributes.knockbackResistance).setBaseValue(dinosaur.getBabyKnockback());
        }
        else
        {
            this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(dinosaur.getAdultHealth());
            this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(dinosaur.getAdultSpeed());
            this.getEntityAttribute(SharedMonsterAttributes.knockbackResistance).setBaseValue(dinosaur.getAdultKnockback());
        }
    }

    public Dinosaur getDinosaur()
    {
        return dinosaur;
    }

    public EntityPart[] getParts()
    {
        return parts;
    }

    public void onUpdate()
    {
        super.onUpdate();
    }

    public boolean isMale()
    {
        return gender;
    }

    @Override
    public boolean canDespawn()
    {
        return false;
    }

    public void writeToNBT(NBTTagCompound nbt)
    {
        super.writeToNBT(nbt);

        nbt.setBoolean("Gender", gender);
        nbt.setInteger("Texture", randTexture);
    }

    public void readFromNBT(NBTTagCompound nbt)
    {
        super.readFromNBT(nbt);

        gender = nbt.getBoolean("Gender");
        randTexture = nbt.getInteger("Texture");
    }

    @Override
    public void writeSpawnData(ByteBuf buffer)
    {
        buffer.writeBoolean(gender);
        buffer.writeInt(randTexture);
    }

    @Override
    public void readSpawnData(ByteBuf additionalData)
    {
        gender = additionalData.readBoolean();
        randTexture = additionalData.readInt();
    }

    public int getTexture()
    {
        return randTexture;
    }
}