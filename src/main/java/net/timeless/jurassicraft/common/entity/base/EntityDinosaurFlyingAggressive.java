package net.timeless.jurassicraft.common.entity.base;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.ai.EntityMoveHelper;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class EntityDinosaurFlyingAggressive extends EntityDinosaurAggressive
{
    public EntityDinosaurFlyingAggressive(World world)
    {
        super(world);
        this.moveHelper = new EntityDinosaurFlyingAggressive.FlyingMoveHelper();
        this.tasks.addTask(5, new EntityDinosaurFlyingAggressive.AIRandomFly());
        this.tasks.addTask(7, new EntityDinosaurFlyingAggressive.AILookAround());
    }

    @Override
    public void fall(float distance, float damageMultiplier)
    {
        // TODO slow itself down when landing, if falling too fast, take damage
    }

    @Override
    protected void updateFallState(double y, boolean onGroundIn, Block blockIn, BlockPos pos)
    {
    }

    /**
     * Moves the entity based on the specified heading.  Args: strafe, forward
     */
    @Override
    public void moveEntityWithHeading(float strafe, float forward)
    {
        if (this.isInWater())
        {
            this.moveFlying(strafe, forward, 0.02F);
            this.moveEntity(this.motionX, this.motionY, this.motionZ);
            this.motionX *= 0.800000011920929D;
            this.motionY *= 0.800000011920929D;
            this.motionZ *= 0.800000011920929D;
        }
        else if (this.isInLava())
        {
            this.moveFlying(strafe, forward, 0.02F);
            this.moveEntity(this.motionX, this.motionY, this.motionZ);
            this.motionX *= 0.5D;
            this.motionY *= 0.5D;
            this.motionZ *= 0.5D;
        }
        else
        {
            float f2 = 0.91F;

            if (this.onGround)
            {
                f2 = this.worldObj.getBlockState(new BlockPos(MathHelper.floor_double(this.posX), MathHelper.floor_double(this.getEntityBoundingBox().minY) - 1, MathHelper.floor_double(this.posZ))).getBlock().slipperiness * 0.91F;
            }

            float f3 = 0.16277136F / (f2 * f2 * f2);
            this.moveFlying(strafe, forward, this.onGround ? 0.1F * f3 : 0.02F);
            f2 = 0.91F;

            if (this.onGround)
            {
                f2 = this.worldObj.getBlockState(new BlockPos(MathHelper.floor_double(this.posX), MathHelper.floor_double(this.getEntityBoundingBox().minY) - 1, MathHelper.floor_double(this.posZ))).getBlock().slipperiness * 0.91F;
            }

            this.moveEntity(this.motionX, this.motionY, this.motionZ);
            this.motionX *= f2;
            this.motionY *= f2;
            this.motionZ *= f2;
        }

        this.prevLimbSwingAmount = this.limbSwingAmount;
        double d1 = this.posX - this.prevPosX;
        double d0 = this.posZ - this.prevPosZ;
        float f4 = MathHelper.sqrt_double(d1 * d1 + d0 * d0) * 4.0F;

        if (f4 > 1.0F)
        {
            f4 = 1.0F;
        }

        this.limbSwingAmount += (f4 - this.limbSwingAmount) * 0.4F;
        this.limbSwing += this.limbSwingAmount;
    }

    /**
     * returns true if this entity is by a ladder, false otherwise
     */
    @Override
    public boolean isOnLadder()
    {
        return false;
    }

    class AIRandomFly extends EntityAIBase
    {
        private final EntityDinosaurFlyingAggressive dino = EntityDinosaurFlyingAggressive.this;

        public AIRandomFly()
        {
            this.setMutexBits(1);
        }

        /**
         * Returns whether the EntityAIBase should begin execution.
         */
        @Override
        public boolean shouldExecute()
        {
            EntityMoveHelper moveHelper = this.dino.getMoveHelper();

            if (!moveHelper.isUpdating())
            {
                return true;
            }
            else
            {
                double d0 = moveHelper.getX() - this.dino.posX;
                double d1 = moveHelper.getY() - this.dino.posY;
                double d2 = moveHelper.getZ() - this.dino.posZ;
                double d3 = d0 * d0 + d1 * d1 + d2 * d2;
                return d3 < 1.0D || d3 > 3600.0D;
            }
        }

        /**
         * Returns whether an in-progress EntityAIBase should continue executing
         */
        @Override
        public boolean continueExecuting()
        {
            return false;
        }

        /**
         * Execute a one shot task or start executing a continuous task
         */
        @Override
        public void startExecuting()
        {
            Random random = this.dino.getRNG();
            double d0 = this.dino.posX + (random.nextFloat() * 2.0F - 1.0F) * 16.0F;
            double d1 = this.dino.posY + (random.nextFloat() * 2.0F - 1.0F) * 16.0F;
            double d2 = this.dino.posZ + (random.nextFloat() * 2.0F - 1.0F) * 16.0F;
            this.dino.getMoveHelper().setMoveTo(d0, d1, d2, 1.0D);
        }
    }

    class FlyingMoveHelper extends EntityMoveHelper
    {
        private final EntityDinosaurFlyingAggressive dino = EntityDinosaurFlyingAggressive.this;
        private int timer;

        public FlyingMoveHelper()
        {
            super(EntityDinosaurFlyingAggressive.this);
        }

        @Override
        public void onUpdateMoveHelper()
        {
            if (this.update)
            {
                double distX = this.posX - this.dino.posX;
                double distY = this.posY - this.dino.posY;
                double distZ = this.posZ - this.dino.posZ;
                double dist = distX * distX + distY * distY + distZ * distZ;

                if (this.timer-- <= 0)
                {
                    this.timer += this.dino.getRNG().nextInt(5) + 2;
                    dist = MathHelper.sqrt_double(dist);

                    if (this.canMoveTo(this.posX, this.posY, this.posZ, dist))
                    {
                        this.dino.motionX += distX / dist * 0.1D;
                        this.dino.motionY += distY / dist * 0.1D;
                        this.dino.motionZ += distZ / dist * 0.1D;
                    }
                    else
                    {
                        this.update = false;
                    }
                }
            }
        }

        private boolean canMoveTo(double posX, double posY, double posZ, double dist)
        {
            double d4 = (posX - this.dino.posX) / dist;
            double d5 = (posY - this.dino.posY) / dist;
            double d6 = (posZ - this.dino.posZ) / dist;
            AxisAlignedBB boudningBox = this.dino.getEntityBoundingBox();

            for (int i = 1; i < dist; ++i)
            {
                boudningBox = boudningBox.offset(d4, d5, d6);

                if (!this.dino.worldObj.getCollidingBoundingBoxes(this.dino, boudningBox).isEmpty())
                {
                    return false;
                }
            }

            return true;
        }
    }

    class AILookAround extends EntityAIBase
    {
        private final EntityDinosaurFlyingAggressive dino = EntityDinosaurFlyingAggressive.this;

        public AILookAround()
        {
            this.setMutexBits(2);
        }

        /**
         * Returns whether the EntityAIBase should begin execution.
         */
        @Override
        public boolean shouldExecute()
        {
            return true;
        }

        /**
         * Updates the task
         */
        @Override
        public void updateTask()
        {
            if (this.dino.getAttackTarget() == null)
            {
                this.dino.renderYawOffset = this.dino.rotationYaw = -((float) Math.atan2(this.dino.motionX, this.dino.motionZ)) * 180.0F / (float) Math.PI;
            }
            else
            {
                EntityLivingBase attackTarget = this.dino.getAttackTarget();
                double d0 = 64.0D;

                if (attackTarget.getDistanceSqToEntity(this.dino) < d0 * d0)
                {
                    double diffX = attackTarget.posX - this.dino.posX;
                    double diffZ = attackTarget.posZ - this.dino.posZ;
                    this.dino.renderYawOffset = this.dino.rotationYaw = -((float) Math.atan2(diffX, diffZ)) * 180.0F / (float) Math.PI;
                }
            }
        }
    }
}
