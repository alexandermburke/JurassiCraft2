package net.timeless.jurassicraft.common.entity;

import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import net.reuxertz.ecoapi.ecology.role.IHerbivore;
import net.reuxertz.ecoapi.entity.IEntityAICreature;
import net.timeless.jurassicraft.common.entity.base.EntityDinosaurProvokableHerbivore;
import net.timeless.unilib.common.animation.ChainBuffer;

public class EntityStegosaurus extends EntityDinosaurProvokableHerbivore implements IEntityAICreature, IHerbivore
{
    public ChainBuffer tailBuffer = new ChainBuffer(6);

    public EntityStegosaurus(World world)
    {
        super(world);
        this.defendFromAttacker(EntityTyrannosaurusRex.class, 1);
        this.defendFromAttacker(EntityPlayer.class, 3);
        this.defendFromAttacker(EntityIndominusRex.class, 1);

        this.tasks.addTask(6, new EntityAIWander(this, dinosaur.getAdultSpeed()));
        this.tasks.addTask(7, new EntityAIWatchClosest(this, EntityPlayer.class, 6.0F));
        this.tasks.addTask(8, new EntityAILookIdle(this));
    }

    public void onUpdate()
    {
        this.tailBuffer.calculateChainSwingBuffer(68.0F, 5, 4.0F, this);
        super.onUpdate();
    }
}