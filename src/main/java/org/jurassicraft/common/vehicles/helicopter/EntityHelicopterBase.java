package org.jurassicraft.common.vehicles.helicopter;

import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import net.minecraftforge.common.util.Constants;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.registry.IEntityAdditionalSpawnData;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.timeless.unilib.utils.MutableVec3;
import org.jurassicraft.JurassiCraft;
import org.jurassicraft.common.message.MessageHelicopterDirection;
import org.jurassicraft.common.message.MessageHelicopterEngine;
import org.jurassicraft.common.vehicles.helicopter.modules.EntityHelicopterSeat;
import org.jurassicraft.common.vehicles.helicopter.modules.EnumModulePosition;
import org.jurassicraft.common.vehicles.helicopter.modules.HelicopterDoor;
import org.jurassicraft.common.vehicles.helicopter.modules.HelicopterMinigun;
import org.jurassicraft.common.vehicles.helicopter.modules.HelicopterModuleSpot;

import java.util.UUID;

/**
 * Base entity for the helicopter, also holds the {@link EntityHelicopterSeat Seat} entities and updates/handles them.
 */
public class EntityHelicopterBase extends EntityLivingBase implements IEntityAdditionalSpawnData
{
    private final HelicopterModuleSpot[] moduleSpots;
    private final EntityHelicopterSeat[] seats;
    private boolean syncModules;
    private UUID heliID;
    public static final int ENGINE_RUNNING = 20;
    public static final int FRONT = EnumModulePosition.FRONT.ordinal();
    public static final int LEFT_SIDE = EnumModulePosition.LEFT_SIZE.ordinal();
    public static final int RIGHT_SIDE = EnumModulePosition.RIGHT_SIDE.ordinal();
    public static final float MAX_POWER = 80f;
    public static final float REQUIRED_POWER = MAX_POWER / 2f;
    private float roll;
    private boolean engineRunning;
    private float enginePower;
    private MutableVec3 direction;

    public EntityHelicopterBase(World worldIn, UUID id)
    {
        this(worldIn);
        prepareDefaultModules();
        setID(id);
    }

    private void setID(UUID id)
    {
        this.heliID = id;
        if (seats != null)
        {
            for (EntityHelicopterSeat seat : seats)
            {
                if (seat != null)
                {
                    seat.setParentID(id);
                }
            }
        }
    }

    public EntityHelicopterBase(World worldIn)
    {
        super(worldIn);
        double w = 3f; // width in blocks
        double h = 3.1f; // height in blocks
        double d = 8f; // depth in blocks
        setBox(0, 0, 0, w, h, d);

        seats = new EntityHelicopterSeat[3];
        for (int i = 0; i < seats.length; i++)
        {
            float distance = i == 0 ? 1.5f : 0; // TODO: Better way to define position
            seats[i] = new EntityHelicopterSeat(distance, i, this, i == 0);
            worldObj.spawnEntityInWorld(seats[i]);
        }
        setID(UUID.randomUUID());
        moduleSpots = new HelicopterModuleSpot[EnumModulePosition.values().length];
        moduleSpots[FRONT] = new HelicopterModuleSpot(EnumModulePosition.FRONT, this, 0);
        moduleSpots[LEFT_SIDE] = new HelicopterModuleSpot(EnumModulePosition.LEFT_SIZE, this, (float) Math.PI);
        moduleSpots[RIGHT_SIDE] = new HelicopterModuleSpot(EnumModulePosition.RIGHT_SIDE, this, 0);

        direction = new MutableVec3(0, 1, 0);
        syncModules = true;
    }

    public void prepareDefaultModules()
    {
        syncModules = false;
        getModuleSpot(EnumModulePosition.LEFT_SIZE).addModule(new HelicopterDoor());
        getModuleSpot(EnumModulePosition.RIGHT_SIDE).addModule(new HelicopterMinigun());
        syncModules = true;
    }

