package net.timeless.jurassicraft.common.entity.raptorsquad;

import net.minecraft.world.World;
import net.timeless.jurassicraft.common.entity.EntityVelociraptor;
import net.timeless.unilib.common.animation.ChainBuffer;
import net.timeless.unilib.common.animation.ControlledAnimation;

public class EntityDelta extends EntityVelociraptor
{
    public ChainBuffer tailBuffer = new ChainBuffer(6);

    public ControlledAnimation dontLean = new ControlledAnimation(5);

    private int frame = this.ticksExisted;

    public EntityDelta(World world)
    {
        super(world);
    }

    public void onUpdate()
    {
        this.tailBuffer.calculateChainSwingBuffer(68.0F, 5, 4.0F, this);
        super.onUpdate();
    }
}