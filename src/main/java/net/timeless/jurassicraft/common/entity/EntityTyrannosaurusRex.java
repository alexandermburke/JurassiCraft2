package net.timeless.jurassicraft.common.entity;

import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.passive.EntityPig;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import net.reuxertz.ecoapi.ecology.role.ICarnivore;
import net.reuxertz.ecoapi.entity.IEntityAICreature;
import net.timeless.animationapi.AnimationAPI;
import net.timeless.animationapi.IAnimatedEntity;
import net.timeless.jurassicraft.common.entity.ai.EntityAIJCWander;
import net.timeless.jurassicraft.common.entity.ai.animations.JCAutoAnimBase;
import net.timeless.jurassicraft.common.entity.base.EntityDinosaurAggressive;
import net.timeless.unilib.common.animation.ChainBuffer;

public class EntityTyrannosaurusRex extends EntityDinosaurAggressive implements IAnimatedEntity, IEntityAICreature, ICarnivore
{
    public ChainBuffer tailBuffer = new ChainBuffer(6);

    private static final String[] hurtSounds = new String[] { "tyrannosaurus_hurt_1", "tyrannosaurus_hurt_2" };
    private static final String[] livingSounds = new String[] { "tyrannosaurus_living_1", "tyrannosaurus_living_2", "tyrannosaurus_living_3", "tyrannosaurus_living_4", "tyrannosaurus_living_5", "tyrannosaurus_living_6" };
    private static final String[] deathSounds = new String[] { "tyrannosaurus_death_1" };
    private static final String[] roarSounds = new String[] { "tyrannosaurus_roar_1" };
    private static final String[] breathSounds = new String[] { "tyrannosaurus_breath_1" };

    public EntityTyrannosaurusRex(World world)
    {
        super(world);

        this.attackCreature(EntityPig.class, 2);
        this.attackCreature(EntityPlayer.class, 0);
        this.attackCreature(EntityCompsognathus.class, 1);
        this.attackCreature(EntityGallimimus.class, 3);
        this.attackCreature(EntitySegisaurus.class, 1);
        this.attackCreature(EntityAchillobator.class, 0);
        this.attackCreature(EntityRugops.class, 1);
        this.attackCreature(EntityDilophosaurus.class, 0);
        this.attackCreature(EntityAnkylosaurus.class, 0);
        this.attackCreature(EntityBrachiosaurus.class, 0);
        this.attackCreature(EntityCarnotaurus.class, 0);
        this.attackCreature(EntityTriceratops.class, 0);
        this.attackCreature(EntityMajungasaurus.class, 0);
        this.attackCreature(EntityParasaurolophus.class, 0);
        this.attackCreature(EntityStegosaurus.class, 0);

        this.defendFromAttacker(EntityPlayer.class, 0);
        this.defendFromAttacker(EntityIndominusRex.class, 0);
        this.defendFromAttacker(EntitySpinosaurus.class, 0);
        this.defendFromAttacker(EntityGiganotosaurus.class, 0);

        this.targetTasks.addTask(1, new EntityAIHurtByTarget(this, true, EntityPlayer.class));

        this.tasks.addTask(6, new EntityAIJCWander(this, 40));
        this.tasks.addTask(7, new EntityAIWatchClosest(this, EntityPlayer.class, 6.0F));
        this.tasks.addTask(8, new EntityAILookIdle(this));

        tasks.addTask(2, new JCAutoAnimBase(this, 75, 1));
        tasks.addTask(2, new JCAutoAnimBase(this, 75, 2));
        tasks.addTask(2, new JCAutoAnimBase(this, 75, 3));
    }

    public String getLivingSound()
    {
        return randomSound(breathSounds);
    }

    public String getHurtSound()
    {
        return randomSound(hurtSounds);
    }

    public String getDeathSound()
    {
        return randomSound(deathSounds);
    }

    public void onUpdate()
    {
        this.tailBuffer.calculateChainSwingBuffer(68.0F, 5, 4.0F, this);
        super.onUpdate();
        if (getAnimID() == 0)
            AnimationAPI.sendAnimPacket(this, 1);
    }
}
