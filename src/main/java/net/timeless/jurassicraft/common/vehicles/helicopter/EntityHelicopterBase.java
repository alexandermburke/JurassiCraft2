package net.timeless.jurassicraft.common.vehicles.helicopter;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.MathHelper;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;

import javax.vecmath.Vector2f;

/**
 * Base entity for the helicopter, also holds the {@link HelicopterSeat Seat} entities and updates/handles them.
 */
public class EntityHelicopterBase extends EntityLivingBase
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
        super.entityInit();
    }

    @Override
    public void readEntityFromNBT(NBTTagCompound tagCompound)
    {
        super.readEntityFromNBT(tagCompound);
    }

    @Override
    public ItemStack getHeldItem()
    {
        return null;
    }

    @Override
    public ItemStack getEquipmentInSlot(int slotIn)
    {
        return null;
    }

    @Override
    public ItemStack getCurrentArmor(int slotIn)
    {
        return null;
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
        super.writeEntityToNBT(tagCompound);
    }

    @Override
    public void onLivingUpdate()
    {
        super.onLivingUpdate();
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

    /**
     * returns the bounding box for this entity
     */
    public AxisAlignedBB getBoundingBox()
    {
        return this.getEntityBoundingBox();
    }

    /**
     * Returns true if this entity should push and be pushed by other entities when colliding.
     */
    public boolean canBePushed()
    {
        return false;
    }

    public void collideWithNearbyEntities()
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
