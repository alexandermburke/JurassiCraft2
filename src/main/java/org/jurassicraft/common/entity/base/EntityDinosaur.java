package org.jurassicraft.common.entity.base;

import io.netty.buffer.ByteBuf;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackOnCollide;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.registry.IEntityAdditionalSpawnData;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.timeless.animationapi.AIAnimation;
import net.timeless.animationapi.AnimationAPI;
import net.timeless.animationapi.IAnimatedEntity;
import net.timeless.animationapi.client.AnimID;
import net.timeless.unilib.common.animation.ChainBuffer;
import org.jurassicraft.JurassiCraft;
import org.jurassicraft.common.damagesource.EntityDinosaurDamageSource;
import org.jurassicraft.common.dinosaur.Dinosaur;
import org.jurassicraft.common.entity.ai.EntityAIHerd;
import org.jurassicraft.common.entity.ai.EntityAIMate;
import org.jurassicraft.common.entity.ai.animations.AnimationAICall;
import org.jurassicraft.common.entity.ai.animations.AnimationAIHeadCock;
import org.jurassicraft.common.entity.ai.animations.AnimationAILook;
import org.jurassicraft.common.entity.ai.metabolism.EntityAIDrink;
import org.jurassicraft.common.genetics.GeneticsContainer;
import org.jurassicraft.common.genetics.GeneticsHelper;
import org.jurassicraft.common.item.ItemBluePrint;
import org.jurassicraft.common.item.JCItemRegistry;

import java.util.UUID;

public abstract class EntityDinosaur extends EntityCreature implements IEntityAdditionalSpawnData, IAnimatedEntity
{
    public static final int MAX_ENERGY = 24000;
    public static final int MAX_WATER = 24000;

    protected Dinosaur dinosaur;

    protected int dinosaurAge;
    protected int prevAge;
    private int growthSpeedOffset;

    private boolean isCarcass;

    private GeneticsContainer genetics;
    private int geneticsQuality;
    private boolean isMale;

    // For animation AI system
    public AIAnimation currentAnim = null;
    private AnimID animID;
    private int animTick;

    private int energy;
    private int water;

    private boolean hasTracker;

    public ChainBuffer tailBuffer;

    private UUID owner;

    private InventoryDinosaur inventory;

    private static final int WATCHER_IS_CARCASS = 25;
    private static final int WATCHER_AGE = 26;
    private static final int WATCHER_GROWTH_OFFSET = 27;
    private static final int WATCHER_HAS_TRACKER = 28;

    public EntityDinosaur(World world)
    {
        super(world);

        inventory = new InventoryDinosaur(this);
        tailBuffer = new ChainBuffer(getTailBoxCount());

        energy = MAX_ENERGY;
        water = MAX_WATER;

        if (!dinosaur.isMarineAnimal())
        {
            tasks.addTask(0, new EntityAISwimming(this));
        }

        tasks.addTask(0, new EntityAIWander(this, 0.8));

        tasks.addTask(1, new EntityAIDrink(this));
        tasks.addTask(2, new EntityAIMate(this));

        tasks.addTask(2, new AnimationAICall(this));
        tasks.addTask(2, new AnimationAILook(this));
        tasks.addTask(2, new AnimationAIHeadCock(this));

        tasks.addTask(3, new EntityAIHerd(this));

        setFullyGrown();

        genetics = GeneticsHelper.randomGenetics(rand, getDinosaur(), getDNAQuality());
        isMale = rand.nextBoolean();

        animTick = 0;
        setAnimID(AnimID.IDLE);

        ignoreFrustumCheck = true; // stops dino disappearing when hitbox goes off screen

        updateCreatureData();
        adjustHitbox();
    }

    public abstract int getTailBoxCount();

    public boolean hasTracker()
    {
        return hasTracker;
    }

    public UUID getOwner()
    {
        return owner;
    }

    public void setOwner(EntityPlayer player)
    {
        this.owner = player.getUniqueID();
    }

