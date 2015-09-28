package org.jurassicraft.common.entity.base;

import io.netty.buffer.ByteBuf;

import java.util.HashSet;
import java.util.Set;

import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackOnCollide;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILeapAtTarget;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.item.EntityXPOrb;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumParticleTypes;
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

import org.jurassicraft.JurassiCraft;
import org.jurassicraft.common.dinosaur.Dinosaur;
import org.jurassicraft.common.disease.Disease;
import org.jurassicraft.common.entity.ai.EntityAIDrink;
import org.jurassicraft.common.entity.ai.EntityAIMetabolism;
import org.jurassicraft.common.genetics.GeneticsContainer;
import org.jurassicraft.common.genetics.GeneticsHelper;
import org.jurassicraft.common.item.ItemBluePrint;
import org.jurassicraft.common.item.JCItemRegistry;

public class EntityDinosaur extends EntityCreature implements IEntityAdditionalSpawnData, IAnimatedEntity, IInventory
{
    protected Dinosaur dinosaur;
    protected int randTexture;

    protected int dinosaurAge;
    protected int prevAge;

    protected Set<Disease> diseases = new HashSet<Disease>();

    // private boolean isCarcass;

    private int quality;

    private int animTick;
    private AnimID animID;

    private GeneticsContainer genetics;

    public AIAnimation currentAnim = null;

    private boolean isMale;

    private int growthSpeedOffset;

    private ItemStack[] inventory = new ItemStack[54];

    private double energy;
    private double water;

    private boolean hasTracker;

//    public void setNavigator(PathNavigate pn)
//    {
//        navigator = pn;
//    }

    public EntityDinosaur(World world)
    {
        super(world);

        energy = 12000;
        water = 12000;

        if (!dinosaur.isMarineAnimal())
        {
            tasks.addTask(0, new EntityAISwimming(this));
        }

        tasks.addTask(0, new EntityAIMetabolism(this));
        tasks.addTask(1, new EntityAIDrink(this));

        dinosaurAge = 0;

        genetics = GeneticsHelper.randomGenetics(rand, getDinosaur(), getDNAQuality());
        isMale = rand.nextBoolean();

        animTick = 0;
        setAnimID(AnimID.IDLE);

        ignoreFrustumCheck = true; // stops dino disappearing when hitbox goes off screen

        updateCreatureData();
        adjustHitbox();
    }

    public boolean hasTracker()
    {
        return hasTracker;
    }

    public boolean checkForTracker()
    {
        for (int i = 0; i < getSizeInventory(); i++)
        {
            ItemStack stack = getStackInSlot(i);

            if (stack != null && stack.getItem() == JCItemRegistry.tracker)
            {
                hasTracker = true;
                return true;
            }
        }

        hasTracker = false;
        return false;
    }
    
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

        boolean attacked = entity.attackEntityFrom(DamageSource.causeMobDamage(this), damage);

        if (attacked)
        {
            if (knockback > 0)
            {
                entity.addVelocity(-MathHelper.sin(rotationYaw * (float) Math.PI / 180.0F) * knockback * 0.5F, 0.1D, MathHelper.cos(rotationYaw * (float) Math.PI / 180.0F) * knockback * 0.5F);
                motionX *= 0.6D;
                motionZ *= 0.6D;
            }

            applyEnchantments(this, entity);
        }

