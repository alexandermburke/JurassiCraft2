package net.timeless.jurassicraft.common.entity;

import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.passive.EntityPig;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import net.timeless.jurassicraft.common.api.animation.IAnimatedEntity;
import net.timeless.jurassicraft.common.entity.ai.EntityAIJCWander;
import net.timeless.jurassicraft.common.entity.base.EntityDinosaurAggressive;
import net.timeless.jurassicraft.common.entity.base.buffer.ChainBuffer;

public class EntityTyrannosaurusRex extends EntityDinosaurAggressive implements IAnimatedEntity
{
    private int animationId = -1;

    public ChainBuffer tailBuffer = new ChainBuffer(6);

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
    }

    @Override
    public void setAnimID(int id)
    {
        this.animationId = id;
    }

    @Override
    public int getAnimID()
    {
        return animationId;
    }

    public void onUpdate()
    {
        this.tailBuffer.calculateChainSwingBuffer(68.0F, 5, 4.0F, this);
        super.onUpdate();
    }
}
