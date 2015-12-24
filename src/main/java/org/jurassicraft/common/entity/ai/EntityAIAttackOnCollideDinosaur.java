package org.jurassicraft.common.entity.ai;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.EntityAIAttackOnCollide;
import org.jurassicraft.common.entity.base.EntityDinosaur;

public class EntityAIAttackOnCollideDinosaur extends EntityAIAttackOnCollide
{
    public EntityAIAttackOnCollideDinosaur(EntityCreature creature, Class<? extends Entity> targetClass, double speedIn, boolean useLongMemory)
    {
        super(creature, targetClass, speedIn, useLongMemory);
    }

    public EntityAIAttackOnCollideDinosaur(EntityCreature entity, double speed, boolean useLongMemory)
    {
        super(entity, speed, useLongMemory);
    }

    @Override
    public boolean shouldExecute()
    {
        EntityLivingBase entitylivingbase = this.attacker.getAttackTarget();

        EntityDinosaur dinosaur = (EntityDinosaur) attacker;

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
