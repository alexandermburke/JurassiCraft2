package net.timeless.jurassicraft.common.entity;

import net.minecraft.world.World;
import net.reuxertz.ecoapi.ecology.role.ICarnivore;
import net.reuxertz.ecoapi.entity.IEntityAISwimmingCreature;
import net.timeless.jurassicraft.common.entity.base.EntityDinosaurSwimmingAggressive;

public class EntityCoelacanth extends EntityDinosaurSwimmingAggressive implements IEntityAISwimmingCreature, ICarnivore
{
    public EntityCoelacanth(World world)
    {
        super(world);

        // Placeholder AIs
        //this.tasks.addTask(0, new EntityAIAttackOnCollide(this, EntityPlayer.class, dinosaur.getAttackSpeed(), false));
        //this.targetTasks.addTask(0, new EntityAINearestAttackableTarget(this, EntityPlayer.class, false));
        //this.tasks.addTask(0, new EntityAIAttackOnCollide(this, EntityPig.class, dinosaur.getAttackSpeed(), false));
        //this.targetTasks.addTask(0, new EntityAINearestAttackableTarget(this, EntityPig.class, false));

        ///\this.targetTasks.addTask(1, new EntityAIHurtByTarget(this, true, EntityPlayer.class));

        //this.tasks.addTask(6, new EntityAIWander(this, dinosaur.getAdultSpeed()));
        //this.tasks.addTask(7, new EntityAIWatchClosest(this, EntityPlayer.class, 6.0F));
        //this.tasks.addTask(8, new EntityAILookIdle(this));
    }

    public void onUpdate()
    {
        super.onUpdate();
    }

}
