package org.jurassicraft.common.vehicles.helicopter.modules;

import com.google.common.collect.Lists;

import java.util.Collection;

public class HelicopterMinigun extends HelicopterModule
{
    public HelicopterMinigun()
    {
        super("ranger_helicopter_minigun");
    }

    @Override
    public Collection<Class<? extends HelicopterModule>> createSupportedModuleList()
    {
        return Lists.newArrayList();
    }

    @Override
    public float getBaseRotationAngle()
    {
        return 0;
    }
}
