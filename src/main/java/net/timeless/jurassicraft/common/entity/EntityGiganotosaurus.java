package net.timeless.jurassicraft.common.entity;

import net.minecraft.entity.ai.EntityAIAttackOnCollide;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.passive.EntityPig;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import net.reuxertz.ecoapi.ecology.role.ICarnivore;
import net.reuxertz.ecoapi.entity.IEntityAICreature;
import net.timeless.jurassicraft.common.entity.ai.EntityAIJCWander;
import net.timeless.jurassicraft.common.entity.base.EntityDinosaurAggressive;

public class EntityGiganotosaurus extends EntityDinosaurAggressive implements IEntityAICreature, ICarnivore
{
    public EntityGiganotosaurus(World world)
    {
        super(world);

        this.tasks.addTask(0, new EntityAIAttackOnCollide(this, EntityPlayer.class, dinosaur.getAttackSpeed(), false));
        this.targetTasks.addTask(0, new EntityAINearestAttackableTarget(this, EntityPlayer.class, false));
        this.tasks.addTask(0, new EntityAIAttackOnCollide(this, EntityPig.class, dinosaur.getAttackSpeed(), false));
        this.targetTasks.addTask(0, new EntityAINearestAttackableTarget(this, EntityGallimimus.class, false));

        this.targetTasks.addTask(1, new EntityAIHurtByTarget(this, true, EntityPlayer.class));

        this.tasks.addTask(6, new EntityAIJCWander(this, 40));
        this.tasks.addTask(7, new EntityAIWatchClosest(this, EntityPlayer.class, 6.0F));
        this.tasks.addTask(8, new EntityAILookIdle(this));
    }
}