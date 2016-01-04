package org.jurassicraft.common.entity.base;

import net.minecraft.block.Block;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.BlockPos;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import org.jurassicraft.common.entity.ai.flyer.EntityAIGlide;
import org.jurassicraft.common.entity.ai.flyer.EntityAILand;
import org.jurassicraft.common.entity.ai.flyer.EntityAITakeOff;

public abstract class EntityDinosaurFlyingAggressive extends EntityDinosaurAggressive
{
    private final static int DW_FLYING = 30;
    private final static int DW_WING_FLAP = 31;
    private int wingFlap, prevWingFlap;

    protected EntityDinosaurFlyingAggressive(World world)
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
        this.dataWatcher.addObject(DW_FLYING, (byte) 0);
        this.dataWatcher.addObject(DW_WING_FLAP, 0);
    }

    @Override
    public void onLivingUpdate()
    {
        super.onLivingUpdate();

        if (!this.isCarcass())
        {
            boolean flying = isFlying();
            int wingFlap = getWingFlap();

            if (flying && !worldObj.isRemote)
            {
                rotationPitch = 20;

//                setWingFlap(wingFlap + 1);

                //            flapMotion = 1.5F;
                moveForward = 0.15F;

                float speedMultiplier = 0.25F;

                double horizontalMotion = (float) (wingFlap - prevWingFlap) * Math.cos(rotationPitch * (float) Math.PI / 180.0F);
                double verticalMotion = (float) (wingFlap - prevWingFlap) * Math.sin(rotationPitch * (float) Math.PI / 180.0F);

                double x = MathHelper.sin(this.rotationYaw * (float) Math.PI / 180.0F) * horizontalMotion;
                double z = MathHelper.cos(this.rotationYaw * (float) Math.PI / 180.0F) * horizontalMotion;
                this.motionX += -x * speedMultiplier * moveForward;
                this.motionZ += z * speedMultiplier * moveForward;
                this.motionY = (verticalMotion * speedMultiplier * moveForward) * 3.75F;

                motionY -= 0.025F;

                motionX *= 1.06F;
                motionZ *= 1.06F;

                if (motionX > 0.15F)
                {
                    motionX = 0.15F;
                }

                if (motionZ > 0.15F)
                {
                    motionZ = 0.15F;
                }

                moveEntity(motionX, motionY, motionZ);

                prevWingFlap = wingFlap;
            }
        }
    }

    public boolean isFlying()
    {
        return dataWatcher.getWatchableObjectByte(DW_FLYING) == 1;
    }

    public void setFlying(boolean fly)
    {
        if (worldObj.isRemote)
        {
            return;
        }

        dataWatcher.updateObject(DW_FLYING, (byte) (fly ? 1 : 0));
    }

    public void setWingFlap(int wingFlap)
    {
        if (worldObj.isRemote)
        {
            return;
        }

        dataWatcher.updateObject(DW_WING_FLAP, wingFlap);
    }

    public int getWingFlap()
    {
        return dataWatcher.getWatchableObjectInt(DW_WING_FLAP);
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

    @Override
    public void writeToNBT(NBTTagCompound nbt)
    {
        super.writeToNBT(nbt);
        nbt.setBoolean("Flying", isFlying());
    }

    @Override
    public void readFromNBT(NBTTagCompound nbt)
    {
        super.readFromNBT(nbt);
        setFlying(nbt.getBoolean("Flying"));
    }
}
