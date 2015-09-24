package org.jurassicraft.common.entity;

import net.minecraft.world.World;
import net.timeless.animationapi.client.AnimID;
import org.jurassicraft.common.entity.ai.animations.JCAutoAnimBase;
import org.jurassicraft.common.entity.base.EntityDinosaurAggressive;
import net.timeless.unilib.common.animation.ChainBuffer;

public class EntityCompsognathus extends EntityDinosaurAggressive // implements IEntityAICreature, ICarnivore
{
    public ChainBuffer tailBuffer = new ChainBuffer(5);

    public EntityCompsognathus(World world)
    {
        super(world);

        tasks.addTask(2, new JCAutoAnimBase(this, 25, AnimID.BEGGING)); // Beg
    }

    @Override
    public void onUpdate()
    {
        super.onUpdate();

        // if (getAnimID() == 0)
        // AnimationAPI.sendAnimPacket(this, 13);

        this.tailBuffer.calculateChainSwingBuffer(68.0F, 5, 4.0F, this);
    }
}