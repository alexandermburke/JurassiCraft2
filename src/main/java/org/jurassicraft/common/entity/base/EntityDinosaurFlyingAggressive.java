package org.jurassicraft.common.entity.base;

import net.minecraft.block.Block;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.ai.EntityMoveHelper;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import org.jurassicraft.common.entity.ai.EntityAIGlide;
import org.jurassicraft.common.entity.ai.EntityAILand;
import org.jurassicraft.common.entity.ai.EntityAITakeOff;

import java.util.Random;

public abstract class EntityDinosaurFlyingAggressive extends EntityDinosaurAggressive
{
    public float wingFlap, prevWingFlap;
    public boolean flying;

    public EntityDinosaurFlyingAggressive(World world)
    {
        super(world);
        this.tasks.addTask(1, new EntityAIGlide(this));
        this.tasks.addTask(1, new EntityAILand(this));
        this.tasks.addTask(1, new EntityAITakeOff(this));
    }

    @Override
    public void entityInit()
    {
        super.entityInit();
        this.dataWatcher.addObject(30, (byte) 0);
    }

    @Override
    public void onLivingUpdate()
    {
        super.onLivingUpdate();

        if (!worldObj.isRemote)
        {
            this.dataWatcher.updateObject(30, (byte) (flying ? 1 : 0));
        }
        else
        {
            this.flying = dataWatcher.getWatchableObjectByte(30) == 1;
        }

        //TODO do gl rotate on renderer

        if (flying)
        {
            float flapDiff = -(wingFlap - prevWingFlap);

            float flapMotion = flapDiff > 0 ? flapDiff : flapDiff / 2;

            flapMotion = 1.5F;
            moveForward = 0.5F;

            float speedMultiplier = 0.25F;

            double horizontalMotion = flapMotion * Math.cos(rotationPitch * (float)Math.PI / 180.0F);
            double verticalMotion = flapMotion * Math.sin(rotationPitch * (float) Math.PI / 180.0F);

            double x = MathHelper.sin(this.rotationYaw * (float)Math.PI / 180.0F) * horizontalMotion;
            double z = MathHelper.cos(this.rotationYaw * (float) Math.PI / 180.0F) * horizontalMotion;
            this.motionX = -x * speedMultiplier * moveForward;
            this.motionZ = z * speedMultiplier * moveForward;
            this.motionY = verticalMotion * speedMultiplier * moveForward;

            moveEntity(motionX, motionY, motionZ);

            prevWingFlap = wingFlap;
        }
    }

    @Override
    public void fall(float distance, float damageMultiplier)
    {
        // TODO slow itself down when landing, if falling too fast, take damage
    }

    @Override
    protected void updateFallState(double y, boolean onGroundIn, Block blockIn, BlockPos pos)
    {}

    @Override
    public void writeToNBT(NBTTagCompound nbt)
    {
        super.writeToNBT(nbt);
        nbt.setBoolean("Flying", flying);
    }

    @Override
    public void readFromNBT(NBTTagCompound nbt)
    {
        super.readFromNBT(nbt);
        flying = nbt.getBoolean("Flying");
    }

    class FlyingMoveHelper extends EntityMoveHelper
    {
        private EntityDinosaurFlyingAggressive flyingEntity = EntityDinosaurFlyingAggressive.this;

        public FlyingMoveHelper()
        {
            super(EntityDinosaurFlyingAggressive.this);
        }

        public void onUpdateMoveHelper()
        {
            this.entity.setMoveForward(0.0F);

            if (this.update)
            {
                this.update = false;
                int i = MathHelper.floor_double(this.entity.getEntityBoundingBox().minY + 0.5D);
                double d0 = this.posX - this.entity.posX;
                double d1 = this.posZ - this.entity.posZ;
                double d2 = this.posY - (double)i;
                double d3 = d0 * d0 + d2 * d2 + d1 * d1;

                if (d3 >= 2.500000277905201E-7D)
                {
                    float f = (float)(Math.atan2(d1, d0) * 180.0D / Math.PI) - 90.0F;
                    this.entity.rotationYaw = this.limitAngle(this.entity.rotationYaw, f, 30.0F);
                    this.entity.setAIMoveSpeed((float)(this.speed * this.entity.getEntityAttribute(SharedMonsterAttributes.movementSpeed).getAttributeValue()));

                    this.flyingEntity.rotationPitch = (float) (this.flyingEntity.getAIMoveSpeed() * d1 * 0.1D);
                }
            }
        }
    }
}
