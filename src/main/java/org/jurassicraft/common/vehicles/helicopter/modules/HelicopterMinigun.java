package org.jurassicraft.common.vehicles.helicopter.modules;

import com.google.common.collect.Lists;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.Vec3;

import java.util.Collection;

public class HelicopterMinigun extends HelicopterModule
{
    public HelicopterMinigun()
    {
        super("minigun");
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

    @Override
    public boolean onClicked(HelicopterModuleSpot m, EntityPlayer player, Vec3 vec)
    {
        return false;
    }

    @Override
    public void writeToNBT(NBTTagCompound compound)
    {

    }

    @Override
    public void readFromNBT(NBTTagCompound compound)
    {

    }

}
