package net.timeless.jurassicraft.common.entity.ai;

import net.minecraft.entity.ai.EntityAIBase;
import net.timeless.jurassicraft.common.entity.base.EntityDinosaur;

public class EntityAIMetabolism extends EntityAIBase
{
    protected EntityDinosaur dinosaur;

    public EntityAIMetabolism(EntityDinosaur dinosaur)
    {
        this.dinosaur = dinosaur;
    }

    @Override
    public boolean shouldExecute()
    {
        return !dinosaur.isDead;
    }

    @Override
    public void updateTask()
    {
        dinosaur.decreaseEnergy();
        dinosaur.decreaseWater();
    }
}
