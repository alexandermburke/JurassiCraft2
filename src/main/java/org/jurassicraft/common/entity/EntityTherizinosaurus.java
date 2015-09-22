package org.jurassicraft.common.entity;

import net.minecraft.world.World;
import net.timeless.unilib.common.animation.ChainBuffer;

import org.jurassicraft.common.entity.base.EntityDinosaurDefensiveHerbivore;

public class EntityTherizinosaurus extends EntityDinosaurDefensiveHerbivore // implements IEntityAICreature, IHerbivore
{
    public ChainBuffer tailBuffer = new ChainBuffer(6);

    public EntityTherizinosaurus(World world)
    {
        super(world);
    }

    @Override
    public void onUpdate()
    {
        tailBuffer.calculateChainSwingBuffer(68.0F, 5, 4.0F, this);
//        // DEBUG
//        if (this.getRNG().nextInt(1000) <= 10)
//        {
//            AnimationAPI.sendAnimPacket(this, AnimID.DYING);
//        }
        super.onUpdate();
    }
}
