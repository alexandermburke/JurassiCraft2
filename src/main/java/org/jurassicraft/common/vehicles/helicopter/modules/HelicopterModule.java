package org.jurassicraft.common.vehicles.helicopter.modules;

import com.google.common.collect.Maps;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.Vec3;

import java.util.Collection;
import java.util.Map;

public abstract class HelicopterModule
{

    public static final Map<String, Class<? extends HelicopterModule>> registry;

    static
    {
        // register default modules
        registry = Maps.newHashMap();
        registry.put("door", HelicopterDoor.class);
        registry.put("minigun", HelicopterMinigun.class);
    }

    private final String moduleID;
    private final Collection<Class<? extends HelicopterModule>> supported;

    protected HelicopterModule(String id)
    {
        this.moduleID = id;
        supported = createSupportedModuleList();
    }

    protected abstract Collection<Class<? extends HelicopterModule>> createSupportedModuleList();

    public Collection<Class<? extends HelicopterModule>> getSupportedModules()
    {
        return supported;
    }

    /**
     * Base rotation of module, currently only used for rendering purposes.
     *
     * @return
     */
    public abstract float getBaseRotationAngle();

    /**
     * Returns the module ID, used for module rendering
     *
     * @return The module ID
     */
    public String getModuleID()
    {
        return moduleID;
    }

    public abstract boolean onClicked(HelicopterModuleSpot m, EntityPlayer player, Vec3 vec);

    public void onAdded(HelicopterModuleSpot m, EntityPlayer player, Vec3 vec)
    {

    }

    public void onRemoved(HelicopterModuleSpot m, EntityPlayer player, Vec3 vec)
    {

    }

    public abstract void writeToNBT(NBTTagCompound compound);

    public abstract void readFromNBT(NBTTagCompound compound);

    public static HelicopterModule createFromID(String id)
    {
        Class<? extends HelicopterModule> clazz = registry.get(id);
        if (clazz != null)
        {
            try
            {
                return clazz.newInstance();
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }
        return null;
    }
}
