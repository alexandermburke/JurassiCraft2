package org.jurassicraft.common.vehicles.helicopter.modules;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.Vec3;

import java.util.Collection;
import java.util.Collections;

public class HelicopterDoor extends HelicopterModule
{
    public HelicopterDoor()
    {
        super("door");
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

    @Override
    public void writeToNBT(NBTTagCompound compound)
    {
        // TODO
    }

    @Override
    public void readFromNBT(NBTTagCompound compound)
    {
        // TODO
    }
}
