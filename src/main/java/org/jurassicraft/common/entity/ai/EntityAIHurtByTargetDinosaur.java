package org.jurassicraft.common.entity.ai;

import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import org.jurassicraft.common.entity.base.EntityDinosaur;

public class EntityAIHurtByTargetDinosaur extends EntityAIHurtByTarget
{
    public EntityAIHurtByTargetDinosaur(EntityCreature creature, boolean entityCallsForHelpIn, Class... targetClassesIn)
    {
        super(creature, entityCallsForHelpIn, targetClassesIn);
    }

    @Override
    public boolean shouldExecute()
    {
        EntityLivingBase entitylivingbase = this.taskOwner.getAttackTarget();

        EntityDinosaur dinosaur = (EntityDinosaur) taskOwner;

        if (entitylivingbase != null)
        {
            if (entitylivingbase.getUniqueID().equals(dinosaur.getOwner()))
            {
                return false;
            }
        }

        return super.shouldExecute();
    }
}
