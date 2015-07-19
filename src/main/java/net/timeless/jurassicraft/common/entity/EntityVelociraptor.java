package net.timeless.jurassicraft.common.entity;

import net.minecraft.entity.ai.EntityAILeapAtTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.passive.EntityPig;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import net.timeless.jurassicraft.common.api.animation.IAnimatedEntity;
import net.timeless.jurassicraft.common.entity.ai.EntityAIDefendFlee;
import net.timeless.jurassicraft.common.entity.base.EntityDinosaurAggressive;
import net.timeless.jurassicraft.common.entity.base.buffer.ChainBuffer;

public class EntityVelociraptor extends EntityDinosaurAggressive implements IAnimatedEntity
{
    public static final int LEAPING_ANIMATION_ID = 0;
    public ChainBuffer tailBuffer = new ChainBuffer(6);
    private int animID;

    public EntityVelociraptor(World world)
    {
        super(world);
        setAnimID(-1);

        //Attacks smaller dinosaurs(And pigs, everyone hates pigs!)
        this.attackCreature(EntityPig.class, 2);
        this.attackCreature(EntityPlayer.class, 0);
        this.attackCreature(EntityCompsognathus.class, 1);
        this.attackCreature(EntityGallimimus.class, 3);
        this.attackCreature(EntitySegisaurus.class, 1);
        this.attackCreature(EntityAchillobator.class, 0);

//        this.defendFromAttacker(EntityPlayer.class, 0);
//        this.defendFromAttacker(EntityTyrannosaurusRex.class, 0);
//        this.defendFromAttacker(EntityIndominusRex.class, 0);
//        this.defendFromAttacker(EntitySpinosaurus.class, 0);
//        this.defendFromAttacker(EntityAchillobator.class, 0);

        this.tasks.addTask(2, new EntityAIDefendFlee(this, true));
        
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

    @Override
    public void setAnimID(int id)
    {
        animID = id;
    }

    @Override
    public int getAnimID()
    {
        return animID;
    }
}
