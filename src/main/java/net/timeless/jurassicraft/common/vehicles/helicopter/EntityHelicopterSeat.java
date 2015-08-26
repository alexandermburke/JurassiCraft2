package net.timeless.jurassicraft.common.vehicles.helicopter;

import net.minecraft.entity.Entity;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

/**
 * Entity representing a seat inside the helicopter. Should NOT be spawned inside the world, the {@link EntityHelicopterBase Helicopter Entity} handles that for you.
 */
public class EntityHelicopterSeat extends Entity
{
    public EntityHelicopterSeat(World worldIn)
    {
        super(worldIn);
        throw new RuntimeException("It is not allowed to spawn a seat by itself.");
    }

    @Override
    protected void entityInit()
    {

    }

    @Override
    protected void readEntityFromNBT(NBTTagCompound tagCompund)
    {

    }

    @Override
    protected void writeEntityToNBT(NBTTagCompound tagCompound)
    {

    }
}
