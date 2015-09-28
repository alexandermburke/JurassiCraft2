package org.jurassicraft.common.vehicles.helicopter.modules;

import java.util.Collection;
import java.util.Collections;

public class HelicopterDoor extends HelicopterModule
{
    public HelicopterDoor()
    {
        super("ranger_helicopter_door");
    }

    @Override
    protected Collection<Class<? extends HelicopterModule>> createSupportedModuleList()
    {
        return Collections.emptyList();
    }

    @Override
    public float getBaseRotationAngle()
    {
        return (float) Math.PI;
    }
}
