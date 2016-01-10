package org.jurassicraft.common.entity.base;

import io.netty.buffer.ByteBuf;
import net.ilexiconn.llibrary.client.model.modelbase.ChainBuffer;
import net.ilexiconn.llibrary.common.animation.Animation;
import net.ilexiconn.llibrary.common.animation.IAnimated;
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
import net.minecraft.util.BlockPos;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.registry.IEntityAdditionalSpawnData;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.timeless.animationapi.AIAnimation;
import net.timeless.animationapi.client.Animations;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jurassicraft.JurassiCraft;
import org.jurassicraft.common.damagesource.EntityDinosaurDamageSource;
import org.jurassicraft.common.dinosaur.Dinosaur;
import org.jurassicraft.common.entity.ai.EntityAIHerd;
import org.jurassicraft.common.entity.ai.EntityAIMate;
import org.jurassicraft.common.entity.ai.EntityAISleep;
import org.jurassicraft.common.entity.ai.animations.AnimationAICall;
import org.jurassicraft.common.entity.ai.animations.AnimationAIHeadCock;
import org.jurassicraft.common.entity.ai.animations.AnimationAILook;
import org.jurassicraft.common.entity.ai.metabolism.EntityAIDrink;
import org.jurassicraft.common.entity.ai.metabolism.EntityAIEatFoodItem;
import org.jurassicraft.common.entity.ai.metabolism.EntityAIFindPlant;
import org.jurassicraft.common.genetics.GeneticsContainer;
import org.jurassicraft.common.genetics.GeneticsHelper;
import org.jurassicraft.common.item.ItemBluePrint;
import org.jurassicraft.common.item.JCItemRegistry;

import java.util.UUID;

public abstract class EntityDinosaur extends EntityCreature implements IEntityAdditionalSpawnData, IAnimated
{
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
    private Animation animation;
    private int animTick;

    private boolean hasTracker;

    public ChainBuffer tailBuffer;

    private UUID owner;

    private InventoryDinosaur inventory;

    private static final int WATCHER_IS_CARCASS = 25;
    private static final int WATCHER_AGE = 26;
    private static final int WATCHER_GROWTH_OFFSET = 27;
    private static final int WATCHER_IS_SLEEPING = 28;

    private MetabolismContainer metabolism;

    private boolean isSleeping;
    private boolean goBackToSleep;

    private static final Logger LOGGER = LogManager.getLogger();

    public EntityDinosaur(World world)
    {
        super(world);

        metabolism = new MetabolismContainer(this);

        inventory = new InventoryDinosaur(this);
        tailBuffer = new ChainBuffer(getTailBoxCount());

        if (!dinosaur.isMarineAnimal())
        {
            tasks.addTask(0, new EntityAISwimming(this));
        }

        tasks.addTask(0, new EntityAISleep(this));

        tasks.addTask(1, new EntityAIDrink(this));
        tasks.addTask(1, new EntityAIMate(this));
        tasks.addTask(1, new EntityAIEatFoodItem(this));

        if (dinosaur.getDiet().doesEatPlants())
        {
            tasks.addTask(1, new EntityAIFindPlant(this));
        }

        tasks.addTask(2, new EntityAIWander(this, 0.8));
        tasks.addTask(2, new EntityAIHerd(this));

        tasks.addTask(3, new AnimationAICall(this));
        tasks.addTask(3, new AnimationAILook(this));
        tasks.addTask(3, new AnimationAIHeadCock(this));

        setFullyGrown();

        genetics = GeneticsHelper.randomGenetics(rand, getDinosaur(), getDNAQuality());
        isMale = rand.nextBoolean();

        animTick = 0;
        setAnimation(Animations.IDLE.get());

        goBackToSleep = true;

        ignoreFrustumCheck = true; // stops dino disappearing when hitbox goes off screen
    }

    public boolean shouldSleep()
    {
        return getDinosaurTime() > getDinosaur().getSleepingSchedule().getAwakeTime();
    }

    public void setSleeping(boolean sleeping)
    {
        if (this.isSleeping != sleeping)
        {
            if (sleeping)
            {
                Animation.sendAnimationPacket(this, Animations.SLEEPING.get());
            }
            else
            {
                Animation.sendAnimationPacket(this, Animations.IDLE.get());
            }
        }

        this.isSleeping = sleeping;
    }

    public int getDinosaurTime()
    {
        EnumSleepingSchedule sleepingSchedule = getDinosaur().getSleepingSchedule();

        long time = (worldObj.getWorldTime() % 24000) - sleepingSchedule.getWakeUpTime();

        if (time < 0)
        {
            time += 24000;
        }

        return (int) time;
    }

    public abstract int getTailBoxCount();

    public boolean hasTracker()
    {
        return hasTracker;
    }

    public void setHasTracker(boolean hasTracker)
    {
        this.hasTracker = hasTracker;
    }

    public UUID getOwner()
    {
        return owner;
    }

    public void setOwner(EntityPlayer player)
    {
        this.owner = player.getUniqueID();
    }

