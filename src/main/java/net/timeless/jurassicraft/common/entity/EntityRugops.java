package net.timeless.jurassicraft.common.entity;

import net.minecraft.entity.ai.EntityAILeapAtTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.passive.EntityPig;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import net.timeless.jurassicraft.common.entity.base.EntityDinosaurAggressive;
import net.timeless.jurassicraft.common.entity.base.buffer.ChainBuffer;

public class EntityRugops extends EntityDinosaurAggressive
{
    public ChainBuffer tailBuffer = new ChainBuffer(6);

    private static final String[] hurtSounds = new String[] { "rugops_hurt_1", "rugops_hurt_2" };
    private static final String[] livingSounds = new String[] { "rugops_living_1", "rugops_living_2", "rugops_living_3", "rugops_living_4" };
    private static final String[] deathSounds = new String[] { "rugops_death_1", "rugops_death_2" };
    
    public EntityRugops(World world)
    {
        super(world);

        //Attacks smaller dinosaurs(And pigs, everyone hates pigs!)
        this.attackCreature(EntityPig.class, 2);
        this.attackCreature(EntityPlayer.class, 0);
        this.attackCreature(EntityCompsognathus.class, 1);
        this.attackCreature(EntityGallimimus.class, 3);
        this.attackCreature(EntitySegisaurus.class, 1);
        this.attackCreature(EntityAchillobator.class, 0);

        this.defendFromAttacker(EntityPlayer.class, 0);
        this.defendFromAttacker(EntityTyrannosaurusRex.class, 0);
        this.defendFromAttacker(EntityIndominusRex.class, 0);
        this.defendFromAttacker(EntitySpinosaurus.class, 0);
        this.defendFromAttacker(EntityAchillobator.class, 0);

        this.tasks.addTask(3, new EntityAILeapAtTarget(this, 0.4F));

        this.tasks.addTask(6, new EntityAIWander(this, dinosaur.getAdultSpeed()));
        this.tasks.addTask(7, new EntityAIWatchClosest(this, EntityPlayer.class, 6.0F));
        this.tasks.addTask(8, new EntityAILookIdle(this));
    }

    public void onUpdate()
    {
        this.tailBuffer.calculateChainSwingBuffer(68.0F, 5, 4.0F, this);
        super.onUpdate();
    }
    
    public String getLivingSound()
    {
        return randomSound(livingSounds);
    }

    public String getHurtSound()
    {
        return randomSound(hurtSounds);
    }

    public String getDeathSound()
    {
        return randomSound(deathSounds);
    }
}