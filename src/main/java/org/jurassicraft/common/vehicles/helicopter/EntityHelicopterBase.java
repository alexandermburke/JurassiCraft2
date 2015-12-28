package org.jurassicraft.common.vehicles.helicopter;

import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.registry.IEntityAdditionalSpawnData;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.timeless.unilib.utils.MutableVec3;
import org.jurassicraft.JurassiCraft;
import org.jurassicraft.common.item.ItemHelicopter;
import org.jurassicraft.common.message.MessageHelicopterDirection;
import org.jurassicraft.common.message.MessageHelicopterEngine;
import org.jurassicraft.common.vehicles.helicopter.modules.EntityHelicopterSeat;
import org.jurassicraft.common.vehicles.helicopter.modules.EnumModulePosition;
import org.jurassicraft.common.vehicles.helicopter.modules.HelicopterModule;
import org.jurassicraft.common.vehicles.helicopter.modules.HelicopterModuleSpot;

import java.util.UUID;

/**
 * Base entity for the helicopter, also holds the {@link EntityHelicopterSeat Seat} entities and updates/handles them.
 */
public class EntityHelicopterBase extends EntityLivingBase implements IEntityAdditionalSpawnData
{
    private final HelicopterModuleSpot[] moduleSpots;
    private boolean syncModules;
    private UUID heliID;
    public static final int ENGINE_RUNNING = 20;
    public static final int PILOT_SEAT = EnumModulePosition.MAIN_SEAT.ordinal();
    public static final int LEFT_PART = EnumModulePosition.BACK_LEFT.ordinal();
    public static final int RIGHT_PART = EnumModulePosition.BACK_RIGHT.ordinal();
    public static final float MAX_POWER = 80f;
    public static final float REQUIRED_POWER = MAX_POWER / 2f;
    private float roll;
    private boolean engineRunning;
    private float enginePower;
    private boolean hasMinigun;
    private MutableVec3 direction;

    public EntityHelicopterBase(World worldIn, ItemHelicopter creator)
    {
        this(worldIn);
        prepareDefaultModules();
    }

    public EntityHelicopterBase(World worldIn)
    {
        super(worldIn);
        heliID = UUID.randomUUID();
        double w = 3f; // width in blocks
        double h = 3.1f; // height in blocks
        double d = 8f; // depth in blocks
        setBox(0, 0, 0, w, h, d);
        moduleSpots = new HelicopterModuleSpot[EnumModulePosition.values().length];
        moduleSpots[PILOT_SEAT] = new HelicopterModuleSpot(EnumModulePosition.MAIN_SEAT, this, 0);
        moduleSpots[LEFT_PART] = new HelicopterModuleSpot(EnumModulePosition.BACK_LEFT, this, (float) Math.PI);
        moduleSpots[RIGHT_PART] = new HelicopterModuleSpot(EnumModulePosition.BACK_RIGHT, this, 0);

        direction = new MutableVec3(0, 1, 0);
        syncModules = true;
    }

    public void prepareDefaultModules()
    {
        syncModules = false;
        getModuleSpot(EnumModulePosition.MAIN_SEAT).addModule(HelicopterModule.seat);
        getModuleSpot(EnumModulePosition.BACK_LEFT).addModule(HelicopterModule.door);
        getModuleSpot(EnumModulePosition.BACK_RIGHT).addModule(HelicopterModule.minigun);
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
        heliID = UUID.fromString(tagCompound.getString("heliID"));

        moduleSpots[PILOT_SEAT].readFromNBT(tagCompound.getCompoundTag("pilotModules"));
        moduleSpots[LEFT_PART].readFromNBT(tagCompound.getCompoundTag("leftModules"));
        moduleSpots[RIGHT_PART].readFromNBT(tagCompound.getCompoundTag("rightModules"));
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

        NBTTagCompound pilotModulesList = new NBTTagCompound();
        moduleSpots[PILOT_SEAT].writeToNBT(pilotModulesList);
        tagCompound.setTag("pilotModules", pilotModulesList);

        NBTTagCompound leftModulesList = new NBTTagCompound();
        moduleSpots[LEFT_PART].writeToNBT(leftModulesList);
        tagCompound.setTag("leftModules", leftModulesList);

        NBTTagCompound rightModulesList = new NBTTagCompound();
        moduleSpots[RIGHT_PART].writeToNBT(rightModulesList);
        tagCompound.setTag("rightModules", rightModulesList);
    }

    @Override
    public void onLivingUpdate()
    {
        super.onLivingUpdate();
        fallDistance = 0f;
        ignoreFrustumCheck = true; // always draws the entity
        // Update seats positions
        for (HelicopterModuleSpot spot : moduleSpots)
        {
            if (spot == null)
            {
                continue;
            }
            if (spot.has(HelicopterModule.seat))
            {
                EntityHelicopterSeat seat = HelicopterModule.seat.getEntity(spot);
                if (seat != null)
                {
                    seat.setParentID(heliID);
                    seat.parent = this;
                }
            }
        }

        if (getModuleSpot(EnumModulePosition.MAIN_SEAT).has(HelicopterModule.seat))
        {
            EntityHelicopterSeat seat = HelicopterModule.seat.getEntity(getModuleSpot(EnumModulePosition.MAIN_SEAT));
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
                            if (engineRunning && enginePower >= REQUIRED_POWER)
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
                        motionY += my;
                        motionX = localDir.xCoord / 10f;
                        motionZ = localDir.zCoord / 10f;
                    }
                    if (enginePower >= MAX_POWER)
                    {
                        enginePower = MAX_POWER;
                    }
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
            }
        }
    }

    private void updateDirection(MutableVec3 direction)
    {
        if (worldObj.isRemote)
        {
            JurassiCraft.networkManager.networkWrapper.sendToServer(new MessageHelicopterDirection(getEntityId(), direction));
        }
        else
        {
            JurassiCraft.networkManager.networkWrapper.sendToAll(new MessageHelicopterDirection(getEntityId(), direction));
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
            JurassiCraft.networkManager.networkWrapper.sendToServer(new MessageHelicopterEngine(getEntityId(), engineState));
        }
        else
        {
            JurassiCraft.networkManager.networkWrapper.sendToAll(new MessageHelicopterEngine(getEntityId(), engineState));
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
    public boolean func_174825_a(EntityPlayer player, Vec3 vec)
    {
        // Transforms the vector in local coordinates (cancels possible rotations to simplify 'seat detection')
        Vec3 localVec = vec.rotateYaw((float) Math.toRadians(this.rotationYaw));
        System.out.println(localVec);
        for (int i = 0; i < moduleSpots.length; i++)
        {
            HelicopterModuleSpot spot = moduleSpots[i];
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
        return null;
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
        heliID = UUID.fromString(ByteBufUtils.readUTF8String(additionalData));

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
}
