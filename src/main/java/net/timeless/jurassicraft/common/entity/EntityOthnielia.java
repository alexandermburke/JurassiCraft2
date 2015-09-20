package net.timeless.jurassicraft.common.entity;

import net.minecraft.world.World;
import net.timeless.jurassicraft.common.entity.base.EntityDinosaurDefensiveHerbivore;
import net.timeless.unilib.common.animation.ChainBuffer;

public class EntityOthnielia extends EntityDinosaurDefensiveHerbivore  //implements IEntityAICreature, IHerbivore
{
    public ChainBuffer tailBuffer = new ChainBuffer(6);
    
    public EntityOthnielia(World world)
    {
        super(world);
    }

    public void onUpdate()
    {
        this.tailBuffer.calculateChainSwingBuffer(68.0F, 5, 4.0F, this);
        super.onUpdate();
    }
}
