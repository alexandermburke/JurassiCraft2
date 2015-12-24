package org.jurassicraft.common.entity.ai;

import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import org.jurassicraft.common.entity.base.EntityDinosaur;

public class EntityAINearestAttackableTargetDinosaur extends EntityAINearestAttackableTarget
{
    public EntityAINearestAttackableTargetDinosaur(EntityCreature creature, Class classTarget, boolean checkSight)
    {
        super(creature, classTarget, checkSight);
    }

    @Override
    public boolean shouldExecute()
    {
        boolean s = super.shouldExecute();

        EntityDinosaur dinosaur = (EntityDinosaur) taskOwner;

        if (targetEntity != null)
        {
            if (targetEntity.getUniqueID().equals(dinosaur.getOwner()))
            {
                return false;
            }
        }

        return s;
    }
}
