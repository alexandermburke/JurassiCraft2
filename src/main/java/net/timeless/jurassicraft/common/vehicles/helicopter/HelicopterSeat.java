package net.timeless.jurassicraft.common.vehicles.helicopter;

import com.google.common.base.Predicate;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

import static com.google.common.base.Preconditions.*;

/**
 * Entity representing a seat inside the helicopter. Should NOT be spawned inside the world, the {@link EntityHelicopterBase Helicopter Entity} handles that for you.
 */
public class HelicopterSeat extends Entity
{
    private UUID parentUUID;
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
        parentUUID = checkNotNull(parent.getUniqueID(), "parent.getUniqueID()");
        dataWatcher.updateObject(20, parentUUID.toString());
        noClip = true;
    }

    @Override
    protected void entityInit()
    {
        dataWatcher.addObject(20, "null");
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
        if(parentUUID == null)
        {
            parentUUID = UUID.fromString(dataWatcher.getWatchableObjectString(20));
        }
        if(parent == null) // we are in this state right after reloading a map
        {
            parent = getParentFromUUID(parentUUID);
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
    }

    @Override
    protected void readEntityFromNBT(NBTTagCompound tagCompound)
    {
        relX = tagCompound.getFloat("relX");
        relY = tagCompound.getFloat("relY");
        relZ = tagCompound.getFloat("relZ");

        index = tagCompound.getInteger("index");

        parentUUID = UUID.fromString(tagCompound.getString("parent"));
        dataWatcher.updateObject(20, parentUUID.toString());
    }

    private EntityHelicopterBase getParentFromUUID(final UUID uuid)
    {
        List list = worldObj.getEntities(EntityHelicopterBase.class, new Predicate()
        {
            @Override
            public boolean apply(Object input)
            {
                if(input instanceof EntityHelicopterBase)
                {
                    EntityHelicopterBase helicopter = (EntityHelicopterBase)input;
                    return helicopter.getUniqueID().equals(uuid);
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

        tagCompound.setString("parent", parentUUID.toString());
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

}
