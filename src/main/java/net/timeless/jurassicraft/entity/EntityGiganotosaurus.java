package net.timeless.jurassicraft.entity;

import net.ilexiconn.llibrary.common.entity.multipart.IEntityMultiPart;
import net.minecraft.entity.ai.EntityAIAttackOnCollide;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.passive.EntityPig;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import net.timeless.jurassicraft.entity.ai.EntityAIJCLookIdle;
import net.timeless.jurassicraft.entity.ai.EntityAIJCWander;
import net.timeless.jurassicraft.entity.ai.EntityAIJCWatchClosest;
import net.timeless.jurassicraft.entity.base.EntityDinosaurAggressive;

public class EntityGiganotosaurus extends EntityDinosaurAggressive implements IEntityMultiPart
{
    public EntityGiganotosaurus(World world)
    {
        super(world);

        if (!this.isChild())
        {
            this.tasks.addTask(0, new EntityAIAttackOnCollide(this, EntityPlayer.class, dinosaur.getAttackSpeed(), false));
            this.targetTasks.addTask(0, new EntityAINearestAttackableTarget(this, EntityPlayer.class, false));
            this.tasks.addTask(0, new EntityAIAttackOnCollide(this, EntityPig.class, dinosaur.getAttackSpeed(), false));
            this.targetTasks.addTask(0, new EntityAINearestAttackableTarget(this, EntityGallimimus.class, false));

            this.targetTasks.addTask(1, new EntityAIHurtByTarget(this, true, EntityPlayer.class));

            this.tasks.addTask(6, new EntityAIJCWander(this, dinosaur.getAdultSpeed(), 40));
            this.tasks.addTask(7, new EntityAIJCWatchClosest(this, EntityPlayer.class, 6.0F, 1F, 40F));
            this.tasks.addTask(8, new EntityAIJCLookIdle(this, 1F, 40F));
        }
    }

    public float getEyeHeight()
    {
        return 5F;
    }
}