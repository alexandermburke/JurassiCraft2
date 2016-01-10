package org.jurassicraft.common.vehicles.helicopter.modules;

import com.google.common.collect.Lists;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.Vec3;
import net.minecraftforge.common.util.Constants;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import org.jurassicraft.JurassiCraft;
import org.jurassicraft.common.message.MessageHelicopterModules;
import org.jurassicraft.common.vehicles.helicopter.EntityHelicopterBase;

import java.util.List;

public class HelicopterModuleSpot
{

    private final List<HelicopterModule> modules;
    private final float angleFromCenter;
    private final EnumModulePosition position;
    private final EntityHelicopterBase helicopter;

    public HelicopterModuleSpot(EnumModulePosition pos, EntityHelicopterBase helicopter, float angleFromCenter)
    {
        this.helicopter = helicopter;
        this.position = pos;
        this.angleFromCenter = angleFromCenter;
        modules = Lists.newArrayList();
    }

    /**
     * List of modules present in this spot.
     */
    public List<HelicopterModule> getModules()
    {
        return modules;
    }

    public boolean addModule(HelicopterModule m)
    {
        return addModule(m, null, new Vec3(0, 0, 0));
    }

    public boolean addModule(HelicopterModule m, EntityPlayer player, Vec3 v)
    {
        if (!modules.contains(m))
        {
            modules.add(m);
            m.onAdded(this, player, v);
            if (getHelicopter().shouldSyncModules() && !getHelicopter().worldObj.isRemote)
            {
                JurassiCraft.networkWrapper.sendToAll(new MessageHelicopterModules(helicopter.getEntityId(), position, this));
            }
            return true;
        }
        return false;
    }

    /**
     * Angle of the module spot compared to the right door (in radians)
     */
    public float getAngleFromCenter()
    {
        return angleFromCenter;
    }

    public void readFromNBT(NBTTagCompound compound)
    {
        modules.clear();
        NBTTagList list = compound.getTagList("modules", Constants.NBT.TAG_COMPOUND);
        for (int i = 0; i < list.tagCount(); i++)
        {
            NBTTagCompound moduleData = list.getCompoundTagAt(i);
            String id = moduleData.getString("id");
            HelicopterModule module = HelicopterModule.createFromID(id);
            if (module == null)
            {
                throw new IllegalArgumentException("Invalid module ID");
            }
        }
    }

    public void writeToNBT(NBTTagCompound compound)
    {
        NBTTagList list = new NBTTagList();
        for (HelicopterModule m : modules)
        {
            NBTTagCompound data = new NBTTagCompound();
            data.setString("id", m.getModuleID());
            m.writeToNBT(data);
            list.appendTag(data);
        }
        compound.setTag("modules", list);
    }

    public void readSpawnData(ByteBuf data)
    {
        modules.clear();
        int size = data.readInt();
        for (int i = 0; i < size; i++)
        {
            String id = ByteBufUtils.readUTF8String(data);
            HelicopterModule module = HelicopterModule.createFromID(id);
            NBTTagCompound nbt = ByteBufUtils.readTag(data);
            if (module == null)
            {
                System.err.println("Null module for id " + id);
            }
            else
            {
                System.out.println(">> Read for " + id + " " + nbt);
                module.readFromNBT(nbt);
                addModule(module);
            }
        }
    }

    public void writeSpawnData(ByteBuf data)
    {
        data.writeInt(modules.size());
        for (HelicopterModule m : modules)
        {
            ByteBufUtils.writeUTF8String(data, m.getModuleID());
            NBTTagCompound moduleData = new NBTTagCompound();
            m.writeToNBT(moduleData);
            ByteBufUtils.writeTag(data, moduleData);
        }
    }

    public EnumModulePosition getPosition()
    {
        return position;
    }

    public boolean isClicked(Vec3 v)
    {
        return position.isClicked(v);
    }

    public void onClicked(EntityPlayer player, Vec3 vec)
    {
        for (HelicopterModule m : modules)
        {
            System.out.println(">> Clicked on " + m.getModuleID());
            if (m.onClicked(this, player, vec))
            {
                return;
            }
        }
    }

    public EntityHelicopterBase getHelicopter()
    {
        return helicopter;
    }

    public boolean has(HelicopterModule module)
    {
        return modules.contains(module);
    }
}
