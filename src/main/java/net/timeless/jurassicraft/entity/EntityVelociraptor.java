package net.timeless.jurassicraft.entity;

import net.ilexiconn.llibrary.client.model.modelbase.ChainBuffer;
import net.minecraft.entity.ai.EntityAIAttackOnCollide;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.passive.EntityPig;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import net.timeless.jurassicraft.api.animation.IAnimatedEntity;
import net.timeless.jurassicraft.entity.base.EntityDinosaurAggressive;

public class EntityVelociraptor extends EntityDinosaurAggressive implements IAnimatedEntity
{
    public static final int LEAPING_ANIMATION_ID = 0;
    public ChainBuffer tailBuffer = new ChainBuffer(5);
    private int animID;

    public EntityVelociraptor(World world)
    {
        super(world);
        setAnimID(-1);
        // this.tasks.addTask(0, new EntityAIAttackOnCollide(this, EntityPlayer.class, 0.5F, false));
        // this.targetTasks.addTask(0, new EntityAINearestAttackableTarget(this, EntityPlayer.class, false));

        // Placeholder AIs
        if (!this.isChild())
        {
            this.tasks.addTask(0, new EntityAIAttackOnCollide(this, EntityPlayer.class, dinosaur.getAttackSpeed(), false));
            this.targetTasks.addTask(0, new EntityAINearestAttackableTarget(this, EntityPlayer.class, false));
            this.tasks.addTask(0, new EntityAIAttackOnCollide(this, EntityPig.class, dinosaur.getAttackSpeed(), false));
            this.targetTasks.addTask(0, new EntityAINearestAttackableTarget(this, EntityPig.class, false));

            this.targetTasks.addTask(1, new EntityAIHurtByTarget(this, true, EntityPlayer.class));

            this.tasks.addTask(6, new EntityAIWander(this, dinosaur.getAdultSpeed()));
            this.tasks.addTask(7, new EntityAIWatchClosest(this, EntityPlayer.class, 6.0F));
            this.tasks.addTask(8, new EntityAILookIdle(this));
        }
    }

    public void onUpdate()
    {
        this.tailBuffer.calculateChainSwingBuffer(68.0F, 5, 4.0F, this);
        super.onUpdate();
    }

    @Override
    public void setAnimID(int id)
    {
        animID = id;
    }

    @Override
    public int getAnimID()
    {
        return animID;
    }
}
