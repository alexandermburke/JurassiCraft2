package net.timeless.jurassicraft.common.entity;

import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAIPanic;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import net.reuxertz.ecoapi.ecology.role.ICarnivore;
import net.reuxertz.ecoapi.entity.IEntityAICreature;
import net.timeless.animationapi.AnimationAPI;
import net.timeless.animationapi.IAnimatedEntity;
import net.timeless.jurassicraft.common.entity.ai.animations.JCAutoAnimBase;
import net.timeless.jurassicraft.common.entity.ai.animations.JCBegAnimation;
import net.timeless.jurassicraft.common.entity.base.EntityDinosaurDefensiveHerbivore;

public class EntityCompsognathus extends EntityDinosaurDefensiveHerbivore implements IEntityAICreature, ICarnivore
{
    public EntityCompsognathus(World world)
    {
        super(world);
        this.tasks.addTask(1, new EntityAIPanic(this, 2.0D));

        this.tasks.addTask(6, new EntityAIWander(this, dinosaur.getAdultSpeed()));
        this.tasks.addTask(7, new EntityAIWatchClosest(this, EntityPlayer.class, 6.0F));
        this.tasks.addTask(8, new EntityAILookIdle(this));

        tasks.addTask(2, new JCAutoAnimBase(this, 25, 13)); //Beg
    }

    public void onUpdate()
    {
        super.onUpdate();
        if (getAnimID() == 0)
            AnimationAPI.sendAnimPacket(this, 13);
    }
}