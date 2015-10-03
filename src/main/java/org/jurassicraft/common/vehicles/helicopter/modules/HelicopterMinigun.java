package org.jurassicraft.common.vehicles.helicopter.modules;

import com.google.common.collect.Lists;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.Vec3;

import java.util.Collection;

public class HelicopterMinigun extends HelicopterModule
{
    protected HelicopterMinigun()
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

    @Override
    public boolean onClicked(HelicopterModuleSpot m, EntityPlayer player, Vec3 vec)
    {
        return false;
    }
}
