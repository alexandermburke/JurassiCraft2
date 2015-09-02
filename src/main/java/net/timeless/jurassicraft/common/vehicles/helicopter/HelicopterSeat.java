package net.timeless.jurassicraft.common.vehicles.helicopter;

import com.google.common.base.Predicate;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.Entity;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.registry.IEntityAdditionalSpawnData;

import java.util.List;
import java.util.UUID;

import static com.google.common.base.Preconditions.*;

/**
 * Entity representing a seat inside the helicopter. Should NOT be spawned inside the world, the {@link EntityHelicopterBase Helicopter Entity} handles that for you.
 */
public class HelicopterSeat extends Entity implements IEntityAdditionalSpawnData
{
    private UUID parentID;
    private float relX;
    private float relY;
    private float relZ;
    private int index;
    EntityHelicopterBase parent;

    public HelicopterSeat(World worldIn)
    {
        super(worldIn);
        setEntityBoundingBox(AxisAlignedBB.fromBounds(Double.NEGATIVE_INFINITY, Double.NEGATIVE_INFINITY, Double.NEGATIVE_INFINITY, Double.NEGATIVE_INFINITY, Double.NEGATIVE_INFINITY, Double.NEGATIVE_INFINITY));
        noClip = true;
        parentID = UUID.randomUUID();
    }

    public HelicopterSeat(float relX, float relY, float relZ, int index, EntityHelicopterBase parent)
    {
        super(parent.getEntityWorld());
        setEntityBoundingBox(AxisAlignedBB.fromBounds(Double.NEGATIVE_INFINITY, Double.NEGATIVE_INFINITY,Double.NEGATIVE_INFINITY,Double.NEGATIVE_INFINITY,Double.NEGATIVE_INFINITY,Double.NEGATIVE_INFINITY));
        this.relX = relX;
        this.relY = relY;
        this.relZ = relZ;
        this.index = index;
        this.parent = checkNotNull(parent, "parent");
        parentID = parent.getHeliID();
        noClip = true;
    }

    @Override
    protected void entityInit()
    {
        width = 0f;
        height = 0f;
    }

    @Override
    public void onEntityUpdate()
    {
        motionX = 0f;
        motionY = 0f;
        motionZ = 0f;
        super.onEntityUpdate();
        if(parent == null) // we are in this state right after reloading a map
        {
            parent = getParentFromID(worldObj, parentID);
        }
        if(parent != null)
        {
            parent.seats[index] = this;

            float angle = parent.rotationYaw;
            double cos = Math.cos(Math.toRadians(angle));
            double sine = Math.sin(Math.toRadians(angle));
            double nx = getRelX() * sine;
            double nz = getRelZ() * cos;
            this.posX = parent.posX + nx;
            this.posY = parent.posY + getRelY();
            this.posZ = parent.posZ + nz;
            if(parent.isDead)
            {
                kill();
            }
        }
        else
        {
            System.out.println("no parent :c "+parentID);
        }
    }

    @Override
    protected void readEntityFromNBT(NBTTagCompound tagCompound)
    {
        relX = tagCompound.getFloat("relX");
        relY = tagCompound.getFloat("relY");
        relZ = tagCompound.getFloat("relZ");

        index = tagCompound.getInteger("index");

        parentID = UUID.fromString(tagCompound.getString("heliID"));
    }

    public static EntityHelicopterBase getParentFromID(World worldObj, final UUID id)
    {
        List list = worldObj.getEntities(EntityHelicopterBase.class, new Predicate()
        {
            @Override
            public boolean apply(Object input)
            {
                if(input instanceof EntityHelicopterBase)
                {
                    EntityHelicopterBase helicopter = (EntityHelicopterBase)input;
                    return helicopter.getHeliID().equals(id);
                }
                return false;
            }
        });
        if(list.isEmpty())
            return null;
        return (EntityHelicopterBase) list.get(0);
    }

    @Override
    protected void writeEntityToNBT(NBTTagCompound tagCompound)
    {
        tagCompound.setFloat("relX", relX);
        tagCompound.setFloat("relY", relY);
        tagCompound.setFloat("relZ", relZ);

        tagCompound.setInteger("index", index);

        tagCompound.setString("heliID", parentID.toString());
    }

    public float getRelX()
    {
        return relX;
    }

    public float getRelY()
    {
        return relY;
    }

    public float getRelZ()
    {
        return relZ;
    }

    public EntityHelicopterBase getParent()
    {
        return parent;
    }

    @Override
    public boolean canBePushed()
    {
        return false;
    }

    @Override
    public boolean canBeCollidedWith()
    {
        return false;
    }

    @Override
    public double getMountedYOffset()
    {
        return 0f;
    }

    public void setParentID(UUID parentID)
    {
        this.parentID = parentID;
    }

    @Override
    public void writeSpawnData(ByteBuf buffer)
    {
        ByteBufUtils.writeUTF8String(buffer, parentID.toString());
        System.out.println(">> sending "+parentID.toString());
    }

    @Override
    public void readSpawnData(ByteBuf additionalData)
    {
        parentID = UUID.fromString(ByteBufUtils.readUTF8String(additionalData));
        System.out.println(">> received "+parentID);
    }
}
