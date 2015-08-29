package net.timeless.jurassicraft.common.vehicles.helicopter;

import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.*;
import net.minecraft.world.World;

import javax.vecmath.Vector2f;

/**
 * Base entity for the helicopter, also holds the {@link HelicopterSeat Seat} entities and updates/handles them.
 */
public class EntityHelicopterBase extends Entity
{
    public HelicopterSeat[] seats;
    public static final int PILOT_SEAT = 0;
    public static final int LEFT_BACK_SEAT = 1;
    public static final int RIGHT_BACK_SEAT = 2;
    private float roll;

    public EntityHelicopterBase(World worldIn)
    {
        super(worldIn);
        double w = 3f; // width in blocks
        double h = 3.1f; // height in blocks
        double d = 8f; // depth in blocks
        setBox(0, 0, 0, w, h, d);
        seats = new HelicopterSeat[3];
    }

    /**
     * Sets entity size
     * @param offsetX
     *              The offset of the box in blocks on the X axis
     * @param offsetY
     *              The offset of the box in blocks on the Y axis
     * @param offsetZ
     *              The offset of the box in blocks on the Z axis
     * @param w
     *          The width of the entity
     * @param h
     *          The height of the entity
     * @param d
     *          The depth of the entity
     */
    private void setBox(double offsetX, double offsetY, double offsetZ, double w, double h, double d)
    {
        double minX = this.getEntityBoundingBox().minX + offsetX;
        double minY = this.getEntityBoundingBox().minY + offsetY;
        double minZ = this.getEntityBoundingBox().minZ + offsetZ;
        double maxX = this.getEntityBoundingBox().minX + w + offsetX;
        double maxY = this.getEntityBoundingBox().minY + h + offsetY;
        double maxZ = this.getEntityBoundingBox().minZ + d + offsetZ;
        this.setEntityBoundingBox(new AxisAlignedBB(minX, minY, minZ, maxX, maxY, maxZ));
        this.width = (float) (maxX - minX);
        this.height = (float) (maxY - minY);
    }

    @Override
    protected void entityInit()
    {
    }

    @Override
    public void readEntityFromNBT(NBTTagCompound tagCompound)
    {
    }

    @Override
    public void setCurrentItemOrArmor(int slotIn, ItemStack stack)
    {

    }

    @Override
    public ItemStack[] getInventory()
    {
        return new ItemStack[0];
    }

    @Override
    public void writeEntityToNBT(NBTTagCompound tagCompound)
    {
    }

    @Override
    public void onUpdate()
    {
        super.onUpdate();
        motionY -= 0.2D;

        motionY *= 0.95;

        moveEntity(motionX, motionY, motionZ);

        ignoreFrustumCheck = true; // always draws the entity
        // Update seats positions
        for(HelicopterSeat seat : seats)
        {
            if(seat == null)
                continue;
            seat.parent = this;
        }

        if(seats[PILOT_SEAT] != null)
        {
            Entity riderEntity = seats[PILOT_SEAT].riddenByEntity;
            if(riderEntity != null) // There is a pilot!
            {
                EntityLivingBase rider = (EntityLivingBase)riderEntity;
                float forward = rider.moveForward;
                float strafe = rider.moveStrafing;
                Vector2f v = new Vector2f(forward, strafe);
                v.normalize();
                this.rotationYaw = rider.rotationYaw;
                this.roll = rider.rotationPitch;
            }
        }
    }

    @Override
    public boolean func_174825_a(EntityPlayer player, Vec3 vec)
    {
        // Transforms the vector in local coordinates (cancels possible rotations to simplify 'seat detection')
        Vec3 localVec = vec.rotateYaw((float) Math.toRadians(this.rotationYaw));
        for(int i = 0;i<seats.length;i++)
        {
            HelicopterSeat seat = seats[i];
            if(seat != null && isClicked(localVec, i))
            {
                System.out.println(seat);
                System.out.println(localVec);
                player.mountEntity(seat);
                return true;
            }
        }
        return false;
    }

