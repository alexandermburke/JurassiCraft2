package net.timeless.jurassicraft.common.entity.ai;

import java.util.Iterator;
import java.util.List;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.EntityAITarget;
import net.minecraft.entity.ai.RandomPositionGenerator;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.Vec3;
import net.timeless.jurassicraft.common.dinosaur.Dinosaur;
import net.timeless.jurassicraft.common.entity.base.EntityDinosaur;

public class EntityAIDefendFlee extends EntityAITarget
{
    private boolean entityCallsForHelp;
    /** Store the previous revengeTimer value */
    private int revengeTimerOld;

    public EntityAIDefendFlee(EntityDinosaur dinosaur, boolean callForHelp)
    {
        super(dinosaur, false);
        this.entityCallsForHelp = callForHelp;
        this.setMutexBits(1);
    }

    /**
     * Returns whether the EntityAIBase should begin execution.
     */
    public boolean shouldExecute()
    {
        int i = this.taskOwner.getRevengeTimer();
        return i != this.revengeTimerOld && this.isSuitableTarget(this.taskOwner.getAITarget(), false);
    }

    /**
     * Execute a one shot task or start executing a continuous task
     */
    public void startExecuting()
    {
        defend((EntityDinosaur) this.taskOwner, this.taskOwner.getAITarget());
        this.revengeTimerOld = this.taskOwner.getRevengeTimer();

        if (this.entityCallsForHelp)
        {
            double d0 = this.getTargetDistance();
            List list = this.taskOwner.worldObj.getEntitiesWithinAABB(this.taskOwner.getClass(), (new AxisAlignedBB(this.taskOwner.posX, this.taskOwner.posY, this.taskOwner.posZ, this.taskOwner.posX + 1.0D, this.taskOwner.posY + 1.0D, this.taskOwner.posZ + 1.0D)).expand(d0, 10.0D, d0));
            Iterator iterator = list.iterator();

            while (iterator.hasNext())
            {
                EntityDinosaur dinosaur = (EntityDinosaur)iterator.next();

                if (this.taskOwner != dinosaur && dinosaur.getAttackTarget() == null && !dinosaur.isOnSameTeam(this.taskOwner.getAITarget()))
                {
                    this.defend(dinosaur, this.taskOwner.getAITarget());
                }
            }
        }

        super.startExecuting();
    }

    protected void defend(EntityDinosaur dinosaur, EntityLivingBase attack)
    {
        boolean flee = false;
        
        if(attack instanceof EntityDinosaur)
        {
            EntityDinosaur attackEntityDinosaur = (EntityDinosaur) attack;
            Dinosaur attackDino = attackEntityDinosaur.getDinosaur();
            
            Dinosaur defendDino = dinosaur.getDinosaur();
            
            float defenderChance = (float) (dinosaur.getHealth() * dinosaur.transitionFromAge(defendDino.getBabyStrength(), defendDino.getAdultStrength()));
            float attackerChance = (float) (attackEntityDinosaur.getHealth() * attackEntityDinosaur.transitionFromAge(attackDino.getBabyStrength(), attackDino.getAdultStrength()));
            
            flee = attackerChance >= defenderChance;
        }
        
        if(flee)
        {
            Vec3 vec3 = RandomPositionGenerator.findRandomTargetBlockAwayFrom(dinosaur, 16, 7, new Vec3(attack.posX, attack.posY, attack.posZ));
            dinosaur.getNavigator().tryMoveToXYZ(vec3.xCoord, vec3.yCoord, vec3.zCoord, dinosaur.getDinosaur().getAttackSpeed());
        }
        else
        {
            dinosaur.setAttackTarget(attack);
        }
    }
}