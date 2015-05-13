package net.ilexiconn.jurassicraft.entity;

import io.netty.buffer.ByteBuf;

import java.util.List;

import net.ilexiconn.jurassicraft.entity.animation.AnimationVelociraptorLeap;
import net.ilexiconn.jurassicraft.entity.animation.IEntityAnimation;
import net.ilexiconn.llibrary.entity.multipart.EntityPart;
import net.ilexiconn.llibrary.entity.multipart.IEntityMultiPart;
import net.ilexiconn.llibrary.json.JsonHelper;
import net.minecraft.entity.EntityCreature;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.IEntityAdditionalSpawnData;

import com.google.common.collect.Lists;

public class EntityDinosaur extends EntityCreature implements IEntityMultiPart, IEntityAdditionalSpawnData
{
    protected EntityPart[] parts;
    protected Creature creature;

    protected boolean gender;

    protected int playingAnimation;
    protected boolean animationInProgress;
    protected float animationTimer;

    protected List<IEntityAnimation> animations = Lists.newArrayList();

    public EntityDinosaur(World world)
    {
        super(world);
        parts = JsonHelper.parseHitboxList(this, creature.getHitboxList());
        gender = rand.nextBoolean();
    }

    public void entityInit()
    {
        super.entityInit();
        this.getDataWatcher().addObject(25, -1);
        this.getDataWatcher().addObject(26, 0);
    }

    protected void applyEntityAttributes()
    {
        super.applyEntityAttributes();

        creature = JCEntityRegistry.getCreatureFromClass(getClass());
    }

    public Creature getCreature()
    {
        return creature;
    }

    public EntityPart[] getParts()
    {
        return parts;
    }

    protected void registerAnimation(IEntityAnimation animation)
    {
        animations.add(animation);
    }

    public void onUpdate()
    {
        super.onUpdate();

        if(worldObj.isRemote)
        {
            this.playingAnimation = this.getDataWatcher().getWatchableObjectInt(25);
            this.animationInProgress = this.getDataWatcher().getWatchableObjectInt(26) == 1;
        }
        
        this.updateAnimation();
    }

    public void startAnimation(int id)
    {
        if(!animationInProgress)
        {
            playingAnimation = id;
            animationTimer = 0;
            animationInProgress = true;

            updateAnimationWatcher();
        }
    }

    private void updateAnimationWatcher()
    {
        if(!worldObj.isRemote)
        {
            this.getDataWatcher().updateObject(25, playingAnimation);
            this.getDataWatcher().updateObject(26, animationInProgress ? 1 : 0);
        }
    }

    public void updateAnimation()
    {
        if(animationInProgress && playingAnimation != -1)
        {
            IEntityAnimation animation = animations.get(playingAnimation);

            animationTimer += animation.getSpeed();

            if(animationTimer >= animation.getLength())
            {
                stopAllAnimations();
            }
        }
    }

    public void stopAllAnimations()
    {
        if(!worldObj.isRemote)
        {
            playingAnimation = -1;
            animationInProgress = false;

            updateAnimationWatcher();
        }
    }

    public boolean isMale()
    {
        return gender;
    }

    public void writeToNBT(NBTTagCompound nbt)
    {
        super.writeToNBT(nbt);

        nbt.setBoolean("Gender", gender);
        nbt.setInteger("Animation", playingAnimation);
        nbt.setBoolean("AnimationInProgress", animationInProgress);
        nbt.setFloat("AnimationTimer", animationTimer);
    }

    public void readFromNBT(NBTTagCompound nbt)
    {
        super.readFromNBT(nbt);

        gender = nbt.getBoolean("Gender");
        playingAnimation = nbt.getInteger("Animation");
        animationInProgress = nbt.getBoolean("AnimationInProgress");
        animationTimer = nbt.getFloat("AnimationTimer");
    }

    @Override
    public void writeSpawnData(ByteBuf buffer)
    {
        buffer.writeBoolean(gender);
    }

    @Override
    public void readSpawnData(ByteBuf additionalData)
    {
        gender = additionalData.readBoolean();
    }

    public int getAnimationPlaying()
    {
        return playingAnimation;
    }

    public IEntityAnimation getAnimation(int animation)
    {
        return animations.get(animation);
    }

    public boolean isAnimationInProgress()
    {
        return animationInProgress;
    }

    public float getAnimationProgress()
    {
        return animationTimer;
    }
}