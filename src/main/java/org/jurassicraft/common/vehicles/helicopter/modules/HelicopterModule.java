package org.jurassicraft.common.vehicles.helicopter.modules;

import com.google.common.collect.Maps;

import java.util.Collection;
import java.util.Map;

public abstract class HelicopterModule
{

    public static final Map<String, HelicopterModule> registry = Maps.newHashMap();
    public static final HelicopterModule door = new HelicopterDoor();
    public static final HelicopterModule minigun = new HelicopterMinigun();

    private final String moduleID;
    private final Collection<Class<? extends HelicopterModule>> supported;

    public HelicopterModule(String id)
    {
        this.moduleID = id;
        registry.put(id, this);
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
}
