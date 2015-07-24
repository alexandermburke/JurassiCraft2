package net.timeless.jurassicraft.common.entity.base;

import io.netty.buffer.ByteBuf;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackOnCollide;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.IEntityAdditionalSpawnData;
import net.timeless.animationapi.AIAnimation;
import net.timeless.animationapi.IAnimatedEntity;
import net.timeless.jurassicraft.JurassiCraft;
import net.timeless.jurassicraft.common.dinosaur.Dinosaur;
import net.timeless.jurassicraft.common.item.ItemBluePrint;
import net.timeless.jurassicraft.common.item.JCItemRegistry;

public class EntityDinosaur extends EntityCreature implements IEntityAdditionalSpawnData, IAnimatedEntity
{
    protected Dinosaur dinosaur;
    protected int randTexture;

    protected boolean gender;

    protected int dinosaurAge;

    private boolean isCarcass;

    private int quality;

    private int animTick;
    private int animID;

    public AIAnimation currentAnim = null;

    public EntityDinosaur(World world)
    {
        super(world);
        tasks.addTask(0, new EntityAISwimming(this));

        dinosaurAge = 0;

        gender = rand.nextBoolean();

        if (gender)
            randTexture = rand.nextInt(dinosaur.getMaleTextures().length);
        else
            randTexture = rand.nextInt(dinosaur.getFemaleTextures().length);

        adjustHitbox();

        animTick = 0;
        animID = 0;
    }

    public void entityInit()
    {
        super.entityInit();
        this.dataWatcher.addObject(25, 0);
    }

    public boolean attackEntityFrom(DamageSource source, float amount)
    {
        if (isCarcass && !isEntityInvulnerable(source))
        {
            this.setDead();
            this.onDeath(source);
        }

        return super.attackEntityFrom(source, amount);
    }

    protected void applyEntityAttributes()
    {
        super.applyEntityAttributes();

        dinosaur = JCEntityRegistry.getDinosaurByClass(getClass());
        double newHealth = transitionFromAge(dinosaur.getBabyHealth(), dinosaur.getAdultHealth());

        this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(newHealth);
        this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(transitionFromAge(dinosaur.getBabySpeed(), dinosaur.getAdultSpeed()));
        this.getEntityAttribute(SharedMonsterAttributes.knockbackResistance).setBaseValue(transitionFromAge(dinosaur.getBabyKnockback(), dinosaur.getAdultKnockback()));

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

    public float getSoundPitch()
    {
        return (float) transitionFromAge(1.5F, 1.0F);
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

        if (!worldObj.isRemote)
        {
            dataWatcher.updateObject(25, isCarcass ? 1 : 0);
        }
        else
        {
            isCarcass = dataWatcher.getWatchableObjectInt(25) == 1;
        }
        if (getAnimID() != 0) animTick++;
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
        nbt.setBoolean("IsCarcass", isCarcass);
        nbt.setInteger("DNAQuality", quality);
    }

    public void readFromNBT(NBTTagCompound nbt)
    {
        super.readFromNBT(nbt);

        gender = nbt.getBoolean("Gender");
        randTexture = nbt.getInteger("Texture");
        dinosaurAge = nbt.getInteger("Dinosaur Age");
        isCarcass = nbt.getBoolean("IsCarcass");
        quality = nbt.getInteger("DNAQuality");

        adjustHitbox();
    }

    @Override
    public void writeSpawnData(ByteBuf buffer)
    {
        buffer.writeBoolean(gender);
        buffer.writeInt(randTexture);
        buffer.writeInt(dinosaurAge);
        buffer.writeBoolean(isCarcass);
        buffer.writeInt(quality);
    }

    @Override
    public void readSpawnData(ByteBuf additionalData)
    {
        gender = additionalData.readBoolean();
        randTexture = additionalData.readInt();
        dinosaurAge = additionalData.readInt();
        isCarcass = additionalData.readBoolean();
        quality = additionalData.readInt();

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
    protected void dropFewItems(boolean p_70628_1_, int looting)
    {
        int meatAmount = (int) (this.rand.nextInt(3) + ((width * height) / 4)) + looting;

        for (int i = 0; i < meatAmount; ++i)
        {
            if (this.isBurning())
                dropStackWithQuality(new ItemStack(JCItemRegistry.dino_steak, 1, JCEntityRegistry.getDinosaurId(dinosaur)));
            else
                dropStackWithQuality(new ItemStack(JCItemRegistry.dino_meat, 1, JCEntityRegistry.getDinosaurId(dinosaur)));
        }
    }

    private void dropStackWithQuality(ItemStack stack)
    {
        NBTTagCompound nbt = new NBTTagCompound();
        nbt.setInteger("DNAQuality", quality);
        stack.setTagCompound(nbt);

        entityDropItem(stack, 0.0F);
    }

    public void setCarcass(boolean carcass)
    {
        this.isCarcass = carcass;
    }

    public boolean isCarcass()
    {
        return isCarcass;
    }

    public boolean interact(EntityPlayer player)
    {
        ItemStack heldItem = player.getHeldItem();

        if (heldItem != null)
        {
            if (heldItem.getItem() instanceof ItemBluePrint)
            {
                ((ItemBluePrint) heldItem.getItem()).setDinosaur(heldItem, JCEntityRegistry.getDinosaurId(getDinosaur()));
            }
        }

        return false;
    }

    //NOTE: This adds an attack target. Class should be the entity class for the target, lower prio get executed earlier
    protected void attackCreature(Class entity, int prio)
    {
        this.tasks.addTask(0, new EntityAIAttackOnCollide(this, entity, dinosaur.getAttackSpeed(), false));
        this.targetTasks.addTask(0, new EntityAINearestAttackableTarget(this, entity, false));
    }

    //NOTE: This registers which attackers to defend from. Class should be the entity class for the attacker, lower prio get executed earlier (Should be based upon attacker's strength and health to decide whether to defend or flee)
    protected void defendFromAttacker(Class entity, int prio)
    {
        //this.targetTasks.addTask(prio, new EntityAIJCShouldDefend(this, true, entity));
        this.targetTasks.addTask(prio, new EntityAIHurtByTarget(this, true, entity));
    }

    public int getDNAQuality()
    {
        return quality;
    }

    public void setDNAQuality(int quality)
    {
        this.quality = quality;
    }

    @Override
    public void setAnimID(int id)
    {
        this.animID = id;
    }

    @Override
    public void setAnimTick(int tick)
    {
        this.animTick = tick;
    }

    @Override
    public int getAnimID()
    {
        return animID;
    }

    @Override
    public int getAnimTick()
    {
        return animTick;
    }

    protected String randomSound(String[] sounds)
    {
        return JurassiCraft.modid + ":" + sounds[rand.nextInt(sounds.length)];
    }
}