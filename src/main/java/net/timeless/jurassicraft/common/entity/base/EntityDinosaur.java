package net.timeless.jurassicraft.common.entity.base;

import io.netty.buffer.ByteBuf;
import net.minecraft.entity.Entity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackOnCollide;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.pathfinding.PathNavigate;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.registry.IEntityAdditionalSpawnData;
import net.reuxertz.ecoapi.entity.EntityAICreature;
import net.timeless.animationapi.AIAnimation;
import net.timeless.animationapi.IAnimatedEntity;
import net.timeless.jurassicraft.JurassiCraft;
import net.timeless.jurassicraft.common.dinosaur.Dinosaur;
import net.timeless.jurassicraft.common.dna.GeneticsContainer;
import net.timeless.jurassicraft.common.dna.GeneticsHelper;
import net.timeless.jurassicraft.common.item.ItemBluePrint;
import net.timeless.jurassicraft.common.item.JCItemRegistry;

public class EntityDinosaur extends EntityAICreature implements IEntityAdditionalSpawnData, IAnimatedEntity
{
    protected Dinosaur dinosaur;
    protected int randTexture;

    protected int dinosaurAge;

//    private boolean isCarcass;

    private int quality;

    private int animTick;
    private int animID;

    private GeneticsContainer genetics;

    public AIAnimation currentAnim = null;

    public void setNavigator(PathNavigate pn)
    {
        this.navigator = pn;
    }

    public EntityDinosaur(World world)
    {
        super(world);
        //tasks.addTask(0, new EntityAISwimming(this));

        dinosaurAge = 0;

        genetics = GeneticsHelper.randomGenetics(rand, getDinosaur(), getDNAQuality());

        animTick = 0;
        animID = 0;

        updateCreatureData();
        adjustHitbox();
    }

    public void entityInit()
    {
        super.entityInit();
        this.dataWatcher.addObject(25, 0);
    }

    protected void applyEntityAttributes()
    {
        super.applyEntityAttributes();

        dinosaur = JCEntityRegistry.getDinosaurByClass(getClass());

        updateCreatureData();
        adjustHitbox();
    }

    public void updateCreatureData()
    {
        double newHealth = transitionFromAge(dinosaur.getBabyHealth(), dinosaur.getAdultHealth());

        this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(newHealth);
        this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(transitionFromAge(dinosaur.getBabySpeed(), dinosaur.getAdultSpeed()));
        this.getEntityAttribute(SharedMonsterAttributes.knockbackResistance).setBaseValue(transitionFromAge(dinosaur.getBabyKnockback(), dinosaur.getAdultKnockback()));

//        adjustHitbox();

        this.heal((float) (newHealth - this.getHealth()));
    }

    private void adjustHitbox()
    {
        this.setSize((float) transitionFromAge(dinosaur.getBabySizeX(), dinosaur.getAdultSizeX()), (float) transitionFromAge(dinosaur.getBabySizeY(), dinosaur.getAdultSizeY()));
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

    public float getSoundVolume()
    {
        return (float) transitionFromAge(0.3F, 1.0F);
    }

    public void setGenetics(String genetics)
    {
        this.genetics = new GeneticsContainer(genetics);
    }

    public GeneticsContainer getGenetics()
    {
        return genetics;
    }

    public void onLivingUpdate()
    {
        super.onLivingUpdate();

        adjustHitbox();

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

//        if (!worldObj.isRemote)
//        {
//            dataWatcher.updateObject(25, isCarcass ? 1 : 0);
//        }
//        else
//        {
//            isCarcass = dataWatcher.getWatchableObjectInt(25) == 1;
//        }
        if (getAnimID() != 0)
            animTick++;
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

    @Override
    public boolean canDespawn()
    {
        return false;
    }

    public void writeToNBT(NBTTagCompound nbt)
    {
        super.writeToNBT(nbt);

        nbt.setInteger("Texture", randTexture);
        nbt.setDouble("Dinosaur Age", dinosaurAge);
//        nbt.setBoolean("IsCarcass", isCarcass);
        nbt.setInteger("DNAQuality", quality);
        nbt.setString("Genetics", genetics.toString());
    }

    public void readFromNBT(NBTTagCompound nbt)
    {
        super.readFromNBT(nbt);

        randTexture = nbt.getInteger("Texture");
        dinosaurAge = nbt.getInteger("Dinosaur Age");
//        isCarcass = nbt.getBoolean("IsCarcass");
        quality = nbt.getInteger("DNAQuality");
        genetics = new GeneticsContainer(nbt.getString("Genetics"));

        updateCreatureData();
        adjustHitbox();
    }

    @Override
    public void writeSpawnData(ByteBuf buffer)
    {
        buffer.writeInt(randTexture);
        buffer.writeInt(dinosaurAge);
//        buffer.writeBoolean(isCarcass);
        buffer.writeInt(quality);
        ByteBufUtils.writeUTF8String(buffer, genetics.toString());
    }

    @Override
    public void readSpawnData(ByteBuf additionalData)
    {
        randTexture = additionalData.readInt();
        dinosaurAge = additionalData.readInt();
//        isCarcass = additionalData.readBoolean();
        quality = additionalData.readInt();

        genetics = new GeneticsContainer(ByteBufUtils.readUTF8String(additionalData));

        updateCreatureData();
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
        nbt.setString("Genetics", genetics.toString());
        stack.setTagCompound(nbt);

        entityDropItem(stack, 0.0F);
    }

//    public void setCarcass(boolean carcass)
//    {
//        this.isCarcass = carcass;
//
//        if (isCarcass)
//        {
//            String s = this.getDeathSound();
//
//            if (s != null)
//            {
//                this.playSound(s, this.getSoundVolume(), (this.rand.nextFloat() - this.rand.nextFloat()) * 0.2F + 1.0F);
//            }
//        }
//    }
//
//    public boolean isCarcass()
//    {
//        return isCarcass;
//    }

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

    protected String randomSound(String... sounds)
    {
        return JurassiCraft.modid + ":" + sounds[rand.nextInt(sounds.length)];
    }

    public double getAttackDamage()
    {
        return transitionFromAge(dinosaur.getBabyStrength(), dinosaur.getAdultStrength());
    }

    public boolean isStronger(EntityDinosaur dinosaur)
    {
        return this.getHealth() * (float) this.getAttackDamage() < dinosaur.getHealth() * (float) dinosaur.getAttackDamage();
    }

    public boolean isMale()
    {
        return genetics.isMale();
    }

    public int getScaleOffset()
    {
        return genetics.getScaleOffset();
    }

    public int getColorOffset()
    {
        return genetics.getColorOffset();
    }

    public int getGeneticVariant()
    {
        return genetics.getGeneticVariation();
    }
}