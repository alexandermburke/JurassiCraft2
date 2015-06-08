package net.ilexiconn.jurassicraft.entity;

import net.ilexiconn.jurassicraft.dinosaur.DinosaurSpinosaurus;
import net.ilexiconn.jurassicraft.entity.base.EntityDinosaurAggressive;
import net.ilexiconn.llibrary.client.model.modelbase.ChainBuffer;
import net.ilexiconn.llibrary.common.entity.multipart.IEntityMultiPart;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackOnCollide;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.passive.EntityPig;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

public class EntitySpinosaurus extends EntityDinosaurAggressive implements IEntityMultiPart
{
    public ChainBuffer tailBuffer = new ChainBuffer(6);

    public EntitySpinosaurus(World world)
    {
        super(world);
        DinosaurSpinosaurus dino = new DinosaurSpinosaurus();
        if (!this.isChild())
        {
            this.tasks.addTask(0, new EntityAIAttackOnCollide(this, EntityPlayer.class, dino.getAttackSpeed(), false));
            this.targetTasks.addTask(0, new EntityAINearestAttackableTarget(this, EntityPlayer.class, false));
            this.tasks.addTask(0, new EntityAIAttackOnCollide(this, EntityPig.class, dino.getAttackSpeed(), false));
            this.targetTasks.addTask(0, new EntityAINearestAttackableTarget(this, EntityPig.class, false));

            this.targetTasks.addTask(1, new EntityAIHurtByTarget(this, true, new Class[] { EntityPlayer.class }));

            this.tasks.addTask(6, new EntityAIWander(this, 1.0D));
            this.tasks.addTask(7, new EntityAIWatchClosest(this, EntityPlayer.class, 6.0F));
            this.tasks.addTask(8, new EntityAILookIdle(this));
        }
    }

    public void onUpdate()
    {
        super.onUpdate();
        this.tailBuffer.calculateChainSwingBuffer(68.0F, 10, 4.0F, this);
    }

    protected void applyEntityAttributes()
    {
        DinosaurSpinosaurus dino = new DinosaurSpinosaurus();
        super.applyEntityAttributes();
        if (this.isChild())
        {
            this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(dino.getBabyHealth());
            this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(dino.getBabySpeed());
            this.getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(dino.getBabyStrength());
            this.getEntityAttribute(SharedMonsterAttributes.knockbackResistance).setBaseValue(dino.getBabyKnockback());
        }
        else
        {
            this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(dino.getAdultHealth());
            this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(dino.getAdultSpeed());
            this.getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(dino.getAdultStrength());
            this.getEntityAttribute(SharedMonsterAttributes.knockbackResistance).setBaseValue(dino.getAdultKnockback());
        }
    }
}