package net.ilexiconn.jurassicraft.entity;

import net.ilexiconn.jurassicraft.entity.base.EntityDinosaurAggressive;
import net.minecraft.entity.ai.EntityAIAttackOnCollide;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

public class EntityVelociraptor extends EntityDinosaurAggressive
{
    public static final int LEAPING_ANIMATION_ID = 0;

    public EntityVelociraptor(World world)
    {
        super(world);
        this.tasks.addTask(0, new EntityAIAttackOnCollide(this, EntityPlayer.class, 0.5F, false));
        this.targetTasks.addTask(0, new EntityAINearestAttackableTarget(this, EntityPlayer.class, false));
    }

    public void onUpdate()
    {
        super.onUpdate();
    }
}