    /**
     * Verifies if given seat is clicked.
     * @param vec
     *           The vec representing where the entity was clicked.
     * @param seat
     *            The seat's index
     * @return
     *        True if given seat is clicked, false otherwise
     */
    private boolean isClicked(Vec3 vec, int seat)
    {
        System.out.println(vec);
        switch(seat)
        {
            case PILOT_SEAT:
                if(vec.zCoord > 0.6)
                    return true;
                return false;

            default:
                return false;
        }

    }

    /**
     * Returns a boundingBox used to collide the entity with other entities and blocks. This enables the entity to be
     * pushable on contact, like boats or minecarts.
     */
    public AxisAlignedBB getCollisionBox(Entity entityIn)
    {
        return entityIn.getEntityBoundingBox();
    }

    public boolean attackEntityFrom(DamageSource source, float amount)
    {
        if(!isEntityInvulnerable(source))
        {
            this.setDead();
        }

        return super.attackEntityFrom(source, amount);
    }

    /**
     * returns the bounding box for this entity
     */
    public AxisAlignedBB getBoundingBox()
    {
        return this.getEntityBoundingBox();
    }

    /**
     * Returns true if other Entities should be prevented from moving through this Entity.
     */
    public boolean canBeCollidedWith()
    {
        return false;
    }

    /**
     * Returns true if this entity should push and be pushed by other entities when colliding.
     */
    public boolean canBePushed()
    {
        return false;
    }

    /**
     * Called when a player mounts an entity. e.g. mounts a pig, mounts a boat.
     */
    public void mountEntity(Entity entityIn)
    {
        if(!net.minecraftforge.event.ForgeEventFactory.canMountEntity(this, entityIn, true)){ return; }
        if (this.ridingEntity != null && entityIn == null)
        {
            if (!this.worldObj.isRemote)
            {
                this.dismountEntity(this.ridingEntity);
            }

            if (this.ridingEntity != null)
            {
                this.ridingEntity.riddenByEntity = null;
            }

            this.ridingEntity = null;
        }
        else
        {
            super.mountEntity(entityIn);
        }
    }

    /**
     * Moves the entity to a position out of the way of its mount.
     */
    public void dismountEntity(Entity p_110145_1_)
    {
        if(!net.minecraftforge.event.ForgeEventFactory.canMountEntity(this, p_110145_1_, false)){ return; }
        double d0 = p_110145_1_.posX;
        double d1 = p_110145_1_.getEntityBoundingBox().minY + (double)p_110145_1_.height;
        double d2 = p_110145_1_.posZ;
        byte b0 = 1;

        for (int i = -b0; i <= b0; ++i)
        {
            for (int j = -b0; j < b0; ++j)
            {
                if (i != 0 || j != 0)
                {
                    int k = (int)(this.posX + (double)i);
                    int l = (int)(this.posZ + (double)j);
                    AxisAlignedBB axisalignedbb = this.getEntityBoundingBox().offset((double)i, 1.0D, (double)j);

                    if (this.worldObj.func_147461_a(axisalignedbb).isEmpty())
                    {
                        if (World.doesBlockHaveSolidTopSurface(this.worldObj, new BlockPos(k, (int)this.posY, l)))
                        {
                            this.setPositionAndUpdate(this.posX + (double)i, this.posY + 1.0D, this.posZ + (double)j);
                            return;
                        }

                        if (World.doesBlockHaveSolidTopSurface(this.worldObj, new BlockPos(k, (int)this.posY - 1, l)) || this.worldObj.getBlockState(new BlockPos(k, (int)this.posY - 1, l)).getBlock().getMaterial() == Material.water)
                        {
                            d0 = this.posX + (double)i;
                            d1 = this.posY + 1.0D;
                            d2 = this.posZ + (double)j;
                        }
                    }
                }
            }
        }

        this.setPositionAndUpdate(d0, d1, d2);
    }

    /**
     * Sets the x,y,z of the entity from the given parameters. Also seems to set up a bounding box.
     */
    public void setPosition(double x, double y, double z)
    {
        super.setPosition(x, y, z);
    }

    /**
     * Applies a velocity to each of the entities pushing them away from each other. Args: entity
     */
    public void applyEntityCollision(Entity entityIn)
    {
    }

    public float getRoll()
    {
        return roll;
    }

    public void setRoll(float roll)
    {
        this.roll = roll;
    }
}
