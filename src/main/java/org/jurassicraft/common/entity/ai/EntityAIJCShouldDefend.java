package org.jurassicraft.common.entity.ai;

import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.AxisAlignedBB;
import org.jurassicraft.common.entity.base.EntityDinosaur;

import java.util.Iterator;
import java.util.List;

public class EntityAIJCShouldDefend extends EntityAIJCTarget
{
    private final boolean entityCallsForHelp;
    /**
     * Store the previous revengeTimer value
     */
    private int revengeTimerOld;
    private final Class[] field_179447_c;

    public EntityAIJCShouldDefend(EntityDinosaur p_i45885_1_, boolean p_i45885_2_, Class... p_i45885_3_)
    {
        super(p_i45885_1_, false);
        this.entityCallsForHelp = p_i45885_2_;
        this.field_179447_c = p_i45885_3_;
        this.setMutexBits(1);
    }

    /**
     * Returns whether the EntityAIBase should begin execution.
     */
    @Override
    public boolean shouldExecute()
    {
        int i = this.taskOwner.getRevengeTimer();
        return i != this.revengeTimerOld && this.isSuitableTarget(this.taskOwner.getAITarget(), false);
    }

    /**
     * Execute a one shot task or start executing a continuous task
     */
    @Override
    public void startExecuting()
    {
        this.taskOwner.setAttackTarget(this.taskOwner.getAITarget());
        this.revengeTimerOld = this.taskOwner.getRevengeTimer();

        if (this.entityCallsForHelp)
        {
            double d0 = this.getTargetDistance();
            List list = this.taskOwner.worldObj.getEntitiesWithinAABB(this.taskOwner.getClass(), (new AxisAlignedBB(this.taskOwner.posX, this.taskOwner.posY, this.taskOwner.posZ, this.taskOwner.posX + 1.0D, this.taskOwner.posY + 1.0D, this.taskOwner.posZ + 1.0D)).expand(d0, 10.0D, d0));
            Iterator iterator = list.iterator();

            while (iterator.hasNext())
            {
                EntityCreature entitycreature = (EntityCreature) iterator.next();

                if (this.taskOwner != entitycreature && entitycreature.getAttackTarget() == null && !entitycreature.isOnSameTeam(this.taskOwner.getAITarget()))
                {
                    boolean flag = false;
                    Class[] aclass = this.field_179447_c;
                    int i = aclass.length;

                    for (int j = 0; j < i; ++j)
                    {
                        Class oclass = aclass[j];

                        if (entitycreature.getClass() == oclass)
                        {
                            flag = true;
                            break;
                        }
                    }

                    if (!flag)
                    {
                        this.func_179446_a(entitycreature, this.taskOwner.getAITarget());
                    }
                }
            }
        }

        super.startExecuting();
    }

    protected void func_179446_a(EntityCreature p_179446_1_, EntityLivingBase p_179446_2_)
    {
        p_179446_1_.setAttackTarget(p_179446_2_);
    }
}