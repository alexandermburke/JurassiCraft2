package org.jurassicraft.common.entity;

import net.minecraft.world.World;
import org.jurassicraft.common.entity.base.EntityDinosaurAggressive;
import net.timeless.unilib.common.animation.ChainBuffer;

public class EntityMajungasaurus extends EntityDinosaurAggressive // implements IEntityAICreature, ICarnivore
{
    public ChainBuffer tailBuffer = new ChainBuffer(6);

    public EntityMajungasaurus(World world)
    {
        super(world);
    }

    public void onUpdate()
    {
        this.tailBuffer.calculateChainSwingBuffer(68.0F, 5, 4.0F, this);
        super.onUpdate();
    }
}