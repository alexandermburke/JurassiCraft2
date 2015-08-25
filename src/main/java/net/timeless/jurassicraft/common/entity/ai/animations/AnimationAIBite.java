package net.timeless.jurassicraft.common.entity.ai.animations;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.util.DamageSource;
import net.timeless.animationapi.AIAnimation;
import net.timeless.jurassicraft.common.entity.EntityGallimimus;
import net.timeless.jurassicraft.common.entity.EntityTyrannosaurus;
import net.timeless.jurassicraft.common.entity.base.EntityDinosaur;

public class AnimationAIBite extends AIAnimation
{
    private EntityDinosaur entityBiting;
    private EntityLivingBase entityTarget;
    private int duration;
    private boolean eat;
    protected int id;

    public AnimationAIBite(EntityDinosaur dino, int duration, int id)
    {
        super(dino);
        this.entityBiting = dino;
        this.entityTarget = null;
        this.duration = duration;
        eat = false;
        this.id = id;
    }

    @Override
    public int getAnimID()
    {
        return id;
    }

    public boolean isAutomatic()
    {
        return true;
    }

    public int getDuration()
    {
        return this.duration;
    }

    public void startExecuting()
    {
        super.startExecuting();
        this.entityTarget = this.entityBiting.getAttackTarget();
    }

    public void updateTask()
    {
        if (this.entityTarget != null)
        {
            if (this.entityBiting.getAnimTick() < ((this.duration / 2) - 2))
                this.entityBiting.getLookHelper().setLookPositionWithEntity(this.entityTarget, 30F, 30F);

            if (this.entityBiting.getAnimTick() == ((this.duration / 2) - 2))
            {
                float damage = (float) getCreatureSpeed();

                if ((this.entityTarget.getHealth() - damage <= 0.0F) && this.entityBiting instanceof EntityTyrannosaurus && this.entityTarget instanceof EntityGallimimus)
                    eat = true;
                else
                {
                    eat = false;
                    this.entityTarget.attackEntityFrom(DamageSource.causeMobDamage(this.entityBiting), damage);
                }
            }
        }
    }

    public double getCreatureSpeed()
    {
        return (double) ((int) (100 * this.entityBiting.getEntityAttribute(SharedMonsterAttributes.movementSpeed).getAttributeValue())) / 100;
    }

    public void resetTask()
    {
        /** Eating animations, should not use super.resetTask, or the eating animation ID will be replaced */
//        if (eat && this.entityTarget instanceof EntityGallimimus && entityTarget.ridingEntity == null)
//        {
//            super.resetTask();
//            this.entityTarget.mountEntity(this.entityBiting);
//            this.entityBiting.setAttackTarget(null);
//            this.entityBiting.getNavigator().clearPathEntity();
//            entityBiting.setAnimationTick(0);
//            AnimationHandler.sendAnimationPacket(this.entityBiting, JurassiCraftAnimationIDs.EATING.animID());
//            EntityGallimimus gallimimus = (EntityGallimimus) this.entityTarget;
//            gallimimus.setAttackTarget(null);
//            gallimimus.getNavigator().clearPathEntity();
//            AnimationHandler.sendAnimationPacket(gallimimus, JurassiCraftAnimationIDs.BEING_EATEN.animID());
//            this.entityTarget = null;
//            return;
//        }

        this.entityTarget = null;
        super.resetTask();
    }
}

