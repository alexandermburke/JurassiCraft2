package org.jurassicraft.common.vehicles.helicopter.modules;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.Vec3;

import java.util.Collection;
import java.util.Collections;

public class HelicopterDoor extends HelicopterModule
{
    HelicopterDoor()
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

    @Override
    public boolean onClicked(HelicopterModuleSpot m, EntityPlayer player, Vec3 vec)
    {
        return false; // TODO: Open or close door
    }
}
