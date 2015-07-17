package net.timeless.jurassicraft.common.entity.base;

import io.netty.buffer.ByteBuf;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.IEntityAdditionalSpawnData;
import net.timeless.jurassicraft.common.dinosaur.Dinosaur;
import net.timeless.jurassicraft.common.item.JCItemRegistry;

public class EntityDinosaur extends EntityCreature implements IEntityAdditionalSpawnData
{
    protected Dinosaur dinosaur;
    protected int randTexture;

    protected boolean gender;

    private int dinosaurAge;

    public EntityDinosaur(World world)
    {
        super(world);

        dinosaurAge = 0;

        gender = rand.nextBoolean();

        if (gender)
            randTexture = rand.nextInt(dinosaur.getMaleTextures().length);
        else
            randTexture = rand.nextInt(dinosaur.getFemaleTextures().length);

        adjustHitbox();
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

        adjustHitbox();

        this.heal((float) (newHealth - this.getHealth()));
    }

    private void adjustHitbox()
    {
        this.setSize((float) transitionFromAge(dinosaur.getBabySizeX(), dinosaur.getBabySizeY()), (float) transitionFromAge(dinosaur.getAdultSizeX(), dinosaur.getAdultSizeY()));
    }

    public double transitionFromAge(double baby, double adult)
    {
        int dinosaurAge = this.dinosaurAge;
        int maxAge = dinosaur.getMaximumAge();

        if (dinosaurAge > maxAge)
            dinosaurAge = maxAge;

        return (adult - baby) / maxAge * dinosaurAge + baby;
    }

    public void onLivingUpdate()
    {
        super.onLivingUpdate();

        this.setSize((float) transitionFromAge(dinosaur.getBabySizeX(), dinosaur.getAdultSizeX()), (float) transitionFromAge(dinosaur.getBabySizeY(), dinosaur.getAdultSizeY()));

        if (ticksExisted % 32 == 0)
        {
            this.dinosaurAge++;

            if (dinosaurAge % 20 == 0)
            {
                updateCreatureData();
            }
        }
    }
    
    public void onUpdate()
    {
        super.onUpdate();  
        
        if(deathTime >= 20)
        {
            if(!worldObj.isRemote)
            {
                EntityCarcass carcass = new EntityCarcass(this);
                
                worldObj.spawnEntityInWorld(carcass);
            }
        }
    }

    public int getDaysExisted()
    {
        return (dinosaurAge * 32) / 24000;
    }

    public void setFullyGrown()
    {
        this.dinosaurAge = dinosaur.getMaximumAge();
    }

    public Dinosaur getDinosaur()
    {
        return dinosaur;
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

        adjustHitbox();
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

        adjustHitbox();
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

    /**
     * Drop 0-2 items of this living's type
     */
    protected void dropFewItems(boolean p_70628_1_, int p_70628_2_)
    {
        int meatAmount = this.rand.nextInt(5);

        for (int i = 0; i < meatAmount; ++i)
        {
            if (this.isBurning())
                entityDropItem(new ItemStack(JCItemRegistry.dino_steak, 1, JCEntityRegistry.getDinosaurId(dinosaur)), 0.0F);
            else
                entityDropItem(new ItemStack(JCItemRegistry.dino_meat, 1, JCEntityRegistry.getDinosaurId(dinosaur)), 0.0F);
        }
    }
}