        return attacked;
    }

    /**
     * Called when the entity is attacked.
     */
    @Override
    public boolean attackEntityFrom(DamageSource source, float amount)
    {
        if (isEntityInvulnerable(source))
        {
            return false;
        }
        else if (super.attackEntityFrom(source, amount))
        {
            Entity entity = source.getEntity();
            return riddenByEntity != entity && ridingEntity != entity ? true : true;
        }
        else
        {
            return false;
        }
    }
    
    @Override
    public void playLivingSound()
    {
        AnimationAPI.sendAnimPacket(this, AnimID.ROARING);
        super.playLivingSound();
    }

    public double getWater()
    {
        return water;
    }

    public double getEnergy()
    {
        return energy;
    }

    public void decreaseEnergy()
    {
        energy--;

        if (energy <= 0)
        {
            attackEntityFrom(DamageSource.outOfWorld, Float.MAX_VALUE);
        }
    }

    public void decreaseWater()
    {
        water--;

        if (water <= 0)
        {
            attackEntityFrom(DamageSource.outOfWorld, Float.MAX_VALUE);
        }
    }

    public void setWater(double water)
    {
        water = Math.min(water, 24000);
    }

    public void setEnergy(double energy)
    {
        energy = Math.min(energy, 24000);
    }

    @Override
    public void entityInit()
    {
        super.entityInit();
        dataWatcher.addObject(25, 0);
        dataWatcher.addObject(26, 0);
        dataWatcher.addObject(27, 0);
        dataWatcher.addObject(28, 0);
    }

    /**
     * Checks if the entity is in range to render by using the past in distance and comparing it to its average edge
     * length * 64 * renderDistanceWeight Args: distance
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

        updateCreatureData();
        adjustHitbox();
    }

    public void updateCreatureData()
    {
        double newHealth = transitionFromAge(dinosaur.getBabyHealth(), dinosaur.getAdultHealth());

        getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(newHealth);
        getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(transitionFromAge(dinosaur.getBabySpeed(), dinosaur.getAdultSpeed()));
        getEntityAttribute(SharedMonsterAttributes.knockbackResistance).setBaseValue(transitionFromAge(dinosaur.getBabyKnockback(), dinosaur.getAdultKnockback()));

        // adjustHitbox();

        heal((float) (newHealth - lastDamage));
    }

    private void adjustHitbox()
    {
        setSize((float) transitionFromAge(dinosaur.getBabySizeX(), dinosaur.getAdultSizeX()), (float) transitionFromAge(dinosaur.getBabySizeY(), dinosaur.getAdultSizeY()));
    }

    public double transitionFromAge(double baby, double adult)
    {
        int dinosaurAge = this.dinosaurAge;
        int maxAge = dinosaur.getMaximumAge();

        if (dinosaurAge > maxAge)
            dinosaurAge = maxAge;

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
        return (10.0F*((float)transitionFromAge(0.3F, 1.0F)));
//        return (float) transitionFromAge(0.3F, 1.0F) + ((rand.nextFloat() - 0.5F) * 0.125F);
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

        if (firstUpdate)
        {
            updateCreatureData();
        }

        adjustHitbox();

        if (ticksExisted % 8 == 0)
        {
            dinosaurAge += Math.min(growthSpeedOffset, 960) + 1;
            energy -= (Math.min(growthSpeedOffset, 960) + 1) * 0.1;

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

    @Override
    public void onUpdate()
    {
        super.onUpdate();

        if (!worldObj.isRemote)
        {
            if (prevAge != dinosaurAge)
            {
                dataWatcher.updateObject(26, dinosaurAge);
                prevAge = dinosaurAge;
            }

            dataWatcher.updateObject(27, growthSpeedOffset);

            dataWatcher.updateObject(28, hasTracker ? 1 : 0);
        }
        else
        {
            int age = dataWatcher.getWatchableObjectInt(26);

            if (age != dinosaurAge)
            {
                updateCreatureData();
                adjustHitbox();
                dinosaurAge = age;
            }

            growthSpeedOffset = dataWatcher.getWatchableObjectInt(27);

            hasTracker = dataWatcher.getWatchableObjectInt(28) == 1;
        }

        // if (!worldObj.isRemote)
        // {
        // dataWatcher.updateObject(25, isCarcass ? 1 : 0);
        // }
        // else
        // {
        // isCarcass = dataWatcher.getWatchableObjectInt(25) == 1;
        // }
        if (getAnimID() != AnimID.IDLE)
            animTick++;
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

        nbt.setInteger("Texture", randTexture);
        nbt.setDouble("DinosaurAge", dinosaurAge);
        // nbt.setBoolean("IsCarcass", isCarcass);
        nbt.setInteger("DNAQuality", quality);
        nbt.setString("Genetics", genetics.toString());
        nbt.setBoolean("IsMale", isMale);
        nbt.setInteger("GrowthSpeedOffset", growthSpeedOffset);
        nbt.setDouble("Energy", energy);
        nbt.setDouble("Water", water);

        NBTTagList nbttaglist = new NBTTagList();

        for (int i = 0; i < inventory.length; ++i)
        {
            if (inventory[i] != null)
            {
                NBTTagCompound slotTag = new NBTTagCompound();
                slotTag.setByte("Slot", (byte) i);
                inventory[i].writeToNBT(slotTag);
                nbttaglist.appendTag(slotTag);
            }
        }

        nbt.setTag("Items", nbttaglist);
    }

    @Override
    public void readFromNBT(NBTTagCompound nbt)
    {
        super.readFromNBT(nbt);

        randTexture = nbt.getInteger("Texture");
        dinosaurAge = nbt.getInteger("DinosaurAge");
        // isCarcass = nbt.getBoolean("IsCarcass");
        quality = nbt.getInteger("DNAQuality");
        genetics = new GeneticsContainer(nbt.getString("Genetics"));
        isMale = nbt.getBoolean("IsMale");
        growthSpeedOffset = nbt.getInteger("GrowthSpeedOffset");
        energy = nbt.getDouble("Energy");
        water = nbt.getDouble("Water");

        NBTTagList nbttaglist = nbt.getTagList("Items", 10);
        inventory = new ItemStack[getSizeInventory()];

        for (int i = 0; i < nbttaglist.tagCount(); ++i)
        {
            NBTTagCompound slotTag = nbttaglist.getCompoundTagAt(i);
            int j = slotTag.getByte("Slot") & 255;

            if (j >= 0 && j < inventory.length)
            {
                setInventorySlotContents(j, ItemStack.loadItemStackFromNBT(slotTag));
            }
        }

        updateCreatureData();
        adjustHitbox();
    }

    @Override
    public void writeSpawnData(ByteBuf buffer)
    {
        buffer.writeInt(randTexture);
        buffer.writeInt(dinosaurAge);
        // buffer.writeBoolean(isCarcass);
        buffer.writeInt(quality);
        buffer.writeBoolean(isMale);
        buffer.writeInt(growthSpeedOffset);
        buffer.writeDouble(energy);
        buffer.writeDouble(water);
        ByteBufUtils.writeUTF8String(buffer, genetics.toString());
    }

    @Override
    public void readSpawnData(ByteBuf additionalData)
    {
        randTexture = additionalData.readInt();
        dinosaurAge = additionalData.readInt();
        // isCarcass = additionalData.readBoolean();
        quality = additionalData.readInt();
        isMale = additionalData.readBoolean();
        growthSpeedOffset = additionalData.readInt();
        energy = additionalData.readDouble();
        water = additionalData.readDouble();

        genetics = new GeneticsContainer(ByteBufUtils.readUTF8String(additionalData));

        updateCreatureData();
        adjustHitbox();
    }

    public int getDinosaurAge()
    {
        return dinosaurAge;
    }

    public int getTexture()
    {
        return randTexture;
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
                dropStackWithQuality(new ItemStack(JCItemRegistry.dino_steak, 1, JCEntityRegistry.getDinosaurId(dinosaur)));
            else
                dropStackWithQuality(new ItemStack(JCItemRegistry.dino_meat, 1, JCEntityRegistry.getDinosaurId(dinosaur)));
        }

        for (int i = 0; i < getSizeInventory(); ++i)
        {
            ItemStack itemstack = getStackInSlot(i);

            if (itemstack != null)
            {
                float f = rand.nextFloat() * 0.8F + 0.1F;
                float f1 = rand.nextFloat() * 0.8F + 0.1F;
                float f2 = rand.nextFloat() * 0.8F + 0.1F;

                while (itemstack.stackSize > 0)
                {
                    int j = rand.nextInt(21) + 10;

                    if (j > itemstack.stackSize)
                    {
                        j = itemstack.stackSize;
                    }

                    itemstack.stackSize -= j;
                    EntityItem entityitem = new EntityItem(worldObj, posX + f, posY + f1, posZ + f2, new ItemStack(itemstack.getItem(), j, itemstack.getItemDamage()));
                    float f3 = 0.05F;
                    entityitem.motionX = (float) rand.nextGaussian() * f3;
                    entityitem.motionY = (float) rand.nextGaussian() * f3 + 0.2F;
                    entityitem.motionZ = (float) rand.nextGaussian() * f3;
                    worldObj.spawnEntityInWorld(entityitem);
                }
            }
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

    // public void setCarcass(boolean carcass)
    // {
    // isCarcass = carcass;
    //
    // if (isCarcass)
    // {
    // String s = getDeathSound();
    //
    // if (s != null)
    // {
    // playSound(s, getSoundVolume(), (rand.nextFloat() - rand.nextFloat()) * 0.2F + 1.0F);
    // }
    // }
    // }
    //
    // public boolean isCarcass()
    // {
    // return isCarcass;
    // }

    @Override
    public boolean interact(EntityPlayer player)
    {
        if (player.isSneaking())
        {
            if (getAgePercentage() > 75)
            {
                player.displayGUIChest(this);
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

                    player.addChatComponentMessage(new ChatComponentText(msg + " is not old enough to hold items!"));
                }
            }
        }
        else
        {
            ItemStack heldItem = player.getHeldItem();

            if (heldItem != null)
            {
                if (heldItem.getItem() instanceof ItemBluePrint)
                {
                    ((ItemBluePrint) heldItem.getItem()).setDinosaur(heldItem, JCEntityRegistry.getDinosaurId(getDinosaur()));
                }
            }
        }

        return false;
    }

    // NOTE: This adds an attack target. Class should be the entity class for the target, lower prio get executed
    // earlier
    protected void attackCreature(Class entity, int prio)
    {
        tasks.addTask(0, new EntityAIAttackOnCollide(this, entity, 1.0D, false));
        tasks.addTask(1, new EntityAILeapAtTarget(this, 0.4F));
        targetTasks.addTask(0, new EntityAINearestAttackableTarget(this, entity, false));
    }

    @Override
    public boolean allowLeashing()
    {
        return !getLeashed() && (getDinosaurAge() <= 7500);
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
        return quality;
    }

    public void setDNAQuality(int quality)
    {
        quality = quality;
    }

    @Override
    public void setAnimID(AnimID parAnimID)
    {
        JurassiCraft.instance.getLogger().debug("Setting anim id for entity " + getEntityId() + " to " + parAnimID);

        if (parAnimID != animID) // only process changes
        {
            animID = parAnimID;
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

    public int getAgePercentage()
    {
        int age = getDinosaurAge();
        return age != 0 ? age * 100 / getDinosaur().getMaximumAge() : 0;
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

    public Set<Disease> getDiseases()
    {
        return diseases;
    }

    public void addDisease(Disease disease)
    {
        diseases.add(disease);
    }

    @Override
    public void onDeath(DamageSource parDamageSource)
    {
        super.onDeath(parDamageSource);
    }
    
    /**
     * have to override so that death time can go much longer so that we can see a good dying animation
     */
    @Override
    protected void onDeathUpdate()
    {
        ++deathTime;
        
        if (getAnimID() != AnimID.DYING)
        {
            AnimationAPI.sendAnimPacket(this, AnimID.DYING);
        }

        if (deathTime == 600)
        {
            int i;

            if (!worldObj.isRemote && (recentlyHit > 0 || isPlayer()) && canDropLoot() && worldObj.getGameRules().getGameRuleBooleanValue("doMobLoot"))
            {
                i = getExperiencePoints(attackingPlayer);
                i = net.minecraftforge.event.ForgeEventFactory.getExperienceDrop(this, attackingPlayer, i);
                while (i > 0)
                {
                    int j = EntityXPOrb.getXPSplit(i);
                    i -= j;
                    worldObj.spawnEntityInWorld(new EntityXPOrb(worldObj, posX, posY, posZ, j));
                }
            }

            setDead();

            for (i = 0; i < 20; ++i)
            {
                double d2 = rand.nextGaussian() * 0.02D;
                double d0 = rand.nextGaussian() * 0.02D;
                double d1 = rand.nextGaussian() * 0.02D;
                worldObj.spawnParticle(EnumParticleTypes.EXPLOSION_NORMAL, posX + rand.nextFloat() * width * 2.0F - width, posY + rand.nextFloat() * height, posZ + rand.nextFloat() * width * 2.0F - width, d2, d0, d1, new int[0]);
            }
        }
    }


    public void increaseGrowthSpeed()
    {
        growthSpeedOffset += 240;
    }

    @Override
    public int getSizeInventory()
    {
        return dinosaur.getStorage();
    }

    @Override
    public ItemStack getStackInSlot(int index)
    {
        return inventory[index];
    }

    @Override
    public ItemStack decrStackSize(int index, int count)
    {
        if (inventory[index] != null)
        {
            ItemStack itemstack;

            if (inventory[index].stackSize <= count)
            {
                itemstack = inventory[index];
                setInventorySlotContents(index, null);
                return itemstack;
            }
            else
            {
                itemstack = inventory[index].splitStack(count);

                if (inventory[index].stackSize == 0)
                {
                    setInventorySlotContents(index, null);
                }

                return itemstack;
            }
        }
        else
        {
            return null;
        }
    }

    @Override
    public ItemStack getStackInSlotOnClosing(int index)
    {
        if (inventory[index] != null)
        {
            ItemStack itemstack = inventory[index];
            setInventorySlotContents(index, null);
            return itemstack;
        }
        else
        {
            return null;
        }
    }

    @Override
    public void setInventorySlotContents(int index, ItemStack stack)
    {
        inventory[index] = stack;

        checkForTracker();

        if (stack != null && stack.stackSize > getInventoryStackLimit())
        {
            stack.stackSize = getInventoryStackLimit();
        }
    }

    @Override
    public int getInventoryStackLimit()
    {
        return 64;
    }

    @Override
    public void markDirty()
    {
    }

    @Override
    public boolean isUseableByPlayer(EntityPlayer player)
    {
        return isDead ? false : player.getDistanceSqToEntity(this) <= 64.0D;
    }

    @Override
    public void openInventory(EntityPlayer player)
    {
    }

    @Override
    public void closeInventory(EntityPlayer player)
    {
    }

    @Override
    public boolean isItemValidForSlot(int index, ItemStack stack)
    {
        return true;
    }

    @Override
    public int getField(int id)
    {
        return 0;
    }

    @Override
    public void setField(int id, int value)
    {

    }

    @Override
    public int getFieldCount()
    {
        return 0;
    }

    @Override
    public void clear()
    {
        for (int i = 0; i < getSizeInventory(); i++)
        {
            setInventorySlotContents(i, null);
        }
    }
}
