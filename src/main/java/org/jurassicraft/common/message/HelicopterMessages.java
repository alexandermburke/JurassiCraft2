package org.jurassicraft.common.message;

import net.minecraft.world.World;
import org.jurassicraft.common.vehicles.helicopter.EntityHelicopterBase;

public class HelicopterMessages
{
    public static EntityHelicopterBase getHeli(World world, int heliID)
    {
        try
        {
            return (EntityHelicopterBase) world.getEntityByID(heliID);
        }
        catch(NullPointerException e)
        {
            // shhh
        }
        return null;
    }
}
