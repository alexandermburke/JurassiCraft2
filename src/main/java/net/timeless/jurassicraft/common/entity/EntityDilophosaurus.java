package net.timeless.jurassicraft.common.entity;

import net.minecraft.entity.ai.EntityAIAttackOnCollide;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILeapAtTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.passive.EntityPig;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import net.timeless.jurassicraft.common.entity.base.EntityDinosaurAggressive;

public class EntityDilophosaurus extends EntityDinosaurAggressive
{
    public EntityDilophosaurus(World world)
    {
        super(world);

        //Attacks smaller dinosaurs
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
}