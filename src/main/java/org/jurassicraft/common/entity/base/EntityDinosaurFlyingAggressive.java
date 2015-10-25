package org.jurassicraft.common.entity.base;

import net.minecraft.block.Block;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.ai.EntityMoveHelper;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

import java.util.Random;

public abstract class EntityDinosaurFlyingAggressive extends EntityDinosaurAggressive
{
    public float wingFlap, prevWingFlap;
    public boolean flying;

    public EntityDinosaurFlyingAggressive(World world)
    {
        super(world);
    }

    @Override
    public void onLivingUpdate()
    {
        super.onLivingUpdate();

        flying = true; //TODO add this on the datawatcher, do gl rotate on renderer

        if (flying)
        {
            float flapDiff = -(wingFlap - prevWingFlap);

            float flapMotion = flapDiff > 0 ? flapDiff : flapDiff / 2;

            float speedMultiplier = 0.25F;

            flapMotion = 1.5F;

            rotationPitch = 10;

            moveForward = 0.5F;

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
}
