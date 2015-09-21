package org.jurassicraft.common.entity;

import net.minecraft.world.World;
import org.jurassicraft.common.entity.base.EntityDinosaurDefensiveHerbivore;
import net.timeless.unilib.common.animation.ChainBuffer;

public class EntityMicroceratus extends EntityDinosaurDefensiveHerbivore // implements IEntityAICreature, IHerbivore
{
    public ChainBuffer tailBuffer = new ChainBuffer(5);

    public EntityMicroceratus(World world)
    {
        super(world);
    }

    public void onUpdate()
    {
        super.onUpdate();

        this.tailBuffer.calculateChainSwingBuffer(68.0F, 5, 4.0F, this);
    }
}