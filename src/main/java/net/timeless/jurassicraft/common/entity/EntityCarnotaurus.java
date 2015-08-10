package net.timeless.jurassicraft.common.entity;

import net.minecraft.world.World;
import net.reuxertz.ecoapi.ecology.role.ICarnivore;
import net.reuxertz.ecoapi.entity.IEntityAICreature;
import net.timeless.jurassicraft.common.entity.base.EntityDinosaurAggressive;
import net.timeless.unilib.common.animation.ChainBuffer;

public class EntityCarnotaurus extends EntityDinosaurAggressive implements IEntityAICreature, ICarnivore
{
    public ChainBuffer tailBuffer = new ChainBuffer(6);

    public EntityCarnotaurus(World world)
    {
        super(world);
    }

    public void onUpdate()
    {
        super.onUpdate();
        this.tailBuffer.calculateChainSwingBuffer(68.0F, 5, 4.0F, this);
    }

    public String getLivingSound()
    {
        return randomSound("carnotaurus_living_1");
    }

    public String getHurtSound()
    {
        return randomSound("carnotaurus_hurt_1");
    }

    public String getDeathSound()
    {
        return randomSound("carnotaurus_death_1");
    }
}