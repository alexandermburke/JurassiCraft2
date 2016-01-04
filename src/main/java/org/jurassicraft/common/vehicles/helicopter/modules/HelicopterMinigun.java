package org.jurassicraft.common.vehicles.helicopter.modules;

import com.google.common.collect.Lists;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.Vec3;

import java.util.Collection;

public class HelicopterMinigun extends HelicopterRidableModule
{
    HelicopterMinigun()
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
        return super.onClicked(m, player, vec);
    }

    @Override
    protected float getDistanceFromCenter()
    {
        return 0;
    }

    @Override
    protected boolean shouldRiderSit()
    {
        return false;
    }
}