    // need to override to add animations
    @Override
    public boolean attackEntityAsMob(Entity entity)
    {
        AnimationAPI.sendAnimPacket(this, AnimID.ATTACKING);

        float damage = (float) getEntityAttribute(SharedMonsterAttributes.attackDamage).getAttributeValue();
        int knockback = 0;

        if (entity instanceof EntityLivingBase)
        {
            damage += EnchantmentHelper.func_152377_a(getHeldItem(), ((EntityLivingBase) entity).getCreatureAttribute());
            knockback += EnchantmentHelper.getKnockbackModifier(this);
        }

        boolean attackSuccesful = entity.attackEntityFrom(new EntityDinosaurDamageSource("mob", this), damage);

        if (entity instanceof EntityLivingBase)
        {
            EntityLivingBase theEntityLivingBase = (EntityLivingBase) entity;
            // if attacked entity is killed, stop attacking animation
            if (theEntityLivingBase.getHealth() < 0.0F)
            {
                AnimationAPI.sendAnimPacket(this, AnimID.IDLE);
            }
        }

        if (attackSuccesful)
        {
            if (knockback > 0)
            {
                entity.addVelocity(-MathHelper.sin(rotationYaw * (float) Math.PI / 180.0F) * knockback * 0.5F, 0.1D, MathHelper.cos(rotationYaw * (float) Math.PI / 180.0F) * knockback * 0.5F);
                motionX *= 0.6D;
                motionZ *= 0.6D;
            }

            applyEnchantments(this, entity);
        }

        return attackSuccesful;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void performHurtAnimation()
    {
        AnimationAPI.sendAnimPacket(this, AnimID.INJURED);
    }

    @Override
    public void playLivingSound()
    {
        AnimationAPI.sendAnimPacket(this, AnimID.LIVING);
        super.playLivingSound();
    }

    public int getWater()
    {
        return water;
    }

    public int getEnergy()
    {
        return energy;
    }

    public void decreaseEnergy()
    {
        energy--;

        if (energy <= 0)
        {
            attackEntityFrom(DamageSource.outOfWorld, 1.0F);
        }
    }

    public void decreaseWater()
    {
        water--;

        if (water <= 0)
        {
            attackEntityFrom(DamageSource.outOfWorld, 1.0F);
        }
    }

    @Override
    public boolean attackEntityFrom(DamageSource damageSource, float amount)
    {
        if (getAnimID() == AnimID.IDLE)
        {
            AnimationAPI.sendAnimPacket(this, AnimID.INJURED);
        }

        return super.attackEntityFrom(damageSource, amount);
    }

    public void setWater(int water)
    {
        this.water = Math.min(water, 48000);
    }

    public void setEnergy(int energy)
    {
        this.energy = Math.min(energy, 48000);
    }

    @Override
    public void entityInit()
    {
        super.entityInit();
        dataWatcher.addObject(WATCHER_IS_CARCASS, 0);
        dataWatcher.addObject(WATCHER_AGE, 0);
        dataWatcher.addObject(WATCHER_GROWTH_OFFSET, 0);
        dataWatcher.addObject(WATCHER_HAS_TRACKER, 0);
    }

    /**
     * Checks if the entity is in range to render by using the past in distance and comparing it to its average edge length * 64 * renderDistanceWeight Args: distance
     */
    @Override
    @SideOnly(Side.CLIENT)
    public boolean isInRangeToRenderDist(double distance)
    {
        return true;
    }

    @Override
    protected void applyEntityAttributes()
    {
        super.applyEntityAttributes();

        dinosaur = JCEntityRegistry.getDinosaurByClass(getClass());

        getAttributeMap().registerAttribute(SharedMonsterAttributes.attackDamage);
        updateCreatureData();
        adjustHitbox();
    }

    public void updateCreatureData()
    {
        double prevHealth = getMaxHealth();
        double newHealth = transitionFromAge(dinosaur.getBabyHealth(), dinosaur.getAdultHealth());

        getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(newHealth);
        getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(transitionFromAge(dinosaur.getBabySpeed(), dinosaur.getAdultSpeed()));
        getEntityAttribute(SharedMonsterAttributes.knockbackResistance).setBaseValue(transitionFromAge(dinosaur.getBabyKnockback(), dinosaur.getAdultKnockback()));

        // adjustHitbox();

        getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(transitionFromAge(dinosaur.getBabyStrength(), dinosaur.getAdultStrength()));

        if (prevHealth != newHealth)
        {
            heal((float) (newHealth - lastDamage));
        }
    }

    private void adjustHitbox()
    {
        setSize((float) transitionFromAge(dinosaur.getBabySizeX(), dinosaur.getAdultSizeX()), (float) transitionFromAge(dinosaur.getBabySizeY(), dinosaur.getAdultSizeY()));
    }

    // Need to override because vanilla knockback makes big dinos get knocked into air
    @Override
    public void knockBack(Entity entity, float p_70653_2_, double motionX, double motionZ)
    {
        if (rand.nextDouble() >= getEntityAttribute(SharedMonsterAttributes.knockbackResistance).getAttributeValue())
        {
            isAirBorne = true;
            float distance = MathHelper.sqrt_double(motionX * motionX + motionZ * motionZ);
            float multiplier = 0.4F;
            this.motionX /= 2.0D;
            this.motionZ /= 2.0D;
            this.motionX -= motionX / distance * multiplier;
            this.motionZ -= motionZ / distance * multiplier;

            // TODO
            // We should make knockback bigger and into air if dino is much smaller than attacking dino
        }
    }

    public void applySettingsForActionFigure()
    {
        this.setFullyGrown();
        this.setMale(true);
        this.genetics = new GeneticsContainer(JCEntityRegistry.getDinosaurId(dinosaur), 0, 0, 0, 255, 255, 255);
}

    public double transitionFromAge(double baby, double adult)
    {
        int maxAge = dinosaur.getMaximumAge();

        if (dinosaurAge > maxAge)
        {
            dinosaurAge = maxAge;
        }

        return (adult - baby) / maxAge * dinosaurAge + baby;
    }

    @Override
    public int getTalkInterval()
    {
        return 1000;
    }

    @Override
    public float getSoundPitch()
    {
        return (float) transitionFromAge(1.5F, 1.0F) + ((rand.nextFloat() - 0.5F) * 0.125F);
    }

    @Override
    public float getSoundVolume()
    {
        return isCarcass ? 0.0F : (2.0F * ((float) transitionFromAge(0.3F, 1.0F)));
    }

    public void setGenetics(String genetics)
    {
        this.genetics = new GeneticsContainer(genetics);
    }

    public GeneticsContainer getGenetics()
    {
        return genetics;
    }

    @Override
    public void onLivingUpdate()
    {
        super.onLivingUpdate();

        if (!isCarcass)
        {
            if (firstUpdate)
            {
                updateCreatureData();
            }

            adjustHitbox();

            updateGrowth();

            updateMetabolism();
        }
    }

    private void updateGrowth()
    {
        if (!this.isDead && ticksExisted % 8 == 0)
        {
            if (worldObj.getGameRules().getGameRuleBooleanValue("dinoGrowth"))
            {
                dinosaurAge += Math.min(growthSpeedOffset, 960) + 1;
                energy -= (Math.min(growthSpeedOffset, 960) + 1) * 0.1;
            }

            if (dinosaurAge % 20 == 0)
            {
                updateCreatureData();
            }

            if (growthSpeedOffset > 0)
            {
                growthSpeedOffset -= 10;

                if (growthSpeedOffset < 0)
                {
                    growthSpeedOffset = 0;
                }
            }
        }
    }

    public void updateMetabolism()
    {
        if (!isDead && !isCarcass() && worldObj.getGameRules().getGameRuleBooleanValue("dinoMetabolism"))
        {
            decreaseEnergy();
            decreaseWater();

            if (this.isWet())
            {
                water = MAX_WATER;
            }
        }
    }

    @Override
    public void onUpdate()
    {
        super.onUpdate();

        this.tailBuffer.calculateChainSwingBuffer(68.0F, 5, 4.0F, this);

        if (this.isCarcass)
        {
            this.tasks.taskEntries.clear();
            this.targetTasks.taskEntries.clear();
        }

        if (!worldObj.isRemote)
        {
            if (prevAge != dinosaurAge)
            {
                dataWatcher.updateObject(WATCHER_AGE, dinosaurAge);
                prevAge = dinosaurAge;
            }

            dataWatcher.updateObject(WATCHER_GROWTH_OFFSET, growthSpeedOffset);
            dataWatcher.updateObject(WATCHER_HAS_TRACKER, hasTracker ? 1 : 0);
        }
        else
        {
            int age = dataWatcher.getWatchableObjectInt(WATCHER_AGE);

            if (age != dinosaurAge)
            {
                updateCreatureData();
                adjustHitbox();
                dinosaurAge = age;
            }

            growthSpeedOffset = dataWatcher.getWatchableObjectInt(WATCHER_GROWTH_OFFSET);
            hasTracker = dataWatcher.getWatchableObjectInt(WATCHER_HAS_TRACKER) == 1;
        }

        if (!worldObj.isRemote)
        {
            dataWatcher.updateObject(WATCHER_IS_CARCASS, isCarcass ? 1 : 0);
        }
        else
        {
            isCarcass = dataWatcher.getWatchableObjectInt(WATCHER_IS_CARCASS) == 1;
        }

        if (isCarcass)
        {
            if (getAnimID() != AnimID.DYING)
            {
                AnimationAPI.sendAnimPacket(this, AnimID.DYING);
            }
        }

        if (getAnimID() != AnimID.IDLE)
        {
            animTick++;
        }
    }


    public int getDaysExisted()
    {
        return (int) Math.floor((dinosaurAge * 8.0F) / 24000.0F);
    }

    public void setFullyGrown()
    {
        dinosaurAge = dinosaur.getMaximumAge();
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

    @Override
    public void writeToNBT(NBTTagCompound nbt)
    {
        super.writeToNBT(nbt);

        nbt.setDouble("DinosaurAge", dinosaurAge);
        nbt.setBoolean("IsCarcass", isCarcass);
        nbt.setInteger("DNAQuality", geneticsQuality);
        nbt.setString("Genetics", genetics.toString());
        nbt.setBoolean("IsMale", isMale);
        nbt.setInteger("GrowthSpeedOffset", growthSpeedOffset);
        nbt.setInteger("Energy", energy);
        nbt.setInteger("Water", water);

        if (owner != null)
        {
            nbt.setString("OwnerUUID", owner.toString());
        }

        inventory.writeToNBT(nbt);
    }

    @Override
    public void readFromNBT(NBTTagCompound nbt)
    {
        super.readFromNBT(nbt);
        System.out.println("Reading from NBT");
        dinosaurAge = nbt.getInteger("DinosaurAge");
        isCarcass = nbt.getBoolean("IsCarcass");
        geneticsQuality = nbt.getInteger("DNAQuality");
        genetics = new GeneticsContainer(nbt.getString("Genetics"));
        isMale = nbt.getBoolean("IsMale");
        growthSpeedOffset = nbt.getInteger("GrowthSpeedOffset");
        energy = nbt.getInteger("Energy");
        water = nbt.getInteger("Water");
        owner = UUID.fromString(nbt.getString("OwnerUUID"));

        inventory.readFromNBT(nbt);

        updateCreatureData();
        adjustHitbox();
    }

    @Override
    public void writeSpawnData(ByteBuf buffer)
    {
        System.out.println("Writing spawn data");
        buffer.writeInt(dinosaurAge);
        buffer.writeBoolean(isCarcass);
        buffer.writeInt(geneticsQuality);
        buffer.writeBoolean(isMale);
        buffer.writeInt(growthSpeedOffset);
        buffer.writeInt(energy);
        buffer.writeInt(water);
        ByteBufUtils.writeUTF8String(buffer, genetics.toString()); //TODO do we need to add the things that are on the datawatcher?
    }

    @Override
    public void readSpawnData(ByteBuf additionalData)
    {
        dinosaurAge = additionalData.readInt();
        isCarcass = additionalData.readBoolean();
        geneticsQuality = additionalData.readInt();
        isMale = additionalData.readBoolean();
        growthSpeedOffset = additionalData.readInt();
        energy = additionalData.readInt();
        water = additionalData.readInt();
        genetics = new GeneticsContainer(ByteBufUtils.readUTF8String(additionalData));

        updateCreatureData();
        adjustHitbox();
    }

    public int getDinosaurAge()
    {
        return dinosaurAge;
    }

    public void setAge(int age)
    {
        dinosaurAge = age;
    }

    @Override
    public float getEyeHeight()
    {
        return (float) transitionFromAge(dinosaur.getBabyEyeHeight(), dinosaur.getAdultEyeHeight());
    }

    /**
     * Drop 0-2 items of this living's type
     */
    @Override
    protected void dropFewItems(boolean p_70628_1_, int looting)
    {
        int meatAmount = (int) (rand.nextInt(3) + ((width * height) / 4)) + looting;

        for (int i = 0; i < meatAmount; ++i)
        {
            if (isBurning())
            {
                dropStackWithGenetics(new ItemStack(JCItemRegistry.dino_steak, 1, JCEntityRegistry.getDinosaurId(dinosaur)));
            }
            else
            {
                dropStackWithGenetics(new ItemStack(JCItemRegistry.dino_meat, 1, JCEntityRegistry.getDinosaurId(dinosaur)));
            }
        }

        inventory.dropItems(worldObj, rand);
    }

    private void dropStackWithGenetics(ItemStack stack)
    {
        NBTTagCompound nbt = new NBTTagCompound();
        nbt.setInteger("DNAQuality", geneticsQuality);
        nbt.setString("Genetics", genetics.toString());
        stack.setTagCompound(nbt);

        entityDropItem(stack, 0.0F);
    }

    public void setCarcass(boolean carcass)
    {
        if (carcass)
        {
            String s = getDeathSound();

            if (s != null)
            {
                playSound(s, getSoundVolume(), (rand.nextFloat() - rand.nextFloat()) * 0.2F + 1.0F);
            }
        }

        isCarcass = carcass;
    }

    public boolean isCarcass()
    {
        return isCarcass;
    }

    @Override
    public boolean interact(EntityPlayer player)
    {
        if (player.isSneaking())
        {
            if (getAgePercentage() > 75)
            {
                player.displayGUIChest(inventory);
            }
            else
            {
                if (worldObj.isRemote)
                {
                    String msg;

                    if (hasCustomName())
                    {
                        msg = getCustomNameTag();
                    }
                    else
                    {
                        msg = "This " + getCommandSenderName();
                    }

                    player.addChatComponentMessage(new ChatComponentText(msg + " is not old enough to hold items!")); //TODO translation
                }
            }
        }
        else
        {
            ItemStack heldItem = player.getHeldItem();

            if (heldItem != null)
            {
                Item item = heldItem.getItem();

                if (item instanceof ItemBluePrint)
                {
                    ((ItemBluePrint) item).setDinosaur(heldItem, JCEntityRegistry.getDinosaurId(getDinosaur()));
                }
            }
        }

        return false;
    }

    // NOTE: This adds an attack target. Class should be the entity class for the target, lower prio get executed
    // earlier
    protected void addAIForAttackTargets(Class entity, int prio)
    {
        tasks.addTask(0, new EntityAIAttackOnCollide(this, entity, 1.0D, false));
        targetTasks.addTask(0, new EntityAINearestAttackableTarget(this, entity, false));
    }

    @Override
    public boolean allowLeashing()
    {
        return !getLeashed() && (getAgePercentage() < 50);
    }

    // NOTE: This registers which attackers to defend from. Class should be the entity class for the attacker, lower
    // prio get executed earlier (Should be based upon attacker's strength and health to decide whether to defend or
    // flee)
    protected void defendFromAttacker(Class entity, int prio)
    {
        // targetTasks.addTask(prio, new EntityAIJCShouldDefend(this, true, entity));
        targetTasks.addTask(prio, new EntityAIHurtByTarget(this, true, entity));
    }

    public int getDNAQuality()
    {
        return geneticsQuality;
    }

    public void setDNAQuality(int quality)
    {
        this.geneticsQuality = quality;
    }

    @Override
    public void setAnimID(AnimID newAnimation)
    {
        JurassiCraft.instance.getLogger().debug("Setting anim id for entity " + getEntityId() + " to " + newAnimation);

        if (newAnimation != animID) // only process changes
        {
            animID = newAnimation;
        }
    }

    @Override
    public void setAnimTick(int tick)
    {
        animTick = tick;
    }

    @Override
    public AnimID getAnimID()
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
        return JurassiCraft.MODID + ":" + sounds[rand.nextInt(sounds.length)];
    }

    public double getAttackDamage()
    {
        return transitionFromAge(dinosaur.getBabyStrength(), dinosaur.getAdultStrength());
    }

    public boolean isStronger(EntityDinosaur dinosaur)
    {
        return getHealth() * (float) getAttackDamage() < dinosaur.getHealth() * (float) dinosaur.getAttackDamage();
    }

    public boolean isMale()
    {
        return isMale;
    }

    public void setMale(boolean male)
    {
        isMale = male;
    }

    public int getAgePercentage()
    {
        int age = getDinosaurAge();
        return age != 0 ? age * 100 / getDinosaur().getMaximumAge() : 0;
    }

    public int getOverlayR()
    {
        return genetics.getOverlayR();
    }

    public int getOverlayG()
    {
        return genetics.getOverlayG();
    }

    public int getOverlayB()
    {
        return genetics.getOverlayB();
    }

    public int getOverlay(int index)
    {
        switch (index)
        {
            case 0:
                return genetics.getOverlay1();
            case 1:
                return genetics.getOverlay2();
            case 2:
                return genetics.getOverlay3();
            default:
                return -1;
        }
    }

    public EnumGrowthStage getGrowthStage()
    {
        EnumGrowthStage stage = EnumGrowthStage.INFANT;

        int percent = getAgePercentage();

        if (percent > 75)
        {
            stage = EnumGrowthStage.ADULT;
        }
        else if (percent > 50)
        {
            stage = EnumGrowthStage.ADOLESCENT;
        }
        else if (percent > 25)
        {
            stage = EnumGrowthStage.JUVENILE;
        }

        return stage;
    }

    public void increaseGrowthSpeed()
    {
        growthSpeedOffset += 240;
    }

    /*
     * Used by DinosaurAnimator class to allow different cyclic animations when land dinosaur is in water (need to @Override the performMowzieSwimmingAnimations() method)
     */
    public boolean isSwimming()
    {
        return (isInWater() || isInLava());
    }

    public String getCallSound()
    {
        return null;
    }

    public void setHasTracker(boolean hasTracker)
    {
        this.hasTracker = hasTracker;
    }
}
