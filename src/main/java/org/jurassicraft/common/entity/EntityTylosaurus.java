package org.jurassicraft.common.entity;

import net.minecraft.world.World;
import net.timeless.unilib.common.animation.ChainBuffer;
import org.jurassicraft.common.entity.base.EntityDinosaurSwimmingAggressive;

public class EntityTylosaurus extends EntityDinosaurSwimmingAggressive // implements IEntityAISwimmingCreature, ICarnivore
{
    public ChainBuffer tailBuffer = new ChainBuffer(9);

    public EntityTylosaurus(World world)
    {
        super(world);
    }

    public void onUpdate()
    {
        super.onUpdate();

        this.tailBuffer.calculateChainSwingBuffer(68.0F, 7, 4.0F, this);
    }
}