    /**
     * Sets entity size
     *
     * @param offsetX The offset of the box in blocks on the X axis
     * @param offsetY The offset of the box in blocks on the Y axis
     * @param offsetZ The offset of the box in blocks on the Z axis
     * @param w       The width of the entity
     * @param h       The height of the entity
     * @param d       The depth of the entity
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
        dataWatcher.addObject(ENGINE_RUNNING, (byte) 0);
    }

    @Override
    public void readEntityFromNBT(NBTTagCompound tagCompound)
    {
        super.readEntityFromNBT(tagCompound);
        setID(UUID.fromString(tagCompound.getString("heliID")));

        NBTTagList spots = tagCompound.getTagList("spots", Constants.NBT.TAG_COMPOUND);
        for (int i = 0; i < spots.tagCount(); i++)
        {
            NBTTagCompound spotData = spots.getCompoundTagAt(i);
            EnumModulePosition position = EnumModulePosition.valueOf(spotData.getString("position").toUpperCase());
            getModuleSpot(position).readFromNBT(spotData);
        }
        System.out.println("read heliID=" + heliID);
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

        NBTTagList spots = new NBTTagList();
        for (HelicopterModuleSpot spot : moduleSpots)
        {
            NBTTagCompound spotData = new NBTTagCompound();
            spot.writeToNBT(spotData);
            String position = spot.getPosition().name().toLowerCase();
            spotData.setString("position", position);
            spots.appendTag(spotData);
        }
        tagCompound.setTag("spots", spots);

        System.out.println("wrote heliID=" + heliID);
    }

    @Override
    public void onLivingUpdate()
    {
        super.onLivingUpdate();
        fallDistance = 0f;
        ignoreFrustumCheck = true; // always draws the entity
        // Update seats positions
        for (EntityHelicopterSeat seat : seats)
        {
            if (seat != null)
            {
                seat.setParentID(heliID);
                seat.parent = this;
                seat.update();
            }
        }

        EntityHelicopterSeat seat = seats[0];
        if (seat != null)
        {
            Entity riderEntity = seat.riddenByEntity;
            boolean runEngine = false;
            if (riderEntity != null) // There is a pilot!
            {
                EntityPlayer rider = (EntityPlayer) riderEntity;
                if (worldObj.isRemote) // We are on client
                {
                    runEngine = handleClientRunning(rider);
                    if (isPilotThisClient(rider))
                    {
                        updateEngine(runEngine);
                        engineRunning = runEngine;
                        if (enginePower >= REQUIRED_POWER)
                        {
                            direction = drive(direction);
                        }
                        else
                        {
                            direction.set(0, 1, 0);
                        }
                    }
                }
            }
            else
            {
                runEngine = false;
                updateEngine(runEngine);
                direction.set(0, 1f, 0);
            }
            rotationYaw -= direction.xCoord * 1.25f;
            roll = (float) (direction.xCoord * 20f);
            rotationPitch = (float) -(direction.zCoord * 40f);
            updateDirection(direction);
            if (engineRunning)
            {
                enginePower++;
            }
            else
            {
                if (enginePower >= REQUIRED_POWER)
                {
                    enginePower -= 0.5f;
                }
                else
                {
                    enginePower--;
                }
                if (enginePower < 0f)
                {
                    enginePower = 0f;
                }
            }
            if (enginePower >= REQUIRED_POWER)
            {
                // We can fly \o/
                // ♪ Fly on the wings of code! ♪
                MutableVec3 localDir = new MutableVec3(direction.xCoord, direction.yCoord, direction.zCoord * 8f);
                localDir = localDir.rotateYaw((float) Math.toRadians(-rotationYaw));
                final float gravityCancellation = 0.08f;
                final float speedY = gravityCancellation + 0.005f;
                double my = speedY * localDir.yCoord;
                if (my < gravityCancellation)
                {
                    my = gravityCancellation;
                }
                motionY += my * 1 * (enginePower / MAX_POWER);
                motionX = localDir.xCoord / 10f;
                motionZ = localDir.zCoord / 10f;
            }
            if (enginePower >= MAX_POWER)
            {
                enginePower = MAX_POWER;
            }
        }
    }

    private void updateDirection(MutableVec3 direction)
    {
        if (worldObj.isRemote)
        {
            JurassiCraft.networkWrapper.sendToServer(new MessageHelicopterDirection(getEntityId(), direction));
        }
        else
        {
            JurassiCraft.networkWrapper.sendToAll(new MessageHelicopterDirection(getEntityId(), direction));
        }
    }

    @SideOnly(Side.CLIENT)
    private MutableVec3 drive(MutableVec3 direction)
    {
        if (Minecraft.getMinecraft().gameSettings.keyBindForward.isKeyDown())
        {
            direction.addVector(0, 0, 1f);
        }

        if (Minecraft.getMinecraft().gameSettings.keyBindBack.isKeyDown())
        {
            direction.addVector(0, 0, -1f);
        }

        if (Minecraft.getMinecraft().gameSettings.keyBindLeft.isKeyDown())
        {
            direction.addVector(1f, 0, 0);
        }

        if (Minecraft.getMinecraft().gameSettings.keyBindRight.isKeyDown())
        {
            direction.addVector(-1f, 0, 0);
        }

        if (!Minecraft.getMinecraft().gameSettings.keyBindUseItem.isKeyDown())
        {
            direction.addVector(0, 1, 0);
        }

        return direction.normalize();
    }

    public void updateEngine(boolean engineState)
    {
        if (worldObj.isRemote)
        {
            JurassiCraft.networkWrapper.sendToServer(new MessageHelicopterEngine(getEntityId(), engineState));
        }
        else
        {
            JurassiCraft.networkWrapper.sendToAll(new MessageHelicopterEngine(getEntityId(), engineState));
        }
    }

    /**
     * Checks if the current pilot is the player using this client
     *
     * @param pilot The pilot
     * @return True if Client's player's UUID is equal to pilot
     */
    @SideOnly(Side.CLIENT)
    private boolean isPilotThisClient(EntityPlayer pilot)
    {
        return pilot.getUniqueID().equals(Minecraft.getMinecraft().thePlayer.getUniqueID());
    }

