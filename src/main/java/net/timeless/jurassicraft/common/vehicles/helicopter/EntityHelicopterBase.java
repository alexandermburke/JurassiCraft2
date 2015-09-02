package net.timeless.jurassicraft.common.vehicles.helicopter;

import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.MathHelper;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.registry.IEntityAdditionalSpawnData;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.timeless.jurassicraft.JurassiCraft;
import net.timeless.jurassicraft.common.entity.data.JCPlayerData;
import net.timeless.jurassicraft.common.message.JCNetworkManager;
import net.timeless.jurassicraft.common.message.MessageHelicopterEngine;

import javax.vecmath.Vector2f;
import java.util.UUID;

/**
 * Base entity for the helicopter, also holds the {@link HelicopterSeat Seat} entities and updates/handles them.
 */
public class EntityHelicopterBase extends EntityLivingBase implements IEntityAdditionalSpawnData
{
    private UUID heliID;
    public HelicopterSeat[] seats;
    public static final int ENGINE_RUNNING = 20;
    public static final int PILOT_SEAT = 0;
    public static final int LEFT_BACK_SEAT = 1;
    public static final int RIGHT_BACK_SEAT = 2;
    public static final float MAX_POWER = 40f;
    private float roll;
    private boolean engineRunning;
    private float enginePower;

    public EntityHelicopterBase(World worldIn)
    {
        super(worldIn);
        heliID = UUID.randomUUID();
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
        dataWatcher.addObject(ENGINE_RUNNING, (byte)0);
    }

    @Override
    public void readEntityFromNBT(NBTTagCompound tagCompound)
    {
        super.readEntityFromNBT(tagCompound);
        heliID = UUID.fromString(tagCompound.getString("heliID"));
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
        tagCompound.setString("heliID", heliID.toString());
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
            seat.setParentID(heliID);
            seat.parent = this;
        }

        if(seats[PILOT_SEAT] != null)
        {
            Entity riderEntity = seats[PILOT_SEAT].riddenByEntity;
            boolean runEngine = false;
            if(riderEntity != null) // There is a pilot!
            {
                EntityPlayer rider = (EntityPlayer)riderEntity;
                if(worldObj.isRemote) // We are on client
                {
                    runEngine = handleClientRunning(rider);
                    if(isPilotThisClient(rider)) {
                        updateEngine(runEngine);
                        engineRunning = runEngine;
                    }
                }
            }
            else
            {
                runEngine = false;
                System.out.println("no rider, resetting");
                updateEngine(runEngine);
            }
            if(engineRunning) {
                enginePower++;
                if(enginePower >= MAX_POWER) {
                    // We can fly \o/
                    // ♪ Fly on the wings of code! ♪
                    enginePower = MAX_POWER;
                }
            } else {
                enginePower--;
                if(enginePower < 0f) {
                    enginePower = 0f;
                }
            }
        }
    }

    public void updateEngine(boolean engineState) {
        if(worldObj.isRemote) {
            JCNetworkManager.networkWrapper.sendToServer(new MessageHelicopterEngine(heliID, engineState));
        } else {
            JCNetworkManager.networkWrapper.sendToAll(new MessageHelicopterEngine(heliID, engineState));
        }
    }

    /**
     * Checks if the current pilot is the player using this client
     * @param pilot
     *              The pilot
     * @return
     *          True if Client's player's UUID is equal to pilot
     */
    @SideOnly(Side.CLIENT)
    private boolean isPilotThisClient(EntityPlayer pilot)
    {
        return pilot.getUniqueID().equals(Minecraft.getMinecraft().thePlayer.getUniqueID());
    }

    @SideOnly(Side.CLIENT)
    private boolean handleClientRunning(EntityPlayer rider)
    {
        if(isPilotThisClient(rider))
        {
            if(Minecraft.getMinecraft().gameSettings.keyBindJump.isKeyDown())
            {
                return true;
            }
        }
        return false;
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

    public UUID getHeliID()
    {
        return heliID;
    }

    @Override
    public void writeSpawnData(ByteBuf buffer)
    {
        ByteBufUtils.writeUTF8String(buffer, heliID.toString());
    }

    @Override
    public void readSpawnData(ByteBuf additionalData)
    {
        heliID = UUID.fromString(ByteBufUtils.readUTF8String(additionalData));
    }

    public boolean isEngineRunning()
    {
        return engineRunning;
    }

    public void setEngineRunning(boolean engineRunning)
    {
        this.engineRunning = engineRunning;
    }

    public float getEnginePower()
    {
        return enginePower;
    }
}
