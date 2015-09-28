package org.jurassicraft.common.vehicles.helicopter.modules;

import com.google.common.collect.Lists;
import io.netty.buffer.ByteBuf;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.nbt.NBTTagString;
import net.minecraftforge.fml.common.network.ByteBufUtils;

import java.util.List;

public class HelicopterModuleSpot
{

    private final List<HelicopterModule> modules;
    private final float angleFromCenter;

    public HelicopterModuleSpot(float angleFromCenter)
    {
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

    /**
     * Angle of the module spot compared to the right door (in radians)
     */
    public float getAngleFromCenter()
    {
        return angleFromCenter;
    }

    public void readFromNBT(NBTTagList tagList)
    {
        modules.clear();
        for(int i = 0;i<tagList.tagCount();i++)
        {
            String s = tagList.getStringTagAt(i);
            HelicopterModule module = HelicopterModule.registry.get(s);
            if(module == null)
            {
                System.err.println("Null module for id "+s);
            }
            modules.add(module);
        }
    }

    public void writeToNBT(NBTTagList tagList)
    {
        for(HelicopterModule m : modules)
        {
            tagList.appendTag(new NBTTagString(m.getModuleID()));
        }
    }

    public void readSpawnData(ByteBuf data)
    {
        modules.clear();
        int size = data.readByte();
        for(int i = 0;i<size;i++)
        {
            String id = ByteBufUtils.readUTF8String(data);
            HelicopterModule module = HelicopterModule.registry.get(id);
            if(module == null)
            {
                System.err.println("Null module for id "+id);
            }
            modules.add(module);
        }
    }

    public void writeSpawnData(ByteBuf data)
    {
        data.writeByte(modules.size());
        for(HelicopterModule m : modules)
        {
            ByteBufUtils.writeUTF8String(data, m.getModuleID());
        }
    }
}
