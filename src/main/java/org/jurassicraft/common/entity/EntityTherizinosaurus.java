package org.jurassicraft.common.entity;

import net.minecraft.world.World;
import org.jurassicraft.common.entity.base.EntityDinosaurDefensiveHerbivore;
import net.timeless.unilib.common.animation.ChainBuffer;

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
//        {
//            AnimationAPI.sendAnimPacket(this, AnimID.ATTACKING);
//        }
        super.onUpdate();
    }
}
