package org.jurassicraft.common.entity;

import net.minecraft.world.World;
import net.timeless.unilib.common.animation.ChainBuffer;
import org.jurassicraft.common.entity.base.EntityDinosaurAggressive;

public class EntitySegisaurus extends EntityDinosaurAggressive //implements IEntityAICreature, ICarnivore
{
    public ChainBuffer tailBuffer = new ChainBuffer(5);

    public EntitySegisaurus(World world)
    {
        super(world);
    }

    public void onUpdate()
    {
        this.tailBuffer.calculateChainSwingBuffer(68.0F, 5, 4.0F, this);
        super.onUpdate();
    }
}