    @Override
    public boolean attackEntityAsMob(Entity entity)
    {
        Animation.sendAnimationPacket(this, Animations.ATTACKING.get());

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
                Animation.sendAnimationPacket(this, Animations.IDLE.get());
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
    public boolean attackEntityFrom(DamageSource damageSource, float amount)
    {
        if (getAnimation() == Animations.IDLE.get())
        {
            Animation.sendAnimationPacket(this, Animations.INJURED.get());
        }

        if (isSleeping)
        {
            isSleeping = false;
            dontGoBackToSleep();
        }

        return super.attackEntityFrom(damageSource, amount);
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

    @Override
    @SideOnly(Side.CLIENT)
    public void performHurtAnimation()
    {
        Animation.sendAnimationPacket(this, Animations.INJURED.get());
    }

    @Override
    public void playLivingSound()
    {
        Animation.sendAnimationPacket(this, Animations.LIVING_SOUND.get());
        super.playLivingSound();
    }

    @Override
    public void entityInit()
    {
        super.entityInit();

        dataWatcher.addObject(WATCHER_IS_CARCASS, 0);
        dataWatcher.addObject(WATCHER_AGE, 0);
        dataWatcher.addObject(WATCHER_GROWTH_OFFSET, 0);
        dataWatcher.addObject(WATCHER_IS_SLEEPING, 0);
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
//        getEntityAttribute(SharedMonsterAttributes.knockbackResistance).setBaseValue(transitionFromAge(dinosaur.getBabyKnockback(), dinosaur.getAdultKnockback())); TODO

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

    public double transitionFromAge(double baby, double adult)
    {
        int maxAge = dinosaur.getMaximumAge();

        if (dinosaurAge > maxAge)
        {
            dinosaurAge = maxAge;
        }

        return (adult - baby) / maxAge * dinosaurAge + baby;
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

    public void applySettingsForActionFigure()
    {
        this.setFullyGrown();
        this.setMale(true);
        this.genetics = new GeneticsContainer(JCEntityRegistry.getDinosaurId(dinosaur), 0, 0, 0, 255, 255, 255);
    }

    @Override
    public int getTalkInterval()
    {
        return 200;
    }

    @Override
    public float getSoundPitch()
    {
        return (float) transitionFromAge(1.5F, 1.0F) + ((rand.nextFloat() - 0.5F) * 0.125F);
    }

    @Override
    public float getSoundVolume()
    {
        return (isCarcass || isSleeping) ? 0.0F : (2.0F * ((float) transitionFromAge(0.3F, 1.0F)));
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

            updateGrowth();

            metabolism.update();
        }
    }

    private void updateGrowth()
    {
        if (!this.isDead && ticksExisted % 8 == 0 && !worldObj.isRemote)
        {
            if (worldObj.getGameRules().getBoolean("dinoGrowth"))
            {
                dinosaurAge += Math.min(growthSpeedOffset, 960) + 1;
                metabolism.decreaseFood((int) ((Math.min(growthSpeedOffset, 960) + 1) * 0.1));
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

    @Override
    public void onUpdate()
    {
        super.onUpdate();

        this.tailBuffer.calculateChainSwingBuffer(68.0F, 5, 4.0F, this);

        if (!worldObj.isRemote)
        {
            dataWatcher.updateObject(WATCHER_AGE, dinosaurAge);

            dataWatcher.updateObject(WATCHER_GROWTH_OFFSET, growthSpeedOffset);
            dataWatcher.updateObject(WATCHER_IS_SLEEPING, isSleeping ? 1 : 0);
            dataWatcher.updateObject(WATCHER_IS_CARCASS, isCarcass ? 1 : 0);
        }
        else
        {
            dinosaurAge = dataWatcher.getWatchableObjectInt(WATCHER_AGE);
            growthSpeedOffset = dataWatcher.getWatchableObjectInt(WATCHER_GROWTH_OFFSET);
            isSleeping = dataWatcher.getWatchableObjectInt(WATCHER_IS_SLEEPING) == 1;
            isCarcass = dataWatcher.getWatchableObjectInt(WATCHER_IS_CARCASS) == 1;
        }

        if (ticksExisted % 16 == 0)
        {
            updateCreatureData();
            adjustHitbox();
        }

        if (isCarcass)
        {
            if (getAnimation() != Animations.DYING.get())
            {
                Animation.sendAnimationPacket(this, Animations.DYING.get());
            }
        }

        if (isSleeping)
        {
            if (getAnimation() != Animations.SLEEPING.get())
            {
                Animation.sendAnimationPacket(this, Animations.SLEEPING.get());
            }

            if (!shouldSleep() && !worldObj.isRemote)
            {
                isSleeping = false;
            }
        }

        if (!shouldSleep() && !isSleeping)
        {
            goBackToSleep = true;
        }

        if (getAnimation() != Animations.IDLE.get())
        {
            animTick++;
        }

        prevAge = dinosaurAge;
    }

    @Override
    public boolean isMovementBlocked()
    {
        return isCarcass() || isSleeping();
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
                        msg = "This " + getName();
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
    protected void addAIForAttackTargets(Class<? extends EntityLivingBase> entity, int prio)
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

    public void setAnimation(Animation newAnimation)
    {
        JurassiCraft.instance.getLogger().debug("Setting anim id for entity " + getEntityId() + " to " + newAnimation);

        if (newAnimation != animation) // only process changes
        {
            animation = newAnimation;
        }
    }

    @Override
    public Animation[] animations()
    {
        return Animations.getAnimations();
    }

    @Override
    public void setAnimationTick(int tick)
    {
        animTick = tick;
    }

    @Override
    public Animation getAnimation()
    {
        return animation;
    }

    @Override
    public int getAnimationTick()
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

        metabolism.writeToNBT(nbt);

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
        dinosaurAge = nbt.getInteger("DinosaurAge");
        isCarcass = nbt.getBoolean("IsCarcass");
        geneticsQuality = nbt.getInteger("DNAQuality");
        genetics = new GeneticsContainer(nbt.getString("Genetics"));
        isMale = nbt.getBoolean("IsMale");
        growthSpeedOffset = nbt.getInteger("GrowthSpeedOffset");

        metabolism.readFromNBT(nbt);

        String ownerUUID = nbt.getString("OwnerUUID");

        if (ownerUUID != null && ownerUUID.length() > 0)
        {
            owner = UUID.fromString(ownerUUID);
        }

        inventory.readFromNBT(nbt);

        updateCreatureData();
        adjustHitbox();
    }

    @Override
    public void writeSpawnData(ByteBuf buffer)
    {
        buffer.writeInt(dinosaurAge);
        buffer.writeBoolean(isCarcass);
        buffer.writeInt(geneticsQuality);
        buffer.writeBoolean(isMale);
        buffer.writeInt(growthSpeedOffset);

        metabolism.writeSpawnData(buffer);

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

        metabolism.readSpawnData(additionalData);

        genetics = new GeneticsContainer(ByteBufUtils.readUTF8String(additionalData));

        updateCreatureData();
        adjustHitbox();
    }

    public MetabolismContainer getMetabolism()
    {
        return metabolism;
    }

    public boolean setSleepLocation(BlockPos sleepLocation, boolean moveTo)
    {
        if (moveTo)
        {
            int x = sleepLocation.getX();
            int y = sleepLocation.getY();
            int z = sleepLocation.getZ();

            return getNavigator().tryMoveToXYZ(x, y, z, 1.0);
        }

        return true;
    }

    public boolean isSleeping()
    {
        return isSleeping;
    }

    public boolean shouldGoBackToSleep()
    {
        return goBackToSleep;
    }

    public void dontGoBackToSleep()
    {
        this.goBackToSleep = false;
    }

    /**
     * Write all the stats about this dino to the log.  There are too many to write to chat.
     */
    public void writeStatsToLog()
    {
        LOGGER.info(this);
    }

    @Override
    public String toString()
    {
        return "EntityDinosaur{ " +
                dinosaur.getName() +
                ", remote=" + getEntityWorld().isRemote +
                ", isDead=" + isDead +
                ", isCarcass=" + isCarcass +
                ", isSleeping=" + isSleeping +
                ", goBackToSleep=" + goBackToSleep +
                "\n    " +
                ", dinosaurAge=" + dinosaurAge +
                ", prevAge=" + prevAge +
                ", maxAge" + dinosaur.getMaximumAge() +
                ", ticksExisted=" + ticksExisted +
                ", entityAge=" + entityAge +
                ", isMale=" + isMale +
                ", growthSpeedOffset=" + growthSpeedOffset +
                "\n    " +
                ", food=" + metabolism.getFood() + " / " + metabolism.getMaxFood() + " (" + metabolism.getMaxFood() * 0.875 + ")" +
                ", water=" + metabolism.getWater() + " / " + metabolism.getMaxWater() + " (" + metabolism.getMaxWater() * 0.875 + ")" +
                ", health=" + getHealth() + " / " + getMaxHealth() +
                "\n    " +
                ", pos=" + getPosition() +
                ", eyePos=" + getPositionEyes(0.0F) +
                ", eyeHeight=" + getEyeHeight() +
                ", lookX=" + getLookHelper().getLookPosX() + ", lookY=" + getLookHelper().getLookPosY() + ", lookZ=" + getLookHelper().getLookPosZ() +
                "\n    " +
                ", width=" + width +
                ", bb=" + getEntityBoundingBox() +
//                "\n    " +
//                ", anim=" + animation + (animation != null ? ", duration" + animation.duration : "" ) +

//                "dinosaur=" + dinosaur +
//                ", genetics=" + genetics +
//                ", geneticsQuality=" + geneticsQuality +
//                ", currentAnim=" + currentAnim +
//                ", animation=" + animation +
//                ", animTick=" + animTick +
//                ", hasTracker=" + hasTracker +
//                ", tailBuffer=" + tailBuffer +
//                ", owner=" + owner +
//                ", inventory=" + inventory +
//                ", metabolism=" + metabolism +
                " }";
    }
}
