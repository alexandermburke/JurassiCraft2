package org.jurassicraft.common.entity;

import net.minecraft.world.World;
import net.timeless.unilib.common.animation.ChainBuffer;
import org.jurassicraft.common.entity.base.EntityDinosaurDefensiveHerbivore;

public class EntityApatosaurus extends EntityDinosaurDefensiveHerbivore // implements IEntityAICreature, IHerbivore
{
    public ChainBuffer tailBuffer = new ChainBuffer(5);

    private int stepCount = 0;

    public EntityApatosaurus(World world)
    {
        super(world);
    }

    public void onUpdate()
    {
        super.onUpdate();
        this.tailBuffer.calculateChainSwingBuffer(68.0F, 5, 4.0F, this);

        /** Step Sound */
        if (this.moveForward > 0 && this.stepCount <= 0)
        {
            this.playSound("jurassicraft:stomp", (float) transitionFromAge(0.1F, 1.0F), this.getSoundPitch());
            stepCount = 65;
        }

        this.stepCount -= this.moveForward * 9.5;
    }
}