    @SideOnly(Side.CLIENT)
    private boolean handleClientRunning(EntityPlayer rider)
    {
        if (isPilotThisClient(rider))
        {
            if (Minecraft.getMinecraft().gameSettings.keyBindJump.isKeyDown())
            {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean interactAt(EntityPlayer player, Vec3 vec)
    {
        // Transforms the vector in local coordinates (cancels possible rotations to simplify 'seat detection')
        Vec3 localVec = vec.rotateYaw((float) Math.toRadians(this.rotationYaw));
        System.out.println(localVec);

        if (localVec.zCoord > 0.6)
        {
            player.mountEntity(seats[0]);
            return true;
        }
        else if (localVec.zCoord < 0.6 && localVec.xCoord > 0)
        {
            player.mountEntity(seats[1]);
            return true;
        }
        else if (localVec.zCoord < 0.6 && localVec.xCoord < 0)
        {
            player.mountEntity(seats[2]);
            return true;
        }
        for (HelicopterModuleSpot spot : moduleSpots)
        {
            if (spot != null && spot.isClicked(localVec))
            {
                System.out.println(spot);
                spot.onClicked(player, vec);
                return true;
            }
        }
        return false;
    }

    /**
     * Returns a boundingBox used to collide the entity with other entities and blocks. This enables the entity to be pushable on contact, like boats or minecarts.
     */
    public AxisAlignedBB getCollisionBox(Entity entityIn)
    {
        return getBoundingBox();
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

        for (HelicopterModuleSpot spot : moduleSpots)
        {
            spot.writeSpawnData(buffer);
        }
    }

    @Override
    public void readSpawnData(ByteBuf additionalData)
    {
        setID(UUID.fromString(ByteBufUtils.readUTF8String(additionalData)));

        for (HelicopterModuleSpot spot : moduleSpots)
        {
            spot.readSpawnData(additionalData);
        }
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

    public void setDirection(MutableVec3 direction)
    {
        this.direction.set(direction);
    }

    public HelicopterModuleSpot[] getModuleSpots()
    {
        return moduleSpots;
    }

    public HelicopterModuleSpot getModuleSpot(EnumModulePosition pos)
    {
        return moduleSpots[pos.ordinal()];
    }

    public boolean shouldSyncModules()
    {
        return syncModules;
    }

    public EntityHelicopterSeat getSeat(int index)
    {
        if (index < 0 || index >= seats.length)
        {
            throw new ArrayIndexOutOfBoundsException(index + ", size is " + seats.length);
        }
        return seats[index];
    }


}
