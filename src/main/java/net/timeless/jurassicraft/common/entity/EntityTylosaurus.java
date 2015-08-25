package net.timeless.jurassicraft.common.entity;

import net.minecraft.world.World;
import net.timeless.jurassicraft.common.entity.base.EntityDinosaurSwimmingAggressive;
import net.timeless.unilib.common.animation.ChainBuffer;

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
