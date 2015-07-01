package net.timeless.jurassicraft.entity.base;

import io.netty.buffer.ByteBuf;
import net.ilexiconn.llibrary.common.entity.multipart.EntityPart;
import net.ilexiconn.llibrary.common.entity.multipart.IEntityMultiPart;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.IEntityAdditionalSpawnData;
import net.timeless.jurassicraft.dinosaur.Dinosaur;

public class EntityDinosaur extends EntityCreature implements IEntityMultiPart, IEntityAdditionalSpawnData
{
    protected EntityPart[] parts;
    protected Dinosaur dinosaur;
    protected int randTexture;

    protected boolean gender;

    private int dinosaurAge;

    public EntityDinosaur(World world)
    {
        super(world);

        dinosaurAge = 0;
        parts = EntityHitbox.parseHitboxList(this, dinosaur.getHitBoxList());

        gender = rand.nextBoolean();

        if (gender)
            randTexture = rand.nextInt(dinosaur.getMaleTextures().length);
        else
            randTexture = rand.nextInt(dinosaur.getFemaleTextures().length);
    }

    public void entityInit()
    {
        super.entityInit();
    }

    protected void applyEntityAttributes()
    {
        super.applyEntityAttributes();

        dinosaur = JCEntityRegistry.getDinosaurByClass(getClass());
    }

    public void updateCreatureData()
    {
        double newHealth = transitionFromAge(dinosaur.getBabyHealth(), dinosaur.getAdultHealth());

        this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(newHealth);
        this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(transitionFromAge(dinosaur.getBabySpeed(), dinosaur.getAdultSpeed()));
        this.getEntityAttribute(SharedMonsterAttributes.knockbackResistance).setBaseValue(transitionFromAge(dinosaur.getBabyKnockback(), dinosaur.getAdultKnockback()));

        this.heal((float) (newHealth - this.getHealth()));
    }

    public double transitionFromAge(double baby, double adult)
    {
        return (adult - baby) / (dinosaur.getMaximumAge()) * dinosaurAge + baby;
    }

    public void onLivingUpdate()
    {
        super.onLivingUpdate();

        if(ticksExisted % 16 == 0)
        {
            if (dinosaurAge < dinosaur.getMaximumAge())
            {
                this.dinosaurAge++;

                if (dinosaurAge % 20 == 0)
                {
                    updateCreatureData();
                }
            }
            else if (dinosaurAge > dinosaur.getMaximumAge())
            {
                dinosaurAge = dinosaur.getMaximumAge();
            }
        }
    }

    public void setFullyGrown()
    {
        this.dinosaurAge = dinosaur.getMaximumAge();
    }

    public Dinosaur getDinosaur()
    {
        return dinosaur;
    }

    public EntityPart[] getParts()
    {
        return parts;
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
        nbt.setDouble("Dinosaur Age", dinosaurAge);
    }

    public void readFromNBT(NBTTagCompound nbt)
    {
        super.readFromNBT(nbt);

        gender = nbt.getBoolean("Gender");
        randTexture = nbt.getInteger("Texture");
        dinosaurAge = nbt.getInteger("Dinosaur Age");
    }

    @Override
    public void writeSpawnData(ByteBuf buffer)
    {
        buffer.writeBoolean(gender);
        buffer.writeInt(randTexture);
        buffer.writeInt(dinosaurAge);
    }

    @Override
    public void readSpawnData(ByteBuf additionalData)
    {
        gender = additionalData.readBoolean();
        randTexture = additionalData.readInt();
        dinosaurAge = additionalData.readInt();
    }

    public double getDinosaurAge()
    {
        return dinosaurAge;
    }

    public int getTexture()
    {
        return randTexture;
    }

    public void setAge(int age)
    {
        this.dinosaurAge = age;
    }
    
    public float getEyeHeight()
    {
        return (float) transitionFromAge(dinosaur.getBabyEyeHeight(), dinosaur.getAdultEyeHeight());
    }
}