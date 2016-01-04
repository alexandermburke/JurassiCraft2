package org.jurassicraft.common.vehicles.helicopter.modules;

import java.util.Collection;
import java.util.Collections;

public class HelicopterSeat extends HelicopterRidableModule
{
    HelicopterSeat()
    {
        super("helicopter_seat");
    }

    @Override
    protected Collection<Class<? extends HelicopterModule>> createSupportedModuleList()
    {
        return Collections.emptyList();
    }

    @Override
    public float getBaseRotationAngle()
    {
        return 0;
    }

    @Override
    protected float getDistanceFromCenter()
    {
        return 1.3f;
    }

    @Override
    protected boolean shouldRiderSit()
    {
        return true;
    }
}
