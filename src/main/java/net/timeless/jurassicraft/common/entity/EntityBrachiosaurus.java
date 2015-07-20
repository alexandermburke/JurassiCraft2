package net.timeless.jurassicraft.common.entity;

import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import net.timeless.jurassicraft.common.entity.base.EntityDinosaurDefensiveHerbivore;

public class EntityBrachiosaurus extends EntityDinosaurDefensiveHerbivore
{
    public EntityBrachiosaurus(World world)
    {
        super(world);
        
        this.defendFromAttacker(false);
        
        this.tasks.addTask(6, new EntityAIWander(this, dinosaur.getAdultSpeed()));
        this.tasks.addTask(7, new EntityAIWatchClosest(this, EntityPlayer.class, 6.0F));
        this.tasks.addTask(8, new EntityAILookIdle(this));
        
    }
}