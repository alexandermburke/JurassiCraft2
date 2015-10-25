package org.jurassicraft.common.entity.ai.metabolism;

import net.minecraft.entity.ai.EntityAIBase;
import org.jurassicraft.common.entity.base.EntityDinosaur;

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
        return !dinosaur.isDead && !dinosaur.isCarcass() && dinosaur.worldObj.getGameRules().getGameRuleBooleanValue("dinoMetabolism");
    }

    @Override
    public void updateTask()
    {
        dinosaur.decreaseEnergy();
        dinosaur.decreaseWater();
    }
}
